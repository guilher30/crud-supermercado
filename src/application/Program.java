package application;

import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.entities.Fornecedor;

public class Program {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
//		System.out.println("====Teste 1: funcionario findById ======");
//		Funcionario funcionario = funcionarioDao.findById(2);
//		System.out.println(funcionario);
//
//		System.out.println("====Teste 2: funcionario findAll ======");
//
//		List<Funcionario> list = funcionarioDao.findAll();
//		for (Funcionario obj : list) {
//			System.out.println(obj);
//		}
//
////		System.out.println("====Teste 3: funcionario insert ======" );
////		funcionario = new Funcionario(null, "Victor Alves", "314904104","380536400878");
////		funcionarioDao.insert(funcionario);
////		System.out.println("Inserted! New id = " + funcionario.getId());
//
//		// System.out.println("\n==Teste 6 deleção");
//		// System.out.println("Enter the if for delete test");
//		// int id = sc.nextInt();
//		// funcionarioDao.deleteById(id);
//		
//		funcionario = funcionarioDao.findById(1);
//		//funcionario.setNome("Guilherme Moura de Souza");
//		//funcionarioDao.update(funcionario);
//		System.out.println("Update campleted");
		
		FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
		System.out.println(fornecedorDao.findAll());
		Fornecedor f = new Fornecedor(null, "Super Estoque", "46949220000167", "372722681871", null, 52, null);
		//fornecedorDao.insert(f, "04853060");
		//fornecedorDao.deleteById(5);
		System.out.println(fornecedorDao.findById(3));
		
		fornecedorDao.update(f, 4);
	}

}
