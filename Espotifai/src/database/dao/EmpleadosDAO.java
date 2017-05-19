package database.dao;
import espotifai.Empleados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class EmpleadosDAO {
    Connection conn;
    public EmpleadosDAO(Connection conn){
        this.conn = conn;
    }
    public ObservableList<Empleados> findAll() {
        ObservableList<Empleados> empleados = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Empleados";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                empleados.add(new Empleados(rs.getInt("EmployeeId"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("Title"),rs.getString("Address"),
                                            rs.getString("City"),rs.getString("State"),rs.getString("Country"),rs.getString("PostalCode"),
                                            rs.getString("Phone"),rs.getString("Fax"),rs.getString("Email"),
                                            rs.getInt("ReportsTo"),rs.getDate("BirthDay"),rs.getDate("HireDate"))); 
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return empleados;
    }
    public Boolean delete(int EmployeeId) {
        try {
            String query = "delete from Employee where EmployeeId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, EmployeeId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean insert(Empleados empleados) {
        try {
            String query = "insert into Employee "
                    + " (EmployeeId) (LastName) (FirstName) (Title) (Address) (City) (State) (Country) (PostalCode) (Phone) (Fax) (Email) (ReportsTo) (BirthDay) (HireDate)"
                    + " values (?) (?) (?) (?) (?) (?) (?) (?) (?) (?) (?) (?) (?) (?) (?)";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,empleados.getEmployeeId());
            st.setString(2,empleados.getLastName());
            st.setString(3,empleados.getFirstName());
            st.setString(4,empleados.getTitle());
            st.setString(5,empleados.getAddress());
            st.setString(6,empleados.getCity());
            st.setString(7,empleados.getState());
            st.setString(8,empleados.getCountry());
            st.setString(9,empleados.getPostalCode());
            st.setString(10,empleados.getPhone());
            st.setString(11,empleados.getFax());
            st.setString(12,empleados.getEmail());
            st.setInt(13,empleados.getReportsTo());
            st.setDate(14,empleados.getBirthDay());
            st.setDate(15,empleados.getHireDate());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public Boolean update(Empleados empleados) {
        try {
            String query = "update Employee "
                    + " set LastName = ?, set FirstName = ?, set Title = ?, set Address = ?, set City = ?, set State = ?, set Country = ?, set PostalCode = ?, set Phone = ?, set Fax = ?, set Email = ?, set ReportsTo = ?, set BirthDay = ?, set HireDate = ?"
                    + " where EmployeeId = ?";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,empleados.getLastName());
            st.setString(2,empleados.getFirstName());
            st.setString(3,empleados.getTitle());
            st.setString(4,empleados.getAddress());
            st.setString(5,empleados.getCity());
            st.setString(6,empleados.getState());
            st.setString(7,empleados.getCountry());
            st.setString(8,empleados.getPostalCode());
            st.setString(9,empleados.getPhone());
            st.setString(10,empleados.getFax());
            st.setString(11,empleados.getEmail());
            st.setInt(12,empleados.getReportsTo());
            st.setDate(13,empleados.getBirthDay());
            st.setDate(14,empleados.getHireDate());
            st.setInt(15,empleados.getEmployeeId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
