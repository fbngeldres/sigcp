package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipomedioalmacenamiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipomedioalmacenamiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipomedioalmacenamien;
	private Unidadmedida unidadmedida;
	private String nombreTipomedioalmacenamiento;
	private String descripcionTipomedioalmacenami;
	private Set medioalmacenamientos = new HashSet(0);
	private Set registromedicions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipomedioalmacenamiento() {
	}

	/** minimal constructor */
	public Tipomedioalmacenamiento(String nombreTipomedioalmacenamiento) {
		this.nombreTipomedioalmacenamiento = nombreTipomedioalmacenamiento;
	}

	/** full constructor */
	public Tipomedioalmacenamiento(Unidadmedida unidadmedida, String nombreTipomedioalmacenamiento,
			String descripcionTipomedioalmacenami, Set medioalmacenamientos, Set registromedicions) {
		this.unidadmedida = unidadmedida;
		this.nombreTipomedioalmacenamiento = nombreTipomedioalmacenamiento;
		this.descripcionTipomedioalmacenami = descripcionTipomedioalmacenami;
		this.medioalmacenamientos = medioalmacenamientos;
		this.registromedicions = registromedicions;
	}

	// Property accessors

	public Long getPkCodigoTipomedioalmacenamien() {
		return this.pkCodigoTipomedioalmacenamien;
	}

	public void setPkCodigoTipomedioalmacenamien(Long pkCodigoTipomedioalmacenamien) {
		this.pkCodigoTipomedioalmacenamien = pkCodigoTipomedioalmacenamien;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getNombreTipomedioalmacenamiento() {
		return this.nombreTipomedioalmacenamiento;
	}

	public void setNombreTipomedioalmacenamiento(String nombreTipomedioalmacenamiento) {
		this.nombreTipomedioalmacenamiento = nombreTipomedioalmacenamiento;
	}

	public String getDescripcionTipomedioalmacenami() {
		return this.descripcionTipomedioalmacenami;
	}

	public void setDescripcionTipomedioalmacenami(String descripcionTipomedioalmacenami) {
		this.descripcionTipomedioalmacenami = descripcionTipomedioalmacenami;
	}

	public Set getMedioalmacenamientos() {
		return this.medioalmacenamientos;
	}

	public void setMedioalmacenamientos(Set medioalmacenamientos) {
		this.medioalmacenamientos = medioalmacenamientos;
	}

	public Set getRegistromedicions() {
		return this.registromedicions;
	}

	public void setRegistromedicions(Set registromedicions) {
		this.registromedicions = registromedicions;
	}

}