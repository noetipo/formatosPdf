package com.example.formatospdf.dto;

public class Verificador {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    private String nombre;

    public Verificador(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    private String documento;
}
