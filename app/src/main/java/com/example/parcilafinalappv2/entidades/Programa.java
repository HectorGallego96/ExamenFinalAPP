package com.example.parcilafinalappv2.entidades;

public class Programa {


    private int id;
    private String nombre;

    // constructor
    public Programa(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // metodos getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


