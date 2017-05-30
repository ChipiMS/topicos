package database.dao;

import espotifai.PlayList;
import espotifai.PlayListTrack;
import espotifai.TipoMedio;
import espotifai.Track;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayListDAO {
    Connection conn;
    public PlayListDAO(Connection conn){
        this.conn = conn;
    }
    public ObservableList<PlayList> findAllPlayList() {
        ObservableList<PlayList> listas = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM PlayList";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                listas.add(new PlayList(rs.getInt("PlayListId"), rs.getString("Name")));
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return listas;
    }
        public ObservableList<Track> findAllCanciones(int TrackId) {
        ObservableList<Track> listas = FXCollections.observableArrayList();
        try {
            String query = "select * from Track where TrackId in (select TrackId from PlaylistTrack where PlaylistId = "+TrackId+");";
            PreparedStatement st = conn.prepareStatement(query);
            //st.setInt(1, TrackId);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                listas.add(new Track(rs.getInt("TrackId"),rs.getInt("AlbumId"),rs.getInt("MediaTypeId"),rs.getInt("GenreId"),rs.getInt("Milliseconds"),
                rs.getInt("Bytes"),rs.getString("Name"),rs.getString("Composer"),rs.getDouble("UnitPrice"))); 
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return listas;
    }
        public ObservableList<Track> findAllCancionesFaltantes(int TrackId) {
        ObservableList<Track> listas = FXCollections.observableArrayList();
        try {
            String query = "select * from Track where TrackId not in (select TrackId from PlaylistTrack where PlaylistId = "+TrackId+");";
            PreparedStatement st = conn.prepareStatement(query);
            //st.setInt(1, TrackId);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                listas.add(new Track(rs.getInt("TrackId"),rs.getInt("AlbumId"),rs.getInt("MediaTypeId"),rs.getInt("GenreId"),rs.getInt("Milliseconds"),
                rs.getInt("Bytes"),rs.getString("Name"),rs.getString("Composer"),rs.getDouble("UnitPrice"))); 
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return listas;
    }
}
