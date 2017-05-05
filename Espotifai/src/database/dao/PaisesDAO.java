package database.dao;
import espotifai.Paises;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class PaisesDAO {
    Connection conn;
    public PaisesDAO(Connection conn){
        this.conn = conn;
    }
    public ObservableList<Paises> findAll() {
        ObservableList<Paises> paises = FXCollections.observableArrayList();
        try {
            String query = "select BillingCountry 'Name',count(*) 'Invoices',sum(Total) 'Total' from Invoice group by BillingCountry;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                paises.add(new Paises(rs.getString("Name"), rs.getString("Invoices"), rs.getString("Total")));
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return paises;
    }
}
