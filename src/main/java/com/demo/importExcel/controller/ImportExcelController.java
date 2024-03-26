package com.demo.importExcel.controller;


import com.demo.importExcel.service.IImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportExcelController {

    @Autowired
    private IImportExcelService excelService;

    @GetMapping("readExcel")
    private void importExcel(){
        excelService.importExcel();
    }
}
