package modelo;

import enums.TipoMovimentacao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por representar uma movimentação de estoque.
 * Uma movimentação pode ser de entrada ou saída de produtos.
 * 
 * @author Guilherme
 */
public class Movimentacao {

    private int id;
    private Produto produto;
    private LocalDate data;
    private double quantidade;
    private TipoMovimentacao tipo;

    /**
     * Construtor padrão da movimentação.
     */
    public Movimentacao() {
        this(0, null, LocalDate.now(), 0, TipoMovimentacao.ENTRADA);
    }

    /**
     * Construtor completo da movimentação.
     * 
     * @param id Identificador da movimentação
     * @param produto Produto movimentado
     * @param data Data da movimentação
     * @param quantidade Quantidade movimentada
     * @param tipo Tipo da movimentação
     */
    public Movimentacao(int id, Produto produto, LocalDate data, double quantidade, TipoMovimentacao tipo) {
        this.id = id;
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    /**
     * Construtor sem o identificador.
     * 
     * @param produto Produto movimentado
     * @param data Data da movimentação
     * @param quantidade Quantidade movimentada
     * @param tipo Tipo da movimentação
     */
    public Movimentacao(Produto produto, LocalDate data, double quantidade, TipoMovimentacao tipo) {
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    /**
     * Recupera o ID da movimentação.
     * 
     * @return int ID da movimentação
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica o ID da movimentação.
     * 
     * @param id ID da movimentação
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Recupera a data da movimentação.
     * 
     * @return LocalDate Data da movimentação
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Modifica a data da movimentação.
     * 
     * @param data Data da movimentação
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Recupera o produto movimentado.
     * 
     * @return Produto Produto movimentado
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Modifica o produto movimentado.
     * 
     * @param produto Produto movimentado
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Recupera a data formatada da movimentação.
     * 
     * @return String Data formatada no padrão dd/MM/yyyy
     */
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    /**
     * Recupera a quantidade movimentada.
     * 
     * @return double Quantidade movimentada
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     * Modifica a quantidade movimentada.
     * 
     * @param quantidade Quantidade movimentada
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Recupera o tipo da movimentação.
     * 
     * @return TipoMovimentacao Tipo da movimentação
     */
    public TipoMovimentacao getTipo() {
        return tipo;
    }

    /**
     * Modifica o tipo da movimentação.
     * 
     * @param tipo Tipo da movimentação
     */
    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

}