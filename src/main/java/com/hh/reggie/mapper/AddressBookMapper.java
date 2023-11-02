package com.hh.reggie.mapper;


import com.hh.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    //新增地址
    void save(AddressBook addressBook);

    //设置默认地址
    void updateDefault(Long currentId);

    //根据id更新默认地址
    void updateById(Long id);

    //根据id查询地址
    AddressBook getById(Long id);

    //查询默认地址
    AddressBook getOne(Long currentId);

    //查询指定用户的全部地址
    List<AddressBook> list(Long userId);

    //逻辑删除地址
    void updateDelete(Long ids);
}
