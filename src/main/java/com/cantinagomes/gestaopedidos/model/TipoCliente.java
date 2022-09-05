package com.cantinagomes.gestaopedidos.model;

public enum TipoCliente {

	funcionario("Funcionário Senai"),
	aluno ("Cliente Aluno");
	
	String desc;
	
	private TipoCliente(String desc) {
		this.desc = desc;
	}
	
}
