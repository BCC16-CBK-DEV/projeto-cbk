
package cbk.conexao;

import cbk.dados.clienteDados;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clienteDAO extends DAO<clienteDados> {

    @Override
    public boolean inserirCliente(clienteDados obj) {
        try {
            String sql = "INSERT INTO cliente (nome_cliente, cpf, rg, cep, endereco, bairro, numero, complemento,"+
                    "email, telefone, celular, cidade, uf)"+
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ResultSet rs = null;
            
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome_cliente());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getCep_cliente());
            stmt.setString(5, obj.getEndereco());
            stmt.setString(6, obj.getBairro());
            stmt.setString(7, obj.getNumero());
            stmt.setString(8, obj.getComplemento());
            stmt.setString(9, obj.getEmail());
            stmt.setString(10, obj.getTelefone());
            stmt.setString(11, obj.getCelular());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            
            if(stmt.executeUpdate() == 1){
                rs = stmt.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                obj.setIdcliente(id);
                return true;
            }
                     
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Erro ao executar o insert: %s", ex.getMessage());
        }
        return false;    
    }
    
    @Override
    public List<clienteDados> clientes() {
        List<clienteDados> listaClientes = new ArrayList<clienteDados>();
        
        try {
            String sql = "SELECT * FROM cliente AS a;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            clienteDados c = new clienteDados();
            
            c.setIdcliente(rs.getInt("id_cliente"));
            c.setNome_cliente(rs.getString("nome_cliente"));
            c.setCpf(rs.getString("cpf"));
            c.setRg(rs.getString("rg"));
            c.setCep_cliente(rs.getString("cep"));
            c.setEndereco(rs.getString("endereco"));
            c.setBairro(rs.getString("bairro"));
            c.setNumero(rs.getString("numero"));
            c.setComplemento(rs.getString("complemento"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            c.setCelular(rs.getString("celular"));
            c.setCidade(rs.getString("cidade"));
            c.setEstado(rs.getString("uf"));                      
            listaClientes.add(c);
            }

        } catch(SQLException e){
            System.out.printf("Erro ao executar o update: %s", e.getMessage());
        }
        
        return listaClientes;
    }

    @Override
    public boolean atualizarCliente(clienteDados obj) {
        try{
            String sql = "UPDATE cliente AS a SET a.nome_cliente = ?, a.cpf = ?, a.rg = ?, a.cep = ?, "+
                         "a.endereco = ?, a.bairro = ?, a.numero = ?, a.complemento = ?, a.email =?, "+
                         "a.telefone = ?, a.celular = ?, a.cidade = ?, a.uf = ? WHERE a.id_cliente = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome_cliente());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getRg()); 
            stmt.setString(4, obj.getCep_cliente());
            stmt.setString(5, obj.getEndereco());
            stmt.setString(6, obj.getBairro());
            stmt.setString(7, obj.getNumero());
            stmt.setString(8, obj.getComplemento());
            stmt.setString(9, obj.getEmail());
            stmt.setString(10, obj.getTelefone());
            stmt.setString(11, obj.getCelular());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            stmt.setInt(14, obj.getIdcliente());
            
            if(stmt.executeUpdate() == 1){
                return true;
            }
           
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        return false;
        
    }

    @Override
    public boolean excluirCliente(clienteDados obj) {
        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getIdcliente());
            
            if(stmt.executeUpdate() == 1){
                return true;
            }

        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        return false;
    }
    
    @Override
    public List<clienteDados> selectClienteFiltro(String nome, String cpf, String email, String celular, int opcao) {
            String sql = null;
            ResultSet rs = null;
            PreparedStatement stmt = null;
            clienteDados obj = new clienteDados();
            List<clienteDados> listaCliente = new ArrayList<clienteDados>();
        // OPÇÕES DE SELECTS
        try{
            switch(opcao){
                case 1: // nome
                    sql = "SELECT * FROM cliente AS a WHERE a.nome_cliente like '%"+nome+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    } 
                break;
                
                case 2: // nome + cpf
                    sql = "SELECT * FROM cliente AS a WHERE a.nome_cliente like '%"+nome+"%'"+
                          " or a.cpf like '%"+cpf+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                       
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));   
                        listaCliente.add(c);
                    }
                break;
                
                case 3: // nome + cpf + email
                    sql = "SELECT * FROM cliente AS a WHERE a.nome_cliente like '%"+nome+"%'"+
                          " or a.cpf like '5"+cpf+"%' or a.email like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 4: // nome + cpf + email + celular
                    sql = "SELECT * FROM cliente AS a WHERE a.nome_cliente like '%"+nome+"%'"+
                          " or a.cpf like '%"+cpf+"%' or a.email like '%"+email+" or a.celular like '%"+celular+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 5: // cpf
                    sql = "SELECT * FROM cliente AS a WHERE a.cpf like '%"+cpf+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 6: // cpf + email
                    sql = "SELECT * FROM cliente AS a WHERE a.cpf like '%"+cpf+"%'"+
                          " or a.email like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 7: // cpf + email + celular
                    sql = "SELECT * FROM cliente AS a WHERE a.cpf like '%"+cpf+"%'"+
                          " or a.email like '5"+email+"%' or a.celular like '%"+celular+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 8: // email
                    sql = "SELECT * FROM cliente AS a WHERE a.email like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 9: // email + celular
                    sql = "SELECT * FROM cliente AS a WHERE a.email like '%"+email+"%'"+
                          " or a.celular like '%"+celular+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 10: // nome + email
                    sql = "SELECT * FROM cliente AS a WHERE a.nome_cliente like '%"+nome+"%'"+
                          " or a.email like '%"+email+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 11: // nome + celular
                    sql = "SELECT * FROM cliente AS a WHERE a.nome_cliente like '%"+nome+"%'"+
                          " or a.celular like '%"+celular+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                       
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 12: // cpf + celular
                    sql = "SELECT * FROM cliente AS a WHERE a.cpf like '%"+cpf+"%'"+
                          " or a.celular like '%"+celular+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));    
                        listaCliente.add(c);
                    }
                break;
                
                case 13: // celular
                    sql = "SELECT * FROM cliente AS a WHERE a.celular like '%"+celular+"%';";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        clienteDados c = new clienteDados();
                        
                        c.setIdcliente(rs.getInt("id_cliente"));
                        c.setNome_cliente(rs.getString("nome_cliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setRg(rs.getString("rg"));
                        c.setCep_cliente(rs.getString("cep"));
                        c.setEndereco(rs.getString("endereco"));
                        c.setBairro(rs.getString("bairro"));
                        c.setNumero(rs.getString("numero"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setTelefone(rs.getString("telefone"));
                        c.setCelular(rs.getString("celular"));
                        c.setCidade(rs.getString("cidade"));
                        c.setEstado(rs.getString("uf"));   
                        listaCliente.add(c);
                    }
                break;
            }
            
        } catch(SQLException e){
            System.out.printf("Erro: %s", e.getMessage());
        }
        
        return listaCliente;
    }
    

    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* ############################################################################################################################################# */
    /* MÉTODOS NÃO UTILIZADOS POR ESSA CLASSE */
    
    @Override
    public boolean verificaLogin(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String Autorizada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Versao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirOrdemServico(clienteDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<clienteDados> Nome(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SelectCpf(int indexSelecionado) {
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
    public boolean inserirPedido(clienteDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirUsuario(clienteDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> departamentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<clienteDados> usuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirItemPedido(clienteDados obj) {
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
    public boolean atualizarUsuario(clienteDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirUsuario(clienteDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarExistenciaUsuario(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<clienteDados> SelectNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
