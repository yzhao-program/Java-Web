package com.souvenirstore.test;

import com.souvenirstore.dao.OrderItemDao;
import com.souvenirstore.dao.impl.OrderItemDaoImpl;
import com.souvenirstore.bean.OrderItem;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {

        orderItemDao.saveOrderItem(new OrderItem(null,"Souvenir Q", 1,new BigDecimal(50),new BigDecimal(50),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Souvenir W", 2,new BigDecimal(10),new BigDecimal(20),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Souvenir E", 1,new BigDecimal(60),new BigDecimal(60),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Souvenir R", 3,new BigDecimal(100),new BigDecimal(300),"1234567891"));

        JdbcUtils.commitAndClose();
    }

    @Test
    public void queryOrderItemsByOrderId() {
        orderItemDao.queryOrderItemsByOrderId("1234567891").forEach(System.out::println);
    }
}
