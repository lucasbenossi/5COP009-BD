<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Alterar Produto</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
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
		</ul>
	</nav>
	<br>
	
	<div class="container">
		<h2>Alterar Produto</h2>
		<form action="${pageContext.request.contextPath}/produtos/alterar_processa" method="POST">
			<input type="hidden" name="id" value="${produto.getId()}" />
			<div class="form-group">
				<label class="">Nome</label>
				<input class="form-control" type="text" name="nome" value="${produto.getNome()}" />
			</div>
			<div class="form-group">
				<label class="">Nome Tratado</label>
				<input class="form-control" type="text" name="nomeTratado" value="${produto.getNomeTratado()}"/>
			</div>
			<div class="form-group">
				<label class="">Preco</label>
				<input class="form-control" type="text" name="preco" value="${produto.getPreco()}" />
			</div>
			<div class="form-group">
				<label class="">Parcelas</label> 
				<input class="form-control" type="text" name="parcelas" value="${produto.getParcelas()}" />
			</div>
			<div class="form-group">
				<label class="">Valor Parcela</label> 
				<input class="form-control" type="text" name="valorParcela" value="${produto.getValorParcela()}" />
			</div>
			<div class="form-group">
				<label class="">IdLoja</label>
				<input class="form-control" type="text" name="idLoja" value="${produto.getIdLoja()}" />
			</div>
			<div class="form-group">
				<label class="">Url</label>
				<input class="form-control" type="text" name="url" value="${produto.getUrl()}" />
			</div>
			<input class="btn btn-primary" type="submit" value="Alterar" />
		</form>
		<br>
		
	</div>
</body>
</html>