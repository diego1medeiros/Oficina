package br.com.oficina.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "\"BONUS_CLIENTE\"")
public class BonusCliente {


	@EmbeddedId
	private BonusClienteId bonusClienteId;

	public BonusClienteId getBonusClienteId() {
		return bonusClienteId;
	}

	public String getDescricaoBonus() {
		return descricaoBonus;
	}

	public void setBonusClienteId(BonusClienteId bonusClienteId) {
		this.bonusClienteId = bonusClienteId;
	}

	public BonusCliente() {
		super();
	}

	public void setDescricaoBonus(String descricaoBonus) {
		this.descricaoBonus = descricaoBonus;
	}

	public BonusCliente(BonusClienteId bonusClienteId, String descricaoBonus) {
		super();
		this.bonusClienteId = bonusClienteId;
		this.descricaoBonus = descricaoBonus;
	}

	@Column(name = "\"descricao_bonus\"")
	private String descricaoBonus;
	
	
	
	

}
