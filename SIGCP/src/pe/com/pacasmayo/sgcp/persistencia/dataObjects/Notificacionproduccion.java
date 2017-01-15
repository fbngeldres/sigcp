package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notificacionproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Notificacionproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoNotificacionproduccio;
	private Hora hora;
	private Registroreporteecs registroreporteecs;
	private Puestotrabajo puestotrabajo;
	private Ordenproduccion ordenproduccion;
	private Notificaciondiaria notificaciondiaria;
	private Medioalmacenamiento medioalmacenamiento;
	private String observacionNotificacionproducc;
	private Boolean produccionlavadoNotificacionpr;
	private Boolean cambioproduccionNotificacionpr;
	private String horaCambioproduccionNotificac;
	private Date fechaNotificacionproduccion;

	private Double horaEcsNotificacionproduccion;
	private Double energiaEcsNotificacionproduccion;
	private Double aguaEcsNotificacionproduccion;
	private Plantillaproducto plantillaproducto;
	private Boolean escalentamientoNotificacionpr;

	private Set componentenotificacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Notificacionproduccion() {
	}

	/** minimal constructor */
	public Notificacionproduccion(Hora hora, Puestotrabajo puestotrabajo, Ordenproduccion ordenproduccion,
			Notificaciondiaria notificaciondiaria, Medioalmacenamiento medioalmacenamiento,
			Boolean produccionlavadoNotificacionpr, Boolean cambioproduccionNotificacionpr, Date fechaNotificacionproduccion) {
		this.hora = hora;
		this.puestotrabajo = puestotrabajo;
		this.ordenproduccion = ordenproduccion;
		this.notificaciondiaria = notificaciondiaria;
		this.medioalmacenamiento = medioalmacenamiento;
		this.produccionlavadoNotificacionpr = produccionlavadoNotificacionpr;
		this.cambioproduccionNotificacionpr = cambioproduccionNotificacionpr;
		this.fechaNotificacionproduccion = fechaNotificacionproduccion;
	}

	/** full constructor */
	public Notificacionproduccion(Hora hora, Registroreporteecs registroreporteecs, Puestotrabajo puestotrabajo,
			Ordenproduccion ordenproduccion, Notificaciondiaria notificaciondiaria, Medioalmacenamiento medioalmacenamiento,
			String observacionNotificacionproducc, Boolean produccionlavadoNotificacionpr,
			Boolean cambioproduccionNotificacionpr, String horaCambioproduccionNotificac, Date fechaNotificacionproduccion,
			Set componentenotificacions, Boolean escalentamientoNotificacionpr) {
		this.hora = hora;
		this.registroreporteecs = registroreporteecs;
		this.puestotrabajo = puestotrabajo;
		this.ordenproduccion = ordenproduccion;
		this.notificaciondiaria = notificaciondiaria;
		this.medioalmacenamiento = medioalmacenamiento;
		this.observacionNotificacionproducc = observacionNotificacionproducc;
		this.produccionlavadoNotificacionpr = produccionlavadoNotificacionpr;
		this.cambioproduccionNotificacionpr = cambioproduccionNotificacionpr;
		this.horaCambioproduccionNotificac = horaCambioproduccionNotificac;
		this.fechaNotificacionproduccion = fechaNotificacionproduccion;
		this.componentenotificacions = componentenotificacions;
		this.escalentamientoNotificacionpr = escalentamientoNotificacionpr;
	}

	// Property accessors

	public Long getPkCodigoNotificacionproduccio() {
		return this.pkCodigoNotificacionproduccio;
	}

	public void setPkCodigoNotificacionproduccio(Long pkCodigoNotificacionproduccio) {
		this.pkCodigoNotificacionproduccio = pkCodigoNotificacionproduccio;
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

	public Ordenproduccion getOrdenproduccion() {
		return this.ordenproduccion;
	}

	public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Notificaciondiaria getNotificaciondiaria() {
		return this.notificaciondiaria;
	}

	public void setNotificaciondiaria(Notificaciondiaria notificaciondiaria) {
		this.notificaciondiaria = notificaciondiaria;
	}

	public Medioalmacenamiento getMedioalmacenamiento() {
		return this.medioalmacenamiento;
	}

	public void setMedioalmacenamiento(Medioalmacenamiento medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public String getObservacionNotificacionproducc() {
		return this.observacionNotificacionproducc;
	}

	public void setObservacionNotificacionproducc(String observacionNotificacionproducc) {
		this.observacionNotificacionproducc = observacionNotificacionproducc;
	}

	public Boolean getProduccionlavadoNotificacionpr() {
		return this.produccionlavadoNotificacionpr;
	}

	public void setProduccionlavadoNotificacionpr(Boolean produccionlavadoNotificacionpr) {
		this.produccionlavadoNotificacionpr = produccionlavadoNotificacionpr;
	}

	public Boolean getCambioproduccionNotificacionpr() {
		return this.cambioproduccionNotificacionpr;
	}

	public void setCambioproduccionNotificacionpr(Boolean cambioproduccionNotificacionpr) {
		this.cambioproduccionNotificacionpr = cambioproduccionNotificacionpr;
	}

	public String getHoraCambioproduccionNotificac() {
		return this.horaCambioproduccionNotificac;
	}

	public void setHoraCambioproduccionNotificac(String horaCambioproduccionNotificac) {
		this.horaCambioproduccionNotificac = horaCambioproduccionNotificac;
	}

	public Date getFechaNotificacionproduccion() {
		return this.fechaNotificacionproduccion;
	}

	public void setFechaNotificacionproduccion(Date fechaNotificacionproduccion) {
		this.fechaNotificacionproduccion = fechaNotificacionproduccion;
	}

	public Set getComponentenotificacions() {
		return this.componentenotificacions;
	}

	public void setComponentenotificacions(Set componentenotificacions) {
		this.componentenotificacions = componentenotificacions;
	}

	public Double getHoraEcsNotificacionproduccion() {
		return horaEcsNotificacionproduccion;
	}

	public void setHoraEcsNotificacionproduccion(Double horaEcsNotificacionproduccion) {
		this.horaEcsNotificacionproduccion = horaEcsNotificacionproduccion;
	}

	public Double getEnergiaEcsNotificacionproduccion() {
		return energiaEcsNotificacionproduccion;
	}

	public void setEnergiaEcsNotificacionproduccion(Double energiaEcsNotificacionproduccion) {
		this.energiaEcsNotificacionproduccion = energiaEcsNotificacionproduccion;
	}

	public Double getAguaEcsNotificacionproduccion() {
		return aguaEcsNotificacionproduccion;
	}

	public void setAguaEcsNotificacionproduccion(Double aguaEcsNotificacionproduccion) {
		this.aguaEcsNotificacionproduccion = aguaEcsNotificacionproduccion;
	}

	public Plantillaproducto getPlantillaproducto() {
		return plantillaproducto;
	}

	public void setPlantillaproducto(Plantillaproducto plantillaproducto) {
		this.plantillaproducto = plantillaproducto;
	}

	public Boolean getEscalentamientoNotificacionpr() {
		return this.escalentamientoNotificacionpr;
	}

	public void setEscalentamientoNotificacionpr(Boolean escalentamientoNotificacionpr) {
		this.escalentamientoNotificacionpr = escalentamientoNotificacionpr;
	}
}