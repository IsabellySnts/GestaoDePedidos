package com.cantinagomes.gestaopedidos.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cantinagomes.gestaopedidos.model.Produto;

public interface RepositoryProduto extends PagingAndSortingRepository<Produto, Long>{

	//metodo para buscar o produto pelo nome 
	public Optional<Produto> findBynome (String nome);
	
	public Optional<Produto> findByPreco (int preco);
	
	public Optional<Produto> findByCategoria (String categoria);
}
