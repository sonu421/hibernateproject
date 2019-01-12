package com.jspiders.dbApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "Select * from student";
		try {
			Class.forName(DBConstants.DRIVER);
			con = DriverManager.getConnection(DBConstants.URL,
					DBConstants.user, DBConstants.password);
			stmt = con.createStatement();
			boolean flag = stmt.execute(sql);
			//if (flag) {
				rs = stmt.getResultSet();
			//}
			//rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("Id: " + rs.getInt("s_id"));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("PhoneNumber: " + rs.getLong(3));
				System.out.println("Age: " + rs.getDouble("age"));
				System.out.println("--------------------------------------");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
