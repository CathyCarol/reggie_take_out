package com.hh.reggie.service;
import com.hh.reggie.entity.Employee;

import java.util.List;

public interface EmployeeService  {
    //根据username获取用户
    Employee getUser(String username);
    //添加员工
    void save(Employee employee);
    //分页
    List<Employee> page(int page, int pageSize,String name);
    //根据id更新员工信息
    void updateById(Long id);
    //根据id获取员工信息
    Employee getById(Long id);

}
