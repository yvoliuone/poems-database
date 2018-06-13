package com.lys.service;

import com.lys.model.Poem;
import org.springframework.stereotype.Service;

@Service
public interface PoemService {

    Poem queryPoem(Integer id);

}
