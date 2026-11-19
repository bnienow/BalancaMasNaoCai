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
public class Engenheiro {

    private int idEngenheiro;
    private String dsNome;
    private String nrCREA;
    private LocalDate dtAdmissao;

    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getNrCREA() {
        return nrCREA;
    }

    public void setNrCREA(String nrCREA) {
        this.nrCREA = nrCREA;
    }

    public LocalDate getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(LocalDate dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    @Override
    public String toString() {
        return dsNome;
    }
}
