package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Actividad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Actividad implements java.io.Serializable {

	// Fields

	private Long pkCodigoActividad;
	private Estadoactividad estadoactividad;
	private Proceso proceso;
	private String nombreActividad;
	private String descripcionActividad;
	private Long codigoSccActividad;
	private Double metrosPerforaActividad;
	private Set configuracionreceptors = new HashSet(0);
	private Set receptors = new HashSet(0);
	private Set operacions = new HashSet(0);
	private Set notificacioncanteras = new HashSet(0);

	// Constructors

	/** default constructor */
	public Actividad() {
	}

	/** minimal constructor */
	public Actividad(Estadoactividad estadoactividad, Proceso proceso, String nombreActividad) {
		this.estadoactividad = estadoactividad;
		this.proceso = proceso;
		this.nombreActividad = nombreActividad;
	}

	/** full constructor */
	public Actividad(Estadoactividad estadoactividad, Proceso proceso, String nombreActividad, String descripcionActividad,
			Long codigoSccActividad, Double metrosPerforaActividad, Set configuracionreceptors, Set receptors, Set operacions,
			Set notificacioncanteras) {
		this.estadoactividad = estadoactividad;
		this.proceso = proceso;
		this.nombreActividad = nombreActividad;
		this.descripcionActividad = descripcionActividad;
		this.codigoSccActividad = codigoSccActividad;
		this.metrosPerforaActividad = metrosPerforaActividad;
		this.configuracionreceptors = configuracionreceptors;
		this.receptors = receptors;
		this.operacions = operacions;
		this.notificacioncanteras = notificacioncanteras;
	}

	// Property accessors

	public Long getPkCodigoActividad() {
		return this.pkCodigoActividad;
	}

	public void setPkCodigoActividad(Long pkCodigoActividad) {
		this.pkCodigoActividad = pkCodigoActividad;
	}

	public Estadoactividad getEstadoactividad() {
		return this.estadoactividad;
	}

	public void setEstadoactividad(Estadoactividad estadoactividad) {
		this.estadoactividad = estadoactividad;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public String getNombreActividad() {
		return this.nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcionActividad() {
		return this.descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public Long getCodigoSccActividad() {
		return this.codigoSccActividad;
	}

	public void setCodigoSccActividad(Long codigoSccActividad) {
		this.codigoSccActividad = codigoSccActividad;
	}

	public Double getMetrosPerforaActividad() {
		return this.metrosPerforaActividad;
	}

	public void setMetrosPerforaActividad(Double metrosPerforaActividad) {
		this.metrosPerforaActividad = metrosPerforaActividad;
	}

	public Set getConfiguracionreceptors() {
		return this.configuracionreceptors;
	}

	public void setConfiguracionreceptors(Set configuracionreceptors) {
		this.configuracionreceptors = configuracionreceptors;
	}

	public Set getReceptors() {
		return this.receptors;
	}

	public void setReceptors(Set receptors) {
		this.receptors = receptors;
	}

	public Set getOperacions() {
		return this.operacions;
	}

	public void setOperacions(Set operacions) {
		this.operacions = operacions;
	}

	public Set getNotificacioncanteras() {
		return this.notificacioncanteras;
	}

	public void setNotificacioncanteras(Set notificacioncanteras) {
		this.notificacioncanteras = notificacioncanteras;
	}

}