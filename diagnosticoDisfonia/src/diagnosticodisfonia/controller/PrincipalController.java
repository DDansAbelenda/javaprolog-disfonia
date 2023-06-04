/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosticodisfonia.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import diagnosticodisfonia.Main;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class PrincipalController implements Initializable {

    @FXML
    private Pane paneContenedor;
    @FXML
    private Pane paneBarraTitulo;

    //Mis atributoss
    public static Pane paneGlobal;
    
    //make dragable (permitir que la ventana se arraste)
    private double xOffSet = 0;
    private double yOffSet = 0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        paneGlobal = paneContenedor;
        cargarVistaInicio();
        makeStageDragable();
    }

    /*Metodo que lo permite que al Stage ser transparente y pueda moverse
    cuando su estilo es UNDECORATED*/
    private void makeStageDragable() {
        //Para el paneContenedor global y para el paneBarraTitulo se le establece la propiedad
       
        //PaneGlobal
        paneGlobal.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        paneGlobal.setOnMouseDragged((event) -> {
            Main.stage.setX(event.getScreenX() - xOffSet);
            Main.stage.setY(event.getScreenY() - yOffSet);
            Main.stage.setOpacity(0.8f);
        });
        paneGlobal.setOnDragDone((event) -> {
            Main.stage.setOpacity(1.0f);
        });
        paneGlobal.setOnMouseReleased((event) -> {
            Main.stage.setOpacity(1.0f);
        });
        
        
        //PaneBarraTitulo
         paneBarraTitulo.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        paneBarraTitulo.setOnMouseDragged((event) -> {
            Main.stage.setX(event.getScreenX() - xOffSet);
            Main.stage.setY(event.getScreenY() - yOffSet);
            Main.stage.setOpacity(0.8f);
        });
        paneBarraTitulo.setOnDragDone((event) -> {
            Main.stage.setOpacity(1.0f);
        });
        paneBarraTitulo.setOnMouseReleased((event) -> {
            Main.stage.setOpacity(1.0f);
        });
        
    }
    
    private void cargarVistaInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Inicio.fxml"));
            Node node = loader.load();
            paneGlobal.getChildren().clear();
            paneGlobal.getChildren().add(node);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cerrar(MouseEvent event) {
        System.exit(0);
       
    }

    @FXML
    private void minimizar(MouseEvent event) {
         /*Codigo para minimizar*/
        Stage stage = (Stage) paneGlobal.getScene().getWindow();
        stage.setIconified(true);
        
    }

}
