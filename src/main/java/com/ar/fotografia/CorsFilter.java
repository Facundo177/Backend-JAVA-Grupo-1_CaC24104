package com.ar.fotografia;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpResquest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");

        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");

        httpResponse.setHeader("Access-Control-Max-Age", "600");

        if("OPTIONS".equalsIgnoreCase(httpResquest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);//200(ok)
            
        }

        chain.doFilter(request, response);
    }
   
}
















/* import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        httpResponse.setHeader("Access-Control-Max-Age", "600");

        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } 

        chain.doFilter(request, response);

    }

} */
