package com.souvenirstore.service;

import com.souvenirstore.bean.Page;
import com.souvenirstore.bean.Souvenir;

import java.util.List;

public interface SouvenirService {

    void addSouvenir(Souvenir souvenir);

    void updateSouvenir(Souvenir souvenir);

    void deleteSouvenirById(Integer id);

    Souvenir querySouvenirById(Integer id);

    List<Souvenir> querySouvenirs();

    Page<Souvenir> queryPageSouvenirs(int pageNo, int pageSize);

    Page<Souvenir> queryPageSouvenirsByPrice(int pageNo, int pageSize, int min, int max);
}
