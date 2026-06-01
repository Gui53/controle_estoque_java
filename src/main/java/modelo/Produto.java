package modelo;

import enums.TipoUnidade;

/**
 * Representa um produto armazenado no sistema de controle de estoque.
 *
 * Um produto possui informações como nome, preço, unidade de medida,
 * quantidade disponível em estoque, limites mínimo e máximo, além da
 * categoria à qual pertence.
 *
 * @author Gabriel Conci
 * @version 1.0
 * @since 2026
 * @see Categoria
 * @see TipoUnidade
 */
public class Produto {

    /**
     * Identificador único do produto.
     */
    private int id;

    /**
     * Nome do produto.
     */
    private String nome;

    /**
     * Preço unitário do produto.
     */
    private double preco;

    /**
     * Unidade de medida utilizada pelo produto.
     */
    private TipoUnidade unidade;

    /**
     * Quantidade atual disponível em estoque.
     */
    private double quantidade;

    /**
     * Quantidade mínima recomendada em estoque.
     */
    private double minimo;

    /**
     * Quantidade máxima permitida em estoque.
     */
    private double maximo;

    /**
     * Categoria à qual o produto pertence.
     */
    private Categoria categoria;

    /**
     * Constrói um produto com valores padrão.
     */
    public Produto() {
        this("", 0, null, 0, 0, 0, null);
    }

    /**
     * Constrói um produto com todos os seus atributos principais.
     *
     * @param nome nome do produto.
     * @param preco preço unitário do produto.
     * @param unidade unidade de medida utilizada.
     * @param quantidade quantidade inicial em estoque.
     * @param minimo quantidade mínima recomendada.
     * @param maximo quantidade máxima permitida.
     * @param categoria categoria do produto.
     */
    public Produto(String nome, double preco, TipoUnidade unidade,
            double quantidade, double minimo, double maximo,
            Categoria categoria) {

        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.minimo = minimo;
        this.maximo = maximo;
        this.categoria = categoria;
    }

    /**
     * Retorna o identificador do produto.
     *
     * @return identificador do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do produto.
     *
     * @param id novo identificador do produto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a unidade de medida do produto.
     *
     * @return unidade de medida utilizada.
     */
    public TipoUnidade getUnidade() {
        return unidade;
    }

    /**
     * Define a unidade de medida do produto.
     *
     * @param unidade nova unidade de medida.
     */
    public void setUnidade(TipoUnidade unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome novo nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return preço unitário do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco novo preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a quantidade disponível em estoque.
     *
     * @return quantidade atual do produto.
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade disponível em estoque.
     *
     * Apenas valores maiores ou iguais a zero são aceitos.
     *
     * @param quantidade nova quantidade em estoque.
     */
    public void setQuantidade(double quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    /**
     * Retorna a quantidade mínima recomendada em estoque.
     *
     * @return quantidade mínima.
     */
    public double getMinimo() {
        return minimo;
    }

    /**
     * Define a quantidade mínima recomendada em estoque.
     *
     * Apenas valores maiores ou iguais a zero são aceitos.
     *
     * @param minimo nova quantidade mínima.
     */
    public void setMinimo(double minimo) {
        if (minimo >= 0) {
            this.minimo = minimo;
        }
    }

    /**
     * Retorna a quantidade máxima permitida em estoque.
     *
     * @return quantidade máxima.
     */
    public double getMaximo() {
        return maximo;
    }

    /**
     * Define a quantidade máxima permitida em estoque.
     *
     * O valor máximo deve ser maior ou igual ao valor mínimo.
     *
     * @param maximo nova quantidade máxima.
     */
    public void setMaximo(double maximo) {
        if (maximo >= this.minimo) {
            this.maximo = maximo;
        }
    }

    /**
     * Retorna a categoria associada ao produto.
     *
     * @return categoria do produto.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria nova categoria do produto.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Adiciona uma quantidade ao estoque do produto.
     *
     * Apenas valores maiores ou iguais a zero são aceitos.
     *
     * @param qtd quantidade a ser adicionada.
     */
    public void adicionar(double qtd) {
        if (qtd >= 0) {
            this.quantidade += qtd;
        }
    }

    /**
     * Remove uma quantidade do estoque do produto.
     *
     * A remoção somente ocorre se houver saldo suficiente
     * em estoque.
     *
     * @param qtd quantidade a ser removida.
     * @return true se a remoção foi realizada com sucesso;
     * false caso contrário.
     */
    public boolean remover(double qtd) {
        if (qtd > 0 && this.quantidade >= qtd) {
            this.quantidade -= qtd;
            return true;
        }
        return false;
    }
}