package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.entities.Cep;
import model.entities.Fornecedor;
import model.entities.Funcionario;

public class FornecedorDaoJDBC implements FornecedorDao {
	private Connection conn;

	public FornecedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Fornecedor obj, String numeroCep) {
		PreparedStatement st = null;
		Cep cep = buscarCep(numeroCep);
		if (cep != null) {
			try {
				st = conn.prepareStatement("INSERT INTO fornecedor " + "(Nome, Cnpj, Ie, Cep_id, numero, complemento) "
						+ "VALUES " + "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				st.setString(1, obj.getNome());
				st.setString(2, obj.getCnpj());
				st.setString(3, obj.getIe());
				st.setInt(4, cep.getId());
				st.setInt(5, obj.getNumero());
				st.setString(6, obj.getComplemento());

				int rowsAffected = st.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
						DB.closeResultSet(rs);
					}
				} else {
					throw new DbException("Erro inesperado, nenhuma linha foi alterada");
				}

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(st);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Cep não encontrado no banco de dados");
		}
	}

	@Override
	public void update(Fornecedor obj,Integer id) {
		
		
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE fornecedor " 
					+ "SET Nome = ?, Cnpj = ?, Ie = ?, " 
				 + "numero = ?,  complemento = ? WHERE Id = ?");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCnpj());
			st.setString(3, obj.getIe());
		
			st.setInt(4, obj.getNumero());
			st.setString(5, obj.getComplemento());
			st.setInt(6, id);
			if (obj.validarCNPJ(obj.getCnpj())) {
				st.executeUpdate();
			} else {
				System.out.println("Cnpj Invalido");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		FornecedorDao funcionarioDao = DaoFactory.createFornecedorDao();
		Fornecedor obj = funcionarioDao.findById(id);
		PreparedStatement st = null;
		if (obj != null) {
			try {
				st = conn.prepareStatement("DELETE FROM fornecedor INNER JOIN cep " + "WHERE Id = ?");

				st.setInt(1, id);
				st.executeUpdate();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(st);
			}
		} else {
			System.out.println("fornecedor com id: " + id + " não encontrado");
		}
	}

	@Override
	public Fornecedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT fornecedor.*, cep.* " + "FROM fornecedor " + "INNER JOIN cep "
					+ "ON fornecedor.Cep_id = cep.Id " + "WHERE fornecedor.Id = ? ORDER BY Nome");
			st.setInt(1, id);
			rs = st.executeQuery();
	
			if (rs.next()) {
				Cep cep = instantiateCep(rs);
				Fornecedor obj = instantiateFornecedor(rs, cep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Fornecedor instantiateFornecedor(ResultSet rs, Cep cep) throws SQLException {
		Fornecedor obj = new Fornecedor();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setCnpj(rs.getString("Cnpj"));
		obj.setIe(rs.getString("Ie"));
		obj.setNumero(rs.getInt("numero"));
		obj.setComplemento(rs.getString("complemento"));
		obj.setCep(cep);

		return obj;
	}

	@Override
	public List<Fornecedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT fornecedor.*, cep.*  " + "FROM fornecedor INNER JOIN cep "
					+ "ON fornecedor.Cep_id = cep.Id " + "ORDER BY Nome");

			rs = st.executeQuery();
			List<Fornecedor> list = new ArrayList<>();
			Map<Integer, Cep> map = new HashMap<>();
			while (rs.next()) {

				Cep cep = map.get(rs.getInt("Cep_id"));
				if (cep == null) {
					cep = instantiateCep(rs);
					map.put(rs.getInt("Cep_id"), cep);
				}

				Fornecedor obj = instantiateFornecedor(rs, cep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Cep instantiateCep(ResultSet rs) throws SQLException {
		Cep cep = new Cep();
		cep.setId(rs.getInt("Id"));
		cep.setNumeroCep(rs.getString("NumeroCep"));
		cep.setUf(rs.getString("Uf"));
		cep.setCidade(rs.getString("Cidade"));
		cep.setBairro(rs.getString("Bairro"));
		cep.setLougradouro(rs.getString("Lougradouro"));
		return cep;
	}

	public Cep buscarCep(String numeroCep) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT cep.* " + "FROM cep " + "WHERE NumeroCep = ?");
			st.setString(1, numeroCep);
			rs = st.executeQuery();
			if (rs.next()) {
				Cep cep = instantiateCep(rs);
				return cep;
			}

			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
