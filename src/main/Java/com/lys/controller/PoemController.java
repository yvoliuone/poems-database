package com.lys.controller;

import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.annotation.RequestMap;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/poems")
public class PoemController {

    @Autowired
    private PoemService poemService;

    @RequestMapping("/getPoem.do")
    public Poem getPoem(@RequestParam("id") Integer id){
        System.out.println("Poem ID received: " + id);

        Poem poem = poemService.getPoem(id);

        return poem;
    }

    @RequestMapping("/getPoemsByTags.do")
    public List<Poem> getPoemsByTags(@RequestParam("tag1") String tag1, @RequestParam("tag2") String tag2) {

        System.out.println("Tags received: " + tag1 + ", " + tag2);

        List<Poem> poems = poemService.getPoemsByTags(tag1, tag2);

        System.out.println(Arrays.toString(poems.toArray()));

        return poems;
    }

}
