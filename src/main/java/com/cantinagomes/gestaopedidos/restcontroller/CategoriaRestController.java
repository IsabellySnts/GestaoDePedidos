package com.cantinagomes.gestaopedidos.restcontroller;


import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.cantinagomes.gestaopedidos.model.Categoria;
import com.cantinagomes.gestaopedidos.model.Erro;
import com.cantinagomes.gestaopedidos.repository.RepositoryCategoria;


@RequestMapping("/api/categoria")
@RestController
public class CategoriaRestController {
	
	
	
	@Autowired
	private RepositoryCategoria categoriaRepository;
	
	//Método para criar categorias
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> criarCategoria(@RequestBody Categoria categoria){
		 try {
			
			categoriaRepository.save(categoria);
			return ResponseEntity.created(URI.create("/"+categoria.getID())).body(categoria);
			
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Erro ao criar a categoria");
			erro.setException(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		} 	
	}
	
	
	//Método para editar categorias
	@RequestMapping(value = "/{ID}", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarCategoria(@RequestBody Categoria categoria, @PathVariable("ID") Long idCategoria){
		
		
		//verificando se está inserindo o id correto
		if(idCategoria != categoria.getID()){
			throw new RuntimeException("ID Inválido!");
		}
		
		categoriaRepository.save(categoria);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(URI.create("/api/categoria"));
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	
	//Método para excluir categorias
	@RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirCategoria(@PathVariable("ID") Long idCategoria){
		categoriaRepository.deleteById(idCategoria);
		return ResponseEntity.noContent().build();
	}
	
	//metodo para buscar categorias
	@RequestMapping(value="/{categoria}", method= RequestMethod.GET)
	public ResponseEntity<Categoria> findCategoria(@PathVariable("categoria") String cat){
		Optional<Categoria> categoria = categoriaRepository.findByCategoria(cat);
		
		//verificando se existe
		if(categoria.isPresent()) {
			
			return ResponseEntity.ok(categoria.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	

}