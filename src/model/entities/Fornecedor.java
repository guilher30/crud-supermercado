package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Fornecedor  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String cnpj;
	private String ie;
	private Endereco endereco;
	private Cep cep;
	
	
	public Fornecedor() {
		super();
	}


	public Fornecedor(Integer id, String nome, String cnpj, String ie, Endereco endereco, Cep cep) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.ie = ie;
		this.endereco = endereco;
		this.cep = cep;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getIe() {
		return ie;
	}


	public void setIe(String ie) {
		this.ie = ie;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Cep getCep() {
		return cep;
	}


	public void setCep(Cep cep) {
		this.cep = cep;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}
	
	
}