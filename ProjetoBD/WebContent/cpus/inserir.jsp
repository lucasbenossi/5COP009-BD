<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Inserir CPU</title>
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
		<h2>Inserir CPU</h2>
		<form action="${pageContext.request.contextPath}/cpus/inserir_processa" method="POST">
			<div class="form-group">
				<label class="">Name</label>
				<input class="form-control" type="text" name="name"/>
			</div>
			<div class="form-group">
				<label class="">Cores</label>
				<input class="form-control" type="text" name="cores"/>
			</div>
			<div class="form-group">
				<label class="">Threads</label>
				<input class="form-control" type="text" name="threads"/>
			</div>
			<div class="form-group">
				<label class="">Frequency</label>
				<input class="form-control" type="text" name="frequency"/>
			</div>
			<div class="form-group">
				<label class="">MaxFrequency</label>
				<input class="form-control" type="text" name="maxFrequency"/>
			</div>
			<div class="form-group">
				<label class="">ScoreSingleCore</label>
				<input class="form-control" type="text" name="scoreSingleCore"/>
			</div>
			<div class="form-group">
				<label class="">ScoreMultiCore</label>
				<input class="form-control" type="text" name="scoreMultiCore"/>
			</div>
			<input class="btn btn-primary" type="submit" value="Inserir" />
		</form>
		<br>
		
		<h2>JSON</h2>
		<form action="${pageContext.request.contextPath}/cpus/json" method="POST" enctype="multipart/form-data">
			<div class="form-group">
				<input class="form-control-file" type="file" name="json" accept="application/json">
			</div>
			<input class="btn btn-primary" type="submit" value="Inserir" />
		</form>
		<br>
		
	</div>
</body>
</html>