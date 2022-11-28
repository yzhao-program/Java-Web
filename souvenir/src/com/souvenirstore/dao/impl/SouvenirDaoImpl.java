package com.souvenirstore.dao.impl;

import com.souvenirstore.dao.SouvenirDao;
import com.souvenirstore.bean.Souvenir;

import java.util.List;

public class SouvenirDaoImpl extends BaseDao<Souvenir> implements SouvenirDao {

    @Override
    public int addSouvenir(Souvenir souvenir) {

        String sql = "insert into souvenir_table(`name`, `price`, `sales`, `img_path`) values(?,?,?,?)";

        return update(sql, souvenir.getName(), souvenir.getPrice(), souvenir.getSales(), souvenir.getImgPath());
    }

    @Override
    public int updateSouvenir(Souvenir souvenir) {

        String sql = "update souvenir_table set `name`=?, `price`=?, `sales`=?, `img_path`=? where id = ?";

        return update(sql,souvenir.getName(),souvenir.getPrice(),souvenir.getSales(),souvenir.getImgPath(),souvenir.getId());
    }

    @Override
    public int deleteSouvenirById(Integer id) {
        String sql = "delete from souvenir_table where id = ?";
        return update(sql, id);
    }

    @Override
    public Souvenir querySouvenirById(Integer id) {
        String sql = "select `id`, `name`, `price`, `sales`, `img_path` imgPath from souvenir_table where id = ?";
        return queryForOne(sql, id);
    }

    @Override
    public List<Souvenir> querySouvenirs() {
        String sql = "select `id`, `name`, `price`, `sales`, `img_path` imgPath from souvenir_table";
        return queryForList(sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from souvenir_table";
        Number count = (Number)queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Souvenir> queryForPageSouvenirs(int begin, int pageSize) {
        String sql = "select `id`, `name`, `price`, `sales`, `img_path` imgPath from souvenir_table limit ?,?";
        return queryForList(sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from souvenir_table where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Souvenir> queryForPageSouvenirsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`price`,`sales`,`img_path` imgPath from souvenir_table where price between ? and ? order by price limit ?,?";
        return queryForList(sql,min,max,begin,pageSize);
    }
}
