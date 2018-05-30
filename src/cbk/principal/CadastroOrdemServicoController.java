/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import cbk.conexao.OrdemServicoDAO;
import cbk.dados.OrdemServicoDados;
import cbk.dados.clienteDados;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gabar
 */
public class CadastroOrdemServicoController implements Initializable {

    public static CadastroOrdemServicoController cadastroOrdemServicoTela;
    
    private final cbk.dados.OrdemServicoDados OS1 = new cbk.dados.OrdemServicoDados();
    
    @FXML private Button btGravarOS;
    @FXML private Button btCancelarOS;
    @FXML DatePicker txtDataAberturaOS; 
    @FXML private TextField txtNotaFiscalOS; 
    @FXML DatePicker txtDataCompraOS;
    @FXML private TextField txtDefeitoOS;
    @FXML private TextField txtCodigoOS;
    @FXML private TextField txtDescricaoOS;
    @FXML private TextField txtVoltagemOS;
    @FXML private TextField txtNumeroSerieOS;
    @FXML private Label lbNumeroOs;
    @FXML private TextField txtCpfCliente;
    @FXML private Label lb_idOS ;
    @FXML private TableView<clienteDados> tabelaClientes;
    @FXML private TableColumn<clienteDados, Integer> colunaIdCliente;
    @FXML private TableColumn<clienteDados, String> colunaNomeCliente;
    @FXML private TableColumn<clienteDados, String> colunaCpf;
    @FXML private ImageView btBuscaCliente;
    @FXML private Button btSelecionarCliente;
    @FXML private Button btCancelarCliente;
    @FXML private Button btBuscarClienteTabela;
    @FXML private TextField txtBuscaNome;
    @FXML private TextField txtNomeCliente;
    @FXML private Pane paneCliente;
    
    OrdemServicoDAO osDAO = new OrdemServicoDAO();
    int clienteIndex = 0; 
    /* Metódo para atualizar a tabela */
    private void atualizarTabela(){
        ObservableList<clienteDados> listaDeClientes = FXCollections.observableArrayList(osDAO.Nome());
        tabelaClientes.setItems(listaDeClientes);
        tabelaClientes.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cadastroOrdemServicoTela = this;
        
        // Seta a data atual para o campo Data Abertura da OS
        txtDataAberturaOS.setValue(LocalDate.now());
        
        // Tabela cliente, inserindo os valores da clienteDados na coluna
        colunaIdCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
 
        // Ação para gravar OS
        btGravarOS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                            
                // Verificação se os campos estão vazio
                if(txtNotaFiscalOS.getText().isEmpty() && txtNumeroSerieOS.getText().isEmpty() && txtDataCompraOS.getValue() == null || txtDefeitoOS.getText().isEmpty() || txtCodigoOS.getText().isEmpty() || 
                        txtDescricaoOS.getText().isEmpty() || txtCpfCliente.getText().isEmpty()){ 
                        JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                     
                } 
                
                    OrdemServicoDados OS = new OrdemServicoDados();
                    OrdemServicoDAO OsDOA = new OrdemServicoDAO();
                    
                    String num = OsDOA.numeroOrdemIncremento();
                    
                    /* Conversor de String para Inteiro para concatenar as 4 casas ao número da Ordem */
                    int qtd = 0;
                    int conversor = Integer.parseInt(num);
                    String aux = null;
                    String numeroNovo = null;
                    conversor++;
                    aux = Integer.toString(conversor);
                    qtd = aux.length();
                    if(qtd == 1){
                        numeroNovo = "000".concat(aux);
                    } else if(qtd == 2){
                        numeroNovo = "00".concat(aux);
                    } else if(qtd == 3){
                        numeroNovo = "0".concat(aux);
                    } else{
                        numeroNovo = aux;
                    }
                    /* Fim da concatenação */          

                    // Converter a data para Sql Date
                    java.sql.Date sqlDataCompra = java.sql.Date.valueOf(txtDataCompraOS.getValue());  
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAberturaOS.getValue());  
                    
                    // Inserir na OrdemServicoDados
                    OS.setNumero_ordem(numeroNovo);
                    OS.setData_abertura(sqlDataAbertura);
                    OS.setNota_fiscal(txtNotaFiscalOS.getText());
                    OS.setData_compra(sqlDataCompra);
                    OS.setDefeito_reclamado(txtDefeitoOS.getText());
                    OS.setCodigoOS(txtCodigoOS.getText());
                    OS.setDescricao_produto(txtDescricaoOS.getText());
                    OS.setNumero_serie_produto(txtNumeroSerieOS.getText());
                    OS.setVoltagem(txtVoltagemOS.getText());
                    OS.setId_ClienteOS(clienteIndex);
                    
                    OsDOA.inserirOrdemServico(OS);  
                    // Aviso da mensagem para finalizar cadastro e descarregar o fxml
                    JOptionPane.showMessageDialog(null,"A ordem de Serviço foi gravado com Sucesso!","Gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                    telaCadastroController.telaCadastroControle.fecharFXML_OS();                
                 }
        });
        // Ação para cancelar o cadastro da OS
        btCancelarOS.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR CADASTRO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if(escolha == JOptionPane.YES_OPTION){
                            txtNotaFiscalOS.setText(null);
                            txtDataCompraOS.setValue(null);
                            txtDefeitoOS.setText(null);
                            txtCodigoOS.setText(null);
                            txtDescricaoOS.setText(null);
                            txtNomeCliente.setText(null);
                            txtCpfCliente.setText(null);
                            txtDataAberturaOS.setValue(null);
                            //lb_idOS.setText(null);
                            
                            telaCadastroController.telaCadastroControle.fecharFXML_OS();
                }
            }
        });
    
        // Imagem Lupa ao lado do campo cliente - Ação para buscar cliente
        btBuscaCliente.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneCliente.setVisible(true);
                btGravarOS.setDisable(true);
                btCancelarOS.setDisable(true);
               if(!txtNomeCliente.getText().isEmpty()){ // nome
                    txtBuscaNome.setText(txtNomeCliente.getText());
                    String nome = txtNomeCliente.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(osDAO.SelectNome(nome));
                    tabelaClientes.setItems(lista);
                    tabelaClientes.refresh();  
                } else {
                   atualizarTabela();
                }
            }
        });
            
        // Ação para buscar cliente - botão para o campo dentro do pane de busca do cliente
            btBuscarClienteTabela.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!txtBuscaNome.getText().isEmpty()){ // nome
                    txtNomeCliente.setText(null);
                    String nome = txtBuscaNome.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(osDAO.SelectNome(nome));
                    tabelaClientes.setItems(lista);
                    tabelaClientes.refresh();  
                } else {
                   JOptionPane.showMessageDialog(null,"Campo nome está Vazio!","Vazio", JOptionPane.PLAIN_MESSAGE);
                }
            }
            }); 
            
            // Ação para selecionar cliente dentro do Pane cliente
            btSelecionarCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!tabelaClientes.getSelectionModel().isEmpty()){
                    paneCliente.setVisible(false);
                    txtBuscaNome.setText(null);                
                    clienteDados c2 = tabelaClientes.getSelectionModel().getSelectedItem();                  
                    clienteIndex = c2.getIdcliente();
                    txtNomeCliente.setText(c2.getNome_cliente()); 
                    txtCpfCliente.setText((c2.getCpf()));   
                    btGravarOS.setDisable(false);
                    btCancelarOS.setDisable(false);
                } else {
                    JOptionPane.showMessageDialog(null,"Selecione uma linha","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
                }
            };
           });
            
            // Ação para cancelar seleçao do cliente dentro Pane cliente
            btCancelarCliente.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneCliente.setVisible(false);
                txtBuscaNome.setText(null);
                btGravarOS.setDisable(false);
                btCancelarOS.setDisable(false);
            }
            });
            
        }
    }    
    


        

