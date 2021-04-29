package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {
	
private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanProduto produto) throws SQLException {
		
		try {
		String sql = "insert into produto(nome, quantidade, valor, categoria_id) values(?, ?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, produto.getNome());
		insert.setDouble(2, produto.getQuantidade());
		insert.setDouble(3, produto.getValor());
		insert.setLong(4, produto.getCategoria_id());
		insert.execute();
		connection.commit();
		
		} catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}
	}
	
	
	public void atualizar(BeanProduto produto) throws SQLException {
		
		try {
		String sql = "update produto set nome = ?, quantidade = ?, valor = ?, categoria_id = ?  where id = " + produto.getId();
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, produto.getNome());
		insert.setDouble(2, produto.getQuantidade());
		insert.setDouble(3, produto.getValor());
		insert.setLong(4, produto.getCategoria_id());
		insert.execute();
		connection.commit();
		
		} catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}
	}
	
	
	public List<BeanProduto> listar() throws SQLException{
		List<BeanProduto> listar = new ArrayList<BeanProduto>();
		String sql = "select * from produto";	
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(rs.getLong("id"));
			beanProduto.setNome(rs.getString("nome"));
			beanProduto.setQuantidade(rs.getDouble("quantidade"));
			beanProduto.setValor(rs.getDouble("valor"));
			beanProduto.setCategoria_id(rs.getLong("categoria_id"));
			listar.add(beanProduto);			
		}
		
		return listar;	
	}
	
	
	public List<BeanCategoria> listaCategoria() throws SQLException{
		List<BeanCategoria> retorno = new ArrayList<BeanCategoria>();
		String sql = "select * from categoria";	
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			BeanCategoria BeanCategoria = new BeanCategoria();
			BeanCategoria.setId(rs.getLong("id"));
			BeanCategoria.setNome(rs.getString("nome"));

			retorno.add(BeanCategoria);		
		}
		
		return retorno;
	}
	
	
	
	public BeanProduto consultar(String id) throws SQLException {
		String sql = "select * from produto where id ='" + id +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(rs.getLong("id"));
			beanProduto.setNome(rs.getString("nome"));
			beanProduto.setQuantidade(rs.getDouble("quantidade"));
			beanProduto.setValor(rs.getDouble("valor"));
			beanProduto.setCategoria_id(rs.getLong("categoria_id"));
			
			return beanProduto;
		}
		return null;
	}
	
	
	public void delete (String id) {
		
		try {
		String sql = "delete from produto where id = '" + id + "'";
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
	
	
	public boolean validarNome(String nome) throws SQLException {
		String sql = "select count(1) as qtd from produto where nome ='" + nome +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0 ;
		}
		
		return false;
	}
	
	
	public boolean validarNomeUpdate(String nome, String id) throws SQLException {
		String sql = "select count(1) as qtd from produto where login ='" + nome +"' and id <> " + id;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0 ;
		}
		
		return false;
	}
	

}
