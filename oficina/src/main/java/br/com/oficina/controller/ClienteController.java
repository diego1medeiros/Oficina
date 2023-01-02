package br.com.oficina.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.oficina.entity.BonusCliente;
import br.com.oficina.entity.Cliente;
import br.com.oficina.entity.Funcionario;
import br.com.oficina.entity.Veiculo;
import br.com.oficina.service.ClienteServiceImpl;
import br.com.oficina.util.Message;
import br.com.oficina.util.RelatorioUtil;

@Named("clientebean")
@SessionScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;
	private ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();
	@Inject
	private Cliente cliente;

	private List<Cliente> clientes = new ArrayList<>();
	private List<Cliente> listaclientes = new ArrayList<>();

	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteServiceImpl.cadastrarCliente(cliente);
	}

	public Cliente editarCliente(Cliente cliente) {
		return clienteServiceImpl.editarCliente(cliente);
	}

	public List<Cliente> buscarDadosDosClientes() {
		return clienteServiceImpl.buscarDadosDosClientes();
	}

	public Cliente removerCliente(Cliente cliente) {
		return clienteServiceImpl.removerCliente(cliente);
	}

	public List<Cliente> buscarDadosDosClientes(Cliente cliente) {
		return clienteServiceImpl.buscarDadosDosClientes(cliente);
	}

	public BonusCliente cadastrarBonusCliente(BonusCliente bonusCliente, Cliente cliente, Veiculo veiculo) {
		return clienteServiceImpl.cadastrarBonusCliente(bonusCliente, cliente, veiculo);
	}

	public List<BonusCliente> buscarBonusDosClientes() {
		return clienteServiceImpl.buscarBonusDosClientes();
	}

	public BonusCliente removerBonus(BonusCliente bonusCliente) {
		return clienteServiceImpl.removerBonus(bonusCliente);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String cadastrarCliente() {
		try {
			if (cliente.getIdCliente() == null) {
				cadastrarCliente(cliente);
				carregarCliente();
				limpar();
				Message.info("Cliente cadastrado com Sucesso!!!");
			} else {
				updateCliente(cliente);
				carregarCliente();
				limpar();

			}
		} catch (Exception e) {
			Message.erro("Cliente não foi cadastrado!!!");
		}
		return null;
	}

	public void excluirCliente(Cliente cliente) {
		try {
			removerCliente(cliente);
			carregarCliente();
			limpar();
			Message.info("Cliente excluido com Sucesso!!!");

		} catch (Exception e) {
			Message.erro("Cliente não foi excluido!!!");
		}
	}

	public void updateCliente(Cliente cliente) {
		try {
			editarCliente(cliente);
			carregarCliente();
			limpar();
			Message.info("Cliente atualizado com Sucesso!!!");

		} catch (Exception e) {
			Message.erro("Cliente não foi atualizado!!!");
		}
	}

	public void pesquisarCliente() {
		clientes = buscarDadosDosClientes(cliente);
		limpar();
		if (!clientes.isEmpty()) {
			Message.info("Cliente foi encontrado !!!");
		} else {
			Message.info("Cliente não foi encontrado !!!");
		}
	}

	@PostConstruct
	public void carregarCliente() {
		clientes = buscarDadosDosClientes();

	}

	public Cliente imprimir() {

		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/relatorioClientes.jrxml",
				buscarDadosDosClientes());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return cliente;

	}

	public Cliente gerarPdf() {
		String pathJasper = "C:\\dev\\worspace\\oficina\\relatorio\\relatorioClientes.jasper";
		String saida = "C:\\dev\\worspace\\oficina\\relatorio\\relatorioClientes.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, buscarDadosDosClientes(), saida);
		Message.info("Download Reliazada com Sucesso!!!");
		return cliente;
	}

	private void limpar() {
		cliente = new Cliente();

	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getListaclientes() {
		return listaclientes;
	}

	public void setListaclientes(List<Cliente> listaclientes) {
		this.listaclientes = listaclientes;
	}

}
