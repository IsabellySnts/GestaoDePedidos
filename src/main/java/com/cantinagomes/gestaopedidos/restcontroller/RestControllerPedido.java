package com.cantinagomes.gestaopedidos.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantinagomes.gestaopedidos.model.Pedido;

@RestController
@RequestMapping("api/pedido")
public class RestControllerPedido {
	
	public String createPedido(Pedido pedido) {
		
		
		return "";
	}

}
