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
	
	public void inserir(BicicletaTO bicicleta) throws SQLException{
		PreparedStatement SQL = null;
		
		try {
			SQL = conexao.prepareStatement("insert into Bicicleta (idBicicleta, serial, tamanho, disponibilidade) VALUES(?,?,?)");
			
			SQL.setInt(1, bicicleta.getId());
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
					String tamanhoString = rs.getString("senha_candidato");
					
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
