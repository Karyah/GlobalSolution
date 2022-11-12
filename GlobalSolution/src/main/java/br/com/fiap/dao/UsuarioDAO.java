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
		try {
			gerarId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			SQLdois = conexao.prepareStatement("select count(idUsuario) from Usuario");
			ResultSet results = SQLdois.executeQuery();
			
			if(results.next()) {
				idConta = results.getInt("count(idUsuario)")  + 1;
				
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
	*Recebe o objeto UsuarioTO com seus respectivos registros, e o cadastra no banco de dados.
		 *@param usuario Objeto UsuarioTO que deve ser cadastrado.
		 *@return void
		 *@throws SQLException caso não seja possível obter conexão com o banco de dados.
		 *@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public void inserir(UsuarioTO usuario) throws SQLException, NullPointerException{
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("insert into Usuario (idUsuario, login, nome, email, senha) VALUES(?,?,?,?,?)");
			
			SQL.setInt(1, gerarId());
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
	
	/**
	* Retorna todos os objetos do tipo UsuarioTO que estão cadastrados no banco de dados.
	*@return List de UsuarioTO
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public List<UsuarioTO> listar() throws SQLException, NullPointerException{
			
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
	
	/**
	*Recebe uma String que condiz ao login do usuário. Este método procura no banco de dados registros que possuam 
	*este login.
	*@param login A string do login do usuário que deseja-se obter.
	*@return O objeto UsuarioTO cujo login é igual ao recebido pelo parâmetro.
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public UsuarioTO buscarPorLogin(String login) throws SQLException, NullPointerException {
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
	
	public UsuarioTO buscarPorId(int id) throws SQLException, NullPointerException{
			
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
	
	/**
	* Recebe o objeto UsuarioTO, e atualiza esse objeto com seus novos atributos no banco de dados.
	*@param usuario Objeto UsuarioTO que deve ser atualizado no banco de dados
	*@return void
	*@throws SQLException caso não seja possível obter conexão com o banco de dados.
	*@throws NullPointerException caso um dado nulo seja inserido.
	*/
	public void atualizar(UsuarioTO usuario) throws SQLException, NullPointerException{

		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("UPDATE Usuario SET login = ?, nome =?, email = ?,  senha = ?");
			SQL.setString(1, usuario.getLogin());
			SQL.setString(2, usuario.getNome());
			SQL.setString(3, usuario.getEmail());
			SQL.setString(4, usuario.getSenha());
			
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
				SQL = conexao.prepareStatement("DELETE FROM Usuario WHERE idUsuario =  ?");
				SQL.setInt(1, id);
				
				SQL.executeUpdate();
				SQL.close();
				conexao.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
}
