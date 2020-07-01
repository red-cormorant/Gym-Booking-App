package view;

import model.User;
import service.MainService;

import javax.swing.*;
import java.util.Optional;

public class LoginWindow extends JFrame {
    private JPanel loginPanel;
    private JTextField textField1;
    private JLabel labelParola;
    private JLabel labelUsername;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton inregistrareButton;

    public  LoginWindow() {
        add(loginPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(450,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginButton.addActionListener(e -> login());
        inregistrareButton.addActionListener(e -> inregistrare());

    }

    public void inregistrare(){
        String username = textField1.getText();
        String parola = new String(passwordField1.getPassword());
        User user = new User(username, parola, false); //doar clientii se pot inregistra, angajatii sunt inregistrati prin baza de date
        if(MainService.getInstance().inregistrare(user)) {
            JOptionPane.showMessageDialog(null, "Userul a fost inregistrat. Incearca sa te loghezi");
        }
        else {
            JOptionPane.showMessageDialog(null, "Contul acesta este deja existent. Incearca sa te loghezi!");
        }
    }


    public void login(){
        String username = textField1.getText();
        String parola = new String(passwordField1.getPassword());
        Optional<User> user = MainService.getInstance().login(username, parola);
        if(user.isPresent() == true && user.get().getEsteAngajat() == true){
            new MainWindowForAngajat(getUsernameFromTextField());
            dispose();
        }
        else if(user.isPresent() == true) {
            if(MainService.getInstance().verificareClientExistent(username)) {
                dispose();
                new MainWindowForClient(getUsernameFromTextField());

            }
            else {
                dispose();
                new AddClientForm(getUsernameFromTextField());

                //System.out.println(getUsernameFromTextField());
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "User sau parola introduse gresit!");
            passwordField1.setText("");
        }


    }

    public String getUsernameFromTextField() {
        return textField1.getText();

    }






}
