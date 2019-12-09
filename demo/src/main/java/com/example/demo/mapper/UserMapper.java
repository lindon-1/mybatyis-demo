package com.example.demo.mapper;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:40
 */
@Mapper
public interface UserMapper {
    public void save(User user);

    public User selectById(Long id);
}
