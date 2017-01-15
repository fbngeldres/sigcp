package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Turno entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Turno implements java.io.Serializable {

	// Fields

	private Long pkCodigoTurno;
	private Unidad unidad;
	private String nombreTurno;
	private Long codigoSccTurno;
	private Short horaInicioTurno;
	private Short horaFinTurno;
	private Set notificacioncanteras = new HashSet(0);
	private Set ingresoproductoprocesos = new HashSet(0);
	private Set horas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Turno() {
	}

	/** minimal constructor */
	public Turno(Unidad unidad, String nombreTurno, Short horaInicioTurno, Short horaFinTurno) {
		this.unidad = unidad;
		this.nombreTurno = nombreTurno;
		this.horaInicioTurno = horaInicioTurno;
		this.horaFinTurno = horaFinTurno;
	}

	/** full constructor */
	public Turno(Unidad unidad, String nombreTurno, Long codigoSccTurno, Short horaInicioTurno, Short horaFinTurno,
			Set notificacioncanteras, Set ingresoproductoprocesos, Set horas) {
		this.unidad = unidad;
		this.nombreTurno = nombreTurno;
		this.codigoSccTurno = codigoSccTurno;
		this.horaInicioTurno = horaInicioTurno;
		this.horaFinTurno = horaFinTurno;
		this.notificacioncanteras = notificacioncanteras;
		this.ingresoproductoprocesos = ingresoproductoprocesos;
		this.horas = horas;
	}

	// Property accessors

	public Long getPkCodigoTurno() {
		return this.pkCodigoTurno;
	}

	public void setPkCodigoTurno(Long pkCodigoTurno) {
		this.pkCodigoTurno = pkCodigoTurno;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public String getNombreTurno() {
		return this.nombreTurno;
	}

	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

	public Long getCodigoSccTurno() {
		return this.codigoSccTurno;
	}

	public void setCodigoSccTurno(Long codigoSccTurno) {
		this.codigoSccTurno = codigoSccTurno;
	}

	public Short getHoraInicioTurno() {
		return this.horaInicioTurno;
	}

	public void setHoraInicioTurno(Short horaInicioTurno) {
		this.horaInicioTurno = horaInicioTurno;
	}

	public Short getHoraFinTurno() {
		return this.horaFinTurno;
	}

	public void setHoraFinTurno(Short horaFinTurno) {
		this.horaFinTurno = horaFinTurno;
	}

	public Set getNotificacioncanteras() {
		return this.notificacioncanteras;
	}

	public void setNotificacioncanteras(Set notificacioncanteras) {
		this.notificacioncanteras = notificacioncanteras;
	}

	public Set getIngresoproductoprocesos() {
		return this.ingresoproductoprocesos;
	}

	public void setIngresoproductoprocesos(Set ingresoproductoprocesos) {
		this.ingresoproductoprocesos = ingresoproductoprocesos;
	}

	public Set getHoras() {
		return this.horas;
	}

	public void setHoras(Set horas) {
		this.horas = horas;
	}

}