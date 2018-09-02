package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/30.
 */
public interface AlbumDao {
    //查询所有
    public List<Album> getAlbum();
    //根据id查询
    public Album getByAlbumId(@Param("id") int id);
    //添加
    public void insertAlbum(Album album);
    //删除
    public void deleteAlbum(int id);
}
