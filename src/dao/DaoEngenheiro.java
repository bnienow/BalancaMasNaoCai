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
import model.Engenheiro;

/**
 *
 * @author benienow
 */
public class DaoEngenheiro {

    private DataSource dataSource;

    public DaoEngenheiro(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Engenheiro> consulta(String busca) {
        ArrayList<Engenheiro> lista = new ArrayList<Engenheiro>();
        try {
            String SQL = "SELECT * FROM engenheiros WHERE dsNome LIKE ? ORDER BY dsNome";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Engenheiro eng = new Engenheiro();
                eng.setIdEngenheiro(rs.getInt("idEngenheiro"));
                eng.setDsNome(rs.getString("dsNome"));
                eng.setNrCREA(rs.getString("nrCREA"));
                eng.setDtAdmissao(rs.getObject("dtAdmissao", LocalDate.class));
                lista.add(eng);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(Engenheiro eng) {
        try {
            String SQL = "INSERT INTO engenheiros (dsNome, nrCREA, dtAdmissao) VALUES (?, ?, ?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, eng.getDsNome());
            ps.setString(2, eng.getNrCREA());
            ps.setObject(3, eng.getDtAdmissao());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(Engenheiro eng) {
        try {
            String SQL = "UPDATE engenheiros SET dsNome = ?, nrCREA = ?, dtAdmissao = ? WHERE idEngenheiro = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, eng.getDsNome());
            ps.setString(2, eng.getNrCREA());
            ps.setObject(3, eng.getDtAdmissao());
            ps.setInt(4, eng.getIdEngenheiro());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idEngenheiro) {
        try {
            String SQL = "DELETE FROM engenheiros WHERE idEngenheiro = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idEngenheiro);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir: engenheiro vinculado a alguma obra.\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }
}
