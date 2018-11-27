<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
		</ul>
	</nav>
	<br>

	<div class="container">
		<a href="${pageContext.request.contextPath}/lojas">Lojas</a><br>
		<a href="${pageContext.request.contextPath}/produtos">Produtos</a><br>
		<a href="${pageContext.request.contextPath}/cpus">CPUs</a><br>
		<a href="${pageContext.request.contextPath}/gpus">GPUs</a><br>
		<a href="${pageContext.request.contextPath}/relatorios/ssd">Relatório SSDs</a><br>
		<a href="${pageContext.request.contextPath}/relatorios/cpu">Relatório CPUs</a><br>
		<a href="${pageContext.request.contextPath}/relatorios/gpu">Relatório GPUs</a><br>
	</div>
</body>
</html>