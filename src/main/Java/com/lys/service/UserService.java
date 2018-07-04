package com.lys.service;

import com.lys.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    User getUser(Integer id);

    String[] getUserTags(Integer id);

    void updateUser(User user);
}
