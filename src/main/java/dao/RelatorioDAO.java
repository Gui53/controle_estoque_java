package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.ConnectionFactory;

public class RelatorioDAO {
    
    public void produtosAbaixoMinimo(){
    
      String sql = """
                SELECT nome, quantidade, minimo
                FROM produto
                WHERE quantidade < minimo
            """;
        
        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepapeStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ){
            System.out.println("\n=== PRODUTOS ABAIXO DO MÍNIMO ===");
            
            while(rs.next()) {
                System.out.println(
                    rs.getString("nome")
                    + " | Quantidade: "
                    + rs.getInt("quantidade")
                    + " | Mínimo: "
                    + rs.getInt("minimo")
                );                                   
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
 
    }
    
    public void produtosAcimaMaximo(){
        
        String sql = """
            SELECT nome, quantidade, maximo
            FROM produto
            WHERE quantidade > maximo
        """;
        
        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            
            System.out.println("\n=== PRODUTO ACIMA DO MÁXIMO ===");
            
            while(rs.next()) {
                System.out.println(
                    rs.getString("nome")
                    + " | Quantidade: "
                    + rs.getInt("quantidade")
                    + " | Máximo: "
                    + rs.getInt("maximo")
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    public void valorTotalEstoque() {
        
        String sql = """
            SELECT SUM(preco * quantidade) AS total
            FROM produto
        """;
        
        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            
            if(rs.next()) {
                System.out.println(
                        "\nValor total do estoque: R$ "
                        + rs.getDouble("total")
                );                                             
            }
        } catch(Exception e) {
            e.printStackTrace();                                                                                                
        }

    } 
    
    
}
