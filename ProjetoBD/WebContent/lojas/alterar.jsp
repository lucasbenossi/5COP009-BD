<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Alterar Loja</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/lojas">Lojas</a>
			</li>
		</ul>
	</nav>
	<br>
	
	<div class="container">
		<h2>Alterar Loja</h2>
		<form action="${pageContext.request.contextPath}/lojas/alterar_processa" method="POST">
			<input type="hidden" name="id" value="${loja.id()}" />
			<div class="form-group">
				<label class="">Nome</label>
				<input class="form-control" type="text" name="nome" value="${loja.nome()}"/>
			</div>
			<div class="form-group">
				<label class="">Url</label>
				<input class="form-control" type="text" name="url" value="${loja.url()}"/>
			</div>
			<input class="btn btn-primary" type="submit" value="Alterar" />
		</form>
		<br>
		
	</div>
</body>
</html>