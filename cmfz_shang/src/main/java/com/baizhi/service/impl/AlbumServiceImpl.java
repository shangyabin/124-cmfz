package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by 小尚 on 2018/8/30.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbumDao albumDao;
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Album> getAlbum() {
        List<Album> albums = albumDao.getAlbum();
        return albums;
    }

    @Override
    public Album getByAlbumId(int id) {

        return albumDao.getByAlbumId(id);
    }

    @Override
    public void insertAlbum(Album album) {
        albumDao.insertAlbum(album);
    }

    @Override
    public void deleteAlbum(int id) {
        albumDao.deleteAlbum(id);
    }
}
