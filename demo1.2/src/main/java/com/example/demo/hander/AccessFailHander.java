package com.example.demo.hander;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/12 15:51
 */
@Component
public class AccessFailHander implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("权限不足！ 没有权限访问！");
    }
}
