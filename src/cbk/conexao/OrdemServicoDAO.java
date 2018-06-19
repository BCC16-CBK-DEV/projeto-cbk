package cbk.conexao;

import cbk.dados.OrdemServicoDados;
import cbk.dados.clienteDados;
import cbk.dados.itemPedidoPecaDados;
import cbk.dados.loginDados;
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    public List<String> status() { 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> statusOS = new ArrayList<String>();
        
        try{
            stmt = conn.prepareStatement("SELECT nome_status FROM status_os ORDER BY id_status;");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                statusOS.add(rs.getString("nome_status"));
            }
            stmt.close();
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        
        return statusOS;
    }

    @Override
    public String numeroOrdemIncremento() {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       String numeroDaOrdem = null;
 
        try {
            String sql = "SELECT numero_ordem FROM ordem_servico WHERE id_ordem = (SELECT MAX(id_ordem) FROM ordem_servico);";
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
    
    @Override
    public List<OrdemServicoDados> ordemServico() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OrdemServicoDados> listaOrdem = new ArrayList<OrdemServicoDados>();
        String sql = null;
        
        try { 
            sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario ORDER BY a.id_ordem";
            
            stmt = conn.prepareStatement(sql);
           
            rs = stmt.executeQuery();
            
            while(rs.next()){
                OrdemServicoDados c = new OrdemServicoDados();
                c.setId_Ordem(rs.getInt("id_ordem"));
                c.setNumero_ordem(rs.getString("numero_ordem"));
                c.setNota_fiscal(rs.getString("nota_fiscal"));
                c.setData_compra(rs.getDate("data_compra"));
                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
                c.setCodigoOS(rs.getString("codigo_produto"));
                c.setDescricao_produto(rs.getString("descricao_produto"));
                c.setVoltagem(rs.getString("voltagem"));
                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
                c.setData_abertura(rs.getDate("data_abertura"));
                c.setStatus_os(rs.getString("nome_status"));
                c.setId_ClienteOS(rs.getInt("id_cliente"));
                c.setNome_clienteOS(rs.getString("nome_cliente"));
                c.setCpfOS(rs.getString("cpf"));
                c.setObservacao(rs.getString("observacao_tecnico"));
                c.setNome_tecnico(rs.getString("nome_completo"));
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
                c.setData_abertura_texto(dataAberturaTexto);
                c.setData_compra_texto(dataCompraTexto);
                
                listaOrdem.add(c);
            }
        
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return listaOrdem;
    }
    
    @Override
    public boolean atualizarOS(OrdemServicoDados obj) {
       try {
           String sql = "UPDATE ordem_servico SET nota_fiscal = ?, data_compra = ?, defeito_reclamado = ?, codigo_produto = ?,"+
                        " descricao_produto = ?, voltagem = ?, numero_serie_produto = ?, data_abertura = ?, id_status = ?,"+
                        " id_cliente = ?, observacao_tecnico = ?, tecnico = ? WHERE id_ordem = ?;";
           PreparedStatement stmt = conn.prepareStatement(sql);
           
           stmt.setString(1, obj.getNota_fiscal());
           stmt.setDate(2, (Date) obj.getData_compra());
           stmt.setString(3, obj.getDefeito_reclamado());
           stmt.setString(4, obj.getCodigo_produto());
           stmt.setString(5, obj.getDescricao_produto());
           stmt.setString(6, obj.getVoltagem());
           stmt.setString(7, obj.getNumero_serie_produto());
           stmt.setDate(8, (Date) obj.getData_abertura());
           stmt.setInt(9, obj.getId_status());
           stmt.setInt(10, obj.getId_ClienteOS());
           stmt.setString(11, obj.getObservacao());
           stmt.setInt(12, obj.getTecnico());
           stmt.setInt(13, obj.getId_Ordem());
           while(stmt.executeUpdate() == 1){
               return true;
           }
           
       } catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
           System.out.println("erro ao se conectar com o banco: "+e.getMessage());
       }
    
       return false;
    }

    @Override
    public int tecnico(String user) {
           int idTecnico = 0;
        try{
              String sql = "SELECT a.id_usuario FROM usuario AS a WHERE nome_usuario = ?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setString(1, user);
              
              
              ResultSet rs = stmt.executeQuery();
              
              while(rs.next()){
                  idTecnico = rs.getInt("id_usuario");
                  
              }
              
          }catch(SQLException e){
              System.out.println("erro ao se conectar com o banco: "+e.getMessage());
          }
        return idTecnico;
    }
    
    @Override
    public List<OrdemServicoDados> historico(int idOS) {
        List<OrdemServicoDados> historico_lista = new ArrayList<OrdemServicoDados>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
                String sql ="SELECT a.id_historico, b.nome_completo, a.data_historico FROM historico_ordem_servico AS a "+
                            "LEFT JOIN usuario AS b ON a.id_tecnico = b.id_usuario "+
                            "LEFT JOIN ordem_servico AS c ON a.id_ordem = c.id_ordem "+
                            "WHERE a.id_ordem = ? ORDER BY id_historico;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idOS);
               
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    OrdemServicoDados c = new OrdemServicoDados();
                    c.setId_historico(rs.getInt("id_historico"));
                    c.setNome_tecnico(rs.getString("nome_completo"));
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String dataHistoricoTexto = sdf.format(rs.getDate("data_historico"));
                    c.setData_historico_texto(dataHistoricoTexto);
                    
                    historico_lista.add(c);
                }
                stmt.close();
        } catch(SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao se conectar ao banco","Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("erro ao se conectar com o banco: "+e.getMessage());
        }
        
        return historico_lista;
    }
    
      @Override
    public List<OrdemServicoDados> selectOrdemServicoFiltro(String numeroOS, String notaFiscal, String data, String nome, 
    													int comboStatus, int opcao) {
            String sql = null;
            ResultSet rs = null;
            PreparedStatement stmt = null;
            OrdemServicoDados obj = new OrdemServicoDados();
            List<OrdemServicoDados> listaOrdem = new ArrayList<OrdemServicoDados>();
        // OPÇÕES DE SELECTS
        try{
            switch(opcao){
                case 1: // numero da Ordem de Servico
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			        String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 

                        listaOrdem.add(c);
                    } 
                break;
                
                case 2: // notaFiscal
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.nota_fiscal like '%"+notaFiscal+"%' ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 3: // data
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.data_abertura = '"+data+"' ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 4: // nome
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE c.nome_cliente like '%"+nome+"%' ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 5: // comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.id_status like '%"+comboStatus+"%' ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 6: // numeroOS + notaFiscal
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' OR a.nota_fiscal like '%"+notaFiscal+"%' "+  
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        
                        OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 7: // numeroOS + data
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' OR a.data_abertura = '"+data+"' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 8: // numeroOS + nome
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "OR c.nome_cliente like '%"+nome+"%' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 9: // numeroOS + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 10: // numeroOS + notaFiscal + data
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "OR a.nota_fiscal like '%"+notaFiscal+"%' OR a.data_abertura = '"+data+"' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 11: // numeroOS + notaFiscal + nome
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "OR a.nota_fiscal like '%"+notaFiscal+"%' OR c.nome_cliente like '%"+nome+"%' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 12: // numeroOS + notaFiscal + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "OR a.nota_fiscal like '%"+notaFiscal+"%' OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;
                
                case 13: // numeroOS + data + nome
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "OR a.data_abertura = '"+data+"' OR c.nome_cliente like '%"+nome+"%' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 14: // numeroOS + data + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' "+
                         "OR a.data_abertura = '"+data+"' OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 15: // data + nome
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.data_abertura = '"+data+"' "+
                         "OR c.nome_cliente = '%"+nome+"%' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 16: // data + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.data_abertura = '"+data+"' "+
                         "OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 17: // data + nome + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.data_abertura = '"+data+"' "+
                         "OR c.nome_cliente = '%"+nome+"%' OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 18: // nome + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE c.nome_cliente like '%"+nome+"%' "+
                         "OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 19: // notaFiscal + data
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.nota_fiscal like '%"+notaFiscal+"%' "+
                         "OR a.data_abertura = '"+data+"' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 20: // notaFiscal + nome
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.nota_fiscal like '%"+notaFiscal+"%' "+
                         "OR c.nome_cliente like '%"+nome+"%' "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

				case 21: // notaFiscal + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.nota_fiscal like '%"+notaFiscal+"%' "+
                         "OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;    

                case 22: // notaFiscal + nome + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.nota_fiscal like '%"+notaFiscal+"%' "+
                         "OR c.nome_cliente = '%"+nome+"%' OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;

                case 23: // numeroOS + data + notaFiscal + nome + comboStatus
                    sql = "SELECT a.id_ordem, a.numero_ordem, a.nota_fiscal, a.data_compra, a.defeito_reclamado, a.codigo_produto, "+
                         "a.descricao_produto, a.voltagem, a.numero_serie_produto, a.data_abertura, b.nome_status, a.id_cliente, c.nome_cliente, c.cpf, "+
                         "a.observacao_tecnico, d.nome_completo "+
                         "FROM ordem_servico AS a "+ 
                         "INNER JOIN status_os AS b ON a.id_status = b.id_status "+
                         "INNER JOIN cliente AS c ON a.id_cliente = c.id_cliente "+
                         "LEFT JOIN usuario AS d ON a.tecnico = d.id_usuario WHERE a.numero_ordem like '%"+numeroOS+"%' OR a.data_abertura = '"+data+"' "+
                         "OR a.nota_fiscal like '%"+notaFiscal+"%' "+
                         "OR c.nome_cliente = '%"+nome+"%' OR a.id_status = "+comboStatus+" "+
                         "ORDER BY a.id_ordem;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                         OrdemServicoDados c = new OrdemServicoDados();
		                c.setId_Ordem(rs.getInt("id_ordem"));
		                c.setNumero_ordem(rs.getString("numero_ordem"));
		                c.setNota_fiscal(rs.getString("nota_fiscal"));
		                c.setData_compra(rs.getDate("data_compra"));
		                c.setDefeito_reclamado(rs.getString("defeito_reclamado"));
		                c.setCodigoOS(rs.getString("codigo_produto"));
		                c.setDescricao_produto(rs.getString("descricao_produto"));
		                c.setVoltagem(rs.getString("voltagem"));
		                c.setNumero_serie_produto(rs.getString("numero_serie_produto"));
		                c.setData_abertura(rs.getDate("data_abertura"));
		                c.setStatus_os(rs.getString("nome_status"));
		                c.setId_ClienteOS(rs.getInt("id_cliente"));
		                c.setNome_clienteOS(rs.getString("nome_cliente"));
		                c.setCpfOS(rs.getString("cpf"));
		                c.setObservacao(rs.getString("observacao_tecnico"));
		                c.setNome_tecnico(rs.getString("nome_completo"));
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAberturaTexto = sdf.format(rs.getDate("data_abertura"));
		                String dataCompraTexto = sdf.format(rs.getDate("data_compra"));
		                c.setData_abertura_texto(dataAberturaTexto);
		                c.setData_compra_texto(dataCompraTexto); 
		                 
                        listaOrdem.add(c);
                    }
                break;            
            }
            
        } catch(SQLException e){
            System.out.printf("Erro: %s", e.getMessage());
        }
        
        return listaOrdem;
    }
    
    @Override
    public boolean excluirOrdem(OrdemServicoDados obj) {
        try {
            String sql = "DELETE FROM ordem_servico WHERE id_ordem = ?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId_Ordem());
            
            if(stmt.executeUpdate() == 1){
                return true;
            }

        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }
        return false;
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

    @Override
    public List<String> ordemServicoNum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int contagemOsFechadas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdemServicoDados> pedidoPeca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<itemPedidoPecaDados> itemPedidoPeca(int idpeca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarPedido(itemPedidoPecaDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarNumeroOrdem(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdemServicoDados> selectFiltroPedido(String numeroPedido, String numeroOrdem, String email, int opcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdemServicoDados> selectNumeroOrdem(String numeroOrdem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdemServicoDados> configuracao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizaConfiguracao(OrdemServicoDados obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
