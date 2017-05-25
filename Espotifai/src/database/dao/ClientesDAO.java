package database.dao;

import espotifai.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientesDAO {
    Connection conn;
    public ClientesDAO(Connection conn){
        this.conn = conn;
    }
    
public ObservableList<Clientes> findAll() {
        ObservableList<Clientes> clientes = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Customer";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                clientes.add(new Clientes(rs.getInt("CustomerId"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("Company"),rs.getString("Address"),
                                            rs.getString("City"),rs.getString("State"),rs.getString("Country"),rs.getString("PostalCode"),
                                            rs.getString("Phone"),rs.getString("Fax"),rs.getString("Email"),
                                            rs.getInt("SupportRepId"))); 
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return clientes;
    }
    public Boolean delete(int CustomerId) {
        try {
            String query = "delete from Customer where CustomerId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, CustomerId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean insert(Clientes clientes) {
        try {
            String query = "insert into Customer "
                    + " (LastName, FirstName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,clientes.getLastName());
            st.setString(2,clientes.getFirstName());
            st.setString(3,clientes.getCompany());
            st.setString(4,clientes.getAddress());
            st.setString(5,clientes.getCity());
            st.setString(6,clientes.getState());
            st.setString(7,clientes.getCountry());
            st.setString(8,clientes.getPostalCode());
            st.setString(9,clientes.getPhone());
            st.setString(10,clientes.getFax());
            st.setString(11,clientes.getEmail());
            st.setInt(12,clientes.getSupportRepId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public ObservableList<Clientes> findAllObs() {
        ObservableList<Clientes> clientes = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Customer";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Clientes e = new Clientes(rs.getInt("CustomerId"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("Company"),rs.getString("Address"),
                                            rs.getString("City"),rs.getString("State"),rs.getString("Country"),rs.getString("PostalCode"),
                                            rs.getString("Phone"),rs.getString("Fax"),rs.getString("Email"),
                                            rs.getInt("SupportRepId"));
                clientes.add(e);
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return clientes;
    }
    public ObservableList<Clientes> reporte(){
        ObservableList<Clientes> clientes = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Customer";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Clientes e = new Clientes(rs.getInt("CustomerId"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("Company"),rs.getString("Address"),
                                            rs.getString("City"),rs.getString("State"),rs.getString("Country"),rs.getString("PostalCode"),
                                            rs.getString("Phone"),rs.getString("Fax"),rs.getString("Email"),
                                            rs.getInt("SupportRepId"));
                clientes.add(e);
            }
            rs.close();
            st.close();
 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return clientes;
    }
    public Boolean update(Clientes clientes) {
        try {
            String query = "update Customer "
                    + " set LastName = ?, FirstName = ?, Company = ?, Address = ?, City = ?, State = ?, Country = ?, PostalCode = ?, Phone = ?, Fax = ?, Email = ?, SupportRepId = ?"
                    + " where CustomerId = ?";
            PreparedStatement st =  conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,clientes.getLastName());
            st.setString(2,clientes.getFirstName());
            st.setString(3,clientes.getCompany());
            st.setString(4,clientes.getAddress());
            st.setString(5,clientes.getCity());
            st.setString(6,clientes.getState());
            st.setString(7,clientes.getCountry());
            st.setString(8,clientes.getPostalCode());
            st.setString(9,clientes.getPhone());
            st.setString(10,clientes.getFax());
            st.setString(11,clientes.getEmail());
            st.setInt(12,clientes.getSupportRepId());
            st.setInt(13,clientes.getCustomerId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}

