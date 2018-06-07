package cbk.conexao;

import cbk.dados.clienteDados;
import cbk.dados.itemPedidoPecaDados;
import cbk.dados.loginDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getInt;

public class loginDAO extends DAO<loginDados> {

    @Override
    public boolean verificaLogin(String usuario, String senha) {
        boolean checkLogin = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        loginDados c = new loginDados();
        
        try {
            stmt = conn.prepareStatement("SELECT a.id_usuario, a.nome_usuario, a.senha FROM usuario as a WHERE BINARY a.nome_usuario = ? AND a.senha = ?;");
            stmt.setString(1,usuario);
            stmt.setString(2,senha);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                c.setIdUsuario(rs.getInt("id_usuario"));
                checkLogin = true;
            }
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return checkLogin;
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
    public boolean inserirUsuario(loginDados obj) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO usuario (nome_usuario, senha, nome_completo, id_departamento)"+
                         "VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,obj.getNomeUsuario());
            stmt.setString(2,obj.getSenhaTexto());
            stmt.setString(3, obj.getNome_completo());
            stmt.setInt(4,obj.getIdDepartamento());
            
            
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
            String sql = "SELECT a.id_usuario, a.nome_usuario, a.nome_completo, b.nome_departamento FROM usuario AS a INNER JOIN departamento AS b "+ 
                         "ON b.id_departamento = a.id_departamento ORDER BY a.id_usuario;";
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                loginDados c = new loginDados();
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNomeUsuario(rs.getString("nome_usuario"));
                c.setNome_completo(rs.getString("nome_completo"));
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
    public boolean atualizarUsuario(loginDados obj) {
        try{
            String sql = "UPDATE usuario AS a SET a.nome_usuario = ?, a.senha = ?, a.id_departamento = ? WHERE a.id_usuario = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNomeUsuario());
            stmt.setString(2, obj.getSenhaTexto());
            stmt.setInt(3, obj.getIdDepartamento()); 
            stmt.setInt(4, obj.getIdUsuario());
            
            if(stmt.executeUpdate() == 1){
                return true;
            }
            
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean excluirUsuario(loginDados obj) {
        try {
            String sql = "DELETE FROM usuario WHERE id_usuario = ?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getIdUsuario());
            
            if(stmt.executeUpdate() == 1){
                return true;
            }
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean verificarExistenciaUsuario(String usuario) {
        try{
            String sql = "SELECT * FROM usuario AS a WHERE a.nome_usuario = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        return false;
    }
    
    @Override
    public int contagemOsFechadas() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int contador = 0;
        
        try{
            stmt = conn.prepareStatement("SELECT count(*) FROM ordem_servico WHERE id_status = 2;");
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                contador = rs.getInt(1);
            }
            
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        
        return contador;
    }
    
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* MÉTODOS NÃO UTILIZADOS POR ESSA CLASSE */
    
     @Override
    public boolean inserirPedido(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirItemPedido(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String numeroPedidoIncremento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean inserirOrdemServico(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<clienteDados> Nome(){
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
    public List<loginDados> clientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarCliente(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirCliente(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<loginDados> selectClienteFiltro(String nome, String cpf, String email, String celular, int opcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean inserirCliente(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<clienteDados> SelectNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> ordemServicoNum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<loginDados> ordemServico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarOS(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int tecnico(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<loginDados> historico(int idOS) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<loginDados> selectOrdemServicoFiltro(String numeroOS, String notaFiscal, String data, String nome, int comboStatus, int opcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirOrdem(loginDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<loginDados> pedidoPeca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<itemPedidoPecaDados> itemPedidoPeca(int idpeca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
