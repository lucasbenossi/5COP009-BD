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
<title>Relatório CPUs</title>
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
		<h2>Relatório CPUs</h2>
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
					<th>ScoreMultiCore</th>
					<th>Loja</th>
					<th>Preco por Performance</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cpu" items="${requestScope['cpusList']}">
				<tr>
					<td>${cpu.getId()}</td>
					<td><a href="${cpu.getUrl()}" target="_blank">${cpu.getName()}</a></td>
					<td>${cpu.getPreco()}</td>
					<td>${cpu.getScore()}</td>
					<td>${cpu.getNomeLoja()}</td>
					<td>${cpu.getPrecoPorPerformance()}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
	</div>

</body>
</html>