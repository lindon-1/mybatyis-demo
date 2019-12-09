package com.example.demo.mapper;

import com.example.demo.po.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 16:25
 */
@Mapper
public interface ResourceMapper {

    public void save(Resource resource);

    public Resource selectById(Long id);

    public List<Resource> selectByRoleId(Long roleId);
}
