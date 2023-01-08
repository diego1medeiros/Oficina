package br.com.oficina.entity;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
