package com.example.demo.config;

import com.example.demo.filter.JwtAuthenticationFilter;
import com.example.demo.filter.JwtLoginFilter;
import com.example.demo.hander.AccessFailHander;
import com.example.demo.mapper.UserMapper;
import com.example.demo.secutity.MyAuthencationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/11 10:26
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthencationProvider myAuthencationProvider;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccessFailHander accessDeniedHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthencationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/resource").permitAll()
                .anyRequest().authenticated();

//                .and()
//                .addFilter(new JwtLoginFilter(authenticationManager()))
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userMapper));

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

}
