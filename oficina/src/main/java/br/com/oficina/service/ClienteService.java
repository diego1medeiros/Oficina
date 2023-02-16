package br.com.oficina.service;

import java.util.List;

import br.com.oficina.entity.Cliente;

public interface ClienteService {

	public Cliente cadastrarCliente(Cliente cliente);
	
	public Cliente editarCliente(Cliente cliente);
	
	public Cliente removerCliente(Cliente cliente);
	
	public List<Cliente>buscarDadosDosClientes(); 
	
	public List <Cliente> buscarDadosDosClientes(Cliente cliente);
	
	
}
