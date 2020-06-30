package com.secur.app;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Integer>
{
    /**
     * Query which returns the users from database for a specific user name
     * @return
     */
    @Query(value = "SELECT users FROM USERS users WHERE users.name=:name")
    List<UserEntity> findUser(@Param("name") String userName);
}
