/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

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
    
    /**
     * Initializes the controller class.
     */
    
    @FXML private AnchorPane telaCadastro;
    @FXML private ImageView btFecharCadastros;
    @FXML StackPane stackPane1;
    @FXML private ImageView btCadastrarCliente;
    @FXML private ImageView btCadastrarOrdemServico;
    
    private boolean checkTelaAberta = false;
    
    Parent telaCadastroCliente;
    public void carregarFXML(){ 
            try {
                telaCadastroCliente = FXMLLoader.load(telaCadastroController.this.getClass().getResource("cadastro-cliente.fxml"));
                stackPane1.getChildren().add(telaCadastroCliente);
            }catch(IOException ex) {
                 System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    Parent telaCadastroOS;
    public void carregarFXML_OS(){ 
            try {
                telaCadastroOS = FXMLLoader.load(telaCadastroController.this.getClass().getResource("cadastroOrdemServico.fxml"));
                stackPane1.getChildren().add(telaCadastroOS);
            }catch(IOException ex) {
                 System.out.printf("Erro: %s", ex.getMessage());
            }
    }
    
    public void fecharFXML_Cliente() {
        stackPane1.setVisible(false);
        stackPane1.getChildren().remove(telaCadastroCliente);
    }
    
    public void fecharFXML_OS() {
        stackPane1.setVisible(false);
        stackPane1.getChildren().remove(telaCadastroOS);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        telaCadastroControle = this;
        
        btCadastrarCliente.setOnMouseClicked((MouseEvent event) -> {
            stackPane1.setVisible(true);
            carregarFXML();
            
            
        });
        
        btCadastrarOrdemServico.setOnMouseClicked((MouseEvent event) -> {
            stackPane1.setVisible(true);
            carregarFXML_OS();
            
            
        });
        
        btFecharCadastros.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               loginController.loginTela.fecharFXML();
            }
        });
        
        
    }    
    
}
