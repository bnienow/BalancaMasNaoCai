/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author benienow
 */
public class Fase {

    private int idFase;
    private String dsFase;

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public String getDsFase() {
        return dsFase;
    }

    public void setDsFase(String dsFase) {
        this.dsFase = dsFase;
    }

    @Override
    public String toString() {
        return dsFase;
    }
}
