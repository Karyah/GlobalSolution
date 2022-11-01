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
			SQL = conexao.prepareStatement("insert into Usuario (id, nome, senha) VALUES(?,?,?)");
			
			SQL.setInt(1, usuario.getId());
			SQL.setString(2, usuario.getNome());
			SQL.setString(3, usuario.getSenha());
			
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
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String senha = rs.getString("senha");
					
					UsuarioTO usuario = new UsuarioTO(id, nome, senha);
					System.out.println(id);
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
}
