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

	public List<Veiculo> buscarDadosDosVeiculos(Veiculo veiculo) {
		return veiculoServiceImpl.buscarDadosDosVeiculos(veiculo);
	}

	public Veiculo cadastrarVeiculo(Veiculo veiculo) {
		return veiculoServiceImpl.cadastrarVeiculo(veiculo);
	}

	public Veiculo editarVeiculo(Veiculo veiculo) {
		return veiculoServiceImpl.editarVeiculo(veiculo);
	}

	public List<Veiculo> buscarDadosDosVeiculos() {
		return veiculoServiceImpl.buscarDadosDosVeiculos();
	}

	public Veiculo removerVeiculo(Veiculo veiculo) {
		return veiculoServiceImpl.removerVeiculo(veiculo);
	}

	public List<Veiculo> buscarVeiculos(String nomeCliente) {
		return veiculoServiceImpl.buscarVeiculos(nomeCliente);
	}

	public Veiculo buscarVeiculoPorId(Long id) {
		return veiculoServiceImpl.buscarVeiculoPorId(id);
	}

	public Veiculo excluirBonusVeiculoPorId(Long id) {
		return veiculoServiceImpl.excluirBonusVeiculoPorId(id);
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String cadastrarVeiculo() {
		try {

			if (veiculo.getIdVeiculo() == null) {
				cadastrarVeiculo(veiculo);
				carregarVeiculo();
				limpar();
				Message.info("Veiculo cadastrado com Sucesso!!!");
			} else {
				updateVeiculo(veiculo);
				carregarVeiculo();
				limpar();

			}
		} catch (Exception e) {
			Message.erro("Veiculo não foi cadastrado!!!");
		}
		return null;
	}

	public void excluirVeiculo(Veiculo veiculo) {
		try {
			removerVeiculo(veiculo);
			carregarVeiculo();
			limpar();
			Message.info("Veiculo excluido com Sucesso!!!");

		} catch (Exception e) {
			Message.erro("Veiculo não foi excluido!!!");
		}
	}

	public void updateVeiculo(Veiculo veiculo) {
		try {
			editarVeiculo(veiculo);
			carregarVeiculo();
			limpar();
			Message.info("Veiculo atualizado com Sucesso!!!");

		} catch (Exception e) {
			Message.erro("Veiculo não foi atualizado!!!");
		}
	}

	public void pesquisarVeiculo() {
		System.out.println(veiculo.getCliente().getNome());
		veiculos = buscarDadosDosVeiculos(veiculo);
		limpar();
		if (!veiculos.isEmpty()) {
			Message.info("Veiculo foi encontrado !!!");
		} else {
			Message.info("Veiculo não foi encontrado !!!");
		}
	}

	@PostConstruct
	public void carregarVeiculo() {
		veiculos = buscarDadosDosVeiculos();
	
	}

	public List<SelectItem> getLista() {
		ClienteController clienteController = new ClienteController();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Cliente> clientes = clienteController.buscarDadosDosClientes();
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
				buscarDadosDosVeiculos());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return veiculo;
	}

	public Veiculo gerarPdf() {
		String pathJasper = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioVeiculos.jasper";
		String saida = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioVeiculos.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, buscarDadosDosVeiculos(), saida);
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