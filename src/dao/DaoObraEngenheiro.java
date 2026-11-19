/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ObraEngenheiro;

/**
 *
 * @author benienow
 */
public class DaoObraEngenheiro {

    private DataSource dataSource;

    public DaoObraEngenheiro(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<ObraEngenheiro> consulta(String busca) {
        ArrayList<ObraEngenheiro> lista = new ArrayList<ObraEngenheiro>();
        try {
            String SQL = "SELECT oe.*, o.Descricao, e.dsNome, f.dsFuncao FROM obras_engenheiros oe INNER JOIN obras o ON o.idObra = oe.idObra INNER JOIN engenheiros e ON e.idEngenheiro = oe.idEngenheiro INNER JOIN funcoes f ON f.idFuncao = oe.idFuncao WHERE o.Descricao LIKE ? ORDER BY o.Descricao, e.dsNome";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ObraEngenheiro obraEngenheiro = new ObraEngenheiro();
                obraEngenheiro.setIdObra(rs.getInt("idObra"));
                obraEngenheiro.setIdEngenheiro(rs.getInt("idEngenheiro"));
                obraEngenheiro.setDtAdmissaoEngenheiro(rs.getObject("dtAdmissaoEngenheiro", LocalDate.class));
                obraEngenheiro.setDtInicioTrabalho(rs.getObject("dtInicioTrabalho", LocalDate.class));
                obraEngenheiro.setDtFimTrabalho(rs.getObject("dtFimTrabalho", LocalDate.class));
                obraEngenheiro.setIdFuncao(rs.getInt("idFuncao"));
                obraEngenheiro.setDescricaoObra(rs.getString("Descricao"));
                obraEngenheiro.setDsNomeEngenheiro(rs.getString("dsNome"));
                obraEngenheiro.setDsFuncao(rs.getString("dsFuncao"));
                lista.add(obraEngenheiro);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(ObraEngenheiro obraEngenheiro) {
        try {
            String SQL = "INSERT INTO obras_engenheiros (idObra, idEngenheiro, dtAdmissaoEngenheiro, dtInicioTrabalho, dtFimTrabalho, idFuncao) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, obraEngenheiro.getIdObra());
            ps.setInt(2, obraEngenheiro.getIdEngenheiro());
            ps.setObject(3, obraEngenheiro.getDtAdmissaoEngenheiro());
            ps.setObject(4, obraEngenheiro.getDtInicioTrabalho());
            ps.setObject(5, obraEngenheiro.getDtFimTrabalho());
            ps.setInt(6, obraEngenheiro.getIdFuncao());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(ObraEngenheiro obraEngenheiro) {
        try {
            String SQL = "UPDATE obras_engenheiros SET dtAdmissaoEngenheiro = ?, dtInicioTrabalho = ?, dtFimTrabalho = ?, idFuncao = ? WHERE idObra = ? AND idEngenheiro = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setObject(1, obraEngenheiro.getDtAdmissaoEngenheiro());
            ps.setObject(2, obraEngenheiro.getDtInicioTrabalho());
            ps.setObject(3, obraEngenheiro.getDtFimTrabalho());
            ps.setInt(4, obraEngenheiro.getIdFuncao());
            ps.setInt(5, obraEngenheiro.getIdObra());
            ps.setInt(6, obraEngenheiro.getIdEngenheiro());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idObra, int idEngenheiro) {
        try {
            String SQL = "DELETE FROM obras_engenheiros WHERE idObra = ? AND idEngenheiro = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idObra);
            ps.setInt(2, idEngenheiro);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }
}
