package com.cantinagomes.gestaopedidos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produto {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idProduto;
	private String nome;
	private double preco;
	private String imagem;
	@ManyToOne
	private Categoria categoria;
	
}
