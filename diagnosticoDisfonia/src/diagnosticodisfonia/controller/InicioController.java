package diagnosticodisfonia.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 *
 * @author danie
 */
public class InicioController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        /*Codigo para cerrar*/
        System.exit(0);
        /*Codigo para minimizar
        Stage stage = (Stage) PrincipalController.paneGlobal.getScene().getWindow();
        stage.setIconified(true);
        */
       
    }

    @FXML
    private void diagnostico(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Diagnostico.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    @FXML
    private void curiosidades(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Curiosidades.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);

    }

}
