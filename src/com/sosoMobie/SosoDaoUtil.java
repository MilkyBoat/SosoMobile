package com.sosoMobie;/*
 * <p>项目名称: SosoMobie </p>
 * <p>文件名称: SosoDaoUtil </p>
 * <p>描述: [类型描述] </p>
 * <p>创建时间:  </p>
 * @author xuyunkai@mail.nankai.edu.cn 徐云凯
 * @version v1.0
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SosoDaoUtil {
	
	private static String JDBC_DRIVER = null;
	private static String DB_URL = null;
	private static String USER = null;
	private static String PASS = null;
	private static String CONFIG_FILE = "database.properties";
	
	protected static void init() {
		Properties params = new Properties();
		InputStream is = SosoDaoUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		try {
			params.load(is);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException ne) {
			System.out.println("数据库配置文件无法访问！");
			return;
		}
		JDBC_DRIVER = params.getProperty("driver");
		DB_URL = params.getProperty("url");
		USER = params.getProperty("username");
		PASS = params.getProperty("password");
	}
	
	/***
	 * Title: getConnection
	 * Description: [连接到SQL数据库]
	 * @author 徐云凯
	 * @return Connection
	 * Datetime:  2019/11/13 22:33
	 */
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(JDBC_DRIVER);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/***
	 * Title: closeConnection
	 * Description: [关闭连接]
	 * @author 徐云凯
	 * @param connection: 数据库连接类
	 * @param stmt: PreparedStatement预编译数据库会话
	 * @param rs: ResultSet结果集合，不存在时使用null
	 * Datetime:  2019/11/13 22:34
	 */
	
	public void closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs) {
		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
