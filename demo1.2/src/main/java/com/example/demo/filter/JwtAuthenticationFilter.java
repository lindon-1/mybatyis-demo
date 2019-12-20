package com.example.demo.filter;

import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/10 17:06
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private UserMapper userMapper;
    private static final String signingKey = "lindongly";
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserMapper userMapper) {
        super(authenticationManager);
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        log.info("JwtAuthenticationFilter token : {}", authorization);
        if (authorization != null && !authorization.startsWith("Bearer")) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthorization(request);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        }

        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
//            String token = Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(authorization.replace("Bearer ", ""))
//                    .getBody().getSubject();
//
//            if (!Objects.isNull(token)) {
//                Long userId = Long.valueOf(token.split(":")[0]);
//                User user = userMapper.selectById(userId);
//                if (!Objects.isNull(user)) {
//                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//                }
//                return new UsernamePasswordAuthenticationToken(new User(), null, new ArrayList<>());
//            }
            Claims claims = Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(authorization.replace("Bearer ", ""))
                    .getBody();
            String user = claims.getSubject();
            List<String> roles = claims.get("role", List.class);
            List<GrantedAuthority> grantedAuthorities = roles.stream().map(e -> new SimpleGrantedAuthority(e)).collect(Collectors.toList());

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
            }

        }
        return null;
    }


}
