/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author esteb
 */
public class Poliza {
    int idPoliza;
    String numeroPlaca;
    double valorAsegurado;
    String plazo;
    String fechaCreacion;
    String fechaVencimiento; 
    String idUsuario;
    int idModelo;
    int idMarca;
    int idCobertura;
    int idCategoria;

    public Poliza(int idPoliza, String numeroPlaca, double valorAsegurado, String plazo, String fechaCreacion, String fechaVencimiento, String idUsuario, int idModelo, int idMarca, int idCobertura, int idCategoria) {
        this.idPoliza = idPoliza;
        this.numeroPlaca = numeroPlaca;
        this.valorAsegurado = valorAsegurado;
        this.plazo = plazo;
        this.fechaCreacion = fechaCreacion;
        this.fechaVencimiento = fechaVencimiento;
        this.idUsuario = idUsuario;
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.idCobertura = idCobertura;
        this.idCategoria = idCategoria;
    }

    public Poliza() {
    }
    
    
    // public int getId(){ return idPoliza; }
    
    public int getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(int idPoliza) {
        this.idPoliza = idPoliza;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public double getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(double valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(int idCobertura) {
        this.idCobertura = idCobertura;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
