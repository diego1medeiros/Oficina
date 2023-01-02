package br.com.oficina.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.oficina.controller.FuncionarioController;
import br.com.oficina.entity.Funcionario;

/*public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		FuncionarioController funcionarioController = new FuncionarioController();
	      Funcionario funcionario = funcionarioController.isLoginSenhaValida(login, senha);
		
	
		
		if(funcionario!=null) {
			HttpSession sessao = request.getSession();
			
			sessao.setAttribute("usuarioLogado", funcionario);
			return "redirect:entrada?acao=ListaFuncionarios";		
		}else {
			return "redirect:entrada?acao=LoginForm";
		}
	}
}*/
