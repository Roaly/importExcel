package com.demo.importExcel.entity;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;

    private String name;

    private Date createTime;
}
