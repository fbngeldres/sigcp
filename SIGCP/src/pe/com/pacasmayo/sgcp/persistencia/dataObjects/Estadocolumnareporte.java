package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadocolumnareporte entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadocolumnareporte implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadocolumnareporte;
	private String nombreEstadocolumnareporte;
	private Set columnareportes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadocolumnareporte() {
	}

	/** minimal constructor */
	public Estadocolumnareporte(String nombreEstadocolumnareporte) {
		this.nombreEstadocolumnareporte = nombreEstadocolumnareporte;
	}

	/** full constructor */
	public Estadocolumnareporte(String nombreEstadocolumnareporte, Set columnareportes) {
		this.nombreEstadocolumnareporte = nombreEstadocolumnareporte;
		this.columnareportes = columnareportes;
	}

	// Property accessors

	public Long getPkCodigoEstadocolumnareporte() {
		return this.pkCodigoEstadocolumnareporte;
	}

	public void setPkCodigoEstadocolumnareporte(Long pkCodigoEstadocolumnareporte) {
		this.pkCodigoEstadocolumnareporte = pkCodigoEstadocolumnareporte;
	}

	public String getNombreEstadocolumnareporte() {
		return this.nombreEstadocolumnareporte;
	}

	public void setNombreEstadocolumnareporte(String nombreEstadocolumnareporte) {
		this.nombreEstadocolumnareporte = nombreEstadocolumnareporte;
	}

	public Set getColumnareportes() {
		return this.columnareportes;
	}

	public void setColumnareportes(Set columnareportes) {
		this.columnareportes = columnareportes;
	}

}