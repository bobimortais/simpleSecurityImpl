package com.secur.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class UserAuthenticationFilter implements Filter
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public void doFilter (
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
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
