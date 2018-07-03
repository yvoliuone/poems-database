package com.lys.controller;

import com.lys.model.Poem;
import com.lys.model.User;
import com.lys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser.do")
    public User getUser(@RequestParam("id") Integer id){
//        System.out.println("User ID received: " + id);

        User user = userService.getUser(id);

        return user;
    }


    @RequestMapping("/getDailyPoems.do")
    public ModelAndView getDailyPoems(@RequestParam("id") Integer id) {

        String[] tags = userService.getUserTags(id);

        String url = "redirect:/ poems/getPoemsByTags.do?tag1=" + tags[0] + "&tag2=" + tags[1];

        return new ModelAndView(url);
    }

    @RequestMapping("/updateTags.do")
    public String updateTags(@RequestParam("id") Integer id, @RequestParam("tags") String tagstring) {

        int[] intarr = atoiArr(tagstring.split(","));
        

    }

    private int[] atoiArr(String[] strarr) {
        int[] intarr = new int[strarr.length];

        for (int i = 0; i < strarr.length; i++) {
            intarr[i] = Integer.parseInt(strarr[i]);
        }

        return intarr;
    }
}
