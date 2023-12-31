package com.hh.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.hh.reggie.common.BaseContext;
import com.hh.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static AntPathMatcher Path_MACHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求的url
        String requestURI = request.getRequestURI();

        log.info("拦截到请求:{}",requestURI);

        //定义不需要处理的url
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3.如果不需要处理则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            chain.doFilter(request,response);
            return;
        }
        //4-1.判断登陆状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee")!= null){
            Long empId = (Long)request.getSession().getAttribute("employee");
            log.info("用户已登录，用户id为{}",empId);

            BaseContext.setCurrentId(empId);

            chain.doFilter(request,response);
            return;
        }

        //4-2.判断移动端登陆状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user")!= null){
            Long userId = (Long)request.getSession().getAttribute("user");
            log.info("用户已登录，用户id为{}",userId);

            BaseContext.setCurrentId(userId);

            chain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
        //5.如果未登录则直接返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = Path_MACHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
