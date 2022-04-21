<%@page import="com.emergentes.modelo.Libro"%>
<%@page import="com.emergentes.modelo.GestorLibros"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("estante")==null){
    GestorLibros objeto1=new GestorLibros();
    
    objeto1.insertarLibro(new Libro(1, "La Ilíada", "Homero","Sí","Novela"));
    objeto1.insertarLibro(new Libro(2, "Harry Potter", "J.K. Rowling","No","Novela"));
    objeto1.insertarLibro(new Libro(3, "Crepúsculo", "Stephenie Meyer","Sí","Novela"));
   
 
    session.setAttribute("estante", objeto1);
    

    }
%>
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Libros</h1>
        <a href="ControllerLibro?op=nuevo">Nuevo</a>
        <table border="1">
          
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Disponible</th>
                    <th>Categoria</th>
                    <th></th>
                    <th></th>
                </tr>
           <c:forEach var ="item" items ="${sessionScope.estante.getLista()}">
            
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.autor}</td>
                    <td>${item.disponible}</td>
                    <td>${item.categoria}</td>
                    <td><a href="ControllerLibro?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="ControllerLibro?op=eliminar&id=${item.id}"
                            onclick='return confirm("Esta seguro de eliminar el registro?");'>Eliminar</a></td>
                </tr>
                </c:forEach>
           
        </table>
        <a href="index.jsp">Volver Biblioteca</a>

    </body>
</html>
