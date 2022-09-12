package com.cantinagomes.gestaopedidos.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produto {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idProduto;
	@NotEmpty
	@Column(unique = true)
	private String nome;
	private double preco;
	private String imagem;
	@ManyToOne
	private Categoria categoria;
	
}
