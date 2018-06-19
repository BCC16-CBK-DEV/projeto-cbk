
package cbk.principal;

import cbk.conexao.loginDAO;
import cbk.dados.loginDados;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class TelaConfiguracoesController implements Initializable {

    public static TelaConfiguracoesController telaConfiguracaoControle;
    
    @FXML TextField txtEmail;
    @FXML PasswordField txtSenha;
    @FXML TextField txtSMTP;
    @FXML TextField txtPorta;
    @FXML Button btGravar;
    @FXML ImageView btFechar;
    @FXML Button btAlterar;
    
    loginDAO c = new loginDAO();
    loginDados c1 = new loginDados();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        telaConfiguracaoControle = this;
        
        /* Ação para fechar tela configurações */
        btFechar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                loginController.loginTela.fecharConfiguracoesFXML();
                loginController.loginTela.checkTelaAberta = false;
            }
        });
        
        btAlterar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                txtEmail.setDisable(false);
                txtSenha.setDisable(false);
                txtSMTP.setDisable(false);
                txtPorta.setDisable(false);
                btGravar.setDisable(false);
                btAlterar.setDisable(true);
            } 
        });
        
        btGravar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!txtSenha.getText().isEmpty()){
                    c1.setEmail_remetente(txtEmail.getText());
                    c1.setSenha_email(txtSenha.getText());
                    c1.setSmtp_servidor(txtSMTP.getText());
                    c1.setPorta_smtp(txtPorta.getText());
                    c.atualizaConfiguracao(c1);

                    JOptionPane.showMessageDialog(null,"A configuração foi gravada com sucesso!","Configurações", JOptionPane.PLAIN_MESSAGE);

                    txtEmail.setDisable(true);
                    txtSenha.setDisable(true);
                    txtSMTP.setDisable(true);
                    txtPorta.setDisable(true);
                    btGravar.setDisable(true);
                    btAlterar.setDisable(false);
                } else {
                    JOptionPane.showMessageDialog(null,"Campo Senha está vazio! Favor inserir informação para gravar","Campo vazio", JOptionPane.PLAIN_MESSAGE);
                }    
            }
        
        });
        
        
    }    
    
}
