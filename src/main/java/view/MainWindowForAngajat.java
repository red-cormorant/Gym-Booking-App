package view;

import javax.swing.*;
import java.awt.*;

public class MainWindowForAngajat extends JFrame{
    private JPanel panel1;
    private JLabel welcome;
    private JButton adaugaUnEvenimentButton;
    private JButton modificaDateleContuluiButton;
    private JButton backToLoginButton;
    private String username;

    MainWindowForAngajat(String username) {
        add(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        this.username = username;
        setSize(600,600);
        welcome.setText("Bine ai venit la Gold Gym, " + username + "!");
        welcome.setFont(new Font("Serif", Font.BOLD, 20));
        adaugaUnEvenimentButton.addActionListener(e -> openEveniment());
        backToLoginButton.addActionListener(e -> backToLogin());
        modificaDateleContuluiButton.addActionListener(e -> modificaDate());
    }

    public void backToLogin(){
        new LoginWindow();
        dispose();
    }

    public void openEveniment(){
        new EvenimentWindow(username);
        dispose();
    }

    public void modificaDate(){
        new ClientAngajatWindow(username);
        dispose();
    }
}
