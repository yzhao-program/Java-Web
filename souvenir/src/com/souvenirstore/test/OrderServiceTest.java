package com.souvenirstore.test;

import com.souvenirstore.bean.Cart;
import com.souvenirstore.bean.CartItem;
import com.souvenirstore.bean.Page;
import com.souvenirstore.service.OrderService;
import com.souvenirstore.service.impl.OrderServiceImpl;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "Souvenir T", 1, new BigDecimal(30),new BigDecimal(30)));
        cart.addItem(new CartItem(1, "Souvenir T", 1, new BigDecimal(30),new BigDecimal(30)));
        cart.addItem(new CartItem(2, "Souvenir Y", 1, new BigDecimal(100),new BigDecimal(100)));

        System.out.println( "Order number is: " + orderService.createOrder(cart, 1) );
        JdbcUtils.commitAndClose();
    }

    @Test
    public void queryAllOrders() {
        orderService.queryAllOrders().forEach(System.out::println);
    }

    @Test
    public void updateOrderStatus() {
        orderService.updateOrderStatus(0, "1234567891");
        JdbcUtils.commitAndClose();
    }

    @Test
    public void queryMyOrders() {
        orderService.queryMyOrders(1).forEach(System.out::println);
    }

    @Test
    public void queryAllPageOrders() {
        System.out.println(orderService.queryAllPageOrders(1, Page.PAGE_SIZE));
    }

    @Test
    public void queryMyPageOrders() {
        System.out.println(orderService.queryMyPageOrders(2, Page.PAGE_SIZE, 1));
    }

    @Test
    public void queryOrderDetails() {
        orderService.queryOrderDetails("1234567891").forEach(System.out::println);
    }
}
