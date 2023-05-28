package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.dao.FuncionarioDao;
import model.entities.Fornecedor;
import model.entities.Funcionario;

public class ApresentacaoDeTela {

	public static void criarMenuPrincipal() {
		JFrame menuPrincipal = new JFrame();
		menuPrincipal.setTitle("Menu Principal");
		menuPrincipal.setSize(300, 300);
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipal.setLayout(null);

		JButton btnCadastrar = new JButton("Cadastrar");
		JButton btnPesquisar = new JButton("Pesquisar");
		JButton btnAtualizar = new JButton("Atualizar");
		JButton btnExcluir = new JButton("Excluir");

		btnCadastrar.setBounds(50, 30, 200, 25);
		btnPesquisar.setBounds(50, 70, 200, 25);
		btnAtualizar.setBounds(50, 110, 200, 25);
		btnExcluir.setBounds(50, 150, 200, 25);

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuCadastrar();
				menuPrincipal.dispose();
			}
		});

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuPesquisar();
				menuPrincipal.dispose();
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuAtualizar();
				menuPrincipal.dispose();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuExcluir();
				menuPrincipal.dispose();
			}
		});

		menuPrincipal.add(btnCadastrar);
		menuPrincipal.add(btnPesquisar);
		menuPrincipal.add(btnAtualizar);
		menuPrincipal.add(btnExcluir);

		menuPrincipal.setVisible(true);
	}

	private static void abrirMenuCadastrar() {
		JFrame menuCadastrar = new JFrame();
		menuCadastrar.setTitle("Menu Cadastrar");
		menuCadastrar.setSize(300, 300);
		menuCadastrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuCadastrar.setLayout(null);

		JButton btnCadastrarFuncionario = new JButton("Funcionário");
		JButton btnCadastrarFornecedor = new JButton("Fornecedor");
		JButton btnVoltar = new JButton("Voltar");

		btnCadastrarFuncionario.setBounds(50, 30, 200, 25);
		btnCadastrarFornecedor.setBounds(50, 70, 200, 25);
		btnVoltar.setBounds(50, 110, 200, 25);

		btnCadastrarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioCadastrarFuncionario();
				menuCadastrar.dispose();
			}
		});

		btnCadastrarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioCadastrarFornecedor();
				menuCadastrar.dispose();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarMenuPrincipal();
				menuCadastrar.dispose();
			}
		});

		menuCadastrar.add(btnCadastrarFuncionario);
		menuCadastrar.add(btnCadastrarFornecedor);
		menuCadastrar.add(btnVoltar);

		menuCadastrar.setVisible(true);
	}

	private static void abrirFormularioCadastrarFuncionario() {
		JFrame formularioCadastrarFuncionario = new JFrame();
		formularioCadastrarFuncionario.setTitle("Formulário Cadastrar Funcionário");
		formularioCadastrarFuncionario.setSize(300, 250);
		formularioCadastrarFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioCadastrarFuncionario.setLayout(new GridLayout(5, 2));

		JTextField nomeField = new JTextField();
		JTextField rgField = new JTextField();
		JTextField cpfField = new JTextField();
		JButton btnCadastrar = new JButton("Cadastrar");
		JButton btnVoltar = new JButton("Voltar");

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = nomeField.getText();
				String rg = rgField.getText();
				String cpf = cpfField.getText();

				FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
				Funcionario f = new Funcionario(null, nome, rg, cpf);
				if (f.validarCPF(cpf) && f.validarRG(rg)) {
					funcionarioDao.insert(f);
					JOptionPane.showMessageDialog(formularioCadastrarFuncionario, "Dados cadastrados com sucesso!");
				} else {
					if (!f.validarCPF(cpf)) {
						JOptionPane.showMessageDialog(null, "CPF INVALIDO", "Validação de Cpf: ",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuCadastrar();
				formularioCadastrarFuncionario.dispose();
			}
		});

		formularioCadastrarFuncionario.add(new JLabel("Nome:"));
		formularioCadastrarFuncionario.add(nomeField);
		formularioCadastrarFuncionario.add(new JLabel("RG:"));
		formularioCadastrarFuncionario.add(rgField);
		formularioCadastrarFuncionario.add(new JLabel("CPF:"));
		formularioCadastrarFuncionario.add(cpfField);
		formularioCadastrarFuncionario.add(btnCadastrar);
		formularioCadastrarFuncionario.add(btnVoltar);

		formularioCadastrarFuncionario.setVisible(true);
	}

	private static void abrirFormularioCadastrarFornecedor() {
		JFrame formularioCadastrarFornecedor = new JFrame();
		formularioCadastrarFornecedor.setTitle("Formulário Cadastrar Fornecedor");
		formularioCadastrarFornecedor.setSize(300, 300);
		formularioCadastrarFornecedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioCadastrarFornecedor.setLayout(new GridLayout(7, 2));

		JTextField nomeField = new JTextField();
		JTextField cnpjField = new JTextField();
		JTextField inscricaoEstadualField = new JTextField();
		JTextField cepField = new JTextField();
		JTextField numeroField = new JTextField();
		JTextField complementoField = new JTextField();
		JButton btnCadastrar = new JButton("Cadastrar");
		JButton btnVoltar = new JButton("Voltar");

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = nomeField.getText();
				String cnpj = cnpjField.getText();
				String inscricaoEstadual = inscricaoEstadualField.getText();
				String cep = cepField.getText();
				String numeroTxt = numeroField.getText();
				int numero = Integer.parseInt(numeroTxt);

				String complemento = complementoField.getText();

				FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
				Fornecedor f = new Fornecedor(null, nome, cnpj, inscricaoEstadual, null, numero, complemento);
				if (Fornecedor.validarCNPJ(cnpj)) {
					fornecedorDao.insert(f, cep);
				} else {
					JOptionPane.showMessageDialog(null, "CNPJ INVALIDO", "Validação de CNPJ: ",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuCadastrar();
				formularioCadastrarFornecedor.dispose();
			}
		});

		formularioCadastrarFornecedor.add(new JLabel("Nome:"));
		formularioCadastrarFornecedor.add(nomeField);
		formularioCadastrarFornecedor.add(new JLabel("CNPJ:"));
		formularioCadastrarFornecedor.add(cnpjField);
		formularioCadastrarFornecedor.add(new JLabel("Inscrição Estadual:"));
		formularioCadastrarFornecedor.add(inscricaoEstadualField);
		formularioCadastrarFornecedor.add(new JLabel("CEP:"));
		formularioCadastrarFornecedor.add(cepField);
		formularioCadastrarFornecedor.add(new JLabel("Número:"));
		formularioCadastrarFornecedor.add(numeroField);
		formularioCadastrarFornecedor.add(new JLabel("Complemento:"));
		formularioCadastrarFornecedor.add(complementoField);
		formularioCadastrarFornecedor.add(btnCadastrar);
		formularioCadastrarFornecedor.add(btnVoltar);

		formularioCadastrarFornecedor.setVisible(true);
	}

	private static void abrirMenuPesquisar() {
		JFrame menuPesquisar = new JFrame();
		menuPesquisar.setTitle("Menu Pesquisar");
		menuPesquisar.setSize(300, 200);
		menuPesquisar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuPesquisar.setLayout(null);

		JButton btnBuscarTodos = new JButton("Buscar Todos");
		JButton btnBuscarPorId = new JButton("Buscar por ID");
		JButton btnVoltar = new JButton("Voltar");

		btnBuscarTodos.setBounds(50, 30, 200, 25);
		btnBuscarPorId.setBounds(50, 70, 200, 25);
		btnVoltar.setBounds(50, 110, 200, 25);

		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuBuscarTodos();
				menuPesquisar.dispose();
			}
		});

		btnBuscarPorId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuBuscarPorId();
				menuPesquisar.dispose();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarMenuPrincipal();
				menuPesquisar.dispose();
			}
		});

		menuPesquisar.add(btnBuscarTodos);
		menuPesquisar.add(btnBuscarPorId);
		menuPesquisar.add(btnVoltar);

		menuPesquisar.setVisible(true);
	}

	private static void abrirMenuBuscarTodos() {
		JFrame menuBuscarTodos = new JFrame();
		menuBuscarTodos.setTitle("Menu Buscar Todos");
		menuBuscarTodos.setSize(300, 200);
		menuBuscarTodos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuBuscarTodos.setLayout(null);

		JButton btnBuscarFuncionarios = new JButton("Funcionários");
		JButton btnBuscarFornecedores = new JButton("Fornecedores");
		JButton btnVoltar = new JButton("Voltar");

		btnBuscarFuncionarios.setBounds(50, 30, 200, 25);
		btnBuscarFornecedores.setBounds(50, 70, 200, 25);
		btnVoltar.setBounds(50, 110, 200, 25);

		btnBuscarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();

				String resultado = funcionarioDao.findAll();

				exibirResultadoPesquisa(resultado);

			}
		});

		btnBuscarFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();

				String resultado = fornecedorDao.findAll();

				exibirResultadoPesquisa(resultado);
				menuBuscarTodos.dispose();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuPesquisar();
				menuBuscarTodos.dispose();
			}
		});

		menuBuscarTodos.add(btnBuscarFuncionarios);
		menuBuscarTodos.add(btnBuscarFornecedores);
		menuBuscarTodos.add(btnVoltar);

		menuBuscarTodos.setVisible(true);
	}

	private static void abrirMenuBuscarPorId() {
		JFrame menuBuscarPorId = new JFrame();
		menuBuscarPorId.setTitle("Menu Buscar por ID");
		menuBuscarPorId.setSize(300, 200);
		menuBuscarPorId.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuBuscarPorId.setLayout(null);

		JButton btnBuscarFuncionario = new JButton("Funcionário");
		JButton btnBuscarFornecedor = new JButton("Fornecedor");
		JButton btnVoltar = new JButton("Voltar");

		btnBuscarFuncionario.setBounds(50, 30, 200, 25);
		btnBuscarFornecedor.setBounds(50, 70, 200, 25);
		btnVoltar.setBounds(50, 110, 200, 25);

		btnBuscarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioBuscarFuncionarioPorId();
				menuBuscarPorId.dispose();
			}
		});

		btnBuscarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioBuscarFornecedorPorId();
				menuBuscarPorId.dispose();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuPesquisar();
				menuBuscarPorId.dispose();
			}
		});

		menuBuscarPorId.add(btnBuscarFuncionario);
		menuBuscarPorId.add(btnBuscarFornecedor);
		menuBuscarPorId.add(btnVoltar);

		menuBuscarPorId.setVisible(true);
	}

	private static void abrirFormularioBuscarFuncionarioPorId() {
		JFrame formularioBuscarFuncionarioPorId = new JFrame();
		formularioBuscarFuncionarioPorId.setTitle("Formulário Buscar Funcionário por ID");
		formularioBuscarFuncionarioPorId.setSize(300, 200);
		formularioBuscarFuncionarioPorId.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioBuscarFuncionarioPorId.setLayout(new GridLayout(3, 2));

		JTextField idField = new JTextField();
		JButton btnBuscar = new JButton("Buscar");
		JButton btnVoltar = new JButton("Voltar");

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();

				Funcionario f = funcionarioDao.findById(id);
				if (f != null) {
					String resultado = f.toString();
					exibirResultadoPesquisa(resultado);
				} else {
					JOptionPane.showMessageDialog(null, "Funcionario não encontrado", "", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuBuscarPorId();
				formularioBuscarFuncionarioPorId.dispose();
			}
		});

		formularioBuscarFuncionarioPorId.add(new JLabel("ID:"));
		formularioBuscarFuncionarioPorId.add(idField);
		formularioBuscarFuncionarioPorId.add(btnBuscar);
		formularioBuscarFuncionarioPorId.add(btnVoltar);

		formularioBuscarFuncionarioPorId.setVisible(true);
	}

	private static void abrirFormularioBuscarFornecedorPorId() {
		JFrame formularioBuscarFornecedorPorId = new JFrame();
		formularioBuscarFornecedorPorId.setTitle("Formulário Buscar Fornecedor por ID");
		formularioBuscarFornecedorPorId.setSize(300, 200);
		formularioBuscarFornecedorPorId.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioBuscarFornecedorPorId.setLayout(new GridLayout(3, 2));

		JTextField idField = new JTextField();
		JButton btnBuscar = new JButton("Buscar");
		JButton btnVoltar = new JButton("Voltar");

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
				Fornecedor f = fornecedorDao.findById(id);
				if (f != null) {
					String resultado = f.toString();
					exibirResultadoPesquisa(resultado);
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuBuscarPorId();
				formularioBuscarFornecedorPorId.dispose();
			}
		});

		formularioBuscarFornecedorPorId.add(new JLabel("ID:"));
		formularioBuscarFornecedorPorId.add(idField);
		formularioBuscarFornecedorPorId.add(btnBuscar);
		formularioBuscarFornecedorPorId.add(btnVoltar);

		formularioBuscarFornecedorPorId.setVisible(true);
	}

	private static void abrirMenuAtualizar() {
		JFrame menuAtualizar = new JFrame();
		menuAtualizar.setTitle("Menu Atualizar");
		menuAtualizar.setSize(300, 200);
		menuAtualizar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuAtualizar.setLayout(null);

		JButton btnAtualizarFuncionario = new JButton("Funcionário");
		JButton btnAtualizarFornecedor = new JButton("Fornecedor");
		JButton btnVoltar = new JButton("Voltar");

		btnAtualizarFuncionario.setBounds(50, 30, 200, 25);
		btnAtualizarFornecedor.setBounds(50, 70, 200, 25);
		btnVoltar.setBounds(50, 110, 200, 25);

		btnAtualizarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioAtualizarFuncionario();
				menuAtualizar.dispose();
			}
		});

		btnAtualizarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioAtualizarFornecedor();
				menuAtualizar.dispose();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarMenuPrincipal();
				menuAtualizar.dispose();
			}
		});

		menuAtualizar.add(btnAtualizarFuncionario);
		menuAtualizar.add(btnAtualizarFornecedor);
		menuAtualizar.add(btnVoltar);

		menuAtualizar.setVisible(true);
	}

	private static void abrirFormularioAtualizarFuncionario() {
		JFrame formularioAtualizarFuncionario = new JFrame();
		formularioAtualizarFuncionario.setTitle("Formulário Atualizar Funcionário");
		formularioAtualizarFuncionario.setSize(300, 250);
		formularioAtualizarFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioAtualizarFuncionario.setLayout(new GridLayout(6, 2));

		JTextField idField = new JTextField();
		JTextField nomeField = new JTextField();
		JTextField rgField = new JTextField();
		JTextField cpfField = new JTextField();
		JButton btnAtualizar = new JButton("Atualizar");
		JButton btnVoltar = new JButton("Voltar");

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				String nome = nomeField.getText();
				String rg = rgField.getText();
				String cpf = cpfField.getText();

				FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();

				Funcionario f = new Funcionario(null, nome, rg, cpf);
				if (f.validarCPF(cpf) && f.validarRG(rg)) {
					funcionarioDao.update(f, id);
					JOptionPane.showMessageDialog(formularioAtualizarFuncionario, "Dados atualizados com sucesso!");
				} else {
					if (!f.validarCPF(cpf)) {
						JOptionPane.showMessageDialog(null, "CPF INVALIDO", "Validação de Cpf: ",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuAtualizar();
				formularioAtualizarFuncionario.dispose();
			}
		});

		formularioAtualizarFuncionario.add(new JLabel("ID:"));
		formularioAtualizarFuncionario.add(idField);
		formularioAtualizarFuncionario.add(new JLabel("Nome:"));
		formularioAtualizarFuncionario.add(nomeField);
		formularioAtualizarFuncionario.add(new JLabel("RG:"));
		formularioAtualizarFuncionario.add(rgField);
		formularioAtualizarFuncionario.add(new JLabel("CPF:"));
		formularioAtualizarFuncionario.add(cpfField);
		formularioAtualizarFuncionario.add(btnAtualizar);
		formularioAtualizarFuncionario.add(btnVoltar);

		formularioAtualizarFuncionario.setVisible(true);
	}

	private static void abrirFormularioAtualizarFornecedor() {
		JFrame formularioAtualizarFornecedor = new JFrame();
		formularioAtualizarFornecedor.setTitle("Formulário Atualizar Fornecedor");
		formularioAtualizarFornecedor.setSize(300, 300);
		formularioAtualizarFornecedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioAtualizarFornecedor.setLayout(new GridLayout(8, 2));

		JTextField idField = new JTextField();
		JTextField nomeField = new JTextField();
		JTextField cnpjField = new JTextField();
		JTextField inscricaoEstadualField = new JTextField();
		JTextField cepField = new JTextField();
		JTextField numeroField = new JTextField();
		JTextField complementoField = new JTextField();
		JButton btnAtualizar = new JButton("Atualizar");
		JButton btnVoltar = new JButton("Voltar");

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				String nome = nomeField.getText();
				String cnpj = cnpjField.getText();
				String inscricaoEstadual = inscricaoEstadualField.getText();
				String cep = cepField.getText();
				String numeroTxt = numeroField.getText();
				int numero = Integer.parseInt(numeroTxt);

				String complemento = complementoField.getText();

				FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
				Fornecedor f = new Fornecedor(null, nome, cnpj, inscricaoEstadual, null, numero, complemento);
				if (Fornecedor.validarCNPJ(cnpj)) {
					fornecedorDao.update(f, id, cep);
					JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso", "UPDATES ",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "CNPJ INVALIDO", "Validação de CNPJ: ",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuAtualizar();
				formularioAtualizarFornecedor.dispose();
			}
		});

		formularioAtualizarFornecedor.add(new JLabel("ID:"));
		formularioAtualizarFornecedor.add(idField);
		formularioAtualizarFornecedor.add(new JLabel("Nome:"));
		formularioAtualizarFornecedor.add(nomeField);
		formularioAtualizarFornecedor.add(new JLabel("CNPJ:"));
		formularioAtualizarFornecedor.add(cnpjField);
		formularioAtualizarFornecedor.add(new JLabel("Inscrição Estadual:"));
		formularioAtualizarFornecedor.add(inscricaoEstadualField);
		formularioAtualizarFornecedor.add(new JLabel("CEP:"));
		formularioAtualizarFornecedor.add(cepField);
		formularioAtualizarFornecedor.add(new JLabel("Número:"));
		formularioAtualizarFornecedor.add(numeroField);
		formularioAtualizarFornecedor.add(new JLabel("Complemento:"));
		formularioAtualizarFornecedor.add(complementoField);
		formularioAtualizarFornecedor.add(btnAtualizar);
		formularioAtualizarFornecedor.add(btnVoltar);

		formularioAtualizarFornecedor.setVisible(true);
	}

	private static void abrirMenuExcluir() {
		JFrame menuExcluir = new JFrame();
		menuExcluir.setTitle("Menu Excluir");
		menuExcluir.setSize(300, 200);
		menuExcluir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuExcluir.setLayout(null);

		JButton btnExcluirFuncionario = new JButton("Funcionário");
		JButton btnExcluirFornecedor = new JButton("Fornecedor");
		JButton btnVoltar = new JButton("Voltar");

		btnExcluirFuncionario.setBounds(50, 30, 200, 25);
		btnExcluirFornecedor.setBounds(50, 70, 200, 25);
		btnVoltar.setBounds(50, 110, 200, 25);

		btnExcluirFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioExcluirFuncionario();
				menuExcluir.dispose();
			}
		});

		btnExcluirFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioExcluirFornecedor();
				menuExcluir.dispose();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarMenuPrincipal();
				menuExcluir.dispose();
			}
		});

		menuExcluir.add(btnExcluirFuncionario);
		menuExcluir.add(btnExcluirFornecedor);
		menuExcluir.add(btnVoltar);

		menuExcluir.setVisible(true);
	}

	private static void abrirFormularioExcluirFuncionario() {
		JFrame formularioExcluirFuncionario = new JFrame();
		formularioExcluirFuncionario.setTitle("Formulário Excluir Funcionário");
		formularioExcluirFuncionario.setSize(300, 100);
		formularioExcluirFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		formularioExcluirFuncionario.setLayout(new GridLayout(2, 2));

		JTextField idField = new JTextField();
		JButton btnExcluir = new JButton("Excluir");
		JButton btnVoltar = new JButton("Voltar");

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
				funcionarioDao.deleteById(id);


			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuExcluir();
				formularioExcluirFuncionario.dispose();
			}
		});

		formularioExcluirFuncionario.add(new JLabel("ID:"));
		formularioExcluirFuncionario.add(idField);
		formularioExcluirFuncionario.add(btnExcluir);
		formularioExcluirFuncionario.add(btnVoltar);

		formularioExcluirFuncionario.setVisible(true);
	}

	private static void abrirFormularioExcluirFornecedor() {
		JFrame formularioExcluirFornecedor = new JFrame();
		formularioExcluirFornecedor.setTitle("Formulário Excluir Fornecedor");
		formularioExcluirFornecedor.setSize(300, 100);
		formularioExcluirFornecedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formularioExcluirFornecedor.setLayout(new GridLayout(2, 2));

		JTextField idField = new JTextField();
		JButton btnExcluir = new JButton("Excluir");
		JButton btnVoltar = new JButton("Voltar");

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());

				FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
				fornecedorDao.deleteById(id);

			
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMenuExcluir();
				formularioExcluirFornecedor.dispose();
			}
		});

		formularioExcluirFornecedor.add(new JLabel("ID:"));
		formularioExcluirFornecedor.add(idField);
		formularioExcluirFornecedor.add(btnExcluir);
		formularioExcluirFornecedor.add(btnVoltar);

		formularioExcluirFornecedor.setVisible(true);
	}

	private static void exibirResultadoPesquisa(String resultado) {
		JFrame frameResultado = new JFrame();
		frameResultado.setTitle("Resultado da Pesquisa");
		frameResultado.setSize(400, 500);
		frameResultado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameResultado.setLayout(new BorderLayout());

		JTextArea resultadoTextArea = new JTextArea(resultado);
		resultadoTextArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(resultadoTextArea);
		frameResultado.add(scrollPane, BorderLayout.CENTER);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameResultado.dispose();
			}
		});
		frameResultado.add(btnFechar, BorderLayout.SOUTH);

		frameResultado.setVisible(true);
	}



	

	


}
