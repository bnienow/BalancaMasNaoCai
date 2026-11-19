/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author benienow
 */
public class ObraFase {

    private int idObra;
    private int idFase;
    private LocalDate dtInicioFase;
    private LocalDate dtTerminoFase;
    private String descricaoObra;
    private String dsFase;

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public LocalDate getDtInicioFase() {
        return dtInicioFase;
    }

    public void setDtInicioFase(LocalDate dtInicioFase) {
        this.dtInicioFase = dtInicioFase;
    }

    public LocalDate getDtTerminoFase() {
        return dtTerminoFase;
    }

    public void setDtTerminoFase(LocalDate dtTerminoFase) {
        this.dtTerminoFase = dtTerminoFase;
    }

    public String getDescricaoObra() {
        return descricaoObra;
    }

    public void setDescricaoObra(String descricaoObra) {
        this.descricaoObra = descricaoObra;
    }

    public String getDsFase() {
        return dsFase;
    }

    public void setDsFase(String dsFase) {
        this.dsFase = dsFase;
    }
}
