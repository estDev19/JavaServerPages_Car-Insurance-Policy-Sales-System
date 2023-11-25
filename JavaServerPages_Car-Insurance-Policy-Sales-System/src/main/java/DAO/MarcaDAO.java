/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.Conexion;
import Modelo.Marca;
import Modelo.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ModeloDAO modeloDAO = new ModeloDAO();

    public Marca obtener(int idMarca) {
        Marca marca = new Marca();
        String sql = "SELECT * FROM marca WHERE id_marca = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMarca);
            rs = ps.executeQuery();

            while (rs.next()) {
                marca.setIdMarca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre"));

                List<Modelo> modelos = modeloDAO.listarPorMarca(marca.getIdMarca());
                marca.setListaModelos(modelos);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la marca");
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

        return marca;
    }

    public Marca agregar(Marca marca) {
        String sql = "INSERT INTO marca (nombre) VALUES (?)";
        ResultSet rs = null;

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, marca.getNombre());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int idMarca = rs.getInt(1);
                    marca.setIdMarca(idMarca);
                    return marca;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la marca");
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
        return null;
    }

    public void delete(int id_marca) {
        String sql = "delete from marca where id_marca=" + id_marca;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la marca");
        }
    }

    public List listar() {
        String sql = "select * from marca";
        List<Marca> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre"));

                List<Modelo> modelos = modeloDAO.listarPorMarca(marca.getIdMarca());
                marca.setListaModelos(modelos);

                lista.add(marca);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la marca validada");
        }
        return lista;
    }

}
