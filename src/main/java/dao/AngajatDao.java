package dao;

import model.Angajat;
import service.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AngajatDao {
    private Connection con;

    public AngajatDao(Connection con) {
        this.con = con;
    }

    public int getIdAngajat(String username) throws SQLException {
        int idAngajat = 0;
        String sql = "SELECT a.idAngajat FROM angajat a INNER JOIN user u ON u.idUser = a.idUser WHERE u.username = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                idAngajat = rs.getInt("idAngajat");
        }
        return idAngajat;
    }

    public void updateAngajat(String usename, Angajat angajat) throws SQLException{
        String sql = "UPDATE angajat SET numeAngajat = ?, prenumeAngajat = ?, telefonAngajat = ?, emailAngajat = ? WHERE idUser = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, angajat.getNumeAngajat());
            stmt.setString(2, angajat.getPrenumeAngajat());
            stmt.setString(3, angajat.getTelefonAngajat());
            stmt.setString(4, angajat.getEmailAngajat());
            stmt.setInt(5, MainService.getInstance().getIdUser(usename));
            stmt.executeUpdate();
        }
    }

    public String[] getFieldsFromAngajat(String username) throws SQLException {
        String [] results = new String[4];
        String select = "SELECT a.numeAngajat, a.prenumeAngajat, a.telefonAngajat, a.emailAngajat FROM angajat a INNER JOIN user u ON u.idUser = a.idUser AND u.username = ?";
        try(PreparedStatement stmt = con.prepareStatement(select)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                results[0] = rs.getString("numeAngajat");
                results[1] = rs.getString("prenumeAngajat");
                results[2] = rs.getString("telefonAngajat");
                results[3] = rs.getString("emailAngajat");
            }
        }

        return results;

    }




}
