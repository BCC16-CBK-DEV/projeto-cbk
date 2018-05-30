/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import cbk.conexao.pedidoPecaDAO;
import cbk.dados.pedidoPecaDados;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author igorcasconi
 */
public class telaCadastroController implements Initializable {

    public static telaCadastroController telaCadastroControle;

    @FXML private AnchorPane telaCadastro;
    @FXML private ImageView btFecharCadastros;
    @FXML StackPane stackPane1;
    @FXML private ImageView btCadastrarCliente;
    @FXML private ImageView btCadastrarOrdemServico;
    @FXML ImageView btCadastroPedido;
    @FXML private Label lbCliente;
    @FXML private Label lbOrdemServico;
    @FXML private Label lbPedidoPeca;
    
    /* Método para Carregar a tela de Cadastro de Cliente */
    
    Parent telaCadastroCliente;
    public void carregarFXML(){ 
            try {
                telaCadastroCliente = FXMLLoader.load(telaCadastroController.this.getClass().getResource("cliente.fxml"));
                stackPane1.getChildren().add(telaCadastroCliente);
                cadastroClienteController.cadastroClienteTela.lbCliente.setText("Cadastro > Clientes");
                loginController.loginTela.btCadastro.setDisable(true);
                loginController.loginTela.btUsuario.setDisable(true);
                loginController.loginTela.btSair.setVisible(false);
                loginController.loginTela.lbSair.setVisible(false);
                loginController.loginTela.lbCadastros.setDisable(true);
                loginController.loginTela.btConsultas.setDisable(true);
                loginController.loginTela.lbConsultas.setDisable(true);
                loginController.loginTela.lbUsuarios.setDisable(true);
                cadastroClienteController.cadastroClienteTela.lbIdCliente.setVisible(false);
                cadastroClienteController.cadastroClienteTela.lbIdCliente_numero.setVisible(false);
            }catch(IOException ex) {
                 System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    /* Método para Carregar a tela de Cadastro de Ordem de Serviço */
    Parent telaCadastroOS;
    public void carregarFXML_OS(){ 
            try {
                telaCadastroOS = FXMLLoader.load(telaCadastroController.this.getClass().getResource("OrdemServico.fxml"));
                stackPane1.getChildren().add(telaCadastroOS);
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
    
    /* Método para Fechar a tela de Cadastro de Cliente */
    public void fecharFXML_Cliente() {
       stackPane1.setVisible(false);
        stackPane1.getChildren().remove(telaCadastroCliente);
        loginController.loginTela.btCadastro.setDisable(false);
        loginController.loginTela.btUsuario.setDisable(false);
        loginController.loginTela.btSair.setVisible(true);
        loginController.loginTela.lbSair.setVisible(true);
        loginController.loginTela.lbCadastros.setDisable(false);
        loginController.loginTela.btConsultas.setDisable(false);
        loginController.loginTela.lbConsultas.setDisable(false);
        loginController.loginTela.lbUsuarios.setDisable(false); 
    } 
    
    /* Método para Fechar a tela de Cadastro de Ordem de Servico */
    public void fecharFXML_OS() {
        stackPane1.setVisible(false);
        stackPane1.getChildren().remove(telaCadastroOS);
        loginController.loginTela.btCadastro.setDisable(false);
        loginController.loginTela.btUsuario.setDisable(false);
        loginController.loginTela.btSair.setVisible(true);
        loginController.loginTela.lbSair.setVisible(true);
        loginController.loginTela.lbCadastros.setDisable(false);
        loginController.loginTela.btConsultas.setDisable(false);
        loginController.loginTela.lbConsultas.setDisable(false);
        loginController.loginTela.lbUsuarios.setDisable(false);  
    }
    
    /* Método para Carregar a tela de Cadastro de Pedido de Peça */
    Parent TelaCadastroPedido;
    public void carregarTelaCadastroPedido(){
        try{
             TelaCadastroPedido = FXMLLoader.load(telaCadastroController.this.getClass().getResource("pedidoPeca.fxml"));
             stackPane1.getChildren().add(TelaCadastroPedido); 
             loginController.loginTela.btCadastro.setDisable(true);
             loginController.loginTela.btUsuario.setDisable(true);
             loginController.loginTela.btSair.setVisible(false);
             loginController.loginTela.lbSair.setVisible(false);
             loginController.loginTela.lbCadastros.setDisable(true);
             loginController.loginTela.btConsultas.setDisable(true);
             loginController.loginTela.lbConsultas.setDisable(true);
             loginController.loginTela.lbUsuarios.setDisable(true);
         }catch(IOException ex){
            System.out.printf("Erro: %s", ex.getMessage());
         }
    }
    
    /* Método para fechar a tela de Cadastro de Pedido de Peça */
    public void FecharTelaCadastroPedido (){
        stackPane1.getChildren().remove(TelaCadastroPedido);
        stackPane1.setVisible(false);
        loginController.loginTela.btSair.setVisible(true);
        loginController.loginTela.btCadastro.setDisable(false);
        loginController.loginTela.btUsuario.setDisable(false);    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        telaCadastroControle = this;
        
        /* Ação de botão abrir tela de Cadastro de Cliente */
        btCadastrarCliente.setOnMouseClicked((MouseEvent event) -> {
            stackPane1.setVisible(true);
            carregarFXML();
        });
        
        /* Ação do label de abrir tela de Cadastro de Cliente */
        lbCliente.setOnMouseClicked((MouseEvent event) -> {
            stackPane1.setVisible(true);
            carregarFXML();  
        });
        
        /* Ação de botão abrir tela de Cadastro de Ordem de Serviço */
        btCadastrarOrdemServico.setOnMouseClicked((MouseEvent event) -> {
            stackPane1.setVisible(true);
            carregarFXML_OS();
        });
        
        /* Ação do label abrir tela de Cadastro de Ordem de Serviço */
        lbOrdemServico.setOnMouseClicked((MouseEvent event) -> {
            stackPane1.setVisible(true);
            carregarFXML_OS();
        });
        
        /* Ação de botão de fechar tela de Cadastro */
        btFecharCadastros.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               loginController.loginTela.fecharFXML();
               loginController.loginTela.checkTelaAberta = false;
            }
        });
        
        /* Ação de botão abrir tela de Cadastro de Pedido de Peça */
        btCadastroPedido.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPane1.setVisible(true);
                carregarTelaCadastroPedido();
           }
        });
        
        /* Ação do label de abrir tela de Cadastro de Pedido de Peça */
        lbPedidoPeca.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPane1.setVisible(true);
                carregarTelaCadastroPedido();
           }
        });
        
        
        
    }    
    
}
