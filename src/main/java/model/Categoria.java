package model;

import dao.CategoriaDAO;
import enums.TipoEmbalagem;
import enums.TipoTamanho;

public class Categoria {

    private int id;
    private String nome;
    private TipoTamanho tamanho;
    private TipoEmbalagem embalagem;
    private CategoriaDAO dao;

    public Categoria() {
    }

    public Categoria(int id, String nome, TipoTamanho tamanho, TipoEmbalagem embalagem) {
        
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoTamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(TipoTamanho tamanho) {
        this.tamanho = tamanho;
    }

    public TipoEmbalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(TipoEmbalagem embalagem) {
        this.embalagem = embalagem;
    }

    public boolean insertCategoria(String nome, TipoTamanho tamanho, TipoEmbalagem embalagem) {
        int id = this.maiorID() + 1;
        Categoria objeto = new Categoria(id, nome, tamanho, embalagem);
        dao.insertCategoria(objeto);
        return true;

    }

    public int maiorID() {
        return dao.maiorID();
    }
}
