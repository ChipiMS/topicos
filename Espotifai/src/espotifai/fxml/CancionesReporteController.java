/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espotifai.fxml;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import database.MySQL;
import database.dao.TrackDAO;
import espotifai.Album;
import espotifai.Genero;
import espotifai.Paises;
import espotifai.TipoMedio;
import espotifai.Track;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class CancionesReporteController implements Initializable {

    @FXML
    Button btnImprimir;
    @FXML
    TextField dir;
    @FXML
    TextField txtName, txtComposer, txtMilliseconds, txtBytes, txtUnitPrice;
    @FXML
    TableView<Track> table;
    TableColumn TrackIdCol, NameCol, AlbumCol, MediaTypeCol, GenreCol, ComposerCol, MillisecondsCol, BytesCol, UnitPriceCol;
    Boolean agregando = false;
    ObservableList<Track> datos;
    ObservableList datosAlbum, datosTipoMedia, datosGenero;

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

        table.setItems(datos);

        btnImprimir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String adr = null;
                if (dir.getText().trim().length() > 0) {
                    adr = dir.getText().trim() + ".pdf";
                    boolean error = true;
                    try {
                        File file = new File(adr);
                        file.getParentFile().mkdirs();
                        PdfWriter writer = new PdfWriter(adr);
                        PdfDocument pdf = new PdfDocument(writer);
                        Document document = new Document(pdf, new PageSize(PageSize.LETTER));
                        document.add(new Paragraph("Canciones"));
                        Table table = new Table(3);
                        table.addHeaderCell(new Cell().add(new Paragraph("Nombre")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Album")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Tipo de medio")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Genero")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Compositor")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Milisegunfos")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Bytes")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Precio Unitario")));
                        
                        for (int i = 0; i < datos.size(); i++) {
                            Track track = datos.get(i);
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getName())));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getAlbumId() + "")));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getMediaTypeId() + "")));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getGenreId() + "")));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getComposer())));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getMilliseconds() + "")));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getBytes() + "")));
                            table.addHeaderCell(new Cell().add(new Paragraph(track.getUnitPrice() + "")));
                        }
                        document.add(table);
                        document.close();
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Canciones");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Reporte generado con éxito");
                        msg.show();
                        error = false;
                    } catch (IOException ex) {

                    } finally {
                        if (error) {
                            Alert msg = new Alert(Alert.AlertType.INFORMATION);
                            msg.setTitle("Canciones");
                            msg.setHeaderText("Espotifai");
                            msg.setContentText("No se pudo generar el reporte");
                            msg.show();
                        }
                    }
                } else {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Canciones");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Tienes que poner una dirección para el reporte");
                    msg.show();
                }
            }
        });
    }

}
