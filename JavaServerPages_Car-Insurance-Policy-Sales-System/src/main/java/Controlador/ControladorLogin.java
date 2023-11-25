/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.MedioPagoDAO;
import DAO.UsuarioDAO;
import DAO.PolizaDAO;
import Modelo.MedioPago;
import Modelo.Poliza;
import Modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author jeffr
 */
public class ControladorLogin extends HttpServlet {

    UsuarioDAO userDao = new UsuarioDAO();
    MedioPagoDAO medioPagoDao = new MedioPagoDAO();
    PolizaDAO polizaDAO = new PolizaDAO();
    Usuario user = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion.equals("Ingresar")) {
            String userID = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");

            user = userDao.validar(userID, pass);

            if (user.getIdentificacion() != null) {
                List<Poliza> polizasUsuario = polizaDAO.listarPorUsuario(user.getIdentificacion());
                user.setListaPolizas(polizasUsuario);
                
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);

                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if (accion.equals("Registrar")) {
            String id = request.getParameter("txtidentificacion");
            String nombreCompleto = request.getParameter("txtnombre");
            String telefono = request.getParameter("txttelefono");
            String email = request.getParameter("txtcorreo");
            String password = request.getParameter("txtpass");
            String tarjetaNom = request.getParameter("txtnombretarjeta");
            String tarjetaNum = request.getParameter("txttarjetanum");
            String tarjetaanio = request.getParameter("txtanio");
            String tarjetaMes = request.getParameter("txtmes");
            String ccv = request.getParameter("txtccv");

            if (id != null && nombreCompleto != null && telefono != null && email != null && password != null
                    && tarjetaNom != null && tarjetaNum != null && tarjetaanio != null && tarjetaMes != null && ccv != null) {
                user = userDao.existe(id);
                if (user.getIdentificacion() == null) {
                    Usuario newUser = new Usuario();
                    newUser.setIdentificacion(id);
                    newUser.setNombre(nombreCompleto);
                    newUser.setCorreo(email);
                    newUser.setClave(password);
                    newUser.setTelefono(telefono);
                    newUser.setRol("Usuario");
                    System.out.println(newUser.toString());
                    if (userDao.agregar(newUser)) {
                        int anioTarjeta = Integer.parseInt(tarjetaanio);
                        int mesTarjeta = Integer.parseInt(tarjetaMes);
                        int ccvTarjeta = Integer.parseInt(ccv);

                        MedioPago newMedioPago = new MedioPago();
                        newMedioPago.setNombre(tarjetaNom);
                        newMedioPago.setCcv(ccvTarjeta);
                        newMedioPago.setFechaMes(mesTarjeta);
                        newMedioPago.setFechaAnio(anioTarjeta);
                        newMedioPago.setNumeroTarjeta(tarjetaNum);
                        medioPagoDao.agregar(newMedioPago, newUser.getIdentificacion());

                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        userDao.delete(id);

                        request.getRequestDispatcher("Registrar.jsp").forward(request, response);
                    }

                } else {

                    request.getRequestDispatcher("Registrar.jsp").forward(request, response);
                }

            } else {
                request.getRequestDispatcher("Registrar.jsp").forward(request, response);
            }

        } else {
            HttpSession session = request.getSession();
            session.invalidate();

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
