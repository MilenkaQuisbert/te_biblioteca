package com.emergentes.controlador;

import com.emergentes.modelo.GestorLibros;
import com.emergentes.modelo.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerLibro", urlPatterns = {"/ControllerLibro"})
public class ControllerLibro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Libro objLibro = new Libro();
        int id;
        int pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null) ? request.getParameter("op") : "view";

        if (op.equals("nuevo")) {
            HttpSession ses = request.getSession();
            GestorLibros estante = (GestorLibros) ses.getAttribute("estante");
            objLibro.setId(estante.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miLibro", objLibro);
            request.getRequestDispatcher("editarLibro.jsp").forward(request, response);

        }
        if (op.equals("editar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorLibros estante = (GestorLibros) ses.getAttribute("estante");
            pos = estante.ubicarLibro(id);
            objLibro = estante.getLista().get(pos);

            request.setAttribute("op", op);
            request.setAttribute("miLibro", objLibro);
            request.getRequestDispatcher("editarLibro.jsp").forward(request, response);

        }
        if (op.equals("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorLibros estante = (GestorLibros) ses.getAttribute("estante");
            pos = estante.ubicarLibro(id);
            estante.eliminarLibro(pos);
            ses.setAttribute("estante", estante);
            response.sendRedirect("libros.jsp");
        }
        if (op.equals("view")) {
            response.sendRedirect("libros.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Libro objLibro = new Libro();
        int pos;
        String op = request.getParameter("op");

        if (op.equals("grabar")) {
            // recuperar valores del formulario 
            //verificar si es nuevo o es una modificaion
            objLibro.setId(Integer.parseInt(request.getParameter("id")));
            objLibro.setTitulo(request.getParameter("titulo"));
            objLibro.setAutor(request.getParameter("autor"));
            objLibro.setDisponible(request.getParameter("disponible"));
            objLibro.setCategoria(request.getParameter("categoria"));

            HttpSession ses = request.getSession();
            GestorLibros estante = (GestorLibros) ses.getAttribute("estante");

            String opg = request.getParameter("opg");
            if (opg.equals("nuevo")) {
                estante.insertarLibro(objLibro);
            } else {
                pos = estante.ubicarLibro(objLibro.getId());
                estante.editarLibro(pos, objLibro);
            }
            ses.setAttribute("estante", estante);
            response.sendRedirect("libros.jsp");
        }
    }

}
