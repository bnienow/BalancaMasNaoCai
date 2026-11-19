/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Funcao;

/**
 *
 * @author benienow
 */
public class DaoFuncao {

    private DataSource dataSource;

    public DaoFuncao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Funcao> consulta(String busca) {
        ArrayList<Funcao> lista = new ArrayList<Funcao>();
        try {
            String SQL = "SELECT * FROM funcoes WHERE dsFuncao LIKE ? ORDER BY dsFuncao";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + busca + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Funcao funcao = new Funcao();
                funcao.setIdFuncao(rs.getInt("idFuncao"));
                funcao.setDsFuncao(rs.getString("dsFuncao"));
                lista.add(funcao);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
        return lista;
    }

    public void incluir(Funcao funcao) {
        try {
            String SQL = "INSERT INTO funcoes (dsFuncao) VALUES (?)";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, funcao.getDsFuncao());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Incluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void alterar(Funcao funcao) {
        try {
            String SQL = "UPDATE funcoes SET dsFuncao = ? WHERE idFuncao = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, funcao.getDsFuncao());
            ps.setInt(2, funcao.getIdFuncao());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }

    public void excluir(int idFuncao) {
        try {
            String SQL = "DELETE FROM funcoes WHERE idFuncao = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setInt(1, idFuncao);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir: função usada por algum engenheiro em obra.\n" + ex.getMessage());
        } finally {
            dataSource.closeDataSource();
        }
    }
}
