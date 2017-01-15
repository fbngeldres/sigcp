package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadohojaruta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadohojaruta implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadohojaruta;
	private String nombreEstadohojaruta;
	private Set hojarutas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadohojaruta() {
	}

	/** minimal constructor */
	public Estadohojaruta(String nombreEstadohojaruta) {
		this.nombreEstadohojaruta = nombreEstadohojaruta;
	}

	/** full constructor */
	public Estadohojaruta(String nombreEstadohojaruta, Set hojarutas) {
		this.nombreEstadohojaruta = nombreEstadohojaruta;
		this.hojarutas = hojarutas;
	}

	// Property accessors

	public Long getPkCodigoEstadohojaruta() {
		return this.pkCodigoEstadohojaruta;
	}

	public void setPkCodigoEstadohojaruta(Long pkCodigoEstadohojaruta) {
		this.pkCodigoEstadohojaruta = pkCodigoEstadohojaruta;
	}

	public String getNombreEstadohojaruta() {
		return this.nombreEstadohojaruta;
	}

	public void setNombreEstadohojaruta(String nombreEstadohojaruta) {
		this.nombreEstadohojaruta = nombreEstadohojaruta;
	}

	public Set getHojarutas() {
		return this.hojarutas;
	}

	public void setHojarutas(Set hojarutas) {
		this.hojarutas = hojarutas;
	}

}