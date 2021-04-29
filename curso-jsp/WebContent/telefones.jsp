<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de telefones</title>

<link rel="stylesheet" href="resources/css/cadastro.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">



</head>
<body>

	<a href="acessoliberado.jsp">home</a>
	</br>
	<a href="index.jsp">Logout</a>

	<center>
		<h1 >Cadastro usuario</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>


	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}"></td>
						<td><input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}"></td>
					</tr>
					
					<tr>
						<td>Numero:</td>
						<td><input type="text"  id="numero" name="numero"></td>
						<td>
						<select id="tipo" name="tipo">
							<option>casa</option>
							<option>Contato</option>
							<option>Celular</option>
						</select>
						</td>
					</tr>


					<tr>
						<td></td>
						<td><input type="submit" value="salvar"></td>						
					</tr>
					
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<caption>Usuários cadastrados</caption>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Numero</th>
					<th scope="col">Tipo</th>
					<th scope="col">Excluir</th>
				
			
				</tr>
			</thead>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>
					
	

					<td><a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}"><img
							src="resources/img/excluir.png" width="20px" height="20px"></a></td>
							
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o numero');
				return false;
			
			} else if (document.getElementById("tipo").value == '') {
				alert('Informe o tipo');
				return false;
			
			}

			return true;
		}

		
	</script>

</body>
</html>