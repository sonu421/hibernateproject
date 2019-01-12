package com.jspiders.dbApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementDemo {
	
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String sql = "Insert into student values (?, ? , ?, ?)";
		String updateQuery = "";
		
		try {
			Class.forName(DBConstants.DRIVER);
			con = DriverManager.
					getConnection(DBConstants.URL, DBConstants.user, DBConstants.password);
			pstmt = con.prepareStatement(sql);
			
			for (int i = 20; i < 25; i++) {
			//OPERATION-1
				pstmt.setInt(1, i);
				pstmt.setString(2, "Test" + i);
				pstmt.setLong(3, 1234567);
				pstmt.setDouble(4, 23.23);
				pstmt.addBatch();
				//pstmt.executeUpdate();
			}
			pstmt.executeBatch();
			//pstmt1 = con.prepareStatement(updateQuery);
			
/*			//OPERATION-2
			pstmt.setInt(1, 11);
			pstmt.setString(2, "Test8");
			pstmt.setLong(3, 456123);
			pstmt.setDouble(4, 32.13);
			pstmt.executeUpdate();
*/		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
