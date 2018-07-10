package com.lys.service.impl;

import com.lys.dao.UserMapper;
import com.lys.model.User;
import com.lys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Transactional
    public User getUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Transactional
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
