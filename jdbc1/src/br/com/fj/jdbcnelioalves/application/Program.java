package br.com.fj.jdbcnelioalves.application;

import java.sql.Connection;

import br.com.fj.jdbcnelioalves.db.DataBase;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = DataBase.getConnection();
		DataBase.closeConnection();
	}

}
