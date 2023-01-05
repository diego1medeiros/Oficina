package br.com.oficina.entity;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"VEICULO\"")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID_VEICULO\"")
	private Long idVeiculo;
	@Column(name = "\"TIPO\"")
	private String tipo;
	@Column(name = "\"MARCA\"")
	private String marca;
	@Column(name = "\"NOME\"")
	private String modelo;
	@Column(name = "\"ANO\"")
	private String ano;
	@ManyToOne
	@JoinColumn(name = "\"ID_CLIENTE\"")
	
	private Cliente cliente = new Cliente();

	public Veiculo() {
	}

	
	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getMarca() {
		return marca;
	}

	public String getAno() {
		return ano;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return this.getModelo();

	}

	public void adicionarCliente(Cliente cliente) {
		setCliente(cliente);
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	
}
