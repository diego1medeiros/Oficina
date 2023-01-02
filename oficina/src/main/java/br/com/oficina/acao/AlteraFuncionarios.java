package br.com.oficina.acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.oficina.controller.FuncionarioController;
import br.com.oficina.entity.Funcionario;

public class AlteraFuncionarios implements Acao {

	
	private Funcionario funcionario = new Funcionario();

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String funcao = request.getParameter("funcao");
		String nomeFuncionario = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String cpf = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String paramId = request.getParameter("id");
		Long id = Long.valueOf(paramId);

		FuncionarioController funcionarioController = new FuncionarioController();

		 funcionarioController.editarFuncionario(
				funcionario = new Funcionario(id,funcao, nomeFuncionario, cpf, telefone, email, endereco, login, senha));

		return "redirect:entrada?acao=ListaFuncionarios";

	}
}
