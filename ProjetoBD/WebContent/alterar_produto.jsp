<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Editar produto</title>
<link href="assets/bootstrap.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="produtos">Produtos</a>
			</li>
		</ul>
	</nav>
	<br>
	
	<div class="container">
		<h2>Editar</h2>
		<form action="inserir_produto_processa" method="POST">
			<input type="hidden" name="id" value="${produto.id()}" />
			<div class="form-group">
				<label class="">Nome</label>
				<input class="form-control" type="text" name="nome" value="${produto.nome()}" />
			</div>
			<div class="form-group">
				<label class="">Preco</label>
				<input class="form-control" type="text" name="preco" value="${produto.preco()}" />
			</div>
			<div class="form-group">
				<label class="">Parcelas</label> 
				<input class="form-control" type="text" name="parcelas" value="${produto.parcelas()}" />
			</div>
			<div class="form-group">
				<label class="">Valor Parcela</label> 
				<input class="form-control" type="text" name="valorParcela" value="${produto.valorParcela()}" />
			</div>
			<div class="form-group">
				<label class="d-block">Disponível</label>
				
				<c:if test="${produto.disponivel()}">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="disponivel" value="true" checked />
					<label class="form-check-label">Sim</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="disponivel" value="false" />
					<label class="form-check-label">Não</label>
				</div>
				</c:if>
				
				<c:if test="${!produto.disponivel()}">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="disponivel" value="true" />
					<label class="form-check-label">Sim</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="disponivel" value="false" checked />
					<label class="form-check-label">Não</label>
				</div>
				</c:if>
				
			</div>
			<div class="form-group">
				<label class="">Loja</label>
				<input class="form-control" type="text" name="loja" value="${produto.loja()}" />
			</div>
			<input class="btn btn-primary" type="submit" value="Inserir" />
		</form>
		<br>
		
	</div>
</body>
</html>