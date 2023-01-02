package br.com.oficina.service;

import java.util.List;

import br.com.oficina.Vo.ServicoVo;
import br.com.oficina.entity.Servico;

public interface ServicoService {
	
		
	public Servico cadastrarServico(Servico servico);
	
	public Servico removerServico(Servico servico);
	
	public List<Servico>buscaDadosdosServicos();
	
	public Servico editarServico(Servico servico);
	
	public List<Servico> buscarServicosPorNomeFuncionario(String nomeFuncionario);
	
	public Servico atualizarStatusServico(Servico servico) ;

	public List<Servico> buscarDadosDosServicos(Servico servico);
	
	public List<ServicoVo> buscaQdtDosServicosPorFuncionarios() ;
	
	
	public List<ServicoVo> relatorioValorTotalDeServicos(Servico servico) ;
	
	public List<Servico> relatorioValorTotalDeServicos1(Servico servico) ;


}
