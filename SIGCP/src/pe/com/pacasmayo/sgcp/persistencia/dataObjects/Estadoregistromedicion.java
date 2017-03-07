package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoregistromedicion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoregistromedicion implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoregistromedicio;
	private String nombreEstadoregistromedicion;
	private String descripcionEstadoregistromedic;
	private Set registromedicions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoregistromedicion() {
	}

	/** minimal constructor */
	public Estadoregistromedicion(String nombreEstadoregistromedicion, String descripcionEstadoregistromedic) {
		this.nombreEstadoregistromedicion = nombreEstadoregistromedicion;
		this.descripcionEstadoregistromedic = descripcionEstadoregistromedic;
	}

	/** full constructor */
	public Estadoregistromedicion(String nombreEstadoregistromedicion, String descripcionEstadoregistromedic,
			Set registromedicions) {
		this.nombreEstadoregistromedicion = nombreEstadoregistromedicion;
		this.descripcionEstadoregistromedic = descripcionEstadoregistromedic;
		this.registromedicions = registromedicions;
	}

	// Property accessors

	public Long getPkCodigoEstadoregistromedicio() {
		return this.pkCodigoEstadoregistromedicio;
	}

	public void setPkCodigoEstadoregistromedicio(Long pkCodigoEstadoregistromedicio) {
		this.pkCodigoEstadoregistromedicio = pkCodigoEstadoregistromedicio;
	}

	public String getNombreEstadoregistromedicion() {
		return this.nombreEstadoregistromedicion;
	}

	public void setNombreEstadoregistromedicion(String nombreEstadoregistromedicion) {
		this.nombreEstadoregistromedicion = nombreEstadoregistromedicion;
	}

	public String getDescripcionEstadoregistromedic() {
		return this.descripcionEstadoregistromedic;
	}

	public void setDescripcionEstadoregistromedic(String descripcionEstadoregistromedic) {
		this.descripcionEstadoregistromedic = descripcionEstadoregistromedic;
	}

	public Set getRegistromedicions() {
		return this.registromedicions;
	}

	public void setRegistromedicions(Set registromedicions) {
		this.registromedicions = registromedicions;
	}

}