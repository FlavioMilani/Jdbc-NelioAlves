package br.com.fj.jdbcnelioalves.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fj.jdbcnelioalves.db.DataBase;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DataBase.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from department");
			
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeResultSet(rs);
			DataBase.closeStatement(st);
			DataBase.closeConnection();
		}
	}

}
