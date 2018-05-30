
package cbk.principal;

import MascaraDeCampos.MaskFieldUtil;
import MascaraDeCampos.MaskFormatter;
import cbk.conexao.clienteDAO;
import cbk.dados.clienteDados;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class cadastroClienteController implements Initializable {

    public static cadastroClienteController cadastroClienteTela;

    @FXML private Button btGravar;
    @FXML private Button btCancelar;
    @FXML TextField txtNome;   
    @FXML  TextField txtCpf;
    @FXML  TextField txtRg;
    @FXML  TextField txtCep;
    @FXML  TextField txtEndereco;
    @FXML  TextField txtBairro;
    @FXML  TextField txtComplemento;
    @FXML  TextField txtNumero;
    @FXML  TextField txtEmail;
    @FXML  TextField txtTelefone;
    @FXML  TextField txtCelular;
    @FXML  TextField txtCidade;
    @FXML  TextField txtEstado;
    @FXML Label lbCliente;
    @FXML Label lbIdCliente;
    @FXML Label lbIdCliente_numero;
  
    clienteDAO c1 = new clienteDAO();
    
    public void listaCliente(int id){
       
    }
    
        MaskFieldUtil mask = new MaskFieldUtil() {};
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        cadastroClienteTela = this;
        
        mask.cpfField(this.txtCpf);
        mask.foneField(this.txtTelefone);
        mask.foneField(this.txtCelular);
        mask.cepField(this.txtCep);
        mask.numericField(this.txtNumero);
        MaskFormatter mask2 = new MaskFormatter(txtRg);
        mask2.setMask(MaskFormatter.RG);

        // gravar ou atualizar cadastro do cliente
        btGravar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
       
                if(txtNome.getText().isEmpty() && txtCpf.getText().isEmpty() && txtCep.getText().isEmpty() || txtEndereco.getText().isEmpty() || 
                   txtBairro.getText().isEmpty() || txtEmail.getText().isEmpty() || txtCelular.getText().isEmpty()){ 
                        JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                     
                } else {
                    clienteDados cliente = new clienteDados();
                    cliente.setNome_cliente(txtNome.getText());
                    cliente.setCpf(txtCpf.getText());
                    cliente.setRg(txtRg.getText());
                    cliente.setCep_cliente(txtCep.getText());
                    cliente.setEndereco(txtEndereco.getText());
                    cliente.setBairro(txtBairro.getText());
                    cliente.setComplemento(txtComplemento.getText());
                    cliente.setNumero(txtNumero.getText());
                    cliente.setEmail(txtEmail.getText());
                    cliente.setTelefone(txtTelefone.getText());
                    cliente.setCelular(txtCelular.getText());
                    cliente.setCidade(txtCidade.getText());
                    cliente.setEstado(txtEstado.getText());
                    
                    clienteDAO cd = new clienteDAO();
                    
                    if(lbCliente.getText().equals("Cadastro > Clientes")){
                        cd.inserirCliente(cliente);
                        JOptionPane.showMessageDialog(null,"Novo cliente foi gravado com sucesso!","Gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        telaCadastroController.telaCadastroControle.fecharFXML_Cliente();
                    } else if(lbCliente.getText().equals("Consulta > Alterar Cliente")){
                        
                        int idCliente = Integer.parseInt(lbIdCliente_numero.getText());
                        
                        cliente.setIdcliente(idCliente);
                        
                        cd.atualizarCliente(cliente);
                        JOptionPane.showMessageDialog(null,"Cliente atualizado com sucesso!","Atualizado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        TelaConsultaClienteController.telaConsultaCliente.fecharTelaClienteAlterarFXML();
                        TelaConsultaClienteController.telaConsultaCliente.atualizarTabela();
                    }    
                }}
        });
        
        // Cancelar cadastro e alteração do cliente
        btCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
                    txtCidade.setText(null);
                    txtEstado.setText(null);
                    if(lbCliente.getText().equals("Cadastro > Clientes")){
                        telaCadastroController.telaCadastroControle.fecharFXML_Cliente();
                    } else if(lbCliente.getText().equals("Consulta > Alterar Cliente")){
                        TelaConsultaClienteController.telaConsultaCliente.fecharTelaClienteAlterarFXML();
                    }   
                }
            }
        });
    }    
    
}
