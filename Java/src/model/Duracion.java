package model;

/**
 * Created by Agustin on 10/6/2017.
 */
public enum Duracion {
	Dia,
	Semana,
	Mes,
	Anio;

	public Duracion getEnumFromString(String s) {
		if (s.equalsIgnoreCase("Dia"))
			return Duracion.Dia;
		else if (s.equalsIgnoreCase("Semana"))
			return Duracion.Semana;
		else if (s.equalsIgnoreCase("Mes"))
			return Duracion.Mes;
		else if (s.equalsIgnoreCase("A�o"))
			return Duracion.Anio;
		else
			return null;			
	}
}
