package com.demo.importExcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.demo.importExcel.entity.User;
import com.demo.importExcel.listener.DataReadListener;
import com.demo.importExcel.mapper.ImportExcelMapper;
import com.demo.importExcel.service.IDataBaseService;
import com.demo.importExcel.service.IImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ImportExcelServiceImpl implements IImportExcelService {

    @Autowired
    private IDataBaseService dataBaseService;

    /**
     * 导入Excel数据实现
     */
    @Override
    public void importExcel() {
        long startTime = System.currentTimeMillis();
        //Excel路径
        String path = "D://exportExcel.xlsx";
        //读取sheet的数量
        int numberSheet = 20;

        //创建一个固定大小的线程池，大小和sheet数量一样
        ExecutorService executor = Executors.newFixedThreadPool(numberSheet);

        //遍历所有sheet
        for (int i = 0; i < numberSheet; i++) {
            //lambda表达式中的变量必须是final的
            int sheetNumber = i;
            //向线程池提交任务
            executor.submit(()->{
               //使用EasyExcel获取相对于sheet数据
                EasyExcel.read(path, User.class,new DataReadListener(dataBaseService))
                        .sheet(sheetNumber)//sheet数
                        .doRead();//开始读取数据
            });
        }
        //线程池关闭
        executor.shutdown();

        //等待所以任务完成读取操作
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("导入时长：" + String.valueOf(endTime-startTime));
    }
}
