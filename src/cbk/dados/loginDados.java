
package cbk.dados;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class loginDados {
    
        private String nome_completo;

    public static final String PROP_NOME_COMPLETO = "nome_completo";

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        String oldNome_completo = this.nome_completo;
        this.nome_completo = nome_completo;
        propertyChangeSupport.firePropertyChange(PROP_NOME_COMPLETO, oldNome_completo, nome_completo);
    }

    
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
