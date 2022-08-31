package com.cantinagomes.gestaopedidos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idProduto;
	private String nome;
	private int preco;
	private String imagem;
	@ManyToOne
	private Categoria categoria;
	
}
