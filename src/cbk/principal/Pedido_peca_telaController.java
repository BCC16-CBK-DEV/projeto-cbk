package cbk.principal;

import cbk.conexao.pedidoPecaDAO;
import cbk.dados.pedidoPecaDados;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import static javafx.scene.input.KeyCode.E;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class Pedido_peca_telaController implements Initializable {
    
    Pedido_peca_telaController pedidoPeca;

    @FXML private TextField txtCodigo1, txtDesc1, txtQtd1;
    @FXML private TextField txtCodigo2, txtDesc2, txtQtd2;
    @FXML private TextField txtCodigo3, txtDesc3, txtQtd3;
    @FXML private TextField txtCodigo4, txtDesc4, txtQtd4;
    @FXML private TextField txtCodigo5, txtDesc5, txtQtd5;
    @FXML private TextField txtCodigo6, txtDesc6, txtQtd6;
    @FXML private TextField txtCodigo7, txtDesc7, txtQtd7;
    @FXML private TextField txtCodigo8, txtDesc8, txtQtd8;
    @FXML private TextField txtCodigo9, txtDesc9, txtQtd9;
    @FXML private TextField txtCodigo10, txtDesc10, txtQtd10;
    
    @FXML private Label lbCodigo6, lbDesc6, lbQtd6;
    @FXML private Label lbCodigo7, lbDesc7, lbQtd7;
    @FXML private Label lbCodigo8, lbDesc8, lbQtd8;
    @FXML private Label lbCodigo9, lbDesc9, lbQtd9;
    @FXML private Label lbCodigo10, lbDesc10, lbQtd10;
    
    @FXML private Button btAdicionar;
    @FXML private Button btGravar;
    @FXML private Button btCancelar;
    @FXML private TextField txtEmail;
    
    @FXML ComboBox<String> combo_OS;
    int contador = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pedidoPeca = this;
        
        pedidoPecaDAO pdDAO = new pedidoPecaDAO();
        ObservableList<String> opcoes = FXCollections.observableArrayList(pdDAO.ordemServicoNum());
        combo_OS.setItems(opcoes);
        
        btAdicionar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                    
                if(contador == 1){    
                    lbCodigo6.setVisible(true);
                    txtCodigo6.setVisible(true);
                    lbDesc6.setVisible(true);
                    txtDesc6.setVisible(true);
                    lbQtd6.setVisible(true);
                    txtQtd6.setVisible(true);
                    contador++;
                } else if (contador == 2){
                    lbCodigo7.setVisible(true);
                    txtCodigo7.setVisible(true);
                    lbDesc7.setVisible(true);
                    txtDesc7.setVisible(true);
                    lbQtd7.setVisible(true);
                    txtQtd7.setVisible(true);
                    contador++;
                } else if (contador == 3){
                    lbCodigo8.setVisible(true);
                    txtCodigo8.setVisible(true);
                    lbDesc8.setVisible(true);
                    txtDesc8.setVisible(true);
                    lbQtd8.setVisible(true);
                    txtQtd8.setVisible(true);
                    contador++;
                } else if (contador == 4){
                    lbCodigo9.setVisible(true);
                    txtCodigo9.setVisible(true);
                    lbDesc9.setVisible(true);
                    txtDesc9.setVisible(true);
                    lbQtd9.setVisible(true);
                    txtQtd9.setVisible(true);
                    contador++;
                } else if (contador == 5){
                    lbCodigo10.setVisible(true);
                    txtCodigo10.setVisible(true);
                    lbDesc10.setVisible(true);
                    txtDesc10.setVisible(true);
                    lbQtd10.setVisible(true);
                    txtQtd10.setVisible(true);
                    contador++;
                } else if (contador > 5){
                    JOptionPane.showMessageDialog(null,"O total de Itens por pedido são 10, você atingiu o limite!", "Limite excedido",JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        }); 
        
        btGravar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(txtCodigo1.getText().isEmpty() && txtDesc1.getText().isEmpty() && txtQtd1.getText().isEmpty()){ 
                      JOptionPane.showMessageDialog(null,"Não foi inserido informações importantes", "Erro ao Gravar Dados", JOptionPane.ERROR_MESSAGE);
                } else if(combo_OS.getSelectionModel().getSelectedIndex() == -1){   
                        JOptionPane.showMessageDialog(null,"Não foi selecionado o número da Ordem de Serviço", "Ordem de Serviço", JOptionPane.ERROR_MESSAGE);
                }else {
                        pedidoPecaDados pd = new pedidoPecaDados();
                        pedidoPecaDAO pdDAO = new pedidoPecaDAO();

                        String num = pdDAO.numeroPedidoIncremento();
                        int numeroOrdemINDEX = combo_OS.getSelectionModel().getSelectedIndex() + 1;
                        
                        String numeroNovo = null;
                        int qtd = 0;
                        int conversor = Integer.parseInt(num);
                        String aux = null;
                        conversor++;
                        aux = Integer.toString(conversor);
                        qtd = aux.length();
                        if(qtd == 1){
                            numeroNovo = "000".concat(aux);
                        } else if(qtd == 2){
                            numeroNovo = "00".concat(aux);
                        } else if(qtd == 3){
                            numeroNovo = "0".concat(aux);
                        } else{
                            numeroNovo = aux;
                        }
                        
                        pd.setNumeroPedido(numeroNovo);
                        pd.setEmailFabricante(txtEmail.getText());
                        pd.setIdOrdem(numeroOrdemINDEX);

                        pdDAO.inserirPedido(pd);

                        if(!"".equals(txtCodigo1.getText()) && !"".equals(txtDesc1.getText()) && !"".equals(txtQtd1.getText())){
                           int quantidade = 0; 

                            if(txtQtd1.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd1.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd1.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo1.getText());
                            pd.setDescricaoPeca(txtDesc1.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo2.getText())  && !"".equals(txtDesc2.getText()) && !"".equals(txtQtd2.getText())){
                           int quantidade = 0; 

                            if(txtQtd2.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd2.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd2.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo2.getText());
                            pd.setDescricaoPeca(txtDesc2.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo3.getText()) && !"".equals(txtDesc3.getText()) && !"".equals(txtQtd3.getText())){
                           int quantidade = 0; 

                            if(txtQtd3.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd3.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd3.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo3.getText());
                            pd.setDescricaoPeca(txtDesc3.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo4.getText()) && !"".equals(txtDesc4.getText()) && !"".equals(txtQtd4.getText())){
                           int quantidade = 0; 

                            if(txtQtd4.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd4.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd4.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo4.getText());
                            pd.setDescricaoPeca(txtDesc4.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo5.getText()) && !"".equals(txtDesc5.getText()) && !"".equals(txtQtd5.getText())){
                           int quantidade = 0; 

                            if(txtQtd5.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd5.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd5.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo5.getText());
                            pd.setDescricaoPeca(txtDesc5.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo6.getText()) && !"".equals(txtDesc6.getText()) && !"".equals(txtQtd6.getText())){
                           int quantidade = 0; 

                            if(txtQtd6.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd6.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd6.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo6.getText());
                            pd.setDescricaoPeca(txtDesc6.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo7.getText()) && !"".equals(txtDesc7.getText()) && !"".equals(txtQtd7.getText())){
                           int quantidade = 0; 

                            if(txtQtd7.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd7.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd7.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo7.getText());
                            pd.setDescricaoPeca(txtDesc7.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo8.getText()) && !"".equals(txtDesc8.getText()) && !"".equals(txtQtd8.getText())){
                           int quantidade = 0; 

                            if(txtQtd8.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd8.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd8.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo8.getText());
                            pd.setDescricaoPeca(txtDesc8.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo9.getText()) && !"".equals(txtDesc9.getText()) && !"".equals(txtQtd9.getText())){
                           int quantidade = 0; 

                            if(txtQtd9.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd9.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd9.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo9.getText());
                            pd.setDescricaoPeca(txtDesc9.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }

                        if(!"".equals(txtCodigo10.getText()) && !"".equals(txtDesc10.getText()) && !"".equals(txtQtd10.getText())){
                           int quantidade = 0; 

                            if(txtQtd10.getText().isEmpty()){
                                quantidade = 0;
                                txtQtd10.getText();
                            } else{
                                quantidade = Integer.parseInt(txtQtd10.getText());    
                            }

                            pd.setIdPeca(pd.getIdPeca());
                            pd.setCodigoPeca(txtCodigo10.getText());
                            pd.setDescricaoPeca(txtDesc10.getText());
                            pd.setQuantidadePeca(quantidade);

                            pdDAO.inserirItemPedido(pd);
                        }
                        JOptionPane.showMessageDialog(null,"O seu Pedido foi gravado com Sucesso!","Gravado com Sucesso", JOptionPane.PLAIN_MESSAGE);
                        telaCadastroController.telaCadastroControle.FecharTelaCadastroPedido();
                    } 
                }
            });
        
        btCancelar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja realmente Cancelar?", "CANCELAR CADASTRO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(escolha == JOptionPane.YES_OPTION){
                    txtCodigo1.setText(null); txtDesc1.setText(null); txtQtd1.setText(null);
                    txtCodigo2.setText(null); txtDesc2.setText(null); txtQtd2.setText(null);
                    txtCodigo3.setText(null); txtDesc3.setText(null); txtQtd3.setText(null);
                    txtCodigo4.setText(null); txtDesc4.setText(null); txtQtd4.setText(null);
                    txtCodigo5.setText(null); txtDesc5.setText(null); txtQtd5.setText(null);
                    txtCodigo6.setText(null); txtDesc6.setText(null); txtQtd6.setText(null);
                    txtCodigo7.setText(null); txtDesc7.setText(null); txtQtd7.setText(null);
                    txtCodigo8.setText(null); txtDesc8.setText(null); txtQtd8.setText(null);
                    txtCodigo9.setText(null); txtDesc9.setText(null); txtQtd9.setText(null);
                    txtCodigo10.setText(null); txtDesc10.setText(null); txtQtd10.setText(null);
                    telaCadastroController.telaCadastroControle.FecharTelaCadastroPedido();
                }
            }
        });
        
    }    
}
