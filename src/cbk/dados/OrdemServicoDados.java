/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.dados;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OrdemServicoDados {
    
    private int id_Ordem;

    public static final String PROP_ID_ORDEM = "id_ordem";

    public int getId_Ordem() {
        return id_Ordem;
    }

    public void setId_Ordem(int id_Ordem) {
        int oldId_Ordem = this.id_Ordem;
        this.id_Ordem = id_Ordem;
        propertyChangeSupport.firePropertyChange(PROP_ID_ORDEM, oldId_Ordem, id_Ordem);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
       private String numero_ordem;

    public static final String PROP_NUMERO_ORDEM = "numero_ordem";

    public String getNumero_ordem() {
        return numero_ordem;
    }

    public void setNumero_ordem(String numero_ordem) {
        String oldNumero_ordem = this.numero_ordem;
        this.numero_ordem = numero_ordem;
        propertyChangeSupport.firePropertyChange(PROP_NUMERO_ORDEM, oldNumero_ordem, numero_ordem);
    }



        private String nota_fiscal;

    public static final String PROP_NOTA_FISCAL = "nota_fiscal";

    public String getNota_fiscal() {
        return nota_fiscal;
    }

    public void setNota_fiscal(String nota_fiscal) {
        String oldNota_fiscal = this.nota_fiscal;
        this.nota_fiscal = nota_fiscal;
        propertyChangeSupport.firePropertyChange(PROP_NOTA_FISCAL, oldNota_fiscal, nota_fiscal);
    }

    
        private String codigo_produto;

    public static final String PROP_CODIGO_PRODUTO = "codigo_produto";

    public String getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigoOS(String codigo_produto) {
        String oldCodigo_produto = this.codigo_produto;
        this.codigo_produto = codigo_produto;
        propertyChangeSupport.firePropertyChange(PROP_CODIGO_PRODUTO, oldCodigo_produto, codigo_produto);
    }

        private String voltagem;

    public static final String PROP_VOLTAGEM = "voltagem";

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        String oldVoltagem = this.voltagem;
        this.voltagem = voltagem;
        propertyChangeSupport.firePropertyChange(PROP_VOLTAGEM, oldVoltagem, voltagem);
    }

        private String numero_serie_produto;

    public static final String PROP_NUMERO_SERIE_PRODUTO = "numero_serie_produto";

    public String getNumero_serie_produto() {
        return numero_serie_produto;
    }

    public void setNumero_serie_produto(String numero_serie_produto) {
        String oldNumero_serie_produto = this.numero_serie_produto;
        this.numero_serie_produto = numero_serie_produto;
        propertyChangeSupport.firePropertyChange(PROP_NUMERO_SERIE_PRODUTO, oldNumero_serie_produto, numero_serie_produto);
    }
    
    private String descricao_produto;

    public static final String PROP_DESCRICAO_PRODUTO = "descricao_produto";

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        String oldDescricao_produto = this.descricao_produto;
        this.descricao_produto = descricao_produto;
        propertyChangeSupport.firePropertyChange(PROP_DESCRICAO_PRODUTO, oldDescricao_produto, descricao_produto);
    }

    
            private Date data_compra;

    public static final String PROP_DATA_COMPRA = "data_compra";

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        Date oldData_compra = this.data_compra;
        this.data_compra = data_compra;
        propertyChangeSupport.firePropertyChange(PROP_DATA_COMPRA, oldData_compra, data_compra);
    }  
    
                private String defeito_reclamado;

    public static final String PROP_DEFEITO_RECLAMADO = "defeito_reclamado";

    public String getDefeito_reclamado() {
        return defeito_reclamado;
    }

    public void setDefeito_reclamado(String defeito_reclamado) {
        String oldDefeito_reclamado = this.defeito_reclamado;
        this.defeito_reclamado = defeito_reclamado;
        propertyChangeSupport.firePropertyChange(PROP_DEFEITO_RECLAMADO, oldDefeito_reclamado, defeito_reclamado);
    }
    
        private Date data_abertura;

    public static final String PROP_DATA_ABERTURA = "data_abertura";

    public Date getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(Date data_abertura) {
        Date oldData_abertura = this.data_abertura;
        this.data_abertura = data_abertura;
        propertyChangeSupport.firePropertyChange(PROP_DATA_ABERTURA, oldData_abertura, data_abertura);
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


        private String CpfOS;

    public static final String PROP_CPFOS = "CpfOS";

    public String getCpfOS() {
        return CpfOS;
    }

    public void setCpfOS(String CpfOS) {
        String oldCpfOS = this.CpfOS;
        this.CpfOS = CpfOS;
        propertyChangeSupport.firePropertyChange(PROP_CPFOS, oldCpfOS, CpfOS);
    }

    
}