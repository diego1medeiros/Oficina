package br.com.oficina.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
//teste
String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;

}
