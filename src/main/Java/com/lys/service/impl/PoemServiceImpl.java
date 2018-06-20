package com.lys.service.impl;

import com.lys.dao.PoemMapper;
import com.lys.model.Poem;
import com.lys.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    PoemMapper poemMapper;

    @Transactional
    public Poem queryPoem(Integer id) {
        return poemMapper.selectByPrimaryKey(id);
    }
}
