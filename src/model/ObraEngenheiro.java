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
public class ObraEngenheiro {

    private int idObra;
    private int idEngenheiro;
    private LocalDate dtAdmissaoEngenheiro;
    private LocalDate dtInicioTrabalho;
    private LocalDate dtFimTrabalho;
    private int idFuncao;
    private String descricaoObra;
    private String dsNomeEngenheiro;
    private String dsFuncao;

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }

    public LocalDate getDtAdmissaoEngenheiro() {
        return dtAdmissaoEngenheiro;
    }

    public void setDtAdmissaoEngenheiro(LocalDate dtAdmissaoEngenheiro) {
        this.dtAdmissaoEngenheiro = dtAdmissaoEngenheiro;
    }

    public LocalDate getDtInicioTrabalho() {
        return dtInicioTrabalho;
    }

    public void setDtInicioTrabalho(LocalDate dtInicioTrabalho) {
        this.dtInicioTrabalho = dtInicioTrabalho;
    }

    public LocalDate getDtFimTrabalho() {
        return dtFimTrabalho;
    }

    public void setDtFimTrabalho(LocalDate dtFimTrabalho) {
        this.dtFimTrabalho = dtFimTrabalho;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getDescricaoObra() {
        return descricaoObra;
    }

    public void setDescricaoObra(String descricaoObra) {
        this.descricaoObra = descricaoObra;
    }

    public String getDsNomeEngenheiro() {
        return dsNomeEngenheiro;
    }

    public void setDsNomeEngenheiro(String dsNomeEngenheiro) {
        this.dsNomeEngenheiro = dsNomeEngenheiro;
    }

    public String getDsFuncao() {
        return dsFuncao;
    }

    public void setDsFuncao(String dsFuncao) {
        this.dsFuncao = dsFuncao;
    }
}
