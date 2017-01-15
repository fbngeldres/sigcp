package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoactividad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoactividad implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoactividad;
	private String nombreEstadoactividad;
	private Set actividads = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoactividad() {
	}

	/** minimal constructor */
	public Estadoactividad(String nombreEstadoactividad) {
		this.nombreEstadoactividad = nombreEstadoactividad;
	}

	/** full constructor */
	public Estadoactividad(String nombreEstadoactividad, Set actividads) {
		this.nombreEstadoactividad = nombreEstadoactividad;
		this.actividads = actividads;
	}

	// Property accessors

	public Long getPkCodigoEstadoactividad() {
		return this.pkCodigoEstadoactividad;
	}

	public void setPkCodigoEstadoactividad(Long pkCodigoEstadoactividad) {
		this.pkCodigoEstadoactividad = pkCodigoEstadoactividad;
	}

	public String getNombreEstadoactividad() {
		return this.nombreEstadoactividad;
	}

	public void setNombreEstadoactividad(String nombreEstadoactividad) {
		this.nombreEstadoactividad = nombreEstadoactividad;
	}

	public Set getActividads() {
		return this.actividads;
	}

	public void setActividads(Set actividads) {
		this.actividads = actividads;
	}

}