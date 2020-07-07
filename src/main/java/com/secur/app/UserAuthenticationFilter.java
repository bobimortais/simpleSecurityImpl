package com.secur.app;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class UserAuthenticationFilter implements Filter
{
    @Override
    public void doFilter (
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        System.out.println("URL called: " + ((HttpServletRequest) request).getRequestURL().toString());
        Cookie[] allCookies = ((HttpServletRequest) request).getCookies();

        if (allCookies != null) {
            Cookie session =
                    Arrays.stream(allCookies).filter(x -> x.getName().equals("springsession"))
                            .findFirst().orElse(null);

            if (session != null)
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                       null, null, null);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
