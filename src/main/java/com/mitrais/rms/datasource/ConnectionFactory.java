package com.mitrais.rms.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final DataSource dataSource;

    ConnectionFactory(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("rmsdb");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("root");
        this.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper{
        private static final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

}
