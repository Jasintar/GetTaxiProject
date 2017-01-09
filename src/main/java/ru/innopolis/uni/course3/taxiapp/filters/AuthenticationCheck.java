package ru.innopolis.uni.course3.taxiapp.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 27.12.2016.
 *
 * @author Julia Savicheva
 */
public class AuthenticationCheck implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession(false).getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
