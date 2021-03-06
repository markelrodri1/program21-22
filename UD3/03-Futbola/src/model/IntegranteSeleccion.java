/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static exekutagarriak.EuskalSelekzioa.azkenId;
import java.util.ArrayList;
import static model.Demarkazioa.DEF;
import static model.Demarkazioa.DEL;
import static model.Demarkazioa.MED;
import static model.Demarkazioa.POR;

/**
 *
 * @author rodriguez.markel
 */
public class IntegranteSeleccion {
    protected int id;
    protected String nombre;
    private String apellidos;
    private int edad;
    
    public IntegranteSeleccion() {
    }
    

    public IntegranteSeleccion(int id, String nombre, String apellidos, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public static ArrayList <IntegranteSeleccion>  getSelekzioa() {
        ArrayList <IntegranteSeleccion> selekzioa = new ArrayList();
        
        return selekzioa;
    }
    public void concentrarse(){
        System.out.println("Kontzentratuta nago");
    }
    public void viajar(){
        System.out.println("Bidaiatzen nago");
    }
    
    @Override
    public String toString() {
        return "IntegranteSeleccion{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + '}';
    }
    
}
