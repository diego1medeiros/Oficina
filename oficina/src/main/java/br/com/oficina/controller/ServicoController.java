package br.com.oficina.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.oficina.Vo.ServicoVo;
import br.com.oficina.entity.Cliente;
import br.com.oficina.entity.Funcionario;
import br.com.oficina.entity.Servico;
import br.com.oficina.entity.Veiculo;
import br.com.oficina.enumeradores.StatusServicos;
import br.com.oficina.service.ServicoServiceImpl;
import br.com.oficina.util.Message;
import br.com.oficina.util.RelatorioUtil;
import br.com.oficina.util.Utils;

@Named("servicobean")
@SessionScoped
public class ServicoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ServicoServiceImpl servicoServiceImpl = new ServicoServiceImpl();
	@Inject
	private Servico servico;
	@Inject
	private Cliente cliente;
	@Inject
	private Veiculo veiculo;
	@Inject
	private Funcionario funcionario;
	@Inject
	private ServicoVo servicoVo;

	private List<Servico> servicoPorFuncionario = new ArrayList<>();

	private List<Servico> servicos = new ArrayList<>();

	private List<ServicoVo> qtdServicos = new ArrayList<>();

	private List<ServicoVo> servicosPorData = new ArrayList<>();

	private String servicoPago;

	public String getServicoPago() {
		return servicoPago;
	}

	public void setServicoPago(String servicoPago) {
		this.servicoPago = servicoPago;
	}

	public List<Servico> buscarDadosDosServicos(Servico servico) {
		return servicoServiceImpl.buscarDadosDosServicos(servico);
	}

	public Servico atualizarStatusServico(Servico servico) {
		return servicoServiceImpl.atualizarStatusServico(servico);
	}

	public Servico cadastrarServico(Servico servico) {
		return servicoServiceImpl.cadastrarServico(servico);
	}

	public Servico removerServico(Servico servico) {
		return servicoServiceImpl.removerServico(servico);
	}

	public List<Servico> buscaDadosdosServicos() {
		return servicoServiceImpl.buscaDadosdosServicos();
	}

	public Servico editarServico(Servico servico) {
		return servicoServiceImpl.editarServico(servico);
	}

	public List<Servico> buscarServicosPorNomeFuncionario(String nomeFuncionario) {
		return servicoServiceImpl.buscarServicosPorNomeFuncionario(nomeFuncionario);
	}

	public List<ServicoVo> buscaQdtDosServicosPorFuncionarios() {
		return servicoServiceImpl.buscaQdtDosServicosPorFuncionarios();
	}

	public List<ServicoVo> relatorioValorTotalDeServicos(Servico servico) {
		return servicoServiceImpl.relatorioValorTotalDeServicos(servico);
	}

	public String cadastrarServico() {
		try {
			if (servico.getIdServico() == null) {
				cadastrarServico(servico);
				carregarServicos();
				limpar();
				Message.info("Serviço Cadastro com Sucesso!!!");
			} else {
				updateServico(servico);
				carregarServicos();
				limpar();
			}
		} catch (Exception e) {
			Message.erro(" Serviço não foi Cadastro!!!");
		}
		return null;
	}

	public void excluirServico(Servico servico) {
		try {
			removerServico(servico);
			carregarServicos();
			Message.info("Serviço excluido com Sucesso!!!");

		} catch (Exception e) {
			Message.erro(" Serviço não foi excluido!!!");
		}
	}

	public void updateServico(Servico servico) {
		try {
			editarServico(servico);
			carregarServicos();
			Message.info("Serviço atualizado com Sucesso!!!");

		} catch (Exception e) {
			Message.erro(" Serviço não foi atualizado!!!");
		}
	}

	public void pesquisarServico() {
		servicos = buscarDadosDosServicos(servico);
		limpar();
		if (!servicos.isEmpty()) {
			Message.info("Serviço foi encontrado !!!");
		} else {
			Message.info("Serviço não foi encontrado !!!");
		}
	}

	public void pesquisarServicoPorFuncioanario() {

		servicoPorFuncionario = buscarServicosPorNomeFuncionario(servico.getFuncionario().getNome());
		limpar();
		if (!servicoPorFuncionario.isEmpty()) {
			Message.info("Serviço foi encontrado !!!");
		} else {
			Message.info("Serviço não foi encontrado !!!");
		}
	}

	public List<Servico> getServicoPorFuncionario() {
		return servicoPorFuncionario;
	}

	public void setServicoPorFuncionario(List<Servico> servicoPorFuncionario) {
		this.servicoPorFuncionario = servicoPorFuncionario;
	}

	@PostConstruct
	public void carregarServicos() {
		servicos = buscaDadosdosServicos();
	}

	public List<ServicoVo> getQtdServicosPorFuncionario() {
		return qtdServicos = buscaQdtDosServicosPorFuncionarios();
	}

	public void buscarServicosPorData() {
		servicosPorData = relatorioValorTotalDeServicos(servico);
		limpar();
		if (!servicosPorData.isEmpty()) {
			Message.info("Serviço foi encontrado !!!");
		} else {
			Message.info("Serviço não foi encontrado !!!");
		}
	}

	private void limpar() {
		servico = new Servico();
	}

	public List<SelectItem> getLista() {
		FuncionarioController funcionarioController = new FuncionarioController();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Funcionario> funcionarios = funcionarioController.buscarDadosDosFuncionarios();
		for (Funcionario funcionario : funcionarios) {
			list.add(new SelectItem(funcionario.getIdFuncionario(), funcionario.getNome()));
		}
		return list;
	}

	public List<SelectItem> getModelo() {
		VeiculoController controller = new VeiculoController();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Veiculo> veiculos = controller.buscarDadosDosVeiculos();
		for (Veiculo veiculo : veiculos) {
			list.add(new SelectItem(veiculo.getIdVeiculo(), veiculo.getModelo()));
		}
		return list;
	}

	public Servico ordemServico(Servico servico) {
		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/ordemDeServico.jrxml",
				buscarServicosPorNomeFuncionario(servico.getFuncionario().getNome()));
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirRelatorioServico() {
		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/relatorioServico.jrxml",
				buscaDadosdosServicos());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirServicoPorFuncionario() {
		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/teste.jrxml",
				buscaQdtDosServicosPorFuncionarios());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirServicoPorData() {
		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/valorTotalServicos.jrxml", servicosPorData);
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirServicoPeloFuncioanrio() {
		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/relatorioServico.jrxml", servicoPorFuncionario);
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirContrato(Servico servico) {
		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/cartaDeServico.jrxml",
				buscarServicosPorNomeFuncionario(servico.getFuncionario().getNome()));
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico gerarPdf() {
		String pathJasper = "C:\\dev\\worspace\\oficina\\relatorio\\relatorioServico.jasper";
		String saida = "C:\\dev\\worspace\\oficina\\relatorio\\relatorioServico.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, buscaDadosdosServicos(), saida);
		return servico;
	}

	public String[] getDescricao() {
		return new String[] { "Trocar a embreagem", "troca do radiador", "Trocar as três Bobina",
				"Trocar 6 velas e cabos", "Reapertar as juntas dos dois cabeçotes", "limpeza bicos injetores para",
				"Trocar bomba de combustivel", "Trocar filtro combustível", "Trocar válvula reguladora de combustível",
				"Trocar o óleo e filtro" };
	}

	public List<String> getcarregarStatus() {
		return Arrays.asList(StatusServicos.getDescricaoComboBox());
	}

	public boolean isChecked() {
		return servico.getPago() != 0;
	}

	public void setChecked(boolean checked) {
		servico.setPago(checked ? 1 : 0);
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ServicoVo> getQtdServicos() {
		return qtdServicos;
	}

	public void setQtdServicos(List<ServicoVo> qtdServicos) {
		this.qtdServicos = qtdServicos;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public ServicoVo getServicoVo() {
		return servicoVo;
	}

	public void setServicoVo(ServicoVo servicoVo) {
		this.servicoVo = servicoVo;
	}

	public List<ServicoVo> getServicosPorData() {
		return servicosPorData;
	}

	public void setServicosPorData(List<ServicoVo> servicosPorData) {
		this.servicosPorData = servicosPorData;
	}

	/*
	 * public String getServicoPagoFormatado(){ for (Servico servico : servicos) {
	 * servicoPago = Utils.formatarPagamento(servico.getPago()); } return
	 * servicoPago;
	 * 
	 * }
	 */

	public String getFormatarServicoPago() {
		if (servico.getPago() == 1) {
			return "Sim";
		} else {
			return "Não";
		}
	}

}
