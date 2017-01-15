package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadopartediario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadopartediario implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadopartediario;
	private String nombreEstadopartediario;
	private String descripcionEstadopartediario;
	private Set partediarios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadopartediario() {
	}

	/** minimal constructor */
	public Estadopartediario(String nombreEstadopartediario, String descripcionEstadopartediario) {
		this.nombreEstadopartediario = nombreEstadopartediario;
		this.descripcionEstadopartediario = descripcionEstadopartediario;
	}

	/** full constructor */
	public Estadopartediario(String nombreEstadopartediario, String descripcionEstadopartediario, Set partediarios) {
		this.nombreEstadopartediario = nombreEstadopartediario;
		this.descripcionEstadopartediario = descripcionEstadopartediario;
		this.partediarios = partediarios;
	}

	// Property accessors

	public Long getPkCodigoEstadopartediario() {
		return this.pkCodigoEstadopartediario;
	}

	public void setPkCodigoEstadopartediario(Long pkCodigoEstadopartediario) {
		this.pkCodigoEstadopartediario = pkCodigoEstadopartediario;
	}

	public String getNombreEstadopartediario() {
		return this.nombreEstadopartediario;
	}

	public void setNombreEstadopartediario(String nombreEstadopartediario) {
		this.nombreEstadopartediario = nombreEstadopartediario;
	}

	public String getDescripcionEstadopartediario() {
		return this.descripcionEstadopartediario;
	}

	public void setDescripcionEstadopartediario(String descripcionEstadopartediario) {
		this.descripcionEstadopartediario = descripcionEstadopartediario;
	}

	public Set getPartediarios() {
		return this.partediarios;
	}

	public void setPartediarios(Set partediarios) {
		this.partediarios = partediarios;
	}

}