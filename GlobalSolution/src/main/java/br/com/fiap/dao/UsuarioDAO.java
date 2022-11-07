package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.entity.UsuarioTO;



public class UsuarioDAO {
	
	private Connection conexao = null;
	
	public UsuarioDAO() {
		this.conexao = new GerenciadorBD().obterConexao();
	}
	
	public void inserir(UsuarioTO usuario) throws SQLException{
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("insert into Usuario (idUsuario, login, nome, email, senha) VALUES(?,?,?,?,?)");
			
			SQL.setInt(1, usuario.getId());
			SQL.setString(2, usuario.getLogin());
			SQL.setString(3, usuario.getNome());
			SQL.setString(4, usuario.getEmail());
			SQL.setString(5, usuario.getSenha());
			
			SQL.executeUpdate();
			conexao.close();
			SQL.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<UsuarioTO> listar() throws SQLException{
			
			List<UsuarioTO> listaUsuarios = new ArrayList<>();
			PreparedStatement SQL = null;
			ResultSet rs = null;
			
			try {
				SQL = conexao.prepareStatement("select * from Usuario");
				rs = SQL.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("idUsuario");
					String login = rs.getString("login");
					String nome = rs.getString("nome");
					String email = rs.getString("email");
					String senha = rs.getString("senha");
					
					UsuarioTO usuario = new UsuarioTO(id, login, nome, email, senha);
					listaUsuarios.add(usuario);
				}
				conexao.close();
				SQL.close();
				rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return listaUsuarios;	
	
		}
	
	public UsuarioTO buscarPorLogin(String login) throws SQLException {
		PreparedStatement SQL = null;
		UsuarioTO usuario = new UsuarioTO();
		
		try {
			SQL = conexao.prepareStatement("SELECT * from Usuario WHERE login = ?");
			SQL.setString(1, login);
			
			ResultSet rs = SQL.executeQuery();
			if(rs.next()) {
				usuario.setId(rs.getInt("idUsuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
			}
			
			SQL.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return usuario;
	}
	
	public UsuarioTO buscarPorId(int id) throws SQLException{
			
			PreparedStatement SQL = null;
			
			UsuarioTO usuario = new UsuarioTO();
			try{
				SQL = conexao.prepareStatement("SELECT * FROM Usuario WHERE idUsuario = ?");
				SQL.setInt(1, id);	
				ResultSet rs = SQL.executeQuery();
				
				if(rs.next()) {
					usuario.setId(rs.getInt("idUsuario"));
					usuario.setLogin(rs.getString("login"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));				
				}
				
				SQL.close();
				conexao.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return usuario;
		}
	
	public void atualizar(UsuarioTO usuario) throws SQLException{

		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("UPDATE Usuario SET login = ?, SET nome =?, SET email = ?, SET senha = ?");
			SQL.setString(1, usuario.getLogin());
			SQL.setString(1, usuario.getNome());
			SQL.setString(4, usuario.getEmail());
			SQL.setString(2, usuario.getSenha());
			
			SQL.executeUpdate();
			SQL.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletar(int id) throws SQLException{
			
			PreparedStatement SQL = null;
			
			try {
				SQL = conexao.prepareStatement("DELETE FROM Usuario WHERE id =  ?");
				SQL.setInt(1, id);
				
				SQL.executeUpdate();
				SQL.close();
				conexao.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
}
