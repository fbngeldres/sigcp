package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Unidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Unidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoUnidad;
	private Sociedad sociedad;
	private String nombreUnidad;
	private String descripcionUnidad;
	private String codigoSapUnidad;

	public Long getPkCodigoUnidad() {
		return pkCodigoUnidad;
	}

	public void setPkCodigoUnidad(Long pkCodigoUnidad) {
		this.pkCodigoUnidad = pkCodigoUnidad;
	}

	public Sociedad getSociedad() {
		return sociedad;
	}

	public void setSociedad(Sociedad sociedad) {
		this.sociedad = sociedad;
	}


	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public String getDescripcionUnidad() {
		return descripcionUnidad;
	}

	public void setDescripcionUnidad(String descripcionUnidad) {
		this.descripcionUnidad = descripcionUnidad;
	}

	public String getCodigoSapUnidad() {
		return codigoSapUnidad;
	}

	public void setCodigoSapUnidad(String codigoSapUnidad) {
		this.codigoSapUnidad = codigoSapUnidad;
	}

	public Unidad() {
		// TODO Auto-generated constructor stub
	}

	public Unidad(Long pkCodigoUnidad, Sociedad sociedad, String nombreUnidad,
			String descripcionUnidad, String codigoSapUnidad) {
		super();
		this.pkCodigoUnidad = pkCodigoUnidad;
		this.sociedad = sociedad;
		this.nombreUnidad = nombreUnidad;
		this.descripcionUnidad = descripcionUnidad;
		this.codigoSapUnidad = codigoSapUnidad;
	}


}