package com.jspiders.dbApp;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/*import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
*/
public class DemoApp {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		Savepoint sp = null;
		String sql = "Insert into sejm2.student values (5, 'test', 1236456, 12.12)";
		String sql1 = "Insert into sejm2.student values (6, 'test', 1236456, 12.12)";
		String sql2 = "Insert into sejm2.student values (7, 'test', 1236456, 12.12)";
		String sql3 = "Insert into sejm2.student values (7, 'test', 1236456, 12.12)";
		//String sql = "UPDATE student set name = 'Test123' where s_id=1";
		//String sql = "DELETE FROM student where s_id=1";
		//String sql = "DROP table student";
		try {
			Class.forName(DBConstants.DRIVER);
			System.out.println("Driver loaded successfully");
			con = DriverManager.
					getConnection(DBConstants.URL, DBConstants.user, DBConstants.password);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			int noOfRecordsUpdated;
			noOfRecordsUpdated =  stmt.executeUpdate(sql);
			noOfRecordsUpdated =  stmt.executeUpdate(sql1);
			sp = con.setSavepoint("sp");
			noOfRecordsUpdated =  stmt.executeUpdate(sql2);
			noOfRecordsUpdated =  stmt.executeUpdate(sql3);
			con.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				if (sp != null) {
					con.rollback(sp);
					con.commit();
				} else {
					con.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			//Sixth step
			//Closing the costly resources
			try {
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
/*
Connection con  = DriverManager.
						getConnection("jdbc:mysql://localhost:3306", "root", "root");
Connection con  = DriverManager.
						getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
*/