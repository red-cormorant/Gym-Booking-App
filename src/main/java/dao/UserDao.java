package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public Optional<User> findByUsername(String username) throws SQLException{
        String sql = "SELECT * from user WHERE username = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setParola(rs.getString("parola"));
                user.setUsername(rs.getString("username"));
                user.setEsteAngajat((Boolean)rs.getBoolean("esteAngajat"));
                return Optional.of(user);
            }
        }


        return Optional.empty();
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user VALUES(NULL,?,?,0)";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getParola());
            stmt.executeUpdate();
        }
    }

    public int getIdUser(String username) throws  SQLException {
        String sql = "SELECT idUser from user WHERE username = ?";
        int idUser = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                idUser = rs.getInt("idUser");
            }
        }

        return idUser;

    }

    public void deleteUser(String username) throws SQLException{
        String sql = "DELETE FROM user WHERE username = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

    public boolean esteAngajat(String username) throws SQLException{
        String sql = "SELECT esteAngajat FROM user WHERE username = ?";
        boolean rez = false;
        int nr = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                nr = rs.getInt("esteAngajat");
            if (nr == 1)
                rez = true;
        }
        return rez;
    }



}

