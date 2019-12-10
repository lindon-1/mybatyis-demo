package com.example.demo.po;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:35
 */
@Data
@ToString
public class Role {
    private Long id;
    private Long userId;
    private String name;
    private Date createTime;
    private Date updateTime;
    private List<Resource> resources;
}
