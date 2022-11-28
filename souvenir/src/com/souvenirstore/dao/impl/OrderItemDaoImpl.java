package com.souvenirstore.dao.impl;

import com.souvenirstore.dao.OrderItemDao;
import com.souvenirstore.bean.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {

        String sql = "insert into order_item_table(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {

        String sql = "select `name`, `count`, `price`, `total_price` totalPrice from order_item_table where `order_id`=?";
        return queryForList(sql, orderId);
    }
}
