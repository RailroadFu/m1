package com.jxau.m1.intercepters;

import com.jxau.m1.utils.MyRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter("/*")
public class EncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MyRequest myRequest = new MyRequest((HttpServletRequest)servletRequest);
        filterChain.doFilter(myRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
