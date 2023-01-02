package br.com.oficina.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BonusClienteId implements Serializable{
	
	@Column(name = "\"id_cliente\"")
	private Long idCliente;
	
	@Column(name = "\"id_veiculo\"" )
	private Long idVeiculo;
	
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	public Long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
	public BonusClienteId() {
		super();
	}
	public BonusClienteId(Long idCliente, Long idVeiculo) {
		super();
		this.idCliente = idCliente;
		this.idVeiculo = idVeiculo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((idVeiculo == null) ? 0 : idVeiculo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusClienteId other = (BonusClienteId) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (idVeiculo == null) {
			if (other.idVeiculo != null)
				return false;
		} else if (!idVeiculo.equals(other.idVeiculo))
			return false;
		return true;
	}
	
}
