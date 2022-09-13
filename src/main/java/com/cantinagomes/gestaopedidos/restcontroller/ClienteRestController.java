package com.cantinagomes.gestaopedidos.restcontroller;

import java.net.URI;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cantinagomes.gestaopedidos.model.Cliente;
import com.cantinagomes.gestaopedidos.model.Erro;
import com.cantinagomes.gestaopedidos.repository.RepositoryCliente;


@RequestMapping("api/clientes")
@RestController
public class ClienteRestController {
	
	@Autowired
	private RepositoryCliente repositoryCliente;
	
	
	//metodo para criar um usuario cliente
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create (@Valid @RequestBody Cliente cliente) {
		
		
		
		try {
			
			repositoryCliente.save(cliente);
			
			return ResponseEntity.created(URI.create("/"+cliente.getIdCliente())).body(cliente);
			
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Parece que já existe um email como este!");
			erro.setException(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//metodo para atualizar
	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> update (@RequestBody Cliente cliente, @PathVariable("idCliente") Long id ){
	
		if(!repositoryCliente.existsById(id)) {
			
			return ResponseEntity.notFound().build();
		}else {
			
			cliente.setIdCliente(id);
			repositoryCliente.save(cliente);
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	
	//metodo para deletar um cliente
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Void> delete (@PathVariable ("idCliente") Long id){
		
		if(!repositoryCliente.existsById(id)) {
			
			return ResponseEntity.notFound().build();
			
		}else {
			
			repositoryCliente.deleteById(id);
			
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@GetMapping(value = "/{nome}")
	public ResponseEntity<Cliente> findByNome (@PathVariable("nome") String nome ){
		
		Optional<Cliente> cliente = repositoryCliente.findByNome(nome);
		
		if(cliente.isPresent()) {
			
			return ResponseEntity.ok(cliente.get());
		}else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	
	

}
