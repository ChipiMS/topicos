/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import espotifai.Artist;
import espotifai.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ELÍAS MANCERA
 */
public class ArtistaDAO {

    Connection conn;

    public ArtistaDAO(Connection conn) {
        this.conn = conn;
    }

    public ObservableList<Artist> findAll() {
        ObservableList<Artist> artista = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Artist";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                artista.add(new Artist(rs.getInt("ArtistId"), rs.getString("Name")));
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return artista;
    }

    public Boolean delete(int ArtistId) {
        try {
            String query = "delete from Artist where ArtistId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ArtistId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Artist artist) {
        try {
            String query = "insert into Artist "
                    + " (Name)"
                    + " values (?)";
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, artist.getName());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Artist artista) {
        try {
            String query = "update Artist "
                    + " set Name = ?"
                    + " where ArtistId = ?";
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, artista.getName());
            st.setInt(2, artista.getArtistId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
