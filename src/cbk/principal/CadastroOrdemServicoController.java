/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import cbk.conexao.OrdemServicoDAO;
import cbk.dados.OrdemServicoDados;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gabar
 */
public class CadastroOrdemServicoController implements Initializable {

    public static CadastroOrdemServicoController cadastroOrdemServicoTela;
    
    private final cbk.dados.OrdemServicoDados OS1 = new cbk.dados.OrdemServicoDados();
    
    @FXML private Button btGravarOS;
    @FXML private Button btCancelarOS;
    @FXML private TextField txtDataAberturaOS; 
    @FXML private TextField txtNotaFiscalOS; 
    @FXML private TextField txtDataCompraOS;
    @FXML private TextField txtDefeitoOS;
    @FXML private TextField txtCodigoOS;
    @FXML private TextField txtDescricaoOS;
    @FXML private TextField txtVoltagemOS;
    @FXML private TextField txtNumeroSerieOS;
    @FXML private ComboBox<String> cmbNomeOS;
    @FXML private TextField txtCpfOS;
    @FXML private Label lb_idOS ;
    
    int auxId = 0;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cadastroOrdemServicoTela = this;
        
        cadastroOrdemServicoTela = this;
        
        btGravarOS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                             
                
                if(txtDataAberturaOS.getText().isEmpty() && txtNotaFiscalOS.getText().isEmpty() && txtNumeroSerieOS.getText().isEmpty() &&txtDataCompraOS.getText().isEmpty() || txtDefeitoOS.getText().isEmpty() || txtCodigoOS.getText().isEmpty() || txtDescricaoOS.getText().isEmpty()){ 
                        JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                     
                } 
                
                    OrdemServicoDados OS = new OrdemServicoDados();
                    OS.setData_abertura(txtDataAberturaOS.getText());
                    //OS.setId_Ordem(lb_idOS.getText());
                    OS.setNota_fiscal(txtNotaFiscalOS.getText());
                    OS.setData_compra(txtDataCompraOS.getText());
                    OS.setDefeito_reclamado(txtDefeitoOS.getText());
                    OS.setCodigoOS(txtCodigoOS.getText());
                    OS.setDescricao_produto(txtDescricaoOS.getText());
                    OS.setNumero_serie_produto(txtNumeroSerieOS.getText());
                    OS.setVoltagem(txtVoltagemOS.getText());
                    OS.setCpfOS(txtCpfOS.getText());
                    OS.setId_ClienteOS(auxId);
                   
                    
                    //combo nome
                    //cpf pelo nome
                    
                
                        OrdemServicoDAO OsDOA = new OrdemServicoDAO();
                        OsDOA.inserirOrdemServico(OS);
                        telaCadastroController.telaCadastroControle.fecharFXML_OS();
                
                 }
        });
        
        btCancelarOS.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR CADASTRO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if(escolha == JOptionPane.YES_OPTION){
                            txtNotaFiscalOS.setText(null);
                            txtDataCompraOS.setText(null);
                            txtDefeitoOS.setText(null);
                            txtCodigoOS.setText(null);
                            txtDescricaoOS.setText(null);
                            cmbNomeOS.setItems(null);
                            txtDataAberturaOS.setText(null);
                            //lb_idOS.setText(null);
                            txtCpfOS.setText(null);
                            
                            telaCadastroController.telaCadastroControle.fecharFXML_OS();
                }
            }
        });
        
        OrdemServicoDAO osDAO = new OrdemServicoDAO();
        ObservableList<String> options = FXCollections.observableArrayList(osDAO.Nome());
        cmbNomeOS.setItems(options);
        String auxNome = cmbNomeOS.getValue(); 
        String auxCpf = osDAO.SelectCpf(auxNome);
        txtCpfOS.setText(auxCpf);
        auxId = osDAO.SelectId_Cliente(auxNome, auxCpf);
        
        
    } 
    
    }    
    


        

