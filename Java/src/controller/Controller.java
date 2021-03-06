package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Duracion;
import model.GrupoResultados;
import model.Resultado;
import model.Riesgo;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.MultifieldValue;

public class Controller {

	private static Controller instance = null; // variable de clase que guarda el Singleton!

	private static Environment clips;
	StringBuilder evalString;

	private Controller() {
		try {
			clips = new Environment();
			clips.loadFromResource("/net/sf/clipsrules/tpoGrupo3.clp");
			clips.reset();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar la libreria de Clips.\n" +
				"Verifique si dispone de la configuracion correspondiente.", "TPO", JOptionPane.ERROR_MESSAGE);
        }
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public List<GrupoResultados> ejecutarClips(Riesgo riesgo, Duracion duracion,
                                               Integer tasaRetorno, Float montoIngresado) {

		try {
			evalString = new StringBuilder();
	
			evalString.append("(assert (Requisitos ");
	
			if (duracion != null)
				evalString.append("(duracion " + duracion.toString() + ") ");
	
			if (riesgo != null)
				evalString.append("(riesgo " + riesgo.toString() + ") ");
	
			if (tasaRetorno != -1)
				evalString.append("(ganancia " + tasaRetorno.toString() + ") ");
	
			evalString.append("))");

			clips.reset();

			clips.eval(evalString.toString());
	
			clips.run();
	
			String evalStr = "(find-all-facts((?J Inversion)) TRUE)";
			// Aca nos devuelve un campo que esta definido en el paquete de clips.
			// Si yo que tengo un solo hecho de resultado como esto lo que devuelve es como un array en
			// este caso me quedo con el primero, es decir con el unico

			MultifieldValue pv = (MultifieldValue) clips.eval(evalStr);

//			FactAddressValue fv = null;

			if (pv != null) {
				List<Resultado> resultadosInversiones = procesarResultadosInversiones(pv, montoIngresado);

				return obtenerGruposPorReglas(resultadosInversiones);


			} else {
				JOptionPane.showMessageDialog(null, "Error al conectar con Clips.\n" +
					"Verifique si dispone de la configuracion correspondiente.", "TPO", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con Clips.\n" +
				"Verifique si dispone de la configuracion correspondiente.", "TPO", JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}

	private List<Resultado> procesarResultadosInversiones(MultifieldValue pv, Float montoIngresado) {

		FactAddressValue fv = null;
		Resultado resultado;
		List<Resultado> resultados = new ArrayList<>();
		String nombreInversion;
		String idRegla;
		Float porcentaje;

		if (pv.size() > 0) {
			for (int i = 0; i < pv.size(); i++) {
				fv = (FactAddressValue) pv.get(i);

				try {
					// ResultadoFinal era el hecho, aca obtengo el slot "resultado"
					// u otro slot que quiera
					System.out.println(fv.getFactIndex());

					if (fv.getFactSlot("inversionSugerida") != null) {
                        nombreInversion = fv.getFactSlot("inversionSugerida").toString();
                        if (fv.getFactSlot("identificacion") != null) {
                            idRegla = fv.getFactSlot("identificacion").toString();
                            if (fv.getFactSlot("porcentaje") != null) {
                                porcentaje = new Float(fv.getFactSlot("porcentaje").toString());

                                resultado = new Resultado()
                                        .setNombre(nombreInversion)
                                        .setIdRegla(idRegla)
                                        .setPorcentaje(porcentaje);

                                resultados.add(resultado);
                            }
                        }
                    }
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			if (resultados.size() > 0) {
				ubicarMontos(resultados, montoIngresado);
				return resultados;
			}

		} else
			JOptionPane.showMessageDialog(null, "No se han obtenido resultados con dicha combinacion.\n" +
				"Por favor, intente con otra...", "TPO", JOptionPane.WARNING_MESSAGE);

		return null;
	}

	private List<GrupoResultados> obtenerGruposPorReglas(List<Resultado> resultadosInversiones){

        String evalStr = "(find-all-facts((?J Regla_Ejecutada)) TRUE)";

        MultifieldValue pv = (MultifieldValue) clips.eval(evalStr);

        if (pv != null) {
            FactAddressValue fv = null;
            GrupoResultados grupo;
            List<GrupoResultados> grupoResultados = new ArrayList<>();
            String descripcionRegla;
            String idRegla;

            if (pv.size() > 0) {
                for (int i = 0; i < pv.size(); i++) {
                    fv = (FactAddressValue) pv.get(i);

                    try {
                        if (fv.getFactSlot("id_regla") != null) {
                            idRegla = fv.getFactSlot("id_regla").toString();
                            if (fv.getFactSlot("descripcion") != null) {
                                descripcionRegla = fv.getFactSlot("descripcion").toString();
                                grupo = new GrupoResultados()
                                        .setIdRegla(idRegla)
                                        .setDescripcionRegla(descripcionRegla)
                                        .setResultados(obtenerResultadosRegla(idRegla, resultadosInversiones));

                                grupoResultados.add(grupo);
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                return grupoResultados;

            } else
                JOptionPane.showMessageDialog(null, "No se han obtenido resultados con dicha combinacion.\n" +
                        "Por favor, intente con otra...", "TPO", JOptionPane.WARNING_MESSAGE);

            return null;
        }
        return null;
    }

	private void ubicarMontos(List<Resultado> resultados, Float montoIngresado) {
		Float montoInvertir;

		for (Resultado aux : resultados) {
            montoInvertir = montoIngresado * aux.getPorcentaje();
            montoInvertir = redondearResultado(montoInvertir);
            aux.setMonto(montoInvertir);
		}
	}

	private List<Resultado> obtenerResultadosRegla(String idRegla, List<Resultado> resultadosTotales){

            List<Resultado> resultadosGrupo = new ArrayList<>();

            for(Resultado resultado: resultadosTotales){
                if(resultado.getIdRegla().equalsIgnoreCase(idRegla))
                    resultadosGrupo.add(resultado);
            }

            return resultadosGrupo;
    }

	private Float redondearResultado(Float montoAInvertir){

		double redondeo = Math.round(montoAInvertir * 100.0) / 100.0;

		return new Float(((float) redondeo));
	}

}
