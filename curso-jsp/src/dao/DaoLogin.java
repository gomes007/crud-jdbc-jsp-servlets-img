package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {
	
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception {
		
		String sql = "select * from usuario where login = '"+login+"' and senha = '"+senha +"'";
		
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet resultado = st.executeQuery();
		
		if (resultado.next()) {
			return true; //possui usuario
		} else {
			return false; //nao valida usuario
		}
		
	}
	
	

}
