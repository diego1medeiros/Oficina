<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List,br.com.oficina.entity.Funcionario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
<style>

body {
  background-image: url("");
}
h1 {
  text-align: center;
}
a:link, a:visited {
  background-color: #000000;
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

body {
  background-color: lightblue;
}
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
<body>
	
	<h2>Usuario Logado: ${usuarioLogado.login }</h2>
	
	<c:if test="${not empty empresa }">   
    Empresa ${ empresa} cadastrada com sucesso!
   </c:if>
	<h1>Lista de funcionarios </h1>
	
	<ul>
		<c:forEach items="${funcionarios }" var="funcionario">
		<br><tr>
			<table id="customers">
  <tr>
   <th>Cod</th>
    <th>Função</th>
    <th>Nome</th>
    <th>Cpf</th>
    <th>Telefone</th>
    <th>Endereço</th>
    <th>Email</th>
    <th>Login</th>
    <th>Senha</th>
    <th>Ações</th>
  </tr>
  <tr>
   <td>${funcionario.idFuncionario}</td>
      <td>${funcionario.funcao}</td>
     <td>${funcionario.nome}</td>
      <td>${funcionario.cpf}</td>
      <td>${funcionario.telefone}</td>
		<td>${ funcionario.endereco } </td>
		<td>${funcionario.email} </td>
		<td>${ funcionario.login } </td>
	<td>${funcionario.senha} </td>
	<td><a href="/oficina/entrada?acao=MostraFuncionario&id=${ funcionario.idFuncionario } ">
				editar</a> <a href="/oficina/entrada?acao=RemoveFuncionario&id=${  funcionario.idFuncionario} ">
				remove</a>	 </td>
  </tr> 
			</br>
			  </table>
			<br>			  
		</c:forEach>
		  <a href="/oficina/entrada?acao=NovoFuncionarioForm&id=${ funcionario.idFuncionario } ">
			cadastrar</a>
		<c:import url="logout-parcial.jsp" />
			
		<br>
		
	
	</ul>
</body>
</html>