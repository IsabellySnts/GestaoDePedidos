package com.cantinagomes.gestaopedidos.restcontroller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantinagomes.gestaopedidos.model.Erro;
import com.cantinagomes.gestaopedidos.model.ItemPedido;
import com.cantinagomes.gestaopedidos.model.Pedido;
import com.cantinagomes.gestaopedidos.repository.RepositoryPedido;

@RequestMapping("api/pedidos")
@RestController
@CrossOrigin
public class PedidoRestController {

	@Autowired
	RepositoryPedido repository;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPedido (Pedido pedido){
		
	try {
		
			
			for(ItemPedido item: pedido.getItens()) {
				item.setPedido(pedido);

			}
		
			repository.save(pedido);
			return ResponseEntity.created(URI.create("/" + pedido.getIdPedido())).body(pedido);
			

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Houve um erro na hora de cadastrar o pedido.");
			erro.setException(e.getClass().getName());
			
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	
		
	
	
}
