package cbk.principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @FXML private Label lbOrdem;
    @FXML private ImageView btOrdem;
    @FXML private ImageView btFecharConsulta;
    @FXML private ImageView btPedido;
    @FXML private Label lbPedido;
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
            loginController.loginTela.btConfiguracao.setDisable(true);
            loginController.loginTela.lbConfiguracao.setDisable(true);
        }catch(IOException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    
    /* Método para carregar a tela de Consulta de Ordem */
    Parent telaOrdem;
    public void carregarOrdemFxml(){
        try {
            telaOrdem = FXMLLoader.load(TelaconsultaController.this.getClass().getResource("telaConsultaOrdemServico.fxml"));
            stackPaneConsulta.getChildren().add(telaOrdem);
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
        } catch (IOException ex) {
            Logger.getLogger(TelaconsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Método para carregar a tela de consulta de pedido de peça */
    Parent telaPedido;
    public void carregarPedidoFXML(){
        try {
            telaPedido = FXMLLoader.load(TelaconsultaController.this.getClass().getResource("telaConsultaPedidoPeca.fxml"));
            stackPaneConsulta.getChildren().add(telaPedido);
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
        } catch (IOException ex) {
            Logger.getLogger(TelaconsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /* Método para fechar o FXML da Consulta do Cliente */
    public void fecharConsultaCliente(){
        stackPaneConsulta.getChildren().remove(telaConsultaCliente);
        stackPaneConsulta.setVisible(false);
    }
    
    /* Método para fechar o FXML da Consulta Ordem de Serviço */
    public void fecharConsultaOrdem(){
        stackPaneConsulta.getChildren().remove(telaOrdem);
        stackPaneConsulta.setVisible(false);
    }
    
    /* Método para fechar fxml da Consulta Pedido de Peça*/
    public void fecharConsultaPedido(){
        stackPaneConsulta.getChildren().remove(telaPedido);
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
        
        /* Ação para abrir a tela de Consulta Cliente */
        lbClientes.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarConsultaCliente();
            }
        });
        
        /* Ação para abrir a tela de Consulta Ordem de Serviço */
        btOrdem.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarOrdemFxml();
            }
        });
        
        /* Ação para abrir a tela de Consulta Ordem de Serviço */
        lbOrdem.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarOrdemFxml();
            }
        });
        
        /* Ação para abrir a tela de Consulta Pedido de Peça */
        btPedido.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarPedidoFXML();
            }
        });
        
        /* Ação para abrir a tela de Consulta Pedido de Peça */
         lbPedido.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stackPaneConsulta.setVisible(true);
                carregarPedidoFXML();
            }
        });
    }    
    
}
