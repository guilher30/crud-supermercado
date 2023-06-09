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
			JOptionPane.showMessageDialog(null, "Cep n�o encontrado no banco de dados");
		}
	}

	@Override
	public void update(Fornecedor obj, Integer id, String numeroCep) {
		PreparedStatement st = null;

		FornecedorDao funcionarioDao = DaoFactory.createFornecedorDao();
		Fornecedor f = funcionarioDao.findById(id);
		
		Cep cep = buscarCep(numeroCep);

		if (f != null) {
			
			if (cep != null) {
				obj.setCep(cep);
				try {
					st = conn.prepareStatement("UPDATE fornecedor " + "SET Nome = ?, Cnpj = ?, Ie = ?, Cep_id = ?, "
							+ "numero = ?,  complemento = ? WHERE Id = ?");
					st.setString(1, obj.getNome());
					st.setString(2, obj.getCnpj());
					st.setString(3, obj.getIe());
					st.setInt(4, obj.getCep().getId());
					st.setInt(5, obj.getNumero());
					st.setString(6, obj.getComplemento());
					st.setInt(7, id);

					st.executeUpdate();

				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				} finally {
					DB.closeStatement(st);
				}
			} else {
				JOptionPane.showMessageDialog(null, "CEP n�o encontrado na base de dados", "",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Fornecedor com Id: " + id + " n�o encontrado", "",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void deleteById(Integer id) {
		FornecedorDao funcionarioDao = DaoFactory.createFornecedorDao();
		Fornecedor obj = funcionarioDao.findById(id);
		PreparedStatement st = null;
		if (obj != null) {
			try {
				st = conn.prepareStatement("DELETE FROM fornecedor " + "WHERE Id = ?");

				st.setInt(1, id);
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Fornecedor deletado", "", JOptionPane.WARNING_MESSAGE);

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(st);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Fornecedor n�o encontrado", "", JOptionPane.WARNING_MESSAGE);
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
	public String findAll() {
		StringBuilder sb = new StringBuilder();
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
				sb.append(obj.toString());
			}
			return sb.toString();
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
