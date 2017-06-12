package model;

/**
 * Created by Agustin on 10/6/2017.
 */
public class Resultado {

	private String nombre;
	private Float monto;

	public Resultado() {

	}

	public String getNombre() {
		return nombre;
	}

	public Resultado setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Float getMonto() {
		return monto;
	}

	public Resultado setMonto(Float monto) {
		this.monto = monto;
		return this;
	}

	@Override
	public String toString() {
		return "Resultado{" + "nombre='" + nombre + '\'' + ", monto=" + monto + '}';
	}

}
