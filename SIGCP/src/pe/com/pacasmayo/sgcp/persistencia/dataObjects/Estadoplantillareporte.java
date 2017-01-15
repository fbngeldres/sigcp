package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoplantillareporte entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoplantillareporte implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoplantillareport;
	private String nombreEstadoplantillareporte;
	private Set plantillareportes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoplantillareporte() {
	}

	/** minimal constructor */
	public Estadoplantillareporte(String nombreEstadoplantillareporte) {
		this.nombreEstadoplantillareporte = nombreEstadoplantillareporte;
	}

	/** full constructor */
	public Estadoplantillareporte(String nombreEstadoplantillareporte, Set plantillareportes) {
		this.nombreEstadoplantillareporte = nombreEstadoplantillareporte;
		this.plantillareportes = plantillareportes;
	}

	// Property accessors

	public Long getPkCodigoEstadoplantillareport() {
		return this.pkCodigoEstadoplantillareport;
	}

	public void setPkCodigoEstadoplantillareport(Long pkCodigoEstadoplantillareport) {
		this.pkCodigoEstadoplantillareport = pkCodigoEstadoplantillareport;
	}

	public String getNombreEstadoplantillareporte() {
		return this.nombreEstadoplantillareporte;
	}

	public void setNombreEstadoplantillareporte(String nombreEstadoplantillareporte) {
		this.nombreEstadoplantillareporte = nombreEstadoplantillareporte;
	}

	public Set getPlantillareportes() {
		return this.plantillareportes;
	}

	public void setPlantillareportes(Set plantillareportes) {
		this.plantillareportes = plantillareportes;
	}

}