package com.cantinagomes.gestaopedidos.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cantinagomes.gestaopedidos.model.Cliente;

public interface RepositoryCliente extends PagingAndSortingRepository<Cliente, Long>{

	public Optional<Cliente> findByNome (String nome);
	
	
	
	public List<Cliente> findByEmail(String email);
	
	public List<Cliente> findByCodigo(int i);
}
