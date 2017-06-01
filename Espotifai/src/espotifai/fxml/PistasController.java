/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espotifai.fxml;

import database.MySQL;
import database.dao.TrackDAO;
import database.dao.AlbumDAO;
import database.dao.GeneroDAO;
import database.dao.TipoMedioDAO;
import espotifai.Track;
import espotifai.Album;
import espotifai.Genero;
import espotifai.TipoMedio;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ELÍAS MANCERA
 */
public class PistasController implements Initializable {

    @FXML
    Button btnAgregar;
    @FXML
    Button btnModificar;
    @FXML
    Button btnBorrar;
    @FXML
    GridPane actions;
    @FXML
    ComboBox<Album> cmbAlbum;
    @FXML
    ComboBox<TipoMedio> cmbMediaType;
    @FXML
    ComboBox<Genero> cmbGenre;
    @FXML
    TextField txtName, txtComposer, txtMilliseconds, txtBytes, txtUnitPrice;
    @FXML
    TableView<Track> table;
    TableColumn TrackIdCol, NameCol, AlbumCol, MediaTypeCol, GenreCol, ComposerCol, MillisecondsCol, BytesCol, UnitPriceCol;
    Boolean agregando = false;
    ObservableList datos;
    ObservableList datosAlbum, datosTipoMedia, datosGenero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        TrackDAO tracksdao = new TrackDAO(db.getConnection());
        AlbumDAO albumdao = new AlbumDAO(db.getConnection());
        TipoMedioDAO mediatypedao = new TipoMedioDAO(db.getConnection());
        GeneroDAO genredao = new GeneroDAO(db.getConnection());
        TrackIdCol = new TableColumn("ID");
        NameCol = new TableColumn("Nombre");
        AlbumCol = new TableColumn("Album");
        MediaTypeCol = new TableColumn("Tipo de media");
        GenreCol = new TableColumn("Genero");
        ComposerCol = new TableColumn("Compositor");
        MillisecondsCol = new TableColumn("Milisegundos");
        BytesCol = new TableColumn("Bytes");
        UnitPriceCol = new TableColumn("Precio Unitario");

        TrackIdCol.setCellValueFactory(new PropertyValueFactory("TrackId"));
        NameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        AlbumCol.setCellValueFactory(new PropertyValueFactory("AlbumId"));
        MediaTypeCol.setCellValueFactory(new PropertyValueFactory("MediaTypeId"));
        GenreCol.setCellValueFactory(new PropertyValueFactory("GenreId"));
        ComposerCol.setCellValueFactory(new PropertyValueFactory("Composer"));
        MillisecondsCol.setCellValueFactory(new PropertyValueFactory("Milliseconds"));
        BytesCol.setCellValueFactory(new PropertyValueFactory("Bytes"));
        UnitPriceCol.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        table.getColumns().addAll(TrackIdCol, NameCol, AlbumCol, MediaTypeCol, GenreCol, ComposerCol, MillisecondsCol, BytesCol, UnitPriceCol);
        datos = tracksdao.findAll();
        
        datosAlbum = albumdao.findAll();
        cmbAlbum.setItems(datosAlbum);
        
        datosTipoMedia = mediatypedao.findAll();
        cmbMediaType.setItems(datosTipoMedia);
        
        datosGenero = genredao.findAll();
        cmbGenre.setItems(datosGenero);
        
        table.setItems(datos);

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                datos = tracksdao.findAll();
                Track g = table.getSelectionModel().getSelectedItem();
                btnModificar.setDisable(false);
                btnBorrar.setDisable(false);
                txtName.setText(g.getName());
                //txtAlbum.setText(g.getAlbumId());
                //txtMediaType.setText(g.getMediaTypeId());
                //txtGenre.setText(g.getGenre());
                txtComposer.setText(g.getComposer());
                txtMilliseconds.setText(g.getMilliseconds() + "");
                txtBytes.setText(g.getBytes() + "");
                txtUnitPrice.setText(g.getUnitPrice() + "");
                for (int i = 0; i < datosAlbum.size(); i++) {
                    Album album = (Album) datosAlbum.get(i);
                    if (g.getAlbumId() == album.getAlbumId()) {
                        cmbAlbum.getSelectionModel().clearAndSelect(i);
                    }
                }
                for (int i = 0; i < datosTipoMedia.size(); i++) {
                    TipoMedio tipoMedio = (TipoMedio) datosTipoMedia.get(i);
                    if (g.getMediaTypeId() == tipoMedio.getMediaTypeId()) {
                        cmbMediaType.getSelectionModel().clearAndSelect(i);
                    }
                }
                for (int i = 0; i < datosGenero.size(); i++) {
                    Genero genero = (Genero) datosGenero.get(i);
                    if (g.getMediaTypeId() == genero.getGenreId()) {
                        cmbGenre.getSelectionModel().clearAndSelect(i);
                    }
                }
                actions.setVisible(true);
                agregando = false;
            }
        });

        btnAgregar.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event
                    ) {
                        if (agregando) {
                            if (txtName.getText().trim().length() > 0
                            && txtComposer.getText().trim().length() > 0
                            && txtBytes.getText().trim().length() > 0
                            && cmbAlbum.getSelectionModel() != null
                            && cmbMediaType.getSelectionModel() != null
                            && cmbGenre.getSelectionModel() != null) {
                                tracksdao.insert(new Track(
                                                cmbAlbum.getSelectionModel().getSelectedItem().getAlbumId(),
                                                cmbMediaType.getSelectionModel().getSelectedItem().getMediaTypeId(),
                                                cmbGenre.getSelectionModel().getSelectedItem().getGenreId(),
                                                Integer.parseInt(txtMilliseconds.getText()),
                                                Integer.parseInt(txtBytes.getText()),
                                                txtName.getText(),
                                                txtComposer.getText(),
                                                Double.parseDouble(txtUnitPrice.getText())
                                        ));
                                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                msg.setTitle("Guardar");
                                msg.setHeaderText("Espotifai");
                                msg.setContentText("Información guardada correctamente");
                                Optional<ButtonType> respuesta = msg.showAndWait();
                                if (respuesta.get() == ButtonType.OK) {
                                    datos = tracksdao.findAll();
                                    table.setItems(datos);
                                    agregando = false;
                                    actions.setVisible(false);
                                }
                            } else {
                                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                msg.setTitle("Guardar");
                                msg.setHeaderText("Espotifai");
                                msg.setContentText("Hay campos obligatorios faltantes");
                                msg.show();

                            }

                        } else {
                            btnModificar.setDisable(true);
                            btnBorrar.setDisable(true);
                            txtName.setText("");
                            txtComposer.setText("");
                            txtMilliseconds.setText("");
                            txtBytes.setText("");
                            txtUnitPrice.setText("");
                            cmbAlbum.getSelectionModel().clearSelection();
                            cmbMediaType.getSelectionModel().clearSelection();
                            cmbGenre.getSelectionModel().clearSelection();
                            actions.setVisible(true);
                            agregando = true;
                        }
                    }
                }
        );
        btnBorrar.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event
                    ) {
                        Track g = table.getSelectionModel().getSelectedItem();
                        if (tracksdao.delete(g.getTrackId())) {
                            Alert msg = new Alert(Alert.AlertType.INFORMATION);
                            msg.setTitle("Borrar");
                            msg.setHeaderText("Espotifai");
                            msg.setContentText("Album borrado correctamente");
                            Optional<ButtonType> respuesta = msg.showAndWait();
                            if (respuesta.get() == ButtonType.OK) {
                                datos = tracksdao.findAll();
                                table.setItems(datos);
                                agregando = false;
                                actions.setVisible(false);
                            }
                        } else {
                            Alert msg = new Alert(Alert.AlertType.INFORMATION);
                            msg.setTitle("Borrar");
                            msg.setHeaderText("Espotifai");
                            msg.setContentText("No se puede borrar");
                            msg.show();
                        }
                    }
                }
        );

        btnModificar.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event
                    ) {
                        Track g = table.getSelectionModel().getSelectedItem();
                        if (txtName.getText().trim().length() > 0
                        && txtComposer.getText().trim().length() > 0
                        && txtBytes.getText().trim().length() > 0
                        && cmbAlbum.getSelectionModel() != null
                        && cmbMediaType.getSelectionModel() != null
                        && cmbGenre.getSelectionModel() != null) {

                            g.setName(txtName.getText());
                            g.setAlbumId(cmbAlbum.getSelectionModel().getSelectedItem().getAlbumId());
                            g.setMediaTypeId(cmbMediaType.getSelectionModel().getSelectedItem().getMediaTypeId());
                            g.setGenreId(cmbGenre.getSelectionModel().getSelectedItem().getGenreId());
                            g.setComposer(txtComposer.getText());
                            g.setMilliseconds(Integer.parseInt(txtMilliseconds.getText()));
                            g.setBytes(Integer.parseInt(txtBytes.getText()));
                            g.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
                            if (tracksdao.update(g)) {
                                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                msg.setTitle("Borrar");
                                msg.setHeaderText("Espotifai");
                                msg.setContentText("Pista modificada correctamente");
                                Optional<ButtonType> respuesta = msg.showAndWait();
                                if (respuesta.get() == ButtonType.OK) {
                                    table.setItems(tracksdao.findAll());
                                    actions.setVisible(false);
                                }
                            } else {
                                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                msg.setTitle("Modificar");
                                msg.setHeaderText("Espotifai");
                                msg.setContentText("No se puede modificar");
                                msg.show();
                            }
                        } else {
                            Alert msg = new Alert(Alert.AlertType.INFORMATION);
                            msg.setTitle("Modificar");
                            msg.setHeaderText("Espotifai");
                            msg.setContentText("Hay campos obligatorios faltantes");
                            msg.show();
                        }
                    }
                }
        );
    }

}
