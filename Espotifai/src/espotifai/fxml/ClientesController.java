package espotifai.fxml;

import database.MySQL;
import database.dao.ClientesDAO;
import database.dao.EmpleadosDAO;
import espotifai.Clientes;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ClientesController implements Initializable {
    @FXML
    Button btnAgregar;
    @FXML
    Button btnModificar;
    @FXML
    Button btnBorrar;
    @FXML
    GridPane actions;
    @FXML
    ComboBox cmbSupportRepId;
    @FXML
    TextField txtLastName, txtFirstName, txtCompany, 
              txtAddress, txtCity, txtState, txtCountry, txtPostalCode, txtPhone, 
              txtFax, txtEmail;
    @FXML
    TableView<Clientes> table;
    TableColumn CustomerIdCol,LastNameCol,FirstNameCol,CompanyCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,
                FaxCol,EmailCol,SupportRepIdCol;
    Boolean agregando = false;
    ObservableList datos;
    ObservableList datosEmpleados;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        ClientesDAO clientesdao = new ClientesDAO(db.getConnection());
        EmpleadosDAO empleadodao = new EmpleadosDAO(db.getConnection());
        CustomerIdCol = new TableColumn("ID");
        LastNameCol = new TableColumn("LastName");
        FirstNameCol = new TableColumn("FirstName");
        CompanyCol = new TableColumn("Company");
        AddressCol = new TableColumn("Address");
        CityCol = new TableColumn("City");
        StateCol = new TableColumn("State");
        CountryCol = new TableColumn("Country");
        PostalCodeCol = new TableColumn("PostalCode");
        PhoneCol = new TableColumn("Phone");
        FaxCol = new TableColumn("Fax");
        EmailCol = new TableColumn("Email");
        SupportRepIdCol = new TableColumn("SupportRepId");
        CustomerIdCol.setCellValueFactory(new PropertyValueFactory("CustomerId"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        CompanyCol.setCellValueFactory(new PropertyValueFactory("Company"));
        AddressCol.setCellValueFactory(new PropertyValueFactory("Address"));
        CityCol.setCellValueFactory(new PropertyValueFactory("City"));
        StateCol.setCellValueFactory(new PropertyValueFactory("State"));
        CountryCol.setCellValueFactory(new PropertyValueFactory("Country"));
        PostalCodeCol.setCellValueFactory(new PropertyValueFactory("PostalCode"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory("Phone"));
        FaxCol.setCellValueFactory(new PropertyValueFactory("Fax"));
        EmailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        SupportRepIdCol.setCellValueFactory(new PropertyValueFactory("SupportRepId"));
        table.getColumns().addAll(CustomerIdCol,LastNameCol,FirstNameCol,CompanyCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,FaxCol,EmailCol,SupportRepIdCol);
        datos = clientesdao.findAllObs();
        datosEmpleados = empleadodao.findAllObs();
        cmbSupportRepId.setItems(datosEmpleados);
        table.setItems(datos);

        
        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                datos = clientesdao.findAllObs();
                Clientes g = table.getSelectionModel().getSelectedItem();
                btnModificar.setDisable(false);
                btnBorrar.setDisable(false);
                txtLastName.setText(g.getLastName());
                txtFirstName.setText(g.getFirstName());
                txtCompany.setText(g.getCompany());
                txtAddress.setText(g.getAddress());
                txtCity.setText(g.getCity());
                txtState.setText(g.getState());
                txtCountry.setText(g.getCountry());
                txtPostalCode.setText(g.getPostalCode());
                txtPhone.setText(g.getPhone());
                txtFax.setText(g.getFax());
                txtEmail.setText(g.getEmail());
                for (int i=0; i<datos.size();i++){
                    Clientes tilin = (Clientes)datos.get(i);
                    if(g.getSupportRepId() == tilin.getSupportRepId()){
                        cmbSupportRepId.getSelectionModel().clearAndSelect(tilin.getSupportRepId());
                    }
                }
                actions.setVisible(true);
                agregando = false;
            }
        });
        
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(agregando){
                    if(txtLastName.getText().trim().length() > 0 &&
                        txtFirstName.getText().trim().length() > 0 &&
                        txtCompany.getText().trim().length() > 0 && 
                        txtAddress.getText().trim().length() > 0 &&
                        txtCity.getText().trim().length() > 0 &&
                        txtState.getText().trim().length() > 0 &&
                        txtCountry.getText().trim().length() > 0 &&
                        txtPostalCode.getText().trim().length() > 0 &&
                        txtPhone.getText().trim().length() > 0 &&
                        txtFax.getText().trim().length() > 0 &&
                        txtEmail.getText().trim().length() > 0 &&
                        cmbSupportRepId.getSelectionModel() != null){
                        
                        
                        Clientes temp = (Clientes) cmbSupportRepId.getSelectionModel().getSelectedItem();
                        clientesdao.insert(new Clientes(
                        txtLastName.getText(),
                        txtFirstName.getText(),
                        txtCompany.getText(), 
                        txtAddress.getText(),
                        txtCity.getText(),
                        txtState.getText(),
                        txtCountry.getText(),
                        txtPostalCode.getText(),
                        txtPhone.getText(),
                        txtFax.getText(),
                        txtEmail.getText(),
                        temp.getSupportRepId()));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            datos = clientesdao.findAllObs();
                            cmbSupportRepId.setItems(datosEmpleados);
                            table.setItems(datos);
                            agregando = false;
                            actions.setVisible(false);
                        
                    }}
                    else{
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Hay campos faltantes");
                        msg.show();
                        
                    }
            
                }
                else{
                    btnModificar.setDisable(true);
                    btnBorrar.setDisable(true);
                    txtLastName.setText("");
                    txtFirstName.setText("");
                    txtCompany.setText(""); 
                    txtAddress.setText("");
                    txtCity.setText("");
                    txtState.setText("");
                    txtCountry.setText("");
                    txtPostalCode.setText("");
                    txtPhone.setText("");
                    txtFax.setText("");
                    txtEmail.setText("");
                    cmbSupportRepId.setSelectionModel(null); 
                    actions.setVisible(true);
                    agregando = true;
                }
            }
        });
        btnBorrar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Clientes g = table.getSelectionModel().getSelectedItem();
                if(clientesdao.delete(g.getCustomerId())){
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Empleado borrado correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if(respuesta.get() == ButtonType.OK){
                        datos = clientesdao.findAllObs();
                        cmbSupportRepId.setItems(datosEmpleados);
                        table.setItems(datos);
                        agregando = false;
                        actions.setVisible(false);
                    }
                }
                else{
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("No se puede borrar");
                    msg.show();
                }
            }
        });
        
        btnModificar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Clientes g = table.getSelectionModel().getSelectedItem();
                if(txtLastName.getText().trim().length() > 0 &&
                        txtFirstName.getText().trim().length() > 0 &&
                        txtCompany.getText().trim().length() > 0 && 
                        txtAddress.getText().trim().length() > 0 &&
                        txtCity.getText().trim().length() > 0 &&
                        txtState.getText().trim().length() > 0 &&
                        txtCountry.getText().trim().length() > 0 &&
                        txtPostalCode.getText().trim().length() > 0 &&
                        txtPhone.getText().trim().length() > 0 &&
                        txtFax.getText().trim().length() > 0 &&
                        txtEmail.getText().trim().length() > 0 &&
                        cmbSupportRepId.getSelectionModel() != null){
                        Clientes temp = (Clientes) cmbSupportRepId.getSelectionModel().getSelectedItem();
                    g.setLastName(txtLastName.getText());
                    g.setFirstName(txtFirstName.getText());
                    g.setCompany(txtCompany.getText());
                    g.setAddress(txtAddress.getText());
                    g.setCity(txtCity.getText());
                    g.setState(txtState.getText());
                    g.setCountry(txtCountry.getText());
                    g.setPostalCode(txtPostalCode.getText());
                    g.setPhone(txtPhone.getText());
                    g.setFax(txtFax.getText());
                    g.setEmail(txtEmail.getText());
                    g.setCustomerId(temp.getCustomerId());
                    if(clientesdao.update(g)){
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Borrar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Empleado modificado correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            table.setItems(clientesdao.findAllObs());
                            actions.setVisible(false);
                        }
                    }
                    else{
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Modificar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("No se puede modificar");
                        msg.show();
                    }
                }
                else{
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Modificar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Hay campos faltantes");
                    msg.show();
                }
            }
        });
    }    
    
}
