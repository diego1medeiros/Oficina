<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/entrada" var="linkEntradaServlet" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<style>
body {
  background-color: lightblue;
}
h1 {
  text-align: center;
}
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 20px;
  box-sizing: border-box;
}
a:link, a:visited {
  background-color: #000000;
  color: white;
  padding: 8px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

input[type=submit] {
  width: 10%;
  background-color: #000000;
  color: white;
  padding: 10px 15px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

div {
  border-radius: 5px;
  background-color: lightblue;
  padding: 5px;
}
</style>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Alterar Cadastro de Funcionario</h1>

	<form action="${linkEntradaServlet }" method="post">
	
		<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Funcao</b></span>
  <input type="text" name="funcao" value="${funcionario.funcao}">
</div>


	<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Nome</b></span>
  <input type="text" name="nome" value="${funcionario.nome}">
</div>

<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Cpf</b></span>
  <input type="text" name="cpf" value="${funcionario.cpf}">
</div>

<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Telefone</b></span>
  <input type="text" name="telefone" value="${funcionario.telefone}">
</div>
	
	<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Endereço</b></span>
  <input type="text" name="endereco" value="${funcionario.endereco}">
</div>		

<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Email</b></span>
  <input type="text" name="email" value="${funcionario.email}">
</div>
		
		<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Login</b></span>
  <input type="text" name="login" value="${funcionario.login}">
</div>

<div class="input-group mb-3 input-group-sm">
   <span class="input-group-text"><b>Senha</b></span>
  <input type="text" name="senha" value="${funcionario.senha}">
</div>
      
       <input type="hidden" name="id" value="${funcionario.idFuncionario}">
       <input type="hidden" name="acao" value="AlteraFuncionarios">
 <input type="submit"/>
 <c:import url="logout-parcial.jsp" />
	</form>
</body>
</html>