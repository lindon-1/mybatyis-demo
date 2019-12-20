package com.example.demo.secutity;

import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/11 10:13
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByName(s);
        if (!Objects.isNull(user)) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            MyDetails myDetails;
            if (user.getRole() != null && user.getRole().getName() != null) {
                authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
                myDetails= new MyDetails(user.getName(), user.getPassword(), authorities);
                log.info("myDetails info : {}", user.getRole().getName());
            } else {
                myDetails= new MyDetails(user.getName(), user.getPassword(), new ArrayList<>());
            }


            return myDetails;
        }
        throw new UsernameNotFoundException("用户不存在！");
    }
}
