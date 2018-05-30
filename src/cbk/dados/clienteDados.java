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
public class clienteDados {
    
    private String nome_cliente;

    public static final String PROP_NOME_CLIENTE = "nome_cliente";

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        String oldNome_cliente = this.nome_cliente;
        this.nome_cliente = nome_cliente;
        propertyChangeSupport.firePropertyChange(PROP_NOME_CLIENTE, oldNome_cliente, nome_cliente);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

        private String cpf;

    public static final String PROP_CPF = "cpf";

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        String oldCpf = this.cpf;
        this.cpf = cpf;
        propertyChangeSupport.firePropertyChange(PROP_CPF, oldCpf, cpf);
    }

        private String cep_cliente;

    public static final String PROP_CEP_CLIENTE = "cep_cliente";

    public String getCep_cliente() {
        return cep_cliente;
    }

    public void setCep_cliente(String cep_cliente) {
        String oldCep_cliente = this.cep_cliente;
        this.cep_cliente = cep_cliente;
        propertyChangeSupport.firePropertyChange(PROP_CEP_CLIENTE, oldCep_cliente, cep_cliente);
    }

    
        private String rg;

    public static final String PROP_RG = "rg";

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        String oldRg = this.rg;
        this.rg = rg;
        propertyChangeSupport.firePropertyChange(PROP_RG, oldRg, rg);
    }

        private String endereco;

    public static final String PROP_ENDERECO = "endereco";

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        String oldEndereco = this.endereco;
        this.endereco = endereco;
        propertyChangeSupport.firePropertyChange(PROP_ENDERECO, oldEndereco, endereco);
    }

        private String bairro;

    public static final String PROP_BAIRRO = "bairro";

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        String oldBairro = this.bairro;
        this.bairro = bairro;
        propertyChangeSupport.firePropertyChange(PROP_BAIRRO, oldBairro, bairro);
    }

        private String numero;

    public static final String PROP_NUMERO = "numero";

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        propertyChangeSupport.firePropertyChange(PROP_NUMERO, oldNumero, numero);
    }


        private String email;

    public static final String PROP_EMAIL = "email";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        propertyChangeSupport.firePropertyChange(PROP_EMAIL, oldEmail, email);
    }

    
        private String telefone;

    public static final String PROP_TELEFONE = "telefone";

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        String oldTelefone = this.telefone;
        this.telefone = telefone;
        propertyChangeSupport.firePropertyChange(PROP_TELEFONE, oldTelefone, telefone);
    }

        private String celular;

    public static final String PROP_CELULAR = "celular";

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        String oldCelular = this.celular;
        this.celular = celular;
        propertyChangeSupport.firePropertyChange(PROP_CELULAR, oldCelular, celular);
    }

private Integer idcliente;

    public static final String PROP_IDCLIENTE = "idcliente";

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        Integer oldIdcliente = this.idcliente;
        this.idcliente = idcliente;
        propertyChangeSupport.firePropertyChange(PROP_IDCLIENTE, oldIdcliente, idcliente);
    }
    
        private String complemento;

    public static final String PROP_COMPLEMENTO = "complemento";

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        String oldComplemento = this.complemento;
        this.complemento = complemento;
        propertyChangeSupport.firePropertyChange(PROP_COMPLEMENTO, oldComplemento, complemento);
    }

        private String cidade;

    public static final String PROP_CIDADE = "cidade";

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        String oldCidade = this.cidade;
        this.cidade = cidade;
        propertyChangeSupport.firePropertyChange(PROP_CIDADE, oldCidade, cidade);
    }

        private String estado;

    public static final String PROP_ESTADO = "estado";

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        String oldEstado = this.estado;
        this.estado = estado;
        propertyChangeSupport.firePropertyChange(PROP_ESTADO, oldEstado, estado);
    }

        private int Id_ClienteOS;

    public static final String PROP_ID_CLIENTEOS = "Id_ClienteOS";

    public int getId_ClienteOS() {
        return Id_ClienteOS;
    }

    public void setId_ClienteOS(int Id_ClienteOS) {
        int oldId_ClienteOS = this.Id_ClienteOS;
        this.Id_ClienteOS = Id_ClienteOS;
        propertyChangeSupport.firePropertyChange(PROP_ID_CLIENTEOS, oldId_ClienteOS, Id_ClienteOS);
    }

    
}
