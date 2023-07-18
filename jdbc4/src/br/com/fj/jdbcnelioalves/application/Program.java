package br.com.fj.jdbcnelioalves.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fj.jdbcnelioalves.db.DataBase;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null;
		PreparedStatement st  = null;
		
		try {
			conn = DataBase.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)");
			
			st.setDouble(1, 500.0);
			st.setInt(2, 4);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Feito! Linhas afetadas: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
			DataBase.closeConnection();
		}
	}
}
