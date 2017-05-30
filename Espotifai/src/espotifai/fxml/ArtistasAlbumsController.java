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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ELÍAS MANCERA
 */
public class ArtistasAlbumsController implements Initializable {
    
    @FXML
    TextField txtNombre;
    @FXML
    TableView<Artist> tvArtist;
    @FXML
    TableView<Album> tvAlbums;
    TableColumn IdArtista, nombre, IdAlbum, titulo, idArtista;
    
    ObservableList datosArtist, datosAlbum;
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
        
        tvArtist.getColumns().addAll(IdArtista,nombre);
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
        
        tvAlbums.getColumns().addAll(IdAlbum,titulo,idArtista);
        datosAlbum = albumdao.findAll();
        
        tvAlbums.setItems(datosAlbum);
        
    }    
    
}
