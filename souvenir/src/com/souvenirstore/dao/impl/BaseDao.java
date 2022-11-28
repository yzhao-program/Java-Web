package com.souvenirstore.dao.impl;

import com.souvenirstore.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {

    //Use DbUtils to handle database
    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public BaseDao(){
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.clazz = (Class<T>) actualTypeArguments[0];
    }

    /**
     * update() method is used for：Insert, Update, Delete
     * @param sql SQL Statement
     * @param args The arguments in the SQL Statement
     * @return if return -1: update fails. else: return the number of rows updated
     */
    public int update(String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve and return one javaBean
     * @param sql SQL Statement
     * @param args The arguments in the SQL Statement
     * @return one JavaBean or null if doesn't retrieve the result
     */
    public T queryForOne(String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve and return multiple javaBean(s)
     * @param sql SQL Statement
     * @param args The arguments in the SQL Statement
     * @return multiple javaBean(s) or null if doesn't retrieve the result
     */
    public List<T> queryForList(String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Search and return single value
     * @param sql SQL Statement
     * @param args The arguments in the SQL Statement
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args){

        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
