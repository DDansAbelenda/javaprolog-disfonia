/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosticodisfonia.controller;

import com.jfoenix.controls.JFXCheckBox;
import diagnosticodisfonia.Main;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class DiagnosticoController implements Initializable {

    @FXML
    private TabPane tabPaneNav;
    @FXML
    private Tab tabSintomas;
    @FXML
    private Tab TabSignos;
    @FXML
    private Tab TabLaring;
    @FXML
    private Pane paneSintomas;
    @FXML
    private TextField nombre_paciente;
    @FXML
    private JFXCheckBox is_fumador;
    @FXML
    private TextField cant_dias;
    @FXML
    private AnchorPane paneSignos;
    @FXML
    private AnchorPane paneLaringoscopia;

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
    private void irASignos(MouseEvent event) {
        if (nombre_paciente.getText().isEmpty() || cant_dias.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Datos Faltantes");
            alert.setContentText("Debe completar los campos de Nombre y cantidad de días");
            alert.showAndWait();
        } else {
            try {
                Integer.parseInt(cant_dias.getText().trim());
                tabPaneNav.getSelectionModel().select(TabSignos);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Datos Incorrectos");
                alert.setContentText("La cantidad de días es un dato numérico");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void irASintomas(MouseEvent event) {
        tabPaneNav.getSelectionModel().select(tabSintomas);
    }

    @FXML
    private void irALaring(MouseEvent event) {
        tabPaneNav.getSelectionModel().select(TabLaring);
    }

    @FXML
    private void diagnosticar(ActionEvent event) throws IOException {
        //Cargar datos
        String nombre = "'" + nombre_paciente.getText().trim() + "'";
        Boolean isFumador = is_fumador.isSelected();
        int dias = Integer.parseInt(cant_dias.getText().trim());
        String sintomas = lista_sintomas();
        String signos = lista_signos();
        String laringoscopia = lista_laringoscopia();
        //Obtener diagnóstico
        String[] diagnostico = Main.connection.diagnosticar(nombre, isFumador, dias, sintomas, signos, laringoscopia);

        //Tomar cargador de la vista Resultado
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagnosticodisfonia/view/Resultado.fxml"));
        //Cargar la vista **necesario antes de inicializar el controlador**
        Node node = loader.load();
        //Inicializar el controlador
        ResultadoController controlador_resultado = loader.getController();
        //Llamar al metodo de rellenar los labels de la vista Resultado
        controlador_resultado.llenar_labels(nombre_paciente.getText().trim(), diagnostico);
        //Quitar contenido de la vista actual
        PrincipalController.paneGlobal.getChildren().clear();
        //Mostrar contenido de la vista Resultado
        PrincipalController.paneGlobal.getChildren().add(node);
    }

    private String lista_sintomas() {
        String sintomas = "";

        for (Node x : paneSintomas.getChildren()) {
            if (x instanceof JFXCheckBox && ((JFXCheckBox) x).isSelected()) {
                sintomas += "'" + ((JFXCheckBox) x).getText() + "',";
            }
        }
        if (sintomas.isEmpty()) {
            sintomas = "[]";
        } else {
            sintomas = "[" + sintomas.substring(0, sintomas.length() - 1) + "]";
        }

        return sintomas;
    }

    private String lista_signos() {
        String signos = "";
        for (Node x : paneSignos.getChildren()) {
            if (x instanceof JFXCheckBox && ((JFXCheckBox) x).isSelected()) {
                signos += "'" + ((JFXCheckBox) x).getText() + "',";
            }
        }
        if (signos.isEmpty()) {
            signos = "[]";
        } else {
            signos = "[" + signos.substring(0, signos.length() - 1) + "]";
        }
        return signos;
    }

    private String lista_laringoscopia() {
        String result_laringoscopia = "";
        for (Node x : paneLaringoscopia.getChildren()) {
            if (x instanceof JFXCheckBox && ((JFXCheckBox) x).isSelected()) {
                result_laringoscopia += "'" + ((JFXCheckBox) x).getText() + "',";
            }
        }
        if (result_laringoscopia.isEmpty()) {
            result_laringoscopia = "[]";
        } else {
            result_laringoscopia = "[" + result_laringoscopia.substring(0, result_laringoscopia.length() - 1) + "]";
        }
        return result_laringoscopia;
    }

    //Metodo que se activa cuando el usuario da click directamente sobre el tab Signos
    //Se verifica que el nombre y la cantidad de días no estén vacías
    @FXML
    private void seleccionarTabSignos(Event event) {
        if (nombre_paciente.getText().isEmpty() || cant_dias.getText().isEmpty()) {
            //En caso de error mantener en tab sintomas
            tabPaneNav.getSelectionModel().select(tabSintomas);
            //Mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Datos Faltantes");
            alert.setContentText("Debe completar los campos de Nombre y cantidad de días");
            alert.showAndWait();
        } else {
            try {
                Integer.parseInt(cant_dias.getText().trim());
            } catch (NumberFormatException e) {
                //En caso de error mantener en tab sintomas
                tabPaneNav.getSelectionModel().select(tabSintomas);
                //Mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Datos Incorrectos");
                alert.setContentText("La cantidad de días es un dato numérico");
                alert.showAndWait();
            }
        }
    }

    //Metodo que se activa cuando el usuario da click directamente sobre el tab Resultados Laring..
    //Se verifica que el nombre y la cantidad de días no estén vacías
    @FXML
    private void seleccionarTabResultadosLaring(Event event) {
        if (nombre_paciente.getText().isEmpty() || cant_dias.getText().isEmpty()) {
            //En caso de error mantener en tab sintomas
            tabPaneNav.getSelectionModel().select(tabSintomas);
            //Mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Datos Faltantes");
            alert.setContentText("Debe completar los campos de Nombre y cantidad de días");
            alert.showAndWait();

        } else {
            try {
                Integer.parseInt(cant_dias.getText().trim());
            } catch (NumberFormatException e) {
                //En caso de error mantener en tab sintomas
                tabPaneNav.getSelectionModel().select(tabSintomas);
                //Mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Datos Incorrectos");
                alert.setContentText("La cantidad de días es un dato numérico");
                alert.showAndWait();
            }
        }
    }

}
