package br.com.oficina.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	

	public static String formatarData(Date data, String formato) {

		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String dataRetorno = sdf.format(data);
		return dataRetorno;
	}

	public static boolean isCpfValido(String cpf) {

		Pattern cpfValido = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
		Matcher mCpf = cpfValido.matcher(cpf);
		boolean isCpfValido = mCpf.matches();
		return isCpfValido;
	}

	public static boolean isTelefoneValido(String telefone) {

		if(telefone==null) {
			return false;
		}
		Pattern telefoneValido = Pattern.compile("^[0-9]{2}-([0-9]{8}|[0-9]{9})");
		Matcher mTelefone = telefoneValido.matcher(telefone);
		boolean isTelefoneValido = mTelefone.matches();
		return isTelefoneValido;
	}

	public static boolean isEmailValido(String email) {

		Pattern emailValido = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		Matcher mEmail = emailValido.matcher(email);
		boolean isEmailValido = mEmail.matches();
		return isEmailValido;
	}

	public static String formatarPagamento(int pago) {

		String pagamento = (String.valueOf(pago));
		if (pagamento.equals("1")) {
			pagamento = "SIM";
		} else  {
			pagamento = "NÃO";
		}
		return pagamento;

	}
	
	public static int formatarServicoPago(String object) {

		int pagamento = (Integer.valueOf(object));
	
		int SIM = 0;
		if (pagamento ==  SIM) {
			pagamento = 1;
		} else {
			pagamento = 0;
		}
		return pagamento;

	}
	
	public static int formatarServicoPago(int pago) {
		
		 int pagamento = pago;
		if( pagamento == 1){
			pagamento = 0;
		} else if  ( pagamento == 0) {
			pagamento = 1;
		}else {
			pagamento = 2 ;
		}
		return pagamento;
	}
	
}
