package com.demo.importExcel.service;

import com.demo.importExcel.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库操作实现接口
 */
public interface IDataBaseService {

    void batchInsert(List<User> dataList);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    int findUserById(String id);
}
