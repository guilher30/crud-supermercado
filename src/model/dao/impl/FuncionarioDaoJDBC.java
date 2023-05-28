package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {
	private Connection conn;

	public FuncionarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Funcionario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO funcionario " + "(Nome, Rg, Cpf) " + "VALUES " + "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getRg());
			st.setString(3, obj.getCpf());
			if (obj.validarCPF(obj.getCpf())) {
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
			} else {
				System.out.println("CPF invalido");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Funcionario obj, Integer id) {
		FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
		Funcionario f = funcionarioDao.findById(id);

		PreparedStatement st = null;
		if (f != null) {
			try {
				st = conn.prepareStatement("UPDATE funcionario " + "SET Nome = ?, Rg = ?, Cpf = ?" + "WHERE Id = ?");
				st.setString(1, obj.getNome());
				st.setString(2, obj.getRg());
				st.setString(3, obj.getCpf());
				st.setInt(4, id);

				st.executeUpdate();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(st);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum funcionario com o Id = " + id + " foi encontrado", "",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void deleteById(Integer id) {
		FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
		Funcionario obj = funcionarioDao.findById(id);
		PreparedStatement st = null;
		if (obj != null) {
			try {
				st = conn.prepareStatement("DELETE FROM funcionario " + "WHERE Id = ?");

				st.setInt(1, id);
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Funcionario deletado", "", JOptionPane.WARNING_MESSAGE);

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(st);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Funcionario não encontrado", "", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT funcionario.* " + "FROM funcionario " + "WHERE funcionario.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Funcionario obj = instantiateFuncionario(rs);
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

	private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
		Funcionario obj = new Funcionario();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setRg(rs.getString("Rg"));
		obj.setCpf(rs.getString("Cpf"));
		return obj;
	}

	@Override
	public String findAll() {

		StringBuilder sb = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT funcionario.* " + "FROM funcionario  " + "ORDER BY Nome");

			rs = st.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			while (rs.next()) {
				Funcionario obj = instantiateFuncionario(rs);
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

}
