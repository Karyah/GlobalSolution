package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;
import br.com.fiap.enums.EnumTamanho;

@XmlRootElement
public class BicicletaTO {
	private int id;
	private String serial;
	private EnumTamanho tamanho;	
	
	public BicicletaTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BicicletaTO(int id, String serial, EnumTamanho tamanho) {
		super();
		this.id = id;
		this.serial = serial;
		this.tamanho = tamanho;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public EnumTamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(EnumTamanho tamanho) {
		this.tamanho = tamanho;
	}
	
	public String retornarTamanho() {
		if(tamanho==EnumTamanho.Adulto) {
			return "Adulto";
		}else{
			return "Kids";
		}
	}
	
}
	