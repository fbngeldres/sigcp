package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notificaciondiaria entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Notificaciondiaria implements java.io.Serializable {

	// Fields

	private Long pkCodigoNotificaciondiaria;
	private Usuario usuarioByFkCodigoUsuarioRegistra;
	private Lineanegocio lineanegocio;
	private Usuario usuarioByFkCodigoUsuarioAprueba;
	private Usuario usuarioByFkCodigoUsuarioCierra;
	private Tablerocontrol tablerocontrol;
	private Estadonotificacion estadonotificacion;
	private Date fechaNotificaciondiaria;
	private String observacionNotificaciondiaria;
	private Date fechaAprobacionNotificaciondi;


	private Set notificacionproduccions = new HashSet(0);
	private Set notificacionoperacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Notificaciondiaria() {
	}

	/** minimal constructor */
	public Notificaciondiaria(Usuario usuarioByFkCodigoUsuarioRegistra, Lineanegocio lineanegocio,
			Estadonotificacion estadonotificacion, Date fechaNotificaciondiaria) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.lineanegocio = lineanegocio;
		this.estadonotificacion = estadonotificacion;
		this.fechaNotificaciondiaria = fechaNotificaciondiaria;
	}

	/** full constructor */
	public Notificaciondiaria(Usuario usuarioByFkCodigoUsuarioRegistra, Lineanegocio lineanegocio,
			Usuario usuarioByFkCodigoUsuarioAprueba, Tablerocontrol tablerocontrol, Estadonotificacion estadonotificacion,
			Date fechaNotificaciondiaria, String observacionNotificaciondiaria, Date fechaAprobacionNotificaciondi,
			Set notificacionproduccions, Set notificacionoperacions,
			Usuario usuarioByFkCodigoUsuarioCierra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.lineanegocio = lineanegocio;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.tablerocontrol = tablerocontrol;
		this.estadonotificacion = estadonotificacion;
		this.fechaNotificaciondiaria = fechaNotificaciondiaria;
		this.observacionNotificaciondiaria = observacionNotificaciondiaria;
		this.fechaAprobacionNotificaciondi = fechaAprobacionNotificaciondi;
		
		this.notificacionproduccions = notificacionproduccions;
		this.notificacionoperacions = notificacionoperacions;
		this.usuarioByFkCodigoUsuarioCierra = usuarioByFkCodigoUsuarioCierra;
	}

	// Property accessors

	public Long getPkCodigoNotificaciondiaria() {
		return this.pkCodigoNotificaciondiaria;
	}

	public void setPkCodigoNotificaciondiaria(Long pkCodigoNotificaciondiaria) {
		this.pkCodigoNotificaciondiaria = pkCodigoNotificaciondiaria;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistra() {
		return this.usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(Usuario usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAprueba() {
		return this.usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(Usuario usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public Tablerocontrol getTablerocontrol() {
		return this.tablerocontrol;
	}

	public void setTablerocontrol(Tablerocontrol tablerocontrol) {
		this.tablerocontrol = tablerocontrol;
	}

	public Estadonotificacion getEstadonotificacion() {
		return this.estadonotificacion;
	}

	public void setEstadonotificacion(Estadonotificacion estadonotificacion) {
		this.estadonotificacion = estadonotificacion;
	}

	public Date getFechaNotificaciondiaria() {
		return this.fechaNotificaciondiaria;
	}

	public void setFechaNotificaciondiaria(Date fechaNotificaciondiaria) {
		this.fechaNotificaciondiaria = fechaNotificaciondiaria;
	}

	public String getObservacionNotificaciondiaria() {
		return this.observacionNotificaciondiaria;
	}

	public void setObservacionNotificaciondiaria(String observacionNotificaciondiaria) {
		this.observacionNotificaciondiaria = observacionNotificaciondiaria;
	}

	public Date getFechaAprobacionNotificaciondi() {
		return this.fechaAprobacionNotificaciondi;
	}

	public void setFechaAprobacionNotificaciondi(Date fechaAprobacionNotificaciondi) {
		this.fechaAprobacionNotificaciondi = fechaAprobacionNotificaciondi;
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

	public Usuario getUsuarioByFkCodigoUsuarioCierra() {
		return this.usuarioByFkCodigoUsuarioCierra;
	}

	public void setUsuarioByFkCodigoUsuarioCierra(Usuario usuarioByFkCodigoUsuarioCierra) {
		this.usuarioByFkCodigoUsuarioCierra = usuarioByFkCodigoUsuarioCierra;
	}

}