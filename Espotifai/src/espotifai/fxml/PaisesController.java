package espotifai.fxml;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import database.MySQL;
import database.dao.PaisesDAO;
import espotifai.Paises;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
public class PaisesController implements Initializable {
    @FXML
    TableView tvPaises;
    @FXML
    Button btnImprimir;
    @FXML
    TextField dir;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        PaisesDAO paisesdao = new PaisesDAO(db.getConnection());
        ObservableList<Paises> paises = paisesdao.findAll();
        
        TableColumn nombres = new TableColumn("País");
        nombres.setCellValueFactory(new PropertyValueFactory("Name"));
        
        TableColumn compras = new TableColumn("Número de compras");
        compras.setCellValueFactory(new PropertyValueFactory("Invoices"));
        
        TableColumn totales = new TableColumn("Total");
        totales.setCellValueFactory(new PropertyValueFactory("Total"));
        
        tvPaises.getColumns().addAll(nombres,compras,totales);
        
        tvPaises.setItems(paises);
        
        btnImprimir.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String adr = null;
                if(dir.getText().trim().length() > 0){
                    adr = dir.getText().trim()+".pdf";
                    boolean error = true;
                    try {
                        File file = new File(adr);
                        file.getParentFile().mkdirs();
                        PdfWriter writer = new PdfWriter(adr);
                        PdfDocument pdf = new PdfDocument(writer);
                        Document document = new Document(pdf,new PageSize(PageSize.LETTER));
                        document.add(new Paragraph("Ventas por países"));
                        Table table = new Table(3);
                        table.addHeaderCell(new Cell().add(new Paragraph("País")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Ventas")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Total")));
                        for(int i=0;i<paises.size();i++){
                            Paises pais = paises.get(i);
                            table.addHeaderCell(new Cell().add(new Paragraph(pais.getName())));
                            table.addHeaderCell(new Cell().add(new Paragraph(pais.getInvoices())));
                            table.addHeaderCell(new Cell().add(new Paragraph(pais.getTotal())));
                        }
                        document.add(table);
                        document.close();
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Paises");
                        msg.setHeaderText("Espotifai");
                        msg.setContentText("Reporte generado con éxito");
                        msg.show();
                        error = false;
                    } catch (IOException ex) {
                        
                    }
                    finally{
                        if(error){
                            Alert msg = new Alert(Alert.AlertType.INFORMATION);
                            msg.setTitle("Paises");
                            msg.setHeaderText("Espotifai");
                            msg.setContentText("No se pudo generar el reporte");
                            msg.show();
                        }
                    }
                }
                else{
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Paises");
                    msg.setHeaderText("Espotifai");
                    msg.setContentText("Tienes que poner una dirección para el reporte");
                    msg.show();
                }
            }
        });
    }    
}
