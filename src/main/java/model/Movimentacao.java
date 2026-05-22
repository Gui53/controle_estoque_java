package model;

import enums.TipoMovimentacao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Movimentacao {
    
    private int id;
    private Produto produto;
    private LocalDate data;
    private double quantidade;
    private TipoMovimentacao tipo;

    public Movimentacao() {
        this(0, null, LocalDate.now(), 0, TipoMovimentacao.ENTRADA);
    }
    
    public Movimentacao(int id, Produto produto, LocalDate data, double quantidade, TipoMovimentacao tipo) {
        this.id = id;
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }
    
    public Movimentacao(Produto produto, LocalDate data, double quantidade, TipoMovimentacao tipo) {
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
    
}
