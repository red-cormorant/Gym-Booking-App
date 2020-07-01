package view;

import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Locale;

public class MainWindowForClient extends JFrame{
    private JPanel panelMainClient;
    private JLabel welcome;
    private JButton contulMeuButton;
    private JButton rezervareButton;
    private JButton backToLoginButton;
    private JButton stergeContUtilizatorButton;
    private JButton evenimenteButton;
    private String username;

    public MainWindowForClient(String username) {
        add(panelMainClient);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        //verificaCompletareDateCont();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,550);
        this.username = username;

        this.welcome.setText("Bine ai venit la Gold Gym, " + username + "!");
        this.welcome.setFont(new Font("Serif", Font.BOLD, 20));

        this.contulMeuButton.addActionListener(e -> deschideContulMeu());
        this.rezervareButton.addActionListener(e -> deschideRezervare());
        this.backToLoginButton.addActionListener(e -> backToLogin());
        this.stergeContUtilizatorButton.addActionListener(e -> stergeUtilizator());
        this.evenimenteButton.addActionListener(e -> viewEvenimente());

        getNotificare();
    }

    public void deschideContulMeu() {
        new ClientAngajatWindow(username);
        dispose();
    }

    public void deschideRezervare(){
        new RezervareWindow(username);
        dispose();
    }

    public void backToLogin(){
        new LoginWindow();
        dispose();
    }

    public void stergeUtilizator(){
        Locale locale = new Locale("ro", "RO");
        JOptionPane.setDefaultLocale(locale);
        int option = JOptionPane.showConfirmDialog(null, "Doriti sa stergeti contul de utilizator?", "Sterge user", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            new LoginWindow();
            dispose();
            MainService.getInstance().deleteUser(username);
        }
    }

    public void viewEvenimente(){
        new InvitatieEveniment(username);
        dispose();
    }

    //primesc notificare cu 30 zile inainte de eveniment
    public void getNotificare(){
        LocalDateTime fromLocalDateTime = LocalDateTime.now();
        LocalDateTime toLocalDateTime;
        LocalDateTime tempLocalDateTime;
        long days;
        long hours;
        long minutes;
        List<LocalDateTime> list = MainService.getInstance().getNotificare(username);
        for(int i = 0; i < list.size(); i++){
            toLocalDateTime = list.get(i);
            tempLocalDateTime = LocalDateTime.from(fromLocalDateTime);
            days = tempLocalDateTime.until(toLocalDateTime, ChronoUnit.DAYS);
            hours = tempLocalDateTime.until(toLocalDateTime, ChronoUnit.HOURS);
            minutes = tempLocalDateTime.until(toLocalDateTime, ChronoUnit.MINUTES);
            if(days > 1 && days <= 30)
                JOptionPane.showMessageDialog(null, "Evenimentul desfasurat" + list.get(i).toString() + " incepe in " + days + " zile ");
            else if(days == 1)
                JOptionPane.showMessageDialog(null, "Evenimentul desfasurat" + list.get(i).toString() + " incepe in " + days + " zile si " + hours + " ore ");
            else if(days == 0 && hours > 0)
                JOptionPane.showMessageDialog(null, "Evenimentul desfasurat" + list.get(i).toString() + " incepe in " + hours + " ore ");
            else if(hours <= 8 && hours > 0)
                JOptionPane.showMessageDialog(null, "Evenimentul desfasurat" + list.get(i).toString() + " incepe in " + hours + " ore si " + minutes + " minute ");
            else if(hours == 0)
                JOptionPane.showMessageDialog(null, "Evenimentul desfasurat" + list.get(i).toString() + " incepe in " + minutes + " minute ");
        }
    }







}
