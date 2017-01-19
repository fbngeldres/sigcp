package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Medioalmacenamiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Medioalmacenamiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoMedioalmacenamiento;
	private Ubicacion ubicacion;
	private Tipomedioalmacenamiento tipomedioalmacenamiento;
	private Puestotrabajo puestotrabajo;
	private Produccion produccion;
	private String nombreMedioalmacenamiento;
	private Short numeroMedioalmacenamiento;
	private Double capacidadMaximaMedioalmacenam;
	private Double capacidadMinimaMedioalmacenam;
	private Double densidadMedioalmacenamiento;
	private Long numeroAlturasMedioalmacenamie;
	private Double alturaEspecificaMedioalmacena;
	private Double factorMetrosCubicosMedioalma;
	private Double stockSeguridadMedioalmacenami;
	private Set notificacionproduccions = new HashSet(0);
	private Set cubicacions = new HashSet(0);
	private Set medicions = new HashSet(0);
	private Set movimientos = new HashSet(0);
	
	private Set kardexmedioalmacenamientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Medioalmacenamiento() {
	}

	/** minimal constructor */
	public Medioalmacenamiento(Tipomedioalmacenamiento tipomedioalmacenamiento, String nombreMedioalmacenamiento) {
		this.tipomedioalmacenamiento = tipomedioalmacenamiento;
		this.nombreMedioalmacenamiento = nombreMedioalmacenamiento;
	}

	/** full constructor */
	public Medioalmacenamiento(Ubicacion ubicacion, Tipomedioalmacenamiento tipomedioalmacenamiento, Puestotrabajo puestotrabajo,
			Produccion produccion, String nombreMedioalmacenamiento, Short numeroMedioalmacenamiento,
			Double capacidadMaximaMedioalmacenam, Double capacidadMinimaMedioalmacenam, Double densidadMedioalmacenamiento,
			Long numeroAlturasMedioalmacenamie, Double alturaEspecificaMedioalmacena, Double factorMetrosCubicosMedioalma,
			Double stockSeguridadMedioalmacenami, Set notificacionproduccions, Set cubicacions, Set medicions, Set movimientos) {
		this.ubicacion = ubicacion;
		this.tipomedioalmacenamiento = tipomedioalmacenamiento;
		this.puestotrabajo = puestotrabajo;
		this.produccion = produccion;
		this.nombreMedioalmacenamiento = nombreMedioalmacenamiento;
		this.numeroMedioalmacenamiento = numeroMedioalmacenamiento;
		this.capacidadMaximaMedioalmacenam = capacidadMaximaMedioalmacenam;
		this.capacidadMinimaMedioalmacenam = capacidadMinimaMedioalmacenam;
		this.densidadMedioalmacenamiento = densidadMedioalmacenamiento;
		this.numeroAlturasMedioalmacenamie = numeroAlturasMedioalmacenamie;
		this.alturaEspecificaMedioalmacena = alturaEspecificaMedioalmacena;
		this.factorMetrosCubicosMedioalma = factorMetrosCubicosMedioalma;
		this.stockSeguridadMedioalmacenami = stockSeguridadMedioalmacenami;
		this.notificacionproduccions = notificacionproduccions;
		this.cubicacions = cubicacions;
		this.medicions = medicions;
		this.movimientos = movimientos;
		
	}

	// Property accessors

	public Long getPkCodigoMedioalmacenamiento() {
		return this.pkCodigoMedioalmacenamiento;
	}

	public void setPkCodigoMedioalmacenamiento(Long pkCodigoMedioalmacenamiento) {
		this.pkCodigoMedioalmacenamiento = pkCodigoMedioalmacenamiento;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Tipomedioalmacenamiento getTipomedioalmacenamiento() {
		return this.tipomedioalmacenamiento;
	}

	public void setTipomedioalmacenamiento(Tipomedioalmacenamiento tipomedioalmacenamiento) {
		this.tipomedioalmacenamiento = tipomedioalmacenamiento;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public String getNombreMedioalmacenamiento() {
		return this.nombreMedioalmacenamiento;
	}

	public void setNombreMedioalmacenamiento(String nombreMedioalmacenamiento) {
		this.nombreMedioalmacenamiento = nombreMedioalmacenamiento;
	}

	public Short getNumeroMedioalmacenamiento() {
		return this.numeroMedioalmacenamiento;
	}

	public void setNumeroMedioalmacenamiento(Short numeroMedioalmacenamiento) {
		this.numeroMedioalmacenamiento = numeroMedioalmacenamiento;
	}

	public Double getCapacidadMaximaMedioalmacenam() {
		return this.capacidadMaximaMedioalmacenam;
	}

	public void setCapacidadMaximaMedioalmacenam(Double capacidadMaximaMedioalmacenam) {
		this.capacidadMaximaMedioalmacenam = capacidadMaximaMedioalmacenam;
	}

	public Double getCapacidadMinimaMedioalmacenam() {
		return this.capacidadMinimaMedioalmacenam;
	}

	public void setCapacidadMinimaMedioalmacenam(Double capacidadMinimaMedioalmacenam) {
		this.capacidadMinimaMedioalmacenam = capacidadMinimaMedioalmacenam;
	}

	public Double getDensidadMedioalmacenamiento() {
		return this.densidadMedioalmacenamiento;
	}

	public void setDensidadMedioalmacenamiento(Double densidadMedioalmacenamiento) {
		this.densidadMedioalmacenamiento = densidadMedioalmacenamiento;
	}

	public Long getNumeroAlturasMedioalmacenamie() {
		return this.numeroAlturasMedioalmacenamie;
	}

	public void setNumeroAlturasMedioalmacenamie(Long numeroAlturasMedioalmacenamie) {
		this.numeroAlturasMedioalmacenamie = numeroAlturasMedioalmacenamie;
	}

	public Double getAlturaEspecificaMedioalmacena() {
		return this.alturaEspecificaMedioalmacena;
	}

	public void setAlturaEspecificaMedioalmacena(Double alturaEspecificaMedioalmacena) {
		this.alturaEspecificaMedioalmacena = alturaEspecificaMedioalmacena;
	}

	public Double getFactorMetrosCubicosMedioalma() {
		return this.factorMetrosCubicosMedioalma;
	}

	public void setFactorMetrosCubicosMedioalma(Double factorMetrosCubicosMedioalma) {
		this.factorMetrosCubicosMedioalma = factorMetrosCubicosMedioalma;
	}

	public Double getStockSeguridadMedioalmacenami() {
		return this.stockSeguridadMedioalmacenami;
	}

	public void setStockSeguridadMedioalmacenami(Double stockSeguridadMedioalmacenami) {
		this.stockSeguridadMedioalmacenami = stockSeguridadMedioalmacenami;
	}

	public Set getNotificacionproduccions() {
		return this.notificacionproduccions;
	}

	public void setNotificacionproduccions(Set notificacionproduccions) {
		this.notificacionproduccions = notificacionproduccions;
	}

	public Set getCubicacions() {
		return this.cubicacions;
	}

	public void setCubicacions(Set cubicacions) {
		this.cubicacions = cubicacions;
	}

	public Set getMedicions() {
		return this.medicions;
	}

	public void setMedicions(Set medicions) {
		this.medicions = medicions;
	}

	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}


	public Set getKardexmedioalmacenamientos() {
		return kardexmedioalmacenamientos;
	}

	public void setKardexmedioalmacenamientos(Set kardexmedioalmacenamientos) {
		this.kardexmedioalmacenamientos = kardexmedioalmacenamientos;
	}

}