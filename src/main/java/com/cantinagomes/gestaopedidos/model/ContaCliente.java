package com.cantinagomes.gestaopedidos.model;

import java.util.List;
import lombok.Data;

@Data
public class ContaCliente {
	
	private Long idPagamento;
	private Cliente cliente;
	private List<ItemPedido> item;
	
}
