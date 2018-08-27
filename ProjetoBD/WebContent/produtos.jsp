<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="prjbd.model.Produto" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/tudo.css" rel="stylesheet">
<title>Produtos</title>
</head>
<body>
	<a href=index.jsp>Home</a>
	<h2>Produtos</h2>
	<table>
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
	</table>
	<a href="limpar_produtos">Limpar</a>
</body>
</html>