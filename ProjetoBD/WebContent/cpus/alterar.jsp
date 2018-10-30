<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Alterar CPU</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
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
		</ul>
	</nav>
	<br>
	
	<div class="container">
		<h2>Alterar CPU</h2>
		<form action="${pageContext.request.contextPath}/cpus/alterar_processa" method="POST">
			<input type="hidden" name="id" value="${cpu.id()}" />
			<div class="form-group">
				<label class="">Name</label>
				<input class="form-control" type="text" name="name" value="${cpu.name()}"/>
			</div>
			<div class="form-group">
				<label class="">Cores</label>
				<input class="form-control" type="text" name="cores" value="${cpu.cores()}"/>
			</div>
			<div class="form-group">
				<label class="">Threads</label>
				<input class="form-control" type="text" name="threads" value="${cpu.threads()}"/>
			</div>
			<div class="form-group">
				<label class="">Frequency</label>
				<input class="form-control" type="text" name="frequency" value="${cpu.frequency()}"/>
			</div>
			<div class="form-group">
				<label class="">MaxFrequency</label>
				<input class="form-control" type="text" name="maxFrequency" value="${cpu.maxFrequency()}"/>
			</div>
			<div class="form-group">
				<label class="">ScoreSingleCore</label>
				<input class="form-control" type="text" name="scoreSingleCore" value="${cpu.scoreSingleCore()}"/>
			</div>
			<div class="form-group">
				<label class="">ScoreMultiCore</label>
				<input class="form-control" type="text" name="scoreMultiCore" value="${cpu.scoreMultiCore()}"/>
			</div>
			<div class="form-group">
				<label class="">Url</label>
				<input class="form-control" type="text" name="url" value="${cpu.url()}"/>
			</div>
			<input class="btn btn-primary" type="submit" value="Alterar" />
		</form>
		<br>
		
	</div>
</body>
</html>