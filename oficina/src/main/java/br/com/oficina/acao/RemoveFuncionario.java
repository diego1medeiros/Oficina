package br.com.oficina.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.oficina.controller.FuncionarioController;
import br.com.oficina.entity.Funcionario;

public class RemoveFuncionario implements Acao{

	
	private Funcionario funcionario = new Funcionario();

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		Long id = Long.valueOf(paramId);
		funcionario.setIdFuncionario(id);
		FuncionarioController funcionarioController = new FuncionarioController();
		funcionarioController.removerFuncionario(funcionario);
		return "redirect:entrada?acao=ListaFuncionarios";
		
		
	}
}
