package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.BicicletaDAO;
import br.com.fiap.entity.BicicletaTO;


public class BicicletaBO {
	private BicicletaDAO bikeDAO;
	
	public void inserir(BicicletaTO bicicleta) {
		bikeDAO = new BicicletaDAO();
		try {
			bikeDAO.inserir(bicicleta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public List<BicicletaTO> listar(){
		bikeDAO = new BicicletaDAO();
		try {
			return bikeDAO.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public BicicletaTO buscarPorId(int id) {
		bikeDAO = new BicicletaDAO();
		try {
			return bikeDAO.buscarPorId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public void atualizar(BicicletaTO bicicleta) {
		bikeDAO = new BicicletaDAO();
		try {
			bikeDAO.atualizar(bicicleta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public void deletar(int id) {
		bikeDAO = new BicicletaDAO();
		try {
			bikeDAO.deletar(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
}	
