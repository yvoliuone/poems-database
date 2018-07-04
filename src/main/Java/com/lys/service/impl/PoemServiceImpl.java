package com.lys.service.impl;

import com.lys.dao.PoemMapper;
import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    PoemMapper poemMapper;

    @Transactional
    public Poem getPoem(Integer id) {
        return poemMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public List<Poem> getPoemsByTags(String tag1, String tag2, Integer count) {
        return poemMapper.selectByTags(tag1, tag2, count);
    }

    @Transactional
    public Poem getPoemByFavAuthor(String author) {
        return poemMapper.favAuthor(author);
    }

    @Transactional
    public Poem random() {
        return poemMapper.random();
    }

    @Transactional
    public void insertPoem(Poem poem) {
        poemMapper.insert(poem);
    }

    @Transactional
    public void updatePoem(Poem poem) {
        poemMapper.updateByPrimaryKey(poem);
    }
}
