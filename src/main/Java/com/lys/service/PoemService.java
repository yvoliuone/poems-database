package com.lys.service;

import com.lys.model.Poem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PoemService {

    Poem getPoem(Integer id);

    List<Poem> getPoemsByTags(String tag1, String tag2);

    int insertPoem(Poem poem);
}
