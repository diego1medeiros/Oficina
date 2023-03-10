package br.com.oficina.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import br.com.oficina.service.FuncionarioServiceImpl;
import br.com.oficina.service.ServicoServiceImpl;
import br.com.oficina.service.VeiculoServiceImpl;
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

	public String cadastrarServico() {
		try {
			if (servico.getIdServico() == null) {
				servicoServiceImpl.cadastrarServico(servico);
				carregarServicos();
				limpar();
				Message.info("Serviço Cadastro com Sucesso!!!");
			} else {
				servicoServiceImpl.editarServico(servico);
				carregarServicos();
				limpar();
				Message.info("Serviço atualizado com Sucesso!!!");
			}
		} catch (Exception e) {
			Message.erro(" Serviço não foi Cadastro!!!");
		}
		return null;
	}

	public void excluirServico(Servico servico) {
		try {
			servicoServiceImpl.removerServico(servico);
			carregarServicos();
			Message.erro("Serviço excluido com Sucesso!!!");
		} catch (Exception e) {
			Message.erro(" Serviço não foi excluido!!!");
		}
	}

	public void pesquisarServico() {
		servicos = servicoServiceImpl.buscarDadosDosServicos(servico);
		limpar();
		if (!servicos.isEmpty()) {
			Message.info("Serviço foi encontrado !!!");
		} else {
			Message.info("Serviço não foi encontrado !!!");
		}
	}

	public void pesquisarServicoPorFuncioanario() {

		servicoPorFuncionario = servicoServiceImpl.buscarServicosPorNomeFuncionario(servico.getFuncionario().getNome());
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
		servicos = servicoServiceImpl.buscaDadosdosServicos();
	}

	public List<ServicoVo> getQtdServicosPorFuncionario() {
		return qtdServicos = servicoServiceImpl.buscaQdtDosServicosPorFuncionarios();
	}

	public void buscarServicosPorData() {
		servicosPorData = servicoServiceImpl.relatorioValorTotalDeServicos(servico);
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
		FuncionarioServiceImpl funcionarioServiceImpl = new FuncionarioServiceImpl();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Funcionario> funcionarios = funcionarioServiceImpl.buscarDadosDosFuncionarios();
		for (Funcionario funcionario : funcionarios) {
			list.add(new SelectItem(funcionario.getIdFuncionario(), funcionario.getNome()));
		}
		return list;
	}

	public List<SelectItem> getModelo() {
		VeiculoServiceImpl veiculoServiceImpl = new VeiculoServiceImpl();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Veiculo> veiculos = veiculoServiceImpl.buscarDadosDosVeiculos();
		for (Veiculo veiculo : veiculos) {
			list.add(new SelectItem(veiculo.getIdVeiculo(), veiculo.getModelo()));
		}
		return list;
	}

	public Servico ordemServico(Servico servico) {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/ordemDeServico.jrxml",
				servicoServiceImpl.buscarServicosPorNomeFuncionario(servico.getFuncionario().getNome()));
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirRelatorioServico() {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/relatorioServico.jrxml",
				servicoServiceImpl.buscaDadosdosServicos());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirServicoPorFuncionario() {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/teste.jrxml",
				servicoServiceImpl.buscaQdtDosServicosPorFuncionarios());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirServicoPorData() {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/valorTotalServicos.jrxml",
				servicosPorData);
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirServicoPeloFuncioanrio() {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/relatorioServico.jrxml",
				servicoPorFuncionario);
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico imprimirContrato(Servico servico) {
		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/cartaDeServico.jrxml",
				servicoServiceImpl.buscarServicosPorNomeFuncionario(servico.getFuncionario().getNome()));
		Message.info("Impressão Reliazada com Sucesso!!!");
		return servico;
	}

	public Servico gerarPdf() {
		String pathJasper = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioServico.jasper";
		String saida = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioServico.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, servicoServiceImpl.buscaDadosdosServicos(), saida);
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

	public String formatarPagamento(Servico servico) {
		return Utils.formatarPagamento(servico.getPago());
	}

	public String formatarPagamentoVo(ServicoVo servicoVo) {
		return Utils.formatarPagamento(servicoVo.getPago());
	}
}
