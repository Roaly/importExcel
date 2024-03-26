package com.demo.importExcel;

import com.demo.importExcel.entity.User;
import com.demo.importExcel.mapper.ImportExcelMapper;
import com.demo.importExcel.service.IImportExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserApplicationTests {

	@Autowired
	private IImportExcelService excelService;
	@Autowired
	private ImportExcelMapper excelMapper;

	@Test
	void contextLoads() {
		List<User> dataList = new ArrayList<>();
		User user = new User();
		user.setId("1");
		user.setName("测试");
		user.setCreateTime(new Date());
		dataList.add(user);
		excelMapper.batchInsertData(dataList);
	}


}
