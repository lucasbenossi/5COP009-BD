<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Alterar GPU</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/cpus">GPUs</a>
			</li>
		</ul>
	</nav>
	<br>
	
	<div class="container">
		<h2>Alterar GPU</h2>
		<form action="${pageContext.request.contextPath}/gpus/alterar_processa" method="POST">
			<input type="hidden" name="id" value="${gpu.id()}" />
			<div class="form-group">
				<label class="">Name</label>
				<input class="form-control" type="text" name="name" value="${gpu.name()}"/>
			</div>
			<div class="form-group">
				<label class="">g3dMark</label>
				<input class="form-control" type="text" name="g3dMark" value="${gpu.g3dMark()}"/>
			</div>
			<div class="form-group">
				<label class="">g2dMark</label>
				<input class="form-control" type="text" name="g2dMark" value="${gpu.g2dMark()}"/>
			</div>
			<input class="btn btn-primary" type="submit" value="Inserir" />
		</form>
		<br>
		
	</div>
</body>
</html>