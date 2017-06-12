package model;

/**
 * Created by Agustin on 10/6/2017.
 */
public enum Riesgo {
	Minimo,
	Bajo,
	Medio,
	Alto;

	public Riesgo getEnumFromString(String s) {
		if (s.equalsIgnoreCase("Minimo"))
			return Riesgo.Minimo;
		else if (s.equalsIgnoreCase("Bajo"))
			return Riesgo.Bajo;
		else if (s.equalsIgnoreCase("Medio"))
			return Riesgo.Medio;
		else if (s.equalsIgnoreCase("Alto"))
			return Riesgo.Alto;
		else
			return null;
	}
}
