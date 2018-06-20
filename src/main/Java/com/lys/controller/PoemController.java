package com.lys.controller;

import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poem")
public class PoemController {

    @Autowired
    private PoemService poemService;

    @RequestMapping("/queryPoem.do")
    public Poem queryPoem(@RequestParam("id") Integer id){
        System.out.println("Poem ID received: " + id);

        Poem poem = poemService.queryPoem(id);

        return poem;
    }

}
