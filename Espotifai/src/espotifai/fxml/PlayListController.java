package espotifai.fxml;

import database.MySQL;
import database.dao.PlayListDAO;
import espotifai.PlayList;
import espotifai.PlayListTrack;
import espotifai.TipoMedio;
import espotifai.Track;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableView<Track> canciones;
    @FXML
    TableView<Track> faltantes;
    Boolean agregando = false;
    TableColumn trackIdCol,albumIdCol,mediaTypeIdCol,genreIdCol,millisecondsCol,bytesCol,nameCol,composerCol,unitPriceCol;
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
                trackIdCol = new TableColumn("trackId");
                albumIdCol = new TableColumn("albumId");
                mediaTypeIdCol = new TableColumn("mediaTypeId");
                genreIdCol = new TableColumn("genreId");
                millisecondsCol = new TableColumn("milliseconds");
                bytesCol = new TableColumn("bytes");
                nameCol = new TableColumn("name");
                composerCol = new TableColumn("composer");
                unitPriceCol = new TableColumn("unitPrice");
                
                trackIdCol.setCellValueFactory(new PropertyValueFactory("trackId"));
                albumIdCol.setCellValueFactory(new PropertyValueFactory("albumId"));
                mediaTypeIdCol.setCellValueFactory(new PropertyValueFactory("mediaTypeId"));
                genreIdCol.setCellValueFactory(new PropertyValueFactory("genreId"));
                millisecondsCol.setCellValueFactory(new PropertyValueFactory("milliseconds"));
                bytesCol.setCellValueFactory(new PropertyValueFactory("bytes"));
                nameCol.setCellValueFactory(new PropertyValueFactory("name"));
                composerCol.setCellValueFactory(new PropertyValueFactory("composer"));
                unitPriceCol.setCellValueFactory(new PropertyValueFactory("unitPrice"));
                agregando = false;
                
                canciones.getColumns().addAll(trackIdCol,albumIdCol,mediaTypeIdCol,genreIdCol,millisecondsCol,bytesCol,nameCol,composerCol,unitPriceCol);
                canciones.setItems(playLdao.findAllCanciones(g.getId()));
                faltantes.getColumns().addAll(trackIdCol,albumIdCol,mediaTypeIdCol,genreIdCol,millisecondsCol,bytesCol,nameCol,composerCol,unitPriceCol);
                faltantes.setItems(playLdao.findAllCancionesFaltantes(g.getId()));
            }
        });
        
    }    
    
}
