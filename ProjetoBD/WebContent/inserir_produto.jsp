<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir produto</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/tudo.css" rel="stylesheet">
</head>
<body>
	<a href="index.jsp">Home</a>
	<h2>Inserir</h2>
	<form action="inserir_produto_processa" method="POST">
		Nome: <input type="text" name="nome" /><br>
		Preco: <input type="text" name="preco" /><br>
		Parcelas: <input type="text" name="parcelas" /><br>
		Valor Parcela: <input type="text" name="valorParcela" /><br>
		Disponivel:
			<input type="radio" name="disponivel" value="true" checked/> Sim
			<input type="radio" name="disponivel" value="false"/> Não<br>
		Loja: <input type="text" name="loja" /><br>
		<input type="submit" value="Inserir" />
	</form>
	<br>
	<h2>JSON</h2>
	<form action="inserir_json_processa" method="POST" enctype="multipart/form-data">
		<!-- <textarea rows="20" cols="80" name="json"></textarea><br> -->
		<input type="file" name="json" accept="application/json">
		<input type="submit" value="Inserir" />
	</form>
</body>
</html>