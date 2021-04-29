package com.epam.web.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class EncodingFilter implements Filter {

    private static final String UTF_8 = "UTF-8";
    private static final String REQUEST_ENCODING_PARAM = "requestEncoding";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter(REQUEST_ENCODING_PARAM);
        if (encoding == null){
            encoding = UTF_8;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(UTF_8);

        next.doFilter(request, response);
    }

    public void destroy() {
    }
}

