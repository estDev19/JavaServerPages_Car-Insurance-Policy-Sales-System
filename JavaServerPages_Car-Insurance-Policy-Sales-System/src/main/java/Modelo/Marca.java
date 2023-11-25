package Modelo;

import java.util.List;

public class Marca {
    int idMarca;
    String nombre;
    List<Modelo> listaModelos;

    public Marca(int idMarca, String nombre, List<Modelo> listaModelos) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.listaModelos = listaModelos;
    }

    public Marca() {
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Modelo> getListaModelos() {
        return listaModelos;
    }

    public void setListaModelos(List<Modelo> listaModelos) {
        this.listaModelos = listaModelos;
    }

}
