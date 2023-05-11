package br.com.oficina.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.oficina.entity.Cliente;
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

	public String cadastrarCliente() {
		try {
			if (cliente.getIdCliente() == null) {
				clienteServiceImpl.cadastrarCliente(cliente);
				carregarCliente();
				limpar();
				Message.info("Cliente cadastrado com Sucesso!!!");
			} else {
				clienteServiceImpl.editarCliente(cliente);
				carregarCliente();
				limpar();
				Message.info("Cliente atualizado com Sucesso!!!");

			}
		} catch (Exception e) {
			Message.erro("Cliente não foi cadastrado!!!");
		}
		return null;
	}

	public void excluirCliente(Cliente cliente) {
		try {
			clienteServiceImpl.removerCliente(cliente);
			carregarCliente();
			limpar();
			Message.erro("Cliente excluido com Sucesso!!!");

		} catch (Exception e) {
			Message.erro("Cliente não foi excluido!!!");
		}
	}

	public void pesquisarCliente() {
		clientes = clienteServiceImpl.buscarDadosDosClientes(cliente);
		limpar();
		if (!clientes.isEmpty()) {
			Message.info("Cliente foi encontrado !!!");
		} else {
			Message.info("Cliente não foi encontrado !!!");
		}
	}

	@PostConstruct
	public void carregarCliente() {
		clientes = clienteServiceImpl.buscarDadosDosClientes();

	}

	public Cliente imprimir() {

		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/relatorioClientes.jrxml",
				clienteServiceImpl.buscarDadosDosClientes());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return cliente;

	}

	public Cliente gerarPdf() {
		String pathJasper = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioClientes.jasper";
		String saida = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioClientes.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, clienteServiceImpl.buscarDadosDosClientes(), saida);
		Message.info("Download Reliazada com Sucesso!!!");
		return cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
