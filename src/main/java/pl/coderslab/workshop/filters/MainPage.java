package pl.coderslab.workshop.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;


@WebFilter("/*")
public class MainPage implements Filter {
    private static final Logger log = LogManager.getLogger();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        StringBuffer requestURL = httpReq.getRequestURL();
        String[] urls = {
          "/add","/delete","/edit","/show","/list","/theme/css/sb-admin-2.css"
        };
        log.debug(">>"+requestURL.toString()+"<<");
        if (StringUtils.indexOfAny(requestURL.toString(),urls)==-1){
            ((HttpServletResponse) response).sendRedirect("/list");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
