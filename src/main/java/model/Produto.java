package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Produto {
    private String nome;
    private double preco;
    private int quantidade;
    private int minimo;
    private int maximo;
    /*private Categoria categoria;*/

    public Produto() {
        this("",0,0,0,0);
    }
    
    
    
    public Produto(String nome, double preco, int quantidade, int minimo, int maximo /* ,Categoria categoria*/){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.minimo = minimo;
        this.maximo = maximo;
        /*this.categoria = categoria;*/
    }
    
    public String getNome(){
        return nome;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
    
    public void adicionar(int qtd){
        this.quantidade += qtd;
    }
    
    public void remover(int qtd){
        this.quantidade -= qtd;
    }
}
