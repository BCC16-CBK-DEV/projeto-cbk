/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import cbk.dados.OrdemServicoDados;
import cbk.dados.clienteDados;
import cbk.dados.loginDados;
import com.mysql.jdbc.Statement;
import java.sql.Date;
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
 * @author gabar
 */
public class OrdemServicoDAO extends DAO<OrdemServicoDados> {

    clienteDados cliente = new clienteDados();

    @Override
    public boolean inserirOrdemServico(OrdemServicoDados obj) {
        try {
            String sql = "insert into ordem_servico" +
                        "(numero_ordem"+
                        " ,nota_fiscal" +
                        " ,data_compra" +
                        " ,defeito_reclamado" +
                        " ,codigo_produto" +
                        " ,descricao_produto" +
                        " ,voltagem" +
                        " ,numero_serie_produto" +
                        " ,data_abertura" +
                        " ,id_cliente)"+
                        "values(" +
                        "?,?,?,?,?,?,?,?,?,?"+ //11stmt
                        ")";
  
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNumero_ordem());
            stmt.setString(2, obj.getNota_fiscal());
            stmt.setDate(3, (Date) obj.getData_compra());
            stmt.setString(4, obj.getDefeito_reclamado());
            stmt.setString(5, obj.getCodigo_produto());
            stmt.setString(6, obj.getDescricao_produto());
            stmt.setString(7, obj.getVoltagem());
            stmt.setString(8, obj.getNumero_serie_produto());
            stmt.setDate(9, (Date) obj.getData_abertura());
            stmt.setInt(10, obj.getId_ClienteOS());
         
            
            if(stmt.executeUpdate() == 1){
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                obj.setId_Ordem(id);
                return true;
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Erro ao executar o insert: %s", ex.getMessage());
        }
        return false;
    }

    @Override   
    public List<clienteDados> Nome()
    {
        List<clienteDados> NomeOS = new ArrayList<clienteDados>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
                String sql ="SELECT id_cliente, nome_cliente, cpf FROM cliente ORDER BY id_cliente" ;
                stmt = conn.prepareStatement(sql);
                      
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    clienteDados c = new clienteDados();
                    c.setIdcliente(rs.getInt("id_cliente"));
                    c.setNome_cliente(rs.getString("nome_cliente"));
                    c.setCpf(rs.getString("cpf"));
                    NomeOS.add(c);
                }
                stmt.close();
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return NomeOS;
    
}

    @Override
    public String SelectCpf(int indexSelecionado) { 
        String Cpf = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement("SELECT cpf FROM cliente WHERE id_cliente = ?;");
            stmt.setInt(1,indexSelecionado);
            
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
               Cpf = rs.getString(1);
            }
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return Cpf;
    }

    @Override
    public String numeroOrdemIncremento() {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       String numeroDaOrdem = null;
 
        try {
            String sql = "SELECT numero_ordem FROM ordem_servico WHERE id_ordem = (SELECT MAX(id_ordem));";
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            if(!rs.next()) {
                numeroDaOrdem = "0";
            } else { 
                numeroDaOrdem = rs.getString(1);
            }
 
        } catch (SQLException e) {
            Logger.getLogger(OrdemServicoDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
       
       return numeroDaOrdem;
    }
    
    @Override
    public List<clienteDados> SelectNome(String nome) {
       List<clienteDados> Nome = new ArrayList<clienteDados>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
                String sql ="SELECT id_cliente, nome_cliente, cpf FROM cliente WHERE nome_cliente like '%"+nome+"%' ORDER BY id_cliente;";
                stmt = conn.prepareStatement(sql);
                      
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    clienteDados c = new clienteDados();
                    c.setIdcliente(rs.getInt("id_cliente"));
                    c.setNome_cliente(rs.getString("nome_cliente"));
                    c.setCpf(rs.getString("cpf"));
                    Nome.add(c);
                }
                stmt.close();
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return Nome;
    }

    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* MÉTODOS NÃO UTILIZADOS POR ESSA CLASSE */
    
    @Override
    public int contagemOsAbertas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirPedido(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirUsuario(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<String> departamentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdemServicoDados> usuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirItemPedido(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> ordemServico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String numeroPedidoIncremento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarUsuario(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirUsuario(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarExistenciaUsuario(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Versao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public boolean inserirCliente(OrdemServicoDados obj) {
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
    public List<OrdemServicoDados> clientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarCliente(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirCliente(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdemServicoDados> selectClienteFiltro(String nome, String cpf, String email, String celular, int opcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
