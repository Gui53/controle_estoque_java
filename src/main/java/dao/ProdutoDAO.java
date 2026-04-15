package dao;

import java.util.ArrayList;
import model.Produto;

public class ProdutoDAO {

    private ArrayList<Produto> lista = new ArrayList<>();

    public void adicionar(Produto produto) {
        lista.add(produto);
    }

    public ArrayList<Produto> listar() {
        return lista;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : lista) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizar(String nome, Produto novoProduto) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                lista.set(i, novoProduto);
                return true;
            }
        }
        return false;
    }

    public boolean remover(String nome) {
        Produto p = buscarPorNome(nome);
        if (p != null) {
            lista.remove(p);
            return true;
        }
        return false;
    }
}
