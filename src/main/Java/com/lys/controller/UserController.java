package com.lys.controller;

import com.lys.model.Poem;
import com.lys.model.User;
import com.lys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping("/userlogin.do")
    public ModelAndView login(@RequestParam("id") Integer id, RedirectAttributes attr) {

        User user = getUser(id) == null ? newUser(id) : getUser(id);

        return getDailyPoems(id, attr);
    }


    @RequestMapping("/updateCounts.do")
    public void updateCounts(@RequestParam("userid") Integer userid,
                           @RequestParam("poemid") Integer poemid, @RequestParam("tags") String tags) {
        User user = userService.getUser(userid);

        // Tags to be incremented by one count
        int[] poemTags = atoiArr(tags.split(","));

        // Original counts
        int[] tagCounts = atoiArr(user.getCounts().split(","));

        for (int i : poemTags) {
            tagCounts[i]++;
        }

        String newCounts = String.join(",", Arrays.toString(tagCounts)).replaceAll(" ", "");
        newCounts = newCounts.substring(1, newCounts.length() - 1);

        // Add to "liked" list and update the database
        user.setCounts(newCounts);

        String liked = user.getLiked();
        liked = liked == null ? poemid.toString() :
                liked.contains(poemid.toString()) ? liked : liked + "," + poemid;
        user.setLiked(liked);
        userService.updateUser(user);
    }


    // Get the five recommended poems for the user
    private ModelAndView getDailyPoems(Integer id, RedirectAttributes attr) {

        updateTags(id);

        User user = userService.getUser(id);
        String tags = user.getUsertags();
        String author = user.getFavauthor();

        attr.addAttribute("author", author);
        attr.addAttribute("tags", tags);
        String url = "redirect:/poems/getUserPoems.do";

        return new ModelAndView(url);
    }


    // Helper method that converts string array to int array
    private int[] atoiArr(String[] strarr) {
        int[] intarr = new int[strarr.length];

        for (int i = 0; i < strarr.length; i++) {
            intarr[i] = Integer.parseInt(strarr[i]);
        }

        return intarr;
    }


    // Update "userTags" to contain tags with the most counts
    private void updateTags(Integer id) {
        User user = userService.getUser(id);
        int[] counts = atoiArr(user.getCounts().split(","));

        // Find three tags with the most counts
        int tag1 = -1, tag2 = -1, tag3 = -1;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (tag1 == -1 || count > counts[tag1]) {
                tag3 = tag2;
                tag2 = tag1;
                tag1 = i;
            } else if (tag2 == -1 || count > counts[tag2]) {
                tag3 = tag2;
                tag2 = i;
            } else if (tag3 == -1 || count > counts[tag3]) {
                tag3 = i;
            }
        }

        String newTags = tag1 + "," + tag2 + "," + tag3;
        user.setUsertags(newTags);
        userService.updateUser(user);
    }


    private User getUser(@RequestParam("id") Integer id){
        User user = userService.getUser(id);

        return user;
    }

    // Insert a new user to the database
    private User newUser(@RequestParam("id") Integer id) {

        User user = new User();

        user.setUserid(id);
        user.setCounts("0,0,0,0,0,0,0,0,0,0");
        user.setFavauthor("李白");
        user.setUsertags("1,3,4");

        userService.insertUser(user);

        return user;
    }
}
