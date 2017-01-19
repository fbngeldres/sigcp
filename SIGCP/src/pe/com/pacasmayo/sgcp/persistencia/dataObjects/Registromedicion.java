package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Registromedicion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Registromedicion implements java.io.Serializable {

	// Fields

	private Long pkCodigoRegistromedicion;

	private Proceso proceso;
	private Usuario usuarioByFkCodigoUsuarioRegistra;
	private Integer anoRegistromedicion;
	private Short mesRegistromedicion;
	private Date fechaRegistromedicion;
	private Set medicions = new HashSet(0);
	private Usuario usuarioByFkCodigoUsuarioAprueba;
	private Tipomedioalmacenamiento tipomedioalmacenamiento;
	private Estadoregistromedicion estadoregistromedicion;
	public Long getPkCodigoRegistromedicion() {
		return pkCodigoRegistromedicion;
	}

	public void setPkCodigoRegistromedicion(Long pkCodigoRegistromedicion) {
		this.pkCodigoRegistromedicion = pkCodigoRegistromedicion;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Integer getAnoRegistromedicion() {
		return anoRegistromedicion;
	}

	public void setAnoRegistromedicion(Integer anoRegistromedicion) {
		this.anoRegistromedicion = anoRegistromedicion;
	}

	public Short getMesRegistromedicion() {
		return mesRegistromedicion;
	}

	public void setMesRegistromedicion(Short mesRegistromedicion) {
		this.mesRegistromedicion = mesRegistromedicion;
	}

	public Date getFechaRegistromedicion() {
		return fechaRegistromedicion;
	}

	public void setFechaRegistromedicion(Date fechaRegistromedicion) {
		this.fechaRegistromedicion = fechaRegistromedicion;
	}

	public Set getMedicions() {
		return medicions;
	}

	public void setMedicions(Set medicions) {
		this.medicions = medicions;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistra() {
		return usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(
			Usuario usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public Tipomedioalmacenamiento getTipomedioalmacenamiento() {
		return tipomedioalmacenamiento;
	}

	public void setTipomedioalmacenamiento(
			Tipomedioalmacenamiento tipomedioalmacenamiento) {
		this.tipomedioalmacenamiento = tipomedioalmacenamiento;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAprueba() {
		return usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(
			Usuario usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}


	public Estadoregistromedicion getEstadoregistromedicion() {
		return estadoregistromedicion;
	}

	public void setEstadoregistromedicion(
			Estadoregistromedicion estadoregistromedicion) {
		this.estadoregistromedicion = estadoregistromedicion;
	}

	public Registromedicion(Long pkCodigoRegistromedicion, Proceso proceso,
			Usuario usuarioByFkCodigoUsuarioRegistra,
			Integer anoRegistromedicion, Short mesRegistromedicion,
			Date fechaRegistromedicion, Set medicions,
			Usuario usuarioByFkCodigoUsuarioAprueba,
			Tipomedioalmacenamiento tipomedioalmacenamiento,
			Estadoregistromedicion estadoregistromedicion) {
		super();
		this.pkCodigoRegistromedicion = pkCodigoRegistromedicion;
		this.proceso = proceso;
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.anoRegistromedicion = anoRegistromedicion;
		this.mesRegistromedicion = mesRegistromedicion;
		this.fechaRegistromedicion = fechaRegistromedicion;
		this.medicions = medicions;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.tipomedioalmacenamiento = tipomedioalmacenamiento;
		this.estadoregistromedicion = estadoregistromedicion;
	}

}