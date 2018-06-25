package com.lys.service.impl;

import com.lys.dao.TagMapper;
import com.lys.model.Tag;
import com.lys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Transactional
    public Tag getTag(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

}
