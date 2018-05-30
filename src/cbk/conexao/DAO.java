/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import cbk.dados.clienteDados;
import cbk.dados.loginDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

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

    /* Utilizado pela Classe ClienteDAO */
    public abstract boolean inserirCliente(T obj);
    public abstract List<T> clientes();
    public abstract boolean atualizarCliente(T obj);
    public abstract boolean excluirCliente(T obj);
    public abstract List<T> selectClienteFiltro(String nome, String cpf, String email, String celular, int opcao);
    
    /* Utilizado pela Classe OrdemServicoDAO */
    public abstract boolean inserirOrdemServico(T obj);   
    public abstract List<clienteDados> Nome();
    public abstract List<clienteDados> SelectNome(String nome);
    public abstract String SelectCpf(int indexSelecionado);
    public abstract String numeroOrdemIncremento();

    /* Utilizado pela Classe pedidoPecaDAO */
    public abstract String numeroPedidoIncremento();
    public abstract boolean inserirPedido(T obj);
    public abstract boolean inserirItemPedido(T obj);
    public abstract List<Integer> ordemServico();
    
    /* Utilizado pela Clasee loginDAO*/
    public abstract boolean verificaLogin(String usuario, String senha);
    public abstract boolean verificarExistenciaUsuario(String usuario); 
    public abstract boolean inserirUsuario(T obj);
    public abstract boolean atualizarUsuario(T obj);
    public abstract boolean excluirUsuario(T obj);
    public abstract List<String> departamentos();
    public abstract String Versao();
    public abstract int contagemOsAbertas();
    public abstract List<T> usuarios();
    public abstract String Autorizada();
}
