package espotifai.fxml;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
public class HomeController implements Initializable {
    @FXML
    MenuItem menuClientes;
    @FXML
    MenuItem menuArtistasAlbums;
    @FXML
    MenuItem menuGeneros;
    @FXML
    MenuItem menuTipoMedios;
    @FXML
    MenuItem menuPistas;
    @FXML
    MenuItem menuCrearLista;
    @FXML
    MenuItem menuComprar;
    @FXML
    MenuItem menuReporteEmpleadso;
    @FXML
    MenuItem menuReportePaises;
    @FXML
    MenuItem menuReporteCanciones;
    @FXML
    MenuItem menuEmpleados;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuClientes.setOnAction(listenerMenuItems);
        menuArtistasAlbums.setOnAction(listenerMenuItems);
        menuGeneros.setOnAction(listenerMenuItems);
        menuTipoMedios.setOnAction(listenerMenuItems);
        menuPistas.setOnAction(listenerMenuItems);
        menuCrearLista.setOnAction(listenerMenuItems);
        menuComprar.setOnAction(listenerMenuItems);
        menuReporteEmpleadso.setOnAction(listenerMenuItems);
        menuReportePaises.setOnAction(listenerMenuItems);
        menuReporteCanciones.setOnAction(listenerMenuItems);
        menuEmpleados.setOnAction(listenerMenuItems);
    }
    EventHandler<ActionEvent> listenerMenuItems = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            if(event.getSource() == menuEmpleados){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource("Empleados.fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(event.getSource() == menuClientes){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource("Clientes.fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*if(event.getSource() == menuArtistasAlbums){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource(".fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            if(event.getSource() == menuGeneros){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource("Genero.fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(event.getSource() == menuTipoMedios){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource("TipoMedio.fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*if(event.getSource() == menuPistas){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource(".fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            /*if(event.getSource() == menuCrearLista){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource(".fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            /*if(event.getSource() == menuComprar){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource(".fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            /*if(event.getSource() == menuReporteEmpleadso){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource(".fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            if(event.getSource() == menuReportePaises){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource("Paises.fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*if(event.getSource() == menuReporteCanciones){
                FXMLLoader loader = new FXMLLoader();
                Parent rootConsultaPersonas = null;
                try {
                    rootConsultaPersonas = loader.load(getClass().getResource(".fxml"));
                    Scene scene = new Scene(rootConsultaPersonas);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
        }
    };
}
