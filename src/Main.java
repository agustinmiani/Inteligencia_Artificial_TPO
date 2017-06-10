import clipsDAO.ClipsDAO;
import model.Duracion;
import model.Resultado;
import model.Riesgo;

import java.util.List;

public class Main {

    public static void main(String[] args){

        //VentanaCarga ventana = new VentanaCarga();

        ClipsDAO clipsDAO = new ClipsDAO();

        List<Resultado> resultados;

        resultados = clipsDAO.ejecutarClips(Riesgo.Minimo, Duracion.Anio, 30, new Float(200000));

        for(Resultado aux : resultados){

            System.out.println(aux.toString());
        }

        resultados = clipsDAO.ejecutarClips(Riesgo.Minimo, Duracion.Anio, 15, new Float(400000));

        for(Resultado aux : resultados){

            System.out.println(aux.toString());
        }

        resultados = clipsDAO.ejecutarClips(Riesgo.Minimo, Duracion.Anio, 45, new Float(400000));

        for(Resultado aux : resultados){

            System.out.println(aux.toString());
        }

        System.out.println("\n\n\n Aca van cuando faltan parametros");
        resultados = clipsDAO.ejecutarClips(Riesgo.Medio, null,null, new Float(400000));

        for(Resultado aux : resultados){

            System.out.println(aux.toString());
        }




    }



}