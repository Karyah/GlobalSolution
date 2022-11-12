package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AluguelTO {
	private int id;
	private UsuarioTO usuario;
	private BicicletaTO bicicleta;
	private int tempoDeUso;
	
	public AluguelTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AluguelTO(int id, UsuarioTO objetoUsuario, BicicletaTO objetoBike, int tempoDeUso) {
		super();
		this.id = id;
		this.usuario=(objetoUsuario);
		this.bicicleta=(objetoBike);
		this.tempoDeUso = tempoDeUso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public BicicletaTO getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(BicicletaTO bicicleta) {
		this.bicicleta = bicicleta;
	}

	public int getTempoDeUso() {
		return tempoDeUso;
	}

	public void setTempoDeUso(int tempoDeUso) {
		this.tempoDeUso = tempoDeUso;
	}
	
	
}
