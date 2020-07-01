package view;

import main.Main;
import model.Angajat;
import model.Client;
import service.MainService;

import javax.swing.*;
import java.awt.*;

public class ClientAngajatWindow extends JFrame {
    private JPanel clientWindow;
    private JButton saveButton;
    private JButton backButton;
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField emailField;
    private JTextField telefonField;
    private String username;
    private JLabel title;

    public ClientAngajatWindow(String username){
        add(clientWindow);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        //verificaCompletareDateCont();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,400);
        title.setText("Informatii personale pentru clientul " + username);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        this.username = username;
        setFields();
        saveButton.addActionListener(e -> save());
        backButton.addActionListener(e -> backToMainMenu());
    }

    public void setFields(){
        String [] results;
        if(MainService.getInstance().esteAngajat(username))
            results = MainService.getInstance().getFieldsFromAngajat(username);
        else
            results = MainService.getInstance().getFieldsFromClient(username);

        numeField.setText(results[0]);
        prenumeField.setText(results[1]);
        telefonField.setText(results[2]);
        emailField.setText(results[3]);
    }

    public void save(){
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        String telefon = telefonField.getText();
        String email = emailField.getText();

        if(MainService.getInstance().esteAngajat(username)) {
            Angajat angajat = new Angajat(nume, prenume, telefon, email);
            MainService.getInstance().updateAngajat(username, angajat);
            dispose();
            new ClientAngajatWindow(username);
        }
        else{
            Client client = new Client(nume, prenume, telefon, email);
            MainService.getInstance().updateClient(username, client);
            dispose();
            new ClientAngajatWindow(username);
        }
    }

    public void backToMainMenu() {
        dispose();
        if(MainService.getInstance().esteAngajat(username))
            new MainWindowForAngajat(username);
        else
            new MainWindowForClient(username);
    }



}
