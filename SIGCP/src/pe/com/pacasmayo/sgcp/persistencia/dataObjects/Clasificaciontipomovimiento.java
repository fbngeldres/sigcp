package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Clasificaciontipomovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Clasificaciontipomovimiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoClasificaciontipomovi;
	private String nombreClasificaciontipomovimie;
	private Set tipomovimientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Clasificaciontipomovimiento() {
	}

	/** minimal constructor */
	public Clasificaciontipomovimiento(String nombreClasificaciontipomovimie) {
		this.nombreClasificaciontipomovimie = nombreClasificaciontipomovimie;
	}

	/** full constructor */
	public Clasificaciontipomovimiento(String nombreClasificaciontipomovimie, Set tipomovimientos) {
		this.nombreClasificaciontipomovimie = nombreClasificaciontipomovimie;
		this.tipomovimientos = tipomovimientos;
	}

	// Property accessors

	public Long getPkCodigoClasificaciontipomovi() {
		return this.pkCodigoClasificaciontipomovi;
	}

	public void setPkCodigoClasificaciontipomovi(Long pkCodigoClasificaciontipomovi) {
		this.pkCodigoClasificaciontipomovi = pkCodigoClasificaciontipomovi;
	}

	public String getNombreClasificaciontipomovimie() {
		return this.nombreClasificaciontipomovimie;
	}

	public void setNombreClasificaciontipomovimie(String nombreClasificaciontipomovimie) {
		this.nombreClasificaciontipomovimie = nombreClasificaciontipomovimie;
	}

	public Set getTipomovimientos() {
		return this.tipomovimientos;
	}

	public void setTipomovimientos(Set tipomovimientos) {
		this.tipomovimientos = tipomovimientos;
	}

}