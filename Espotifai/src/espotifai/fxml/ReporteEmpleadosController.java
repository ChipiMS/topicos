package espotifai.fxml;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import database.MySQL;
import database.dao.ClientesDAO;
import database.dao.EmpleadosDAO;
import espotifai.Empleados;
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
public class ReporteEmpleadosController implements Initializable {
    @FXML
    TableView tvEmpleados,tvClientes;
    @FXML
    Button btnImprimir;
    @FXML
    TextField dir;
    TableColumn LastNameCol,FirstNameCol,TitleCol,AddressCol,CityCol,StateCol,CountryCol,PostalCodeCol,PhoneCol,
                FaxCol,EmailCol,ReportsToCol,BirthDateCol,HireDateCol;
    TableColumn ClientsCustomerIdCol,ClientsLastNameCol,ClientsFirstNameCol,ClientsCompanyCol,ClientsAddressCol,ClientsCityCol,ClientsStateCol,ClientsCountryCol,ClientsPostalCodeCol,ClientsPhoneCol,ClientsFaxCol,ClientsEmailCol,ClientsSupportRepIdCol;
    ObservableList<Empleados> empleados;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MySQL db = new MySQL();
        db.Connect();
        EmpleadosDAO empleadosdao = new EmpleadosDAO(db.getConnection());
        ClientesDAO clientesdao = new ClientesDAO(db.getConnection());
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
        empleados = empleadosdao.findAllObs();
        tvEmpleados.setItems(empleados);
        
        ClientsCustomerIdCol = new TableColumn("ID");
        ClientsLastNameCol = new TableColumn("LastName");
        ClientsFirstNameCol = new TableColumn("FirstName");
        ClientsCompanyCol = new TableColumn("Company");
        ClientsAddressCol = new TableColumn("Address");
        ClientsCityCol = new TableColumn("City");
        ClientsStateCol = new TableColumn("State");
        ClientsCountryCol = new TableColumn("Country");
        ClientsPostalCodeCol = new TableColumn("PostalCode");
        ClientsPhoneCol = new TableColumn("Phone");
        ClientsFaxCol = new TableColumn("Fax");
        ClientsEmailCol = new TableColumn("Email");
        ClientsSupportRepIdCol = new TableColumn("SupportRepId");
        ClientsCustomerIdCol.setCellValueFactory(new PropertyValueFactory("CustomerId"));
        ClientsLastNameCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        ClientsFirstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        ClientsCompanyCol.setCellValueFactory(new PropertyValueFactory("Company"));
        ClientsAddressCol.setCellValueFactory(new PropertyValueFactory("Address"));
        ClientsCityCol.setCellValueFactory(new PropertyValueFactory("City"));
        ClientsStateCol.setCellValueFactory(new PropertyValueFactory("State"));
        ClientsCountryCol.setCellValueFactory(new PropertyValueFactory("Country"));
        ClientsPostalCodeCol.setCellValueFactory(new PropertyValueFactory("PostalCode"));
        ClientsPhoneCol.setCellValueFactory(new PropertyValueFactory("Phone"));
        ClientsFaxCol.setCellValueFactory(new PropertyValueFactory("Fax"));
        ClientsEmailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        ClientsSupportRepIdCol.setCellValueFactory(new PropertyValueFactory("SupportRepId"));
        tvClientes.getColumns().addAll(ClientsCustomerIdCol,ClientsLastNameCol,ClientsFirstNameCol,ClientsCompanyCol,ClientsAddressCol,ClientsCityCol,ClientsStateCol,ClientsCountryCol,ClientsPostalCodeCol,ClientsPhoneCol,ClientsFaxCol,ClientsEmailCol,ClientsSupportRepIdCol);
        tvClientes.setVisible(false);
        
        tvEmpleados.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Empleados g = (Empleados)tvEmpleados.getSelectionModel().getSelectedItem();
                if(g == null){
                    tvClientes.setVisible(false);
                    return;
                }
                tvClientes.setItems(clientesdao.findAllByEmployee(g.getEmployeeId()));
                tvClientes.setVisible(true);
            }
            
        });
        
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
                        document.add(new Paragraph("Empleados y sus clientes"));
                        for(int j=0;j<empleados.size();j++){
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
