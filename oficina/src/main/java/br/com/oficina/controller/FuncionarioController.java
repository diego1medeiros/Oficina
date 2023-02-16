package br.com.oficina.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.oficina.entity.Funcionario;
import br.com.oficina.service.FuncionarioServiceImpl;
import br.com.oficina.util.Message;
import br.com.oficina.util.RelatorioUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named("bean")
@SessionScoped
public class FuncionarioController implements Serializable {
	private static final long serialVersionUID = 1L;
	private FuncionarioServiceImpl funcionarioServiceImpl = new FuncionarioServiceImpl();

	@Inject
	private Funcionario funcionario;

	private List<Funcionario> funcionarios = new ArrayList<>();

	public String isLoginSenhaValida(String login, String senha) {
		try {
			List<Funcionario> listaFuncionarios = funcionarioServiceImpl.buscarDadosDosFuncionarios();
			for (Funcionario funcionario : listaFuncionarios) {
				if (login.equals(funcionario.getLogin()) && senha.equals(funcionario.getSenha())) {
					Message.info("Login e Senha valida!!!");
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getSessionMap().put("funcionarioLogado", funcionario);
					return "menu?faces-redirect-true";
				}
			}
		} catch (Exception e) {
		}
		Message.erro("Login e Senha errada!!!");
		return null;
	}

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("funcionarioLogado");
		return "logar?faces-redirect-true";

	}

	public String cadastroLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("http://localhost:8080/oficina/cadastroLoginFuncionario.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}
		return "cadastroLoginFuncionario?faces-redirect-true";

	}

	public String cadastrarFuncionario() {
		try {
			if (funcionario.getIdFuncionario() == null) {
				 funcionarioServiceImpl.cadastrarFuncionario(funcionario);
				Message.info("Funcionario Cadastro com Sucesso!!!");
				carregarFuncionarios();
				limpar();
			} else {
				funcionarioServiceImpl.editarFuncionario(funcionario);
				Message.info("Funcionario atualizado com Sucesso!!!");
				carregarFuncionarios();
				limpar();
			}
		
		} catch (Exception e) {
			Message.erro(" Funcionario não foi Cadastro!!!");

		}
		return null;
	}

	public Funcionario imprimir() {

		RelatorioUtil.criarRelatorio("C:/Users/diego/git/oficinaWeb/oficina/relatorio/relatorioFuncionario.jrxml",
				funcionarioServiceImpl.buscarDadosDosFuncionarios());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return funcionario;

	}
	
	public Funcionario gerarPdf() {
		String pathJasper = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioFuncionario.jasper";
		String saida = "C:\\Users\\diego\\git\\oficinaWeb\\oficina\\relatorio\\relatorioFuncionario.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, funcionarioServiceImpl.buscarDadosDosFuncionarios(), saida);
		Message.info("Download Reliazada com Sucesso!!!");
		return funcionario;

	}

	public void excluirFuncionario(Funcionario funcionario) {
		try {
			funcionarioServiceImpl.removerFuncionario(funcionario);
			Message.erro("Funcionario excluido com Sucesso!!!");
			carregarFuncionarios();
			limpar();
		} catch (Exception e) {
			Message.erro(" Funcionario não foi excluido!!!");
		}
	}
	
	public void pesquisarFuncionario() {
		funcionarios =funcionarioServiceImpl.buscarDadosDosFuncionarios(funcionario);
		limpar();
		if(!funcionarios.isEmpty()) {
			Message.info("Funcionario foi encontrado !!!");
		}else {
			Message.info("Funcionario não foi encontrado !!!");
		}
		}
	
	@PostConstruct
	public void carregarFuncionarios() {
		funcionarios = funcionarioServiceImpl.buscarDadosDosFuncionarios();
	}

	private void limpar() {
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	

}
