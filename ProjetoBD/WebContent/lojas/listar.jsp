<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
<title>Lojas</title>
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
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/lojas/inserir">Inserir</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/lojas/limpar">Limpar</a>
			</li>
		</ul>
	</nav>
	<br>

	<div class="container">
		<h2>Lojas</h2>
		
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="loja" items="${requestScope['lojasList']}">
				<tr>
					<td>${loja.id()}</td>
					<td><a href="${loja.url()}" target="_blank">${loja.nome()}</a></td>
					<td><a href="${pageContext.request.contextPath}/lojas/alterar?id=${loja.id()}">Alterar</a></td>
					<td><a href="${pageContext.request.contextPath}/lojas/excluir?id=${loja.id()}">Excluir</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
	</div>
</body>
</html>