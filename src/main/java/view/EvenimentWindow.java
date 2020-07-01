package view;

import model.Client;
import model.Eveniment;
import service.MainService;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Locale;

public class EvenimentWindow extends JFrame{
    private String username;
    private LocalDate today;
    private JPanel evenimentPanel;
    private DefaultTableModel modelClienti;
    private JTable tabelClienti;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxDay;
    private JSpinner numarParticipanti;
    private JTextArea notes;
    private JButton adaugaEvenimentButton;
    private DefaultTableModel modelEvenimente;
    private JTable tabelEvenimente;
    private JButton adaugaClientiLaEvenimentButton;
    private JLabel myEvents;
    private JComboBox comboBoxOraDorita;
    private JButton modificaNotiteButton;
    private JButton stergeEvenimentButton;
    private JButton backToMainMenuButton;
    private int nrClientiInvitati;


    public EvenimentWindow(String username) {
        this.username = username;
        this.nrClientiInvitati = 0;
        add(evenimentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        this.username = username;
        setSize(900,600);
        this.myEvents.setText("Evenimentele mele: ");
        this.myEvents.setFont(new Font("Serif", Font.BOLD, 20));
        this.today = LocalDate.now();
        setComboBoxMonth();
        setComboBoxDay();
        setComboBoxOraDorita();
        setModelClienti();
        tabelClienti.setModel(modelClienti);
        setModelEvenimente();
        tabelEvenimente.setModel(modelEvenimente);
        this.tabelEvenimente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tabelClienti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        this.adaugaEvenimentButton.addActionListener(e -> adaugaEveniment());
        this.backToMainMenuButton.addActionListener(e -> backToMainMenu());
        this.stergeEvenimentButton.addActionListener(e -> stergeEveniment());
        this.adaugaClientiLaEvenimentButton.addActionListener(e -> adaugaClientiLaEveniment());
        //nu merge :((
        this.modificaNotiteButton.addActionListener(e -> updateNotite());


        comboBoxMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Va rugam sa schimbati si ziua!");
                setComboBoxOraDorita();
            }
        });

        comboBoxDay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setComboBoxOraDorita();
            }
        });




        /* nu functioneaza

        tabelEvenimente.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                setModelEvenimente();
                tabelEvenimente.setModel(modelEvenimente);
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                setModelEvenimente();
                tabelEvenimente.setModel(modelEvenimente);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }

        });

         */


    }

    public void setComboBoxMonth(){
        for(int i = -2; i <= 4; i++) {
            //comboBoxMonth.addItem(Month.of(today.getMonthValue() + i).toString() +  " " + String.valueOf(today.getYear()));
            Month month = today.plusMonths(i).getMonth();
            int year = today.plusMonths(i).getYear();
            comboBoxMonth.addItem(month.toString() +  " " + String.valueOf(year));
        }

        comboBoxMonth.setSelectedItem(today.getMonth().toString() + " " + String.valueOf(today.getYear()));
    }

    public void setComboBoxDay(){
        int length = today.getMonth().length(today.isLeapYear());
        for(int i = 1; i <= length; i++)
            comboBoxDay.addItem(i);

        comboBoxDay.setSelectedItem(today.getDayOfMonth());
    }

    public void setComboBoxOraDorita(){
        LocalDate localDate = dateFormat();
        DefaultComboBoxModel<LocalTime> model = new DefaultComboBoxModel<>();
        if(localDate.getDayOfWeek() != DayOfWeek.SUNDAY && localDate.getDayOfWeek() != DayOfWeek.SATURDAY) {
            for (int i = 9; i <= 23; i++)
                model.addElement(LocalTime.of(i, 0));
        }
        else if(localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            for (int i = 10; i <= 18; i++)
                model.addElement(LocalTime.of(i, 0));
        }
        else if(localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            for (int i = 10; i <= 16; i++)
                model.addElement(LocalTime.of(i, 0));
        }

        comboBoxOraDorita.setModel(model);
        comboBoxOraDorita.setSelectedItem(model.getElementAt(0));

    }

    public void setModelClienti(){
        modelClienti = new DefaultTableModel(new String[]{"idClient", "Nume", "Prenume", "Telefon"}, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        List<Client> clients = MainService.getInstance().getClients();
        for(int i = 0; i < clients.size(); i++){
            modelClienti.addRow(new Object[]{
                    clients.get(i).getIdClient(),
                    clients.get(i).getNumeClient(),
                    clients.get(i).getPrenumeClient(),
                    clients.get(i).getTelefon()
            });
        }
    }

    public void setModelEvenimente(){
        modelEvenimente = new DefaultTableModel(new String[]{"Numar maxim clienti", "Notes", "Ora", "Zi","Clienti invitati"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }


        };
        List<Eveniment> evenimente = MainService.getInstance().getEvenimente(username);
        for(int i = 0; i < evenimente.size(); i++) {
            modelEvenimente.addRow(new Object[]{
                    evenimente.get(i).getNrMaximParticipanti(),
                    evenimente.get(i).getNotes(),
                    evenimente.get(i).getOraEveniment(),
                    evenimente.get(i).getZiEveniment(),
                    MainService.getInstance().verificaNumarClienti(username, evenimente.get(i))
            });
        }

    }

    public LocalDate dateFormat(){
        String anSiLuna = comboBoxMonth.getSelectedItem().toString();

        String[] splited = anSiLuna.split("\\s+");
        Month luna = Month.valueOf(splited[0]);
        int year = Integer.parseInt(splited[1]);
        int zi = Integer.parseInt(comboBoxDay.getSelectedItem().toString());

        return LocalDate.of(year, luna, zi);
    }

    public LocalTime timeFormat(){
        return LocalTime.parse(comboBoxOraDorita.getSelectedItem().toString());
    }

    public void adaugaEveniment(){
        LocalDate localDate = dateFormat();
        LocalTime localTime = timeFormat();

        MainService.getInstance().addEveniment(username, new Eveniment(
                MainService.getInstance().getIdAngajat(username),
                Integer.parseInt(numarParticipanti.getValue().toString()),
                notes.getText(),
                localTime,
                localDate
        ));

        setModelEvenimente();
        this.tabelEvenimente.setModel(modelEvenimente);
    }

    public void backToMainMenu(){
        new MainWindowForAngajat(username);
        dispose();
    }

    public void stergeEveniment(){
        Locale locale = new Locale("ro", "RO");
        JOptionPane.setDefaultLocale(locale);
        int option = JOptionPane.showConfirmDialog(null, "Doriti sa stergeti evenimentul selectat?", "Sterge eveniment", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION) {
           int row = tabelEvenimente.getSelectedRow();
           Eveniment eveniment = new Eveniment(
                   Integer.parseInt(modelEvenimente.getValueAt(row, 0).toString()),
                   modelEvenimente.getValueAt(row, 1).toString(),
                   LocalTime.parse(modelEvenimente.getValueAt(row, 2).toString()),
                   LocalDate.parse(modelEvenimente.getValueAt(row, 3).toString())

           );
           MainService.getInstance().stergeEveniment(username, eveniment);
           setModelEvenimente();
           tabelEvenimente.setModel(modelEvenimente);
           JOptionPane.showMessageDialog(null, "Stergerea evenimentului a fost realizata cu succes!");
        }

    }

    public void adaugaClientiLaEveniment() {
        int rowClienti = tabelClienti.getSelectedRow();
        int idClient = (Integer) modelClienti.getValueAt(rowClienti, 0);
        int rowEveniment = tabelEvenimente.getSelectedRow();
        Eveniment eveniment = new Eveniment(
                    Integer.parseInt(modelEvenimente.getValueAt(rowEveniment, 0).toString()),
                    modelEvenimente.getValueAt(rowEveniment, 1).toString(),
                    LocalTime.parse(modelEvenimente.getValueAt(rowEveniment, 2).toString()),
                    LocalDate.parse(modelEvenimente.getValueAt(rowEveniment, 3).toString())
        );

        int numarInvitati = (Integer) modelEvenimente.getValueAt(rowEveniment, 4);

        if(eveniment.getNrMaximParticipanti() > MainService.getInstance().verificaNumarClienti(username, eveniment)) {
            MainService.getInstance().adaugaClientLaEveniment(username, eveniment, idClient);
            numarInvitati++;
            JOptionPane.showMessageDialog(null, "Clientul a fost invitat cu succes");
            tabelEvenimente.setValueAt(numarInvitati, rowEveniment, 4);

        }
        else {
            JOptionPane.showMessageDialog(null, "Numarul maxim de clienti invitati a fost depasit! ");
        }

    }

    public void updateNotite(){
        int row = tabelEvenimente.getSelectedRow();
        Eveniment eveniment = new Eveniment(
                Integer.parseInt(modelEvenimente.getValueAt(row, 0).toString()),
                modelEvenimente.getValueAt(row, 1).toString(),
                LocalTime.parse(modelEvenimente.getValueAt(row, 2).toString()),
                LocalDate.parse(modelEvenimente.getValueAt(row, 3).toString())
        );

        modelEvenimente.fireTableCellUpdated(row, 1);
        String notes = modelEvenimente.getValueAt(row, 1).toString();
        MainService.getInstance().updateNotes(notes, username, eveniment);

    }



}
