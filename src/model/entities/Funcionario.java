package model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.swing.JOptionPane;

public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String rg;
	private String cpf;

	public Funcionario() {
		super();
	}

	public Funcionario(Integer id, String nome, String rg, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		while (!validarCPF(cpf)) {
			cpf = JOptionPane.showInputDialog("CPF Invalido! Digite novamente: ");
		}
		this.cpf = cpf;

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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
		Funcionario other = (Funcionario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", rg=" + rg + ", cpf=" + cpf + "]";
	}

	public boolean validarCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
			return false;
		}
		// Calcula o primeiro d�gito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * (10 - i);
		}
		int digito1 = 11 - (soma % 11);
		if (digito1 > 9) {
			digito1 = 0;
		}
		// Calcula o segundo d�gito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += (cpf.charAt(i) - '0') * (11 - i);
		}
		int digito2 = 11 - (soma % 11);
		if (digito2 > 9) {
			digito2 = 0;
		}
		// Verifica se os d�gitos verificadores est�o corretos
		return cpf.substring(9).equals(Integer.toString(digito1) + Integer.toString(digito2));
	}

}
