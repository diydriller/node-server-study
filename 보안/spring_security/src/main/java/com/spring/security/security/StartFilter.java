package com.spring.security.security;

import javax.servlet.*;
import java.io.IOException;

public class StartFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터 시작");

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
