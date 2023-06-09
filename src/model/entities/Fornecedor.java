package model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.swing.JOptionPane;

public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String cnpj;
	private String ie;
	private Cep cep;
	private Integer numero;
	private String complemento;
	

	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer id, String nome, String cnpj, String ie, Cep cep, Integer numero, String complemento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.ie = ie;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		
		
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
	
	

	

	@Override
	public String toString() {
		return "Id: " + id + "\nNome: " + nome + "\nCNPJ: " + cnpj + "\nIncri��o Estadual: " + ie +"\n"+ cep
				+ "\nNumero: " + numero + "\nComplemento: " + complemento +"\n\n";
	}

	public static boolean validarCNPJ(String cnpj) {
	    cnpj = cnpj.replace(".", "").replace("-", "").replace("/", ""); // Remove caracteres especiais
	    if (cnpj.length() != 14) { // Verifica se possui 14 d�gitos
	        return false;
	    }

	    // Calcula o primeiro d�gito verificador
	    int soma = 0;
	    int peso = 5;
	    for (int i = 0; i < 12; i++) {
	        soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
	        peso--;
	        if (peso < 2) {
	            peso = 9;
	        }
	    }
	    int digito1 = soma % 11;
	    if (digito1 < 2) {
	        digito1 = 0;
	    } else {
	        digito1 = 11 - digito1;
	    }

	    // Calcula o segundo d�gito verificador
	    soma = 0;
	    peso = 6;
	    for (int i = 0; i < 13; i++) {
	        soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
	        peso--;
	        if (peso < 2) {
	            peso = 9;
	        }
	    }
	    int digito2 = soma % 11;
	    if (digito2 < 2) {
	        digito2 = 0;
	    } else {
	        digito2 = 11 - digito2;
	    }

	    // Verifica se os d�gitos calculados s�o iguais aos d�gitos informados
	    return Integer.parseInt(cnpj.substring(12, 13)) == digito1 && Integer.parseInt(cnpj.substring(13, 14)) == digito2;
	}




}
