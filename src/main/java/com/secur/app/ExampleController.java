package com.secur.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class ExampleController
{
    @Autowired
    private SessionRepo sessionRepo;

    @GetMapping(value = "/authenticate")
    public String authenticate(HttpServletResponse servletResponse)
    {
        String response = "Authenticated";
        String sessionId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("springsession", sessionId);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        servletResponse.addCookie(cookie);
        UserSession userSession = new UserSession(sessionId,"TEST");
        sessionRepo.save(userSession);
        return response;
    }

    @GetMapping(value = "/hello")
    public String hello(@CookieValue(value = "springsession") String sessionId)
    {
        List<UserSession> userSession = sessionRepo.findSession(sessionId);
        String response = "User " + userSession.get(0).getUserName() + " authenticated for session " + userSession.get(0).getSessionId();
        return response;
    }

    @GetMapping(value = "/exit")
    public String expireSession(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
    {
        Cookie[] allCookies = ((HttpServletRequest) servletRequest).getCookies();
        System.out.println("Sendo Executado");
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
            else
            {
                response = "There is not session to log out";
            }
        }
        else
        {
            response = "There is not session to log out";
        }
        return response;
    }
}
