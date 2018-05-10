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
    
    
  
    //private ObservableList<loginDados> listaDeUsuarios (){
          //  return FXCollections.observableArrayList(c1.usuarios());
        //}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        loginDAO c1 = new loginDAO(); 
        
        /* Tabela de usuários
        colunaIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colunaNomeUsuarios.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        colunaDepartamentos.setCellValueFactory(new PropertyValueFactory<>("idDepartamento"));
        tabelaUsuarios.setItems(listaDeUsuarios()); */
        
        // Botão Novo Usuário
        btNovoUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneUsuario.setVisible(true);
                lbCadastro.setText("Novo Usuário");
                btFecharUsuario.setVisible(false);
            }
        });
        
        ObservableList<String> options = FXCollections.observableArrayList(c1.departamentos());
        comboDepartamento.setItems(options);

        btGravarNovoUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loginDados c = new loginDados();

                if(txtUsuario_Cadastro.getText().isEmpty() || txtSenha_Cadastro.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    int departamentoIndex = comboDepartamento.getSelectionModel().getSelectedIndex() + 1;
                    
                    c.setNomeUsuario(txtUsuario_Cadastro.getText());
                    c.setSenhaTexto(txtSenha_Cadastro.getText());
                    c.setIdDepartamento(departamentoIndex);
                    
                    c1.inserirUsuario(c);
                    paneUsuario.setVisible(false);
                    txtUsuario_Cadastro.setText(null);
                    txtSenha_Cadastro.setText(null);
                    btFecharUsuario.setVisible(true); 
                }
            }
            
        });
        
        
        btAlterarUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                paneUsuario.setVisible(true);
                lbCadastro.setText("Alterar Usuário");
                btFecharUsuario.setVisible(false);
            }
        
        });
        
        btFecharUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loginController.loginTela.fecharTelaUsuarioFXML();
            }
            
        });
        
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
        
        
        
    }    
    
}
