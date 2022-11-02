package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class PontoTO {
	private int id;
	private String endereco;
	private UsuarioTO usuario;
	

	public PontoTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PontoTO(int id, String endereco, UsuarioTO usuario) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.usuario = usuario;
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
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
	
	
}