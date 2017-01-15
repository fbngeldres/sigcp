package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Date;

public class NotificaciondiariaDTO implements java.io.Serializable {

	private static final long serialVersionUID = -2794787142949697454L;
	private Long pkCodigoNotificaciondiaria;
	private Long pkCodigoUsuarioRegistra;
	private Long pkCodigoTiponotificacion;
	private Long pkCodigoLineanegocio;
	private Long pkCodigoUsuarioAprueba;
	private Long pkCodigoTablerocontrol;
	private Long pkCodigoEstadonotificacion;
	private Date fechaNotificaciondiaria;
	private String observacionNotificaciondiaria;
	private Date fechaAprobacionNotificaciondi;

	public NotificaciondiariaDTO() {

	}

	/**
	 * @return the pkCodigoNotificaciondiaria
	 */
	public Long getPkCodigoNotificaciondiaria() {
		return pkCodigoNotificaciondiaria;
	}

	/**
	 * @param pkCodigoNotificaciondiaria the pkCodigoNotificaciondiaria to set
	 */
	public void setPkCodigoNotificaciondiaria(Long pkCodigoNotificaciondiaria) {
		this.pkCodigoNotificaciondiaria = pkCodigoNotificaciondiaria;
	}

	/**
	 * @return the pkCodigoUsuarioRegistra
	 */
	public Long getPkCodigoUsuarioRegistra() {
		return pkCodigoUsuarioRegistra;
	}

	/**
	 * @param pkCodigoUsuarioRegistra the pkCodigoUsuarioRegistra to set
	 */
	public void setPkCodigoUsuarioRegistra(Long pkCodigoUsuarioRegistra) {
		this.pkCodigoUsuarioRegistra = pkCodigoUsuarioRegistra;
	}

	/**
	 * @return the pkCodigoTiponotificacion
	 */
	public Long getPkCodigoTiponotificacion() {
		return pkCodigoTiponotificacion;
	}

	/**
	 * @param pkCodigoTiponotificacion the pkCodigoTiponotificacion to set
	 */
	public void setPkCodigoTiponotificacion(Long pkCodigoTiponotificacion) {
		this.pkCodigoTiponotificacion = pkCodigoTiponotificacion;
	}

	/**
	 * @return the pkCodigoLineanegocio
	 */
	public Long getPkCodigoLineanegocio() {
		return pkCodigoLineanegocio;
	}

	/**
	 * @param pkCodigoLineanegocio the pkCodigoLineanegocio to set
	 */
	public void setPkCodigoLineanegocio(Long pkCodigoLineanegocio) {
		this.pkCodigoLineanegocio = pkCodigoLineanegocio;
	}

	/**
	 * @return the pkCodigoUsuarioAprueba
	 */
	public Long getPkCodigoUsuarioAprueba() {
		return pkCodigoUsuarioAprueba;
	}

	/**
	 * @param pkCodigoUsuarioAprueba the pkCodigoUsuarioAprueba to set
	 */
	public void setPkCodigoUsuarioAprueba(Long pkCodigoUsuarioAprueba) {
		this.pkCodigoUsuarioAprueba = pkCodigoUsuarioAprueba;
	}

	/**
	 * @return the pkCodigoTablerocontrol
	 */
	public Long getPkCodigoTablerocontrol() {
		return pkCodigoTablerocontrol;
	}

	/**
	 * @param pkCodigoTablerocontrol the pkCodigoTablerocontrol to set
	 */
	public void setPkCodigoTablerocontrol(Long pkCodigoTablerocontrol) {
		this.pkCodigoTablerocontrol = pkCodigoTablerocontrol;
	}

	/**
	 * @return the pkCodigoEstadonotificacion
	 */
	public Long getPkCodigoEstadonotificacion() {
		return pkCodigoEstadonotificacion;
	}

	/**
	 * @param pkCodigoEstadonotificacion the pkCodigoEstadonotificacion to set
	 */
	public void setPkCodigoEstadonotificacion(Long pkCodigoEstadonotificacion) {
		this.pkCodigoEstadonotificacion = pkCodigoEstadonotificacion;
	}

	/**
	 * @return the fechaNotificaciondiaria
	 */
	public Date getFechaNotificaciondiaria() {
		return fechaNotificaciondiaria;
	}

	/**
	 * @param fechaNotificaciondiaria the fechaNotificaciondiaria to set
	 */
	public void setFechaNotificaciondiaria(Date fechaNotificaciondiaria) {
		this.fechaNotificaciondiaria = fechaNotificaciondiaria;
	}

	/**
	 * @return the observacionNotificaciondiaria
	 */
	public String getObservacionNotificaciondiaria() {
		return observacionNotificaciondiaria;
	}

	/**
	 * @param observacionNotificaciondiaria the observacionNotificaciondiaria to
	 *            set
	 */
	public void setObservacionNotificaciondiaria(String observacionNotificaciondiaria) {
		this.observacionNotificaciondiaria = observacionNotificaciondiaria;
	}

	/**
	 * @return the fechaAprobacionNotificaciondi
	 */
	public Date getFechaAprobacionNotificaciondi() {
		return fechaAprobacionNotificaciondi;
	}

	/**
	 * @param fechaAprobacionNotificaciondi the fechaAprobacionNotificaciondi to
	 *            set
	 */
	public void setFechaAprobacionNotificaciondi(Date fechaAprobacionNotificaciondi) {
		this.fechaAprobacionNotificaciondi = fechaAprobacionNotificaciondi;
	}
}
