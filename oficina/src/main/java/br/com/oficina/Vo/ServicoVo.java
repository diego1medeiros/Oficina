package br.com.oficina.Vo;

import java.io.Serializable;
import java.util.Date;
import br.com.oficina.entity.Servico;



public class ServicoVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nomeFuncionario;
	private Long qtdeServico;
	private String nomeCliente;
	private String modelo;
	private String status;
	private String observacoes;
	private String descricao;
	private Double valor;
	private Double valorTotal;
	private Servico servico;
	private int pago;
	private Date dataServico;
	private Date dataEntrega;

	public ServicoVo() {

	}
	
	public ServicoVo(String nomeFuncionario, Long qtdeServico) {
		super();
		this.nomeFuncionario = nomeFuncionario;
		this.qtdeServico = qtdeServico;
	}
	
	
	public ServicoVo(String nomeFuncionario, String nomeCliente, String modelo, String descricao, String observacoes,
			String status, Date dataServico, Date dataEntrega, Double valor, int pago, Long qtdeServico,Double valorTotal) {
		super();
		

		this.nomeFuncionario = nomeFuncionario;
		this.nomeCliente = nomeCliente;
		this.modelo = modelo;
		this.observacoes = observacoes;
		this.descricao = descricao;
		this.status = status;
		this.dataServico = dataServico;
		this.dataEntrega = dataEntrega;
		this.valor = valor;
		this.pago = pago;
		this.qtdeServico = qtdeServico;
		this.valorTotal = valorTotal;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getPago() {
		return pago;
	}

	public void setPago(int pago) {
		this.pago = pago;
	}

	public Date getDataServico() {
		return dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getQtdeServico() {
		return qtdeServico;
	}

	public void setQtdeServico(Long qtdeServico) {
		this.qtdeServico = qtdeServico;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
