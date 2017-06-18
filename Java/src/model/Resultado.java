package model;

/**
 * Created by Agustin on 10/6/2017.
 */
public class Resultado {

	private String idRegla;
	private String nombre;
	private Float monto;
    private Float porcentaje;

	public Resultado() {

	}

	public String getNombre() {
		return nombre;
	}

	public Resultado setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getIdRegla() {
		return idRegla;
	}

	public Resultado setIdRegla(String idRegla) {
		this.idRegla = idRegla;
		return this;
	}

	public Float getMonto() {
		return monto;
	}

	public Resultado setMonto(Float monto) {
		this.monto = monto;
		return this;
	}

    public Float getPorcentaje() {
        return porcentaje;
    }

    public Resultado setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    @Override
	public String toString() {
		return "Resultado{" + "nombre='" + nombre + '\'' + ", monto=" + monto + '}';
	}

}
