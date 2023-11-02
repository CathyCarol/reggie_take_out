package com.hh.reggie.service.impl;
import com.hh.reggie.entity.AddressBook;
import com.hh.reggie.mapper.AddressBookMapper;
import com.hh.reggie.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public void save(AddressBook addressBook) {
        addressBookMapper.save(addressBook);
    }

    @Override
    public void updateDefault(Long currentId) {
        addressBookMapper.updateDefault(currentId);
    }

    @Override
    public boolean updateById(Long id) {
        addressBookMapper.updateById(id);
        return true;
    }

    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    @Override
    public AddressBook getOne(Long currentId) {
        return addressBookMapper.getOne(currentId);
    }

    @Override
    public List<AddressBook> list(Long userId) {
        return addressBookMapper.list(userId);
    }

    @Override
    public boolean updateDelete(Long ids) {
        addressBookMapper.updateDelete(ids);
        return true;
    }
}
