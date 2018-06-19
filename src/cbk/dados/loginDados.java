
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

        private String email_remetente;

    public static final String PROP_EMAIL_REMETENTE = "email_remetente";

    public String getEmail_remetente() {
        return email_remetente;
    }

    public void setEmail_remetente(String email_remetente) {
        String oldEmail_remetente = this.email_remetente;
        this.email_remetente = email_remetente;
        propertyChangeSupport.firePropertyChange(PROP_EMAIL_REMETENTE, oldEmail_remetente, email_remetente);
    }

        private String senha_email;

    public static final String PROP_SENHA_EMAIL = "senha_email";

    public String getSenha_email() {
        return senha_email;
    }

    public void setSenha_email(String senha_email) {
        String oldSenha_email = this.senha_email;
        this.senha_email = senha_email;
        propertyChangeSupport.firePropertyChange(PROP_SENHA_EMAIL, oldSenha_email, senha_email);
    }

        private String smtp_servidor;

    public static final String PROP_SMTP_SERVIDOR = "smtp_servidor";

    public String getSmtp_servidor() {
        return smtp_servidor;
    }

    public void setSmtp_servidor(String smtp_servidor) {
        String oldSmtp_servidor = this.smtp_servidor;
        this.smtp_servidor = smtp_servidor;
        propertyChangeSupport.firePropertyChange(PROP_SMTP_SERVIDOR, oldSmtp_servidor, smtp_servidor);
    }

        private String porta_smtp;

    public static final String PROP_PORTA_SMTP = "porta_smtp";

    public String getPorta_smtp() {
        return porta_smtp;
    }

    public void setPorta_smtp(String porta_smtp) {
        String oldPorta_smtp = this.porta_smtp;
        this.porta_smtp = porta_smtp;
        propertyChangeSupport.firePropertyChange(PROP_PORTA_SMTP, oldPorta_smtp, porta_smtp);
    }

    
    
}
