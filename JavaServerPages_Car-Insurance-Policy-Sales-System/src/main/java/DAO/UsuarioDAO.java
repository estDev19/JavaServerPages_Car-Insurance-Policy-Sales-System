package DAO;

import Config.Conexion;
import Modelo.Poliza;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    PolizaDAO polizaDAO = new PolizaDAO();

    public Usuario validar(String id, String clave) {
        Usuario user = new Usuario();
        String sql = "select * from usuario where identificacion=? and clave=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setIdentificacion(rs.getString("identificacion"));
                user.setClave(rs.getString("clave"));
                user.setNombre(rs.getString("nombre"));
                user.setTelefono(rs.getString("telefono"));
                user.setCorreo(rs.getString("correo"));
                user.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el usuario validado");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public Usuario existe(String id) {
        Usuario user = new Usuario();
        String sql = "select * from usuario where identificacion=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setIdentificacion(rs.getString("identificacion"));
                user.setClave(rs.getString("clave"));
                user.setNombre(rs.getString("nombre"));
                user.setTelefono(rs.getString("telefono"));
                user.setCorreo(rs.getString("correo"));
                user.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario validado");

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public boolean agregar(Usuario e) {
        String sql = "insert into usuario(identificacion,clave,nombre,telefono,correo,rol) values (?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getIdentificacion());
            ps.setString(2, e.getClave());
            ps.setString(3, e.getNombre());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getCorreo());
            ps.setString(6, e.getRol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al ingresar el usuario");
            return false;
        }
        return true;
    }

    public void delete(String id) {
        String sql = "delete from usuario where identificacion=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al eliminar el usuario");
        }
    }

    public int actualizar(Usuario e) {
        String sql = "update usuario set identificacion=?, clave=?, nombre=?, telefono=?, correo=?, rol=? where identificacion=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getIdentificacion());
            ps.setString(2, e.getClave());
            ps.setString(3, e.getNombre());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getCorreo());
            ps.setString(6, e.getRol());
            ps.setString(7, e.getIdentificacion());
            r = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al actualizar el usuario");
        }
        return r;
    }

    public List listar() {
        String sql = "select * from usuario";
        List<Usuario> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setRol(rs.getString("rol"));

                List<Poliza> polizas = polizaDAO.listarPorUsuario(usuario.getIdentificacion());
                usuario.setListaPolizas(polizas);

                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario");
        }
        return lista;
    }

    public List listarClientes() {
        String sql = "select * from usuario where rol='Usuario'";  
        List<Usuario> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setRol(rs.getString("rol"));

                List<Poliza> polizas = polizaDAO.listarPorUsuario(usuario.getIdentificacion());
                usuario.setListaPolizas(polizas);

                lista.add(usuario);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario");
        }
        return lista;
    }

    public Usuario obtener(String idUsuario) {
        String sql = "SELECT * FROM usuario WHERE identificacion =?";
        Usuario usuario = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {

                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setRol(rs.getString("rol"));

                List<Poliza> polizas = polizaDAO.listarPorUsuario(usuario.getIdentificacion());
                usuario.setListaPolizas(polizas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario por Id");
        }
        return usuario;
    }

}
