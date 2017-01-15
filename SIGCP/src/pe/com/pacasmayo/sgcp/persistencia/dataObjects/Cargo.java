package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Cargo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Cargo implements java.io.Serializable {

	// Fields

	private Long pkCodigoCargo;
	private Nivelcargo nivelcargo;
	private String nombreCargo;
	private Set unidadcargos = new HashSet(0);
	private Set sociedadcargos = new HashSet(0);
	private Set areacargos = new HashSet(0);
	private Set personas = new HashSet(0);
	private Set divisioncargos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Cargo() {
	}

	/** minimal constructor */
	public Cargo(Nivelcargo nivelcargo, String nombreCargo) {
		this.nivelcargo = nivelcargo;
		this.nombreCargo = nombreCargo;
	}

	/** full constructor */
	public Cargo(Nivelcargo nivelcargo, String nombreCargo, Set unidadcargos, Set sociedadcargos, Set areacargos, Set personas,
			Set divisioncargos) {
		this.nivelcargo = nivelcargo;
		this.nombreCargo = nombreCargo;
		this.unidadcargos = unidadcargos;
		this.sociedadcargos = sociedadcargos;
		this.areacargos = areacargos;
		this.personas = personas;
		this.divisioncargos = divisioncargos;
	}

	// Property accessors

	public Long getPkCodigoCargo() {
		return this.pkCodigoCargo;
	}

	public void setPkCodigoCargo(Long pkCodigoCargo) {
		this.pkCodigoCargo = pkCodigoCargo;
	}

	public Nivelcargo getNivelcargo() {
		return this.nivelcargo;
	}

	public void setNivelcargo(Nivelcargo nivelcargo) {
		this.nivelcargo = nivelcargo;
	}

	public String getNombreCargo() {
		return this.nombreCargo;
	}

	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}

	public Set getUnidadcargos() {
		return this.unidadcargos;
	}

	public void setUnidadcargos(Set unidadcargos) {
		this.unidadcargos = unidadcargos;
	}

	public Set getSociedadcargos() {
		return this.sociedadcargos;
	}

	public void setSociedadcargos(Set sociedadcargos) {
		this.sociedadcargos = sociedadcargos;
	}

	public Set getAreacargos() {
		return this.areacargos;
	}

	public void setAreacargos(Set areacargos) {
		this.areacargos = areacargos;
	}

	public Set getPersonas() {
		return this.personas;
	}

	public void setPersonas(Set personas) {
		this.personas = personas;
	}

	public Set getDivisioncargos() {
		return this.divisioncargos;
	}

	public void setDivisioncargos(Set divisioncargos) {
		this.divisioncargos = divisioncargos;
	}

}