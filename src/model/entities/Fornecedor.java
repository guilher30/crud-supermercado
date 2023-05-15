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
	

	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer id, String nome, String cnpj, String ie, Cep cep) {
		super();
		this.id = id;
		this.nome = nome;

		while (!validarCNPJ(cnpj)) {
			cnpj = JOptionPane.showInputDialog("CNPJ Invalido! Digite novamente: ");
		}
		System.out.println("CNPJ VALIDO");
		this.cnpj = cnpj;
		this.ie = ie;
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

	public Cep getEndereco() {
		return cep;
	}

	public void setEndereco(Cep cep) {
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

	

	public static boolean validarCNPJ(String cnpj) {
	    cnpj = cnpj.replace(".", "").replace("-", "").replace("/", ""); // Remove caracteres especiais
	    if (cnpj.length() != 14) { // Verifica se possui 14 dígitos
	        return false;
	    }

	    // Calcula o primeiro dígito verificador
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

	    // Calcula o segundo dígito verificador
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

	    // Verifica se os dígitos calculados são iguais aos dígitos informados
	    return Integer.parseInt(cnpj.substring(12, 13)) == digito1 && Integer.parseInt(cnpj.substring(13, 14)) == digito2;
	}


}
