package br.com.fj.jdbcnelioalves.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fj.jdbcnelioalves.db.DataBase;
import br.com.fj.jdbcnelioalves.db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null;
		PreparedStatement st  = null;
		
		try {
			conn = DataBase.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			
			st.setInt(1, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Feito! Linhas afetadas: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DataBase.closeStatement(st);
			DataBase.closeConnection();
		}
	}
}
