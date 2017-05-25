package database.dao;

import espotifai.PlayList;
import espotifai.PlayListTrack;
import espotifai.TipoMedio;
import java.sql.Connection;
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
        public ObservableList<PlayListTrack> findAllCanciones(int TrackId) {
        ObservableList<PlayListTrack> listas = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM PlayListTrack where PlayListId = "+TrackId+"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                listas.add(new PlayListTrack(rs.getInt("PlayListId"), rs.getInt("TrackId")));
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
