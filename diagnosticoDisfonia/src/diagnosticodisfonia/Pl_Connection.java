/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosticodisfonia;

import java.util.Map;
import javafx.scene.control.Alert;
import org.jpl7.*;

/**
 *
 * @author Ale
 */
public class Pl_Connection {

    private String consulta;
    private Query q;
    private Map<String, Term> solucion;

    public Pl_Connection() {
        String file = "prolog/disfonia.pl";
        consulta = String.format("consult('%s').", file);
        q = new Query(consulta);
        try {
            q.hasSolution();
        } catch (PrologException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de Carga");
            alert.setContentText("No se encuentra el fichero de la base de concimientos");
            alert.showAndWait();
            System.exit(0);
        }

    }

    public String[] diagnosticar(String nombre, Boolean fumador, int dias, String sintomas, String signos, String laringoscopia) {
        String enfermedad;
        String tipo;
        assert_fumador(nombre, fumador);

        consulta = String.format("diagnostica(%s,Enfermedad,%s,%s,%s).", nombre, sintomas, signos, laringoscopia);
        q = new Query(consulta);
        q.hasSolution();
        solucion = q.nextSolution();

        enfermedad = solucion.get("Enfermedad").toString().replace('\'', ' ').trim();
        if (!enfermedad.equals("Sano")) {
            tipo = tipo_disfonia(dias);
        } else {
            tipo = "---";
        }
        
        String[] respuesta = {enfermedad, tipo};
        return respuesta;
    }
    
    public void retract_fumador(){
        consulta = "retract(fumador(_,_)).";
        q= new Query(consulta);
        q.hasSolution();
    }

    private String tipo_disfonia(int dias) {
        consulta = String.format("clasificacion_tiempo(%d,Clasificacion)", dias);
        q = new Query(consulta);
        q.hasSolution();
        solucion = q.nextSolution();
        String tipo = solucion.get("Clasificacion").toString().replace('\'', ' ').trim();
        return tipo;
    }

    private void assert_fumador(String nombre,Boolean fumador) {
        if (fumador) {
            consulta = String.format("assert(fumador(%s,1)).", nombre);
            q = new Query(consulta);
            q.hasSolution();
        } else {
            consulta = String.format("assert(fumador(%s,0)).", nombre);
            q = new Query(consulta);
            q.hasSolution();
        }
    }

}
