package cbk.conexao;

import cbk.dados.clienteDados;
import cbk.dados.itemPedidoPecaDados;
import cbk.dados.loginDados;
import cbk.dados.pedidoPecaDados;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class pedidoPecaDAO extends DAO<pedidoPecaDados> {

    @Override
    public boolean inserirPedido(pedidoPecaDados obj) {
        try{
            String sql = "insert into pedido_peca(num_pedido, email_fabricante, id_ordem) values (?, ?, ?);";
            
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNumeroPedido());
            stmt.setString(2, obj.getEmailFabricante());
            stmt.setInt(3, obj.getIdOrdem());
            
            if(stmt.executeUpdate()==1){
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                obj.setIdPeca(id);
                return true;
            }
            
        }catch(SQLException ex){
          Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
          System.out.printf("Erro ao executar o insert: %s", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean inserirItemPedido(pedidoPecaDados obj) {
        try{
            String sql = "insert into pedido_peca_item(id_peca, codigo_peca, descricao_peca, qtd_peca) values (?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getIdPeca());
            stmt.setString(2, obj.getCodigoPeca());
            stmt.setString(3, obj.getDescricaoPeca());
            stmt.setInt(4, obj.getQuantidadePeca());
            
            if(stmt.executeUpdate()==1){
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                obj.setIdPecaItem(id);
                return true;
            }
            
        }catch(SQLException ex){
          Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
          System.out.printf("Erro ao executar o insert: %s", ex.getMessage());               
        }
        return false;
    }

    @Override
    public List<String> ordemServicoNum() {
        List<String> NumeroOrdem = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql ="SELECT numero_ordem FROM ordem_servico;" ;
            stmt = conn.prepareStatement(sql);
                        
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                 NumeroOrdem.add(rs.getString("numero_ordem"));
                }
                stmt.close();
        } catch(SQLException e) {
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return NumeroOrdem;
    }

    @Override
    public String numeroPedidoIncremento() {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       String numeroPedido = null;
       
        try {
            String sql = "SELECT num_pedido FROM pedido_peca WHERE id_peca = (SELECT MAX(id_peca) FROM pedido_peca);";
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            if(!rs.next()) {
                numeroPedido = "0";
            } else { 
                numeroPedido = rs.getString(1);
            }
 
        } catch (SQLException e) {
            Logger.getLogger(OrdemServicoDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
       
       return numeroPedido;
    }
    
    @Override
    public List<pedidoPecaDados> pedidoPeca() {
       List<pedidoPecaDados> listaPedido = new ArrayList<pedidoPecaDados>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
                String sql ="SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.numero_ordem "+
                            "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                            "ON a.id_peca = b.numero_ordem;";
                stmt = conn.prepareStatement(sql);
                      
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    pedidoPecaDados c = new pedidoPecaDados();
                    c.setIdPeca(rs.getInt(rs.getInt("id_peca")));
                    c.setNumeroPedido(rs.getString("num_pedido"));
                    c.setNumero_ordem(rs.getString("numero_ordem"));
                    c.setEmailFabricante(rs.getString("email_fabricante"));
                    listaPedido.add(c);
                }
                stmt.close();
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return listaPedido;
    }
    
    @Override
    public List<itemPedidoPecaDados> itemPedidoPeca(int idpeca) {
        List<itemPedidoPecaDados> listaItem = new ArrayList<itemPedidoPecaDados>();
        itemPedidoPecaDados c = new itemPedidoPecaDados();
        
        try {
            String sql = "SELECT a.id_peca_item, b.num_pedido, a.codigo_peca, a.descricao_peca, a.qtd_peca, c.numero_ordem "+
                         "FROM pedido_peca_item AS a "+
                         "INNER JOIN pedido_peca AS b ON a.id_peca = b.id_peca "+
                         "LEFT JOIN ordem_servico AS c ON b.id_ordem = c.id_ordem "+
                         "WHERE a.id_peca = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idpeca);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                c.setIdPecaItem(rs.getInt("id_peca_item"));
                c.setNumero_pedido(rs.getString("num_pedido"));
                c.setCodigo(rs.getString("codigo_peca"));
                c.setDescricao(rs.getString("descricao_peca"));
                c.setQuantidade(rs.getInt("qtd_peca"));
                c.setNumero_ordem(rs.getString("numero_ordem"));
                listaItem.add(c);
            }
            
            
        }catch(SQLException e){
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return listaItem;
    }
    
    
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* MÉTODOS NÃO UTILIZADOS POR ESSA CLASSE */
    @Override
    public boolean atualizarUsuario(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirUsuario(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarExistenciaUsuario(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean inserirUsuario(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> departamentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pedidoPecaDados> usuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public boolean verificaLogin(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Autorizada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirCliente(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Versao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirOrdemServico(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<clienteDados> Nome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> status() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String numeroOrdemIncremento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int contagemOsAbertas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pedidoPecaDados> clientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarCliente(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirCliente(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pedidoPecaDados> selectClienteFiltro(String nome, String cpf, String email, String celular, int opcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<clienteDados> SelectNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pedidoPecaDados> ordemServico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarOS(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int tecnico(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pedidoPecaDados> historico(int idOS) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int contagemOsFechadas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pedidoPecaDados> selectOrdemServicoFiltro(String numeroOS, String notaFiscal, String data, String nome, int comboStatus, int opcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirOrdem(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
