package visao;

import dao.RelatorioDAO;
import java.util.Scanner;

public class RelatorioView {
    
    public void menuRelatorios() {
        
        Scanner sc = new Scanner(System.in);
        
        RelatorioDAO dao = new RelatorioDAO();
        
        int opcao;
        
        do {
            
            System.out.println("\n=== RELATÓRIOS ===");
            
            System.out.println("1 - Produtos abaixo do mínimo");
            
            System.out.println("2 - Produtos acima do máximo");

            System.out.println("3 - Valor total do estoque");
            
            System.out.println("0 - Voltar");
            
            System.out.println("Escolha: ");
            
            opcao = sc.nextInt();
            
            switch(opcao) {
                
                case 1:
                    
                    dao.produtosAbaixoMinimo();
                    break;
                
                case 2:
                    
                    dao.produtosAcimaMaximo();
                    break;
                
                case 3:
                    
                    dao.valorTotalEstoque();
                    break;
                 
                case 0:
                    
                    System.out.println("Voltando...");
                    break;
                    
                default:
                    
                    System.out.println("Opção inválida.");
                    
            }
            
        } while(opcao != 0);
    }
}
