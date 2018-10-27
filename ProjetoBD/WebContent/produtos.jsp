<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="prjbd.model.Produto" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="assets/bootstrap.css" rel="stylesheet">
<title>Produtos</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="limpar_produtos">Limpar</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="inserir_produto">Inserir</a>
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
					<th>Disponível</th>
					<th>Loja</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="produto" items="${requestScope['produtosList']}">
				<tr>
					<td>${produto.id()}</td>
					<td>${produto.nome()}</td>
					<td>${produto.preco()}</td>
					<td>${produto.parcelas()}</td>
					<td>${produto.valorParcela()}</td>
					<td>${produto.disponivel()}</td>
					<td>${produto.loja()}</td>
					<td><a href="alterar_produto?id=${produto.id()}">Alterar</a></td>
					<td><a href="excluir_produto?id=${produto.id()}">Excluir</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
	</div>
</body>
</html>