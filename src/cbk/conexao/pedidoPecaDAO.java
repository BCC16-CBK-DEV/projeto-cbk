package cbk.conexao;

import cbk.dados.OrdemServicoDados;
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
                String sql ="SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                            "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                            "ON a.id_ordem = b.id_ordem;";
                stmt = conn.prepareStatement(sql);
                      
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    pedidoPecaDados c = new pedidoPecaDados();
                    c.setIdPeca(rs.getInt("id_peca"));
                    c.setNumeroPedido(rs.getString("num_pedido"));
                    c.setEmailFabricante(rs.getString("email_fabricante"));
                    c.setIdOrdem(rs.getInt("id_ordem"));
                    c.setNumero_ordem(rs.getString("numero_ordem"));
                    listaPedido.add(c);
                }
                
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro: "+e.getMessage());
             
        }
        
        return listaPedido;
    }
    
    @Override
    public List<itemPedidoPecaDados> itemPedidoPeca(int idpeca) {
        List<itemPedidoPecaDados> listaItem = new ArrayList<itemPedidoPecaDados>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM pedido_peca_item AS a "+
                         "WHERE a.id_peca = "+idpeca+";";
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                itemPedidoPecaDados c = new itemPedidoPecaDados();
                c.setIdPecaItem(rs.getInt("id_peca_item"));
                c.setCodigo(rs.getString("codigo_peca"));
                c.setDescricao(rs.getString("descricao_peca"));
                c.setQuantidade(rs.getInt("qtd_peca"));
                c.setQuantidade_texto(Integer.toString(rs.getInt("qtd_peca")));
                listaItem.add(c);
            }
            
            stmt.close();
        }catch(SQLException e){
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return listaItem;
    }
    
    @Override
    public boolean atualizarPedido(itemPedidoPecaDados obj) {
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE pedido_peca_item SET codigo_peca = ?, descricao_peca = ?, qtd_peca = ? "+
                         "WHERE id_peca_item = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getCodigo());
            stmt.setString(2, obj.getDescricao());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getIdPecaItem());
            
            while(stmt.executeUpdate() == 1){
                return true;
            }

        }catch(SQLException e){
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return false;
    }
    
    @Override
    public boolean atualizarNumeroOrdem(pedidoPecaDados obj) {
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE pedido_peca SET id_ordem = ?, email_fabricante = ? "+
                         "WHERE id_peca = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getIdOrdem());
            stmt.setString(2, obj.getEmailFabricante());
            stmt.setInt(3, obj.getIdPeca());
            
            while(stmt.executeUpdate() == 1){
                return true;
            }

        }catch(SQLException e){
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return false;
    }
    
     @Override
    public List<pedidoPecaDados> selectFiltroPedido(String numeroPedido, String numeroOrdem, String email, int opcao){
            String sql = null;
            ResultSet rs = null;
            PreparedStatement stmt = null;
            pedidoPecaDados obj = new pedidoPecaDados();
            List<pedidoPecaDados> listaPedido = new ArrayList<pedidoPecaDados>();
        // OPÇÕES DE SELECTS
        try{
            switch(opcao){
                case 1: // numero do pedido
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE a.num_pedido like '%"+numeroPedido+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
	                    pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    } 
                break;
                
                case 2: // numero da ordem de serviço
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE b.numero_ordem like '%"+numeroOrdem+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    }
                break;
                
                case 3: // email
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE a.email_fabricante like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    }
                break;
                
                case 4: // numeroPedido + numeroOrdem
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE a.num_pedido like '%"+numeroPedido+"%' OR b.numero_ordem like '%"+numeroOrdem+"%';";
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    }
                break;
                
                case 5: // numeroPedido + email
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE a.num_pedido like '%"+numeroPedido+"%' OR a.email_fabricante like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    }
                break;
                
                case 6: // numeroOrdem + email
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE b.numero_ordem like '%"+numeroOrdem+"%' OR a.email_fabricante like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    }
                break;
                
                case 7: // numeroPedido + numeroOrdem + email
                    sql = "SELECT a.id_peca, a.num_pedido, a.email_fabricante, b.id_ordem, b.numero_ordem "+
                          "FROM pedido_peca AS a LEFT JOIN ordem_servico AS b "+
                          "ON a.id_ordem = b.id_ordem WHERE a.num_pedido like '%"+numeroPedido+"%' "+ 
                          "b.numero_ordem like '%"+numeroOrdem+"%' OR a.email_fabricante like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        pedidoPecaDados c = new pedidoPecaDados();
	                    c.setIdPeca(rs.getInt("id_peca"));
	                    c.setNumeroPedido(rs.getString("num_pedido"));
	                    c.setEmailFabricante(rs.getString("email_fabricante"));
	                    c.setIdOrdem(rs.getInt("id_ordem"));
	                    c.setNumero_ordem(rs.getString("numero_ordem"));
	                    listaPedido.add(c);
                    }
                break;
                
                
            }
            
        } catch(SQLException e){
            System.out.printf("Erro: %s", e.getMessage());
        }
        
        return listaPedido;
    }
    
    @Override
    public List<OrdemServicoDados> selectNumeroOrdem(String numeroOrdem) {
        List<OrdemServicoDados> listaNumeroOrdem = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
                String sql ="SELECT a.id_ordem, a.numero_ordem "+
                            "FROM ordem_servico AS a WHERE a.numero_ordem like '%"+numeroOrdem+"%';";
                stmt = conn.prepareStatement(sql);
                      
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    OrdemServicoDados c = new OrdemServicoDados();
                    c.setId_Ordem(rs.getInt("id_ordem"));
                    c.setNumero_ordem(rs.getString("numero_ordem"));
                    listaNumeroOrdem.add(c);
                }
                
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro: "+e.getMessage());
             
        }
        
        return listaNumeroOrdem;
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

    @Override
    public List<pedidoPecaDados> configuracao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizaConfiguracao(pedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
