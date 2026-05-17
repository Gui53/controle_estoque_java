package dao;

import java.util.ArrayList;
import model.Categoria;

public class CategoriaDAO {

    private ArrayList<Categoria> lista = new ArrayList<>();

    public void adicionar(Categoria categoria) {
        lista.add(categoria);
    }

    public ArrayList<Categoria> listar() {
        return lista;
    }

    public Categoria buscarPorNome(String nome) {
        for (Categoria c : lista) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizar(String nome, Categoria novaCategoria) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                lista.set(i, novaCategoria);
                return true;
            }
        }
        return false;
    }

    public boolean remover(String nome) {
        Categoria c = buscarPorNome(nome);

        if (c != null) {
            lista.remove(c);
            return true;
        }

        return false;
    }
}
