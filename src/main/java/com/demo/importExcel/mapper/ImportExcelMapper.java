package com.demo.importExcel.mapper;

import com.demo.importExcel.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImportExcelMapper {

    /**
     * 批量导入数据
     * @param dataList
     */
    void batchInsertData(List<User> dataList);

    /**
     * 查询用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    int findUserById(String id);
}
