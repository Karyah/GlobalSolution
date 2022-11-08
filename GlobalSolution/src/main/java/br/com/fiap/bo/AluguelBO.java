package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.AluguelDAO;
import br.com.fiap.entity.AluguelTO;

public class AluguelBO {
	
	private AluguelDAO aluguelDAO;
	
	public void inserir(AluguelTO alguel) {
		try {
			aluguelDAO.inserir(alguel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<AluguelTO> listar(){
		try {
			return aluguelDAO.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
