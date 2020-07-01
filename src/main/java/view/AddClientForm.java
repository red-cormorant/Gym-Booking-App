package view;

import model.Client;
import service.MainService;

import javax.swing.*;

public class AddClientForm extends JFrame {
    private JPanel addClientPanel;
    private JButton saveButton;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField telephoneField;
    private String username;

    public AddClientForm(String username) {
        add(addClientPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.username = username;
        System.out.println(username);

        saveButton.addActionListener(e -> insert());

    }

    public void insert(){
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();

        Client client = new Client(lastName, firstName, telephone, email);
        MainService.getInstance().addClient(username, client);
        dispose();
        new MainWindowForClient(username);



    }
}
