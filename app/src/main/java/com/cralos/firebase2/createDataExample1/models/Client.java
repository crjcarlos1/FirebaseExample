package com.cralos.firebase2.createDataExample1.models;

public class Client {
    private String apellido;
    private int edad;
    private String nombre;

    public Client() {
    }

    public Client(String apellido, int edad, String nombre) {
        this.apellido = apellido;
        this.edad = edad;
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
