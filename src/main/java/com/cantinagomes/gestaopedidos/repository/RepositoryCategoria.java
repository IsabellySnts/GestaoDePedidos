package com.cantinagomes.gestaopedidos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.cantinagomes.gestaopedidos.model.Categoria;
import com.cantinagomes.gestaopedidos.model.Produto;

public interface RepositoryCategoria extends PagingAndSortingRepository<Categoria, Long>{


	@Query("SELECT c FROM Produto c WHERE UPPER(TRIM(c.categoria)) LIKE %?1%")
	public List<Produto> buscarPorCategoria (@Param("c") String nomeCat);


}
