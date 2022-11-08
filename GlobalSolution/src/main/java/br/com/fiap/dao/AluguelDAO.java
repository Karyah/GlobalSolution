package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.AluguelTO;
import br.com.fiap.entity.BicicletaTO;
import br.com.fiap.entity.UsuarioTO;


public class AluguelDAO {
	private Connection conexao;
	
	public AluguelDAO() {
		this.conexao = new GerenciadorBD().obterConexao();
	}
	
	public void inserir(AluguelTO aluguel) throws SQLException{
		PreparedStatement SQL = null;
		try {
			SQL = conexao.prepareStatement("insert into Aluguel (idAluguel, idUsuario, idBicileta, tempoDeUso) values(?,?,?,?)");
			SQL.setInt(1, aluguel.getId());
			SQL.setInt(2, aluguel.getUsuario().getId());
			SQL.setInt(3, aluguel.getBicicleta().getId());
			SQL.setInt(4, aluguel.getTempoDeUso());
			
			SQL.executeUpdate();
			conexao.close();
			SQL.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public List<AluguelTO> listar() throws SQLException{
		List<AluguelTO> listaAluguel = new ArrayList<>();
		PreparedStatement SQL = null;
		ResultSet rs = null;
		
		try {
			SQL = conexao.prepareStatement("Select * from Aluguel");
			rs = SQL.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("idAluguel");
				int tempoDeUso = rs.getInt("tempoDeUso");
				int idUsuario = rs.getInt("idUsuario");
				int idBicicleta = rs.getInt("idBicicleta");
				
				UsuarioDAO usuario = new UsuarioDAO();
				UsuarioTO objetoUsuario = usuario.buscarPorId(idUsuario);
				
				BicicletaDAO bike = new BicicletaDAO();
				
				BicicletaTO objetoBike = bike.buscarPorId(idBicicleta);
				
				AluguelTO aluguel = new AluguelTO(id, objetoUsuario, objetoBike, tempoDeUso);
				listaAluguel.add(aluguel);
			}
			conexao.close();
			SQL.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaAluguel;
	}
}
