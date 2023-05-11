package application;

import javax.swing.JOptionPane;

import model.entities.Fornecedor;
import model.entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		String rg = JOptionPane.showInputDialog("Digite seu rg");
		String cpf = JOptionPane.showInputDialog("Digite seu cpf");
		Funcionario f = new Funcionario(1, "Guilherme", rg, cpf);
		System.out.println(f.toString());

		// String cnpj = JOptionPane.showInputDialog("Digite seu cnpj");
		// Fornecedor f = new Fornecedor();
		// System.out.println(Fornecedor.validarCNPJ(cnpj));

	}

}
