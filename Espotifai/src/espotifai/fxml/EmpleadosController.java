package espotifai.fxml;

import database.MySQL;
import database.dao.EmpleadosDAO;
import espotifai.Empleados;
import espotifai.TipoMedio;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
public class EmpleadosController implements Initializable {
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
        }
    @FXML
    Button btnAgregar;
    @FXML
    Button btnModificar;
    @FXML
    Button btnBorrar;
    @FXML
    GridPane actions;
    @FXML
    TextField txtEmployeeId;
    @FXML
    DatePicker dpBirthDate, dpHireDate;
    @FXML
    TextField txtLastName, txtFirstName, txtTitle, 
              txtAddress, txtCity, txtState, txtCountry, txtPostalCode, txtPhone, 
              txtFax, txtEmail, txtReportsTo;
    @FXML
    TableView<Empleados> table;
    TableColumn LastNameCol,FirstNameCol,TitleCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,
                FaxCol,EmailCol,ReportsToCol,BirthDateCol,HireDateCol;
    Boolean agregando = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    MySQL db = new MySQL();
        db.Connect();
        EmpleadosDAO empleadodao = new EmpleadosDAO(db.getConnection());
        LastNameCol = new TableColumn("LastName");
        FirstNameCol = new TableColumn("FirstName");
        TitleCol = new TableColumn("Title");
        AddressCol = new TableColumn("Address");
        CityCol = new TableColumn("City");
        StateCol = new TableColumn("State");
        CountryCol = new TableColumn("Country");
        PostalCodeCol = new TableColumn("PostalCode");
        PhoneCol = new TableColumn("Phone");
        FaxCol = new TableColumn("Fax");
        EmailCol = new TableColumn("Email");
        ReportsToCol = new TableColumn("ReportsTo");
        BirthDateCol = new TableColumn("BirthDate");
        HireDateCol = new TableColumn("HireDate");
        LastNameCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        TitleCol.setCellValueFactory(new PropertyValueFactory("Title"));
        AddressCol.setCellValueFactory(new PropertyValueFactory("Address"));
        CityCol.setCellValueFactory(new PropertyValueFactory("City"));
        StateCol.setCellValueFactory(new PropertyValueFactory("State"));
        CountryCol.setCellValueFactory(new PropertyValueFactory("Country"));
        PostalCodeCol.setCellValueFactory(new PropertyValueFactory("PostalCode"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory("Phone"));
        FaxCol.setCellValueFactory(new PropertyValueFactory("Fax"));
        EmailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        ReportsToCol.setCellValueFactory(new PropertyValueFactory("ReportsTo"));
        BirthDateCol.setCellValueFactory(new PropertyValueFactory("BirthDate"));
        HireDateCol.setCellValueFactory(new PropertyValueFactory("HireDate"));
        table.getColumns().addAll(LastNameCol,FirstNameCol,TitleCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,FaxCol,EmailCol,ReportsToCol,BirthDateCol,HireDateCol);
        table.setItems(empleadodao.findAllObs());
        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Empleados g = table.getSelectionModel().getSelectedItem();
                btnModificar.setDisable(false);
                btnBorrar.setDisable(false);
                txtLastName.setText(g.getLastName());
                txtFirstName.setText(g.getFirstName());
                txtTitle.setText(g.getTitle());
                txtAddress.setText(g.getAddress());
                txtCity.setText(g.getCity());
                txtState.setText(g.getState());
                txtCountry.setText(g.getCountry());
                txtPostalCode.setText(g.getPostalCode());
                txtPhone.setText(g.getPhone());
                txtFax.setText(g.getFax());
                txtEmail.setText(g.getEmail());
                txtReportsTo.setText(Integer.toString(g.getReportsTo()));
                dpBirthDate.setValue(LOCAL_DATE(g.getBirthDate().toString()));
                dpHireDate.setValue(LOCAL_DATE(g.getHireDate().toString()));
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
                        txtTitle.getText().trim().length() > 0 && 
                        txtAddress.getText().trim().length() > 0 &&
                        txtCity.getText().trim().length() > 0 &&
                        txtState.getText().trim().length() > 0 &&
                        txtCountry.getText().trim().length() > 0 &&
                        txtPostalCode.getText().trim().length() > 0 &&
                        txtPhone.getText().trim().length() > 0 &&
                        txtFax.getText().trim().length() > 0 &&
                        txtEmail.getText().trim().length() > 0 &&
                        txtReportsTo.getText().trim().length() > 0 &&
                        dpBirthDate.getValue() != null &&
                        dpHireDate.getValue() != null)
                    {
                        Date HireDate = Date.valueOf(dpHireDate.getValue());
                        Date BirthDate = Date.valueOf(dpBirthDate.getValue());
                        empleadodao.insert(new Empleados(
                        txtLastName.getText(),
                        txtFirstName.getText(),
                        txtTitle.getText(), 
                        txtAddress.getText(),
                        txtCity.getText(),
                        txtState.getText(),
                        txtCountry.getText(),
                        txtPostalCode.getText(),
                        txtPhone.getText(),
                        txtFax.getText(),
                        txtEmail.getText(),
                        Integer.valueOf(txtReportsTo.getText()),HireDate,BirthDate));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            table.setItems(empleadodao.findAllObs());
                            agregando = false;
                            actions.setVisible(false);
                        
                    }}
                    else{
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Hay que dar un nombre");
                        msg.show();
                        
                    }
            
                }
                else{
                    btnModificar.setDisable(true);
                    btnBorrar.setDisable(true);
                    txtLastName.setText("");
                    txtFirstName.setText("");
                    txtTitle.setText(""); 
                    txtAddress.setText("");
                    txtCity.setText("");
                    txtState.setText("");
                    txtCountry.setText("");
                    txtPostalCode.setText("");
                    txtPhone.setText("");
                    txtFax.setText("");
                    txtEmail.setText("");
                    txtReportsTo.setText("");
                    dpHireDate.setValue(null);
                    dpBirthDate.setValue(null);
                    actions.setVisible(true);
                    agregando = true;
                }
            }
        });
        btnBorrar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Empleados g = table.getSelectionModel().getSelectedItem();
                if(empleadodao.delete(g.getEmployeeId())){
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Empleado borrado correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if(respuesta.get() == ButtonType.OK){
                        table.setItems(empleadodao.findAllObs());
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
    }    
    
}
