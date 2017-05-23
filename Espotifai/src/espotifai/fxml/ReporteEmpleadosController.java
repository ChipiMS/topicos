package espotifai.fxml;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import database.MySQL;
import database.dao.EmpleadosDAO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
public class ReporteEmpleadosController implements Initializable {
    @FXML
    TableView tvEmpleados;
    @FXML
    Button btnImprimir;
    @FXML
    TextField dir;
    TableColumn LastNameCol,FirstNameCol,TitleCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,
                FaxCol,EmailCol,ReportsToCol,BirthDateCol,HireDateCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        EmpleadosDAO empleadosdao = new EmpleadosDAO(db.getConnection());
        LastNameCol = new TableColumn("LastName");
        FirstNameCol = new TableColumn("FirstName");
        TitleCol = new TableColumn("Title");
        AddressCol = new TableColumn("Address");
        CityCol = new TableColumn("City");
        StateCol = new TableColumn("State");
        CountryCol = new TableColumn("Country");
        PostalCodeCol = new TableColumn("PostalCode");
        PhoneCol = new TableColumn("Phone");
        FaxCol = new TableColumn("Fax");
        EmailCol = new TableColumn("Email");
        ReportsToCol = new TableColumn("ReportsTo");
        BirthDateCol = new TableColumn("BirthDate");
        HireDateCol = new TableColumn("HireDate");
        LastNameCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        TitleCol.setCellValueFactory(new PropertyValueFactory("Title"));
        AddressCol.setCellValueFactory(new PropertyValueFactory("Address"));
        CityCol.setCellValueFactory(new PropertyValueFactory("City"));
        StateCol.setCellValueFactory(new PropertyValueFactory("State"));
        CountryCol.setCellValueFactory(new PropertyValueFactory("Country"));
        PostalCodeCol.setCellValueFactory(new PropertyValueFactory("PostalCode"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory("Phone"));
        FaxCol.setCellValueFactory(new PropertyValueFactory("Fax"));
        EmailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        ReportsToCol.setCellValueFactory(new PropertyValueFactory("ReportsTo"));
        BirthDateCol.setCellValueFactory(new PropertyValueFactory("BirthDate"));
        HireDateCol.setCellValueFactory(new PropertyValueFactory("HireDate"));
        tvEmpleados.getColumns().addAll(LastNameCol,FirstNameCol,TitleCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,FaxCol,EmailCol,ReportsToCol,BirthDateCol,HireDateCol);
        tvEmpleados.setItems(empleadosdao.findAllObs());
        
        /*btnImprimir.setOnMouseClicked(new EventHandler<MouseEvent>(){
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
                        document.add(new Paragraph("Reporte de Empleados"));
                        Table table = new Table(3);
                        table.addHeaderCell(new Cell().add(new Paragraph("País")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Ventas")));
                        table.addHeaderCell(new Cell().add(new Paragraph("Total")));
            
            }
        });*/
    }    
    
}
