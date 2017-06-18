package com.baizhi.dao;
import com.baizhi.entity.Image;
/**
 * 与药品图片相关的Dao层接口
 * Created by MaXn on 2017/6/14.
 */
public interface ImageDao extends BaseDao<Image>{

    //根据药品id删除该药品的所有图片
    void deleteByMedicineId(String id);

}
