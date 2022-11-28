package com.souvenirstore.test;

import com.souvenirstore.dao.OrderDao;
import com.souvenirstore.dao.impl.OrderDaoImpl;
import com.souvenirstore.bean.Order;
import com.souvenirstore.bean.Page;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderDaoTest {

    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567891", new Date(4234324141L), new BigDecimal(430), 0, 1));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void queryOrders() {
        orderDao.queryOrders().forEach(System.out::println);
    }

    @Test
    public void updateOrderStatus() {
        orderDao.updateOrderStatus(1, "1234567891");
        JdbcUtils.commitAndClose();
    }

    @Test
    public void queryOrdersByUserId() {
        orderDao.queryOrdersByUserId(1).forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(orderDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageOrders() {
        orderDao.queryForPageOrders(1, Page.PAGE_SIZE).forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByUserId() {
        System.out.println(orderDao.queryForPageTotalCountByUserId(1));
    }

    @Test
    public void queryForPageOrdersByUserId() {
        orderDao.queryForPageOrdersByUserId(0, Page.PAGE_SIZE, 1).forEach(System.out::println);
    }
}
