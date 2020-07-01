package view;

import model.Eveniment;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class InvitatieEveniment extends JFrame {
    private String username;
    private DefaultTableModel modelEveniment;
    private JTable tabelEveniment;
    private JButton stergeParticipareaMeaButton;
    private JButton backToMainMenuButton;
    private JPanel panel;


    public InvitatieEveniment(String username){
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,550);
        this.username = username;

        this.stergeParticipareaMeaButton.addActionListener(e -> stergereParticipare());
        this.backToMainMenuButton.addActionListener(e -> backToMainMenu());

        setModelEveniment();
        this.tabelEveniment.setModel(modelEveniment);
        this.tabelEveniment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void stergereParticipare(){
        int rowEveniment = tabelEveniment.getSelectedRow();

        Locale locale = new Locale("ro", "RO");
        JOptionPane.setDefaultLocale(locale);
        int option = JOptionPane.showConfirmDialog(null, "Doriti sa stergeti invitatia la evenimentul selectat?", "Sterge invitatie", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION) {
            Eveniment eveniment = new Eveniment(
                    Integer.parseInt(modelEveniment.getValueAt(rowEveniment, 0).toString()),
                    modelEveniment.getValueAt(rowEveniment, 1).toString(),
                    LocalTime.parse(modelEveniment.getValueAt(rowEveniment, 2).toString()),
                    LocalDate.parse(modelEveniment.getValueAt(rowEveniment, 3).toString())
            );
            MainService.getInstance().stergeInvitatie(username, eveniment);
            setModelEveniment();
            this.tabelEveniment.setModel(modelEveniment);
        }
    }

    public void backToMainMenu(){
        new MainWindowForClient(username);
        dispose();
    }

    public void setModelEveniment(){
        modelEveniment = new DefaultTableModel(new String[]{"Numar maxim de clienti", "Notes", "Ora", "Zi"}, 0);
        List<Eveniment> list = MainService.getInstance().getClientEveniment(username);
        for(int i = 0; i < list.size(); i++) {

            modelEveniment.addRow(new Object[]{
                    list.get(i).getNrMaximParticipanti(),
                    list.get(i).getNotes(),
                    list.get(i).getOraEveniment(),
                    list.get(i).getZiEveniment()
            });
        }
    }
}
