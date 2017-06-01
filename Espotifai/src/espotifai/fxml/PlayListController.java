package espotifai.fxml;

import database.MySQL;
import database.dao.PlayListDAO;
import espotifai.Clientes;
import espotifai.PlayList;
import espotifai.PlayListTrack;
import espotifai.TipoMedio;
import espotifai.Track;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    Button btnBorrarLis;
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
    int pl;
    Boolean agregando = false, borrar;
    TableColumn trackIdCol,albumIdCol,mediaTypeIdCol,genreIdCol,millisecondsCol,bytesCol,nameCol,composerCol,unitPriceCol,
            trackIdColFal,albumIdColFal,mediaTypeIdColFal,genreIdColFal,millisecondsColFal,bytesColFal,nameColFal,composerColFal,unitPriceColFal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        PlayListDAO playLdao = new PlayListDAO(db.getConnection());
        
        listas.setItems(playLdao.findAllPlayList());
        
        trackIdColFal = new TableColumn("trackId");
                albumIdColFal = new TableColumn("albumId");
                mediaTypeIdColFal = new TableColumn("mediaTypeId");
                genreIdColFal = new TableColumn("genreId");
                millisecondsColFal = new TableColumn("milliseconds");
                bytesColFal = new TableColumn("bytes");
                nameColFal = new TableColumn("name");
                composerColFal = new TableColumn("composer");
                unitPriceColFal = new TableColumn("unitPrice");
                
                trackIdColFal.setCellValueFactory(new PropertyValueFactory("trackId"));
                albumIdColFal.setCellValueFactory(new PropertyValueFactory("albumId"));
                mediaTypeIdColFal.setCellValueFactory(new PropertyValueFactory("mediaTypeId"));
                genreIdColFal.setCellValueFactory(new PropertyValueFactory("genreId"));
                millisecondsColFal.setCellValueFactory(new PropertyValueFactory("milliseconds"));
                bytesColFal.setCellValueFactory(new PropertyValueFactory("bytes"));
                nameColFal.setCellValueFactory(new PropertyValueFactory("name"));
                composerColFal.setCellValueFactory(new PropertyValueFactory("composer"));
                unitPriceColFal.setCellValueFactory(new PropertyValueFactory("unitPrice"));
                faltantes.getColumns().addAll(trackIdColFal,albumIdColFal,mediaTypeIdColFal,genreIdColFal,millisecondsColFal,bytesColFal,nameColFal,composerColFal,unitPriceColFal);

        
        listas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                canciones.getColumns().clear();
                faltantes.getColumns().clear();
                PlayList g = listas.getSelectionModel().getSelectedItem();
                pl = g.getId();
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
                
                trackIdColFal.setCellValueFactory(new PropertyValueFactory("trackId"));
                albumIdColFal.setCellValueFactory(new PropertyValueFactory("albumId"));
                mediaTypeIdColFal.setCellValueFactory(new PropertyValueFactory("mediaTypeId"));
                genreIdColFal.setCellValueFactory(new PropertyValueFactory("genreId"));
                millisecondsColFal.setCellValueFactory(new PropertyValueFactory("milliseconds"));
                bytesColFal.setCellValueFactory(new PropertyValueFactory("bytes"));
                nameColFal.setCellValueFactory(new PropertyValueFactory("name"));
                composerColFal.setCellValueFactory(new PropertyValueFactory("composer"));
                unitPriceColFal.setCellValueFactory(new PropertyValueFactory("unitPrice"));
                canciones.getColumns().addAll(trackIdCol,albumIdCol,mediaTypeIdCol,genreIdCol,millisecondsCol,bytesCol,nameCol,composerCol,unitPriceCol);
                canciones.setItems(playLdao.findAllCanciones(g.getId()));
                faltantes.getColumns().addAll(trackIdColFal,albumIdColFal,mediaTypeIdColFal,genreIdColFal,millisecondsColFal,bytesColFal,nameColFal,composerColFal,unitPriceColFal);

                faltantes.setItems(playLdao.findAllCancionesFaltantes(g.getId()));
                btnBorrarLis.setVisible(true);
            }
        });
         btnNueva.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(btnNueva.getText().equals("Nueva PlayList")){
                    btnNueva.setText("Crear");
                    actions.setVisible(true);
                }
                if(btnNueva.getText().equals("Crear")){
                    if(agregando){
                    if(txtNombre.getText().trim().length() > 0){
                        playLdao.insert(new PlayList(txtNombre.getText()));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("PlayList creada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            listas.setItems(playLdao.findAllPlayList());
                            agregando = false;
                            actions.setVisible(false);
                            btnNueva.setText("Nueva PlayList");
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
                    txtNombre.setText("");
                    actions.setVisible(true);
                    agregando = true;
                }
            }
            }
        });
        btnBorrarLis.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                PlayList g = listas.getSelectionModel().getSelectedItem();
                if(playLdao.delete(g.getId())){
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("PlayList borrada correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if(respuesta.get() == ButtonType.OK){
                        listas.setItems(playLdao.findAllPlayList());
                        agregando = false;
                        btnBorrarLis.setVisible(false);
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
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Track g = faltantes.getSelectionModel().getSelectedItem();
                if(agregando){
                        playLdao.insertTrack(new PlayListTrack(pl, g.getTrackId()));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if(respuesta.get() == ButtonType.OK){
                            canciones.setItems(playLdao.findAllCanciones(pl));
                            faltantes.setItems(playLdao.findAllCancionesFaltantes(pl));
                            agregando = false;
                            actions.setVisible(false);
                        }
                    }
                else{
                    agregando = true;
                }
            }
        });
        btnBorrar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Track g = canciones.getSelectionModel().getSelectedItem();
                if(playLdao.deleteTrack(g.getTrackId())){
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Cancion borrada correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if(respuesta.get() == ButtonType.OK){
                        listas.setItems(playLdao.findAllPlayList());
                        canciones.setItems(playLdao.findAllCanciones(pl));
                        faltantes.setItems(playLdao.findAllCancionesFaltantes(pl));
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
