package br.com.oficina.service;

import java.util.List;

import br.com.oficina.entity.BonusCliente;
import br.com.oficina.entity.Cliente;
import br.com.oficina.entity.Veiculo;




public interface ClienteService {

	public Cliente cadastrarCliente(Cliente cliente);
	
	public Cliente editarCliente(Cliente cliente);
	
	public Cliente removerCliente(Cliente cliente);
	
	public List<Cliente>buscarDadosDosClientes(); 
	
	public List <Cliente> buscarDadosDosClientes(Cliente cliente);
	
	public BonusCliente cadastrarBonusCliente(BonusCliente bonusCliente , Cliente cliente, Veiculo veiculo);
	
	public List<BonusCliente> buscarBonusDosClientes();
	
	public BonusCliente removerBonus(BonusCliente bonusCliente) ;		
}
