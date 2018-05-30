/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class TelaconsultaController implements Initializable {

    public static TelaconsultaController telaConsultaControle;
    
    @FXML private ImageView btClientes;
    @FXML private Label lbClientes;
    @FXML private ImageView btFecharConsulta;
    @FXML StackPane stackPaneConsulta;
    
    
    /* Método para carregar o FXML da Consulta do Cliente */
    Parent telaConsultaCliente;
    public void carregarConsultaCliente(){
        try{ 
            telaConsultaCliente = FXMLLoader.load(TelaconsultaController.this.getClass().getResource("telaConsultaCliente.fxml"));
            stackPaneConsulta.getChildren().add(telaConsultaCliente);
            loginController.loginTela.btCadastro.setDisable(true);
            loginController.loginTela.btUsuario.setDisable(true);
            loginController.loginTela.btSair.setVisible(false);
            loginController.loginTela.lbSair.setVisible(false);
            loginController.loginTela.lbCadastros.setDisable(true);
            loginController.loginTela.btConsultas.setDisable(true);
            loginController.loginTela.lbConsultas.setDisable(true);
            loginController.loginTela.lbUsuarios.setDisable(true);
        }catch(IOException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    
    /* Método para fechar o FXML da Consulta do Cliente */
    public void fecharConsultaCliente(){
        stackPaneConsulta.getChildren().remove(telaConsultaCliente);
        stackPaneConsulta.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        telaConsultaControle = this;
        
        /* Ação de fechar a tela de Consulta */
        btFecharConsulta.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loginController.loginTela.fecharTelaConsultaFXML();
                loginController.loginTela.checkTelaAberta = false;
            }
        });
        
        /* Ação para abrir a tela de Consulta Cliente */
        btClientes.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarConsultaCliente();
            }
        });
        
        lbClientes.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarConsultaCliente();
            }
        });
    }    
    
}
