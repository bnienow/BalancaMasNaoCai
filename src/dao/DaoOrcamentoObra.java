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
import model.OrcamentoObra;

/**
 *
 * @author benienow
 */
public class DaoOrcamentoObra {

    private DataSource dataSource;

    public DaoOrcamentoObra(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<OrcamentoObra> consulta(String busca) {
        ArrayList<OrcamentoObra> lista = new ArrayList<OrcamentoObra>();
        try {
            String SQL = "SELECT oo.*, o.Descricao FROM orcamentos_obras oo INNER JOIN obras o ON o.idObra = oo.idObra WHERE o.Descricao LIKE ? ORDER BY oo.idOrcamento, o.Descricao";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrcamentoObra orcamentoObra = new OrcamentoObra();
                orcamentoObra.setIdOrcamento(rs.getInt("idOrcamento"));
                orcamentoObra.setIdObra(rs.getInt("idObra"));
                orcamentoObra.setDtInicio(rs.getObject("dtInicio", LocalDate.class));
                orcamentoObra.setDtFim(rs.getObject("dtFim", LocalDate.class));
                orcamentoObra.setVlPrevisto(rs.getDouble("vlPrevisto"));
                orcamentoObra.setVlRealizado(rs.getDouble("vlRealizado"));
                orcamentoObra.setDescricaoObra(rs.getString("Descricao"));
                lista.add(orcamentoObra);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(OrcamentoObra orcamentoObra) {
        try {
            String SQL = "INSERT INTO orcamentos_obras (idOrcamento, idObra, dtInicio, dtFim, vlPrevisto, vlRealizado) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, orcamentoObra.getIdOrcamento());
            ps.setInt(2, orcamentoObra.getIdObra());
            ps.setObject(3, orcamentoObra.getDtInicio());
            ps.setObject(4, orcamentoObra.getDtFim());
            ps.setDouble(5, orcamentoObra.getVlPrevisto());
            ps.setDouble(6, orcamentoObra.getVlRealizado());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(OrcamentoObra orcamentoObra) {
        try {
            String SQL = "UPDATE orcamentos_obras SET dtInicio = ?, dtFim = ?, vlPrevisto = ?, vlRealizado = ? WHERE idOrcamento = ? AND idObra = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setObject(1, orcamentoObra.getDtInicio());
            ps.setObject(2, orcamentoObra.getDtFim());
            ps.setDouble(3, orcamentoObra.getVlPrevisto());
            ps.setDouble(4, orcamentoObra.getVlRealizado());
            ps.setInt(5, orcamentoObra.getIdOrcamento());
            ps.setInt(6, orcamentoObra.getIdObra());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idOrcamento, int idObra) {
        try {
            String SQL = "DELETE FROM orcamentos_obras WHERE idOrcamento = ? AND idObra = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idOrcamento);
            ps.setInt(2, idObra);

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
