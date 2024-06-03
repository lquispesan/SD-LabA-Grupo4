
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Resultado de la Compra</title>
</head>
<body>
    <h1>Resultado de la Compra</h1>
    <p>Producto: <%= request.getAttribute("nombreProducto") %></p>
    <p>Total a pagar: $<%= request.getAttribute("total") %></p>
    <a href="index.jsp">Volver</a>
</body>
</html>