/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.Conexion;
import Modelo.Poliza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esteb
 */
public class PolizaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listarPorUsuario(String idUsuario) {
        List<Poliza> lista = new ArrayList<>();

        String sql = "select * from poliza where usuario_identificacion=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                Poliza poliza = new Poliza();
                poliza.setIdPoliza(rs.getInt("id_poliza"));
                poliza.setNumeroPlaca(rs.getString("numero_placa"));
                poliza.setValorAsegurado(rs.getDouble("valor_asegurado"));
                poliza.setPlazo(rs.getString("plazo"));
                poliza.setFechaCreacion(rs.getString("fecha_creacion"));
                poliza.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                poliza.setIdUsuario(rs.getString("usuario_identificacion"));
                poliza.setIdModelo(rs.getInt("modelo_id_modelo"));
                poliza.setIdMarca(rs.getInt("modelo_id_marca"));
                poliza.setIdCobertura(rs.getInt("cobertura_id_cobertura"));
                poliza.setIdCategoria(rs.getInt("cobertura_id_categoria"));

                lista.add(poliza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la lista de polizas");
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

    public List listarPorPlaca(String numeroPlaca, String identificacion) {
        List<Poliza> lista = new ArrayList<>();

        String sql = "select * from poliza where numero_placa LIKE ? and usuario_identificacion=?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + numeroPlaca + "%");
            ps.setString(2, identificacion);
            rs = ps.executeQuery();
            while (rs.next()) {
                Poliza poliza = new Poliza();
                poliza.setIdPoliza(rs.getInt("id_poliza"));
                poliza.setNumeroPlaca(rs.getString("numero_placa"));
                poliza.setValorAsegurado(rs.getDouble("valor_asegurado"));
                poliza.setPlazo(rs.getString("plazo"));
                poliza.setFechaCreacion(rs.getString("fecha_creacion"));
                poliza.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                poliza.setIdUsuario(rs.getString("usuario_identificacion"));
                poliza.setIdModelo(rs.getInt("modelo_id_modelo"));
                poliza.setIdMarca(rs.getInt("modelo_id_marca"));
                poliza.setIdCobertura(rs.getInt("cobertura_id_cobertura"));
                poliza.setIdCategoria(rs.getInt("cobertura_id_categoria"));

                lista.add(poliza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la lista de polizas");
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

    public Poliza agregar(Poliza p) {
        String sql = "insert into poliza(numero_placa,valor_asegurado,plazo,fecha_creacion,fecha_vencimiento,usuario_identificacion,modelo_id_modelo,modelo_id_marca,cobertura_id_cobertura,cobertura_id_categoria) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNumeroPlaca());
            ps.setDouble(2, p.getValorAsegurado());
            ps.setString(3, p.getPlazo());
            ps.setString(4, p.getFechaCreacion());
            ps.setString(5, p.getFechaVencimiento());
            ps.setString(6, p.getIdUsuario());
            ps.setInt(7, p.getIdModelo());
            ps.setInt(8, p.getIdMarca());
            ps.setInt(9, p.getIdCobertura());
            ps.setInt(10, p.getIdCategoria());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int idPoliza = rs.getInt(1);
                    p.setIdPoliza(idPoliza);
                    return p;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al ingresar la poliza");
        }
        return null;
    }

    //-------------------------------------- obtenerPorId
    public Poliza obtenerPorId(int idPoliza) {
        Poliza poliza = new Poliza();
        String sql = "SELECT * from poliza where id_poliza=?";
     // String sql = "select * from poliza where numero_placa LIKE ? and usuario_identificacion=?";
        try {

            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPoliza);
            rs = ps.executeQuery();

            while (rs.next()) {
                poliza.setIdPoliza(rs.getInt("id_poliza"));
                poliza.setNumeroPlaca(rs.getString("numero_placa"));
                poliza.setValorAsegurado(rs.getDouble("valor_asegurado"));
                poliza.setPlazo(rs.getString("plazo"));
                poliza.setFechaCreacion(rs.getString("fecha_creacion"));
                poliza.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                poliza.setIdUsuario(rs.getString("usuario_identificacion"));
                poliza.setIdModelo(rs.getInt("modelo_id_modelo"));
                poliza.setIdMarca(rs.getInt("modelo_id_marca"));
                poliza.setIdCobertura(rs.getInt("cobertura_id_cobertura"));
                poliza.setIdCategoria(rs.getInt("cobertura_id_categoria"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la poliza");
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
        return poliza;
    }
}
