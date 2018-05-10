/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

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

/**
 *
 * @author igorcasconi
 */
public class pedidoPecaDAO extends DAO<pedidoPecaDados> {

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
    public List<String> Nome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SelectCpf(int indexSelecionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int numeroOrdemIncremento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int contagemOsAbertas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirPedido(pedidoPecaDados obj) {
        try{
            String sql = "insert into pedido_peca(num_pedido, email_fabricante, id_ordem) values (?, ?, ?);";
            
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getNumeroPedido());
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
    public List<Integer> ordemServico() {
        List<Integer> NumeroOrdem = new ArrayList<Integer>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql ="SELECT numero_ordem FROM ordem_servico;" ;
            stmt = conn.prepareStatement(sql);
                        
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                 NumeroOrdem.add(rs.getInt("numero_ordem"));
                }
                stmt.close();
        } catch(SQLException e) {
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return NumeroOrdem;
    }

    @Override
    public int numeroPedidoIncremento() {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       int numeroPedido = 0;
       
        try {
            String sql = "SELECT (SELECT MAX(num_pedido)) FROM pedido_peca;";
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            if(!rs.next()) {
                numeroPedido = 1;
            } else { 
                numeroPedido = rs.getInt(1);
                numeroPedido++;
            }
 
        } catch (SQLException e) {
            Logger.getLogger(OrdemServicoDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
       
       return numeroPedido;
    }

    @Override
    public boolean finalizarGravarDadosPedido(pedidoPecaDados obj) {
        PreparedStatement stmt = null;
       ResultSet rs = null;
       int numeroPedido = 0;
       
        try {
            String sql = "UPDATE pedido_peca SET email_fabricante = ?, id_ordem = ? WHERE id_peca = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getEmailFabricante());
            stmt.setInt(2, obj.getIdOrdem());
 
            if(stmt.executeUpdate() == 1) {
               
            }
 
        } catch (SQLException e) {
            Logger.getLogger(OrdemServicoDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
       
       return false;
    }

    
}
