package com.yart.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yart.app.dao.AbstractDAO;
import com.yart.app.dao.UserDao;
import com.yart.app.domain.User;

@Service
public class UserService extends AbstractService<User>
{
    
    @Autowired
    UserDao userDao;

    @Override
    public AbstractDAO<User> getDao()
    {
        return userDao;
    }

}
