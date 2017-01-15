package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadopuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadopuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadopuestotrabajo;
	private String nombreEstadopuestotrabajo;
	private Set puestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadopuestotrabajo() {
	}

	/** minimal constructor */
	public Estadopuestotrabajo(String nombreEstadopuestotrabajo) {
		this.nombreEstadopuestotrabajo = nombreEstadopuestotrabajo;
	}

	/** full constructor */
	public Estadopuestotrabajo(String nombreEstadopuestotrabajo, Set puestotrabajos) {
		this.nombreEstadopuestotrabajo = nombreEstadopuestotrabajo;
		this.puestotrabajos = puestotrabajos;
	}

	// Property accessors

	public Long getPkCodigoEstadopuestotrabajo() {
		return this.pkCodigoEstadopuestotrabajo;
	}

	public void setPkCodigoEstadopuestotrabajo(Long pkCodigoEstadopuestotrabajo) {
		this.pkCodigoEstadopuestotrabajo = pkCodigoEstadopuestotrabajo;
	}

	public String getNombreEstadopuestotrabajo() {
		return this.nombreEstadopuestotrabajo;
	}

	public void setNombreEstadopuestotrabajo(String nombreEstadopuestotrabajo) {
		this.nombreEstadopuestotrabajo = nombreEstadopuestotrabajo;
	}

	public Set getPuestotrabajos() {
		return this.puestotrabajos;
	}

	public void setPuestotrabajos(Set puestotrabajos) {
		this.puestotrabajos = puestotrabajos;
	}

}