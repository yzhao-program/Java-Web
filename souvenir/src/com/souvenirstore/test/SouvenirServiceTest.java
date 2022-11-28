package com.souvenirstore.test;

import com.souvenirstore.bean.Page;
import com.souvenirstore.bean.Souvenir;
import com.souvenirstore.service.SouvenirService;
import com.souvenirstore.service.impl.SouvenirServiceImpl;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class SouvenirServiceTest {

    private SouvenirService souvenirService = new SouvenirServiceImpl();

    @Test
    public void addSouvenir() {
        souvenirService.addSouvenir(new Souvenir(null,"112233", new BigDecimal(99999), 99999, null));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void updateSouvenir() {
        souvenirService.updateSouvenir(new Souvenir(28,"332211", new BigDecimal(100000), 100000, null));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void deleteSouvenirById() {
        souvenirService.deleteSouvenirById(28);
        JdbcUtils.commitAndClose();
    }

    @Test
    public void querySouvenirById() {
        System.out.println(souvenirService.querySouvenirById(28));
    }

    @Test
    public void querySouvenirs() {
        souvenirService.querySouvenirs().forEach(System.out::println);
    }

    @Test
    public void queryPageSouvenirs(){
        System.out.println(souvenirService.queryPageSouvenirs(1, Page.PAGE_SIZE ));
    }

    @Test
    public void queryPageSouvenirsByPrice(){
        System.out.println(souvenirService.queryPageSouvenirsByPrice(1, Page.PAGE_SIZE,10,50 ));
    }
}
