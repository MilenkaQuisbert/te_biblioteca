<%@page import="com.emergentes.modelo.Categoria"%>
<%@page import="com.emergentes.modelo.GestorCategoria"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    if(session.getAttribute("cate")==null){
    GestorCategoria objeto1=new GestorCategoria();
    
    objeto1.insertarCategoria(new Categoria(1, "Novela"));
    objeto1.insertarCategoria(new Categoria(2, "Cuento"));
    objeto1.insertarCategoria(new Categoria(3, "Historia"));
   
 
    session.setAttribute("cate", objeto1);
    

    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Categorias</h1>
        <a href="ControllerCategoria?op=nuevo">Nuevo</a>
        <table border="1">
          
                <tr>
                    <th>Id</th>
                    <th>Categoría</th>
                    <th></th>
                    <th></th>
                </tr>
           <c:forEach var ="item" items ="${sessionScope.cate.getLista()}">
            
                <tr>
                    <td>${item.id}</td>
                    <td>${item.categoria}</td>
                    <td><a href="ControllerCategoria?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="ControllerCategoria?op=eliminar&id=${item.id}"
                            onclick='return confirm("Esta seguro de eliminar el registro?");'>Eliminar</a></td>
                </tr>
                </c:forEach>
           
        </table>
        <a href="index.jsp">Volver Biblioteca</a>

    </body>
</html>
