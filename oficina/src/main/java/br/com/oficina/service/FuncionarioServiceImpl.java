package br.com.oficina.service;

import java.util.List;
import javax.inject.Inject;
import br.com.oficina.entity.Funcionario;
import br.com.oficina.dao.FuncionarioDAO;

public class FuncionarioServiceImpl implements FuncionarioService {
	@Inject
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	
	@Override
	public List<Funcionario> buscarDadosDosFuncionarios() {
		return funcionarioDAO.buscarDadosDoFuncionarios();
	}

	@Override
	public Funcionario buscarFuncionarioPeloId(Long id) {
		return funcionarioDAO.buscarFuncionarioPeloId(id);
	}

	@Override
	public Funcionario editarFuncionario(Funcionario funcionario) {
		return funcionarioDAO.editarFuncionario(funcionario);
	}

	@Override
	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		return funcionarioDAO.cadastrarFuncionario(funcionario);
	}

	@Override
	public Funcionario removerFuncionario(Funcionario funcionario) {
		return funcionarioDAO.removerFuncionario(funcionario) ;
	}

	@Override
	public Long removerFuncionario(Long id) {
		return funcionarioDAO.removerFuncionario(id);
	}

	@Override
	public List<Funcionario> buscarDadosDosFuncionarios(Funcionario funcionario) {
		return funcionarioDAO.buscarDadosDosFuncionarios(funcionario);
	}

}
