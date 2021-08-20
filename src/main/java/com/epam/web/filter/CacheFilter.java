package com.epam.web.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CacheFilter implements Filter {

    private final static String CACHE_CONTROL_HTTP1_1 = "Cache-Control";
    private final static String NO_CACHE_HTTP1_1 = "no-cache, no-store, must-revalidate";
    private final static String CACHE_CONTROL_HTTP1_0 = "Pragma";
    private final static String NO_CACHE_HTTP1_0 = "no-cache";
    private final static String EXPIRES = "Expires";
    private final static long EXPIRES_TIME_RIGHT_AWAY = 0L;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader(CACHE_CONTROL_HTTP1_1, NO_CACHE_HTTP1_1);
        res.setHeader(CACHE_CONTROL_HTTP1_0, NO_CACHE_HTTP1_0);
        res.setDateHeader(EXPIRES, EXPIRES_TIME_RIGHT_AWAY);


        chain.doFilter(request, response);
    }
}
