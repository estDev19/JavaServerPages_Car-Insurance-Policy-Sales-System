/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.Conexion;
import Modelo.Categoria;
import Modelo.Cobertura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    CoberturaDAO coberturaDAO = new CoberturaDAO();

    public Categoria obtener(int idCategoria) {
        String sql = "SELECT * FROM categoria WHERE id_categoria=?";
        Categoria categoria = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

                List<Cobertura> coberturas = coberturaDAO.listarCoberturas(categoria.getIdCategoria());
                categoria.setListaCoberturas(coberturas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la categoría por ID");
        }
        return categoria;
    }

    public boolean agregar(Categoria categoria) {
        String sql = "INSERT INTO categoria (descripcion) VALUES (?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getDescripcion());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la categoria");
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

    public void delete(int id_categoria) {
        String sql = "delete from categoria where id_categoria=" + id_categoria;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la categoria");
        }
    }

    public List listar() {
        String sql = "select * from categoria";
        List<Categoria> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

                List<Cobertura> coberturas = coberturaDAO.listarCoberturas(categoria.getIdCategoria());
                categoria.setListaCoberturas(coberturas);

                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la categoria");
        }
        return lista;
    }
}
