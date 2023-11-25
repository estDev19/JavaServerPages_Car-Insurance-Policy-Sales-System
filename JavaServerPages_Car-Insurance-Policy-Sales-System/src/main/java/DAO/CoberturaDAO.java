package DAO;

import Config.Conexion;
import Modelo.Cobertura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoberturaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Cobertura obtener(int idCobertura) {
        Cobertura cobertura = new Cobertura();
        String sql = "SELECT * FROM cobertura WHERE id_cobertura = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCobertura); //int, no String...revisar        
            rs = ps.executeQuery();

            while (rs.next()) {
                cobertura.setIdCobertura(rs.getInt("id_cobertura"));
                cobertura.setDescripcion(rs.getString("descripcion"));
                cobertura.setCosto(rs.getDouble("costo"));
                cobertura.setIdCategoria(rs.getInt("id_categoria"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la cobertura");
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

        return cobertura;
    }

    public boolean agregar(Cobertura cobertura) {
        String sql = "INSERT INTO cobertura (descripcion, costo, id_categoria) VALUES (?, ?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cobertura.getDescripcion());
            ps.setDouble(2, cobertura.getCosto());
            ps.setInt(3, cobertura.getIdCategoria());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la cobertura");
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
        return false;
    }

    public void delete(int id_cobertura) {
        String sql = "delete from cobertura where id_cobertura=" + id_cobertura;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la cobertura");
        }
    }

    public List listar() {
        String sql = "select * from cobertura";
        List<Cobertura> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cobertura cobertura = new Cobertura();
                cobertura.setIdCobertura(rs.getInt("id_cobertura"));
                cobertura.setDescripcion(rs.getString("descripcion"));
                cobertura.setCosto(rs.getDouble("costo"));
                cobertura.setIdCategoria(rs.getInt("id_categoria"));

                lista.add(cobertura);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtenerla cobertura validada");
        }
        return lista;
    }

    public List<Cobertura> listarCoberturas(int idCategoria) {
        String sql = "SELECT * FROM cobertura WHERE id_categoria=?";
        List<Cobertura> listaCoberturas = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cobertura cobertura = new Cobertura();
                cobertura.setIdCobertura(rs.getInt("id_cobertura"));
                cobertura.setDescripcion(rs.getString("descripcion"));
                cobertura.setCosto(rs.getDouble("costo"));
                cobertura.setIdCategoria(rs.getInt("id_categoria"));

                listaCoberturas.add(cobertura);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las coberturas");
        }
        return listaCoberturas;
    }

}
