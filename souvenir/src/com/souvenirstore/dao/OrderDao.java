package com.souvenirstore.dao;

import com.souvenirstore.bean.Order;

import java.util.List;

public interface OrderDao {

    int saveOrder(Order order);

    List<Order> queryOrders();

    int updateOrderStatus(Integer status, String orderId);

    List<Order> queryOrdersByUserId(Integer userId);

    Integer queryForPageTotalCount();

    List<Order> queryForPageOrders(int begin, int pageSize);

    Integer queryForPageTotalCountByUserId(Integer userId);

    List<Order> queryForPageOrdersByUserId(int begin, int pageSize, Integer userId);
}
