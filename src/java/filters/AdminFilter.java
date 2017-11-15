package filters;

import businesslogic.UserService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 734972
 */
public class AdminFilter implements Filter {


    private FilterConfig filterConfig = null;
    
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
               
        try {
            
            HttpSession session = ((HttpServletRequest)request).getSession();
            String username = (String)session.getAttribute("username");
            
            UserService us = new UserService();
            
            if (us.get(username).getRole().getRoleID() == 1) {
                
                chain.doFilter(request, response);
                
            } else {
                
                ((HttpServletResponse)response).sendRedirect("home");
            }
        } catch (Exception ex) {
            ((HttpServletResponse)response).sendRedirect("login");
        }
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }

}
