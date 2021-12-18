package com.xumaggregatepayments.boot.globalConfig.InputStreamHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-56-31
 * @Description
*/
public class ReplaceStreamFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
           RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
           filterChain.doFilter(requestWrapper, servletResponse);
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
