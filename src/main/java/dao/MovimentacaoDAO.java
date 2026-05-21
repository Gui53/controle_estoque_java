package dao;

import connection.Conexao;
import java.util.ArrayList;
import model.Movimentacao;
import enums.TipoMovimentacao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDAO {
    
    private ArrayList<Movimentacao> lista = new ArrayList<>();
    
    public boolean insert(Movimentacao m){
        String sql = "INSERT INTO tb_movimentacao(data_movimentacao, quantidade_movimentada, tipo_movimentacao, tb_produto_id) VALUES(?,?,?,?) ";
        
        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(m.getData()));
            stmt.setInt(2, m.getQuantidade());
            stmt.setString(3, m.getTipo().name());
            stmt.setInt(4, m.getProduto().getId());
            
            stmt.execute();
            stmt.close();
            
            System.out.println("MOVIMENTAÇÃO CADASTRADA!");
            
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            throw new RuntimeException(e);
        }
    
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
