package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipopuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipopuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipopuestotrabajo;
	private String nombreTipopuestotrabajo;
	private String descripcionTipopuestotrabajo;
	private Set puestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipopuestotrabajo() {
	}

	/** minimal constructor */
	public Tipopuestotrabajo(String nombreTipopuestotrabajo) {
		this.nombreTipopuestotrabajo = nombreTipopuestotrabajo;
	}

	/** full constructor */
	public Tipopuestotrabajo(String nombreTipopuestotrabajo, String descripcionTipopuestotrabajo, Set puestotrabajos) {
		this.nombreTipopuestotrabajo = nombreTipopuestotrabajo;
		this.descripcionTipopuestotrabajo = descripcionTipopuestotrabajo;
		this.puestotrabajos = puestotrabajos;
	}

	// Property accessors

	public Long getPkCodigoTipopuestotrabajo() {
		return this.pkCodigoTipopuestotrabajo;
	}

	public void setPkCodigoTipopuestotrabajo(Long pkCodigoTipopuestotrabajo) {
		this.pkCodigoTipopuestotrabajo = pkCodigoTipopuestotrabajo;
	}

	public String getNombreTipopuestotrabajo() {
		return this.nombreTipopuestotrabajo;
	}

	public void setNombreTipopuestotrabajo(String nombreTipopuestotrabajo) {
		this.nombreTipopuestotrabajo = nombreTipopuestotrabajo;
	}

	public String getDescripcionTipopuestotrabajo() {
		return this.descripcionTipopuestotrabajo;
	}

	public void setDescripcionTipopuestotrabajo(String descripcionTipopuestotrabajo) {
		this.descripcionTipopuestotrabajo = descripcionTipopuestotrabajo;
	}

	public Set getPuestotrabajos() {
		return this.puestotrabajos;
	}

	public void setPuestotrabajos(Set puestotrabajos) {
		this.puestotrabajos = puestotrabajos;
	}

}