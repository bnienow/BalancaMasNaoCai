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
import model.ObraFase;

/**
 *
 * @author benienow
 */
public class DaoObraFase {

    private DataSource dataSource;

    public DaoObraFase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<ObraFase> consulta(String busca) {
        ArrayList<ObraFase> lista = new ArrayList<ObraFase>();
        try {
            String SQL = "SELECT ofa.*, o.Descricao, f.dsFase FROM obras_fases ofa INNER JOIN obras o ON o.idObra = ofa.idObra INNER JOIN fases f ON f.idFase = ofa.idFase WHERE o.Descricao LIKE ? ORDER BY o.Descricao, ofa.dtIniciofase";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ObraFase obraFase = new ObraFase();
                obraFase.setIdObra(rs.getInt("idObra"));
                obraFase.setIdFase(rs.getInt("idFase"));
                obraFase.setDtInicioFase(rs.getObject("dtIniciofase", LocalDate.class));
                obraFase.setDtTerminoFase(rs.getObject("dtTerminoFase", LocalDate.class));
                obraFase.setDescricaoObra(rs.getString("Descricao"));
                obraFase.setDsFase(rs.getString("dsFase"));
                lista.add(obraFase);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(ObraFase obraFase) {
        try {
            String SQL = "INSERT INTO obras_fases (idObra, idFase, dtIniciofase, dtTerminoFase) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, obraFase.getIdObra());
            ps.setInt(2, obraFase.getIdFase());
            ps.setObject(3, obraFase.getDtInicioFase());
            ps.setObject(4, obraFase.getDtTerminoFase());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(ObraFase obraFase) {
        try {
            String SQL = "UPDATE obras_fases SET dtIniciofase = ?, dtTerminoFase = ? WHERE idObra = ? AND idFase = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setObject(1, obraFase.getDtInicioFase());
            ps.setObject(2, obraFase.getDtTerminoFase());
            ps.setInt(3, obraFase.getIdObra());
            ps.setInt(4, obraFase.getIdFase());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idObra, int idFase) {
        try {
            String SQL = "DELETE FROM obras_fases WHERE idObra = ? AND idFase = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idObra);
            ps.setInt(2, idFase);

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
