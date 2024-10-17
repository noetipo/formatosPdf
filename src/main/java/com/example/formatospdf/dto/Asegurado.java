package com.example.formatospdf.dto;

public class Asegurado {
    private Integer numero;
    private String nombresApellidos;
    private String dni;
    private String periodo;

    public Asegurado(Integer numero, String nombresApellidos, String dni, String periodo) {
        this.numero = numero;
        this.nombresApellidos = nombresApellidos;
        this.dni = dni;
        this.periodo = periodo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
