package Modelo;

import java.util.List;

public class Categoria {
    
    int idCategoria;
    String descripcion;
    List<Cobertura> listaCoberturas;

    public Categoria(int idCategoria, String descripcion, List<Cobertura> listaCoberturas) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.listaCoberturas = listaCoberturas;
    }

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Cobertura> getListaCoberturas() {
        return listaCoberturas;
    }

    public void setListaCoberturas(List<Cobertura> listaCoberturas) {
        this.listaCoberturas = listaCoberturas;
    }

    
    
}
