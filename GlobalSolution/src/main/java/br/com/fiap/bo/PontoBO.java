package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.PontoDAO;
import br.com.fiap.entity.PontoTO;


public class PontoBO {
	private PontoDAO pontoDAO;
	
	public void inserir(PontoTO ponto) {
		pontoDAO = new PontoDAO();
		try {
			pontoDAO.inserir(ponto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public List<PontoTO> listar(){
		pontoDAO = new PontoDAO();
		try {
			return pontoDAO.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public List<PontoTO> buscarPorEndereco(String endereco) {
		pontoDAO = new PontoDAO();
		try {
			return pontoDAO.buscarPorEndereco(endereco);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public void atualizar(PontoTO ponto) {
		pontoDAO = new PontoDAO();
		try {
			pontoDAO.atualizar(ponto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public void deletar(int id) {
		pontoDAO = new PontoDAO();
		try {
			pontoDAO.deletar(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
}
