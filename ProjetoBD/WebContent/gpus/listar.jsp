<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
<title>GPUs</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/gpus">GPUs</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/gpus/inserir">Inserir</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/gpus/limpar">Limpar</a>
			</li>
		</ul>
	</nav>
	<br>

	<div class="container">
		<h2>GPUs</h2>
		
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>g3dMark</th>
					<th>g2dMark</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="gpu" items="${requestScope['gpusList']}">
				<tr>
					<td>${gpu.id()}</td>
					<td>${gpu.name()}</td>
					<td>${gpu.g3dMark()}</td>
					<td>${gpu.g3dMark()}</td>
					<td><a href="${pageContext.request.contextPath}/gpus/alterar?id=${gpu.id()}">Alterar</a></td>
					<td><a href="${pageContext.request.contextPath}/gpus/excluir?id=${gpu.id()}">Excluir</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
	</div>
</body>
</html>