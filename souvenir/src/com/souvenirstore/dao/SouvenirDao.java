package com.souvenirstore.dao;

import com.souvenirstore.bean.Souvenir;

import java.util.List;

public interface SouvenirDao {

    int addSouvenir(Souvenir souvenir);

    int updateSouvenir(Souvenir souvenir);

    int deleteSouvenirById(Integer id);

    Souvenir querySouvenirById(Integer id);

    List<Souvenir> querySouvenirs();

    Integer queryForPageTotalCount();

    List<Souvenir> queryForPageSouvenirs(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Souvenir> queryForPageSouvenirsByPrice(int begin, int pageSize, int min, int max);
}
