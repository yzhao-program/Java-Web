package com.souvenirstore.dao.impl;

import com.souvenirstore.dao.OrderDao;
import com.souvenirstore.bean.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    @Override
    public int saveOrder(Order order) {

        String sql = "insert into order_table(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {

        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from order_table";
        return queryForList(sql);
    }

    @Override
    public int updateOrderStatus(Integer status, String orderId) {

        String sql = "update order_table set `status`=? where `order_id`=?";
        return update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {

        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from order_table where `user_id`=?";
        return queryForList(sql, userId);
    }

    @Override
    public Integer queryForPageTotalCount() {

        String sql = "select count(*) from order_table";
        Number count = (Number)queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageOrders(int begin, int pageSize) {

        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from order_table limit ?, ?";
        return queryForList(sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByUserId(Integer userId) {

        String sql = "select count(*) from order_table where `user_id`=?";
        Number count = (Number)queryForSingleValue(sql, userId);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageOrdersByUserId(int begin, int pageSize, Integer userId) {

        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from order_table where `user_id`=? limit ?, ?";
        return queryForList(sql, userId, begin, pageSize);
    }
}
