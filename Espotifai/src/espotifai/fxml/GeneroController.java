package espotifai.fxml;
import database.MySQL;
import database.dao.GeneroDAO;
import espotifai.Genero;
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
public class GeneroController implements Initializable {
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
    ListView<Genero> list;
    Boolean agregando = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        GeneroDAO generodao = new GeneroDAO(db.getConnection());
        
        list.setItems(generodao.findAll());
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Genero g = list.getSelectionModel().getSelectedItem();
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
                    if(txtNombre.getText().length() > 0){
                        generodao.insert(new Genero(txtNombre.getText()));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            list.setItems(generodao.findAll());
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
                Genero g = list.getSelectionModel().getSelectedItem();
                if(txtNombre.getText().length() > 0){
                    g.setName(txtNombre.getText());
                    if(generodao.update(g)){
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Borrar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Genero modificado correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            list.setItems(generodao.findAll());
                            actions.setVisible(false);
                        }
                    }
                    else{
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Modificar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("No se puede modificar");
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
                Genero g = list.getSelectionModel().getSelectedItem();
                if(generodao.delete(g.getGenreId())){
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Genero borrado correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if(respuesta.get() == ButtonType.OK){
                        list.setItems(generodao.findAll());
                        actions.setVisible(false);
                    }
                }
                else{
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("No se puede borrar");
                }
            }
        });
    }    
}
