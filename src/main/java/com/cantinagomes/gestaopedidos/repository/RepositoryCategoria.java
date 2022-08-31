package com.cantinagomes.gestaopedidos.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cantinagomes.gestaopedidos.model.Categoria;

public interface RepositoryCategoria extends PagingAndSortingRepository<Categoria, Long>{

	public Optional<Categoria> findByCategoria (String cat);
}
