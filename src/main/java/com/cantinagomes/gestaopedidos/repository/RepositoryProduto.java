package com.cantinagomes.gestaopedidos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cantinagomes.gestaopedidos.model.Produto;

public interface RepositoryProduto extends PagingAndSortingRepository<Produto, Long>{

	//metodo para buscar o produto pelo nome 
	public Optional<Produto> findBynome (String nome);
	
	public Optional<Produto> findByPreco (int preco);
	
	public Optional<Produto> findByCategoria (String categoria);
	

	@Query("SELECT c FROM Produto c WHERE UPPER(TRIM(c.categoria)) LIKE %?1%")
	public List<Produto> buscarPorCategoria (@Param("c") String nomeCat);


}
