package com.example.nowcoder;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class NowCoderApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        System.out.println("HikariDataSource 数据源最大连接数：" + hikariDataSource.getMaximumPoolSize());
        System.out.println("HikariDataSource 数据源初始化连接数：" + hikariDataSource.getMinimumIdle());

        //关闭连接
        connection.close();

    }

}
