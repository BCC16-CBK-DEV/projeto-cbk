package cbk.principal;

import cbk.conexao.clienteDAO;
import cbk.dados.clienteDados;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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

public class TelaConsultaClienteController implements Initializable {

    public static TelaConsultaClienteController telaConsultaCliente;
    
    @FXML private TextField txtBuscaNome;
    @FXML private TextField txtBuscaCpf;
    @FXML private TextField txtBuscaEmail;
    @FXML private TextField txtBuscaCelular;
    @FXML private Button btBuscar;
    @FXML private Button btAlterar;
    @FXML private Button btExcluir;
    @FXML private Button btLimpar;
    @FXML private ImageView btFecharTela;
    @FXML private TableView tabelaCliente;
    @FXML private TableColumn<clienteDados, Integer> colunaId;
    @FXML private TableColumn<clienteDados, String> colunaNome;
    @FXML private TableColumn<clienteDados, String> colunaCpf;
    @FXML private TableColumn<clienteDados, String> colunaEmail;
    @FXML private TableColumn<clienteDados, String> colunaCelular;
    @FXML private StackPane stackPaneAlterar;
    
    int opcao = 0;
    clienteDAO c1 = new clienteDAO();
    
    // Método para carregar cliente.fxml
    Parent telaCadastroCliente;
    public void carregarClienteFXML(){
        try {
            telaCadastroCliente = FXMLLoader.load(TelaConsultaClienteController.this.getClass().getResource("cliente.fxml"));
            stackPaneAlterar.getChildren().add(telaCadastroCliente);
            loginController.loginTela.btCadastro.setDisable(true);
            loginController.loginTela.btUsuario.setDisable(true);
            loginController.loginTela.btSair.setVisible(false);
            loginController.loginTela.lbSair.setVisible(false);
            loginController.loginTela.lbCadastros.setDisable(true);
            loginController.loginTela.btConsultas.setDisable(true);
            loginController.loginTela.lbConsultas.setDisable(true);
            loginController.loginTela.lbUsuarios.setDisable(true);
            cadastroClienteController.cadastroClienteTela.lbIdCliente.setVisible(true);
            cadastroClienteController.cadastroClienteTela.lbIdCliente_numero.setVisible(true);
        } catch(IOException ex) {
                 System.out.printf("Erro: %s", ex.getMessage());
        }
    }
    
    // fechar cliente.fxml
    public void fecharTelaClienteAlterarFXML(){
        stackPaneAlterar.setVisible(false);
        stackPaneAlterar.getChildren().remove(telaCadastroCliente);
        loginController.loginTela.btCadastro.setDisable(true);
        loginController.loginTela.btUsuario.setDisable(true);
        loginController.loginTela.btSair.setVisible(false);
        loginController.loginTela.lbSair.setVisible(false);
        loginController.loginTela.lbCadastros.setDisable(true);
        loginController.loginTela.btConsultas.setDisable(true);
        loginController.loginTela.lbConsultas.setDisable(true);
        loginController.loginTela.lbUsuarios.setDisable(true);
    }
    
    // Método para atualizar tabela
    public void atualizarTabela(){
        ObservableList<clienteDados> listaDeClientes = FXCollections.observableArrayList(c1.clientes());
        tabelaCliente.setItems(listaDeClientes);
        tabelaCliente.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        telaConsultaCliente = this;
        
        /* Ação para aplicar filtro */
        btBuscar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String nome = null;
                String cpf = null;
                String email = null;
                String celular = null;
                opcao = 0;

                if(!txtBuscaNome.getText().isEmpty()){ // nome
                    opcao = 1;
                    nome = txtBuscaNome.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaNome.getText().isEmpty() && !txtBuscaCpf.getText().isEmpty()){ // nome + cpf
                    opcao = 2;
                    nome = txtBuscaNome.getText();
                    cpf = txtBuscaCpf.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaNome.getText().isEmpty() && !txtBuscaCpf.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty()){ // nome+cpf+email
                    opcao = 3;
                    nome = txtBuscaNome.getText();
                    cpf = txtBuscaCpf.getText();
                    email = txtBuscaEmail.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                }else if (!txtBuscaNome.getText().isEmpty() && !txtBuscaCpf.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty()
                && !txtBuscaCelular.getText().isEmpty()){ // nome + cpf + email + celular
                    opcao = 4;
                    nome = txtBuscaNome.getText();
                    cpf = txtBuscaCpf.getText();
                    email = txtBuscaEmail.getText();
                    celular = txtBuscaCelular.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if (!txtBuscaCpf.getText().isEmpty()){ // cpf
                    opcao = 5;
                    cpf = txtBuscaCpf.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaCpf.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty()){ // cpf + email
                    opcao = 6;
                    cpf = txtBuscaCpf.getText();
                    email = txtBuscaEmail.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaCpf.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty() && !txtBuscaCelular.getText().isEmpty()){
                    // cpf + email + celular
                    opcao = 7;
                    cpf = txtBuscaCpf.getText();
                    email = txtBuscaEmail.getText();
                    celular = txtBuscaCelular.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaEmail.getText().isEmpty()){ // email
                    opcao = 8;
                    email = txtBuscaEmail.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaEmail.getText().isEmpty() && !txtBuscaCelular.getText().isEmpty()){ // email + celular
                    opcao = 9;
                    email = txtBuscaEmail.getText();
                    celular = txtBuscaCelular.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaNome.getText().isEmpty() && !txtBuscaEmail.getText().isEmpty()){ // nome + email
                    opcao = 10;
                    nome = txtBuscaNome.getText();
                    email = txtBuscaEmail.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaNome.getText().isEmpty() && !txtBuscaCelular.getText().isEmpty()){ // nome + celular
                    opcao = 11;
                    nome = txtBuscaNome.getText();
                    celular = txtBuscaCelular.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaCpf.getText().isEmpty() && !txtBuscaCelular.getText().isEmpty()){ // cpf + celular
                    opcao = 12;
                    cpf = txtBuscaCpf.getText();
                    celular = txtBuscaCelular.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                } else if(!txtBuscaCelular.getText().isEmpty()){ // celular
                    opcao = 13;
                    celular = txtBuscaCelular.getText();
                    ObservableList<clienteDados> lista = FXCollections.observableArrayList(c1.selectClienteFiltro(nome,cpf,email,celular,opcao));
                    tabelaCliente.setItems(lista);
                    tabelaCliente.refresh();
                    
                }
            }            
        });
        
        // Botão para alterar cliente
        btAlterar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
             if(!tabelaCliente.getSelectionModel().isEmpty()){
                 clienteDados c = (clienteDados) tabelaCliente.getSelectionModel().getSelectedItem();
                 stackPaneAlterar.setVisible(true);
                 
                 carregarClienteFXML();
                
                 cadastroClienteController.cadastroClienteTela.lbCliente.setText("Consulta > Alterar Cliente");

                 cadastroClienteController.cadastroClienteTela.lbIdCliente_numero.setText(c.getIdcliente().toString());
                 cadastroClienteController.cadastroClienteTela.txtNome.setText(c.getNome_cliente());
                 cadastroClienteController.cadastroClienteTela.txtCpf.setText(c.getCpf());
                 cadastroClienteController.cadastroClienteTela.txtRg.setText(c.getRg());
                 cadastroClienteController.cadastroClienteTela.txtCep.setText(c.getCep_cliente());
                 cadastroClienteController.cadastroClienteTela.txtEndereco.setText(c.getEndereco());
                 cadastroClienteController.cadastroClienteTela.txtBairro.setText(c.getBairro());
                 cadastroClienteController.cadastroClienteTela.txtCidade.setText(c.getCidade());
                 cadastroClienteController.cadastroClienteTela.txtEstado.setText(c.getEstado());
                 cadastroClienteController.cadastroClienteTela.txtComplemento.setText(c.getComplemento());
                 cadastroClienteController.cadastroClienteTela.txtNumero.setText(c.getNumero());
                 cadastroClienteController.cadastroClienteTela.txtEmail.setText(c.getEmail());
                 cadastroClienteController.cadastroClienteTela.txtCelular.setText(c.getCelular());
                 cadastroClienteController.cadastroClienteTela.txtTelefone.setText(c.getTelefone());
                 
                 atualizarTabela();
             } else {
                 JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
             }  
            }
        });
        
        btExcluir.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            if(!tabelaCliente.getSelectionModel().isEmpty()){    
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir este usuário?", "Excluir Usuário", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(escolha == JOptionPane.YES_OPTION){
                  clienteDados c2 = (clienteDados) tabelaCliente.getSelectionModel().getSelectedItem();
                  c1.excluirCliente(c2);
                  tabelaCliente.refresh();
              } 
            } else {
                JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Exclusão","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
            }
            }
        });
        
        // Fechar tela de Consulta do cliente
        btFecharTela.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TelaconsultaController.telaConsultaControle.fecharConsultaCliente();
                loginController.loginTela.btCadastro.setDisable(false);
                loginController.loginTela.btUsuario.setDisable(false);
                loginController.loginTela.btSair.setVisible(false);
                loginController.loginTela.lbSair.setVisible(false);
                loginController.loginTela.lbCadastros.setDisable(false);
                loginController.loginTela.btConsultas.setDisable(false);
                loginController.loginTela.lbConsultas.setDisable(false);
                loginController.loginTela.lbUsuarios.setDisable(false);
            }
        });
        // Botão limpar campos
        btLimpar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                txtBuscaNome.setText("");
                txtBuscaCpf.setText("");
                txtBuscaEmail.setText("");
                txtBuscaCelular.setText("");
                atualizarTabela();
                opcao = 0;
            }
        
        });
        
        
        // Tabela de cliente
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        atualizarTabela();
        
    }    
    
}
