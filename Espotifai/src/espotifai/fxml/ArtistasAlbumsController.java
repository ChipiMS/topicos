/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espotifai.fxml;

import database.MySQL;
import database.dao.AlbumDAO;
import database.dao.ArtistaDAO;
import espotifai.Album;
import espotifai.Artist;
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
public class ArtistasAlbumsController implements Initializable {
    
    @FXML
    TextField txtNombreAr, txtTitle, txtIdArtist;
    @FXML
    TableView<Artist> tvArtist;
    @FXML
    TableView<Album> tvAlbums;
    TableColumn IdArtista, nombre, IdAlbum, titulo, idArtista;
    
    ObservableList datosArtist, datosAlbum;
    
    @FXML
    Button btnAgregarAr;
    @FXML
    Button btnModificarAr;
    @FXML
    Button btnBorrarAr;
    @FXML
    GridPane actions1;
    
    @FXML
    Button btnAgregarAl;
    @FXML
    Button btnModificarAl;
    @FXML
    Button btnBorrarAl;
    @FXML
    GridPane actions2;
    
    Boolean agregando = false, agregando2 = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();

        //Diseño Artistas
        ArtistaDAO artistdao = new ArtistaDAO(db.getConnection());
        IdArtista = new TableColumn("Id Artista");
        nombre = new TableColumn("Nombre");
        
        IdArtista.setCellValueFactory(new PropertyValueFactory("ArtistId"));
        nombre.setCellValueFactory(new PropertyValueFactory("name"));
        
        tvArtist.getColumns().addAll(IdArtista, nombre);
        datosArtist = artistdao.findAll();
        
        tvArtist.setItems(datosArtist);

        //Diseño Albums
        AlbumDAO albumdao = new AlbumDAO(db.getConnection());
        IdAlbum = new TableColumn("Id Album");
        titulo = new TableColumn("Titulo");
        idArtista = new TableColumn("Id Artista");
        
        IdAlbum.setCellValueFactory(new PropertyValueFactory("AlbumId"));
        titulo.setCellValueFactory(new PropertyValueFactory("Title"));
        idArtista.setCellValueFactory(new PropertyValueFactory("ArtistId"));
        
        tvAlbums.getColumns().addAll(IdAlbum, titulo, idArtista);
        datosAlbum = albumdao.findAll();
        
        tvAlbums.setItems(datosAlbum);
        
        tvArtist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Artist g = tvArtist.getSelectionModel().getSelectedItem();
                if (g == null) {
                    actions1.setVisible(false);
                    agregando = false;
                    return;
                }
                btnModificarAr.setDisable(false);
                btnBorrarAr.setDisable(false);
                txtNombreAr.setText(g.getName());
                txtIdArtist.setText(g.getArtistId() + "");
                actions1.setVisible(true);
                agregando = false;
            }
        });
        
        btnAgregarAr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (agregando) {
                    if (txtNombreAr.getText().trim().length() > 0) {
                        artistdao.insert(new Artist(
                                txtNombreAr.getText()));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if (respuesta.get() == ButtonType.OK) {
                            datosArtist = artistdao.findAll();
                            tvArtist.setItems(datosArtist);
                            agregando = false;
                            actions1.setVisible(false);
                        }
                    } else {
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Hay campos obligatorios faltantes");
                        msg.show();
                    }
                } else {
                    btnModificarAr.setDisable(true);
                    btnBorrarAr.setDisable(true);
                    txtNombreAr.setText("");
                    actions1.setVisible(true);
                    agregando = true;
                }
            }
        });
        
        btnModificarAr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Artist g = tvArtist.getSelectionModel().getSelectedItem();
                if (txtNombreAr.getText().trim().length() > 0) {
                    g.setName(txtNombreAr.getText());
                    if (artistdao.update(g)) {
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Borrar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Artista modificado correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if (respuesta.get() == ButtonType.OK) {
                            tvArtist.setItems(artistdao.findAll());
                            actions1.setVisible(false);
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
                    msg.setContentText("Hay que dar un nombre");
                    msg.show();
                }
            }
        });
        btnBorrarAr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Artist g = tvArtist.getSelectionModel().getSelectedItem();
                if (artistdao.delete(g.getArtistId())) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Artista borrado correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if (respuesta.get() == ButtonType.OK) {
                        tvArtist.setItems(artistdao.findAll());
                        actions1.setVisible(false);
                    }
                } else {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("No se puede borrar");
                    msg.show();
                }
            }
        });

        //Diseño Albums
        tvAlbums.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Album g = tvAlbums.getSelectionModel().getSelectedItem();
                if (g == null) {
                    actions2.setVisible(false);
                    agregando2 = false;
                    return;
                }
                btnModificarAl.setDisable(false);
                btnBorrarAl.setDisable(false);
                txtTitle.setText(g.getTitle());
                txtIdArtist.setText(g.getArtistId() + "");
                actions2.setVisible(true);
                agregando2 = false;
            }
        });
        
        btnAgregarAl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (agregando2) {
                    if (txtTitle.getText().trim().length() > 0 && txtIdArtist.getText().trim().length() > 0) {
                        albumdao.insert(new Album(
                                txtTitle.getText(), Integer.parseInt(txtIdArtist.getText())));
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Información guardada correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if (respuesta.get() == ButtonType.OK) {
                            datosAlbum = albumdao.findAll();
                            tvAlbums.setItems(datosAlbum);
                            agregando2 = false;
                            actions2.setVisible(false);
                        }
                    } else {
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Guardar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Hay campos obligatorios faltantes");
                        msg.show();
                    }
                } else {
                    btnModificarAl.setDisable(true);
                    btnBorrarAl.setDisable(true);
                    txtTitle.setText("");
                    actions2.setVisible(true);
                    agregando2 = true;
                }
            }
        });
        
        btnModificarAl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Album g = tvAlbums.getSelectionModel().getSelectedItem();
                if (txtTitle.getText().trim().length() > 0 && txtIdArtist.getText().trim().length() > 0) {
                    g.setTitle(txtTitle.getText());
                    g.setArtistId(Integer.parseInt(txtIdArtist.getText()));
                    if (albumdao.update(g)) {
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Borrar");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Album modificado correctamente");
                        Optional<ButtonType> respuesta = msg.showAndWait();
                        if (respuesta.get() == ButtonType.OK) {
                            tvAlbums.setItems(albumdao.findAll());
                            actions2.setVisible(false);
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
                    msg.setContentText("Hay que dar un nombre");
                    msg.show();
                }
            }
        });
        btnBorrarAl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Album g = tvAlbums.getSelectionModel().getSelectedItem();
                if (albumdao.delete(g.getAlbumId())) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Borrar");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Album borrado correctamente");
                    Optional<ButtonType> respuesta = msg.showAndWait();
                    if (respuesta.get() == ButtonType.OK) {
                        tvAlbums.setItems(albumdao.findAll());
                        actions2.setVisible(false);
                    }
                } else {
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
