package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/30.
 */
public interface AlbumService {
    //查询所有
    public List<Album> getAlbum();
    //根据id查询
    public Album getByAlbumId(int id);
    //添加
    public void insertAlbum(Album album);
    //删除
    public void deleteAlbum(int id);
}
