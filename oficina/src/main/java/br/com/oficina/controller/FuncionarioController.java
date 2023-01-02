package br.com.oficina.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.oficina.entity.Funcionario;
import br.com.oficina.service.FuncionarioServiceImpl;
import br.com.oficina.util.Message;
import br.com.oficina.util.RelatorioUtil;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
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

	public List<Funcionario> buscarDadosDosFuncionarios() {
		return funcionarioServiceImpl.buscarDadosDosFuncionarios();
	}

	public Funcionario buscarFuncionarioPeloId(Long id) {
		return funcionarioServiceImpl.buscarFuncionarioPeloId(id);
	}

	public Funcionario editarFuncionario(Funcionario funcionario) {
		return funcionarioServiceImpl.editarFuncionario(funcionario);
	}

	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		return funcionarioServiceImpl.cadastrarFuncionario(funcionario);
	}

	public Funcionario removerFuncionario(Funcionario funcionario) {

		return funcionarioServiceImpl.removerFuncionario(funcionario);

	}

	public Long removerFuncionario(Long id) {
		return funcionarioServiceImpl.removerFuncionario(id);
	}

	public List<Funcionario> buscarDadosDosFuncionarios(Funcionario funcionario) {
		return funcionarioServiceImpl.buscarDadosDosFuncionarios(funcionario);
	}

	public String isLoginSenhaValida(String login, String senha) {
		try {
			List<Funcionario> listaFuncionarios = buscarDadosDosFuncionarios();
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
		Message.info("Login e Senha errada!!!");
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
				cadastrarFuncionario(funcionario);
				carregarFuncionarios();
				limpar();
				Message.info("Funcionario Cadastro com Sucesso!!!");
			} else {
				updateFuncionario(funcionario);
				carregarFuncionarios();
				limpar();
			}
		} catch (Exception e) {
			Message.erro(" Funcionario não foi Cadastro!!!");

		}
		return null;
	}

	public Funcionario imprimir() {

		RelatorioUtil.criarRelatorio("C:/dev/worspace/oficina/relatorio/relatorioFuncionario.jrxml",
				buscarDadosDosFuncionarios());
		Message.info("Impressão Reliazada com Sucesso!!!");
		return funcionario;

	}
	
	public Funcionario gerarPdf() {
		String pathJasper = "C:\\dev\\worspace\\oficina\\relatorio\\relatorioFuncionario.jasper";
		String saida = "C:\\dev\\worspace\\oficina\\relatorio\\relatorioFuncionario.pdf";
		RelatorioUtil.gerarArquivoPdf(pathJasper, buscarDadosDosFuncionarios(), saida);
		Message.info("Download Reliazada com Sucesso!!!");
		return funcionario;

	}

	public void excluirFuncionario(Funcionario funcionario) {
		try {
			removerFuncionario(funcionario);
			Message.info("Funcionario excluido com Sucesso!!!");
			carregarFuncionarios();
			limpar();

		} catch (Exception e) {
			Message.erro(" Funcionario não foi excluido!!!");
		}
	}
	public void updateFuncionario(Funcionario funcionario) {
		try {
			editarFuncionario(funcionario);
			carregarFuncionarios();
			Message.info("Funcionario atualizado com Sucesso!!!");

		} catch (Exception e) {
			Message.erro(" Funcionario não foi atualizado!!!");
		}
	}

	public void pesquisarFuncionario() {
		funcionarios = buscarDadosDosFuncionarios(funcionario);
		limpar();
		if(!funcionarios.isEmpty()) {
			Message.info("Funcionario foi encontrado !!!");
		}else {
			Message.info("Funcionario não foi encontrado !!!");
		}
		}
	@PostConstruct
	public void carregarFuncionarios() {
		funcionarios = buscarDadosDosFuncionarios();
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
