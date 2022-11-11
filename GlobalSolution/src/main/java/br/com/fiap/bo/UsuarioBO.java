package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.UsuarioTO;

public class UsuarioBO {
	private UsuarioDAO usuarioDAO;
	
	public void inserir(UsuarioTO usuario) {
		usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.inserir(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public List<UsuarioTO> listar(){
		usuarioDAO = new UsuarioDAO();
		try {
			return usuarioDAO.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public UsuarioTO buscarPorId(int id) {
		usuarioDAO = new UsuarioDAO();
		try {
			return usuarioDAO.buscarPorId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public UsuarioTO buscarPorLogin(String login) {
		usuarioDAO = new UsuarioDAO();
		try {
			return usuarioDAO.buscarPorLogin(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	
	public void atualizar(UsuarioTO usuario) {
		usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.atualizar(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	public void deletar(int id) {
		usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.deletar(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
	}
}
