package com.souvenirstore.service.impl;

import com.souvenirstore.dao.SouvenirDao;
import com.souvenirstore.dao.impl.SouvenirDaoImpl;
import com.souvenirstore.bean.Page;
import com.souvenirstore.bean.Souvenir;
import com.souvenirstore.service.SouvenirService;

import java.util.List;

public class SouvenirServiceImpl implements SouvenirService {

    private SouvenirDao souvenirDao = new SouvenirDaoImpl();

    @Override
    public void addSouvenir(Souvenir souvenir) {
        souvenirDao.addSouvenir(souvenir);
    }

    @Override
    public void updateSouvenir(Souvenir souvenir) {
        souvenirDao.updateSouvenir(souvenir);
    }

    @Override
    public void deleteSouvenirById(Integer id) {
        souvenirDao.deleteSouvenirById(id);
    }

    @Override
    public Souvenir querySouvenirById(Integer id) {
        return souvenirDao.querySouvenirById(id);
    }

    @Override
    public List<Souvenir> querySouvenirs() {
        return souvenirDao.querySouvenirs();
    }

    @Override
    public Page<Souvenir> queryPageSouvenirs(int pageNo, int pageSize) {
        Page<Souvenir> page = new Page<>();
        // set the number of items in each page
        page.setPageSize(pageSize);
        // get the total number of items
        Integer pageTotalCount = souvenirDao.queryForPageTotalCount();
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
        List<Souvenir> items = souvenirDao.queryForPageSouvenirs(begin, pageSize);
        // set the items of the current page number
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Souvenir> queryPageSouvenirsByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Souvenir> page = new Page<>();

        // set the number of items in each page
        page.setPageSize(pageSize);
        // get the total number of items
        Integer pageTotalCount = souvenirDao.queryForPageTotalCountByPrice(min, max);
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
        List<Souvenir> items = souvenirDao.queryForPageSouvenirsByPrice(begin, pageSize, min, max);
        // set the items of the current page number
        page.setItems(items);

        return page;
    }
}
