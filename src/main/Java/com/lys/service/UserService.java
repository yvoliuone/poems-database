package com.lys.service;

import com.lys.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    User getUser(Integer id);

    void updateUser(User user);

    void insertUser(User user);
}
