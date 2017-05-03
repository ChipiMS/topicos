package database.dao;
import espotifai.Genero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class GeneroDAO {
    Connection conn;
    public GeneroDAO(Connection conn){
        this.conn = conn;
    }
    public ObservableList<Genero> findAll() {
        ObservableList<Genero> carreras = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Genre";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                carreras.add(new Genero(rs.getInt("GenreId"), rs.getString("Name")));
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return carreras;
    }
    public Boolean delete(int GenreId) {
        try {
            String query = "delete from Genre where GenreId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, GenreId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean insert(Genero genero) {
        try {
            String query = "insert into Genre "
                    + " (Name)"
                    + " values (?)";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, genero.getName());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public Boolean update(Genero genero) {
        try {
            String query = "update Genre "
                    + " set Name = ? "
                    + " where GenreId = ?";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, genero.getName());
            st.setInt(2, genero.getGenreId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
