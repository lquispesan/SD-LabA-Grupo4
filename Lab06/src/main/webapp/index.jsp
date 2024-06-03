<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Compras de Productos</title>
</head>
<body>
    <h1>Compras de Productos</h1>
    <form action="calcular" method="post">
        <label for="nombreProducto">Nombre del Producto:</label><br>
        <input type="text" id="nombreProducto" name="nombreProducto" required><br>
        <label for="precioProducto">Precio:</label><br>
        <input type="number" id="precioProducto" name="precioProducto" step="0.01" required><br>
        <label for="cantidadProducto">Cantidad:</label><br>
        <input type="number" id="cantidadProducto" name="cantidadProducto" required><br><br>
        <input type="submit" value="Calcular Total">
    </form>
</body>
</html>