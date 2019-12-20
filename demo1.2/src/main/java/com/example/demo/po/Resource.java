package com.example.demo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:36
 */
@Data
public class Resource {
    private Long id;
    private String name;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
    private Long roleId;
}
