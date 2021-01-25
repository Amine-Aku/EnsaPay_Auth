/*package ensa.example.backoffice.Security;

import ensa.example.backoffice.Entities.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

@Component
public class PasswordExpirationFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestUrl=httpRequest.getRequestURL().toString();

        System.out.println("PasswordExpirationFilter"+requestUrl);
        if (isUrlExcluded(httpRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }



        User customer = getLoggedInCustomer();

        if (customer != null && customer.isPasswordExpired()) {
            showChangePasswordPage(servletResponse, httpRequest, customer);
        } else {
            filterChain.doFilter(httpRequest, servletResponse);
        }


    }
}*/
