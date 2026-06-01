package modelo;

import dao.CategoriaDAO;
import enums.TipoEmbalagem;
import enums.TipoTamanho;

/**
 * Representa uma categoria de produtos do sistema.
 *
 * Uma categoria é utilizada para agrupar produtos com características
 * semelhantes, definindo informações como nome, tamanho e tipo de
 * embalagem.
 *
 * @author Gabriel Conci
 * @version 1.0
 * @since 2026
 * @see TipoTamanho
 * @see TipoEmbalagem
 * @see CategoriaDAO
 */
public class Categoria {

    /**
     * Identificador único da categoria.
     */
    private int id;

    /**
     * Nome da categoria.
     */
    private String nome;

    /**
     * Tipo de tamanho associado à categoria.
     */
    private TipoTamanho tamanho;

    /**
     * Tipo de embalagem associado à categoria.
     */
    private TipoEmbalagem embalagem;

    /**
     * Objeto responsável pelas operações de persistência da categoria.
     */
    private CategoriaDAO dao;

    /**
     * Constrói uma categoria sem inicializar seus atributos.
     */
    public Categoria() {
    }

    /**
     * Constrói uma categoria com todos os seus atributos principais.
     *
     * @param id identificador da categoria.
     * @param nome nome da categoria.
     * @param tamanho tipo de tamanho da categoria.
     * @param embalagem tipo de embalagem da categoria.
     */
    public Categoria(int id, String nome, TipoTamanho tamanho, TipoEmbalagem embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    /**
     * Retorna o identificador da categoria.
     *
     * @return identificador da categoria.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da categoria.
     *
     * @param id novo identificador da categoria.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da categoria.
     *
     * @return nome da categoria.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nome novo nome da categoria.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o tipo de tamanho da categoria.
     *
     * @return tipo de tamanho associado à categoria.
     */
    public TipoTamanho getTamanho() {
        return tamanho;
    }

    /**
     * Define o tipo de tamanho da categoria.
     *
     * @param tamanho novo tipo de tamanho da categoria.
     */
    public void setTamanho(TipoTamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo de embalagem da categoria.
     *
     * @return tipo de embalagem associado à categoria.
     */
    public TipoEmbalagem getEmbalagem() {
        return embalagem;
    }

    /**
     * Define o tipo de embalagem da categoria.
     *
     * @param embalagem novo tipo de embalagem da categoria.
     */
    public void setEmbalagem(TipoEmbalagem embalagem) {
        this.embalagem = embalagem;
    }

}