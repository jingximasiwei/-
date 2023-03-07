package com.giao.test;
import com.giao.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class JDBCUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i=0;i<100;i++) {
            Connection connection = JDBCUtils.getConnection();
            System.out.println(connection);
//            JDBCUtils.close(connection);
        }

    }
}
