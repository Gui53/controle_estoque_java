package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;
import java.util.ArrayList;
import modelo.Produto;

public class RelatorioDAO {
    
    public ArrayList<Produto> produtosAbaixoMinimo() {

        ArrayList<Produto> lista = new ArrayList<>();

        String sql = """
            SELECT *
            FROM produto
            WHERE quantidade < minimo
        """;

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {

                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getDouble("quantidade"));
                p.setMinimo(rs.getDouble("minimo"));

                lista.add(p);
            }

        } catch(Exception e) {
            e.printStackTrace();
    }

    return lista;
}
    
    public ArrayList<Produto> produtosAcimaMaximo(){
        
        ArrayList<Produto> lista = new ArrayList<>();
        
        String sql = """
            SELECT *
            FROM produto
            WHERE quantidade > maximo
        """;
        
        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
                                    
            while(rs.next()) {
                
                Produto p = new Produto();
                
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getDouble("quantidade"));
                p.setMaximo(rs.getDouble("maximo"));
                
                lista.add(p);      
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public double valorTotalEstoque() {
        
        String sql = """
            SELECT SUM(preco * quantidade) AS total
            FROM produto
        """;
        
        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            
            if(rs.next()) {
                return rs.getDouble("total");                                             
            }
            
        } catch(Exception e) {
            e.printStackTrace();                                                                                                
        }
        
        return 0;
    }     
}
