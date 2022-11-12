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
	
	/**
	 * Método que calcula o tamanho da lista no banco de dados e adiciona um para criar o id.
	 * @return idConta numero do id gerado.
	 * @throws SQLException caso não seja possível obter conexão com o banco de dados.
	 */
	public int gerarId() throws SQLException{
		PreparedStatement SQLdois = null;
		int idConta= 0;
		try {
			SQLdois = conexao.prepareStatement("select count(idPonto) from Ponto");
			ResultSet results = SQLdois.executeQuery();
			
			if(results.next()) {
				idConta = results.getInt("count(idPonto)")  + 1;
			
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
	*Recebe o objeto PontoTO com seus respectivos registros, e o cadastra no banco de dados.
		 *@param ponto Objeto PontoTO que deve ser cadastrado.
		 *@return void
		 *@throws SQLException caso não seja possível obter conexão com o banco de dados.
		 *@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public void inserir(PontoTO ponto) throws SQLException{
		PreparedStatement SQL = null;
		try {
			SQL = conexao.prepareStatement("insert into Ponto (idPonto, endereco, idUsuario) values(?,?,?)");
			
			SQL.setInt(1, gerarId());
			SQL.setString(2, ponto.getEndereco());
			SQL.setInt(3, ponto.getUsuario().getId());
			
			SQL.executeUpdate();
			conexao.close();
			SQL.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Retorna todos os objetos do tipo PontoTO que estão cadastrados no banco de dados.
	*@return List de PontoTO
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
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
				
				UsuarioDAO usuarioDao = new UsuarioDAO();
				UsuarioTO objetoUsuario = usuarioDao.buscarPorId(idUsuario);
				
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
	
	/**
	*Recebe o id do objeto do tipo PontoTO, cujo registro deve ser buscado e retornado. Busca no banco 
	*de dados o registro do objeto que tem esse mesmo id e o retorna.
	*@param id O id do objeto que se deseja obter.
	*@return O objeto PontoTO cujo id é igual ao recebido pelo parâmetro.
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public List<PontoTO> buscarPorEndereco(String endereco) throws SQLException {
		PreparedStatement SQL = null;
		ResultSet rs = null;
		
		List<PontoTO> listaPontos = new ArrayList<>();
		
		try {
			SQL = conexao.prepareStatement("SELECT * FROM Ponto WHERE endereco LIKE ?");
			SQL.setString(1, "%"+endereco+"%");
			rs = SQL.executeQuery();
			
			while(rs.next()) {
				int idPonto = rs.getInt("idPonto");
				String enderecoString =rs.getString("endereco");
				int idUsuario = rs.getInt("idUsuario");
//				
				UsuarioDAO usuario = new UsuarioDAO();
				UsuarioTO objetoUsuario = usuario.buscarPorId(idUsuario);
				
				PontoTO ponto = new PontoTO(idPonto, enderecoString, objetoUsuario	);
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
	
	/**
	* Recebe o objeto PontoTO, e atualiza esse objeto com seus novos atributos no banco de dados.
	*@param ponto Objeto PontoTO que deve ser atualizado no banco de dados
	*@return void
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
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
	
	/**
	*Recebe o id do objeto cujo registro deve ser excluído no banco de dados, e o exclui.
	*@param id id do objeto que deve ser excluído.
	*@return void
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
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
