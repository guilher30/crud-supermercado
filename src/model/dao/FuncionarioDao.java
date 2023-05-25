package model.dao;

import java.util.List;


import model.entities.Funcionario;

public interface FuncionarioDao {

	void insert(Funcionario obj);

	void update(Funcionario obj, Integer id);

	void deleteById(Integer id);

	Funcionario findById(Integer id);

	List<Funcionario> findAll();
	
	
}
