/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bezerointerfazea;

/**
 *
 * @author rodriguez.markel
 */
public class Pertsona implements Bezeroak{
    int kodea;
    String izena;
    String abizena;
    String helbidea;
    String emaila;

    public Pertsona(int kodea, String izena, String abizena, String helbidea, String emaila) {
        this.kodea = kodea;
        this.izena = izena;
        this.abizena = abizena;
        this.helbidea = helbidea;
        this.emaila = emaila;
    }

    public int getKodea() {
        return kodea;
    }

    public String getIzena() {
        return izena;
    }
    
    public String getAbizena() {
        return abizena;
    }
    
    public String getHelbidea() {
        return helbidea;
    }


    public String getEmaila() {
        return emaila;
    }

    @Override
    public String toString() {
        return "Pertsona{" + "kodea=" + kodea + ", izena=" + izena + ", abizena=" + abizena + ", helbidea=" + helbidea + ", emaila=" + emaila + '}';
    }
    
    
    
}
