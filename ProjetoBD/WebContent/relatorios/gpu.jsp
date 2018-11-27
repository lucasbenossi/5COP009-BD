<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<title>Relatório GPUs</title>
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
		
	<div class="container-fluid">
		<h2>Relatório GPUs</h2>
		<p>Preço por Performance</p>
		<br>
		
		<div id="grafico"></div>
		<script>
			var data = [{
				x: ${x},
				y: ${y},
				text: ${text},
				type: 'bar',
			}];
			
			Plotly.newPlot("grafico", data);
		</script>
		<br>
		
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Preço</th>
					<th>g3dMark</th>
					<th>Loja</th>
					<th>Preco por Performance</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="gpu" items="${requestScope['gpusList']}">
				<tr>
					<td>${gpu.getProduto().getId()}</td>
					<td><a href="${gpu.getProduto().getUrl()}" target="_blank">${gpu.getGpu().name()}</a></td>
					<td>${gpu.getProduto().getPreco()}</td>
					<td>${gpu.getGpu().g3dMark()}</td>
					<td>${gpu.getNomeLoja()}</td>
					<td>${gpu.getPrecoPorPerformance()}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
	</div>

</body>
</html>