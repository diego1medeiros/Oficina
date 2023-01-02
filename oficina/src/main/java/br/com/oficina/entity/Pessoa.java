package br.com.oficina.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoa {
 
	@Column(name = "\"NOME\"")
	private String nome; 
	@Column(name = "\"CPF\"")
	private String cpf;
	@Column(name = "\"TELEFONE\"")
	private String telefone;
	@Column(name = "\"EMAIL\"")
	private String email;
	@Column(name = "\"ENDERECO\"")
	private String endereco;
	
	
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getEmail() {
		return email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + "]";
	}

	public Pessoa() {
		super();
	}

	public Pessoa(String nome, String cpf, String telefone, String email, String endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
}
