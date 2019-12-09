package com.example.demo.mapper;


import com.example.demo.po.Resource;
import com.example.demo.po.Role;
import com.example.demo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void testSave() {
        User user = new User();
        user.setPassword("111");
        user.setName("aaa");
        userMapper.save(user);
    }

    @Test
    public void testRole() {
        Role role = new Role();
        role.setName("老师");
        roleMapper.save(role);
    }

    @Test
    public void testRoleSe() {
        Role role = roleMapper.selectById(new Long(1));
        System.out.println(role.toString());
    }

    @Test
    public void testRessave() {
        Resource resource = new Resource();
        resource.setName("查看");
        resource.setRoleId(new Long(1));
        resourceMapper.save(resource);
        Resource resource1 = new Resource();
        resource1.setName("增加");
        resource1.setRoleId(new Long(1));
        resourceMapper.save(resource1);
    }

    @Test
    public void testResSe() {
        Resource resource = resourceMapper.selectById(new Long(1));
        System.out.println(resource);
    }
    @Test
    public void testUserSe() {
        User user = userMapper.selectById(1L);
        System.out.println(user);

    }
}