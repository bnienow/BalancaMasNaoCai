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
public class Orcamento {

    private int idOrcamento;
    private LocalDate dtAprovacao;
    private int idEngenheiroResponsavel;
    private String dsNomeResponsavel;

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public LocalDate getDtAprovacao() {
        return dtAprovacao;
    }

    public void setDtAprovacao(LocalDate dtAprovacao) {
        this.dtAprovacao = dtAprovacao;
    }

    public int getIdEngenheiroResponsavel() {
        return idEngenheiroResponsavel;
    }

    public void setIdEngenheiroResponsavel(int idEngenheiroResponsavel) {
        this.idEngenheiroResponsavel = idEngenheiroResponsavel;
    }

    public String getDsNomeResponsavel() {
        return dsNomeResponsavel;
    }

    public void setDsNomeResponsavel(String dsNomeResponsavel) {
        this.dsNomeResponsavel = dsNomeResponsavel;
    }

    @Override
    public String toString() {
        return "Orçamento nº " + idOrcamento;
    }
}
