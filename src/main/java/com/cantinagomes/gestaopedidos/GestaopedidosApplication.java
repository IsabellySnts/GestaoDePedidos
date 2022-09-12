package com.cantinagomes.gestaopedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GestaopedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaopedidosApplication.class, args);
	}

}
