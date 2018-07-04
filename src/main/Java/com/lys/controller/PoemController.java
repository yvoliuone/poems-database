package com.lys.controller;

import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/poems")
public class PoemController {

    @Autowired
    private PoemService poemService;

    @RequestMapping("/getPoem.do")
    public Poem getPoem(@RequestParam("id") Integer id){

        return poemService.getPoem(id);
    }

    @RequestMapping("/like.do")
    public ModelAndView getDailyPoems(@RequestParam("userid") Integer userid, @RequestParam("poemid") Integer poemid) {

        String tags = poemService.getPoem(poemid).getTags();

        String url = "redirect:/user/updateCounts.do?userid=" + userid + "&poemid=" + poemid + "&tags=" + tags;

        return new ModelAndView(url);
    }

    @RequestMapping("/getUserPoems.do")
    public List<Poem> getUserPoems(@RequestParam("author") String author,
                                   @RequestParam("tags") String tags) {

        List<Poem> poems = getPoemsByTags(tags);
        poems.add(getPoemByFavAuthor(author));
        poems.add(random());

        return poems;
    }


    // Get three poems by user tags
    private List<Poem> getPoemsByTags(@RequestParam("tags") String tags) {

        // Random shuffle array
        String[] tag = tags.split(",");
        shuffle(tag);

        // Get at most two poems from each set of two tags
        List<Poem> poems = poemService.getPoemsByTags(tag[0], tag[1], 2);
        int count = 3 - poems.size();
        poems.addAll(poemService.getPoemsByTags(tag[1], tag[2], count));
        if (poems.size() < 3) {
            count = 3 - poems.size();
            poems.addAll(poemService.getPoemsByTags(tag[0], tag[2], count));
        }

        return poems;
    }


    // Find one poem by user's favorite author
    private Poem getPoemByFavAuthor(String author) {

        return poemService.getPoemByFavAuthor(author);
    }


    // Include a random poem
    private Poem random() {

        return poemService.random();
    }


    // Shuffles a string array, implementing Fisher–Yates shuffle
    private void shuffle(String[] tags) {
        Random rnd = new Random();
        for (int i = tags.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String s = tags[index];
            tags[index] = tags[i];
            tags[i] = s;
        }
    }
}
