package com.example.demo.mapper;


import com.example.demo.po.Resource;
import com.example.demo.po.Role;
import com.example.demo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.parameters.P;


import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSave() {
        User user = new User();
        user.setPassword("112");
        user.setName("aa");
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
        System.out.println(role.getName());
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

    //    @Test
//    public void testRedis() {
//        List<String> list = Arrays.asList(new String[]{"11","22"});
//        long time = 1000 * 60 * 60;
//        //stringRedisTemplate.opsForValue().set("dd", list.toString());
//        String dd = stringRedisTemplate.opsForValue().get("dd");
//        System.out.println(dd);
//    }
    @Test
    public void testUserDate() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse("2019-12-09 15:47:49");
        List<User> users = userMapper.findByDate(parse);
        System.out.println(users.get(0));
        System.out.println(users.size());

        Date startTime = simpleDateFormat.parse("2019-12-08 15:47:45");
        Date endTime = simpleDateFormat.parse("2019-12-10 15:47:51");
        Map<String, Date> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<User> userList = userMapper.findByDateBetween(map);
        System.out.println(userList.toString());

    }

    @Test
    public void testUserFindAll() {
        List<User> users = userMapper.findAll();
        System.out.println(users);
    }

    @Test
    public void testUserFindCon() {
        User user = new User();
        user.setName("%a");
        user.setCreateTime(new Date());
        int count = userMapper.getCountByCondition(user);
        System.out.println(count);
    }

    @Test
    public void testUserProxy() {
        People people = new People();
        Peo peo = (Peo) Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(),
                ((proxy, method, args) -> {
                    if (method.getName().equals("talk")) {
                        System.out.println("执行根据条件查询用户");
                        return method.invoke(people, args);
                    }
                    return null;
                }));
        peo.talk();
    }

    @Test
    public void test() {
        User user = new User();
        user.setName("123");
        user.setPassword("123");
        User user1 = new User();
        user1.setName("123");
        user1.setPassword("124");
        User user2 = new User();
        user2.setName("124");
        user2.setPassword("125");
        User user3 = new User();
        user3.setName("125");
        user3.setPassword("125");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        Map<String, List<User>> userMap = users.stream().collect(Collectors.toMap(User::getName, p -> {
            List<User> list = new ArrayList<>();
            list.add(user);
            return list;
        }, (List<User> value1, List<User> value2) -> {
            value1.addAll(value2);
            return value1;
        }));
        System.out.println(userMap.toString());
    }

    @Test
    public void testList() {
        User user = new User();
        user.setName("123");
        user.setPassword("123");
        User user1 = new User();
        user1.setName("123");
        user1.setPassword("124");
        User user2 = new User();
        user2.setName("124");
        user2.setPassword("125");
        User user3 = new User();
        user3.setName("125");
        user3.setPassword("125");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
//        Map<String, String> userMap = users.stream().collect(Collectors.toMap(User::getName, User::getPassword));
//        System.out.println(userMap);
//        Map<String, String> userMap = users.stream().collect(Collectors.toMap(User::getName, User::getPassword, (String name1, String name2) -> name2));
//        System.out.println(userMap);
//        Map<String, String> userMap = users.stream().collect(Collectors.toMap(User::getName, User::getPassword, (String name1, String name2) -> name1 + ":" + name2));
//        System.out.println(userMap);
        Map<String, List<String>> userMap = users.stream().collect(Collectors.toMap(User::getName, perUser -> {
                    List<String> list = new ArrayList<>();
                    list.add(perUser.getPassword());
                    return list;
                },
                (List<String> password1, List<String> password2) -> {
                    password1.addAll(password2);
                    return password1;
                }
        ));
        System.out.println(userMap);
    }

}