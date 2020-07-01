package view;

import model.Rezervare;
import service.MainService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

import java.util.*;
import java.util.List;

public class RezervareWindow extends JFrame{
    private JPanel rezervarePanel;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxDay;
    private JList listHours;
    private DefaultListModel<LocalTime> modelHours;
    private JList listRezervare;
    private DefaultListModel<String> modelRezervare;
    private JComboBox comboBoxOraDorita;
    private JButton rezervaButton;
    private JButton modificaButton;
    private JButton stergeButton;
    private JButton backToMainMenuButton;
    private String username;
    private LocalDate today;

    /**
     *  am incercat sa folosesc JTable cu doua coloane in loc de 2 JList dar nu am reusit
     *  UPDATE: am reusit sa fac 2 tabele pentru AddEvenimentWindow :)))
     */

    public RezervareWindow(String username)  {
        add(rezervarePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        this.today = LocalDate.now();
        this.username = username;
        setSize(750,600);
        setComboBoxMonth();
        setComboBoxDay();
        updateModels();
        setOraDorita();

        comboBoxMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Va rugam sa schimbati si ziua pentru o afisare corecta a situatiei!");
                updateModels();
                setOraDorita();
            }
        });

        comboBoxDay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateModels();
                setOraDorita();
            }
        });

        this.rezervaButton.addActionListener(e -> adaugaRezervare());
        this.modificaButton.addActionListener(e -> modificaRezervare());
        this.stergeButton.addActionListener(e -> stergeRezervare());
        this.backToMainMenuButton.addActionListener(e -> backToMainMenu());

        //this.rezervaButton.setToolTipText("Selectati o ora disponibila din lista si alegeti ora pentru o noua rezervare");
    }

    public void updateModels() {
        setModelHours();
        this.listHours.setModel(modelHours);
        setModelRezervare();
        this.listRezervare.setModel(modelRezervare);
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

    /*
    public DefaultTableModel buildTableModel()
    {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Ora");
        columnNames.add("Disponibilitate");
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        int i,j;
        //String[] columnNames = {"First Name", "Last Name"};
        //Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
        Vector<Object> vector = new Vector<>();
        for(i = 0; i < 15; i++) {
            vector.add("Indisponibil");
            vector.add("Indisponibil");
            data.add(vector);
        }

        /*
        for (i = 0; i < 15; i++) {
            if(!dataFormat().equals("2000-01-01")) {
                vector.add("" + (i + 9) + ":00");
                vector.add("Liber");
                data.add(vector);
            }

        }

        // model = new TableModel(data, columnNames);
        //this.tabelRezervari = new JTable(data, columnNames);
        //this.tabelRezervari.setFillsViewportHeight(true);

        return new DefaultTableModel(data, columnNames);
    }
    */

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

    public void setModelHours(){
        LocalDate date = dateFormat();
        this.modelHours = new DefaultListModel<>();
        int i;
        List<LocalTime> hours = new ArrayList<>();
        if(date.getDayOfWeek() != DayOfWeek.SUNDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY){
            for(i = 9; i <= 23; i++)
                hours.add(LocalTime.of(i,0));
        }
        else if(date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            for(i = 10; i <= 18; i++)
                hours.add(LocalTime.of(i,0));
        }
        else if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            for(i = 10; i <= 16; i++)
                hours.add(LocalTime.of(i,0));
        }

        modelHours.clear();
        hours.forEach(modelHours::addElement);

    }

    public void setModelRezervare() {
        LocalDate localDate = dateFormat();
        int i,j=0;
        List<LocalTime> hoursModel = new ArrayList<>();
        for(i = 0; i < modelHours.size(); i++)
            hoursModel.add(modelHours.getElementAt(i));
        List<LocalTime> hoursSql = MainService.getInstance().getOraRezervare(localDate);
        int index = hoursSql.size();
        this.modelRezervare = new DefaultListModel<>();

        System.out.println(hoursSql);
        System.out.println(hoursSql.size());
        System.out.println(hoursModel.size());

        List<String> rezervare = new ArrayList<>();
        for(i = 0; i < hoursModel.size(); i++)
            rezervare.add("Liber");


        if(hoursSql.size() >= 1){
            for(j = 0; j < hoursSql.size(); j++)
                for(i = 0; i < hoursModel.size(); i++) {
                    if(hoursModel.get(i) == hoursSql.get(j) && MainService.getInstance().getRezervare(hoursSql.get(j),localDate) == MainService.getInstance().getIdClient(username))
                        rezervare.set(i,"Rezervarea mea");
                    else if(hoursModel.get(i) == hoursSql.get(j))
                        rezervare.set(i, "Rezervare alt client");

                }
        }

        modelRezervare.clear();
        rezervare.forEach(modelRezervare::addElement);

    }

    public void setOraDorita(){
        comboBoxOraDorita.setModel(new DefaultComboBoxModel(modelHours.toArray()));
        comboBoxOraDorita.setSelectedItem(comboBoxOraDorita.getItemAt(0));
    }


    public void adaugaRezervare(){
        LocalDate localDate = dateFormat();
        LocalTime localTime = timeFormat();
        int idClient = MainService.getInstance().getIdClient(username);
        for(int i = 0; i < modelHours.size(); i++){
            if(modelHours.get(i) == localTime && modelRezervare.get(i).equals("Liber")) {
                Rezervare rezervare = new Rezervare();
                rezervare.setIdClient(idClient);
                rezervare.setOraRezervare(localTime);
                rezervare.setZiRezervare(localDate);
                MainService.getInstance().addRezervare(username, rezervare);
                updateModels();
                JOptionPane.showMessageDialog(null, "Rezervarea a fost adaugata cu succes!");
            }
            else if(modelHours.get(i) == localTime && modelRezervare.get(i).equals("Rezervare alt client")){
                JOptionPane.showMessageDialog(null, "NU puteti adauga aceasta rezervare!");
            }
        }

    }

    public void modificaRezervare(){
        LocalDate localDate = dateFormat();
        LocalTime localTime = timeFormat();
        LocalTime oraSelectata = LocalTime.parse(listHours.getSelectedValue().toString());
        int i,idClient;

        for(i = 0; i < modelHours.size(); i++){
            if(modelHours.get(i) == localTime && modelRezervare.get(i).equals("Liber")) {
                idClient = MainService.getInstance().getIdClient(username);
                Rezervare rezervare = new Rezervare();
                rezervare.setIdClient(idClient);
                rezervare.setOraRezervare(localTime);
                rezervare.setZiRezervare(localDate);
                //MainService.getInstance().updateRezervare(username, rezervare, oraSelectata, localDate);
                MainService.getInstance().updateRezervare(username, rezervare, oraSelectata);
                modelRezervare.setElementAt("Rezervarea mea", i);
                updateModels();
                JOptionPane.showMessageDialog(null, "Modificarea orei a fost realizata cu succes!");
            }
            else if(modelHours.get(i) == localTime && modelRezervare.get(i).equals("Rezervare alt client")){
                JOptionPane.showMessageDialog(null, "NU puteti modifica aceasta rezervare!");
            }
        }
    }

    public void stergeRezervare(){
        LocalDate localDate = dateFormat();
        LocalTime localTime = timeFormat();
        int idClient;

        Locale locale = new Locale("ro", "RO");
        JOptionPane.setDefaultLocale(locale);
        int option = JOptionPane.showConfirmDialog(null, "Doriti sa stergeti evenimenul selectat?", "Sterge rezervare", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION) {
            for (int i = 0; i < modelRezervare.size(); i++) {
                if (modelHours.get(i) == localTime && modelRezervare.get(i).equals("Rezervarea mea")) {
                    idClient = MainService.getInstance().getIdClient(username);
                    Rezervare rezervare = new Rezervare();
                    rezervare.setIdClient(idClient);
                    rezervare.setOraRezervare(localTime);
                    rezervare.setZiRezervare(localDate);
                    MainService.getInstance().deleteRezervare(username, rezervare);
                    updateModels();
                    JOptionPane.showMessageDialog(null, "Stergerea rezervarii a fost realizata cu succes!");
                } else if (modelHours.get(i) == localTime && modelRezervare.get(i).equals("Rezervare alt client")) {
                    JOptionPane.showMessageDialog(null, "NU puteti sterge aceasta rezervare!");
                }
            }
        }
    }

    public void backToMainMenu(){
        new MainWindowForClient(username);
        dispose();
    }


}
