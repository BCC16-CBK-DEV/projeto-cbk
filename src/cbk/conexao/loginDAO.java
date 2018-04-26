/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import cbk.dados.loginDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author igorcasconi
 */
public class loginDAO extends DAO<loginDados> {

    @Override
    public boolean verificaLogin(String usuario, String senha) {
        boolean checkLogin = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement("SELECT a.nome_usuario, a.senha FROM usuario as a WHERE a.nome_usuario = ? AND a.senha = ?");
            stmt.setString(1,usuario);
            stmt.setString(2,senha);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                checkLogin = true;
            }
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return checkLogin;
    }

    @Override
    public boolean inserirCliente(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String Autorizada() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String autorizada = null;
        
        
        try {
            String sql = "SELECT a.nome_autorizada FROM informacao_autorizada AS a WHERE id_autorizada = 1";
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                autorizada = rs.getString(1);
            }
            
        } catch(SQLException e) {
            System.out.println("erro: "+e.getMessage());
        }
        
        return autorizada;
    }

    @Override
    public String Versao() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String versao = null;
        
        
        try {
            String sql = "SELECT a.versao_num FROM versao AS a WHERE id_versao = (SELECT MAX(id_versao) FROM versao)";
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                versao = rs.getString(1);
            }
            
        } catch(SQLException e) {
            System.out.println("erro: "+e.getMessage());
        }
        
        return versao;
    }

    @Override
    public boolean inserirOrdemServico(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int SelectId_Cliente(String pNome, String pCpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> Nome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SelectCpf(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
