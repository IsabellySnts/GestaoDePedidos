package com.cantinagomes.gestaopedidos.restcontroller;



import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cantinagomes.gestaopedidos.model.Categoria;
import com.cantinagomes.gestaopedidos.model.Erro;
import com.cantinagomes.gestaopedidos.model.Produto;
import com.cantinagomes.gestaopedidos.repository.RepositoryCategoria;


@RequestMapping("/api/categoria")
@RestController
@CrossOrigin
public class CategoriaRestController {

	@Autowired
	private RepositoryCategoria categoriaRepository;
	
	//Método para criar categorias
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> criarCategoria(@RequestBody Categoria categoria){
		 try {
			
			categoriaRepository.save(categoria);
			return ResponseEntity.created(URI.create("/"+categoria.getId())).body(categoria);
			
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			Erro erro = new Erro();
			erro.setStatusCode(500);
			erro.setMensagem("Erro ao criar a categoria, talvez já exista uma categoria com esse nome...");
			erro.setException(e.getClass().getName());
			return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		} 	
	}
	
	
	//Método para editar categorias
	@PutMapping("/{ID}")
	public ResponseEntity<Categoria> editarCategoria(@RequestBody Categoria categoria, @PathVariable("ID") Long idCategoria){
		
		
		//verificando se o id existe
		if(!categoriaRepository.existsById(idCategoria)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		categoria.setId(idCategoria);
		categoriaRepository.save(categoria);
		
		return ResponseEntity.ok(categoria);
	}
	
	
	//Método para excluir categorias
	@RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirCategoria(@PathVariable("ID") Long idCategoria){
		categoriaRepository.deleteById(idCategoria);
		return ResponseEntity.noContent().build();
	}
	

	@GetMapping(value = "findall")
	public Page<Categoria> findCategoria (Pageable pageable){
		
		return categoriaRepository.findAll(pageable);
	}
	

	@GetMapping(value = "/buscarPorCategoria/{nomeCat}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable("nomeCat") String nomeCat){
        List<Produto> acesso = categoriaRepository.buscarPorCategoria(nomeCat.toUpperCase());
        return new ResponseEntity<List<Produto>>(acesso, HttpStatus.OK);    
    
	}
	

}
