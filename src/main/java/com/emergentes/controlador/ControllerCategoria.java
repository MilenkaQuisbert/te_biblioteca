
package com.emergentes.controlador;

import com.emergentes.modelo.Categoria;
import com.emergentes.modelo.GestorCategoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerCategoria", urlPatterns = {"/ControllerCategoria"})
public class ControllerCategoria extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria objCategoria = new Categoria();
        int id;
        int pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null) ? request.getParameter("op") : "view";

        if (op.equals("nuevo")) {
            HttpSession ses = request.getSession();
            GestorCategoria cate = (GestorCategoria) ses.getAttribute("cate");
            objCategoria.setId(cate.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miCategoria", objCategoria);
            request.getRequestDispatcher("editarCategoria.jsp").forward(request, response);

        }
        if (op.equals("editar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorCategoria cate = (GestorCategoria) ses.getAttribute("cate");
            pos = cate.ubicarCategoria(id);
            objCategoria = cate.getLista().get(pos);

            request.setAttribute("op", op);
            request.setAttribute("miCategoria", objCategoria);
            request.getRequestDispatcher("editarCategoria.jsp").forward(request, response);

        }
        if (op.equals("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorCategoria cate = (GestorCategoria) ses.getAttribute("cate");
            pos = cate.ubicarCategoria(id);
            cate.eliminarCategoria(pos);
            ses.setAttribute("cate", cate);
            response.sendRedirect("categorias.jsp");
        }
        if (op.equals("view")) {
            response.sendRedirect("categorias.jsp");
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria objCategoria = new Categoria();
        int pos;
        String op = request.getParameter("op");

        if (op.equals("grabar")) {
            // recuperar valores del formulario 
            //verificar si es nuevo o es una modificaion
            objCategoria.setId(Integer.parseInt(request.getParameter("id")));
            objCategoria.setCategoria(request.getParameter("categoria"));

            HttpSession ses = request.getSession();
            GestorCategoria cate = (GestorCategoria) ses.getAttribute("cate");

            String opg = request.getParameter("opg");
            if (opg.equals("nuevo")) {
                cate.insertarCategoria(objCategoria);
            } else {
                pos = cate.ubicarCategoria(objCategoria.getId());
                cate.editarCategoria(pos, objCategoria);
            }
            ses.setAttribute("cate", cate);
            response.sendRedirect("categorias.jsp");
        }
    }



}
