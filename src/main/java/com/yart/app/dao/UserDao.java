package com.yart.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yart.app.domain.User;

/**
 *
 * @author Omid Pourhadi
 *
 */
public interface UserDao extends AbstractDAO<User>
{
    
    @Query("select u from User u where u.username = :uname")
    public User findByUsername(@Param("uname") String username);

}
