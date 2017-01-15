package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadousuario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadousuario implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadousuario;
	private String nombreEstadousuario;
	private String descripcionEstadousuario;
	private Set usuarios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadousuario() {
	}

	/** minimal constructor */
	public Estadousuario(String nombreEstadousuario) {
		this.nombreEstadousuario = nombreEstadousuario;
	}

	/** full constructor */
	public Estadousuario(String nombreEstadousuario, String descripcionEstadousuario, Set usuarios) {
		this.nombreEstadousuario = nombreEstadousuario;
		this.descripcionEstadousuario = descripcionEstadousuario;
		this.usuarios = usuarios;
	}

	// Property accessors

	public Long getPkCodigoEstadousuario() {
		return this.pkCodigoEstadousuario;
	}

	public void setPkCodigoEstadousuario(Long pkCodigoEstadousuario) {
		this.pkCodigoEstadousuario = pkCodigoEstadousuario;
	}

	public String getNombreEstadousuario() {
		return this.nombreEstadousuario;
	}

	public void setNombreEstadousuario(String nombreEstadousuario) {
		this.nombreEstadousuario = nombreEstadousuario;
	}

	public String getDescripcionEstadousuario() {
		return this.descripcionEstadousuario;
	}

	public void setDescripcionEstadousuario(String descripcionEstadousuario) {
		this.descripcionEstadousuario = descripcionEstadousuario;
	}

	public Set getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

}