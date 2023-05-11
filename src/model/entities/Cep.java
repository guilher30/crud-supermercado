package model.entities;

import java.io.Serializable;

public class Cep implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numeroCep;
	private String uf;
	private String cidade;
	private String bairro;
	private String lougradouro;

	public Cep() {
		super();
	}

	public Cep(Integer id, String numeroCep, String uf, String cidade, String bairro, String lougradouro) {
		super();
		this.id = id;
		this.numeroCep = numeroCep;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.lougradouro = lougradouro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroCep() {
		return numeroCep;
	}

	public void setNumeroCep(String numeroCep) {
		this.numeroCep = numeroCep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLougradouro() {
		return lougradouro;
	}

	public void setLougradouro(String lougradouro) {
		this.lougradouro = lougradouro;
	}

}
