package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 小尚 on 2018/8/31.
 */
@Service
@Transactional  //控制事务注解
public class ChapterServiceImpl implements ChapterService{
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public void addChapter(Chapter chapter) {
        chapterDao.insert(chapter);
    }
}
