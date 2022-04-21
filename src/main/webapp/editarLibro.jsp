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
         <h1>
            <c:if test="${requestScope.op =='nuevo'}" var="res" scope="request">
                Registro de
            </c:if>
                <c:if test="${requestScope.op=='editar'}" var="res" scope="request">
                    Modificar
                </c:if>
                    Libro
                
            
        </h1>
        <jsp:useBean id="miLibro" scope="request" class="com.emergentes.modelo.Libro" />
        <form action="ControllerLibro" method ="post">
            <table>
                    <tr>
                        <td>Id</td>
                        <td><input type="text" name="id" value="<jsp:getProperty name="miLibro" property="id" />" />
                        </td>
                    </tr>
                    <tr>
                        <td>Título</td>
                        <td><input type="text" name="titulo" value="<jsp:getProperty name="miLibro" property="titulo" />" />
                        </td>
                    </tr>
                    <tr>
                        <td>Autor</td>
                        <td><input type="text" name="autor" value="<jsp:getProperty name="miLibro" property="autor" />" />
                        </td>
                    </tr>
                    <tr>
                        <td>Disponible</td>
                        <td>
                            <select name="disponible">
                                <option value ="Si"
                                        <c:if test="${miLibro.disponible == 'Si'}"
                                              var="res" scope="request">
                                            selected
                                        </c:if>
                                            >Si</option>
                                <option value ="No"
                                        <c:if test="${miLibro.disponible == 'No'}"
                                              var="res" scope="request">
                                            selected
                                        </c:if>
                                            >No</option>
                              
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Categoria</td>
                        <td> <select name="categoria">
                                <c:forEach var ="item" items ="${sessionScope.cate.getLista()}">
                                <option value ="${item.categoria}"
                                        <c:if test="${miLibro.categoria == '${item.categoria}'}"
                                              var="res" scope="request">
                                            selected
                                        </c:if>
                                            >${item.categoria}</option>
                                
                                
                         </c:forEach>
                                </select>
                        </td>
                            
                    </tr>
                    <tr>
                        <td>
                            <input type="hidden" name="opg" value="${requestScope.op}" />   
                            <input type="hidden" name="op" value="grabar" />   

                        </td>
                        <td><input type="submit" value="Enviar" name="Enviar" /></td>
                    </tr>
            </table>

        </form>
    </body>
</html>
