package com.example.u1t1_tarea;

import java.util.ArrayList;

public class Persona {
    private String nombre;
    private String edad;
    private int fotoID;

    Persona(String nombre, String edad, int fotoID){
        this.nombre = nombre;
        this.edad = edad;
        this.fotoID = fotoID;
    }

    public String getNombre(){
        return nombre;
    }
    public String getEdad(){
        return edad;
    }
    public int getFotoID(){
        return fotoID;
    }

}
