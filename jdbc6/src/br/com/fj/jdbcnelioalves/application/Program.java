package br.com.fj.jdbcnelioalves.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fj.jdbcnelioalves.db.DataBase;
import br.com.fj.jdbcnelioalves.db.DbException;
import br.com.fj.jdbcnelioalves.db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null;
		Statement st  = null;
		
		try {
			conn = DataBase.getConnection();
			
			conn.setAutoCommit(false);
			
			
			st = conn.createStatement();
			
			
			int rows1 = st.executeUpdate(
							"UPDATE seller "
							+ "SET BaseSalary = 2090 "
							+ "WHERE "
							+ "DepartmentId = 1");
			
			int x = 1;
			
			
			/*
			 * if (x < 2) { throw new SQLException("Erro falso"); }
			 */
			 
			
			int rows2 = st.executeUpdate(
					"UPDATE seller "
					+ "SET BaseSalary = 3090 "
					+ "WHERE "
					+ "DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("Feito! Linhas afetadas no primeiro comando: " + rows1);
			System.out.println("Feito! Linhas afetadas no segundo comando: " + rows2);
			System.out.println("Feito! Linhas afetadas: " + (rows1 + rows2));
			
		} 
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação não foi concluída! Causa:" + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DbException("Erro ao tentar voltar a transação! Causa: " + e1.getMessage());
			}
		}
		finally {
			DataBase.closeStatement(st);
			DataBase.closeConnection();
		}
	}
}
