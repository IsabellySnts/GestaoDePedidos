package com.cantinagomes.gestaopedidos.model;

import lombok.Data;

@Data
public class Erro {

	private int statusCode;
	private String mensagem;
	private String exception;
}
