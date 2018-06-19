package cbk.dados;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class itemPedidoPecaDados {
    
    
    public String toString(){
        String s = "Codigo: "+getCodigo()+" Descrição: "+getDescricao()+" Quantidade: "+getQuantidade()+"\n";
        return s;
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

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

            private String id_peca;

    public static final String PROP_ID_PECA = "id_peca";

    public String getId_peca() {
        return id_peca;
    }

    public void setId_peca(String id_peca) {
        String oldId_peca = this.id_peca;
        this.id_peca = id_peca;
        propertyChangeSupport.firePropertyChange(PROP_ID_PECA, oldId_peca, id_peca);
    }

        private String codigo;

    public static final String PROP_CODIGO = "codigo";

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        propertyChangeSupport.firePropertyChange(PROP_CODIGO, oldCodigo, codigo);
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

        private String numero_pedido;

    public static final String PROP_NUMERO_PEDIDO = "numero_pedido";

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        String oldNumero_pedido = this.numero_pedido;
        this.numero_pedido = numero_pedido;
        propertyChangeSupport.firePropertyChange(PROP_NUMERO_PEDIDO, oldNumero_pedido, numero_pedido);
    }

    

           public int quantidade;

    public static final String PROP_QUANTIDADE = "quantidade";

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        int oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        propertyChangeSupport.firePropertyChange(PROP_QUANTIDADE, oldQuantidade, quantidade);
    }


    
        private String descricao;

    public static final String PROP_DESCRICAO = "descricao";

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        propertyChangeSupport.firePropertyChange(PROP_DESCRICAO, oldDescricao, descricao);
    }

        private String quantidade_texto;

    public static final String PROP_QUANTIDADE_TEXTO = "quantidade_texto";

    public String getQuantidade_texto() {
        return quantidade_texto;
    }

    public void setQuantidade_texto(String quantidade_texto) {
        String oldQuantidade_texto = this.quantidade_texto;
        this.quantidade_texto = quantidade_texto;
        propertyChangeSupport.firePropertyChange(PROP_QUANTIDADE_TEXTO, oldQuantidade_texto, quantidade_texto);
    }

    
}
