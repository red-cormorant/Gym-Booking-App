package service;

import dao.*;
import model.*;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class MainService {

    private String url = "jdbc:mysql://localhost/planificator";
    private String user = "root";
    private String password = "";
    private Connection con;

    private MainService() {

        try{
            con = DriverManager.getConnection(url,user,password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static final class SingletonHolder{
        private static final MainService INSTANCE = new MainService();
    }

    public static MainService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /***
     * UserService
     */


    public boolean inregistrare(User user){
        boolean rezultat = false;
        UserDao userDao = new UserDao(con);
        try{
            Optional<User> optionalUser = userDao.findByUsername(user.getUsername());
            if(optionalUser.isEmpty()) {
                userDao.addUser(user);
                rezultat = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultat;
    }

    public Optional<User> login(String username, String parola) {
        UserDao userDao = new UserDao(con);
        try{
            Optional<User> optionalUser = userDao.findByUsername(username);
            if(optionalUser.isPresent())
                if(optionalUser.get().getParola().equals(parola))
                    return optionalUser;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int getIdUser(String username) throws SQLException{
        UserDao userDao = new UserDao(con);
        try {
            return userDao.getIdUser(username);
        } finally {

        }

    }

    public void deleteUser(String username) {
        UserDao userDao = new UserDao(con);
        try{
            userDao.deleteUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean esteAngajat(String username) {
        boolean rez = false;
        UserDao userDao = new UserDao(con);
        try{
            rez = userDao.esteAngajat(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;
    }


    /***
     * ClientService
     */

    public void addClient(String username, Client client){
        ClientDao clientDao = new ClientDao(con);
        try{
            clientDao.addClient(username, client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificareClientExistent(String username) {
        ClientDao clientDao = new ClientDao(con);
        boolean rezultat = false;
        try {
           if(clientDao.verificareClientExistent(username))
               rezultat = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    public String[] getFieldsFromClient(String username) {
        ClientDao clientDao = new ClientDao(con);
        String[] results = new String[4];
        try{
            results = clientDao.getFieldsFromClient(username);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void updateClient(String username, Client client) {
        ClientDao clientDao = new ClientDao(con);
        try{
            clientDao.updateClient(username, client);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getIdClient(String username) {
        int idClient = 0;
        ClientDao clientDao = new ClientDao(con);
        try{
            idClient = clientDao.getIdClient(username);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return idClient;
    }

    public List<Client> getClients(){
        ClientDao clientDao = new ClientDao(con);
        List<Client> clients = new ArrayList<>();
        try{
            clients = clientDao.getClients();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return clients;
    }


    /**
     * RezervareService
     */

    public void addRezervare(String username, Rezervare rezervare){
        RezervareDao rezervareDao = new RezervareDao(con);

        try{
            rezervareDao.addRezervare(username, rezervare);
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateRezervare(String username, Rezervare rezervare, LocalTime localTime){
        RezervareDao rezervareDao = new RezervareDao(con);
        try{
            rezervareDao.updateRezervare(username, rezervare, localTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRezervare(LocalTime time, LocalDate date) {
        RezervareDao rezervareDao = new RezervareDao(con);
        int idClient = 0;
        try{
            idClient = rezervareDao.getRezervare(time, date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idClient;
    }

    public List<LocalTime> getOraRezervare(LocalDate localDate) {
        RezervareDao rezervareDao = new RezervareDao(con);
        List<LocalTime> list = new ArrayList<>();
        try{
            list = rezervareDao.getOraRezervare(localDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteRezervare(String username, Rezervare rezervare) {
        RezervareDao rezervareDao = new RezervareDao(con);
        try{
            rezervareDao.deleteRezervare(username, rezervare);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * AngajatService
     */

    public int getIdAngajat(String username) {
        AngajatDao angajatDao = new AngajatDao(con);
        int idAngajat = 0;
        try{
            idAngajat = angajatDao.getIdAngajat(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAngajat;
    }

    public String[] getFieldsFromAngajat(String username){
        String[] results = new String[4];
        AngajatDao angajatDao = new AngajatDao(con);
        try{
            results = angajatDao.getFieldsFromAngajat(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public void updateAngajat(String username, Angajat angajat) {
        AngajatDao angajatDao = new AngajatDao(con);
        try{
            angajatDao.updateAngajat(username, angajat);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * EvenimentService
     */

    public void addEveniment(String username, Eveniment eveniment) {
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimentDao.addEveniment(username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Eveniment> getEvenimente(String username){
        List<Eveniment> evenimente = new ArrayList<>();
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimente = evenimentDao.getEvenimente(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenimente;

    }

    public int getIdEveniment(String username, Eveniment eveniment){
        int idEveniment = 0;
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            idEveniment = evenimentDao.getIdEveniment(username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEveniment;
    }

    public int getIdEveniment(Eveniment eveniment){
        int idEveniment = 0;
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            idEveniment = evenimentDao.getIdEveniment(eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEveniment;
    }

    public void stergeEveniment(String username, Eveniment eveniment){
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimentDao.stergeEveniment(username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Eveniment> getClientEveniment(String username){
        EvenimentDao evenimentDao = new EvenimentDao(con);
        List<Eveniment> list = new ArrayList<>();
        try{
            list = evenimentDao.getClientEveniment(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateNrMaximParticipanti(int nr, String username, Eveniment eveniment){
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimentDao.updateNrMaximParticipanti(nr, username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNotes(String notes, String username, Eveniment eveniment){
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimentDao.updateNotes(notes, username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOraEveniment(LocalTime ora, String username, Eveniment eveniment){
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimentDao.updateOraEveniment(ora, username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateZiEveniment(LocalDate zi, String username, Eveniment eveniment){
        EvenimentDao evenimentDao = new EvenimentDao(con);
        try{
            evenimentDao.updateZiEveniment(zi, username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * DetaliiEvenimentDao
     */

    public void adaugaClientLaEveniment(String username, Eveniment eveniment, int idClient){
        DetaliiEvenimentDao detaliiEvenimentDao = new DetaliiEvenimentDao(con);
        try{
            detaliiEvenimentDao.adaugaClientiLaEveniment(username, eveniment, idClient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int verificaNumarClienti(String username, Eveniment eveniment) {
        int numar = 0;
        DetaliiEvenimentDao detaliiEvenimentDao = new DetaliiEvenimentDao(con);
        try{
            numar = detaliiEvenimentDao.verificaNumarCLienti(username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numar;
    }

    public void stergeInvitatie(String username, Eveniment eveniment) {
        DetaliiEvenimentDao detaliiEvenimentDao = new DetaliiEvenimentDao(con);
        try{
            detaliiEvenimentDao.stergeInvitatie(username, eveniment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LocalDateTime> getNotificare(String username){
        DetaliiEvenimentDao detaliiEvenimentDao = new DetaliiEvenimentDao(con);
        List<LocalDateTime> list = new ArrayList<>();
        try{
            list = detaliiEvenimentDao.getNotificare(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }







}
