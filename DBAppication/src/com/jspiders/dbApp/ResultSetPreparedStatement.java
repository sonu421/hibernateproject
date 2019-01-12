package com.jspiders.dbApp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class ResultSetPreparedStatement {

	public static void main(String[] args) {
		System.out.println("Enter id to be fetched: ");
		Scanner scan = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseMetaData dbMetaData = null;
		ResultSetMetaData rsMetaData = null;
		String sql = "Select * from student where s_id = ?";
		try {
			scan = new Scanner(System.in);
			int id = scan.nextInt();
			Class.forName(DBConstants.DRIVER);
			con = DriverManager.getConnection(DBConstants.URL,
					DBConstants.user, DBConstants.password);
			dbMetaData = con.getMetaData();
			System.out.println("DriverName: " + dbMetaData.getDriverName());
			System.out.println("DB name: " + dbMetaData.getDatabaseProductName());
			pstmt = con.prepareStatement(sql);
			//boolean flag = pstmt.execute(sql);
			// if (flag) {
			//rs = pstmt.getResultSet();
			// }
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rsMetaData = rs.getMetaData();
			System.out.println("Column count: " + rsMetaData.getColumnCount());
			System.out.println("Column Name: " + rsMetaData.getColumnName(1));
			/*if (rs.next()) {
				System.out.println("Id: " + rs.getInt("s_id"));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("PhoneNumber: " + rs.getLong(3));
				System.out.println("Age: " + rs.getDouble("age"));
				System.out.println("--------------------------------------");
			}*/
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				if (scan != null) {
					scan.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
