package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoajusteproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoajusteproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoajusteproduccio;
	private String nombreEstadoajusteproduccion;
	private Set ajusteproduccions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoajusteproduccion() {
	}

	/** minimal constructor */
	public Estadoajusteproduccion(String nombreEstadoajusteproduccion) {
		this.nombreEstadoajusteproduccion = nombreEstadoajusteproduccion;
	}

	/** full constructor */
	public Estadoajusteproduccion(String nombreEstadoajusteproduccion, Set ajusteproduccions) {
		this.nombreEstadoajusteproduccion = nombreEstadoajusteproduccion;
		this.ajusteproduccions = ajusteproduccions;
	}

	// Property accessors

	public Long getPkCodigoEstadoajusteproduccio() {
		return this.pkCodigoEstadoajusteproduccio;
	}

	public void setPkCodigoEstadoajusteproduccio(Long pkCodigoEstadoajusteproduccio) {
		this.pkCodigoEstadoajusteproduccio = pkCodigoEstadoajusteproduccio;
	}

	public String getNombreEstadoajusteproduccion() {
		return this.nombreEstadoajusteproduccion;
	}

	public void setNombreEstadoajusteproduccion(String nombreEstadoajusteproduccion) {
		this.nombreEstadoajusteproduccion = nombreEstadoajusteproduccion;
	}

	public Set getAjusteproduccions() {
		return this.ajusteproduccions;
	}

	public void setAjusteproduccions(Set ajusteproduccions) {
		this.ajusteproduccions = ajusteproduccions;
	}

}