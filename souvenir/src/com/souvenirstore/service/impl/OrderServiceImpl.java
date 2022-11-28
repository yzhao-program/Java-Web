package com.souvenirstore.service.impl;

import com.souvenirstore.bean.*;
import com.souvenirstore.dao.OrderDao;
import com.souvenirstore.dao.OrderItemDao;
import com.souvenirstore.dao.SouvenirDao;
import com.souvenirstore.dao.impl.OrderDaoImpl;
import com.souvenirstore.dao.impl.OrderItemDaoImpl;
import com.souvenirstore.dao.impl.SouvenirDaoImpl;
import com.souvenirstore.service.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private SouvenirDao souvenirDao = new SouvenirDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        // generate an unique order id
        long currentTimeMillis = System.currentTimeMillis();
        String orderId = currentTimeMillis+""+userId;

        // create an order object
        Order order = new Order(orderId, new Date(currentTimeMillis), cart.getTotalPrice(), 0, userId);
        // save order
        orderDao.saveOrder(order);

        // int i = 1 / 0;

        // traverse the souvenirs in the cart and save them as order items
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // get the souvenir in the cart
            CartItem cartItem = entry.getValue();
            // save the souvenir as an order item
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // save order item
            orderItemDao.saveOrderItem(orderItem);

            // update sales
            Souvenir souvenir = souvenirDao.querySouvenirById(cartItem.getId());
            souvenir.setSales( souvenir.getSales() + cartItem.getCount() );
            souvenirDao.updateSouvenir(souvenir);

        }
        // clear the cart
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void updateOrderStatus(Integer status, String orderId) {
        orderDao.updateOrderStatus(status, orderId);
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public Page<Order> queryAllPageOrders(int pageNo, int pageSize) {
        Page<Order> page = new Page<>();
        // set the number of items in each page
        page.setPageSize(pageSize);
        // get the total number of items
        Integer pageTotalCount = orderDao.queryForPageTotalCount();
        // set the total number of items
        page.setPageTotalCount(pageTotalCount);
        // get the total pages
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // set the total pages
        page.setPageTotal(pageTotal);

        // set the current page number
        page.setPageNo(pageNo);

        // get the beginning index of the current page number
        int begin = (page.getPageNo() - 1) * pageSize;
        // get the items of the current page number
        List<Order> items = orderDao.queryForPageOrders(begin, pageSize);
        // set the items of the current page number
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Order> queryMyPageOrders(int pageNo, int pageSize, Integer userId) {
        Page<Order> page = new Page<>();
        // set the number of items in each page
        page.setPageSize(pageSize);
        // get the total number of items
        Integer pageTotalCount = orderDao.queryForPageTotalCountByUserId(userId);
        // set the total number of items
        page.setPageTotalCount(pageTotalCount);
        // get the total pages
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0 || pageTotalCount == 0) {
            pageTotal+=1;
        }
        // set the total pages
        page.setPageTotal(pageTotal);

        // set the current page number
        page.setPageNo(pageNo);

        // get the beginning index of the current page number
        int begin = (page.getPageNo() - 1) * pageSize;
        // get the items of the current page number
        List<Order> items = orderDao.queryForPageOrdersByUserId(begin, pageSize, userId);
        // set the items of the current page number
        page.setItems(items);

        return page;
    }

    @Override
    public List<OrderItem> queryOrderDetails(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }
}
