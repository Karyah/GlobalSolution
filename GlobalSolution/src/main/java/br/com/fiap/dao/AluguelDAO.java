package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.entity.AluguelTO;

public class AluguelDAO {
	private Connection conexao;
	
	public AluguelDAO() {
		this.conexao = new GerenciadorBD().obterConexao();
	}
	
	public void inserir(AluguelTO aluguel) throws SQLException{
		PreparedStatement SQL = null;
		try {
			SQL = conexao.prepareStatement("insert into Aluguel (idUsuario, idBicileta, tempoDeUso) values(?,?)");
			SQL.setInt(1, aluguel.getUsuario().getId());
			SQL.setInt(2, aluguel.getBicicleta().getId());
			SQL.setInt(3, aluguel.getTempoDeUso());
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}
