package dao;

import model.Rezervare;
import service.MainService;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RezervareDao {
    private Connection con;

    public RezervareDao(Connection con) {
        this.con = con;
    }

    public void addRezervare(String username, Rezervare rezervare) throws SQLException {
        String sql = "INSERT INTO rezervare VALUES(NULL,?,?,?)";
        Date date = Date.valueOf(rezervare.getZiRezervare());
        Time time = Time.valueOf(rezervare.getOraRezervare());
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            int id = MainService.getInstance().getIdClient(username);
            stmt.setInt(1, id);
            stmt.setTime(2, time);
            stmt.setDate(3, date);
            stmt.executeUpdate();
        }
    }

    public int getIdRezervare(String username, Rezervare rezervare) throws SQLException{
        String sql = "SELECT idRezervare from rezervare WHERE idClient = ? AND oraRezervare = ? AND ziRezervare = ?";
        Date date = Date.valueOf(rezervare.getZiRezervare());
        Time time = Time.valueOf(rezervare.getOraRezervare());
        int idRezervare = 0;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setTime(1, time);
            stmt.setDate(2, date);
            stmt.setInt(3, MainService.getInstance().getIdClient(username));
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                idRezervare = rs.getInt("idRezervare");
            }
        }
        return idRezervare;

    }


    public void updateRezervare(String username, Rezervare rezervare, LocalTime localTime) throws SQLException {
        String sql = "UPDATE rezervare SET oraRezervare = ?, ziRezervare = ? WHERE idClient = ? AND oraRezervare = ?";

        Date date = Date.valueOf(rezervare.getZiRezervare());
        Time time = Time.valueOf(rezervare.getOraRezervare());
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setTime(1, time);
            stmt.setDate(2, date);
            stmt.setInt(3, MainService.getInstance().getIdClient(username));
            stmt.setTime(4, Time.valueOf(localTime));

            stmt.executeUpdate();
        }
    }

    public int getRezervare(LocalTime localTime, LocalDate localDate) throws SQLException {
        String sql = "SELECT idClient FROM rezervare WHERE oraRezervare = ? AND ziRezervare = ?";
        int idClient = 0;
        Date date = Date.valueOf(localDate);
        Time time = Time.valueOf(localTime);
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setTime(1, time);
            stmt.setDate(2, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idClient = rs.getInt("idClient");
            }
        }
        return idClient;
    }

    public List<LocalTime> getOraRezervare(LocalDate localDate) throws SQLException {
        String sql = "SELECT oraRezervare FROM rezervare WHERE ziRezervare = ?";
        Date date = Date.valueOf(localDate);
        List<LocalTime> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getTime("oraRezervare").toLocalTime());
            }
        }

        if (list.isEmpty())
            return Collections.emptyList();
        else
            return list;
    }

    public void deleteRezervare(String username, Rezervare rezervare) throws SQLException{
        Date date = Date.valueOf(rezervare.getZiRezervare());
        Time time = Time.valueOf(rezervare.getOraRezervare());
        String sql = "DELETE FROM rezervare WHERE idClient = ? AND oraRezervare = ? AND ziRezervare = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, MainService.getInstance().getIdClient(username));
            stmt.setTime(2, time);
            stmt.setDate(3, date);
            stmt.executeUpdate();
        }
    }

}
