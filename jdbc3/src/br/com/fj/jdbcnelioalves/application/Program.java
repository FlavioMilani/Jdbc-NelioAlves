package br.com.fj.jdbcnelioalves.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.fj.jdbcnelioalves.db.DataBase;

public class Program {

	public static void main(String[] args) {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DataBase.getConnection();
			/*
			 * st = conn.prepareStatement( "INSERT INTO seller " +
			 * "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES " +
			 * "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); //Retorna o id criado.
			 * 
			 * st.setString(1, "Flavio Milani"); st.setString(2, "flavio@dominio.com");
			 * st.setDate(3, new java.sql.Date(sdf.parse("13/11/2000").getTime()));
			 * st.setDouble(4, 1500.0); st.setInt(5, 4);
			 */
			
			st = conn.prepareStatement(
								"INSERT INTO department" 
								+ "(Name)"
								+ "VALUES"
								+ "('D1'), ('D2')",
								Statement.RETURN_GENERATED_KEYS
									);
		
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while (rs.next() ) {
					int id = rs.getInt(1);
					System.out.println("Feito! Id = " + id);
				}
			} else {
				System.out.println("Nenhuma linha afetada!");
			}
			
		} catch (SQLException  e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
			DataBase.closeConnection();
		}
	}
}
