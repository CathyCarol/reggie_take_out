package com.hh.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hh.reggie.entity.Employee;
import com.hh.reggie.mapper.EmployeeMapper;
import com.hh.reggie.service.EmployeeService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public Employee getUser(String username) {
        return employeeMapper.getUser(username);
    }

    @Override
    public void save(Employee employee) {
        employeeMapper.save(employee);
    }


    @Override
    public List<Employee> page(int page, int pageSize,String name) {
        return employeeMapper.page(page,pageSize,name);
    }

    @Override
    public void updateById(Long id) {
        employeeMapper.updateById(id);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.getById(id);
    }
}
