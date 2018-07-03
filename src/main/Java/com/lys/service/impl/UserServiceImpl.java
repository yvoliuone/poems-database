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
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public String[] getUserTags(Integer id) {
        return userMapper.selectByPrimaryKey(id).getUsertags().split(",");
    }

}
