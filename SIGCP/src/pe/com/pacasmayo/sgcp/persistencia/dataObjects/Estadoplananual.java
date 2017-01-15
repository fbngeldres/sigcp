package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoplananual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoplananual implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoplananual;
	private String nombreEstadoplan;
	private String descripcionEstadoplan;
	private Set plananuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoplananual() {
	}

	/** minimal constructor */
	public Estadoplananual(String nombreEstadoplan) {
		this.nombreEstadoplan = nombreEstadoplan;
	}

	/** full constructor */
	public Estadoplananual(String nombreEstadoplan, String descripcionEstadoplan, Set plananuals) {
		this.nombreEstadoplan = nombreEstadoplan;
		this.descripcionEstadoplan = descripcionEstadoplan;
		this.plananuals = plananuals;
	}

	// Property accessors

	public Long getPkCodigoEstadoplananual() {
		return this.pkCodigoEstadoplananual;
	}

	public void setPkCodigoEstadoplananual(Long pkCodigoEstadoplananual) {
		this.pkCodigoEstadoplananual = pkCodigoEstadoplananual;
	}

	public String getNombreEstadoplan() {
		return this.nombreEstadoplan;
	}

	public void setNombreEstadoplan(String nombreEstadoplan) {
		this.nombreEstadoplan = nombreEstadoplan;
	}

	public String getDescripcionEstadoplan() {
		return this.descripcionEstadoplan;
	}

	public void setDescripcionEstadoplan(String descripcionEstadoplan) {
		this.descripcionEstadoplan = descripcionEstadoplan;
	}

	public Set getPlananuals() {
		return this.plananuals;
	}

	public void setPlananuals(Set plananuals) {
		this.plananuals = plananuals;
	}

}