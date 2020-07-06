package com.secur.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USER_SESSION")
public class UserSession
{
    public UserSession()
    {

    }

    public UserSession(String sessionId, String userName)
    {
        this.sessionId = sessionId;
        this.userName = userName;
    }

    @Id
    @Column(name="SESSION_ID")
    private String sessionId;

    @Column(name="USER_NAME")
    private String userName;

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
