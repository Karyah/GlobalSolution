package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.BicicletaTO;
import br.com.fiap.enums.EnumTamanho;

public class BicicletaDAO {
	private Connection conexao = null;
	
	public BicicletaDAO() {
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
			SQLdois = conexao.prepareStatement("select count(idBicicleta) from Bicicleta");
			ResultSet results = SQLdois.executeQuery();
			
			if(results.next()) {
				idConta = results.getInt("count(idBicicleta)")  + 1;
			
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
	*Recebe o objeto BicicletaTO com seus respectivos registros, e o cadastra no banco de dados.
		 *@param bicicleta Objeto BicicletaTO que deve ser cadastrado.
		 *@return void
		 *@throws SQLException caso não seja possível obter conexão com o banco de dados.
		 *@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public void inserir(BicicletaTO bicicleta) throws SQLException{
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("insert into Bicicleta (idBicicleta, serial, tamanho, disponibilidade) VALUES(?,?,?)");
			
			SQL.setInt(1, gerarId());
			SQL.setString(2, bicicleta.getSerial());
			SQL.setString(3,bicicleta.retornarTamanho());
			SQL.setString(4, bicicleta.retornaDisponibilidade());
			
			SQL.executeUpdate();
			conexao.close();
			SQL.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Retorna todos os objetos do tipo BicicletaTO que estão cadastrados no banco de dados.
	*@return List de BicicletaTO
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/

	public List<BicicletaTO> listar() throws SQLException{
			
			List<BicicletaTO> listaBicicletas = new ArrayList<>();
	
			PreparedStatement SQL = null;
			ResultSet rs = null;
			
			try {
				SQL = conexao.prepareStatement("SELECT * FROM Bicicleta");
				rs = SQL.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("idBicicleta");
					String serial = rs.getString("serial");
					String tamanhoString = rs.getString("tamanho");
					
					EnumTamanho tamanho = null;
					if(tamanhoString.equals("Adulto")) {
						tamanho = EnumTamanho.Adulto;
					}else if(tamanhoString.equals("Kids")) {
						tamanho = EnumTamanho.Kids;
					}
					
					String disponibilidade = rs.getString("disponibilidade");
					
					boolean isDisponivel = true;
					
					if (disponibilidade.equals("Disponível")) {
						isDisponivel = true;
					}else if(disponibilidade.equals("Indisponível")) {
						isDisponivel = false;
					}
					
					
					
					BicicletaTO bicicleta = new BicicletaTO(id, serial, tamanho, isDisponivel);
					listaBicicletas.add(bicicleta);
				}
				
				conexao.close();
				SQL.close();
				rs.close();
				
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return listaBicicletas;	
	
		}
	
	/**
	*Recebe o id do objeto do tipo BicicletaTO, cujo registro deve ser buscado e retornado. Busca no banco 
	*de dados o registro do objeto que tem esse mesmo id e o retorna.
	*@param id O id do objeto que se deseja obter.
	*@return O objeto BicicletaTO cujo id é igual ao recebido pelo parâmetro.
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public BicicletaTO buscarPorId(int id) throws SQLException{
			
			PreparedStatement SQL = null;
			BicicletaTO bicicleta = new BicicletaTO();
			
			try{
				SQL = conexao.prepareStatement("SELECT * FROM Bicicleta WHERE idBicicleta = ?");
				SQL.setInt(1, id);	
				ResultSet rs = SQL.executeQuery();
				
				if(rs.next()) {
					bicicleta.setId(rs.getInt("idBicicleta"));
					bicicleta.setSerial(rs.getString("serial"));
					
					
					
					String tamanho = rs.getString("tamanho");
					if(tamanho.equals("Adulto")) {
						bicicleta.setTamanho(EnumTamanho.Adulto);
					}
					if(tamanho.equals("Kids")) {
						bicicleta.setTamanho(EnumTamanho.Kids);
					}		
					
					
					
					String disponibilidade = rs.getString("disponibilidade");
					
					boolean isDisponivel = true;
					
					if (disponibilidade.equals("Disponível")) {
						isDisponivel = true;
					}else if(disponibilidade.equals("Indisponível")) {
						isDisponivel = false;
						
					bicicleta.setDisponivel(isDisponivel);	
					}
				}
				
				SQL.close();
				conexao.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return bicicleta;
	}
	
	/** 
	* Recebe o objeto BicicletaTO, e atualiza esse objeto com seus novos atributos no banco de dados.
	*@param bicicleta Objeto BicicletaTO que deve ser atualizado no banco de dados
	*@return void
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public void atualizar(BicicletaTO bicicleta) throws SQLException{

		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("UPDATE Bicicleta SET serial = ?, SET tamanho = ?, SET disponibilidade = ?");
			SQL.setString(1, bicicleta.getSerial());
			SQL.setString(2, bicicleta.retornarTamanho());
			SQL.setString(3, bicicleta.retornaDisponibilidade());
			
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
	public void deletar(int id) throws SQLException{
			
			PreparedStatement SQL = null;
			
			try {
				SQL = conexao.prepareStatement("DELETE FROM Bicicleta WHERE idBicicleta =  ?");
				SQL.setInt(1, id);
				
				SQL.executeUpdate();
				SQL.close();
				conexao.close();
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
}
