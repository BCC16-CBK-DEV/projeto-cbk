/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import cbk.conexao.clienteDAO;
import cbk.dados.clienteDados;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author igorcasconi
 */
public class cadastroClienteController implements Initializable {

    public static cadastroClienteController cadastroClienteTela;
    
    private final cbk.dados.clienteDados cliente1 = new cbk.dados.clienteDados();

    
    
    @FXML private Button btGravar;
    @FXML private Button btCancelar;
    @FXML private TextField txtNome;   
    @FXML private TextField txtCpf;
    @FXML private TextField txtRg;
    @FXML private TextField txtCep;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtBairro;
    @FXML private TextField txtComplemento;
    @FXML private TextField txtNumero;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtCelular;
    @FXML private TextField txtCidade;
    @FXML private TextField txtEstado;
    
    /**
     * Initializes the controller class
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        cadastroClienteTela = this;
        
        btGravar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               int numero_residencia;
               
                
                if(txtNome.getText().isEmpty() && txtCpf.getText().isEmpty() && txtCep.getText().isEmpty() || txtEndereco.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCelular.getText().isEmpty()){ 
                        JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                     
                } else {
                  if(txtNumero.getText().isEmpty()){
                       numero_residencia = 0;
                       txtNumero.getText();
                   } else{
                    numero_residencia = Integer.parseInt(txtNumero.getText());    
                   }
                
                    clienteDados cliente = new clienteDados();
                    cliente.setNome_cliente(txtNome.getText());
                    cliente.setCpf(txtCpf.getText());
                    cliente.setRg(txtRg.getText());
                    cliente.setCep_cliente(txtCep.getText());
                    cliente.setEndereco(txtEndereco.getText());
                    cliente.setBairro(txtBairro.getText());
                    cliente.setComplemento(txtComplemento.getText());
                    cliente.setNumero(numero_residencia);
                    cliente.setEmail(txtEmail.getText());
                    cliente.setTelefone(txtTelefone.getText());
                    cliente.setCelular(txtCelular.getText());
                    cliente.setCidade(txtCidade.getText());
                    cliente.setEstado(txtEstado.getText());
                    
                
                        clienteDAO cd = new clienteDAO();
                        cd.inserirCliente(cliente);
                        telaCadastroController.telaCadastroControle.fecharFXML_Cliente();
                
                }}
        });
        
        btCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR CADASTRO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(escolha == JOptionPane.YES_OPTION){
                    txtNome.setText(null);
                    txtCpf.setText(null);
                    txtRg.setText(null);
                    txtCep.setText(null);
                    txtEndereco.setText(null);
                    txtBairro.setText(null);
                    txtComplemento.setText(null);
                    txtNumero.setText(null);
                    txtEmail.setText(null);
                    txtTelefone.setText(null);
                    txtCelular.setText(null);
                    telaCadastroController.telaCadastroControle.fecharFXML_Cliente();   
                }
            }
        });
    }    
    
}
