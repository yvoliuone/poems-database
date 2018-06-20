package com.lys.controller;

import com.lys.model.User;
import com.lys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser.do")
    public User getUser(@RequestParam("id") Integer id){
        System.out.println("Poem ID received: " + id);

        User user = userService.getUser(id);

        return user;
    }
}
