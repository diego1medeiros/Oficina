package br.com.oficina.service;

import java.util.List;

import br.com.oficina.entity.Funcionario;


public interface FuncionarioService {

	public List<Funcionario> buscarDadosDosFuncionarios(); 

	public Funcionario buscarFuncionarioPeloId(Long id);
	
	public Funcionario editarFuncionario(Funcionario funcionario) ;
	
	public Funcionario cadastrarFuncionario(Funcionario funcionario);
	
	public Funcionario removerFuncionario(Funcionario funcionario);
	
	public Long removerFuncionario(Long id);
	
	public List<Funcionario> buscarDadosDosFuncionarios(Funcionario funcionario);
}
