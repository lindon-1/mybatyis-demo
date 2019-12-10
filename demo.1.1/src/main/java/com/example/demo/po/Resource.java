package com.example.demo.po;

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
    private Date createTime;
    private Date updateTime;
    private Long roleId;
}
