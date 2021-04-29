<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de produtos</title>

<link rel="stylesheet" href="resources/css/cadastro.css">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<a href="acessoliberado.jsp" class="container">home</a> </br>
<a href="index.jsp" class="container">Logout</a>


	<center>
	<h1>Cadastro Produtos</h1>
	<h3 style="color: red;">${msg}</h3>
	</center>

	<form action="salvarProduto" method="post" id="formProduto" onsubmit="return validarCampos() ? true : false;">
	<ul class="form-style-1"><li>
		<table>
		
			<tr>
				<td>Código:</td>
				<td><input type="text" readonly="readonly" id="id" name="id" value="${produto.id}"></td>
			</tr>
		
			<tr>
				<td>Nome:</td>
				<td><input type="text" id="nome" name="nome" value="${produto.nome}"></td>
			</tr>
			
			<tr>
				<td>Quantidade:</td>
				<td><input type="number" id="quantidade" name="quantidade" value="${produto.quantidade}"></td>
			</tr>
			
			<tr>
				<td>Valor:</td>
				<td><input type="text" id="valor" name="valor" value="${produto.valor}"></td>
			</tr>	
				
			<tr>
				<td>Categoria:</td>
				<td>
					<select id="categorias" name="categoria_id">
						<c:forEach items="${categorias}" var="cat">
							<option value="${cat.id}" id="${cat.id}" 
								<c:if test="${cat.id} == produto.categoria_id">
									<c:out value = "selected=selected" />
								</c:if>>									
								${cat.nome}
							</option>
						</c:forEach>
					</select>			
				</td>
			</tr>				

			<tr>
			<td></td>
			<td><input type="submit" value="salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'"></td> 
			
			</tr>
		</table>		
		</li>
		</ul>
	</form>

	<div class="container">
	<caption>Produtos cadastrados</caption>
	<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">Id</th>
		      <th scope="col">Nome</th>
		      <th scope="col">Quantidade</th>
		      <th scope="col">Valor</th>
		      <th scope="col">Excluir</th>
		      <th scope="col">Editar</th>
		    </tr>
		  </thead>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td style="width: 150px"><c:out value="${produto.id}"></c:out></td>
				<td style="width: 150px"><c:out value="${produto.nome}"></c:out></td>
				<td><c:out value="${produto.quantidade}"></c:out></td>
				<td><c:out value="${produto.valor}"></c:out></td>
				
				<td><a href="salvarProduto?acao=delete&produto=${produto.id}"><img src="resources/img/excluir.png" width="20px" height="20px"></a></td>
				<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img alt="Editar" title="Editar" src="resources/img/editar.jpg" width="20px" height="20px"></a></td>
				
			</tr>
		</c:forEach>
	
	</table>
	</div>

<script type="text/javascript">

	function validarCampos() {
		if (document.getElementById("nome").value == '') {
			alert('Informe o nome');
			return false;
		} else if (document.getElementById("quantidade").value == '') {
			alert('Informe a quantidade');
			return false;
		} else if (document.getElementById("valor").value == '') {
			alert('Informe o valor');
			return false;
		} 
		
		return true;		
	}
	
</script>



</body>
</html>