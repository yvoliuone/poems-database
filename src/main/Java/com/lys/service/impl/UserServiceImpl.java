package com.lys.service.impl;

import com.lys.dao.UserMapper;
import com.lys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;

    @Transactional
    User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
