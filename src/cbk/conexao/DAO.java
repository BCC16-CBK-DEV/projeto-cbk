package cbk.conexao;

import cbk.dados.OrdemServicoDados;
import cbk.dados.clienteDados;
import cbk.dados.itemPedidoPecaDados;
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
    public abstract List<String> status();
    public abstract String numeroOrdemIncremento();
    public abstract List<T> ordemServico();
    public abstract boolean atualizarOS(T obj);
    public abstract int tecnico(String user);
    public abstract List<T> historico(int idOS); 
    public abstract boolean excluirOrdem(T obj);
    public abstract List<T> selectOrdemServicoFiltro(String numeroOS, String notaFiscal, String data, String nome, 
    							             int comboStatus, int opcao); 
    

    /* Utilizado pela Classe pedidoPecaDAO */
    public abstract String numeroPedidoIncremento();
    public abstract boolean inserirPedido(T obj);
    public abstract boolean inserirItemPedido(T obj);
    public abstract List<String> ordemServicoNum();
    public abstract List<T> pedidoPeca();
    public abstract List<itemPedidoPecaDados> itemPedidoPeca(int idpeca);
    public abstract boolean atualizarPedido(itemPedidoPecaDados obj);
    public abstract boolean atualizarNumeroOrdem(T obj);
    public abstract List<T> selectFiltroPedido(String numeroPedido, String numeroOrdem, String email, int opcao);
    public abstract List<OrdemServicoDados> selectNumeroOrdem(String numeroOrdem);
    
    /* Utilizado pela Clasee loginDAO*/
    public abstract boolean verificaLogin(String usuario, String senha);
    public abstract boolean verificarExistenciaUsuario(String usuario); 
    public abstract boolean inserirUsuario(T obj);
    public abstract boolean atualizarUsuario(T obj);
    public abstract boolean excluirUsuario(T obj);
    public abstract List<String> departamentos();
    public abstract String Versao();
    public abstract int contagemOsAbertas();
    public abstract int contagemOsFechadas();
    public abstract List<T> usuarios();
    public abstract String Autorizada();
    public abstract List<T> configuracao();
    public abstract boolean atualizaConfiguracao(T obj);
}
