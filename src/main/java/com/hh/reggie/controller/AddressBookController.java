package com.hh.reggie.controller;

import com.hh.reggie.common.BaseContext;
import com.hh.reggie.common.R;
import com.hh.reggie.entity.AddressBook;
import com.hh.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        log.info("addressBook:{}", addressBook);
        //LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        //wrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        //wrapper.set(AddressBook::getIsDefault, 0);
        //SQL:update address_book set is_default = 0 where user_id = ?

        Long currentId = BaseContext.getCurrentId();

        addressBookService.updateDefault(currentId);

        addressBook.setIsDefault(1);
        //SQL:update address_book set is_default = 1 where id = ?
        Long id = addressBook.getId();
        addressBookService.updateById(id);
        return R.success(addressBook);
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public R<AddressBook> getDefault() {
        //LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        //queryWrapper.eq(AddressBook::getIsDefault, 1);

        //SQL:select * from address_book where user_id = ? and is_default = 1
        Long currentId = BaseContext.getCurrentId();
        AddressBook addressBook = addressBookService.getOne(currentId);

        if (null == addressBook) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);

        //条件构造器
        //LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(null != addressBook.getUserId(), AddressBook::getUserId, addressBook.getUserId());
        //queryWrapper.orderByDesc(AddressBook::getUpdateTime);
        Long userId = addressBook.getUserId();

        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(addressBookService.list(userId));
    }

    /**
     * 修改地址信息。
     *
     * @param addressBook
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody AddressBook addressBook) {
        log.info("修改地址信息：{}", addressBook);
        Long id = addressBook.getId();
        // 进行更新
        if (addressBookService.updateById(id)) {
            return R.success("更新用户信息成功");
        }
        return R.success("更新用户信息失败");
    }

    /**
     * 根据id删除地址（逻辑删除）
     *
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("根据id删除地址：{}", ids);
        // 逻辑删除：把指定id的用户is_deleted改为1即可
        //update address_book set id_deleted =1 where id=ids;
        //UpdateWrapper<AddressBook> addressBookUpdateWrapper = new UpdateWrapper<>();
        //addressBookUpdateWrapper.set("is_deleted", 1);
        //addressBookUpdateWrapper.eq("id", ids);
        if (!addressBookService.updateDelete(ids)) {
            return R.error("删除地址失败");
        }
        return R.error("删除地址成功");
    }




}
