<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>

<html  xmlns="http://www.w3.org/1999/xhtml"
 xmlns:h="http://xmlns.jcp.org/jsf/html"
  xmlns:p="http://primefaces.org/ui"
>

<h:head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</h:head>
<style>
body{
background-image:;
}

body {
  background-color: lightblue;
}
h2 {
  text-align: center;
}
</style>


<body>



<div class="container mt-3">
  
  <h2>OFICINA MEDEIROS</h2>
  <form action="${linkEntradaServlet }" method="post">

    <div class="mb-3 mt-3">
      <label for="login">Login:</label>
      <input type="text" class="form-control" id="login" placeholder="Enter login" name="login">
    </div>
    <p:div class="mb-3">
      <label for="pwd">Senha:</label>
      <input type="password" class="form-control" id="senha" placeholder="Enter senha" name="senha">
    </p:div>
   
    <button name="acao" value="Login"class="btn btn-primary">Submit</button>
  </form>
</div>
	
</body>

</html>