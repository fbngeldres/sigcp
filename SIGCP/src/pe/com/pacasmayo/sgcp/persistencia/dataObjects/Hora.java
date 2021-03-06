package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Hora entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Hora implements java.io.Serializable {

	// Fields

	private Long pkCodigoHora;
	private Turno turno;
	private Short horaHora;
	private Set notificacionoperacions = new HashSet(0);
	private Set notificacionproduccions = new HashSet(0);
	
	private Set datoreportes = new HashSet(0);
	

	// Constructors

	/** default constructor */
	public Hora() {
	}

	/** minimal constructor */
	public Hora(Turno turno, Short horaHora) {
		this.turno = turno;
		this.horaHora = horaHora;
	}

	/** full constructor */
	public Hora(Turno turno, Short horaHora, Set notificacionoperacions, Set notificacionproduccions, 
			Set datoreportes) {
		this.turno = turno;
		this.horaHora = horaHora;
		this.notificacionoperacions = notificacionoperacions;
		this.notificacionproduccions = notificacionproduccions;
	
		this.datoreportes = datoreportes;
		
	}

	// Property accessors

	public Long getPkCodigoHora() {
		return this.pkCodigoHora;
	}

	public void setPkCodigoHora(Long pkCodigoHora) {
		this.pkCodigoHora = pkCodigoHora;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Short getHoraHora() {
		return this.horaHora;
	}

	public void setHoraHora(Short horaHora) {
		this.horaHora = horaHora;
	}

	public Set getNotificacionoperacions() {
		return this.notificacionoperacions;
	}

	public void setNotificacionoperacions(Set notificacionoperacions) {
		this.notificacionoperacions = notificacionoperacions;
	}

	public Set getNotificacionproduccions() {
		return this.notificacionproduccions;
	}

	public void setNotificacionproduccions(Set notificacionproduccions) {
		this.notificacionproduccions = notificacionproduccions;
	}



	public Set getDatoreportes() {
		return this.datoreportes;
	}

	public void setDatoreportes(Set datoreportes) {
		this.datoreportes = datoreportes;
	}

	

}