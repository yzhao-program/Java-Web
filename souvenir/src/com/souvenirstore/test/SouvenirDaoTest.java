package com.souvenirstore.test;

import com.souvenirstore.dao.SouvenirDao;
import com.souvenirstore.dao.impl.SouvenirDaoImpl;
import com.souvenirstore.bean.Page;
import com.souvenirstore.bean.Souvenir;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class SouvenirDaoTest {

    private SouvenirDao souvenirDao = new SouvenirDaoImpl();

    @Test
    public void addSouvenir() {
        souvenirDao.addSouvenir(new Souvenir(null,"123456", new BigDecimal(9999),9999,null));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void updateSouvenir() {
        souvenirDao.updateSouvenir(new Souvenir(27,"654321", new BigDecimal(10000),10000,null));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void deleteSouvenirById() {
        souvenirDao.deleteSouvenirById(27);
        JdbcUtils.commitAndClose();
    }

    @Test
    public void querySouvenirById() {
        System.out.println( souvenirDao.querySouvenirById(27) );
    }

    @Test
    public void querySouvenirs() {
        souvenirDao.querySouvenirs().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println( souvenirDao.queryForPageTotalCount() );
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println( souvenirDao.queryForPageTotalCountByPrice(10, 50) );
    }

    @Test
    public void queryForPageSouvenirs() {
        souvenirDao.queryForPageSouvenirs(8, Page.PAGE_SIZE).forEach(System.out::println);
    }

    @Test
    public void queryForPageSouvenirsByPrice() {
        souvenirDao.queryForPageSouvenirsByPrice(0, Page.PAGE_SIZE,10,50).forEach(System.out::println);
    }
}
