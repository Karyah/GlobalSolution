package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AluguelTO {
	private UsuarioTO usuario;
	private BicicletaTO bicicleta;
	private int tempoDeUso;
	
	public AluguelTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AluguelTO(UsuarioTO usuario, BicicletaTO bicicleta, int tempoDeUso) {
		super();
		this.usuario = usuario;
		this.bicicleta = bicicleta;
		this.tempoDeUso = tempoDeUso;
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
