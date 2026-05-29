package modelo;

import enums.TipoUnidade;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private TipoUnidade unidade;
    private double quantidade;
    private double minimo;
    private double maximo;

    private Categoria categoria;

    public Produto() {
        this("", 0, null, 0, 0, 0, null);
    }

    public Produto(String nome, double preco, TipoUnidade unidade, double quantidade, double minimo, double maximo, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.minimo = minimo;
        this.maximo = maximo;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoUnidade getUnidade() {
        return unidade;
    }

    public void setUnidade(TipoUnidade unidade) {
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        if (minimo >= 0) {
            this.minimo = minimo;
        }
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        if (maximo >= this.minimo) {
            this.maximo = maximo;
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void adicionar(double qtd) {
        if (qtd >= 0) {
            this.quantidade += qtd;
        }
    }

    public boolean remover(double qtd) {
        if (qtd > 0 && this.quantidade >= qtd) {
            this.quantidade -= qtd;
            return true;
        }
        return false;
    }
}
