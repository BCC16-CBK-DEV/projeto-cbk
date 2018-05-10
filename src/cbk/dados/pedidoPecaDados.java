/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.dados;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author igorcasconi
 */
public class pedidoPecaDados {
  
    private int idPeca;

    public static final String PROP_IDPECA = "idPeca";
    
    public int getIdPeca() {
        return idPeca;
    } 
    public void setIdPeca(int idPeca) {
        int oldIdPeca = this.idPeca;
        this.idPeca = idPeca;
        propertyChangeSupport.firePropertyChange(PROP_IDPECA, oldIdPeca, idPeca);
    }
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
  
        private int numeroPedido;

    public static final String PROP_NUMEROPEDIDO = "numeroPedido";

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        int oldNumeroPedido = this.numeroPedido;
        this.numeroPedido = numeroPedido;
        propertyChangeSupport.firePropertyChange(PROP_NUMEROPEDIDO, oldNumeroPedido, numeroPedido);
    }

    private String emailFabricante;

    public static final String PROP_EMAILFABRICANTE = "emailFabricante";

    public String getEmailFabricante() {
        return emailFabricante;
    }

    public void setEmailFabricante(String emailFabricante) {
        String oldEmailFabricante = this.emailFabricante;
        this.emailFabricante = emailFabricante;
        propertyChangeSupport.firePropertyChange(PROP_EMAILFABRICANTE, oldEmailFabricante, emailFabricante);
    }

    private int idPecaItem;

    public static final String PROP_IDPECAITEM = "idPecaItem";

    public int getIdPecaItem() {
        return idPecaItem;
    }

    public void setIdPecaItem(int idPecaItem) {
        int oldIdPecaItem = this.idPecaItem;
        this.idPecaItem = idPecaItem;
        propertyChangeSupport.firePropertyChange(PROP_IDPECAITEM, oldIdPecaItem, idPecaItem);
    }

    private String codigoPeca;

    public static final String PROP_CODIGOPECA = "codigoPeca";

    public String getCodigoPeca() {
        return codigoPeca;
    }

    public void setCodigoPeca(String codigoPeca) {
        String oldCodigoPeca = this.codigoPeca;
        this.codigoPeca = codigoPeca;
        propertyChangeSupport.firePropertyChange(PROP_CODIGOPECA, oldCodigoPeca, codigoPeca);
    }

    private String descricaoPeca;

    public static final String PROP_DESCRICAOPECA = "descricaoPeca";

    public String getDescricaoPeca() {
        return descricaoPeca;
    }

    public void setDescricaoPeca(String descricaoPeca) {
        String oldDescricaoPeca = this.descricaoPeca;
        this.descricaoPeca = descricaoPeca;
        propertyChangeSupport.firePropertyChange(PROP_DESCRICAOPECA, oldDescricaoPeca, descricaoPeca);
    }

    private int quantidadePeca;

    public static final String PROP_QUANTIDADEPECA = "quantidadePeca";

    public int getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setQuantidadePeca(int quantidadePeca) {
        int oldQuantidadePeca = this.quantidadePeca;
        this.quantidadePeca = quantidadePeca;
        propertyChangeSupport.firePropertyChange(PROP_QUANTIDADEPECA, oldQuantidadePeca, quantidadePeca);
    }
    
            private int idOrdem;

    public static final String PROP_IDORDEM = "idOrdem";

    public int getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(int idOrdem) {
        int oldIdOrdem = this.idOrdem;
        this.idOrdem = idOrdem;
        propertyChangeSupport.firePropertyChange(PROP_IDORDEM, oldIdOrdem, idOrdem);
    }



}
