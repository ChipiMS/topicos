/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import espotifai.Album;
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
public class AlbumDAO {
    Connection conn;
    
    public AlbumDAO(Connection conn){
        this.conn = conn;
    }
    
    public ObservableList<Album> findAll(){
        ObservableList<Album> album = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Album";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                album.add(new Album(rs.getInt("AlbumId"),rs.getString("Title"),rs.getInt("ArtistId"))); 
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return album;
    }
    
    public Boolean delete(int AlbumId) {
        try {
            String query = "delete from Album where AlbumId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, AlbumId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean insert(Album album) {
        try {
            String query = "insert into Album "
                    + " (Name, ArtistId)"
                    + " values (?,?)";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,album.getTitle());
            st.setInt(2, album.getArtistId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public Boolean update(Album album) {
        try {
            String query = "update album "
                    + " set Name = ?"
                    + " where AlbumId = ?";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,album.getTitle());
            st.setInt(2,album.getArtistId());
            st.setInt(3,album.getAlbumId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
