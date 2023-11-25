package Modelo;

public class Cobertura {
    int idCobertura;
    String descripcion;
    double costo;
    int idCategoria;

    public Cobertura(int idCobertura, String descripcion, double costo,int idCategoria) {
        this.idCobertura = idCobertura;
        this.descripcion = descripcion;
        this.costo = costo;
        this.idCategoria = idCategoria;
    }

    public Cobertura() {
    }

    public int getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(int idCobertura) {
        this.idCobertura = idCobertura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

   
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    } 
    
}
