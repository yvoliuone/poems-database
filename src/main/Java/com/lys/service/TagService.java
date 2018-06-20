package com.lys.service;

import com.lys.model.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagService {

    Tag getTag(Integer id);

}
