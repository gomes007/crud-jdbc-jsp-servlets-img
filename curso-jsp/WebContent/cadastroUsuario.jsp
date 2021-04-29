<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuario</title>

<link rel="stylesheet" href="resources/css/cadastro.css">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

</head>
<body>

<a href="acessoliberado.jsp">home</a> </br>
<a href="index.jsp">Logout</a>

	<center>
	<h1>Cadastro usuario</h1>
	<h3 style="color: red;">${msg}</h3>
	</center>


	<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data">
	<ul class="form-style-1">
	<li>
		<table>
		
			<tr>
				<td>Código:</td>
				<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}"></td>
				
				<td>CEP:</td>
				<td><input type="text" id="cep" name="cep" value="${user.cep}"></td>
				
			</tr>
		
			<tr>
				<td>Login:</td>
				<td><input type="text" id="login" name="login" value="${user.login}"></td>
				
				<td>Rua:</td>
				<td><input type="text" id="rua" name="rua" value="${user.rua}"></td>
				
			</tr>
			
			<tr>
				<td>Nome:</td>
				<td><input type="text" id="nome" name="nome" value="${user.nome}" placeholder="informe o nome do usuario"></td>
				

				
				<td>Bairro:</td>
				<td><input type="text" id="bairro" name="bairro" value="${user.bairro}"></td>				
				
			</tr>
			
			<tr>
				<td>Fone:</td>
				<td><input type="text" id="fone" name="fone" value="${user.fone}"></td>
				
				<td>Cidade:</td>
				<td><input type="text" id="cidade" name="cidade" value="${user.cidade}"></td>
								
			</tr>	
			
				
			<tr>
				<td>Estado:</td>
				<td><input type="text" id="estado" name="estado" value="${user.estado}"></td>
				
				<td>IBGE:</td>
				<td><input type="text" id="ibge" name="ibge" value="${user.ibge}"></td>	
				

			</tr>
			
			<tr>
				<td>Sexo:</td>
				<td>
				<input type="radio" name="sexo"				
				<%
				if(request.getAttribute("user") != null){						
					BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
					if(user.getSexo().equalsIgnoreCase("masculino")){
						out.print("checked=\"checked\"");
						out.print(" ");							
					}						
				}
				
				%>				
				 value="masculino">Masculino</input>
				<input type="radio" name="sexo" 
				<%
				if(request.getAttribute("user") != null){						
					BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
					if(user.getSexo().equalsIgnoreCase("feminino")){
						out.print("checked=\"checked\"");
						out.print(" ");							
					}						
				}
				
				%>								
				value="feminino">Feminino</input>				
				</td>			
			</tr>

			<tr>
				<td>Senha:</td>
				<td><input type="password" id="senha" name="senha" value="${user.senha}"></td>				
			</tr>
			
			<tr>
				<td>Ativo:</td>
				<td><input type="checkbox" id="ativo" name="ativo"
				<%
					if(request.getAttribute("user") != null){						
						BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						if(user.isAtivo()){
							out.print("checked=\"checked\"");
							out.print(" ");							
						}						
					}
				%>				
				></td>	
			</tr>			
			
			
			<tr>
				<td>
					Foto:
				</td>
				<td>
				<input type="file" name="foto">
				<input type="text" style="display: none;" name="fotoTemp" readonly="readonly" value="${user.fotoBase64}"/>
				<input type="text" style="display: none;" name="contTemp" readonly="readonly" value="${user.fotoBase64}"/>				
				</td>
				
			</tr>
			
			
			<tr>
				<td>
					CV:
				</td>
				<td>
				<input type="file" name="curriculo" value="curriculo">
				<input type="text" style="display: none;" name="cvTemp" readonly="readonly" value="${user.curriculoBase64}"/>
				<input type="text" style="display: none;" name="cvcontTemp" readonly="readonly" value="${user.curriculocontentType}"/>								
				</td>
				
				<td>Perfil:</td>		
				<td>
				<select id="perfil" name="perfil" style="width: 200px;">
					<option value="nao_informado">[--selecione--]</option>
					
					
					<option value="administrador"
					<%
					if(request.getAttribute("user") != null){						
						BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						if(user.getPerfil().equalsIgnoreCase("administrador")){
							out.print("selected=\"selected\"");
							out.print(" ");							
						}						
					} 
					%>
					>Administrador</option>
					
					<option value="secretario"
										<%
					if(request.getAttribute("user") != null){						
						BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						if(user.getPerfil().equalsIgnoreCase("secretario")){
							out.print("selected=\"selected\"");
							out.print(" ");							
						}						
					}
					%>					
					>Secretario</option>
					
					<option value="gerente"
					<%
					if(request.getAttribute("user") != null){						
						BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						if(user.getPerfil().equalsIgnoreCase("gerente")){
							out.print("selected=\"selected\"");
							out.print(" ");							
						}						
					}
					%>
					>Gerente</option>
					
					<option value="funcionario"
					<%
					if(request.getAttribute("user") != null){						
						BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						if(user.getPerfil().equalsIgnoreCase("funcionario")){
							out.print("selected=\"selected\"");
							out.print(" ");							
						}						
					}
					%>
					>Funcionario</option>
				</select>
				</td>				
				
			</tr>			
			
			
			<tr>
			<td></td>
			<td>
			<input type="submit" value="salvar"> 
			<input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
			</td> 

			<td><a href="salvarUsuario?acao=listartodos">Listar todos</a></td>
			</tr>
		</table>
		
		</li>
		</ul>
	</form>
	
	<form method="post" action="servletPesquisa" style="width: 90%;">
	<ul class="form-style-1">
	<li>
		<table>
			<tr>
				<td>Descricao</td>
				<td><input type="text" id="descricaoconsulta" name="descricaoconsulta"></td>
				<td><input type="submit" value="Pesquisar"></td>
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
		      <th scope="col">Login</th>
		      <th scope="col">Foto</th>	
		      <th scope="col">CV</th>	      
		      <th scope="col">Nome</th>
		      <th scope="col">Fone</th>
		      <th scope="col">Cidade</th>
		      <th scope="col">Excluir</th>
		      <th scope="col">Editar</th>
		      <th scope="col">Fones</th>
		    </tr>
		  </thead>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td style="width: 150px"><c:out value="${user.id}"></c:out></td>	
				<td style="width: 150px"><c:out value="${user.login}"></c:out></td>		
				
				<c:if test="${user.fotoBase64.isEmpty() == false}">
					<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src='<c:out value="${user.tempfotoUser}"/>' alt="Imagem User" title="Imagem User" width="40px" height="32px"></a></td>
				</c:if>
				
				<c:if test="${user.fotoBase64.isEmpty() == true}">
					<td><img alt="Imagem User" src="resources/img/user.png" width="20px" height="20px"> </td>					
				</c:if>
				
				<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">CV</a></td>
				<td><c:out value="${user.nome}"></c:out></td>
				<td><c:out value="${user.fone}"></c:out></td>
				<td><c:out value="${user.cidade}"></c:out></td>
				
				<td><a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclusao?');"><img src="resources/img/excluir.png" width="20px" height="20px"></a></td>
				<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img alt="Editar" title="Editar" src="resources/img/editar.jpg" width="20px" height="20px"></a></td>
				<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img alt="Telefones" title="Telefones" src="resources/img/phone.png" width="20px" height="20px"></a></td>
			</tr>
		</c:forEach>	
	</table>
	</div>
	
	<script type="text/javascript">
	function validarCampos() {
		if (document.getElementById("login").value == '') {
			alert('Informe o login');
			return false;
		} else if (document.getElementById("nome").value == '') {
			alert('Informe o nome');
			return false;
		} else if (document.getElementById("fone").value == '') {
			alert('Informe o telefone');
			return false;
		} else if (document.getElementById("senha").value == '') {
			alert('Informe a senha');
			return false;
		}
		
		return true;		
	}
	
	
	$(document).ready(function() {

        function limpa_formulário_cep() {
            // Limpa valores do formulário de cep.
            $("#rua").val("");
            $("#bairro").val("");
            $("#cidade").val("");
            $("#estado").val("");
            $("#ibge").val("");
        }
        
        //Quando o campo cep perde o foco.
        $("#cep").blur(function() {

            //Nova variável "cep" somente com dígitos.
            var cep = $(this).val().replace(/\D/g, '');

            //Verifica se campo cep possui valor informado.
            if (cep != "") {

                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if(validacep.test(cep)) {

                    //Preenche os campos com "..." enquanto consulta webservice.
                    $("#rua").val("...");
                    $("#bairro").val("...");
                    $("#cidade").val("...");
                    $("#estado").val("...");
                    $("#ibge").val("...");

                    //Consulta o webservice viacep.com.br/
                    $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                        if (!("erro" in dados)) {
                            //Atualiza os campos com os valores da consulta.
                            $("#rua").val(dados.logradouro);
                            $("#bairro").val(dados.bairro);
                            $("#cidade").val(dados.localidade);
                            $("#estado").val(dados.estado);
                            $("#ibge").val(dados.ibge);
                        } //end if.
                        else {
                            //CEP pesquisado não foi encontrado.
                            limpa_formulário_cep();
                            alert("CEP não encontrado.");
                        }
                    });
                } //end if.
                else {
                    //cep é inválido.
                    limpa_formulário_cep();
                    alert("Formato de CEP inválido.");
                }
            } //end if.
            else {
                //cep sem valor, limpa formulário.
                limpa_formulário_cep();
            }
        });
    });
	
	
	</script>
	
</body>
</html>