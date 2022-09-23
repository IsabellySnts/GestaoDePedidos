package com.cantinagomes.gestaopedidos.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@NotEmpty
	private String nome;
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String identificador;
	private String phone;
	private String senha;
	private TipoCliente tipoCliente;
	private int codigo;
	

	public void setSenha(String senha) {

		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		
		String senhaCriptografada = passwordEncryptor.encryptPassword(senha);
		
		this.senha = senhaCriptografada;

	}

	

	public void setIdentificador(String identificador) {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPasswordCharArray("identificadorcrip".toCharArray());

		String idcrip = textEncryptor.encrypt(identificador);

		System.out.println(idcrip);

		this.identificador = idcrip;

	}
	

	public String getIdentificador() {

		BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

		basicTextEncryptor.setPasswordCharArray("identificadorcrip".toCharArray());

		String iddesc = basicTextEncryptor.decrypt(identificador);

		System.out.println(iddesc);

		return identificador = iddesc;
	}

}
