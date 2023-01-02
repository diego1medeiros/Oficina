package br.com.oficina.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.oficina.controller.FuncionarioController;
import br.com.oficina.entity.Funcionario;

public class ListaFuncionarios implements Acao{

	private FuncionarioController funcionarioController = new FuncionarioController();
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Funcionario> listaFuncionarios = funcionarioController.buscarDadosDosFuncionarios();
		request.setAttribute("funcionarios", listaFuncionarios);
	
	return "forward:listaFuncionarios.jsp";
		
	
		
	}

	
}