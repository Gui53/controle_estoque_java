package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Movimentacao {
    private Produto produto;
    private LocalDate data;
    private int quantidade;
    private TipoMovimentacao tipo;

    public Movimentacao() {
        this(null, LocalDate.now(), 0, TipoMovimentacao.ENTRADA);
    }
    
    public Movimentacao(Produto produto, LocalDate data, int quantidade, TipoMovimentacao tipo) {
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getDataFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
    
    public void executar(){
        if(produto == null){
            JOptionPane.showMessageDialog(null, "Produto inválido");
            return;
        }
        
        if(quantidade <= 0){
            JOptionPane.showMessageDialog(null, "Quantidade inválida!");
            return;                
        }
        
        if(tipo == TipoMovimentacao.ENTRADA){
            produto.adicionar(quantidade);
        }else{
            if(produto.getQuantidade() < quantidade){
                JOptionPane.showMessageDialog(null, "Estoque insuficiente!");
                return;
            }
            produto.remover(quantidade);
        }
        JOptionPane.showMessageDialog(null, "Movimentação realizada com sucesso!");
        
        if(produto.getQuantidade() < produto.getMinimo()){
            JOptionPane.showMessageDialog(null, "ESTOQUE ABAIXO DO MÍNIMO!");
        }
        
        if(produto.getQuantidade() > produto.getMaximo()){
            JOptionPane.showMessageDialog(null, "ESTOQUE ACIMA DO MÁXIMO!");
        }
        
    }
}
