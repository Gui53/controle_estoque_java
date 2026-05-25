package main;

import view.LoginView;

public class Principal {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginView().setVisible(true));
    }
}
