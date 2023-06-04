/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosticodisfonia.controller;

import diagnosticodisfonia.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class ResultadoController implements Initializable {

    @FXML
    private Label label_paciente;
    @FXML
    private Label label_diagnostico;
    @FXML
    private Label label_tipo;
    @FXML
    private Label label_tipo_titulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptar_resultado(ActionEvent event) throws IOException {
        Main.connection.retract_fumador();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Inicio.fxml"));
        Node node = loader.load();
        PrincipalController.paneGlobal.getChildren().clear();
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    void llenar_labels(String nombre, String[] diagnostico) {
        label_paciente.setText(nombre);
        label_diagnostico.setText(diagnostico[0]);
        if(diagnostico[1].equals("---")){
            label_tipo_titulo.setVisible(false);
            label_tipo.setVisible(false);
        }else{
            label_tipo.setText(diagnostico[1]);
        }
        
    }
    
}
