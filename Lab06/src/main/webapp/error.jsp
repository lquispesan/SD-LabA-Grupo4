<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Error en la Compra</h1>
    <p><%= request.getAttribute("mensaje") %></p>
    <a href="index.jsp">Volver</a>
</body>
</html>