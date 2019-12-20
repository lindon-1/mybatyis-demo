package com.example.demo.controller;

import com.example.demo.mapper.ResourceMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/11 11:26
 */
@RestController
public class UserController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ResourceMapper resourceMapper;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    //@PreAuthorize("hasAnyAuthority('老师')")
    public String find() {
        List<User> users = userMapper.findAll();
        return users.get(0).getName();
    }

    @RequestMapping(value = "/resource")
    public List<com.example.demo.po.Resource> findResource() {
        return resourceMapper.findAll();
    }
}
