/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import cbk.dados.clienteDados;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igorcasconi
 */
public class clienteDAO extends DAO<clienteDados> {

    @Override
    public boolean verificaLogin(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirCliente(clienteDados obj) {
        try {
            String sql = "INSERT INTO cliente (nome_cliente, cpf, rg, cep, endereco, bairro, numero, complemento,"+
                    "email, telefone, celular, cidade, uf)"+
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome_cliente());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getCep_cliente());
            stmt.setString(5, obj.getEndereco());
            stmt.setString(6, obj.getBairro());
            stmt.setInt(7, obj.getNumero());
            stmt.setString(8, obj.getComplemento());
            stmt.setString(9, obj.getEmail());
            stmt.setString(10, obj.getTelefone());
            stmt.setString(11, obj.getCelular());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            
            if(stmt.executeUpdate() == 1){
                ResultSet rs = stmt.getGeneratedKeys();
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
}
