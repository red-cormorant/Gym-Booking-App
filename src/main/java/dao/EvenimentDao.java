package dao;

import model.Eveniment;
import model.Rezervare;
import service.MainService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EvenimentDao {
    private Connection con;

    public EvenimentDao(Connection con) {
        this.con = con;
    }


    public void addEveniment(String username, Eveniment eveniment) throws SQLException {
        String sql = "INSERT into eveniment VALUES(NULL, ?, ?, ?, ?, ?)";
        Date date = Date.valueOf(eveniment.getZiEveniment());
        Time time = Time.valueOf(eveniment.getOraEveniment());
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, MainService.getInstance().getIdAngajat(username));
            stmt.setInt(2, eveniment.getNrMaximParticipanti());
            stmt.setString(3, eveniment.getNotes());
            stmt.setTime(4, time);
            stmt.setDate(5, date);
            stmt.executeUpdate();
        }
    }

    public List<Eveniment> getEvenimente(String username) throws SQLException {
        String sql = "SELECT nrMaximParticipanti, notes, oraEveniment, ziEveniment FROM eveniment where idAngajat = ? ORDER BY ziEveniment DESC";
        List<Eveniment> evenimente = new ArrayList<>();
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, MainService.getInstance().getIdAngajat(username));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                evenimente.add(new Eveniment(
                        rs.getInt("nrMaximParticipanti"),
                        rs.getString("notes"),
                        rs.getTime("oraEveniment").toLocalTime(),
                        rs.getDate("ziEveniment").toLocalDate()
                ));
            }
        }
        return evenimente;
    }

    public int getIdEveniment(String username, Eveniment eveniment) throws SQLException {
        String sql = "SELECT idEveniment FROM eveniment WHERE idAngajat = ? AND nrMaximParticipanti = ? AND notes = ? AND oraEveniment = ? AND ziEveniment = ?";
        int idEveniment = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, MainService.getInstance().getIdAngajat(username));
            stmt.setInt(2, eveniment.getNrMaximParticipanti());
            stmt.setString(3, eveniment.getNotes());
            stmt.setTime(4, Time.valueOf(eveniment.getOraEveniment()));
            stmt.setDate(5, Date.valueOf(eveniment.getZiEveniment()));
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                idEveniment = rs.getInt("idEveniment");
        }

        return idEveniment;
    }

    public int getIdEveniment(Eveniment eveniment) throws SQLException {
        String sql = "SELECT idEveniment FROM eveniment WHERE notes = ? AND oraEveniment = ? AND ziEveniment = ?";
        int idEveniment = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, eveniment.getNotes());
            stmt.setTime(2,  Time.valueOf(eveniment.getOraEveniment()));
            stmt.setDate(3, Date.valueOf(eveniment.getZiEveniment()));
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                idEveniment = rs.getInt("idEveniment");
        }
        return idEveniment;
    }

    public void stergeEveniment(String username, Eveniment eveniment) throws SQLException {
        String sql = "DELETE from eveniment WHERE idEveniment = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, getIdEveniment(username, eveniment));
            stmt.executeUpdate();
        }
    }

    public List<Eveniment> getClientEveniment(String username) throws SQLException {
        String sql = "SELECT  e.nrMaximParticipanti, e.notes, e.oraEveniment, e.ziEveniment FROM eveniment e INNER JOIN detaliieveniment d ON e.idEveniment = d.idEveniment WHERE d.idClient = ? ORDER BY ziEveniment DESC";
        List<Eveniment> evenimente = new ArrayList<>();
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1,MainService.getInstance().getIdClient(username));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Eveniment eveniment = new Eveniment(
                        rs.getInt("nrMaximParticipanti"),
                        rs.getString("notes"),
                        rs.getTime("oraEveniment").toLocalTime(),
                        rs.getDate("ziEveniment").toLocalDate()
                );
                evenimente.add(eveniment);
            }
        }
        return evenimente;
    }

    public void updateNrMaximParticipanti(int nr, String username, Eveniment eveniment) throws SQLException {
        String sql = "UPDATE eveniment SET nrMaximParticipanti = ? WHERE idEveniment = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, nr);
            stmt.setInt(2,MainService.getInstance().getIdEveniment(username, eveniment));
            stmt.executeUpdate();
        }
    }

    public void updateNotes(String notes, String username, Eveniment eveniment) throws SQLException{
        String sql = "UPDATE eveniment SET notes = ? WHERE idEveniment = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, notes);
            stmt.setInt(2,MainService.getInstance().getIdEveniment(username, eveniment));
            stmt.executeUpdate();
        }
    }

    public void updateOraEveniment(LocalTime ora, String username, Eveniment eveniment) throws SQLException{
        String sql = "UPDATE eveniment SET oraEveniment = ? WHERE idEveniment = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setTime(1, Time.valueOf(ora));
            stmt.setInt(2,MainService.getInstance().getIdEveniment(username, eveniment));
            stmt.executeUpdate();
        }
    }

    public void updateZiEveniment(LocalDate zi, String username, Eveniment eveniment) throws SQLException{
        String sql = "UPDATE eveniment SET ziEveniment = ? WHERE idEveniment = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setDate(1, Date.valueOf(zi));
            stmt.setInt(2,MainService.getInstance().getIdEveniment(username, eveniment));
            stmt.executeUpdate();
        }
    }








}
