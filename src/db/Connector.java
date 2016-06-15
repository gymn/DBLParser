package db;

import java.sql.*;

public class Connector {
	// Change the parameters accordingly.
	private static String dbUrl = "jdbc:mysql://192.168.1.125:3306/dblp?useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "123";

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			System.out.println("Error while opening a conneciton to database server: "
								+ e.getMessage());
			return null;
		}
	}
}
