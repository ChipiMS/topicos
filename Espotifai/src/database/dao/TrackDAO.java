/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import espotifai.Track;
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
public class TrackDAO {
    Connection conn;
    public TrackDAO(Connection conn){
        this.conn = conn;
    }
    public ObservableList<Track> findAll() {
        ObservableList<Track> track = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Track";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                track.add(new Track(rs.getInt("TrackId"),rs.getInt("AlbumId"),rs.getInt("MediaTypeId"),rs.getInt("GenreId"),rs.getInt("Milliseconds"),rs.getInt("Bytes"),rs.getString("Name"),rs.getString("Composer"),rs.getDouble("UnitPrice"))); 
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return track;
    }
    public Boolean delete(int TrackId) {
        try {
            String query = "delete from Track where TrackId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, TrackId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean insert(Track track) {
        try {
            String query = "insert into Track "
                    + " (Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)"
                    + " values (?,?,?,?,?,?,?,?)";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,track.getName());
            st.setInt(2,track.getAlbumId());
            st.setInt(3,track.getMediaTypeId());
            st.setInt(4,track.getGenreId());
            st.setString(5,track.getComposer());
            st.setInt(6,track.getMilliseconds());
            st.setInt(7,track.getBytes());
            st.setDouble(8,track.getUnitPrice());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public Boolean update(Track track) {
        try {
            String query = "update Track "
                    + " set Name = ?, AlbumId = ?, MediaTypeId = ?, GenreId = ?, Composer = ?, Milliseconds = ?, Bytes = ?, UnitPrice = ?"
                    + " where TrackId = ?";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,track.getName());
            st.setInt(2,track.getAlbumId());
            st.setInt(3,track.getMediaTypeId());
            st.setInt(4,track.getGenreId());
            st.setString(5,track.getComposer());
            st.setInt(6,track.getMilliseconds());
            st.setInt(7,track.getBytes());
            st.setDouble(8,track.getUnitPrice());
            st.setInt(9,track.getTrackId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
