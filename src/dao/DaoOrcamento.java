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
import model.Orcamento;

/**
 *
 * @author benienow
 */
public class DaoOrcamento {

    private DataSource dataSource;

    public DaoOrcamento(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Orcamento> consulta(String busca) {
        ArrayList<Orcamento> lista = new ArrayList<Orcamento>();
        try {
            String SQL = "SELECT o.*, e.dsNome FROM orcamentos o INNER JOIN engenheiros e ON e.idEngenheiro = o.idEngenheiroResponsavel WHERE e.dsNome LIKE ? ORDER BY o.idOrcamento";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orcamento orcamento = new Orcamento();
                orcamento.setIdOrcamento(rs.getInt("idOrcamento"));
                orcamento.setDtAprovacao(rs.getObject("dtAprovacao", LocalDate.class));
                orcamento.setIdEngenheiroResponsavel(rs.getInt("idEngenheiroResponsavel"));
                orcamento.setDsNomeResponsavel(rs.getString("dsNome"));
                lista.add(orcamento);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(Orcamento orcamento) {
        try {
            String SQL = "INSERT INTO orcamentos (dtAprovacao, idEngenheiroResponsavel) VALUES (?, ?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setObject(1, orcamento.getDtAprovacao());
            ps.setInt(2, orcamento.getIdEngenheiroResponsavel());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(Orcamento orcamento) {
        try {
            String SQL = "UPDATE orcamentos SET dtAprovacao = ?, idEngenheiroResponsavel = ? WHERE idOrcamento = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setObject(1, orcamento.getDtAprovacao());
            ps.setInt(2, orcamento.getIdEngenheiroResponsavel());
            ps.setInt(3, orcamento.getIdOrcamento());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idOrcamento) {
        try {
            String SQL = "DELETE FROM orcamentos WHERE idOrcamento = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idOrcamento);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir: orçamento com obras vinculadas.\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }
}
