package com.cantinagomes.gestaopedidos.restcontroller;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cantinagomes.gestaopedidos.model.Erro;
import com.cantinagomes.gestaopedidos.model.Produto;
import com.cantinagomes.gestaopedidos.repository.RepositoryProduto;

@RequestMapping("api/produto")
@RestController
public class ProdutosRestController {

	@Autowired
	private RepositoryProduto produtoRepository;
	
	//método para criar produto
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProduct (@RequestBody Produto produto){
		
		try {
			
			produtoRepository.save(produto);
			
			return ResponseEntity.created(URI.create("/"+produto.getIdProduto())).body(produto);
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Erro ao criar protudo");
			erro.setException(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//metodo para atualizar produto
	@PutMapping("/{idProduto}")
	public ResponseEntity<Produto> updateProduct(@RequestBody Produto produto, @PathVariable("idProduto") Long id){
		
		//verificando se o id existe 
		if(!produtoRepository.existsById(id)) {
			
			return ResponseEntity.notFound().build();
		}else {
			
		 produto.setIdProduto(id);
		 produtoRepository.save(produto);
		}
		
		return ResponseEntity.ok(produto);
	}
	
	
	//método para excluir produto
	@DeleteMapping("/{idProduto}")
	public ResponseEntity<Void> deleteProduct(@PathVariable ("idProduto") Long id){
		
		
		if(!produtoRepository.existsById(id)) {
			
			return ResponseEntity.notFound().build();
		}else {
			
			produtoRepository.deleteById(id);
			
			return ResponseEntity.noContent().build();
		}
	}
	
	
	//buscando produtos pelo nome 
	@GetMapping("/{nome}")
	public ResponseEntity<Produto> findProductByNome (@PathVariable ("nome") String nomeCategoria){
	
		Optional<Produto> produto = produtoRepository.findBynome(nomeCategoria);
		
		if(produto.isPresent()) {
			
			return ResponseEntity.ok(produto.get());
			
		}else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	//buscando produtos pelo preco 
	
	@GetMapping
	public ResponseEntity<Produto> findProductByPreco (@PathVariable ("preco") int preco){
		
		
		Optional<Produto> produto = produtoRepository.findByPreco(preco);
		
		if(produto.isPresent()) {
			
			return ResponseEntity.ok(produto.get());
		}else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Produto> findProductByCategoria (@PathVariable("categoria") String categoria){
		
		Optional<Produto> product = produtoRepository.findByCategoria(categoria);
		
		if(product.isPresent()) {
			
			return ResponseEntity.ok(product.get());
		}else {
			
			return ResponseEntity.notFound().build();
		}
	}
}
