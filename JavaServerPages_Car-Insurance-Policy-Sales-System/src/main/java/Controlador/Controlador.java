package Controlador;

import DAO.CategoriaDAO;
import DAO.CoberturaDAO;
import java.util.Base64;
import DAO.MarcaDAO;
import DAO.ModeloDAO;
import DAO.PolizaDAO;
import DAO.UsuarioDAO;
import Modelo.Categoria;
import Modelo.Cobertura;
import Modelo.Marca;
import Modelo.Modelo;
import Modelo.Poliza;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
public class Controlador extends HttpServlet {

    UsuarioDAO userDao = new UsuarioDAO();
    MarcaDAO marcaDAO = new MarcaDAO();
    ModeloDAO modeloDAO = new ModeloDAO();
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    CoberturaDAO coberturaDAO = new CoberturaDAO();
    PolizaDAO polizaDAO = new PolizaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Boolean forwarded = false;
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        //VARIABLES VARIAS
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        int id = -1;
        if (usuario != null) {
            if (usuario.getRol().equals("Usuario")) {
                if (menu == null || menu.equals("Principal")) {
                    if (accion == null) {
                        List<Poliza> polizasUsuario = polizaDAO.listarPorUsuario(usuario.getIdentificacion());
                        usuario.setListaPolizas(polizasUsuario);
                        session.setAttribute("usuario", usuario);

                        forwarded = true;
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }
                    if (accion != null && accion.equals("Busqueda")) {
                        String numeroPlaca = request.getParameter("buscarPlaca");
                        List<Poliza> polizasUsuario = polizaDAO.listarPorPlaca(numeroPlaca, usuario.getIdentificacion());
                        usuario.setListaPolizas(polizasUsuario);
                        session.setAttribute("usuario", usuario);

                        forwarded = true;
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }
                }
                if (menu != null && menu.equals("Comprar")) {

                    if (accion == null) {
                        List<Categoria> categorias = categoriaDAO.listar();
                        session.setAttribute("categorias", categorias);
                        List<Cobertura> coberturas = coberturaDAO.listar();
                        session.setAttribute("coberturas", coberturas);
                        List<Modelo> modelos = modeloDAO.listar();
                        session.setAttribute("modelos", modelos);
                        List<Marca> marcas = marcaDAO.listar();
                        session.setAttribute("marcas", marcas);

                        forwarded = true;
                        request.getRequestDispatcher("Comprar.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("ComprarPoliza")) {

                        String numeroPlaca = request.getParameter("txtNumeroPlaca");
                        int idModelo = Integer.parseInt(request.getParameter("selectMarcaModelo"));  //falta traer la marca
                        Modelo modelo = modeloDAO.obtener(idModelo);
                        double valorAsegurado = Double.parseDouble(request.getParameter("txtValorAsegurado"));
                        String plazo = request.getParameter("selectPlazo");
                        int idCobertura = Integer.parseInt(request.getParameter("idCategoria"));
                        Cobertura cobertura = coberturaDAO.obtener(idCobertura);

                        String fechaInicio = request.getParameter("txtFechaInicio");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate localDateInicio = LocalDate.parse(fechaInicio, formatter);
                        LocalDate fechaTerminacion = localDateInicio.plusYears(1);
                        String fechaTerminacionString = fechaTerminacion.format(formatter);

                        Poliza poliza = new Poliza();
                        poliza.setNumeroPlaca(numeroPlaca);
                        poliza.setIdModelo(idModelo);
                        poliza.setIdMarca(modelo.getIdMarca());
                        poliza.setValorAsegurado(valorAsegurado);
                        poliza.setPlazo(plazo);
                        poliza.setIdCobertura(idCobertura);
                        poliza.setIdCategoria(cobertura.getIdCategoria());
                        poliza.setFechaCreacion(fechaInicio);
                        poliza.setFechaVencimiento(fechaTerminacionString);
                        poliza.setIdUsuario(usuario.getIdentificacion());
                        Poliza polizaConId = polizaDAO.agregar(poliza);
                        usuario.getListaPolizas().add(polizaConId);

                        session.setAttribute("usuario", usuario);
                        forwarded = true;
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }
                }

                if (menu != null && menu.equals("Actualizar")) {

                    if (accion == null) {
                        forwarded = true;
                        request.getRequestDispatcher("Actualizar.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("Actualizar")) {
                        String correo = request.getParameter("txtcorreo");
                        String tel = request.getParameter("txttelefono");
                        String nombre = request.getParameter("txtnombre");
                        usuario.setNombre(nombre);
                        usuario.setTelefono(tel);
                        usuario.setCorreo(correo);

                        userDao.actualizar(usuario);
                        session.setAttribute("usuario", usuario);
                        forwarded = true;
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }

                }

                if (menu != null && menu.equals("Detalles")) {
                    if (accion != null && accion.equals("MostrarDetalle")) {

                        //Se busca por el ID de la poliza, no por placa
                        int idPoliza = Integer.parseInt(request.getParameter("idPoliza"));

                        Poliza poliza = polizaDAO.obtenerPorId(idPoliza); //en el DAO hace falta un obtener por id
                        Marca marca = marcaDAO.obtener(poliza.getIdMarca());
                        Modelo modelo = modeloDAO.obtener(poliza.getIdModelo());
                        Cobertura cobertura = coberturaDAO.obtener(poliza.getIdCobertura());
                        Categoria categoria = categoriaDAO.obtener(poliza.getIdCategoria());

                        // List<Poliza> poliza = polizaDAO.listarPorPlaca();
                        session.setAttribute("detallePoliza", poliza);
                        session.setAttribute("detalleMarca", marca);
                        session.setAttribute("detalleModelo", modelo);
                        session.setAttribute("detalleCobertura", cobertura);
                        session.setAttribute("detalleCategoria", categoria);

                        forwarded = true;
                        request.getRequestDispatcher("Detalle.jsp").forward(request, response);

                        //Recuerden el admin no tiene buscador, solo el usuario
                    }
                }

                if (!forwarded) {

                    List<Poliza> polizasUsuario = polizaDAO.listarPorUsuario(usuario.getIdentificacion());
                    usuario.setListaPolizas(polizasUsuario);
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("Principal.jsp").forward(request, response);
                }

            }

            // ----------------------------------- ADMIN ----------------------------------
            if (usuario.getRol().equals("Administrador")) {

                if (menu == null || menu.equals("Principal")) {
                    forwarded = true;
                    request.getRequestDispatcher("Principal.jsp").forward(request, response);
                }
                if (menu != null && menu.equals("Marcas")) {

                    if (accion == null) {
                        List<Marca> marcas = marcaDAO.listar();
                        session.setAttribute("listaMarcas", marcas);

                        forwarded = true;
                        request.getRequestDispatcher("Marcas.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("AgregarMarca")) {
                        String nombreMarca = request.getParameter("nombreMarca");
                        Marca marca = new Marca();

                        marca.setNombre(nombreMarca);
                        marcaDAO.agregar(marca);

                        List<Marca> marcas = marcaDAO.listar();
                        session.setAttribute("listaMarcas", marcas);

                        forwarded = true;
                        request.getRequestDispatcher("Marcas.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("AgregarModelo")) {

                        String nombreModelo = request.getParameter("nombreModelo");
                        int idMarca = Integer.parseInt(request.getParameter("idMarca"));
                        int fechaAnio = Integer.parseInt(request.getParameter("fechaAnio"));

                        Part filePart = request.getPart("imagen");
                        InputStream inputStream = null;
                        if (filePart != null) {
                            inputStream = filePart.getInputStream();
                        }
                        byte[] imagen = inputStream.readAllBytes();

                        Modelo modelo = new Modelo();
                        modelo.setNombre(nombreModelo);
                        modelo.setIdMarca(idMarca);
                        modelo.setFechaAnio(fechaAnio);
                        modelo.setImagen(imagen);
                        String base64Image = Base64.getEncoder().encodeToString(modelo.getImagen());
                        modelo.setBase64Image(base64Image);
                        modeloDAO.agregar(modelo);

                        List<Marca> marcas = marcaDAO.listar();
                        session.setAttribute("listaMarcas", marcas);

                        forwarded = true;
                        request.getRequestDispatcher("Marcas.jsp").forward(request, response);
                    }

                }
                //inicio
                if (menu != null && menu.equals("Categorias")) {

                    if (accion == null) {
                        List<Categoria> categorias = categoriaDAO.listar();
                        session.setAttribute("listaCategorias", categorias);

                        forwarded = true;
                        request.getRequestDispatcher("Categorias.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("AgregarCategoria")) {
                        String descripcionCategoria = request.getParameter("descripcionCategoria");
                        Categoria categoria = new Categoria();

                        categoria.setDescripcion(descripcionCategoria);
                        categoriaDAO.agregar(categoria);

                        List<Categoria> categorias = categoriaDAO.listar();
                        session.setAttribute("listaCategorias", categorias);

                        forwarded = true;
                        request.getRequestDispatcher("Categorias.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("AgregarCobertura")) {

                        String descripcionCobertura = request.getParameter("descripcionCobertura");
                        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                        double costo = Double.parseDouble(request.getParameter("costoCobertura"));
                        //  InputStream inputStream = null;

                        Cobertura cobertura = new Cobertura();
                        cobertura.setIdCategoria(idCategoria);
                        cobertura.setDescripcion(descripcionCobertura);
                        cobertura.setCosto(costo);

                        coberturaDAO.agregar(cobertura);

                        List<Categoria> categorias = categoriaDAO.listar();
                        session.setAttribute("listaCategorias", categorias);

                        forwarded = true;
                        request.getRequestDispatcher("Categorias.jsp").forward(request, response);
                    }

                }
                ////fin
                if (menu != null && menu.equals("Clientes")) {

                    if (accion == null) {
                        List<Usuario> clientes = userDao.listarClientes();
                        session.setAttribute("listaClientes", clientes);
                        forwarded = true;
                        request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                    }

                    if (accion != null && accion.equals("MostrarDetalle")) {

                        //Se busca por el ID de la poliza, no por placa
                        int idPoliza = Integer.parseInt(request.getParameter("idPoliza"));

                        Poliza poliza = polizaDAO.obtenerPorId(idPoliza); //en el DAO hace falta un obtener por id
                        Marca marca = marcaDAO.obtener(poliza.getIdMarca());
                        Modelo modelo = modeloDAO.obtener(poliza.getIdModelo());
                        Cobertura cobertura = coberturaDAO.obtener(poliza.getIdCobertura());
                        Categoria categoria = categoriaDAO.obtener(poliza.getIdCategoria());

                        // List<Poliza> poliza = polizaDAO.listarPorPlaca();
                        session.setAttribute("detallePoliza", poliza);
                        session.setAttribute("detalleMarca", marca);
                        session.setAttribute("detalleModelo", modelo);
                        session.setAttribute("detalleCobertura", cobertura);
                        session.setAttribute("detalleCategoria", categoria);

                        forwarded = true;
                        request.getRequestDispatcher("Detalle.jsp").forward(request, response);

                        //Recuerden el admin no tiene buscador, solo el usuario
                    }

                }

                if (menu != null && menu.equals("Categorias")) {

                    if (accion == null) {
                        forwarded = true;
                        request.getRequestDispatcher("Categorias.jsp").forward(request, response);
                    }

                }

                if (menu != null) {

                    if (!forwarded) {
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }

                }

            }

        } else {
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
