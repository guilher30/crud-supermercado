package model.dao;

import db.DB;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.FuncionarioDaoJDBC;


public class DaoFactory {
	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoJDBC(DB.getConnection());
	}

	public static FornecedorDao createFornecedorDao() {
		 return new FornecedorDaoJDBC(DB.getConnection());
	}
}
