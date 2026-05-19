package main;

import dao.CategoriaDAO;
import enums.TipoTamanho;
import enums.TipoEmbalagem;
import model.Categoria;

public class Principal {

    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();
        
        Categoria categoria = new Categoria("Cereais", TipoTamanho.PEQUENO, TipoEmbalagem.PLASTICO);
        
        dao.insertCategoria(categoria);
        
    }
}