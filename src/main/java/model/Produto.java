package model;

public class Produto {

    private String nome;
    private double preco;
    private int quantidade;
    private int minimo;
    private int maximo;

    //Aguardando classe Categoria ficar pronta
    /*private Categoria categoria;*/
    public Produto() {
        this("", 0, 0, 0, 0);
    }

    public Produto(String nome, double preco, int quantidade, int minimo, int maximo /* ,Categoria categoria*/) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.minimo = minimo;
        this.maximo = maximo;
        /*this.categoria = categoria;*/
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        }
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        if (minimo >= 0) {
            this.minimo = minimo;
        }
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        if (maximo >= this.minimo) {
            this.maximo = maximo;
        }
    }

    /*public Categoria getCategoria(){
    return categoria;
    }*/
    public void adicionar(int qtd) {
        if (qtd >= 0) {
            this.quantidade += qtd;
        }
    }

    public boolean remover(int qtd) {
        if (qtd > 0 && this.quantidade >= qtd) {
            this.quantidade -= qtd;
            return true;
        }
        return false;
    }
}
