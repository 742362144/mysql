package com.mysql.util;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ConnectionManager {

	// 表示定义数据库的用户名
	private static String USERNAME;

	// 定义数据库的密码
	private static String PASSWORD;

	// 定义数据库的驱动信息
	private static String DRIVER;

	// 定义访问数据库的地址
	private static String URL;

	// 使用单利模式创建数据库连接池
	private static ConnectionManager instance;
	private static ComboPooledDataSource dataSource;

	static {
		// 加载数据库配置信息，并给相关的属性赋值
		loadConfig();
	}

	public static void loadConfig() {
		try {
			InputStream in = ConnectionManager.class
					.getResourceAsStream("/JDBCConfig.properties");
			Properties properties = new Properties();
			properties.load(in);
			USERNAME = properties.getProperty("jdbc.username");
			PASSWORD = properties.getProperty("jdbc.password");
			DRIVER = properties.getProperty("jdbc.driver");
			URL = properties.getProperty("jdbc.url");
		} catch (Exception e) {
			throw new RuntimeException("加载数据库配置文件异常！！！！", e);
		}
	}

	private ConnectionManager() throws SQLException, PropertyVetoException {
		dataSource = new ComboPooledDataSource();

		dataSource.setUser(USERNAME); // 用户名
		dataSource.setPassword(PASSWORD); // 密码
		dataSource.setJdbcUrl(URL);// 数据库地址
		dataSource.setDriverClass(DRIVER);
		dataSource.setInitialPoolSize(300); // 初始化连接数
		dataSource.setMinPoolSize(1);// 最小连接数
		dataSource.setMaxPoolSize(900);// 最大连接数
		dataSource.setMaxStatements(50);// 最长等待时间
		dataSource.setMaxIdleTime(60);// 最大空闲时间，单位毫秒
	}

	public static final ConnectionManager getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public synchronized final Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
