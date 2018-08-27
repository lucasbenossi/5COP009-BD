<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar produto</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/tudo.css" rel="stylesheet">
</head>
<body>
	<a href="index.jsp">Home</a>
	<h2>Editar</h2>
	<form action="alterar_produto_processa" method="POST">
		<input type="hidden" name="id" value="${produto.id()}" />
		Nome: <input type="text" name="nome" value="${produto.nome()}"/><br>
		Preco: <input type="text" name="preco" value="${produto.preco()}"/><br>
		Parcelas: <input type="text" name="parcelas" value="${produto.parcelas()}"/><br>
		Valor Parcela: <input type="text" name="valorParcela" value="${produto.valorParcela()}"/><br>
		Disponivel:
		
		<c:if test="${produto.disponivel()}">
			<input type="radio" name="disponivel" value="true" checked /> Sim
			<input type="radio" name="disponivel" value="false" /> Não<br>
		</c:if>
		
		<c:if test="${!produto.disponivel()}">
			<input type="radio" name="disponivel" value="true" /> Sim
			<input type="radio" name="disponivel" value="false" checked /> Não<br>
		</c:if>

		Loja: <input type="text" name="loja" value="${produto.loja()}"/><br>
		<input type="submit" value="Alterar" />
	</form>
</body>
</html>