package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoproducto;
	private String nombreEstadoproducto;
	private Set productos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoproducto() {
	}

	/** minimal constructor */
	public Estadoproducto(String nombreEstadoproducto) {
		this.nombreEstadoproducto = nombreEstadoproducto;
	}

	/** full constructor */
	public Estadoproducto(String nombreEstadoproducto, Set productos) {
		this.nombreEstadoproducto = nombreEstadoproducto;
		this.productos = productos;
	}

	// Property accessors

	public Long getPkCodigoEstadoproducto() {
		return this.pkCodigoEstadoproducto;
	}

	public void setPkCodigoEstadoproducto(Long pkCodigoEstadoproducto) {
		this.pkCodigoEstadoproducto = pkCodigoEstadoproducto;
	}

	public String getNombreEstadoproducto() {
		return this.nombreEstadoproducto;
	}

	public void setNombreEstadoproducto(String nombreEstadoproducto) {
		this.nombreEstadoproducto = nombreEstadoproducto;
	}

	public Set getProductos() {
		return this.productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

}