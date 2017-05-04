package espotifai.fxml;
import database.MySQL;
import database.dao.TipoMedioDAO;
import espotifai.TipoMedio;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
public class TipoMedioController implements Initializable {
    @FXML
    Button btnAgregar;
    @FXML
    Button btnModificar;
    @FXML
    Button btnBorrar;
    @FXML
    HBox actions;
    @FXML
    TextField txtNombre;
    @FXML
    ListView<TipoMedio> list;
    Boolean agregando = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        TipoMedioDAO madiatypedao = new TipoMedioDAO(db.getConnection());
        
        list.setItems(madiatypedao.findAll());
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TipoMedio g = list.getSelectionModel().getSelectedItem();
                btnModificar.setDisable(false);
                btnBorrar.setDisable(false);
                txtNombre.setText(g.getName());
                actions.setVisible(true);
                agregando = false;
            }
        });
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(agregando){
                    if(txtNombre.getText().trim().length() > 0){
                        madiatypedao.insert(new TipoMedio(txtNombre.getText()));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            list.setItems(madiatypedao.findAll());
                            agregando = false;
                            actions.setVisible(false);
                        }
                    }
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
                    txtNombre.setText("");
                    actions.setVisible(true);
                    agregando = true;
                }
            }
        });
        btnModificar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TipoMedio g = list.getSelectionModel().getSelectedItem();
                if(txtNombre.getText().trim().length() > 0){
                    g.setName(txtNombre.getText());
                    if(madiatypedao.update(g)){
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Borrar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Tipo de medio modificado correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            list.setItems(madiatypedao.findAll());
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
                    msg.setContentText("Hay que dar un nombre");
                    msg.show();
                }
            }
        });
        btnBorrar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                TipoMedio g = list.getSelectionModel().getSelectedItem();
                if(madiatypedao.delete(g.getMediaTypeId())){
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Tipo de medio borrado correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if(respuesta.get() == ButtonType.OK){
                        list.setItems(madiatypedao.findAll());
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
