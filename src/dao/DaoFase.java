/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Fase;

/**
 *
 * @author benienow
 */
public class DaoFase {

    private DataSource dataSource;

    public DaoFase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Fase> consulta(String busca) {
        ArrayList<Fase> lista = new ArrayList<Fase>();
        try {
            String SQL = "SELECT * FROM fases WHERE dsFase LIKE ? ORDER BY dsFase";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fase fase = new Fase();
                fase.setIdFase(rs.getInt("idFase"));
                fase.setDsFase(rs.getString("dsFase"));
                lista.add(fase);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(Fase fase) {
        try {
            String SQL = "INSERT INTO fases (dsFase) VALUES (?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, fase.getDsFase());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(Fase fase) {
        try {
            String SQL = "UPDATE fases SET dsFase = ? WHERE idFase = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, fase.getDsFase());
            ps.setInt(2, fase.getIdFase());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idFase) {
        try {
            String SQL = "DELETE FROM fases WHERE idFase = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idFase);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir: fase usada em alguma obra.\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }
}
