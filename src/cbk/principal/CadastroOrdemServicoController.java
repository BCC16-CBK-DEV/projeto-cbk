/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import MascaraDeCampos.MaskFieldUtil;
import cbk.conexao.OrdemServicoDAO;
import cbk.dados.OrdemServicoDados;
import cbk.dados.clienteDados;
import cbk.dados.loginDados;
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
import javafx.scene.control.TextArea;
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
    
    @FXML Button btGravarOS;
    @FXML Button btCancelarOS;
    @FXML DatePicker txtDataAberturaOS; 
    @FXML TextField txtNotaFiscalOS; 
    @FXML DatePicker txtDataCompraOS;
    @FXML TextField txtDefeitoOS;
    @FXML TextField txtCodigoOS;
    @FXML TextField txtDescricaoOS;
    @FXML TextField txtVoltagemOS;
    @FXML TextField txtNumeroSerieOS;
    @FXML Label lbNumeroOs;
    @FXML TextField txtCpfCliente;
    @FXML Label lb_idOS ;
    @FXML TableView<clienteDados> tabelaClientes;
    @FXML TableColumn<clienteDados, Integer> colunaIdCliente;
    @FXML TableColumn<clienteDados, String> colunaNomeCliente;
    @FXML TableColumn<clienteDados, String> colunaCpf;
    @FXML ImageView btBuscaCliente;
    @FXML Button btSelecionarCliente;
    @FXML Button btCancelarCliente;
    @FXML Button btBuscarClienteTabela;
    @FXML TextField txtBuscaNome;
    @FXML TextField txtNomeCliente;
    @FXML Pane paneCliente;
    @FXML ComboBox<String> cbStatus;
    @FXML Label lbStatus;
    @FXML Label lbOrdemServico;
    @FXML Label lbTextoOrdem;
    @FXML Label lbTecnico;
    @FXML Label lbObservacao;
    @FXML Label lbHistorico;
    @FXML ImageView btHistorico;
    @FXML TextArea txtObservacao;
    @FXML TextField txtTecnico;
    @FXML Pane paneHistorico;
    @FXML ImageView btFecharHistorico;
    @FXML TableView tabelaHistorico;
    @FXML TableColumn<OrdemServicoDados, Integer> colunaIdHistorico;
    @FXML TableColumn<OrdemServicoDados, String> colunaNomeTecnico;
    @FXML TableColumn<OrdemServicoDados, String> colunaData;
    
    OrdemServicoDAO osDAO = new OrdemServicoDAO();
    loginDados login = new loginDados();
    
    int clienteIndex = 0; 
    int idOrdemServico = 0;
    /* Metódo para atualizar a tabela */
    private void atualizarTabela(){
        ObservableList<clienteDados> listaDeClientes = FXCollections.observableArrayList(osDAO.Nome());
        tabelaClientes.setItems(listaDeClientes);
        tabelaClientes.refresh();
    }
    
    /* Método para atualizar tabela Historico */
    public void atualizaHistorico(){
        ObservableList<OrdemServicoDados> listaDeHistorico = FXCollections.observableArrayList(osDAO.historico(TelaConsultaOrdemServicoController.telaConsultaOrdemServico.idOS));
        tabelaHistorico.setItems(listaDeHistorico);
        tabelaHistorico.refresh();
    }
    
    MaskFieldUtil mask = new MaskFieldUtil() {};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cadastroOrdemServicoTela = this;
        
        mask.numericField(this.txtCodigoOS);
        mask.numericField(this.txtVoltagemOS);
        mask.numericField(this.txtNumeroSerieOS);
        
        /* Combobox de Status para Cadastro */
        ObservableList<String> options = FXCollections.observableArrayList(osDAO.status());
        cbStatus.getItems().addAll(options);
        
        // Tabela de historico
        colunaIdHistorico.setCellValueFactory(new PropertyValueFactory<>("id_historico"));
        colunaNomeTecnico.setCellValueFactory(new PropertyValueFactory<>("nome_tecnico"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data_historico_texto"));
        
        // Tabela cliente, inserindo os valores da clienteDados na coluna
        colunaIdCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
 
        // Ação para gravar OS
        btGravarOS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                            
                // Verificação se os campos estão vazio
                if(txtNotaFiscalOS.getText().isEmpty() && txtNumeroSerieOS.getText().isEmpty() && txtDataCompraOS.getValue() == null || 
                        txtDefeitoOS.getText().isEmpty() || txtCodigoOS.getText().isEmpty() || 
                        txtDescricaoOS.getText().isEmpty() || txtCpfCliente.getText().isEmpty() || txtNomeCliente.getText().isEmpty()){ 
                        JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                     
                } else{
                
                    OrdemServicoDados OS = new OrdemServicoDados();
                    OrdemServicoDAO OsDOA = new OrdemServicoDAO();
                    
                    // Converter a data para Sql Date
                    java.sql.Date sqlDataCompra = java.sql.Date.valueOf(txtDataCompraOS.getValue());  
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAberturaOS.getValue());
                    
                    OS.setId_Ordem(TelaConsultaOrdemServicoController.telaConsultaOrdemServico.idOS);
                    OS.setData_abertura(sqlDataAbertura);
                    OS.setNota_fiscal(txtNotaFiscalOS.getText());
                    OS.setData_compra(sqlDataCompra);
                    OS.setDefeito_reclamado(txtDefeitoOS.getText());
                    OS.setCodigoOS(txtCodigoOS.getText());
                    OS.setDescricao_produto(txtDescricaoOS.getText());
                    OS.setNumero_serie_produto(txtNumeroSerieOS.getText());
                    OS.setVoltagem(txtVoltagemOS.getText());
                    
                    
                    if(lbOrdemServico.getText().equals("Cadastros > Ordem de Serviço")){
                        String num = OsDOA.numeroOrdemIncremento();

                        /* Conversor de String para Inteiro para concatenar as 4 casas ao número da Ordem */
                        int qtd = 0;
                        int conversor = Integer.parseInt(num);
                        conversor++;
                        String aux = null;
                        String numeroNovo = null;
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
                        OS.setNumero_ordem(numeroNovo);
                        
                        OS.setId_ClienteOS(clienteIndex);
                        
                        // Inserir no Banco de Dados
                        OsDOA.inserirOrdemServico(OS);
  
                        // Aviso da mensagem para finalizar cadastro e descarregar o fxml
                        JOptionPane.showMessageDialog(null,"A ordem de Serviço foi gravado com Sucesso!","Gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        telaCadastroController.telaCadastroControle.fecharFXML_OS();  
                        
                    } else if(lbOrdemServico.getText().equals("Consultas > Alterar Ordem de Serviço")){
                        
                        // Seleção do comboBox;
                        int statusOSIndex = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                        
                        OS.setId_status(statusOSIndex);
                        
                        // Setar Tecnico no cadastro
                        OS.setTecnico(TelaConsultaOrdemServicoController.telaConsultaOrdemServico.idTecnico);
                        
                        OS.setObservacao(txtObservacao.getText());
                        
                        if(clienteIndex != 0 ){
                            OS.setId_ClienteOS(clienteIndex);
                        } else {
                            OS.setId_ClienteOS(TelaConsultaOrdemServicoController.telaConsultaOrdemServico.idCliente);
                        }
                        
                        //Atualizar ordem de serviço no banco de dados
                        OsDOA.atualizarOS(OS);
                        
                        // Aviso da mensagem para finalizar cadastro e descarregar o fxml
                        JOptionPane.showMessageDialog(null,"A ordem de Serviço foi atualizado com Sucesso!","Atualizado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        TelaConsultaOrdemServicoController.telaConsultaOrdemServico.fecharFXML_OS();
                        
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.lbNumeroOs.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.lb_idOS.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.lbStatus.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.cbStatus.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.lbHistorico.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.lbTecnico.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.btHistorico.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.lbObservacao.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.txtObservacao.setVisible(false);
                        CadastroOrdemServicoController.cadastroOrdemServicoTela.txtTecnico.setVisible(false);
                        
                        TelaConsultaOrdemServicoController.telaConsultaOrdemServico.atualizarTabela();
                    }
                }
            }
        });
        // Ação para cancelar o cadastro da OS
        btCancelarOS.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR CADASTRO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if(escolha == JOptionPane.YES_OPTION){
                            if(lbOrdemServico.getText().equals("Cadastros > Ordem de Serviço")){   
                                lb_idOS.setText("0000");
                                cbStatus.setValue(null);
                                txtNotaFiscalOS.setText(null);
                                txtDataCompraOS.setValue(null);
                                txtDefeitoOS.setText(null);
                                txtCodigoOS.setText(null);
                                txtDescricaoOS.setText(null);
                                txtNomeCliente.setText(null);
                                txtCpfCliente.setText(null);
                                txtDataAberturaOS.setValue(null);
                                telaCadastroController.telaCadastroControle.fecharFXML_OS();
                            } else if(lbOrdemServico.getText().equals("Consultas > Alterar Ordem de Serviço")){
                                lb_idOS.setText("0000");
                                cbStatus.setValue(null);
                                txtNotaFiscalOS.setText(null);
                                txtDataCompraOS.setValue(null);
                                txtDefeitoOS.setText(null);
                                txtCodigoOS.setText(null);
                                txtDescricaoOS.setText(null);
                                txtNomeCliente.setText(null);
                                txtCpfCliente.setText(null);
                                txtDataAberturaOS.setValue(null);
                                TelaConsultaOrdemServicoController.telaConsultaOrdemServico.fecharFXML_OS();
                            }
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
                    String nome = txtBuscaNome.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(osDAO.SelectNome(nome));
                    tabelaClientes.setItems(lista);
                    tabelaClientes.refresh();  
                } else {
                   atualizarTabela();
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
            
            // Ação para abrir Pane de Historico Alteração de Ordem
            btHistorico.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneHistorico.setVisible(true);
                atualizaHistorico();
            }
            });
            
            // Ação para abrir Pane de Historico Alteração de Ordem
            lbHistorico.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneHistorico.setVisible(true);
                atualizaHistorico();
            }
            });
            
            // Ação para fechar Pane de Historico Alteração de Ordem
            btFecharHistorico.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneHistorico.setVisible(false);
                
            }
            });
            
        }
    }    
    


        

