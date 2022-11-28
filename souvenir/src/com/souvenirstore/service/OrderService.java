package com.souvenirstore.service;

import com.souvenirstore.bean.Cart;
import com.souvenirstore.bean.Order;
import com.souvenirstore.bean.OrderItem;
import com.souvenirstore.bean.Page;

import java.util.List;

public interface OrderService {

    String createOrder(Cart cart, Integer userId);

    List<Order> queryAllOrders();

    void updateOrderStatus(Integer status, String orderId);

    List<Order> queryMyOrders(Integer userId);

    Page<Order> queryAllPageOrders(int pageNo, int pageSize);

    Page<Order> queryMyPageOrders(int pageNo, int pageSize, Integer userId);

    List<OrderItem> queryOrderDetails(String orderId);
}
