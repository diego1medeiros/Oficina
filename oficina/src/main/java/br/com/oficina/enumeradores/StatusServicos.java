package br.com.oficina.enumeradores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum StatusServicos {

	SERVIÇO_EM_ORÇAMENTO("SERVIÇO_EM_ORÇAMENTO", "SERVIÇO EM ORÇAMENTO"),
	ORÇAMENTO_AGUARDANDO_APROVAÇÃO("ORÇAMENTO_AGUARDANDO_APROVAÇÃO", "ORÇAMENTO AGUARDANDO APROVAÇÃO"),
	ORÇAMENTO_ACEITO_PELO_CLIENTE("ORÇAMENTO_ACEITO_PELO_CLIENTE", "ORÇAMENTO ACEITO PELO CLIENTE"),
	SERVIÇO_EM_ANDAMENTO("SERVICO_EM_ANDAMENTO", "SERVIÇO EM ANDAMENTO"),
	SERVIÇO_CONCLUIDO("SERVIÇO_CONCLUIDO", "SERVIÇO CONCLUIDO"),
	SERVIÇO_CANCELADO("SERVIÇO_CANCELADO", "SERVIÇO CANCELADO");

	private String codigo;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public static String[] getDescricaoComboBox() {
		String[] listaRetorno = new String[6];
		int i = 0;
		for (StatusServicos s : StatusServicos.values()) {
			listaRetorno[i] = s.getDescricao();
			i++;
		}
		return listaRetorno;
	}

	public static String getCodigoByDescricao(String descricao) {
		return null;
	}

	public static String getDescricaoBycodigo(String codigo) {
		return null;
	}

	private StatusServicos(String codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return getCodigo();
	}

	public static List<StatusServicos> getListDecricao() {
		List<StatusServicos> descricoes = new ArrayList<StatusServicos>();
		for (StatusServicos statusServico : StatusServicos.values()) {
			descricoes.add(statusServico);
		}
		Collections.sort(descricoes, new Comparator<StatusServicos>() {

			@Override
			public int compare(StatusServicos o1, StatusServicos o2) {

				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});

		return descricoes;

	}

}
