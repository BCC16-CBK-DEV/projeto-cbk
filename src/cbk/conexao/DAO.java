/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author igorcasconi
 */
public abstract class DAO<T>{
    protected Connection conn;
    
    public DAO(){
        String url = "jdbc:mysql://localhost:3306/cbk_database";
        String user = "root";
        String senha = "";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, senha);
        } catch(ClassNotFoundException e){
            System.out.printf("Classe não localizada");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);    
            System.out.printf("\nErro ao se conectar ao banco de dados: %s", e.getMessage());
        }
    }
    
    public abstract boolean verificaLogin(String usuario, String senha);
    public abstract String Autorizada();
    public abstract boolean inserirCliente(T obj);
    public abstract String Versao();
}
