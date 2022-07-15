/**
 * Clase correspondiente a la entidad Programa
 * con los atributos y sus metodos de abstraccion
 */

package com.example.parcilafinalappv2.entidades;

public class Programa {


    // atributos de la clase
    private int id;
    private String nombre;
    private int duracion;
    private String modalidad;
    private String facultad;

    // constructor
    public Programa(int id, String nombre, int duracion, String modalidad, String facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.modalidad = modalidad;
        this.facultad = facultad;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}


