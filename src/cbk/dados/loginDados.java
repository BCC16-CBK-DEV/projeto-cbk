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
public class loginDados {
    
    private String nomeUsuario;

    public static final String PROP_NOMEUSUARIO = "nomeUsuario";

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        String oldNomeUsuario = this.nomeUsuario;
        this.nomeUsuario = nomeUsuario;
        propertyChangeSupport.firePropertyChange(PROP_NOMEUSUARIO, oldNomeUsuario, nomeUsuario);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

        private String senhaTexto;

    public static final String PROP_SENHATEXTO = "senhaTexto";

    public String getSenhaTexto() {
        return senhaTexto;
    }

    public void setSenhaTexto(String senhaTexto) {
        String oldSenhaTexto = this.senhaTexto;
        this.senhaTexto = senhaTexto;
        propertyChangeSupport.firePropertyChange(PROP_SENHATEXTO, oldSenhaTexto, senhaTexto);
    }

    
}
