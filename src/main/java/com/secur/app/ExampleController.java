package com.secur.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
public class ExampleController
{
    @GetMapping(value = "/authenticate")
    public String authenticate(HttpServletResponse servletResponse)
    {
        String response = "Authenticated";
        Cookie cookie = new Cookie("springsession", "test");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        servletResponse.addCookie(cookie);
        return response;
    }

    @GetMapping(value = "/ronaldo")
    public String ronaldo()
    {
        String response = "User authenticated";
        return response;
    }

    @GetMapping(value = "/exit")
    public String authenticate(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
    {
        Cookie[] allCookies = ((HttpServletRequest) servletRequest).getCookies();
        String response = "Logged out";
        if (allCookies != null) {
            Cookie session =
                    Arrays.stream(allCookies).filter(x -> x.getName().equals("springsession"))
                            .findFirst().orElse(null);

            if (session != null)
            {
                session.setMaxAge(0);
                servletResponse.addCookie(session);
            }
        }
        return response;
    }
}
