<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
<title>Produtos</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/produtos">Produtos</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/produtos/inserir">Inserir</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/produtos/limpar">Limpar</a>
			</li>
		</ul>
	</nav>
	<br>

	<div class="container">
		<h2>Produtos</h2>
		
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Preço</th>
					<th>Parcelas</th>
					<th>Valor Parcela</th>
					<th>IdLoja</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="produto" items="${requestScope['produtosList']}">
				<tr>
					<td>${produto.getId()}</td>
					<td><a href="${produto.getUrl()}" target="_blank">${produto.getNome()}</a></td>
					<td>${produto.getPreco()}</td>
					<td>${produto.getParcelas()}</td>
					<td>${produto.getValorParcela()}</td>
					<td>${produto.getIdLoja()}</td>
					<td><a href="${pageContext.request.contextPath}/produtos/alterar?id=${produto.getId()}">Alterar</a></td>
					<td><a href="${pageContext.request.contextPath}/produtos/excluir?id=${produto.getId()}">Excluir</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
	</div>
</body>
</html>