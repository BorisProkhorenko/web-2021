package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionLocaleFilter implements Filter {

    private static final String LOCALE = "javax.servlet.jsp.jstl.fmt.locale.session";
    private static final String LANG = "lang";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if (req.getParameter(LOCALE) != null) {
            String localeParam = req.getParameter(LOCALE);
            session.setAttribute(LANG, localeParam);
        }
        chain.doFilter(request, response);
    }

}



