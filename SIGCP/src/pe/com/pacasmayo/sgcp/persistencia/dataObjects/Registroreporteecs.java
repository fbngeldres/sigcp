package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Registroreporteecs entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Registroreporteecs implements java.io.Serializable {

	// Fields

	private Long pkCodigoRegistroreporteecs;
	private Date fechaRegistroreporteecs;
	private String nombreRegistroreporteecs;
	private Set notificacionproduccions = new HashSet(0);
	private Set notificacionoperacions = new HashSet(0);
	private Set datoreportes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Registroreporteecs() {
	}

	/** minimal constructor */
	public Registroreporteecs(Date fechaRegistroreporteecs, String nombreRegistroreporteecs) {
		this.fechaRegistroreporteecs = fechaRegistroreporteecs;
		this.nombreRegistroreporteecs = nombreRegistroreporteecs;
	}

	/** full constructor */
	public Registroreporteecs(Date fechaRegistroreporteecs, String nombreRegistroreporteecs, Set notificacionproduccions,
			Set notificacionoperacions, Set datoreportes) {
		this.fechaRegistroreporteecs = fechaRegistroreporteecs;
		this.nombreRegistroreporteecs = nombreRegistroreporteecs;
		this.notificacionproduccions = notificacionproduccions;
		this.notificacionoperacions = notificacionoperacions;
		this.datoreportes = datoreportes;
	}

	// Property accessors

	public Long getPkCodigoRegistroreporteecs() {
		return this.pkCodigoRegistroreporteecs;
	}

	public void setPkCodigoRegistroreporteecs(Long pkCodigoRegistroreporteecs) {
		this.pkCodigoRegistroreporteecs = pkCodigoRegistroreporteecs;
	}

	public Date getFechaRegistroreporteecs() {
		return this.fechaRegistroreporteecs;
	}

	public void setFechaRegistroreporteecs(Date fechaRegistroreporteecs) {
		this.fechaRegistroreporteecs = fechaRegistroreporteecs;
	}

	public String getNombreRegistroreporteecs() {
		return this.nombreRegistroreporteecs;
	}

	public void setNombreRegistroreporteecs(String nombreRegistroreporteecs) {
		this.nombreRegistroreporteecs = nombreRegistroreporteecs;
	}

	public Set getNotificacionproduccions() {
		return this.notificacionproduccions;
	}

	public void setNotificacionproduccions(Set notificacionproduccions) {
		this.notificacionproduccions = notificacionproduccions;
	}

	public Set getNotificacionoperacions() {
		return this.notificacionoperacions;
	}

	public void setNotificacionoperacions(Set notificacionoperacions) {
		this.notificacionoperacions = notificacionoperacions;
	}

	public Set getDatoreportes() {
		return this.datoreportes;
	}

	public void setDatoreportes(Set datoreportes) {
		this.datoreportes = datoreportes;
	}

}