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
	
	/**
	 * Método que calcula o tamanho da lista no banco de dados e adiciona um para criar o id.
	 * @return idConta numero do id gerado.
	 * @throws SQLException caso não seja possível obter conexão com o banco de dados.
	 */
	public int gerarId() throws SQLException{
		PreparedStatement SQLdois = null;
		int idConta= 0;
		try {
			SQLdois = conexao.prepareStatement("select count(idAluguel) from Aluguel");
			ResultSet results = SQLdois.executeQuery();
			
			if(results.next()) {
				idConta = results.getInt("count(idAluguel)")  + 1;
			
			}
			SQLdois.close();
			results.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idConta;
	}
	
	 /**
	 *Recebe o objeto AluguelTO com seus respectivos registros, e o cadastra no banco de dados.
	 *@param aluguel Objeto AluguelTO que deve ser cadastrado.
	 *@return void
	 *@throws SQLException caso não seja possível obter conexão com o banco de dados.
	 *@throws NullPointerException caso um dado nulo seja inserido.
	  **/
	public void inserir(AluguelTO aluguel) throws SQLException{
		PreparedStatement SQL = null;
		try {
			SQL = conexao.prepareStatement("insert into Aluguel (idAluguel, idUsuario, idBicileta, tempoDeUso) values(?,?,?,?)");
			SQL.setInt(1, gerarId());
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
	
	
	/**
	* Retorna todos os objetos do tipo AluguelTO que estão cadastrados no banco de dados.
	*@return List de AlguelTO
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
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
	
	/**
	*Recebe o id do objeto do tipo AluguelTO, cujo registro deve ser buscado e retornado. Busca no banco 
	*de dados o registro do objeto que tem esse mesmo id e o retorna.
	*@param id O id do objeto que se deseja obter.
	*@return O objeto AluguelTO cujo id é igual ao recebido pelo parâmetro.
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public AluguelTO buscarPorId(int id) throws SQLException{
		
		PreparedStatement SQL = null;
		AluguelTO aluguel = new AluguelTO();
		
		try{
			SQL = conexao.prepareStatement("SELECT * FROM Aluguel WHERE idAluguel = ?");
			SQL.setInt(1, id);	
			ResultSet rs = SQL.executeQuery();
			
			if(rs.next()) {
				aluguel.setId(rs.getInt("idAluguel"));
				aluguel.setTempoDeUso(rs.getInt("tempoDeUso"));
				
				int idUsuario = rs.getInt("idUsuario");
				int idBicicleta = rs.getInt("idBicicleta");
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				BicicletaDAO bikeDAO = new BicicletaDAO();
				
				UsuarioTO usuario = usuarioDAO.buscarPorId(idUsuario);
				BicicletaTO bike = bikeDAO.buscarPorId(idBicicleta);
				
				aluguel.setBicicleta(bike);
				aluguel.setUsuario(usuario);
			}
		conexao.close();
		SQL.close();
				
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		return aluguel;
	}
	
	/**
	*Recebe o id do objeto cujo registro deve ser excluído no banco de dados, e o exclui.
	*@param id id do objeto que deve ser excluído.
	*@return void
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/

	public void deletar(int id) throws SQLException{
		
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("DELETE FROM Aluguel WHERE idAluguel =  ?");
			SQL.setInt(1, id);
			
			SQL.executeUpdate();
			SQL.close();
			conexao.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
