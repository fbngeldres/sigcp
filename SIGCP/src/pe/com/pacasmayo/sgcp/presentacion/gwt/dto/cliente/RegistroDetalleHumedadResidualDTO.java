package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroDetalleHumedadResidualDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fecha;
	private Map<String, Double> humedadPerdida = new HashMap<String, Double>();
	private Map<String, Double> factorHumedad = new HashMap<String, Double>();
	private Map<String, Double> humedadResidual = new HashMap<String, Double>();

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Map<String, Double> getHumedadPerdida() {
		return humedadPerdida;
	}

	public void setHumedadPerdida(Map<String, Double> humedadPerdida) {
		this.humedadPerdida = humedadPerdida;
	}

	public Map<String, Double> getFactorHumedad() {
		return factorHumedad;
	}

	public void setFactorHumedad(Map<String, Double> factorHumedad) {
		this.factorHumedad = factorHumedad;
	}

	public Map<String, Double> getHumedadResidual() {
		return humedadResidual;
	}

	public void setHumedadResidual(Map<String, Double> humedadResidual) {
		this.humedadResidual = humedadResidual;
	}

}
