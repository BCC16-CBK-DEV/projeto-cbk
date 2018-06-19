
package cbk.principal;

import cbk.conexao.OrdemServicoDAO;
import cbk.conexao.loginDAO;
import cbk.conexao.pedidoPecaDAO;
import cbk.dados.OrdemServicoDados;
import cbk.dados.itemPedidoPecaDados;
import cbk.dados.pedidoPecaDados;
import email.SendMail;
import email.enviarEmail;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JOptionPane;
import org.apache.commons.mail.SimpleEmail;



public class TelaAlteracaoPedidoController implements Initializable {

    public static TelaAlteracaoPedidoController telaItemPedido;
    
    @FXML TableView tabelaItemPedido;
    @FXML TableColumn<itemPedidoPecaDados, Integer> colunaId;
    @FXML TableColumn<itemPedidoPecaDados, String> colunaCodigo;
    @FXML TableColumn<itemPedidoPecaDados, String> colunaDescricao;
    @FXML TableColumn<itemPedidoPecaDados, String> colunaQuantidade;
    @FXML Label lbNumeroPedido;
    @FXML TextField txtEmail;
    @FXML TextField lbNumeroOrdem;
    @FXML TextField txtNumeroBuscaOrdem;
    @FXML private Button btGravar;
    @FXML private Button btEditar;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtQuantidade;
    @FXML private Button btAlterar;
    @FXML private Button btCancelarAlteracao;
    @FXML private Button btCancelar;
    @FXML private ImageView btBuscarNumeroOrdem;
    @FXML private Pane paneNumeroOrdem;
    @FXML private Button btBuscarNumero;
    @FXML private Button btSelecionar;
    @FXML private Button CancelarSelecaoNumero;
    @FXML private TableView tabelaNumeroOrdem;
    @FXML private TableColumn<OrdemServicoDados, Integer> colunaIdOrdem;
    @FXML private TableColumn<OrdemServicoDados, String> colunaNumeroOrdem;
    
    pedidoPecaDAO ppDAO = new pedidoPecaDAO();
    itemPedidoPecaDados ippd = new itemPedidoPecaDados();
    pedidoPecaDados ppDados = new pedidoPecaDados();
    OrdemServicoDAO OS = new OrdemServicoDAO();
    loginDAO ld = new loginDAO();
    
    int idPeca = 0;
    public int idPecaItem = 0;
    
    // Método para atualizar tabela
    public void atualizarTabela(int id){
        ObservableList<itemPedidoPecaDados> listaPedido = FXCollections.observableArrayList(ppDAO.itemPedidoPeca(id));
        tabelaItemPedido.setItems(listaPedido);
        tabelaItemPedido.refresh();
        idPeca = id;
    }
    
    public void atualizarTabelaNumeroOrdem(){
        ObservableList<OrdemServicoDados> listaOrdem = FXCollections.observableArrayList(OS.ordemServico());
        tabelaNumeroOrdem.setItems(listaOrdem);
        tabelaNumeroOrdem.refresh();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        telaItemPedido = this;
        
        ppDados.setIdOrdem(TelaConsultaPedidoPecaController.telaPedidoPecaController.idOrdem);
        
        // Tabela de Item de Pedido de Peça
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idPecaItem"));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        atualizarTabela(idPeca);
        
        // Tabela de Item de Numero da Ordem de Serviço
        colunaIdOrdem.setCellValueFactory(new PropertyValueFactory<>("id_Ordem"));
        colunaNumeroOrdem.setCellValueFactory(new PropertyValueFactory<>("numero_ordem"));
        atualizarTabelaNumeroOrdem();
   
        
        
        btEditar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               if(!tabelaItemPedido.getSelectionModel().isEmpty()){
                    txtCodigo.setDisable(false);
                    txtDescricao.setDisable(false);
                    txtQuantidade.setDisable(false);
                    btAlterar.setDisable(false);
                    btCancelarAlteracao.setDisable(false);
                    
                    itemPedidoPecaDados c = (itemPedidoPecaDados) tabelaItemPedido.getSelectionModel().getSelectedItem();
                    
                    idPecaItem = c.getIdPecaItem();
                    
                    txtCodigo.setText(c.getCodigo());
                    txtDescricao.setText(c.getDescricao());
                    txtQuantidade.setText(Integer.toString(c.getQuantidade()));
                    
                } else {
                   JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
               }
            }
        
        });
        
        btCancelarAlteracao.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                txtCodigo.setText(null);
                txtDescricao.setText(null);
                txtQuantidade.setText(null);
                
                txtCodigo.setDisable(true);
                txtDescricao.setDisable(true);
                txtQuantidade.setDisable(true);
                btAlterar.setDisable(true);
                btCancelarAlteracao.setDisable(true);
            }
        
        });
        
        btAlterar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ippd.setIdPecaItem(idPecaItem);
                ippd.setCodigo(txtCodigo.getText());
                ippd.setDescricao(txtDescricao.getText());
                ippd.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                
                ppDAO.atualizarPedido(ippd);
                atualizarTabela(idPeca);

                JOptionPane.showMessageDialog(null,"O item deste pedido foi atualizado com Sucesso!","Atualizado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                
                txtCodigo.setText(null);
                txtDescricao.setText(null);
                txtQuantidade.setText(null);
                
                txtCodigo.setDisable(true);
                txtDescricao.setDisable(true);
                txtQuantidade.setDisable(true);
                btAlterar.setDisable(true);
                btCancelarAlteracao.setDisable(true);
                
            }
        
        });
        
        
        btGravar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                    
                Object[] options = { "GRAVAR DADOS", "ENVIAR E-MAIL", "CANCELAR"};
                int escolha = JOptionPane.showOptionDialog(null, "Desejar enviar e-mail para o fabricante ou somente gravar dados?", "Aviso",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
                
                if(escolha == 1){
                    int i = 0;

                    String s = "";

                    List<itemPedidoPecaDados> lista = ppDAO.itemPedidoPeca(idPeca);

                    for(i = 0; i < lista.size(); i++){
                        s += "Codigo: "+lista.get(i).getCodigo()+" Descrição: "+lista.get(i).getDescricao()+" Quantidade: "+lista.get(i).getQuantidade_texto()+"\n\n";
                    }


                   if(!txtEmail.getText().isEmpty()){ 
                        SendMail sm = new SendMail(loginController.loginTela.smtp, loginController.loginTela.porta);

                        sm.sendMail(loginController.loginTela.email_remetente,txtEmail.getText(),
                                "CBK - "+ld.Autorizada()+" - Pedido de Peça Nº:"+lbNumeroOrdem.getText(),
                                "\n\nPedido de Peça solicitado pela Autorizada "+ld.Autorizada()+" "
                                        + "\nSegue abaixo a lista dos itens solicitados:\n\n"+s
                                );


                        ppDados.setEmailFabricante(txtEmail.getText());
                       
                        ppDAO.atualizarNumeroOrdem(ppDados);
 
                        JOptionPane.showMessageDialog(null,"O e-mail foi enviado para o fabricante!","e-Mail enviado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        TelaConsultaPedidoPecaController.telaPedidoPecaController.fecharPedidoFXML();
                   }
                } else if (escolha == 0){
                        
                        ppDados.setEmailFabricante(txtEmail.getText());
                        JOptionPane.showMessageDialog(null,"Gravou dados corretamente!","Dados gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        TelaConsultaPedidoPecaController.telaPedidoPecaController.fecharPedidoFXML();
                        
                } else {
                    TelaConsultaPedidoPecaController.telaPedidoPecaController.fecharPedidoFXML();
                }
            }
        });
        
        btCancelar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TelaConsultaPedidoPecaController.telaPedidoPecaController.fecharPedidoFXML();
            }
            
        });
        
        
        
        btBuscarNumeroOrdem.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneNumeroOrdem.setVisible(true);
                btGravar.setDisable(true);
                btCancelar.setDisable(true);
            }        
        });
        
        btSelecionar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                 if(!tabelaNumeroOrdem.getSelectionModel().isEmpty()){
                     OrdemServicoDados c = (OrdemServicoDados) tabelaNumeroOrdem.getSelectionModel().getSelectedItem();
                     
                     lbNumeroOrdem.setText(c.getNumero_ordem());
                     
                     ppDados.setIdOrdem(c.getId_Ordem());
                     
                     paneNumeroOrdem.setVisible(false);
                     btGravar.setDisable(false);
                     btCancelar.setDisable(false);
                 } else {
                     JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
                 }
            }
        });
        
        CancelarSelecaoNumero.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneNumeroOrdem.setVisible(false);
                btGravar.setDisable(false);
                btCancelar.setDisable(false);
            }
        });
        
        btBuscarNumero.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!txtNumeroBuscaOrdem.getText().isEmpty()){
                    pedidoPecaDAO ppDAO = new pedidoPecaDAO();
                    
                    ObservableList<OrdemServicoDados> listaOrdem = FXCollections.observableArrayList(ppDAO.selectNumeroOrdem(txtNumeroBuscaOrdem.getText()));
                    tabelaNumeroOrdem.setItems(listaOrdem);
                    tabelaNumeroOrdem.refresh();
                } else {
                    atualizarTabelaNumeroOrdem();
                }
            }
        });
        
    }    
    
}
