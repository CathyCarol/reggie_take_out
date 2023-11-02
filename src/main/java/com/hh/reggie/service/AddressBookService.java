package com.hh.reggie.service;


import com.hh.reggie.entity.AddressBook;

import java.util.List;

public interface AddressBookService  {
    //新增地址
    void save(AddressBook addressBook);

    //设置默认地址
    void updateDefault(Long currentId);

    //根据id更新默认地址
    boolean updateById(Long id);

    //根据id查询地址
    AddressBook getById(Long id);

    //查询默认地址
    AddressBook getOne(Long currentId);

    //查询指定用户的全部地址
    List<AddressBook> list(Long userId);

    //逻辑删除地址
    boolean updateDelete(Long ids);
}
