package com.demo.importExcel.service.impl;

import com.demo.importExcel.entity.User;
import com.demo.importExcel.mapper.ImportExcelMapper;
import com.demo.importExcel.service.IDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库操作实现类
 */
@Service
public class IDataBaseServiceImpl implements IDataBaseService {

    @Autowired
    private ImportExcelMapper excelMapper;

    /**
     * 批量新增
     */
    @Override
    @Transactional
    public void batchInsert(List<User> dataList) {
        excelMapper.batchInsertData(dataList);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public int findUserById(String id) {
        return excelMapper.findUserById(id);
    }
}
