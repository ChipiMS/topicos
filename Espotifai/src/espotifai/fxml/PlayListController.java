package espotifai.fxml;

import database.MySQL;
import database.dao.PlayListDAO;
import espotifai.PlayList;
import espotifai.PlayListTrack;
import espotifai.TipoMedio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PlayListController implements Initializable {
    @FXML
    Button btnAgregar;
    @FXML
    Button btnNueva;
    @FXML
    Button btnBorrar;
    @FXML
    HBox actions;
    @FXML
    ComboBox cmbSupportRepId;
    @FXML
    TextField txtNombre;
    @FXML
    ListView<PlayList> listas;
    @FXML
    ListView<PlayListTrack> canciones;
    Boolean agregando = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        PlayListDAO playLdao = new PlayListDAO(db.getConnection());
        
        listas.setItems(playLdao.findAllPlayList());
        
        listas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                PlayList g = listas.getSelectionModel().getSelectedItem();
                btnBorrar.setDisable(false);
                canciones.setItems(playLdao.findAllCanciones(g.getId())); 
                agregando = false;
            }
        });
        
    }    
    
}
