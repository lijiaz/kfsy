package com.baizhi.dao;
import com.baizhi.entity.OrderItem;
import java.util.List;
/**
 *订单项dao层接口
 * Created by MaXn on 2017/6/16.
 */
public interface OrderItemDao extends BaseDao<OrderItem>{

    //根据订单id查询所有订单项
    List<OrderItem> selectByOrderId(String id);
}
