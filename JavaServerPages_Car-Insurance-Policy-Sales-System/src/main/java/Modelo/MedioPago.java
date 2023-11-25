package Modelo;


public class MedioPago {
    
    int idMedio;
    String nombre;
    int ccv; //Codigo
    String numeroTarjeta;
    int fechaAnio;
    int fechaMes;

    public int getIdMedio() {
        return idMedio;
    }

    public void setIdMedio(int idMedio) {
        this.idMedio = idMedio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getFechaAnio() {
        return fechaAnio;
    }

    public void setFechaAnio(int fechaAnio) {
        this.fechaAnio = fechaAnio;
    }

    public int getFechaMes() {
        return fechaMes;
    }

    public void setFechaMes(int fechaMes) {
        this.fechaMes = fechaMes;
    }

    public MedioPago() {
    }

    public MedioPago(int idMedio, String nombre, int ccv, String numeroTarjeta, int fechaAnio, int fechaMes) {
        this.idMedio = idMedio;
        this.nombre = nombre;
        this.ccv = ccv;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaAnio = fechaAnio;
        this.fechaMes = fechaMes;
    }

}
