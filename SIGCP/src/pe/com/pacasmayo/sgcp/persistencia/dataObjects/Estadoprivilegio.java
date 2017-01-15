package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoprivilegio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoprivilegio implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoprivilegio;
	private String nombreEstadoprivilegio;
	private String descripcionEstadoprivilegio;
	private Set privilegios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoprivilegio() {
	}

	/** minimal constructor */
	public Estadoprivilegio(String nombreEstadoprivilegio) {
		this.nombreEstadoprivilegio = nombreEstadoprivilegio;
	}

	/** full constructor */
	public Estadoprivilegio(String nombreEstadoprivilegio, String descripcionEstadoprivilegio, Set privilegios) {
		this.nombreEstadoprivilegio = nombreEstadoprivilegio;
		this.descripcionEstadoprivilegio = descripcionEstadoprivilegio;
		this.privilegios = privilegios;
	}

	// Property accessors

	public Long getPkCodigoEstadoprivilegio() {
		return this.pkCodigoEstadoprivilegio;
	}

	public void setPkCodigoEstadoprivilegio(Long pkCodigoEstadoprivilegio) {
		this.pkCodigoEstadoprivilegio = pkCodigoEstadoprivilegio;
	}

	public String getNombreEstadoprivilegio() {
		return this.nombreEstadoprivilegio;
	}

	public void setNombreEstadoprivilegio(String nombreEstadoprivilegio) {
		this.nombreEstadoprivilegio = nombreEstadoprivilegio;
	}

	public String getDescripcionEstadoprivilegio() {
		return this.descripcionEstadoprivilegio;
	}

	public void setDescripcionEstadoprivilegio(String descripcionEstadoprivilegio) {
		this.descripcionEstadoprivilegio = descripcionEstadoprivilegio;
	}

	public Set getPrivilegios() {
		return this.privilegios;
	}

	public void setPrivilegios(Set privilegios) {
		this.privilegios = privilegios;
	}

}