/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author benienow
 */
public class DataSource {

    private String hostname;
    private int porta;
    private String database;
    private String username;
    private String password;

    private Connection connection;

    public DataSource() {
        try {
            hostname = "127.0.0.1";
            porta = 3306;
            database = "balanca_mas_nao_cai";
            username = "root";
            password = "BeNienow2909-2008";

            String url = "jdbc:mysql://" + hostname + ":" + porta + "/" + database
                    + "?useTimezone=true&serverTimezone=UTC";

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco\n" + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeDataSource() {
        try {
            connection.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar\n" + ex.getMessage());
        }
    }
}
