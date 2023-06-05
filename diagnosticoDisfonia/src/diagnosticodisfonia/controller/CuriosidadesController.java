/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosticodisfonia.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ale
 */
public class CuriosidadesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backPrincipal(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Inicio.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    @FXML
    private void isADisfoniaOrganica(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/DisfoniaOrganica.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    @FXML
    private void irADisfoniaPsiquica(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/DisfoniaPsiquica.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    @FXML
    private void irADisfoniaProfesional(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/DisfoniaProfesional.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    @FXML
    private void irAConsejos(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Consejos.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    @FXML
    private void irADisfoniaFuncional(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/DisfoniaFuncional.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }
    
}
