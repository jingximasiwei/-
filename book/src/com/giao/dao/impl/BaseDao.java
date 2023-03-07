package com.giao.dao.impl;

import com.giao.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();
    /*
    * update()方法用来执行：Insert\Update\Delete语句
    * 如果返回-1说明失败，返回其他表示影响的行数
    * */
    public int update(String sql,Object ... args){
        Connection connection= JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*查询返回一条javabean的sql语句
    * type 返回的对象类型
    * sql 执行的sql语句
    * args sql对应的参数值
    * <T> 返回的类型的泛型
    * */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection con = JDBCUtils.getConnection();
        try {
            return queryRunner.query(con, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*
    * 查询返回多条javabean的sql语句*/
    public <T> List <T> queryForList(Class<T>type, String sql, Object ... args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*
    * 返回一行一列的sql语句*/
    public Object queryForSingleValue(String sql,Object ... args){
        Connection connection=JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
