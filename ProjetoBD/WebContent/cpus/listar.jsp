<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
<title>CPUs</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/cpus">CPUs</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/cpus/inserir">Inserir</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/cpus/limpar">Limpar</a>
			</li>
		</ul>
	</nav>
	<br>

	<div class="container">
		<h2>CPUs</h2>
		
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Cores</th>
					<th>Threads</th>
					<th>Frequency</th>
					<th>MaxFrequency</th>
					<th>ScoreSingleCore</th>
					<th>ScoreMultiCore</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cpu" items="${requestScope['cpusList']}">
				<tr>
					<td>${cpu.id()}</td>
					<td>${cpu.name()}</td>
					<td>${cpu.cores()}</td>
					<td>${cpu.threads()}</td>
					<td>${cpu.frequency()}</td>
					<td>${cpu.maxFrequency()}</td>
					<td>${cpu.scoreSingleCore()}</td>
					<td>${cpu.scoreMultiCore()}</td>
					<td><a href="${pageContext.request.contextPath}/cpus/alterar?id=${cpu.id()}">Alterar</a></td>
					<td><a href="${pageContext.request.contextPath}/cpus/excluir?id=${cpu.id()}">Excluir</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
	</div>
</body>
</html>