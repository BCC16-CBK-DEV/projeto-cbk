
package cbk.principal;

import cbk.conexao.OrdemServicoDAO;
import cbk.conexao.loginDAO;
import cbk.dados.OrdemServicoDados;
import cbk.dados.loginDados;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;

public class TelaConsultaOrdemServicoController implements Initializable {

    public static TelaConsultaOrdemServicoController telaConsultaOrdemServico;
    
    @FXML private ImageView btFechar;
    @FXML private TextField txtBuscaNumeroOS;
    @FXML private TextField txtBuscaNotaFiscal;
    @FXML private DatePicker txtDataAbertura;
    @FXML private TextField txtBuscaNomeCliente;
    @FXML private ComboBox<String> cbStatus;
    @FXML private Button btBuscar;
    @FXML private Button btLimpar;
    @FXML private Button btAlterar;
    @FXML private Button btExcluir;
    @FXML private StackPane stackPaneAlterar;
    @FXML private TableView tabelaOS;
    @FXML private TableColumn<OrdemServicoDados, Integer> colunaIdOS;
    @FXML private TableColumn<OrdemServicoDados, String> colunaNumeroOS;
    @FXML private TableColumn<OrdemServicoDados, String> colunaNotaFiscal;
    @FXML private TableColumn<OrdemServicoDados, String> colunaDataAbertura;
    @FXML private TableColumn<OrdemServicoDados, String> colunaNomeCliente;
    @FXML private TableColumn<OrdemServicoDados, String> colunaStatusOS;
    int idOS = 0;
    int idCliente = 0;
    int idTecnico = 0;
    int opcao = 0;
    
    OrdemServicoDAO osDAO = new OrdemServicoDAO();
    loginDados login = new loginDados();
    
    // Método para atualizar tabela
    public void atualizarTabela(){
        ObservableList<OrdemServicoDados> listaOrdem = FXCollections.observableArrayList(osDAO.ordemServico());
        tabelaOS.setItems(listaOrdem);
        tabelaOS.refresh();
    }
    
    // Método para carregar FXML de Cadastro de Ordem de Serviço
    Parent telaAlterarOS;
    public void carregarFXML_OS(){ 
            try {
                telaAlterarOS = FXMLLoader.load(TelaConsultaOrdemServicoController.this.getClass().getResource("OrdemServico.fxml"));
                stackPaneAlterar.getChildren().add(telaAlterarOS);
                loginController.loginTela.btCadastro.setDisable(true);
                loginController.loginTela.btUsuario.setDisable(true);
                loginController.loginTela.btSair.setVisible(false);
                loginController.loginTela.lbSair.setVisible(false);
                loginController.loginTela.lbCadastros.setDisable(true);
                loginController.loginTela.btConsultas.setDisable(true);
                loginController.loginTela.lbConsultas.setDisable(true);
                loginController.loginTela.lbUsuarios.setDisable(true);
            }catch(IOException ex) {
                System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    public void fecharFXML_OS(){
        stackPaneAlterar.setVisible(false);
        stackPaneAlterar.getChildren().remove(telaAlterarOS);
        loginController.loginTela.btCadastro.setDisable(true);
        loginController.loginTela.btUsuario.setDisable(true);
        loginController.loginTela.btSair.setVisible(false);
        loginController.loginTela.lbSair.setVisible(false);
        loginController.loginTela.lbCadastros.setDisable(true);
        loginController.loginTela.btConsultas.setDisable(true);
        loginController.loginTela.lbConsultas.setDisable(true);
        loginController.loginTela.lbUsuarios.setDisable(true);
        loginController.loginTela.btConfiguracao.setDisable(true);
        loginController.loginTela.lbConfiguracao.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        telaConsultaOrdemServico = this;
        
        /* Combobox de Status para Cadastro */
        ObservableList<String> options = FXCollections.observableArrayList(osDAO.status());
        cbStatus.getItems().addAll(options);
        
        /* Alterar Ordem de Serviço */
        btAlterar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!tabelaOS.getSelectionModel().isEmpty()){
                    OrdemServicoDados os = (OrdemServicoDados) tabelaOS.getSelectionModel().getSelectedItem();
                    stackPaneAlterar.setVisible(true);
                    
                    idOS = os.getId_Ordem();
                    idCliente = os.getId_ClienteOS();

                    carregarFXML_OS();
                    
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbOrdemServico.setText("Consultas > Alterar Ordem de Serviço");
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbTextoOrdem.setText("Alterar Ordem de Serviço");
                    
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbNumeroOs.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lb_idOS.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbStatus.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.cbStatus.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbHistorico.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbTecnico.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.btHistorico.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lbObservacao.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtObservacao.setVisible(true);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtTecnico.setVisible(true);
                    
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.lb_idOS.setText(os.getNumero_ordem());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.cbStatus.setValue(os.getStatus_os());
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
                    //convert String to LocalDate
                    LocalDate localDateAbertura = LocalDate.parse(os.getData_abertura_texto(), formatter);
                    LocalDate localDateCompra = LocalDate.parse(os.getData_compra_texto(), formatter);
                    
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtDataAberturaOS.setValue(localDateAbertura);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtNotaFiscalOS.setText(os.getNota_fiscal());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtDataCompraOS.setValue(localDateCompra);
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtDefeitoOS.setText(os.getDefeito_reclamado());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtCodigoOS.setText(os.getCodigo_produto());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtDescricaoOS.setText(os.getDescricao_produto());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtVoltagemOS.setText(os.getVoltagem());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtNumeroSerieOS.setText(os.getNumero_serie_produto());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtNomeCliente.setText(os.getNome_clienteOS());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtCpfCliente.setText(os.getCpfOS());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtTecnico.setText(os.getNome_tecnico());
                    CadastroOrdemServicoController.cadastroOrdemServicoTela.txtObservacao.setText(os.getObservacao());
                    
                    idTecnico = osDAO.tecnico(loginController.loginTela.txtUsuario.getText());
                    
                    atualizarTabela();
                } else {
                 JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
                }  
            }
        
        });
        
        
        /* Fechar tela Consulta de Ordem Serviço */
        btFechar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TelaconsultaController.telaConsultaControle.fecharConsultaOrdem();
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
        
      // Tabela de Ordem de Serviço
        colunaIdOS.setCellValueFactory(new PropertyValueFactory<>("id_Ordem"));
        colunaNumeroOS.setCellValueFactory(new PropertyValueFactory<>("numero_ordem"));
        colunaNotaFiscal.setCellValueFactory(new PropertyValueFactory<>("nota_fiscal"));
        colunaDataAbertura.setCellValueFactory(new PropertyValueFactory<>("data_abertura_texto"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome_clienteOS"));
        colunaStatusOS.setCellValueFactory(new PropertyValueFactory<>("status_os"));
        atualizarTabela();  
        
        
/* Ação para aplicar filtro */
        btBuscar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String numeroOrdem = null;
                String notaFiscal = null;
                String data = null;
                String nome = null;
                int comboStatus = 0;
                opcao = 0;
                
                if(!txtBuscaNumeroOS.getText().isEmpty()){ // numeroOrdem
                    opcao = 1;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNotaFiscal.getText().isEmpty()){ // notaFiscal
                    opcao = 2;
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(txtDataAbertura.getValue() != null){ // data
                    opcao = 3;
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                }else if (!txtBuscaNomeCliente.getText().isEmpty()) { // nome
                    opcao = 4;
                    nome = txtBuscaNomeCliente.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if (cbStatus.getSelectionModel().getSelectedIndex() != -1){ // comboStatus
                    opcao = 5;
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && !txtBuscaNotaFiscal.getText().isEmpty()){ // numeroOrdem + notaFiscal
                    opcao = 6;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && txtDataAbertura.getValue() != null){ //numeroOrdem + data
                    opcao = 7;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && !txtBuscaNomeCliente.getText().isEmpty()){ // numeroOrdem + nome
                    opcao = 8;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    nome = txtBuscaNomeCliente.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1){ // numeroOrdem + comboStatus
                    opcao = 9;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && !txtBuscaNotaFiscal.getText().isEmpty() && txtDataAbertura.getValue() != null)
                { // numeroOS + notaFiscal + data
                    opcao = 10;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && !txtBuscaNotaFiscal.getText().isEmpty() && !txtBuscaNomeCliente.getText().isEmpty())
                { // numeroOS + notaFiscal + nome
                    opcao = 11;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    nome = txtBuscaNomeCliente.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && !txtBuscaNotaFiscal.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // numeroOS + notaFiscal + comboStatus
                    opcao = 12;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && txtDataAbertura.getValue() != null && !txtBuscaNomeCliente.getText().isEmpty())
                { // numeroOS + data + nome
                    opcao = 13;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    nome = txtBuscaNomeCliente.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && txtDataAbertura.getValue() != null && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // numeroOS + data + comboStatus
                    opcao = 14;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh(); 

                } else if(txtDataAbertura.getValue() != null && !txtBuscaNomeCliente.getText().isEmpty())
                { // data + nome
                    opcao = 15;
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    nome = txtBuscaNomeCliente.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh(); 

                } else if(txtDataAbertura.getValue() != null && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // data + comboStatus
                    opcao = 16;
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh(); 

                } else if(txtDataAbertura.getValue() != null && !txtBuscaNomeCliente.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // data + nome + comboStatus
                    opcao = 17;
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    nome = txtBuscaNomeCliente.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh(); 

                } else if(!txtBuscaNomeCliente.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // nome + comboStatus
                    opcao = 18;
                    nome = txtBuscaNomeCliente.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh(); 

                } else if(!txtBuscaNotaFiscal.getText().isEmpty() && txtDataAbertura.getValue() != null)
                { // notaFiscal + data
                    opcao = 19;
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNotaFiscal.getText().isEmpty() && !txtBuscaNomeCliente.getText().isEmpty())
                { // notaFiscal + nome
                    opcao = 20;
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    nome = txtBuscaNomeCliente.getText();
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNotaFiscal.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // notaFiscal + comboStatus
                    opcao = 21;
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNotaFiscal.getText().isEmpty() && !txtBuscaNomeCliente.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // notaFiscal + nome + comboStatus
                    opcao = 22;
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    nome = txtBuscaNomeCliente.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                } else if(!txtBuscaNumeroOS.getText().isEmpty() && !txtBuscaNotaFiscal.getText().isEmpty() &&  txtDataAbertura.getValue() != null &&
                          !txtBuscaNomeCliente.getText().isEmpty() && cbStatus.getSelectionModel().getSelectedIndex() != -1)
                { // numeroOrdem + notaFiscal + data + nome + comboStatus
                    opcao = 23;
                    numeroOrdem = txtBuscaNumeroOS.getText();
                    notaFiscal = txtBuscaNotaFiscal.getText();
                    java.sql.Date sqlDataAbertura = java.sql.Date.valueOf(txtDataAbertura.getValue());
                    data = sqlDataAbertura.toString();
                    nome = txtBuscaNomeCliente.getText();
                    comboStatus = cbStatus.getSelectionModel().getSelectedIndex() + 1;
                    ObservableList<OrdemServicoDados> lista = FXCollections.observableArrayList(osDAO.selectOrdemServicoFiltro(numeroOrdem,notaFiscal,data,nome,comboStatus,opcao));
                    tabelaOS.setItems(lista);
                    tabelaOS.refresh();
                    
                }
            }            
        });
        
        btLimpar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                txtBuscaNumeroOS.setText(null);
                txtBuscaNotaFiscal.setText(null);
                txtDataAbertura.setValue(null);
                txtBuscaNomeCliente.setText(null);
                cbStatus.setValue(null);
                atualizarTabela();
            }
        
        });
        
        // Ação para excluir ordem de serviço
        btExcluir.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            if(!tabelaOS.getSelectionModel().isEmpty()){    
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir esta Ordem de Serviço?", "Excluir Ordem de Serviço", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(escolha == JOptionPane.YES_OPTION){
                  OrdemServicoDados c2 = (OrdemServicoDados) tabelaOS.getSelectionModel().getSelectedItem();
                  osDAO.excluirOrdem(c2);
                 // tabelaOS.refresh();
              } 
            } else {
                JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Exclusão","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
            }
            atualizarTabela();
            }
        });
    }
}
