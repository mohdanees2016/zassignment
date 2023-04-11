package com.zurich.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zurich.base.TestBase;

public class JDBCDatabaseUtilities extends TestBase{
	
	Connection conn;
	Statement st;

	public void dbConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zurichdb", "root", "admin");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String[][] testData(String query){
		String [][] result=null;
		try {
			dbConnection();
			ResultSet rs = st.executeQuery(query);
			int clmCount = rs.getMetaData().getColumnCount();
			rs.last();
			int rowcnt = rs.getRow();
			rs.beforeFirst();
			result = new String[rowcnt][clmCount];
			 int i=0;
			 while(rs.next()) {
				 for(int j=0;j<clmCount;j++) {
					 result[i][j]=rs.getString(j+1);
				 }
				 i++;
			 }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return result;
	}
	
	public void updateData(String tableName, String columnName, String value) {
		
		dbConnection();
		try {
			st.executeUpdate("update "+ tableName + " set "+ columnName + " = '" + value + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
//	public void main() {
//		updateData("adduser", "EmployeeName", "MTest  ATest");
//		
//	}
	
//	public static void main(String[] args) {
//		new JDBCDatabaseUtilities().main();
//	}

}
