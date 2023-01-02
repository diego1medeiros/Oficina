package br.com.oficina.service;

import java.util.List;

import br.com.oficina.Vo.ServicoVo;
import br.com.oficina.dao.ServicoDAO;
import br.com.oficina.entity.Servico;

public class ServicoServiceImpl implements ServicoService {

	private ServicoDAO servicoDAO = new ServicoDAO();

	@Override
	public Servico cadastrarServico(Servico servico) {
		return servicoDAO.cadastrarServico(servico);
	}

	@Override
	public Servico removerServico(Servico servico) {
		return servicoDAO.removerServico(servico);
	}

	@Override
	public List<Servico>buscaDadosdosServicos() {
		return servicoDAO.buscaDadosdosServicos();
	}

	@Override
	public Servico editarServico(Servico servico) {
		return servicoDAO.editarServico(servico);
	}

	@Override
	public List<Servico> buscarServicosPorNomeFuncionario(String nomeFuncionario) {
		return servicoDAO.buscarServicosPorNomeFuncionario(nomeFuncionario);
	}

	@Override
	public Servico atualizarStatusServico(Servico servico) {
		return servicoDAO.atualizarStatusServico(servico);
	}

	@Override
	public List<Servico> buscarDadosDosServicos(Servico servico) {
		return servicoDAO.buscarDadosDosServicos(servico);
	}

	@Override
	public List<ServicoVo>buscaQdtDosServicosPorFuncionarios() {
		return servicoDAO.buscaQdtDosServicosPorFuncionarios();
	}


	@Override
	public List<ServicoVo> relatorioValorTotalDeServicos(Servico servico) {
		return servicoDAO.relatorioValorTotalDeServicos(servico);
	}

	@Override
	public List<Servico> relatorioValorTotalDeServicos1(Servico servico) {
		return servicoDAO.relatorioValorTotalDeServicos1(servico);
	}

	

}
