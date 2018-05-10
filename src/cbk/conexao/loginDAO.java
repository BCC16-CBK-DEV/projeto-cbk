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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
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
            stmt = conn.prepareStatement("SELECT a.nome_usuario, a.senha FROM usuario as a WHERE a.nome_usuario = ? AND a.senha = ?;");
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
            String sql = "SELECT a.nome_autorizada FROM informacao_autorizada AS a WHERE id_autorizada = 1;";
            
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
            String sql = "SELECT a.versao_num FROM versao AS a WHERE id_versao = (SELECT MAX(id_versao) FROM versao);";
            
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
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int contador = 0;
        
        try{
            stmt = conn.prepareStatement("SELECT count(*) FROM ordem_servico WHERE id_status = 1;");
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                contador = rs.getInt(1);
            }
            
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        
        return contador;
    }

    @Override
    public boolean inserirPedido(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirUsuario(loginDados obj) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO usuario (nome_usuario, senha, id_departamento)"+
                         "VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,obj.getNomeUsuario());
            stmt.setString(2,obj.getSenhaTexto());
            stmt.setInt(3,obj.getIdDepartamento());
            
            
            if(stmt.executeUpdate() == 1){
                rs = stmt.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                obj.setIdUsuario(id);
                return true;
            }
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        
        return false;
    }

    @Override
    public List<loginDados> usuarios() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<loginDados> listaDeUsuarios = new LinkedList<>();
        
        try{
            String sql = "SELECT a.id_usuario, a.nome_usuario, b.nome_departamento FROM usuario AS a LEFT JOIN departamentos AS b"+
                        "ON a.id_departamento = b.id_departamento";
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                loginDados c = new loginDados();
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNomeUsuario(rs.getString("nome_usuario"));
                c.setNome_departamento(rs.getString("nome_departamento"));
                
                listaDeUsuarios.add(c);
            }
            
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        
        return listaDeUsuarios;
    }

    @Override
    public List<String> departamentos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> dept = new ArrayList<String>();
        
        try{
            stmt = conn.prepareStatement("SELECT nome_departamento FROM departamento ORDER BY id_departamento;");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                dept.add(rs.getString("nome_departamento"));
            }
            stmt.close();
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        
        return dept;
    }

    @Override
    public boolean inserirItemPedido(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> ordemServico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int numeroPedidoIncremento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean finalizarGravarDadosPedido(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
