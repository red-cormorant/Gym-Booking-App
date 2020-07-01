package dao;

import model.Client;
import model.Eveniment;
import service.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DetaliiEvenimentDao {
    private Connection con;

    public DetaliiEvenimentDao(Connection con) {
        this.con = con;
    }

    public void adaugaClientiLaEveniment(String username, Eveniment eveniment, int idClient) throws SQLException {
        String sql = "INSERT INTO detaliieveniment VALUES(NULL,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, MainService.getInstance().getIdEveniment(username, eveniment));
            stmt.setInt(2, idClient);
            stmt.executeUpdate();
        }

    }

    public int verificaNumarCLienti(String username, Eveniment eveniment) throws SQLException{
        String sql = "SELECT COUNT(idEveniment) FROM detaliiEveniment WHERE idEveniment = ?";
        int numar = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, MainService.getInstance().getIdEveniment(username, eveniment));
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                numar = rs.getInt(1);
        }
        return numar;
    }


    public void stergeInvitatie(String username, Eveniment eveniment) throws SQLException{
        String sql = "DELETE FROM detaliieveniment WHERE idClient = ? AND idEveniment = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, MainService.getInstance().getIdClient(username));
            stmt.setInt(2, MainService.getInstance().getIdEveniment(eveniment));
            stmt.executeUpdate();
        }
    }

    public List<LocalDateTime> getNotificare(String username) throws SQLException {
        String sql = "SELECT e.oraEveniment, e.ziEveniment FROM eveniment e INNER JOIN detaliieveniment d ON e.idEveniment = d.idEveniment WHERE d.idClient = ? ORDER BY ziEveniment ASC, oraEveniment ASC;";
        List<LocalDateTime> list = new ArrayList<>();
        LocalTime localTime;
        LocalDate localDate;
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, MainService.getInstance().getIdClient(username));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                localTime = rs.getTime("oraEveniment").toLocalTime();
                localDate = rs.getDate("ziEveniment").toLocalDate();
                list.add(LocalDateTime.of(localDate, localTime));
            }
        }
        return list;
    }




}
