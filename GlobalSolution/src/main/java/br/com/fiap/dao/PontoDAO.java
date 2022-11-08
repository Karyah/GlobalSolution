package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.PontoTO;
import br.com.fiap.entity.UsuarioTO;

public class PontoDAO {
	private Connection conexao;
	
	public PontoDAO() {
		this.conexao = new GerenciadorBD().obterConexao();
	}
	
	public void inserir(PontoTO ponto) throws SQLException{
		PreparedStatement SQL = null;
		try {
			SQL = conexao.prepareStatement("insert into Ponto (idPonto, endereco, idUsuario) values(?,?)");
			
			SQL.setInt(1, ponto.getId());
			SQL.setString(2, ponto.getEndereco());
			SQL.setInt(3, ponto.getUsuario().getId());
			
			SQL.executeUpdate();
			conexao.close();
			SQL.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<PontoTO> listar() throws SQLException{
		List<PontoTO> listaPontos = new ArrayList<>();
		PreparedStatement SQL = null;
		ResultSet rs = null;
		
		try {
			SQL = conexao.prepareStatement("Select * from Ponto");
			rs = SQL.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("idPonto");
				String endereco = rs.getString("endereco");
				int idUsuario = rs.getInt("idUsuario");
				
				UsuarioDAO usuario = new UsuarioDAO();
				UsuarioTO objetoUsuario = usuario.buscarPorId(idUsuario);
				
				PontoTO ponto = new PontoTO(id,endereco, objetoUsuario);
				listaPontos.add(ponto);
			}
			conexao.close();
			SQL.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPontos;
	}
	
 
	public List<PontoTO> buscarPorEndereco(String endereco) throws SQLException {
		PreparedStatement SQL = null;
		ResultSet rs = null;
		
		List<PontoTO> listaPontos = new ArrayList<>();
		
		try {
			SQL = conexao.prepareStatement("SELECT * FROM Ponto WHERE endereco LIKE '%?%'");
			SQL.setString(1, endereco);
			rs = SQL.executeQuery();
			
			while(rs.next()) {
				int idPonto = rs.getInt("idPonto");
				String enderecoString =rs.getString("endereco");
				int idUsuario = rs.getInt("idUsuario");
				
				UsuarioDAO usuario = new UsuarioDAO();
				UsuarioTO objetoUsuario = usuario.buscarPorId(idUsuario);
				
				PontoTO ponto = new PontoTO(idPonto, enderecoString, objetoUsuario);
				listaPontos.add(ponto);
			}
			SQL.close();
			rs.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaPontos;
	}
	
	public void atualizar(PontoTO ponto) throws SQLException {
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("UPDATE Ponto SET endereco = ? ");
			SQL.setString(1, ponto.getEndereco());
			
			SQL.executeUpdate();
			SQL.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deletar(int id) throws SQLException {
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("DELETE FROM Ponto WHERE idPonto = ?");
			SQL.setInt(1, id);
			
			SQL.executeUpdate();
			SQL.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
