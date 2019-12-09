package com.example.demo.mapper;

import com.example.demo.po.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:46
 */
@Mapper
public interface RoleMapper {

    public Role selectById(Long id);

    public void save(Role role);
}
