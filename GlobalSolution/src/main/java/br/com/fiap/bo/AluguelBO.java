package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.AluguelDAO;
import br.com.fiap.entity.AluguelTO;

public class AluguelBO {
	
	private AluguelDAO aluguelDAO;
	
	public void inserir(AluguelTO alguel) {
		aluguelDAO = new AluguelDAO();
		try {
			aluguelDAO.inserir(alguel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public List<AluguelTO> listar(){
		aluguelDAO = new AluguelDAO();
		try {
			return aluguelDAO.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public AluguelTO buscarPorId(int id) {
		aluguelDAO = new AluguelDAO();
		try {
			return aluguelDAO.buscarPorId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public void deletar(int id) {
		aluguelDAO = new AluguelDAO();
		try {
			aluguelDAO.deletar(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
}
