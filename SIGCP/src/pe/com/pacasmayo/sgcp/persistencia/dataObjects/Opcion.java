package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Opcion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Opcion implements java.io.Serializable {

	// Fields

	private Long pkCodigoOpcion;
	private String nombreOpcion;
	private Set opcionaccions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Opcion() {
	}

	/** minimal constructor */
	public Opcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}

	/** full constructor */
	public Opcion(String nombreOpcion, Set opcionaccions) {
		this.nombreOpcion = nombreOpcion;
		this.opcionaccions = opcionaccions;
	}

	// Property accessors

	public Long getPkCodigoOpcion() {
		return this.pkCodigoOpcion;
	}

	public void setPkCodigoOpcion(Long pkCodigoOpcion) {
		this.pkCodigoOpcion = pkCodigoOpcion;
	}

	public String getNombreOpcion() {
		return this.nombreOpcion;
	}

	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}

	public Set getOpcionaccions() {
		return this.opcionaccions;
	}

	public void setOpcionaccions(Set opcionaccions) {
		this.opcionaccions = opcionaccions;
	}

}