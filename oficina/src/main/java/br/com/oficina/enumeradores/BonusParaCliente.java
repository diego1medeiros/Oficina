package br.com.oficina.enumeradores;

public enum BonusParaCliente {

	LAVAGEM_GRATIS(" Lavagem Gratis"), TROCA_DE_OLEO("Troca de oleo"), POLIMENTO_GRATIS("Polimento Gratis");

	private BonusParaCliente(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String[] getDescricaoComboBox() {
		String[] listaRetorno = new String[3];
		int i = 0;
		for (BonusParaCliente bonus : BonusParaCliente.values()) {
			listaRetorno[i] = bonus.getDescricao();
			i++;
		}
		return listaRetorno;
	}

}
