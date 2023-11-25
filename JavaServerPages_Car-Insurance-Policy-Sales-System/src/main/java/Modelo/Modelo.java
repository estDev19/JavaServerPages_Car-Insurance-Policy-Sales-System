package Modelo;

import java.util.Base64;

public class Modelo {
    int idModelo;
    String nombre;
    int idMarca;
    int fechaAnio;
    byte[] imagen;
    String base64Image;

    public Modelo(int idModelo, String nombre, int idMarca, int fechaAnio, byte[] imagen) {
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.idMarca = idMarca;
        this.fechaAnio = fechaAnio;
        this.imagen = imagen;
        this.base64Image = Base64.getEncoder().encodeToString(imagen);
    }
    
    public Modelo() {
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getFechaAnio() {
        return fechaAnio;
    }

    public void setFechaAnio(int fechaAnio) {
        this.fechaAnio = fechaAnio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
        this.base64Image = Base64.getEncoder().encodeToString(imagen);
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }    
}
