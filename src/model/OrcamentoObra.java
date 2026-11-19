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
public class OrcamentoObra {

    private int idOrcamento;
    private int idObra;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private double vlPrevisto;
    private double vlRealizado;
    private String descricaoObra;

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }

    public double getVlPrevisto() {
        return vlPrevisto;
    }

    public void setVlPrevisto(double vlPrevisto) {
        this.vlPrevisto = vlPrevisto;
    }

    public double getVlRealizado() {
        return vlRealizado;
    }

    public void setVlRealizado(double vlRealizado) {
        this.vlRealizado = vlRealizado;
    }

    public String getDescricaoObra() {
        return descricaoObra;
    }

    public void setDescricaoObra(String descricaoObra) {
        this.descricaoObra = descricaoObra;
    }
}
