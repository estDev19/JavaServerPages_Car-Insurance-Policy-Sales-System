package DAO;

import Config.Conexion;
import Modelo.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ModeloDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Modelo obtener(int idModelo) {
        Modelo modelo = new Modelo();
        String sql = "SELECT * FROM modelo WHERE id_modelo = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idModelo);
            rs = ps.executeQuery();

            while (rs.next()) {
                modelo.setIdModelo(rs.getInt("id_modelo"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setIdMarca(rs.getInt("id_marca"));
                modelo.setFechaAnio(rs.getInt("año"));
                modelo.setImagen(rs.getBytes("imagen")); // Obtener la imagen
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el modelo");
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
            }
        }

        return modelo;
    }

    public Modelo agregar(Modelo modelo) {
        String sql = "INSERT INTO modelo (nombre, id_marca, año, imagen) VALUES (?, ?, ?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, modelo.getNombre());
            ps.setInt(2, modelo.getIdMarca());
            ps.setInt(3, modelo.getFechaAnio());
            ps.setBytes(4, modelo.getImagen()); // Insertar la imagen

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        modelo.setIdModelo(generatedKeys.getInt(1));
                        return modelo;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el modelo");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public void delete(int id_modelo) {
        String sql = "delete from modelo where id_modelo=" + id_modelo;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el modelo");
        }
    }

    public List listar() {
        String sql = "select * from modelo";
        List<Modelo> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("id_modelo"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setIdMarca(rs.getInt("id_marca"));
                modelo.setFechaAnio(rs.getInt("año"));
                modelo.setImagen(rs.getBytes("imagen")); // Incluir la imagen

                lista.add(modelo);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de modelos");
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
                System.out.println("Error al cerrar conexiones en el método listar");
            }
        }
        return lista;
    }

    public List<Modelo> listarPorMarca(int idMarca) {
        List<Modelo> lista = new ArrayList<>();
        String sql = "select * from modelo where id_marca=?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMarca);
            rs = ps.executeQuery();

            while (rs.next()) {
                Modelo modelo = new Modelo();

                modelo.setIdModelo(rs.getInt("id_modelo"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setIdMarca(rs.getInt("id_marca"));
                modelo.setFechaAnio(rs.getInt("año"));

                byte[] imagenBytes = rs.getBytes("imagen");
                if (imagenBytes != null) {
                    String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
                    modelo.setBase64Image(base64Image);
                }

                lista.add(modelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la lista de modelos");
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
        return lista;
    }

}
