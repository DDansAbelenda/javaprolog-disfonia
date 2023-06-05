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
public class DisfoniaProfesionalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backCuriosidades(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Curiosidades.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }
    
}
