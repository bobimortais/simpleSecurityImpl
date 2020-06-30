package com.secur.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ExampleController
{
    @GetMapping(value = "/authenticate")
    public String authenticate(HttpServletResponse servletResponse)
    {
        String response = "Authenticated";
        Cookie cookie = new Cookie("springsession", "test");
        servletResponse.addCookie(cookie);
        return response;
    }

    @GetMapping(value = "/ronaldo")
    public String ronaldo()
    {
        String response = "User authenticated";
        return response;
    }
}
