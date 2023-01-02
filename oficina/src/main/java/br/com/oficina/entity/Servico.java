package br.com.oficina.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"SERVICO\"")
public class Servico implements Serializable{

	@Override
	public int hashCode() {
		return Objects.hash(idServico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		return Objects.equals(idServico, other.idServico);
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID_SERVICO\"")
	private Long idServico;
	@Column(name = "\"DATA\"")
	private Date data;
	@Column(name = "\"DESCRICAO\"")
	private String descricao;
	@Column(name = "\"DATE_ENTREGA\"")
	private Date dataEntrega;
	
	public Servico(Date data, Date dataEntrega, int pagamento) {
		super();
		this.data = data;
		this.dataEntrega = dataEntrega;
		this.pago = pagamento;
	}

	@Column(name = "\"VALOR\"")
	private double valor;
	@Column(name = "\"STATUS\"")
	private String status;
	@Column(name = "\"PAGO\"")
	private int pago;
	@Column(name = "\"OBSERVACOES\"")
	private String observacoes;
	@ManyToOne
	@JoinColumn(name = "\"ID_CLIENTE\"")
	private Cliente cliente = new Cliente();
	@OneToOne
	@JoinColumn(name = "\"ID_VEICULO\"")
	private Veiculo veiculo = new Veiculo();
	@ManyToOne
	@JoinColumn(name = "\"ID_FUNCIONARIO\"")
	private Funcionario funcionario = new Funcionario();

	
	public Servico(Funcionario funcionario) {
		super();
		this.funcionario = funcionario;
	}

	public Servico(Long idServico, String status, String observacoes) {
		super();
		this.idServico = idServico;
		this.status = status;
		this.observacoes = observacoes;
	}

	public Servico(Long idServico, Date data, String descricao, Date dataEntrega, String status, int pago,
			String observacoes, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
		super();
		this.idServico = idServico;
		this.data = data;
		this.descricao = descricao;
		this.dataEntrega = dataEntrega;

		this.status = status;
		this.pago = pago;
		this.observacoes = observacoes;
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.funcionario = funcionario;
	}

	public Servico(Long idServico, Date data, String descricao, Date dataEntrega, double valor, String status, int pago,
			String observacoes, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
		super();
		this.idServico = idServico;
		this.data = data;
		this.descricao = descricao;
		this.dataEntrega = dataEntrega;
		this.valor = valor;
		this.status = status;
		this.pago = pago;
		this.observacoes = observacoes;
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.funcionario = funcionario;
	}

	public Servico(Long idServico, Date data, String descricao, Date dataEntrega, double valor, String status, int pago,
			String observacoes) {
		super();
		this.idServico = idServico;
		this.data = data;
		this.descricao = descricao;
		this.dataEntrega = dataEntrega;
		this.valor = valor;
		this.status = status;
		this.pago = pago;
		this.observacoes = observacoes;

	}

	public Servico(Date data, String descricao, Date dataEntrega, double valor, String status, int pago,
			String observacoes, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
		super();

		this.data = data;
		this.descricao = descricao;
		this.dataEntrega = dataEntrega;
		this.valor = valor;
		this.status = status;
		this.pago = pago;
		this.observacoes = observacoes;
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.funcionario = funcionario;
	}

	public Servico() {
	}

	public Long getIdServico() {
		return idServico;
	}

	public Date getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public double getValor() {
		return valor;
	}

	public String getStatus() {
		return status;
	}

	public int getPago() {
		return  pago;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPago(int pago) {
		this.pago = pago;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
