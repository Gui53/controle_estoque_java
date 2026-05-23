
package main;

import view.PrincipalView;

public class Principal {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new PrincipalView().setVisible(true));
    }
}
