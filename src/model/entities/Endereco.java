package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Endereco  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idFornecedor;
	private Integer idCep;
	private Integer numero;
	private String complemento;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public Integer getIdCep() {
		return idCep;
	}
	public void setIdCep(Integer idCep) {
		this.idCep = idCep;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
