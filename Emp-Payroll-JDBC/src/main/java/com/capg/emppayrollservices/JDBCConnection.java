package com.capg.emppayrollservices;

import java.sql.*;

public class JDBCConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
	private static final String PASSWORD = "";
	private static final String USER = "root";

	public static Connection con = null;

	public static Connection getconnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
