package dao;

import java.util.ArrayList;
import model.Movimentacao;
import model.TipoMovimentacao;

public class MovimentacaoDAO {
    
    private ArrayList<Movimentacao> lista = new ArrayList<>();
    
    public void adicionar(Movimentacao m){
        lista.add(m);
    }
    
    public ArrayList<Movimentacao> listar(){
        return lista;
    }
    
    public int totalEntradas(){
        int total = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            Movimentacao m = lista.get(i);
            
            if(m.getTipo() == TipoMovimentacao.ENTRADA){
                total += m.getQuantidade();
            }
        }
        return total;
    }
    
    public int totalSaidas(){
        int total = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            Movimentacao m = lista.get(i);
            
            if(m.getTipo() == TipoMovimentacao.SAIDA){
                total += m.getQuantidade();
            }
        }
        return total;
    }
}
