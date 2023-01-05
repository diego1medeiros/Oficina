package br.com.oficina.util;

public class Utils {
	
	public static String formatarPagamento(int pago) {

		String pagamento = (String.valueOf(pago));
		if (pagamento.equals("1")) {
			pagamento = "SIM";
		} else  {
			pagamento = "NÃO";
		}
		return pagamento;

	}
	
}
