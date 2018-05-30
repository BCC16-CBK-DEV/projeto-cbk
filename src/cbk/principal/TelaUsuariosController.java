/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import cbk.conexao.loginDAO;
import cbk.dados.loginDados;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

public class TelaUsuariosController implements Initializable {
    
    @FXML private Button btNovoUsuario;
    @FXML private Button btAlterarUsuario;
    @FXML private Button btExcluirUsuario;
    @FXML private Pane paneUsuario;
    @FXML private Label lbCadastro;
    @FXML private ImageView btFecharUsuario;
    @FXML private Button btGravarNovoUsuario;
    @FXML private Button btCancelarCadastro;
    @FXML private TextField txtUsuario_Cadastro;
    @FXML private PasswordField txtSenha_Cadastro;
    @FXML private ComboBox<String> comboDepartamento;
    @FXML private TableView<loginDados> tabelaUsuarios;
    @FXML private TableColumn<loginDados, Integer> colunaIdUsuario;
    @FXML private TableColumn<loginDados, String> colunaNomeUsuarios;
    @FXML private TableColumn<loginDados, String> colunaDepartamentos;
    
    loginDAO c1 = new loginDAO(); 
    // Variavel criada para receber a id do usuario do banco de dados
    int idUser = 0;
    
    /* Metodo para atualizar tabela */
    private void atualizarTabela(){
        ObservableList<loginDados> listaDeUsuarios = FXCollections.observableArrayList(c1.usuarios());
        tabelaUsuarios.setItems(listaDeUsuarios);
        tabelaUsuarios.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       

    // Botão Novo Usuário
        btNovoUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneUsuario.setVisible(true);
                lbCadastro.setText("Novo Usuário");
                btFecharUsuario.setVisible(false);
                loginController.loginTela.btCadastro.setDisable(true);
                loginController.loginTela.btUsuario.setDisable(true);
                loginController.loginTela.btSair.setVisible(false);
                loginController.loginTela.lbSair.setVisible(false);
                loginController.loginTela.lbCadastros.setDisable(true);
                loginController.loginTela.btConsultas.setDisable(true);
                loginController.loginTela.lbConsultas.setDisable(true);
                loginController.loginTela.lbUsuarios.setDisable(true);
            }
        });
        
        /* Combobox de Departamento para Cadastro */
        ObservableList<String> options = FXCollections.observableArrayList(c1.departamentos());
        comboDepartamento.getItems().addAll(options);

        /* Botão para gravar cadastro de Usuário */
        btGravarNovoUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loginDados c = new loginDados();
                if(lbCadastro.getText().equals("Novo Usuário")){ /* Gravar novo Usuário quando clicar em gravar*/
                    if((txtUsuario_Cadastro.getText().isEmpty() || txtSenha_Cadastro.getText().isEmpty()) && comboDepartamento.getSelectionModel().getSelectedIndex() == -1){
                        JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                    } else {

                        int departamentoIndex = comboDepartamento.getSelectionModel().getSelectedIndex() + 1;
                        // verificar se o nome de usuário já existe no banco de dados
                        if(!c1.verificarExistenciaUsuario(txtUsuario_Cadastro.getText())){
                            c.setNomeUsuario(txtUsuario_Cadastro.getText());
                            c.setSenhaTexto(txtSenha_Cadastro.getText());
                            c.setIdDepartamento(departamentoIndex);
                            c1.inserirUsuario(c);
                            JOptionPane.showMessageDialog(null,"O usuário foi gravado com Sucesso!","Gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                            paneUsuario.setVisible(false);
                            txtUsuario_Cadastro.setText(null);
                            txtSenha_Cadastro.setText(null);
                            btFecharUsuario.setVisible(true);
                            atualizarTabela();
                        }else {
                            JOptionPane.showMessageDialog(null,"Esse nome de usuário já existe","Nome de Usuário", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                } else if(lbCadastro.getText().equals("Alterar Usuário")) { /* Alterar Usuário quando clicar em gravar*/
                    if(txtSenha_Cadastro.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Não foi inserido a senha, favor inserir a senha atual ou uma nova", "Erro ao Alterar Dados", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int departamentoIndex = comboDepartamento.getSelectionModel().getSelectedIndex() + 1;
                        
                            c.setIdUsuario(idUser);
                            c.setNomeUsuario(txtUsuario_Cadastro.getText());
                            c.setSenhaTexto(txtSenha_Cadastro.getText());
                            c.setIdDepartamento(departamentoIndex);

                            c1.atualizarUsuario(c);

                            JOptionPane.showMessageDialog(null,"O usuário foi alterado com Sucesso!","Gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                            paneUsuario.setVisible(false);
                            txtUsuario_Cadastro.setText(null);
                            txtSenha_Cadastro.setText(null);
                            btFecharUsuario.setVisible(true);
                            atualizarTabela();
                    }
                }
            }
        });
        
        /* Botão para alterar usuário */
        btAlterarUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!tabelaUsuarios.getSelectionModel().isEmpty()){
                    paneUsuario.setVisible(true);
                    lbCadastro.setText("Alterar Usuário");
                    btFecharUsuario.setVisible(false);                 
                    loginDados c2 = tabelaUsuarios.getSelectionModel().getSelectedItem();                  
                    idUser = c2.getIdUsuario();
                    txtUsuario_Cadastro.setText(c2.getNomeUsuario()); 
                    comboDepartamento.setValue((c2.getNome_departamento()));
                    loginController.loginTela.btCadastro.setDisable(true);
                    loginController.loginTela.btUsuario.setDisable(true);
                    loginController.loginTela.btSair.setVisible(false);
                    loginController.loginTela.lbSair.setVisible(false);
                    loginController.loginTela.lbCadastros.setDisable(true);
                    loginController.loginTela.btConsultas.setDisable(true);
                    loginController.loginTela.lbConsultas.setDisable(true);
                    loginController.loginTela.lbUsuarios.setDisable(true);
                    
                } else {
                    JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        
        /* Botão para fechar tela de Usuário */
        btFecharUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loginController.loginTela.fecharTelaUsuarioFXML();
                loginController.loginTela.checkTelaAberta = false;
            }   
        });
        
        /* Botão para cancelar o Cadastro do Usuário */
        btCancelarCadastro.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
              int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR CADASTRO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
              if(escolha == JOptionPane.YES_OPTION){
                    txtUsuario_Cadastro.setText(null);
                    txtSenha_Cadastro.setText(null);
                    paneUsuario.setVisible(false);
                    btFecharUsuario.setVisible(true);
              } 
            }
        });
        
        /* Botão para excluir usuário selecionado na tabela*/
        btExcluirUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            if(!tabelaUsuarios.getSelectionModel().isEmpty()){    
              int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir este usuário?", "Excluir Usuário", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
              if(escolha == JOptionPane.YES_OPTION){
                  loginDados c2 = tabelaUsuarios.getSelectionModel().getSelectedItem();
                  c1.excluirUsuario(c2);
                  atualizarTabela();
              } 
              } else {
                JOptionPane.showMessageDialog(null,"Selecione uma linha para realizar Alteração","Nenhuma Seleção", JOptionPane.PLAIN_MESSAGE);
            }
            }
        });
        
        // Tabela de usuários
        colunaIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colunaNomeUsuarios.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        colunaDepartamentos.setCellValueFactory(new PropertyValueFactory<>("nome_departamento"));
        atualizarTabela();
    }    
    
}
