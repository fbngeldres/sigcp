package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Nivelcargo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Nivelcargo implements java.io.Serializable {

	// Fields

	private Long pkCodigoNivelcargo;
	private String nombreNivelcargo;
	private String descripcionNivelcargo;
	private Set cargos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Nivelcargo() {
	}

	/** minimal constructor */
	public Nivelcargo(String nombreNivelcargo, String descripcionNivelcargo) {
		this.nombreNivelcargo = nombreNivelcargo;
		this.descripcionNivelcargo = descripcionNivelcargo;
	}

	/** full constructor */
	public Nivelcargo(String nombreNivelcargo, String descripcionNivelcargo, Set cargos) {
		this.nombreNivelcargo = nombreNivelcargo;
		this.descripcionNivelcargo = descripcionNivelcargo;
		this.cargos = cargos;
	}

	// Property accessors

	public Long getPkCodigoNivelcargo() {
		return this.pkCodigoNivelcargo;
	}

	public void setPkCodigoNivelcargo(Long pkCodigoNivelcargo) {
		this.pkCodigoNivelcargo = pkCodigoNivelcargo;
	}

	public String getNombreNivelcargo() {
		return this.nombreNivelcargo;
	}

	public void setNombreNivelcargo(String nombreNivelcargo) {
		this.nombreNivelcargo = nombreNivelcargo;
	}

	public String getDescripcionNivelcargo() {
		return this.descripcionNivelcargo;
	}

	public void setDescripcionNivelcargo(String descripcionNivelcargo) {
		this.descripcionNivelcargo = descripcionNivelcargo;
	}

	public Set getCargos() {
		return this.cargos;
	}

	public void setCargos(Set cargos) {
		this.cargos = cargos;
	}

}