package com.example.demo.filter;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.po.User;
import com.example.demo.secutity.MyDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/10 17:35
 */
@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private static final String signingKey = "lindongly";

    private Date date = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000);

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            MyDetails user = new MyDetails(userName, password, new ArrayList<>());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String token = Jwts.builder().setSubject(((MyDetails) authResult.getPrincipal()).getUsername()).setExpiration(date)
//                .signWith(SignatureAlgorithm.HS256, signingKey.getBytes())
//                .compact();
//        response.addHeader("Authorization", "Bearer " + token);
//        log.info("JwtLoginFilter token : {}", token);
//        response.getOutputStream().println(token);
        Claims claims = Jwts.claims();
        claims.put("role", authResult.getAuthorities().stream().map(e -> ((GrantedAuthority) e).getAuthority()).collect(Collectors.toList()));
        String token = Jwts.builder().setClaims(claims).setSubject(authResult.getName())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, signingKey.getBytes()).compact();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getOutputStream().println(token);
    }
}
