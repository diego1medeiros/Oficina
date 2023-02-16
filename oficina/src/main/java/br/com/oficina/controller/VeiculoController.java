package br.com.oficina.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.oficina.entity.Cliente;
import br.com.oficina.entity.Veiculo;
import br.com.oficina.service.ClienteServiceImpl;
import br.com.oficina.service.VeiculoServiceImpl;
import br.com.oficina.util.Message;
import br.com.oficina.util.RelatorioUtil;

@Named("veiculobean")
@SessionScoped
public class VeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private VeiculoServiceImpl veiculoServiceImpl = new VeiculoServiceImpl();
	@Inject
	private Veiculo veiculo;
	@Inject
	private Cliente cliente;

	private List<Veiculo> veiculos = new ArrayList<>();

	private List<Veiculo> listaVeiculos = new ArrayList<>();

	public String cadastrarVeiculo() {
		try {

			if (veiculo.getIdVeiculo() == null) {
				veiculoServiceImpl.cadastrarVeiculo(veiculo);
				carregarVeiculo();
				limpar();
				Message.info("Veiculo cadastrado com Sucesso!!!");
			} else {
				veiculoServiceImpl.editarVeiculo(veiculo);
				carregarVeiculo();
				limpar();
				Message.info("Veiculo atualizado com Sucesso!!!");
			}
		} catch (Exception e) {
			Message.erro("Veiculo não foi cadastrado!!!");
		}
		return null;
	}

	public void excluirVeiculo(Veiculo veiculo) {
		try {
			veiculoServiceImpl.removerVeiculo(veiculo);
			carregarVeiculo();
			limpar();
			Message.erro("Veiculo excluido com Sucesso!!!");

		} catch (Exception e) {
			Message.erro("Veiculo não foi excluido!!!");
		}
	}

	public void pesquisarVeiculo() {
		System.out.println(veiculo.getCliente().getNome());
		veiculos = veiculoServiceImpl.buscarDadosDosVeiculos(veiculo);
		limpar();
		if (!veiculos.isEmpty()) {
			Message.info("Veiculo foi encontrado !!!");
		} else {
			Message.info("Veiculo não foi encontrado !!!");
		}
	}

	@PostConstruct
	public void carregarVeiculo() {
		veiculos = veiculoServiceImpl.buscarDadosDosVeiculos();

	}

	public List<SelectItem> getLista() {
		ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Cliente> clientes = clienteServiceImpl.buscarDadosDosClientes();
		for (Cliente cliente : clientes) {
			list.add(new SelectItem(cliente.getIdCliente(), cliente.getNome()));
		}
		return list;
	}

	private void limpar() {
		veiculo = new Veiculo();
	}

	public Veiculo imprimir() {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/relatorioVeiculos.jrxml",
				veiculoServiceImpl.buscarDadosDosVeiculos());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return veiculo;
	}

	public Veiculo gerarPdf() {
		String pathJasper = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioVeiculos.jasper";
		String saida = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioVeiculos.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, veiculoServiceImpl.buscarDadosDosVeiculos(), saida);
		Message.info("Download Reliazada com Sucesso!!!");
		return veiculo;

	}

	public String[] getMarca() {
		return new String[] { "Volkswagen", "Chevrolet", "Fiat", "Hyundai", "Toyota", "Jeep", "Caoa Chery", "Renault",
				"Nissan", "Honda", "Peugeot", "Ford", "Citroen", "Mitsubishi", "Audi", "BWM", "Volvo", "Mercedes-Benz",
				"JAC Motors", "Kia", "Land Rover", "Suzuki", "RAM", "Porsche" };
	}

	public String[] getTipo() {
		return new String[] { "Carro", "Caminhão", "Ônibus" };
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

}