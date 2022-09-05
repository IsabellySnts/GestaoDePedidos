package com.cantinagomes.gestaopedidos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import com.cantinagomes.gestaopedidos.util.HashUtil;


import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long idCliente;
	@NotEmpty
	private String nome;
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;
	private String identificador;
	@NotEmpty
	private String senha;
	private TipoCliente tipoCliente;
	
	
	public void setSenha(String senha) {
		this.senha = HashUtil.hash256(senha);
	}
	
	
	
}
