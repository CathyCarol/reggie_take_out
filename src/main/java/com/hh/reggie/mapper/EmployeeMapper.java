package com.hh.reggie.mapper;
import com.hh.reggie.entity.Employee;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    //根据username获取用户
    Employee getUser(String username);

    //添加员工
    void save(Employee employee);

    @Select("select * from employee\n" +
            "        where name = #{name}\n" +
            "        limit #{page},#{pageSize}\n")
    //分页查询
    List<Employee> page(@Param("page") int page, @Param("pageSize")int pageSize,@Param("name") String name);

    //根据id更新员工信息
    void updateById(Long id);

    //根据id获取员工信息
    Employee getById(Long id);
}
