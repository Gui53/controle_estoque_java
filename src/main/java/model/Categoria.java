package model;

import enums.TipoEmbalagem;
import enums.TipoTamanho;

public class Categoria {

    private String nome;
    private TipoTamanho tamanho;
    private TipoEmbalagem embalagem;

    public Categoria() {
    }

    public Categoria(String nome, TipoTamanho tamanho, TipoEmbalagem embalagem) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
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

    

}
