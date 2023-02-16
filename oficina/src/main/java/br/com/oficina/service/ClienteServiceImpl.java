package br.com.oficina.service;

import java.util.List;

import br.com.oficina.dao.ClienteDAO;
import br.com.oficina.entity.BonusCliente;
import br.com.oficina.entity.Cliente;
import br.com.oficina.entity.Veiculo;




public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO = new ClienteDAO();

	@Override
	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteDAO.cadastrarCliente(cliente);

	}

	@Override
	public Cliente editarCliente(Cliente cliente) {
		return clienteDAO.editarCliente(cliente);
	}

	@Override
	public Cliente removerCliente(Cliente cliente) {
		return clienteDAO.removerCliente(cliente);
	}

	@Override
	public List<Cliente> buscarDadosDosClientes() {
		return clienteDAO.buscarDadosDosClientes();
	}

	@Override
	public List<Cliente> buscarDadosDosClientes(Cliente cliente) {
		return clienteDAO.buscarDadosDosClientes(cliente);
	}

	

}
