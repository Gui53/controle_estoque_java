package principal;

import visao.LoginView;

/**
 * Classe principal do sistema.
 * 
 * Responsável por iniciar a aplicação
 * e abrir a tela de login.
 * 
 * @author Guilherme
 */
public class Principal {

    /**
     * Método principal responsável pela execução do sistema.
     * 
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginView().setVisible(true));
    }
}