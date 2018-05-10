/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import cbk.conexao.loginDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;
/**
 *
 * @author igorcasconi
 */
public class loginController implements Initializable {
    
    public static loginController loginTela;
    
    
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private Pane loginPane;
    @FXML private ImageView imagemFundo;
    @FXML ImageView btCadastro;
    @FXML ImageView btUsuario;
    @FXML StackPane stackPane;
    @FXML private Label txtUsuario_ativo;
    @FXML private Label txtAutorizada;
    @FXML private Label txtVersao;
    @FXML private Label txtOrdensAbertas;
    @FXML ImageView btSair;
    
    
    
    
    /* Vai verificar o Login do usuário */
    @FXML 
    private void entrarLogin(ActionEvent event) {    
        loginDAO c = new loginDAO();
       if(c.verificaLogin(txtUsuario.getText(),txtSenha.getText())){
           loginPane.setVisible(false);
           imagemFundo.setVisible(false);
           txtUsuario_ativo.setText(txtUsuario.getText());
           txtAutorizada.setText(c.Autorizada());
           txtVersao.setText(c.Versao());
           txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
       }
       else {
           JOptionPane.showMessageDialog(null,"Senha ou Usuário INCORRETO","LOGIN", JOptionPane.ERROR_MESSAGE);
       }
   }
    Parent telaCadastroFxml;
    public void carregarFXML(){

            try {
                telaCadastroFxml = FXMLLoader.load(loginController.this.getClass().getResource("tela-cadastro.fxml"));
                stackPane.getChildren().add(telaCadastroFxml);
                btSair.setVisible(false);
                btCadastro.setDisable(true);
                btUsuario.setDisable(true);
            }catch (IOException ex) {
                System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    Parent telaUsuario;
    public void UsuarioCarregarFXML(){
         try{
             stackPane.setVisible(true);
             telaUsuario = FXMLLoader.load(loginController.this.getClass().getResource("TelaUsuario.fxml"));
             stackPane.getChildren().add(telaUsuario);
             btSair.setVisible(false);
             btCadastro.setDisable(true);
             btUsuario.setDisable(true);
         }catch(IOException ex){
            System.out.printf("Erro: %s", ex.getMessage());
         }
    }
    
    public void fecharFXML() {
        stackPane.getChildren().remove(telaCadastroFxml);
        stackPane.setVisible(false);
        btSair.setVisible(true);
        btCadastro.setDisable(false);
        btUsuario.setDisable(false);
    }
    
    public void fecharTelaUsuarioFXML(){
        stackPane.getChildren().remove(telaUsuario);
        stackPane.setVisible(false);
        btSair.setVisible(true);
        btCadastro.setDisable(false);
        btUsuario.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginTela = this;
        /* Ao clicar no botão Cadastros abrir tela StackPane */
        btCadastro.setOnMouseClicked((MouseEvent event) -> { 
                stackPane.setVisible(true);
                carregarFXML();
        });
        
        txtSenha.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    loginDAO c = new loginDAO();
                    if(c.verificaLogin(txtUsuario.getText(),txtSenha.getText())){
                        loginPane.setVisible(false);
                        imagemFundo.setVisible(false);
                        txtUsuario_ativo.setText(txtUsuario.getText());
                        txtAutorizada.setText(c.Autorizada());
                        txtVersao.setText(c.Versao());
                        txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Senha ou Usuário INCORRETO","LOGIN", JOptionPane.ERROR_MESSAGE);
                    }
                
                }
            }
        });
 
        btSair.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente SAIR?", "FAZER LOGOFF", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(escolha == JOptionPane.YES_OPTION){
                    stackPane.setVisible(false);
                    txtUsuario.setText(null);
                    txtSenha.setText(null);
                    txtUsuario_ativo.setText(null);                  
                    txtAutorizada.setText(null);
                    txtVersao.setText(null);
                    loginPane.setVisible(true);
                    imagemFundo.setVisible(true);
                }    
            }
        });
        
        btUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                UsuarioCarregarFXML();
            }
        });
        
    }    
    
    
 }
