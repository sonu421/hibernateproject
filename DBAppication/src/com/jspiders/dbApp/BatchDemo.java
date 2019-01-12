package com.jspiders.dbApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchDemo {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		String sql = "Insert into sejm2.student values (9, 'test', 1236456, 12.12)";
		String updateQuery = "UPDATE student set name = 'TestValue' where s_id > 10";
		//https://ombn.svn.cloudforge.com/jspiders/Pavan/BTM/
		try {
			Class.forName(DBConstants.DRIVER);
			con = DriverManager.getConnection(DBConstants.URL,
					DBConstants.user, DBConstants.password);
			stmt = con.createStatement();

			stmt.addBatch(sql);
			System.out.println("Insert added");
			stmt.addBatch(updateQuery);
			System.out.println("Update added");

			int[] recourdUpdated = stmt.executeBatch();
			System.out.println("Batch Excecuted");

			for (int i = 0; i < recourdUpdated.length; i++) {
				System.out.println("Rercords updated: " + recourdUpdated[i]);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
