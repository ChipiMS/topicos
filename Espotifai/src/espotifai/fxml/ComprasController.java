/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espotifai.fxml;

import database.MySQL;
import database.dao.AlbumDAO;
import database.dao.GeneroDAO;
import database.dao.TipoMedioDAO;
import database.dao.TrackDAO;
import espotifai.Track;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ELÍAS MANCERA
 */
public class ComprasController implements Initializable {

    @FXML
    Button btnAgregar;
    @FXML
    TableView<Track> tvTrackList, tvTrackAdd;
    TableColumn TrackIdCol, NameCol, AlbumCol, MediaTypeCol, GenreCol, ComposerCol, MillisecondsCol, BytesCol, UnitPriceCol;

    ObservableList datos, datosAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        TrackDAO tracksdao = new TrackDAO(db.getConnection());
        TrackIdCol = new TableColumn("ID");
        NameCol = new TableColumn("Nombre");
        AlbumCol = new TableColumn("Album");
        MediaTypeCol = new TableColumn("Tipo de media");
        GenreCol = new TableColumn("Genero");
        ComposerCol = new TableColumn("Compositor");
        MillisecondsCol = new TableColumn("Milisegundos");
        BytesCol = new TableColumn("Bytes");
        UnitPriceCol = new TableColumn("Precio Unitario");
        tvTrackAdd.getColumns().addAll(TrackIdCol, NameCol, AlbumCol, MediaTypeCol, GenreCol, ComposerCol, MillisecondsCol, BytesCol, UnitPriceCol);

        TrackIdCol.setCellValueFactory(new PropertyValueFactory("TrackId"));
        NameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        AlbumCol.setCellValueFactory(new PropertyValueFactory("AlbumId"));
        MediaTypeCol.setCellValueFactory(new PropertyValueFactory("MediaTypeId"));
        GenreCol.setCellValueFactory(new PropertyValueFactory("GenreId"));
        ComposerCol.setCellValueFactory(new PropertyValueFactory("Composer"));
        MillisecondsCol.setCellValueFactory(new PropertyValueFactory("Milliseconds"));
        BytesCol.setCellValueFactory(new PropertyValueFactory("Bytes"));
        UnitPriceCol.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        tvTrackList.getColumns().addAll(TrackIdCol, NameCol, AlbumCol, MediaTypeCol, GenreCol, ComposerCol, MillisecondsCol, BytesCol, UnitPriceCol);

        datos = tracksdao.findAll();

        tvTrackList.setItems(datos);
        
        //tvTrackAdd.setItems(datosAdd);

        tvTrackList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Track g = tvTrackList.getSelectionModel().getSelectedItem();
                if (g == null) {
                    btnAgregar.setDisable(true);
                    return;
                }
                btnAgregar.setDisable(false);
            }
        });

        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Track g = tvTrackList.getSelectionModel().getSelectedItem();
                tvTrackAdd.getItems().add(g);
//datosAdd.add(g);
            }
        });
    }
}
