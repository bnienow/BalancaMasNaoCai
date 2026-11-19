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
import model.Obra;

/**
 *
 * @author benienow
 */
public class DaoObra {

    private DataSource dataSource;

    public DaoObra(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Obra> consulta(String busca) {
        ArrayList<Obra> lista = new ArrayList<Obra>();
        try {
            String SQL = "SELECT o.*, e.dsNome FROM obras o INNER JOIN engenheiros e ON e.idEngenheiro = o.idEngenheiro WHERE o.Descricao LIKE ? ORDER BY o.Descricao";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Obra obra = new Obra();
                obra.setIdObra(rs.getInt("idObra"));
                obra.setDescricao(rs.getString("Descricao"));
                obra.setDtInicio(rs.getObject("dtInicio", LocalDate.class));
                obra.setDtFim(rs.getObject("dtFim", LocalDate.class));
                obra.setIdEngenheiro(rs.getInt("idEngenheiro"));
                obra.setNrCrea(rs.getString("nrCrea"));
                obra.setDtAdmissaoEngenheiro(rs.getObject("dtDdmissaoEngenheiro", LocalDate.class));
                obra.setDsNomeEngenheiro(rs.getString("dsNome"));
                lista.add(obra);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(Obra obra) {
        try {
            String SQL = "INSERT INTO obras (Descricao, dtInicio, dtFim, idEngenheiro, nrCrea, dtDdmissaoEngenheiro) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, obra.getDescricao());
            ps.setObject(2, obra.getDtInicio());
            ps.setObject(3, obra.getDtFim());
            ps.setInt(4, obra.getIdEngenheiro());
            ps.setString(5, obra.getNrCrea());
            ps.setObject(6, obra.getDtAdmissaoEngenheiro());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(Obra obra) {
        try {
            String SQL = "UPDATE obras SET Descricao = ?, dtInicio = ?, dtFim = ?, idEngenheiro = ?, nrCrea = ?, dtDdmissaoEngenheiro = ? WHERE idObra = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, obra.getDescricao());
            ps.setObject(2, obra.getDtInicio());
            ps.setObject(3, obra.getDtFim());
            ps.setInt(4, obra.getIdEngenheiro());
            ps.setString(5, obra.getNrCrea());
            ps.setObject(6, obra.getDtAdmissaoEngenheiro());
            ps.setInt(7, obra.getIdObra());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idObra) {
        try {
            String SQL = "DELETE FROM obras WHERE idObra = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idObra);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir: obra com fases, engenheiros ou orçamentos vinculados.\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }
}
