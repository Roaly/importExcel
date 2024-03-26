package com.demo.importExcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.demo.importExcel.entity.User;
import com.demo.importExcel.mapper.ImportExcelMapper;
import com.demo.importExcel.service.IDataBaseService;
import com.demo.importExcel.service.IImportExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

//自定义监听器，处理读取到的Excel数据
@Slf4j
public class DataReadListener implements ReadListener<User> {

    private IDataBaseService dataBaseService;

    /**
     * 每次批量插入数据的数量
     */
    private static final int batchSize = 1000;

    /**
     * 用于暂存数据的集合，直到数量等于batchSize时就会进行插入操作并清空集合
     */
    private List<User> batchList = new ArrayList();

    /**
     * 注入mapper
     * @param mapper
     */
    public DataReadListener(IDataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    //EasyExcel每读取一行数据就会执行一次
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        log.info("读取到的行数据：{}",user);
        if(validateData(user)){
            batchList.add(user);
        }else {
            //没有通过校验的数据，打印日志
            log.error("id为[{}]的数据没有通过校验",user.getId());
        }

        //如果集合数量大于设置的批量数量，那么就插入数据并清空集合
        if(batchList.size() >= batchSize){
            dataBaseService.batchInsert(batchList);
            batchList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("Excel读取完成！");
        //如果还有数据就一起插入到数据库中
        if(!batchList.isEmpty()){
            dataBaseService.batchInsert(batchList);
        }
    }


    /**
     * 数据校验
     * @param user
     * @return
     */
    private boolean validateData(User user){
        int userCount = dataBaseService.findUserById(user.getId());
        //判断是否存在数据库中
        if(userCount == 0){
            return true;
        }
        //处理其他逻辑校验........
        return false;
    }

}
