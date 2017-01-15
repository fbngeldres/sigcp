package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoordenproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoordenproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoorden;
	private String nombreEstadoorden;
	private Set ordenproduccions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoordenproduccion() {
	}

	/** minimal constructor */
	public Estadoordenproduccion(String nombreEstadoorden) {
		this.nombreEstadoorden = nombreEstadoorden;
	}

	/** full constructor */
	public Estadoordenproduccion(String nombreEstadoorden, Set ordenproduccions) {
		this.nombreEstadoorden = nombreEstadoorden;
		this.ordenproduccions = ordenproduccions;
	}

	// Property accessors

	public Long getPkCodigoEstadoorden() {
		return this.pkCodigoEstadoorden;
	}

	public void setPkCodigoEstadoorden(Long pkCodigoEstadoorden) {
		this.pkCodigoEstadoorden = pkCodigoEstadoorden;
	}

	public String getNombreEstadoorden() {
		return this.nombreEstadoorden;
	}

	public void setNombreEstadoorden(String nombreEstadoorden) {
		this.nombreEstadoorden = nombreEstadoorden;
	}

	public Set getOrdenproduccions() {
		return this.ordenproduccions;
	}

	public void setOrdenproduccions(Set ordenproduccions) {
		this.ordenproduccions = ordenproduccions;
	}

}