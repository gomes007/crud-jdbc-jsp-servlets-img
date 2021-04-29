package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
	
	
	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanCursoJsp usuario) throws SQLException {
		
		try {
		String sql = "insert into usuario(login, senha, nome, fone, cep, rua, bairro, cidade, estado, "
				+ " ibge, fotoBase64, contentType, curriculoBase64, curriculocontentType, ativo, sexo, perfil) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, usuario.getLogin());
		insert.setString(2, usuario.getSenha());
		insert.setString(3, usuario.getNome());
		insert.setString(4, usuario.getFone());
		insert.setString(5, usuario.getCep());
		insert.setString(6, usuario.getRua());
		insert.setString(7, usuario.getBairro());
		insert.setString(8, usuario.getCidade());
		insert.setString(9, usuario.getEstado());
		insert.setString(10, usuario.getIbge());
		insert.setString(11, usuario.getFotoBase64());
		insert.setString(12, usuario.getContentType());
		insert.setString(13, usuario.getCurriculoBase64());
		insert.setString(14, usuario.getCurriculocontentType());
		insert.setBoolean(15, usuario.isAtivo());
		insert.setString(16, usuario.getSexo());
		insert.setString(17, usuario.getPerfil());
		insert.execute();
		connection.commit();
		
		} catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}
	}
	
	
	public void atualizar(BeanCursoJsp usuario) {
		
		try {
		
		String sql = "update usuario set login = ?, senha = ?, nome = ?, fone = ?, "
				+ " cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, "
				+ " fotoBase64 = ?, contentType = ?, curriculoBase64 = ?, "
				+ " curriculocontentType = ?, ativo = ?, sexo = ?, perfil = ? where id = " + usuario.getId();
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, usuario.getLogin());
		st.setString(2, usuario.getSenha());
		st.setString(3, usuario.getNome());
		st.setString(4, usuario.getFone());
		st.setString(5, usuario.getCep());
		st.setString(6, usuario.getRua());
		st.setString(7, usuario.getBairro());
		st.setString(8, usuario.getCidade());
		st.setString(9, usuario.getEstado());
		st.setString(10, usuario.getIbge());
		st.setString(11, usuario.getFotoBase64());
		st.setString(12, usuario.getContentType());
		st.setString(13, usuario.getCurriculoBase64());
		st.setString(14, usuario.getCurriculocontentType());
		st.setBoolean(15, usuario.isAtivo());
		st.setString(16, usuario.getSexo());
		st.setString(17, usuario.getPerfil());
		st.executeUpdate();
		
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
	
	
	public List<BeanCursoJsp> listar(String descricaoPesquisa) throws SQLException{
		String sql = "select * from usuario where login <> 'pg' and nome like '%"+descricaoPesquisa+"%'";
		
			
		
		return consultarUsuarios(sql);
	}
	
	
	
	public List<BeanCursoJsp> listar() throws SQLException{
		String sql = "select * from usuario where login <> 'pg'";
		
		return consultarUsuarios(sql);
		
	
	}

	private List<BeanCursoJsp> consultarUsuarios(String sql) throws SQLException {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(rs.getLong("id"));
			beanCursoJsp.setLogin(rs.getString("login"));
			beanCursoJsp.setSenha(rs.getString("senha"));
			beanCursoJsp.setNome(rs.getString("nome"));
			beanCursoJsp.setFone(rs.getString("fone"));
			beanCursoJsp.setCep(rs.getString("cep"));
			beanCursoJsp.setRua(rs.getString("rua"));
			beanCursoJsp.setBairro(rs.getString("bairro"));
			beanCursoJsp.setCidade(rs.getString("cidade"));
			beanCursoJsp.setEstado(rs.getString("estado"));
			beanCursoJsp.setIbge(rs.getString("ibge"));
			beanCursoJsp.setFotoBase64(rs.getString("fotoBase64"));
			beanCursoJsp.setContentType(rs.getString("contentType"));
			beanCursoJsp.setCurriculoBase64(rs.getString("curriculoBase64"));
			beanCursoJsp.setCurriculocontentType(rs.getString("curriculocontentType"));
			beanCursoJsp.setAtivo(rs.getBoolean("ativo"));
			beanCursoJsp.setSexo(rs.getString("sexo"));
			beanCursoJsp.setPerfil(rs.getString("perfil"));
			listar.add(beanCursoJsp);		
		}
		
		return listar;
	}
	
	
	public BeanCursoJsp consultar(String id) throws SQLException {
		String sql = "select * from usuario where id ='" + id +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(rs.getLong("id"));
			beanCursoJsp.setLogin(rs.getString("login"));
			beanCursoJsp.setSenha(rs.getString("senha"));
			beanCursoJsp.setNome(rs.getString("nome"));
			beanCursoJsp.setFone(rs.getString("fone"));
			beanCursoJsp.setCep(rs.getString("cep"));
			beanCursoJsp.setRua(rs.getString("rua"));
			beanCursoJsp.setBairro(rs.getString("bairro"));
			beanCursoJsp.setCidade(rs.getString("cidade"));
			beanCursoJsp.setEstado(rs.getString("estado"));
			beanCursoJsp.setIbge(rs.getString("ibge"));	
			beanCursoJsp.setFotoBase64(rs.getString("fotoBase64"));
			beanCursoJsp.setContentType(rs.getString("contentType"));
			beanCursoJsp.setCurriculoBase64(rs.getString("curriculoBase64"));
			beanCursoJsp.setCurriculocontentType(rs.getString("curriculocontentType"));
			beanCursoJsp.setAtivo(rs.getBoolean("ativo"));
			beanCursoJsp.setSexo(rs.getString("sexo"));
			beanCursoJsp.setPerfil(rs.getString("perfil"));
			
			
			return beanCursoJsp;
		}
		return null; 
	}
	
	
	
	
	public void delete (String id) {
		
		try {
		String sql = "delete from usuario where id = '" + id + "'";
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
	

	
	
	
	public boolean validarLogin(String login) throws SQLException {
		String sql = "select count(1) as qtd from usuario where login ='" + login +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0;
		}
		
		return false;
	}
	
	
	public boolean validarSenha(String senha) throws SQLException {
		String sql = "select count(1) as qtd from usuario where senha ='" + senha +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0 ;
		}
		
		return false;
	}
	
	
	public boolean validarLoginUpdate(String login, String id) throws SQLException {
		String sql = "select count(1) as qtd from usuario where login ='" + login +"' and id <> " + id;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			
			return rs.getInt("qtd") <=0 ;
		}
		
		return false;
	}
	



}
