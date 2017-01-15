package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EstadoNotificacionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoEstadonotificacion;
	private String nombreEstadonotificacion;
	private Set notificaciondiarias = new HashSet(0);

	public Long getPkCodigoEstadonotificacion() {
		return pkCodigoEstadonotificacion;
	}

	public void setPkCodigoEstadonotificacion(Long pkCodigoEstadonotificacion) {
		this.pkCodigoEstadonotificacion = pkCodigoEstadonotificacion;
	}

	public String getNombreEstadonotificacion() {
		return nombreEstadonotificacion;
	}

	public void setNombreEstadonotificacion(String nombreEstadonotificacion) {
		this.nombreEstadonotificacion = nombreEstadonotificacion;
	}

	public Set getNotificaciondiarias() {
		return notificaciondiarias;
	}

	public void setNotificaciondiarias(Set notificaciondiarias) {
		this.notificaciondiarias = notificaciondiarias;
	}

}
