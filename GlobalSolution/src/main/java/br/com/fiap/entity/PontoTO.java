package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PontoTO {
	private int id;
	private String endereco;
	private UsuarioTO dono;
	

	public PontoTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PontoTO(int id, String endereco, UsuarioTO objetoUsuario) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.dono= (objetoUsuario);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public UsuarioTO getUsuario() {
		return dono;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.dono = usuario;
	}
	
	
}
