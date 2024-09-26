package com.softnahu.inmueblerecuperatoriovargas.model;

public class Inmueble {

    private String codigo;

    private String descripcion;

    private int cantAmbiente;

    private String direccion;

    private double precio;

    public Inmueble(String codigo, String descripcion, int cantAmbiente, String direccion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantAmbiente = cantAmbiente;
        this.direccion = direccion;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantAmbiente() {
        return cantAmbiente;
    }

    public void setCantAmbiente(int cantAmbiente) {
        this.cantAmbiente = cantAmbiente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
