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

        private int idUsuario;

    public static final String PROP_IDUSUARIO = "idUsuario";

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        int oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        propertyChangeSupport.firePropertyChange(PROP_IDUSUARIO, oldIdUsuario, idUsuario);
    }

        private int idDepartamento;

    public static final String PROP_IDDEPARTAMENTO = "idDepartamento";

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        int oldIdDepartamento = this.idDepartamento;
        this.idDepartamento = idDepartamento;
        propertyChangeSupport.firePropertyChange(PROP_IDDEPARTAMENTO, oldIdDepartamento, idDepartamento);
    }
    
        private String nome_departamento;

    public static final String PROP_NOME_DEPARTAMENTO = "nome_departamento";

    public String getNome_departamento() {
        return nome_departamento;
    }

    public void setNome_departamento(String nome_departamento) {
        String oldNome_departamento = this.nome_departamento;
        this.nome_departamento = nome_departamento;
        propertyChangeSupport.firePropertyChange(PROP_NOME_DEPARTAMENTO, oldNome_departamento, nome_departamento);
    }

    
}
