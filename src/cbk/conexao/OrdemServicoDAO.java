/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.conexao;

import cbk.dados.OrdemServicoDados;
import cbk.dados.clienteDados;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabar
 */
public class OrdemServicoDAO extends DAO<OrdemServicoDados> {

    @Override
    public boolean verificaLogin(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Autorizada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inserirCliente(OrdemServicoDados obj) {
        try {
            String sql = "insert into ordem_servico" +
                        "id_ordem" +
                        " , numero_ordem" +
                        " ,nota_fiscal" +
                        " ,data_compra" +
                        " ,defeito_reclamado" +
                        " ,data_abertura" +
                        " ,codigo_produto" +
                        " ,descricao_produto" +
                        " ,voltagem" +
                        " ,numero_serie_produto" +
                        ")values(" +
                        "?,?,?,?,?,?,?,?,?,?" +
                        ")";
                    
            
            
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getId_Ordem());
            stmt.setInt(2, obj.getNumero_OS());
            stmt.setString(3, obj.getNota_fiscal());
            stmt.setString(4, obj.getData_compra());
            stmt.setString(5, obj.getDefeito_reclamado());
            stmt.setString(6, obj.getData_abertura());
            stmt.setString(7, obj.getCodigo_produto());
            stmt.setString(8, obj.getDescricao_produto());
            stmt.setString(9, obj.getVoltagem());
            stmt.setString(10, obj.getNumero_serie_produto());
         
            
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
    public String Versao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public boolean inserirOrdemServico(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
