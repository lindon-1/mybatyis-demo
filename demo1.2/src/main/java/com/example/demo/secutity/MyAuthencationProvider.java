package com.example.demo.secutity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/11 10:11
 */
@Component
@Slf4j
public class MyAuthencationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
        if (userDetails.getPassword().equals(password)) {
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            log.info("authorities info : {}", authorities.size());
            return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        }

        return  null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
