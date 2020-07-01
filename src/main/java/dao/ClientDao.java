package dao;

import model.Client;
import model.Rezervare;
import service.MainService;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private Connection con;

    public ClientDao(Connection con) {
        this.con = con;
    }

    public void addClient(String username, Client client) throws SQLException {
        String sqlInsert = "INSERT INTO client VALUES(NULL,?,?,?,?,?)";
        try{
            PreparedStatement stmt; //= con.prepareStatement(sqlInsert);
            client.setIdUsername(MainService.getInstance().getIdUser(username));
            stmt = con.prepareStatement(sqlInsert);
            stmt.setInt(1, client.getIdUsername());
            stmt.setString(2, client.getNumeClient());
            stmt.setString(3, client.getPrenumeClient());
            stmt.setString(4, client.getTelefon());
            stmt.setString(5, client.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //am ales sa ma folosesc de SQL pentru ca mi-a fost mai usor. Puteam sa creez o metoda getIdUser pt client si
    //faceam comparatie cu metoda getIdUser din UserDao
    public boolean verificareClientExistent(String username) throws SQLException{
        String sql = "SELECT u.idUser FROM user u INNER JOIN client c where c.idUser = u.idUser AND u.username = ?";
        boolean rezultat = false;
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs =  stmt.executeQuery();
            while(rs.next())
                rezultat = true;

            return rezultat;
        }

    }

    public String[] getFieldsFromClient(String username) throws SQLException {
        String [] results = new String[4];
        String select = "SELECT c.numeClient, c.prenumeClient, c.telefon, c.email FROM client c INNER JOIN user u ON u.idUser = c.idUser WHERE u.username = ?";
        try(PreparedStatement stmt = con.prepareStatement(select)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                results[0] = rs.getString("numeClient");
                results[1] = rs.getString("prenumeClient");
                results[2] = rs.getString("telefon");
                results[3] = rs.getString("email");
            }
        }

        return results;

    }


    public void updateClient(String username, Client client) throws SQLException {
        String sql = "UPDATE client SET numeClient = ?, prenumeClient = ?, telefon = ?, email = ? WHERE idUser = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, client.getNumeClient());
            stmt.setString(2, client.getPrenumeClient());
            stmt.setString(3, client.getTelefon());
            stmt.setString(4, client.getEmail());
            stmt.setInt(5, MainService.getInstance().getIdUser(username));
            stmt.executeUpdate();
        }
    }

    public int getIdClient(String username) throws SQLException{
        String sql = "SELECT c.idClient from client c INNER JOIN user u ON u.idUser = c.idUser WHERE username = ?";
        int idClient = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                idClient = rs.getInt("idClient");
        }


        return idClient;
    }

    public List<Client> getClients() throws SQLException{
        String sql = "SELECT idClient, numeClient, prenumeClient, telefon FROM client";
        List<Client> clients = new ArrayList<>();
        int idClient;
        String numeClient, prenumeClient, numarTelefon;
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                idClient = rs.getInt("idClient");
                numeClient = rs.getString("numeClient");
                prenumeClient = rs.getString("prenumeClient");
                numarTelefon = rs.getString("telefon");
                clients.add(new Client(idClient, numeClient, prenumeClient, numarTelefon));
            }
        }
        return clients;
    }



}
