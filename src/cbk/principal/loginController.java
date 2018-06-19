package cbk.principal;

import cbk.conexao.loginDAO;
import cbk.dados.loginDados;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

public class loginController implements Initializable {
    
    public static loginController loginTela;
    
    
    @FXML TextField txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private Pane loginPane;
    @FXML private ImageView imagemFundo;
    @FXML ImageView btCadastro;
    @FXML ImageView btUsuario;
    @FXML ImageView btConsultas;
    @FXML ImageView btConfiguracao;
    @FXML StackPane stackPane;
    @FXML private Label txtUsuario_ativo;
    @FXML private Label txtAutorizada;
    @FXML private Label txtVersao;
    @FXML private Label txtOrdensAbertas;
    @FXML private Label txtOrdensFechadas;
    @FXML ImageView btSair;
    @FXML Label lbCadastros;
    @FXML Label lbConsultas;
    @FXML Label lbUsuarios;
    @FXML Label lbSair;
    @FXML Label lbConfiguracao;
    
    // Variavel para controlar Tela abertas
    boolean checkTelaAberta = false;
    loginDAO c = new loginDAO();
    public int idUsuarioTecnico = 0;
    
    public String email_remetente = "";
    public String senha_email = "";
    public String smtp = "";
    public String porta = "";
    
    /* ação que vai verificar o Login do usuário */
    @FXML 
    private void entrarLogin(ActionEvent event) {    
        
       if(c.verificaLogin(txtUsuario.getText(),txtSenha.getText())){
           loginPane.setVisible(false);
           imagemFundo.setVisible(false);
           txtUsuario_ativo.setText(txtUsuario.getText());
           txtAutorizada.setText(c.Autorizada());
           txtVersao.setText(c.Versao());
           txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
           txtOrdensFechadas.setText(String.format("%d", c.contagemOsFechadas()));
       }
       else {
           JOptionPane.showMessageDialog(null,"Senha ou Usuário INCORRETO","LOGIN", JOptionPane.ERROR_MESSAGE);
       }
   }
    /* Método para carregar a tela de Cadastro*/
    Parent telaCadastroFxml;
    public void carregarFXML(){

            try {
                stackPane.setVisible(true); 
                telaCadastroFxml = FXMLLoader.load(loginController.this.getClass().getResource("tela-cadastro.fxml"));
                stackPane.getChildren().add(telaCadastroFxml);
            }catch (IOException ex) {
                System.out.printf("Erro: %s", ex.getMessage());
            }
    }

    /* Método para carregar a tela de Usuários*/
    Parent telaUsuario;
    public void UsuarioCarregarFXML(){
         try{
             stackPane.setVisible(true);
             telaUsuario = FXMLLoader.load(loginController.this.getClass().getResource("TelaUsuario.fxml"));
             stackPane.getChildren().add(telaUsuario);
         }catch(IOException ex){
            System.out.printf("Erro: %s", ex.getMessage());
         }
    }
    
     /* Método para carregar a tela de Consulta */
    Parent telaConsulta;
    public void carregarConsultaFXML(){
            try {
                stackPane.setVisible(true);
                telaConsulta = FXMLLoader.load(loginController.this.getClass().getResource("tela-consulta.fxml"));
                stackPane.getChildren().add(telaConsulta);
            }catch (IOException ex) {
                System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    /* Método para carregar a tela de Configuracoes */
    Parent telaConfiguracoes;
    public void carregarConfiguracoesFXML(){
            try {
                stackPane.setVisible(true);
                telaConsulta = FXMLLoader.load(loginController.this.getClass().getResource("telaConfiguracoes.fxml"));
                stackPane.getChildren().add(telaConsulta);
            }catch (IOException ex) {
                System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    /* Método para fechar a tela de Cadastro*/
    public void fecharFXML() {
        stackPane.getChildren().remove(telaCadastroFxml);
        stackPane.setVisible(false);
        btSair.setVisible(true);
        lbSair.setVisible(true);
        txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
        txtOrdensFechadas.setText(String.format("%d", c.contagemOsFechadas()));
    }
    
    /* Método para fechar a tela de Consultas */
    public void fecharTelaConsultaFXML(){
        stackPane.getChildren().remove(telaConsulta);
        stackPane.setVisible(false);
        btSair.setVisible(true);
        lbSair.setVisible(true);
        txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
        txtOrdensFechadas.setText(String.format("%d", c.contagemOsFechadas()));
    } 
    
    /* Método para fechar a tela de Usuários*/
    public void fecharTelaUsuarioFXML(){
        stackPane.getChildren().remove(telaUsuario);
        stackPane.setVisible(false);
        btSair.setVisible(true);
        lbSair.setVisible(true);
        txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
        txtOrdensFechadas.setText(String.format("%d", c.contagemOsFechadas()));
    }
    
    /* Método para fechar a tela de Configurações*/
    public void fecharConfiguracoesFXML() {
        stackPane.getChildren().remove(telaConfiguracoes);
        stackPane.setVisible(false);
        btSair.setVisible(true);
        lbSair.setVisible(true);
        txtOrdensAbertas.setText(String.format("%d", c.contagemOsAbertas()));
        txtOrdensFechadas.setText(String.format("%d", c.contagemOsFechadas()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginTela = this;
        
        /* Configuração de Email */
        List<loginDados> lista = c.configuracao();

        for(int i = 0; i < lista.size(); i++){
            email_remetente = lista.get(i).getEmail_remetente();
            senha_email = lista.get(i).getSenha_email();
            smtp = lista.get(i).getSmtp_servidor();
            porta = lista.get(i).getPorta_smtp();
        }
        
        
        /* Pressionar ENTER para realizar o Login */
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
                        txtOrdensFechadas.setText(String.format("%d", c.contagemOsFechadas()));
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Senha ou Usuário INCORRETO","LOGIN", JOptionPane.ERROR_MESSAGE);
                    }
                
                }
            }
        });

        /* Clicar na Imagem SAIR para realizar LOGOFF */    
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
        
        /* Clicar na Imagem SAIR para realizar LOGOFF */   
        lbSair.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        
        /* Ao clicar na imagem Cadastros carregar a tela de Cadastros */
        btCadastro.setOnMouseClicked(new EventHandler<MouseEvent>() { 
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta == false){
                    carregarFXML();
                    checkTelaAberta = true;
                } else {
                    fecharTelaConsultaFXML();
                    fecharTelaUsuarioFXML();
                    fecharConfiguracoesFXML();
                    carregarFXML();
                    checkTelaAberta = true;
                }
            }
        });
        
        /* Ao clicar no Label Cadastros carregar a tela de Cadastros */
        lbCadastros.setOnMouseClicked(new EventHandler<MouseEvent>() { 
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta == false){
                    stackPane.setVisible(true); 
                    carregarFXML();
                    checkTelaAberta = true;
                } else {
                    fecharTelaConsultaFXML();
                    fecharTelaUsuarioFXML();
                    fecharConfiguracoesFXML();
                    carregarFXML();
                    checkTelaAberta = true;
                }
            }
        });
        
        /* Ao clicar na imagem Cadastros carregar a tela de Consultas */
        btConsultas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta == false){
                    carregarConsultaFXML();
                    checkTelaAberta = true;
                } else {
                    fecharFXML();
                    fecharTelaUsuarioFXML();
                    fecharConfiguracoesFXML();
                    carregarConsultaFXML();
                    checkTelaAberta = true;
                }
            }
        });
        
        /* Ao clicar no Label Cadastros carregar a tela de Consultas */
        lbConsultas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               if(checkTelaAberta == false){
                    carregarConsultaFXML();
                    checkTelaAberta = true;
                } else {
                    fecharFXML();
                    fecharTelaUsuarioFXML();
                    fecharConfiguracoesFXML();
                    carregarConsultaFXML();
                    checkTelaAberta = true;
                }
            }
            
        });
 
        /* Clicar na Imagem Usuários para carregar a tela dos Usuários */
        btUsuario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta = false){    
                    UsuarioCarregarFXML();
                    checkTelaAberta = true;
                } else {
                    fecharFXML();
                    fecharTelaConsultaFXML();
                    fecharConfiguracoesFXML();
                    UsuarioCarregarFXML();
                    checkTelaAberta = true;
                }    
            }
        });
        
        /* Clicar no label Usuários para carregar a tela dos Usuários */
         lbUsuarios.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta = false){    
                    UsuarioCarregarFXML();
                    checkTelaAberta = true;
                } else {
                    fecharFXML();
                    fecharTelaConsultaFXML();
                    fecharConfiguracoesFXML();
                    UsuarioCarregarFXML();
                    checkTelaAberta = true;
                } 
            }
        });
         
         /* Clicar na Imagem Configuracao para carregar a tela de Configuracao */
        btConfiguracao.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta = false){    
                    carregarConfiguracoesFXML();
                    checkTelaAberta = true;
                } else {
                    fecharFXML();
                    fecharTelaConsultaFXML();
                    fecharTelaUsuarioFXML();
                    carregarConfiguracoesFXML();
                    checkTelaAberta = true;
                }  
                    
                TelaConfiguracoesController.telaConfiguracaoControle.txtEmail.setText(email_remetente);
                TelaConfiguracoesController.telaConfiguracaoControle.txtSenha.setText(senha_email);
                TelaConfiguracoesController.telaConfiguracaoControle.txtSMTP.setText(smtp);
                TelaConfiguracoesController.telaConfiguracaoControle.txtPorta.setText(porta);
            }
        });
        
        /* Clicar na Label Configuracao para carregar a tela de Configuracao */
         lbConfiguracao.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(checkTelaAberta = false){    
                    carregarConfiguracoesFXML();
                    checkTelaAberta = true;
                } else {
                    fecharFXML();
                    fecharTelaConsultaFXML();
                    fecharTelaUsuarioFXML();
                    carregarConfiguracoesFXML();
                    checkTelaAberta = true;
                } 
    
                TelaConfiguracoesController.telaConfiguracaoControle.txtEmail.setText(email_remetente);
                TelaConfiguracoesController.telaConfiguracaoControle.txtSenha.setText(senha_email);
                TelaConfiguracoesController.telaConfiguracaoControle.txtSMTP.setText(smtp);
                TelaConfiguracoesController.telaConfiguracaoControle.txtPorta.setText(porta);
            }
        });
    }        
 }
