package com.yart.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yart.app.dao.UserDao;
import com.yart.app.domain.User;

/**
 *
 * @author Omid Pourhadi
 *
 */
public class UserDetailService implements UserDetailsService
{

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userDao.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("username not found");
        return user;
    }

}
