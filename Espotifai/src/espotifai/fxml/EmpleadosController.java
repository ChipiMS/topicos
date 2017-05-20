package espotifai.fxml;

import database.MySQL;
import database.dao.EmpleadosDAO;
import espotifai.Empleados;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
public class EmpleadosController implements Initializable {
    @FXML
    Button btnAgregar;
    @FXML
    Button btnModificar;
    @FXML
    Button btnBorrar;
    @FXML
    GridPane actions;
    @FXML
    TextField txtEmployeeId, txtLastName, txtFirstName, txtTitle, 
              txtAddress, txtCity, txtState, txtCountry, txtPostalCode, txtPhone, 
              txtFax, txtEmail, txtReportsTo, txtBirthDay, txtHireDate;
    @FXML
    TableView<Empleados> table;
    Boolean agregando = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    MySQL db = new MySQL();
        db.Connect();
        EmpleadosDAO empleadodao = new EmpleadosDAO(db.getConnection());
        table.setItems(empleadodao.findAll());
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
                txtBirthDay.setText(g.getBirthDay().toString());
                txtHireDate.setText(g.getHireDate().toString());
                actions.setVisible(true);
                agregando = false;
            }
        });
    }    
    
}
