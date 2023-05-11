package model.entities;

import java.io.Serializable;

public class Cep  implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	private Integer id;
	private String numeroCep;
	private String uf;
	private String cidade;
	private String bairro;
	private String lougradouro;
}
