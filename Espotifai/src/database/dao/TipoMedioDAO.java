package database.dao;
import espotifai.TipoMedio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class TipoMedioDAO {
    Connection conn;
    public TipoMedioDAO(Connection conn){
        this.conn = conn;
    }
    public ObservableList<TipoMedio> findAll() {
        ObservableList<TipoMedio> carreras = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM MediaType";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                carreras.add(new TipoMedio(rs.getInt("MediaTypeId"), rs.getString("Name")));
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return carreras;
    }
    public Boolean delete(int MediaTypeId) {
        try {
            String query = "delete from MediaType where MediaTypeId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, MediaTypeId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean insert(TipoMedio tipoMedio) {
        try {
            String query = "insert into MediaType "
                    + " (Name)"
                    + " values (?)";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, tipoMedio.getName());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public Boolean update(TipoMedio tipoMedio) {
        try {
            String query = "update MediaType "
                    + " set Name = ? "
                    + " where MediaTypeId = ?";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, tipoMedio.getName());
            st.setInt(2, tipoMedio.getMediaTypeId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
