package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoajusteproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoajusteproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoajusteproducto;
	private String nombreEstadoajusteproducto;
	private Set ajusteproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoajusteproducto() {
	}

	/** minimal constructor */
	public Estadoajusteproducto(String nombreEstadoajusteproducto) {
		this.nombreEstadoajusteproducto = nombreEstadoajusteproducto;
	}

	/** full constructor */
	public Estadoajusteproducto(String nombreEstadoajusteproducto, Set ajusteproductos) {
		this.nombreEstadoajusteproducto = nombreEstadoajusteproducto;
		this.ajusteproductos = ajusteproductos;
	}

	// Property accessors

	public Long getPkCodigoEstadoajusteproducto() {
		return this.pkCodigoEstadoajusteproducto;
	}

	public void setPkCodigoEstadoajusteproducto(Long pkCodigoEstadoajusteproducto) {
		this.pkCodigoEstadoajusteproducto = pkCodigoEstadoajusteproducto;
	}

	public String getNombreEstadoajusteproducto() {
		return this.nombreEstadoajusteproducto;
	}

	public void setNombreEstadoajusteproducto(String nombreEstadoajusteproducto) {
		this.nombreEstadoajusteproducto = nombreEstadoajusteproducto;
	}

	public Set getAjusteproductos() {
		return this.ajusteproductos;
	}

	public void setAjusteproductos(Set ajusteproductos) {
		this.ajusteproductos = ajusteproductos;
	}

}