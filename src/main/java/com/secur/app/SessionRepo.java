package com.secur.app;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepo extends CrudRepository<UserSession, Integer>
{
    /**
     * Query which returns the session from database for a specific session id
     * @return
     */
    @Query(value = "SELECT session FROM USER_SESSION session WHERE session.sessionId=:session_id")
    List<UserSession> findSession(@Param("session_id") String sessionId);
}
