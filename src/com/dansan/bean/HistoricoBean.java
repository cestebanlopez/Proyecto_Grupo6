package com.dansan.bean;

/**
 *
 * @author dansan
 */
public class HistoricoBean {

    private String patente;
    private double hora_ocupacion;
    private char tipo_auto;
    private int piso;
    private double hora_salida;
    private double importe;

    /**
     * @return the patente
     */
    public String getPatente() {
        return patente;
    }

    /**
     * @param patente the patente to set
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * @return the hora_ocupacion
     */
    public double getHora_ocupacion() {
        return hora_ocupacion;
    }

    /**
     * @param hora_ocupacion the hora_ocupacion to set
     */
    public void setHora_ocupacion(double hora_ocupacion) {
        this.hora_ocupacion = hora_ocupacion;
    }

    /**
     * @return the tipo_auto
     */
    public char getTipo_auto() {
        return tipo_auto;
    }

    /**
     * @param tipo_auto the tipo_auto to set
     */
    public void setTipo_auto(char tipo_auto) {
        this.tipo_auto = tipo_auto;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * @return the hora_salida
     */
    public double getHora_salida() {
        return hora_salida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(double hora_salida) {
        this.hora_salida = hora_salida;
    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

}
