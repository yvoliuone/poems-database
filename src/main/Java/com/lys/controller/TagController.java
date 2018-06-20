package com.lys.controller;

import com.lys.model.Tag;
import com.lys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping("/getTag.do")
    public Tag getTag(@RequestParam("id") Integer id){
        System.out.println("Poem ID received: " + id);

        Tag tag = tagService.getTag(id);

        return tag;
    }

}
