package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefones;
import connection.SingleConnection;

public class DaoTelefones {
	
private Connection connection;
	
	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanTelefones telefone) throws SQLException {
		
		try {
		String sql = "insert into telefone(numero, tipo, usuario) values(?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, telefone.getNumero());
		insert.setString(2, telefone.getTipo());
		insert.setLong(3, telefone.getUsuario());

		insert.execute();
		connection.commit();
		
		} catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}
	}
	
	
	public void atualizar(BeanTelefones telefone) throws SQLException {
		
		try {
		String sql = "update telefone set numero = ?, tipo = ?, usuario = ? where id = " + telefone.getId();
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, telefone.getNumero());
		insert.setString(2, telefone.getTipo());
		insert.setLong(3, telefone.getUsuario());
		insert.execute();
		connection.commit();
		
		} catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}
	}
	
	
	public List<BeanTelefones> listar(Long user) throws SQLException{
		List<BeanTelefones> listar = new ArrayList<BeanTelefones>();
		String sql = "select * from telefone where usuario = " + user;	
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			BeanTelefones telefone = new BeanTelefones();
			telefone.setId(rs.getLong("id"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setTipo(rs.getString("tipo"));
			telefone.setUsuario(rs.getLong("usuario"));
			listar.add(telefone);			
		}
		
		return listar;	
	}
	
	
	public BeanTelefones consultar(String id) throws SQLException {
		String sql = "select * from telefone where id ='" + id +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			BeanTelefones telefone = new BeanTelefones();
			telefone.setId(rs.getLong("id"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setTipo(rs.getString("tipo"));
			telefone.setUsuario(rs.getLong("usuario"));
			
			return telefone;
		}
		return null;
	}
	
	
	public void delete (String id) {
		
		try {
		String sql = "delete from telefone where id = '" + id + "'";
		PreparedStatement st = connection.prepareStatement(sql);
		st.execute();
		
		connection.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public boolean validarNumero(String numero) throws SQLException {
		String sql = "select count(1) as qtd from telefone where numero ='" + numero +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0 ;
		}
		
		return false;
	}
	
	
	public boolean validarNumeroUpdate(String numero, String id) throws SQLException {
		String sql = "select count(1) as qtd from produto where login ='" + numero +"' and id <> " + id;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0 ;
		}
		
		return false;
	}
	

}
