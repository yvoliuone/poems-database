package com.lys.controller;

import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    public ModelAndView like(@RequestParam("userid") String userid, @RequestParam("poemid") Integer poemid) {

        String tags = poemService.getPoem(poemid).getTags();

        String url = "redirect:/user/updateCounts.do?userid=" + userid + "&poemid=" + poemid
                + "&count=3" + "&tags=" + tags;

        return new ModelAndView(url);
    }

    @RequestMapping("/click.do")
    public ModelAndView click(@RequestParam("userid") String userid, @RequestParam("poemid") Integer poemid) {

        String tags = poemService.getPoem(poemid).getTags();

        String url = "redirect:/user/updateCounts.do?userid=" + userid + "&poemid=" + poemid
                + "&count=1" + "&tags=" + tags;

        return new ModelAndView(url);
    }

    @RequestMapping("/unlike.do")
    public ModelAndView unlike(@RequestParam("userid") String userid, @RequestParam("poemid") Integer poemid) {

        String tags = poemService.getPoem(poemid).getTags();

        String url = "redirect:/user/updateCounts.do?userid=" + userid + "&poemid=" + poemid
                + "&count=-3" + "&tags=" + tags;

        return new ModelAndView(url);
    }

    @RequestMapping("/getCollection.do")
    public List<Poem> getCollection(@RequestParam("ids") String ids) {
        String[] idArray = ids.split(",");
        List<Poem> poems = new ArrayList<>();

        for (String idString : idArray) {
            int id = Integer.parseInt(idString);
            poems.add(getPoem(id));
        }

        return poems;
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
    private List<Poem> getPoemsByTags(String tags) {

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
