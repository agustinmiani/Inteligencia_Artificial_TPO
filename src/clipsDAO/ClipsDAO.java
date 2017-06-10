package clipsDAO;

import model.Duracion;
import model.Resultado;
import model.Riesgo;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.MultifieldValue;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agustin on 10/6/2017.
 */
public class ClipsDAO {

    private static Environment clips;
    StringBuilder evalString;


    public ClipsDAO(){
    }


    public List<Resultado> ejecutarClips(Riesgo riesgo, Duracion duracion, Integer gananciaEsperada, Float montoIngresado) {


        try {
            clips = new Environment();


            clips.loadFromResource("/net/sf/clipsrules/jni/examples/tpoGrupo3/tpoGrupo3.clp");

            clips.reset();

            evalString = new StringBuilder();


            evalString.append("(assert (Requisitos ");

            if (duracion != null)
                evalString.append("(duracion " + duracion.toString() + ") ");

            if (riesgo != null)
                evalString.append("(riesgo " + riesgo.toString() + ") ");

            if (gananciaEsperada != null)
                evalString.append("(ganancia " + gananciaEsperada.toString() + ") ");

            evalString.append("))");


            clips.eval(evalString.toString());


            clips.run();


            String evalStr = "(find-all-facts((?J Inversion)) TRUE)";
            //Aca nos devuelve un campo que esta definido en el paquete de clips. Si yo que tengo un
            //solo hecho de resultado como esto lo que devuelve es como un array en este caso me
            //quedo con el primero, es decir con el unico
            try {
                MultifieldValue pv = (MultifieldValue) clips.eval(evalStr);

                FactAddressValue fv = null;

                if (pv != null) {

                    return procesarResultados(pv, montoIngresado);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Conectar con Clips. \n Verifique si dispone de las Configuraciones Correspondientes.", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al Conectar con Clips. \n Verifique si dispone de las Configuraciones Correspondientes.", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Levantar la Libreria de Clips. \n Verifique si dispone de las Configuraciones Correspondientes.", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private List<Resultado> procesarResultados(MultifieldValue pv, Float montoIngresado) {

        FactAddressValue fv = null;
        Resultado resultado;
        List<Resultado> resultados = new ArrayList<>();
        String nombreInversion;


        if (pv.size() > 0) {

            for (int i = 0; i < pv.size(); i++) {

                fv = (FactAddressValue) pv.get(i);

                try {
                    //ResultadoFinal era el hecho, aca obtengo el slot "resultado" u otro slot que quiera
                    if (fv.getFactSlot("inversionSugerida") != null) {
                        nombreInversion = fv.getFactSlot("inversionSugerida").toString();
                        if (!esRepetida(resultados, nombreInversion)) {
                            resultado = new Resultado()
                                    .setNombre(nombreInversion);
                            resultados.add(resultado);
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            if(resultados.size() > 0){
                ubicarMontos(resultados, montoIngresado);
                return resultados;
            }

        } else
            JOptionPane.showMessageDialog(null, "No se han obtenido resultados con dicha combinaci?n.\nPor favor, intente con otra...", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);

        return null;

    }


    private void ubicarMontos(List<Resultado> resultados, Float montoIngresado) {

        Float montoInvertir = montoIngresado/resultados.size();

        for(Resultado aux: resultados){
            aux.setMonto(montoInvertir);
        }


    }

    private Boolean esRepetida (List<Resultado> resultados, String nombreInversion){

        if(resultados != null || resultados.size() > 0){
            for(Resultado aux: resultados){
                if(aux.getNombre().equalsIgnoreCase(nombreInversion))
                    return true;
            }
        }
        return false;
    }
}
