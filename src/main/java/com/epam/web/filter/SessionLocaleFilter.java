package com.epam.web.filter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SessionLocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("javax.servlet.jsp.jstl.fmt.locale.session") != null) {
            req.getSession().setAttribute("lang", req.getParameter("javax.servlet.jsp.jstl.fmt.locale.session"));
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {}
    @Override
    public void init(FilterConfig arg0){}
}



