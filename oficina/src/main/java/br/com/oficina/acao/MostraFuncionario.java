package br.com.oficina.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.oficina.controller.FuncionarioController;
import br.com.oficina.entity.Funcionario;

public class MostraFuncionario implements Acao{

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String paramId = request.getParameter("id");
		Long id = Long.valueOf(paramId);
		
		FuncionarioController funcionarioController = new FuncionarioController();
        Funcionario funcionario = funcionarioController.buscarFuncionarioPeloId(id);
        request.setAttribute("funcionario", funcionario);
       
		return "forward:formAlteraFuncionario.jsp";

		
		
	}
}
