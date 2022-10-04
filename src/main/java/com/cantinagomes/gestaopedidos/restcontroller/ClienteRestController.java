package com.cantinagomes.gestaopedidos.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cantinagomes.gestaopedidos.model.Cliente;
import com.cantinagomes.gestaopedidos.model.Erro;
import com.cantinagomes.gestaopedidos.repository.RepositoryCliente;

@RequestMapping("api/clientes")
@RestController
@CrossOrigin
public class ClienteRestController {

	@Autowired
	private RepositoryCliente repositoryCliente;

	@Autowired
	JavaMailApp email;

	// metodo para criar um usuario cliente
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@Valid @RequestBody Cliente cliente) {

		try {
	
			Random random = new Random();
			cliente.setCodigo(random.nextInt(1000));
			repositoryCliente.save(cliente);
			email.sendEmail(cliente);
			return ResponseEntity.created(URI.create("/" + cliente.getIdCliente())).body(cliente);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Parece que já existe um email como este!");
			erro.setException(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// metodo para atualizar
	@GetMapping(value="buscarEmail")
	public ResponseEntity<Cliente> verifEmail(@RequestBody Cliente cliente, @PathVariable("email") Long id) {

		List<Cliente> identificadores = repositoryCliente.findByEmail(cliente.getEmail());
		

		for (Cliente clienteident : identificadores) {

			if (clienteident.getEmail().equals(cliente.getEmail())) {

				Random random = new Random();
				cliente.setCodigo(random.nextInt(1000));
				email.sendEmail(cliente);
				repositoryCliente.save(cliente);

			} else {

				return ResponseEntity.notFound().build();
			}

		}
		
		

		return ResponseEntity.ok(cliente);
	}
	
	
	@GetMapping(value="verificarcod")
	public ResponseEntity<Cliente> verifCodigo(@RequestBody Cliente cliente, @PathVariable("codigo") Long id){
		
		//List<Cliente> identificadores = repositoryCliente.findByIdentificador(cliente.getEmail());
		
		List<Cliente> codigoVerificacao  = repositoryCliente.findByCodigo(cliente.getCodigo());
		
		for (Cliente clientecod : codigoVerificacao) {

			if (clientecod.getIdentificador().equals(cliente.getIdentificador()) && clientecod.getCodigo() == cliente.getCodigo()) {

				
				cliente.setIdCliente(id);;
				repositoryCliente.save(cliente);
				
	

			} else {

				return ResponseEntity.notFound().build();
			}

		}
		
		

		return ResponseEntity.ok(cliente);
		
	}

	// metodo para deletar um cliente
	@DeleteMapping( "/{idCliente}" )
	@CrossOrigin
	public ResponseEntity<Void> delete(@PathVariable("idCliente") Long id) {

		if (!repositoryCliente.existsById(id)) {

			return ResponseEntity.notFound().build();

		} else {

			repositoryCliente.deleteById(id);
			

			return ResponseEntity.noContent().build();
		}
	}

	/*@GetMapping(value = "/{nome}")
	public ResponseEntity<Cliente> findByNome(@PathVariable("nome") String nome) {

		Optional<Cliente> cliente = repositoryCliente.findByNome(nome);

		if (cliente.isPresent()) {

			return ResponseEntity.ok(cliente.get());
		} else {

			return ResponseEntity.notFound().build();
		}
	}
	
	*/
	
	@GetMapping(value = "findall")
	public List<Cliente> findSale( Pageable pageable){

		return repositoryCliente.findAll(pageable).getContent();
	}

	

}
