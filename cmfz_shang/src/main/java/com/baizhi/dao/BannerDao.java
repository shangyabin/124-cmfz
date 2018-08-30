package com.baizhi.dao;





import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */
public interface BannerDao {
    //查所有
    public List<Banner> queryAll();
    //添加
    public void insertBanner(Banner banner);
    //修改
    public void updateBanner(@Param("status") String status,@Param("id") int id);
    //删除
    public void deleteBanner(int id);

}
