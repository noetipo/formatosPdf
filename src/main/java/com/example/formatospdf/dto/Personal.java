package com.example.formatospdf.dto;

public class Personal {
    private Integer numero;
    private String apellidosNombres;
    private String dni;
    private String cargo;

    public Personal(Integer numero, String apellidosNombres, String dni, String cargo) {
        this.numero = numero;
        this.apellidosNombres = apellidosNombres;
        this.dni = dni;
        this.cargo = cargo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombre) {
        this.apellidosNombres = apellidosNombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
