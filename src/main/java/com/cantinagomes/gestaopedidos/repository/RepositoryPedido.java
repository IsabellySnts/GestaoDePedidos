package com.cantinagomes.gestaopedidos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cantinagomes.gestaopedidos.model.Pedido;

public interface RepositoryPedido extends PagingAndSortingRepository<Pedido, Long> {

}
