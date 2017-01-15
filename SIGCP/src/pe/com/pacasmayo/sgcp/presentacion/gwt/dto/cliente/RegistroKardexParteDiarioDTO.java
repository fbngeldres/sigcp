package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class RegistroKardexParteDiarioDTO implements java.io.Serializable {

	protected Date fecha = null;
	protected String almacen = null;
	protected String ubicacionOrigen = null;

	protected Double saldoInicial = null;
	protected Double ingreso = null;
	protected Double ingresoHV = 0d;
	protected Double consumo = null;
	protected String ubicacionDestino = null;
	protected String observacion = null;

	protected Double despachoCAL = null;
	protected boolean despachoCALbool = false;

	protected Map<String, Double> consumosPorPuesto = new HashMap<String, Double>();
	protected Map<String, Double> consumosPorPuestoHumedo = new HashMap<String, Double>();

	protected Map<String, Double> medicionDiariaMedioAlmacenamiento = new HashMap<String, Double>();
	// AGREGADO POR FABIAN
	protected String estado = null;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public String getUbicacionOrigen() {
		return ubicacionOrigen;
	}

	public void setUbicacionOrigen(String ubicacionOrigen) {
		this.ubicacionOrigen = ubicacionOrigen;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getIngreso() {
		return ingreso;
	}

	public void setIngreso(Double ingreso) {
		this.ingreso = ingreso;
	}

	public Double getTotalInicial() {
		return saldoInicial + ingreso;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Double getTotalFinal() {
		return saldoInicial + ingreso - consumo;
	}

	/**
	 * @return the ingresoHV
	 */
	public Double getIngresoHV() {
		return ingresoHV;
	}

	/**
	 * @param ingresoHV the ingresoHV to set
	 */
	public void setIngresoHV(Double ingresoHV) {
		this.ingresoHV = ingresoHV;
	}

	/**
	 * @return the consumosPorPuesto
	 */
	public Map<String, Double> getConsumosPorPuesto() {
		return consumosPorPuesto;
	}

	/**
	 * @param consumosPorPuesto the consumosPorPuesto to set
	 */
	public void setConsumosPorPuesto(Map<String, Double> consumosPorPuesto) {
		this.consumosPorPuesto = consumosPorPuesto;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getDespachoCAL() {
		return despachoCAL;
	}

	public void setDespachoCAL(Double despachoCAL) {
		this.despachoCAL = despachoCAL;
	}

	public boolean isDespachoCALbool() {
		return despachoCALbool;
	}

	public void setDespachoCALbool(boolean despachoCALbool) {
		this.despachoCALbool = despachoCALbool;
	}

	/**
	 * @return the consumosPorPuestoHumedo
	 */
	public Map<String, Double> getConsumosPorPuestoHumedo() {
		return consumosPorPuestoHumedo;
	}

	/**
	 * @param consumosPorPuestoHumedo the consumosPorPuestoHumedo to set
	 */
	public void setConsumosPorPuestoHumedo(Map<String, Double> consumosPorPuestoHumedo) {
		this.consumosPorPuestoHumedo = consumosPorPuestoHumedo;
	}

	public Map<String, Double> getMedicionDiariaMedioAlmacenamiento() {
		return medicionDiariaMedioAlmacenamiento;
	}

	public void setMedicionDiariaMedioAlmacenamiento(Map<String, Double> medicionDiariaMedioAlmacenamiento) {
		this.medicionDiariaMedioAlmacenamiento = medicionDiariaMedioAlmacenamiento;
	}

}
