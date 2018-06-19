package cbk.principal;

import cbk.conexao.pedidoPecaDAO;
import cbk.dados.itemPedidoPecaDados;
import cbk.dados.pedidoPecaDados;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;

public class TelaConsultaPedidoPecaController implements Initializable {

    public static TelaConsultaPedidoPecaController telaPedidoPecaController;
    
    @FXML TextField txtBuscaNumeroPedido;
    @FXML TextField txtBuscaNumeroOrdem;
    @FXML TextField txtBuscaEmail;
    @FXML Button btBuscar;
    @FXML Button btLimpar;
    @FXML Button btAlterar;
    @FXML Button btExcluir;
    @FXML TableView tabelaPedido;
    @FXML TableColumn<itemPedidoPecaDados, Integer> colunaId;
    @FXML TableColumn<itemPedidoPecaDados, String> colunaNumeroPedido;
    @FXML TableColumn<itemPedidoPecaDados, String> colunaNumeroOrdem;
    @FXML TableColumn<itemPedidoPecaDados, String> colunaEmail;
    @FXML StackPane stackPanePedido;
    @FXML ImageView btFechar;
    
    pedidoPecaDAO ppDAO = new pedidoPecaDAO();
    int idOrdem = 0;
    int opcao = 0;
    
    //Carregar tela de pedido para alteração
    Parent telaPedido1;
    public void carregarPedidoFXML(){
        try {
            telaPedido1 = FXMLLoader.load(TelaConsultaPedidoPecaController.this.getClass().getResource("telaAlteracaoPedido.fxml"));
            stackPanePedido.getChildren().add(telaPedido1);
            
        } catch (IOException ex) {
            Logger.getLogger(TelaConsultaPedidoPecaController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Erro: %s", ex.getMessage());
        }        
    }
    
    //Fechar a tela de pedido para alterar
    public void fecharPedidoFXML(){
        stackPanePedido.setVisible(false);
        stackPanePedido.getChildren().remove(telaPedido1);
    }
    
    // Método para atualizar tabela
    public void atualizarTabela(){
        ObservableList<pedidoPecaDados> listaPedido = FXCollections.observableArrayList(ppDAO.pedidoPeca());
        tabelaPedido.setItems(listaPedido);
        tabelaPedido.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        telaPedidoPecaController = this;
        
        // Tabela de Pedido de Peça
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idPeca"));
        colunaNumeroPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        colunaNumeroOrdem.setCellValueFactory(new PropertyValueFactory<>("numero_ordem"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("emailFabricante"));
        atualizarTabela();  
        
        // Ação para fechar tela de consulta
        btFechar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TelaconsultaController.telaConsultaControle.fecharConsultaPedido();
                loginController.loginTela.btCadastro.setDisable(false);
                loginController.loginTela.btUsuario.setDisable(false);
                loginController.loginTela.btSair.setVisible(false);
                loginController.loginTela.lbSair.setVisible(false);
                loginController.loginTela.lbCadastros.setDisable(false);
                loginController.loginTela.btConsultas.setDisable(false);
                loginController.loginTela.lbConsultas.setDisable(false);
                loginController.loginTela.lbUsuarios.setDisable(false);
                loginController.loginTela.btConfiguracao.setDisable(false);
                loginController.loginTela.lbConfiguracao.setDisable(false);
            }
        });
        
        // Ação para alterar o pedido
        btAlterar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                if(!tabelaPedido.getSelectionModel().isEmpty()){
                    stackPanePedido.setVisible(true);
                    carregarPedidoFXML();
                    pedidoPecaDados pp = (pedidoPecaDados) tabelaPedido.getSelectionModel().getSelectedItem();

                   int id = pp.getIdPeca();
                   idOrdem = pp.getIdOrdem();
                    TelaAlteracaoPedidoController.telaItemPedido.atualizarTabela(id);
                    TelaAlteracaoPedidoController.telaItemPedido.lbNumeroPedido.setText(pp.getNumeroPedido());
                    TelaAlteracaoPedidoController.telaItemPedido.lbNumeroOrdem.setText(pp.getNumero_ordem());
                    TelaAlteracaoPedidoController.telaItemPedido.txtEmail.setText(pp.getEmailFabricante());

                } else {
                    JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
                }
            }
        
        });
        
        /* Ação para aplicar filtro */
        btBuscar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String numeroPedido = null;
                String numeroOrdem = null;
                String email = null;
                opcao = 0;

                if(!txtBuscaNumeroPedido.getText().isEmpty()){ // numeroPedido
                    opcao = 1;
                    numeroOrdem = txtBuscaNumeroPedido.getText();
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                } else if(!txtBuscaNumeroOrdem.getText().isEmpty()){ // numeroOrdem
                    opcao = 2;
                    numeroOrdem = txtBuscaNumeroOrdem.getText();
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                } else if(!txtBuscaEmail.getText().isEmpty()){ // email
                    opcao = 3;
                    email = txtBuscaEmail.getText();
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                }else if (!txtBuscaNumeroPedido.getText().isEmpty() && !txtBuscaNumeroOrdem.getText().isEmpty()) { // numeroPedido + numeroOrdem
                    opcao = 4;
                    numeroPedido = txtBuscaNumeroPedido.getText();
                    numeroOrdem = txtBuscaNumeroOrdem.getText();
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                } else if (!txtBuscaNumeroPedido.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty()){ // numeroPedido + email
                    opcao = 5;
                    numeroPedido = txtBuscaNumeroPedido.getText();
                    email = txtBuscaEmail.getText();
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                } else if(!txtBuscaNumeroOrdem.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty()){ // numeroOrdem + email
                    opcao = 6;
                    numeroOrdem = txtBuscaNumeroOrdem.getText();
                    email = txtBuscaEmail.getText();
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                } else if(!txtBuscaNumeroPedido.getText().isEmpty() && !txtBuscaNumeroOrdem.getText().isEmpty()  && !txtBuscaEmail.getText().isEmpty()){ // numeroPedido + numeroOrdem + email
                    opcao = 7;
                    numeroPedido = txtBuscaNumeroPedido.getText();
                    numeroOrdem = txtBuscaNumeroOrdem.getText();
                    email = txtBuscaEmail.getText();    
                    ObservableList<pedidoPecaDados> lista = FXCollections.observableArrayList(ppDAO.selectFiltroPedido(numeroPedido, numeroOrdem, email, opcao));
                    tabelaPedido.setItems(lista);
                    tabelaPedido.refresh();
                    
                }
            }            
        });
    }    
    
}
