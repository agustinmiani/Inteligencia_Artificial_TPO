package model;

import java.util.List;

/**
 * Created by Agustin on 18/6/2017.
 */
public class GrupoResultados {

    String idRegla;
    List<Resultado> resultados;
    String descripcionRegla;

    public String getIdRegla() {
        return idRegla;
    }

    public GrupoResultados setIdRegla(String idRegla) {
        this.idRegla = idRegla;
        return this;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public GrupoResultados setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
        return this;
    }

    public String getDescripcionRegla() {
        return descripcionRegla;
    }

    public GrupoResultados setDescripcionRegla(String descripcionRegla) {
        this.descripcionRegla = descripcionRegla;
        return this;
    }

    @Override
    public String toString() {
        return "GrupoResultados{" +
                "idRegla='" + idRegla + '\'' +
                ", resultados=" + resultados +
                ", descripcionRegla='" + descripcionRegla + '\'' +
                '}';
    }
}
