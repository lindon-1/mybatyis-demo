package com.example.demo.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 11:23
 */
@Data
@Accessors
@ToString
public class User {
    private Long id;
    private String password;
    private String name;
    private Long roleId;
    private Date createTime;
    private Date updateTime;
    private Role role;
}
