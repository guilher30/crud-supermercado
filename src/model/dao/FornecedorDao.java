package model.dao;

import model.entities.Cep;
import model.entities.Fornecedor;

public interface FornecedorDao {

	void insert(Fornecedor obj, String numeroCep);

	void update(Fornecedor obj,Integer id, String cep);

	void deleteById(Integer id);

	Fornecedor findById(Integer id);

	String findAll();
	
	
}
