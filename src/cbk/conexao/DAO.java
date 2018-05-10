/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import cbk.dados.loginDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author igorcasconi
 */
public abstract class DAO<T>{
    protected Connection conn;
    
    public DAO(){
        String url = "jdbc:mysql://127.0.0.1:3306/cbk_database";
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
    public abstract boolean inserirOrdemServico(T obj);   
    public abstract List<String> Nome();
    public abstract String SelectCpf(int indexSelecionado);
    public abstract int numeroOrdemIncremento();
    public abstract int contagemOsAbertas();
    public abstract boolean inserirPedido(T obj);
    public abstract boolean inserirItemPedido(T obj);
    public abstract List<Integer> ordemServico();
    public abstract boolean inserirUsuario(T obj);
    public abstract List<T> usuarios();
    public abstract List<String> departamentos();
    public abstract int numeroPedidoIncremento();
    public abstract boolean finalizarGravarDadosPedido(T obj);    
    
}
