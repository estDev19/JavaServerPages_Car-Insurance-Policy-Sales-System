package Modelo;

import java.util.List;


public class Usuario {
    
    String identificacion;
    String clave;
    String nombre;
    String telefono;
    String correo;
    String rol;
    MedioPago medio;
    List<Poliza> listaPolizas;  
    
        public Usuario(String identificacion, String clave, String nombre, String telefono, String correo, String rol, List<Poliza> listaPolizas) {
        this.identificacion = identificacion;
        this.clave = clave;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
        this.listaPolizas = listaPolizas;
    }

    public Usuario() {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public MedioPago getMedio() {
        return medio;
    }

    public void setMedio(MedioPago medio) {
        this.medio = medio;
    }
    
    public List<Poliza> getListaPolizas() {
        return listaPolizas;
    }
    
    

    public void setListaPolizas(List<Poliza>  listaPolizas) {
        this. listaPolizas =  listaPolizas;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "identificacion=" + identificacion + ", clave=" + clave + ", nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + ", rol=" + rol + '}';
    }
    
}
