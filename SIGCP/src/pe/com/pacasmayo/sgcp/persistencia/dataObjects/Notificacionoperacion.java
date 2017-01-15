package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notificacionoperacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Notificacionoperacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoNotificacionoperacion;
	private Hora hora;
	private Registroreporteecs registroreporteecs;
	private Puestotrabajo puestotrabajo;
	private Notificaciondiaria notificaciondiaria;
	private Date fechaNotificacionoperacion;
	private Set variablevalornotificacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Notificacionoperacion() {
	}

	/** minimal constructor */
	public Notificacionoperacion(Hora hora, Registroreporteecs registroreporteecs, Puestotrabajo puestotrabajo,
			Notificaciondiaria notificaciondiaria, Date fechaNotificacionoperacion) {
		this.hora = hora;
		this.registroreporteecs = registroreporteecs;
		this.puestotrabajo = puestotrabajo;
		this.notificaciondiaria = notificaciondiaria;
		this.fechaNotificacionoperacion = fechaNotificacionoperacion;
	}

	/** full constructor */
	public Notificacionoperacion(Hora hora, Registroreporteecs registroreporteecs, Puestotrabajo puestotrabajo,
			Notificaciondiaria notificaciondiaria, Date fechaNotificacionoperacion, Set variablevalornotificacions) {
		this.hora = hora;
		this.registroreporteecs = registroreporteecs;
		this.puestotrabajo = puestotrabajo;
		this.notificaciondiaria = notificaciondiaria;
		this.fechaNotificacionoperacion = fechaNotificacionoperacion;
		this.variablevalornotificacions = variablevalornotificacions;
	}

	// Property accessors

	public Long getPkCodigoNotificacionoperacion() {
		return this.pkCodigoNotificacionoperacion;
	}

	public void setPkCodigoNotificacionoperacion(Long pkCodigoNotificacionoperacion) {
		this.pkCodigoNotificacionoperacion = pkCodigoNotificacionoperacion;
	}

	public Hora getHora() {
		return this.hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Registroreporteecs getRegistroreporteecs() {
		return this.registroreporteecs;
	}

	public void setRegistroreporteecs(Registroreporteecs registroreporteecs) {
		this.registroreporteecs = registroreporteecs;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Notificaciondiaria getNotificaciondiaria() {
		return this.notificaciondiaria;
	}

	public void setNotificaciondiaria(Notificaciondiaria notificaciondiaria) {
		this.notificaciondiaria = notificaciondiaria;
	}

	public Date getFechaNotificacionoperacion() {
		return this.fechaNotificacionoperacion;
	}

	public void setFechaNotificacionoperacion(Date fechaNotificacionoperacion) {
		this.fechaNotificacionoperacion = fechaNotificacionoperacion;
	}

	public Set getVariablevalornotificacions() {
		return this.variablevalornotificacions;
	}

	public void setVariablevalornotificacions(Set variablevalornotificacions) {
		this.variablevalornotificacions = variablevalornotificacions;
	}

}