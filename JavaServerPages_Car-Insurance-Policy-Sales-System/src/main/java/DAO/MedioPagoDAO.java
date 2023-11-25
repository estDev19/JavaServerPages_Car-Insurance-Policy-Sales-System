package DAO;

import Config.Conexion;
import Modelo.MedioPago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedioPagoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public MedioPago obtener(String identificacionUsuario) {
        MedioPago medioPago = new MedioPago();
        String sql = "SELECT * FROM mediopago WHERE usuario_identificacion = ?";
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, identificacionUsuario);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                medioPago.setIdMedio(rs.getInt("id_medio_pago"));
                medioPago.setNombre(rs.getString("nombre"));
                medioPago.setCcv(rs.getInt("codigo"));
                medioPago.setNumeroTarjeta(rs.getString("numero_tarjeta"));
                medioPago.setFechaAnio(rs.getInt("fecha_año"));
                medioPago.setFechaMes(rs.getInt("fecha_mes"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el medio de pago");
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

        return medioPago;
    }

    public boolean agregar(MedioPago medioPago, String identificacionUsuario) {
        String sql = "INSERT INTO mediopago (nombre, codigo, numero_tarjeta, fecha_año, fecha_mes, usuario_identificacion) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, medioPago.getNombre());
            ps.setInt(2, medioPago.getCcv());
            ps.setString(3, medioPago.getNumeroTarjeta());
            ps.setInt(4, medioPago.getFechaAnio());
            ps.setInt(5, medioPago.getFechaMes());
            ps.setString(6, identificacionUsuario);
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el medio de pago");
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
}

