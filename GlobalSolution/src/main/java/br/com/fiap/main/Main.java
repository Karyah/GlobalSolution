package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.UsuarioTO;

public class Main {
	public static void main(String[] args) {
		UsuarioTO usuario = new UsuarioTO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setNome("Alvares");
		usuario.setLogin("ALVES");
		usuario.setEmail("albvesasnto@gmail.com.br");
		usuario.setSenha("senhadoalvaro");
		
		try {
			usuarioDAO.inserir(usuario);
			
			List<UsuarioTO> listaUsuario = usuarioDAO.listar();
			
			for(UsuarioTO usuarioto: listaUsuario)
//				usuarioto.toString();
				System.out.println("Nome: "+usuarioto.getNome()+ ". Email: "+usuarioto.getEmail()+". Login: "
						+ usuarioto.getLogin()+". Senha:"+usuarioto.getSenha());
			
			UsuarioTO buscandoXandria = usuarioDAO.buscarPorLogin("xandriaA");
			
			System.out.println(buscandoXandria.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
}
