<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Inserir Produto</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/produtos">Produtos</a>
			</li>
		</ul>
	</nav>
	<br>

	<div class="container">
		<h2>Inserir Produto</h2>
		<form action="${pageContext.request.contextPath}/produtos/inserir_processa" method="POST">
			<div class="form-group">
				<label class="">Nome</label>
				<input class="form-control" type="text" name="nome"/>
			</div>
			<div class="form-group">
				<label class="">Nome Tratado</label>
				<input class="form-control" type="text" name="nomeTratado"/>
			</div>
			<div class="form-group">
				<label class="">Preco</label>
				<input class="form-control" type="text" name="preco" />
			</div>
			<div class="form-group">
				<label class="">Parcelas</label> 
				<input class="form-control" type="text" name="parcelas" />
			</div>
			<div class="form-group">
				<label class="">Valor Parcela</label> 
				<input class="form-control" type="text" name="valorParcela" />
			</div>
			<div class="form-group">
				<label class="">IdLoja</label>
				<input class="form-control" type="text" name="idLoja" />
			</div>
			<div class="form-group">
				<label class="">Url</label> 
				<input class="form-control" type="text" name="url" />
			</div>
			<input class="btn btn-primary" type="submit" value="Inserir" />
		</form>
		<br>
		
		<h2>JSON</h2>
		<form action="${pageContext.request.contextPath}/produtos/json" method="POST" enctype="multipart/form-data">
			<div class="form-group">
				<input class="form-control-file" type="file" name="json" accept="application/json">
			</div>
			<input class="btn btn-primary" type="submit" value="Inserir" />
		</form>
		<br>
		
	</div>
</body>
</html>