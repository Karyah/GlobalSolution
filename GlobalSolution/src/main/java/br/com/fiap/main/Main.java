package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.UsuarioTO;

public class Main {
	/*Esta classe foi criada a fim de criar testes do projeto, e poder obter o log de erros. Assim que o servidor
	 * funcionou, está classe foi descontinuada.*/
	public static void main(String[] args) {
		UsuarioTO usuario = new UsuarioTO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setNome("Karina");
		usuario.setLogin("kaka");
		usuario.setEmail("karihh@yahoo.com.br");
		usuario.setSenha("kkkkkkkkkkkk");
		
		try {

//			usuarioDAO.inserir(usuario);
			
			List<UsuarioTO> listaUsuario = usuarioDAO.listar();
			
			for(UsuarioTO usuarioto: listaUsuario)
//				usuarioto.toString();
				System.out.println("Nome: "+usuarioto.getNome()+ ". Email: "+usuarioto.getEmail()+". Login: "
						+ usuarioto.getLogin()+". Senha:"+usuarioto.getSenha());
			
			UsuarioTO buscandoXandria = usuarioDAO.buscarPorLogin("xandriaA");
			
			System.out.println(buscandoXandria.toString());
			
			usuarioDAO.deletar(0);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
}
