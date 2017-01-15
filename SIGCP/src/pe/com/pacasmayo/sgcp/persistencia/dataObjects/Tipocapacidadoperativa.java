package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipocapacidadoperativa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipocapacidadoperativa implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipocapacidadoperativ;
	private Unidadmedida unidadmedida;
	private String nombreTipocapacidad;
	private String descripcionTipocapacidad;
	private Set capacidadoperativas = new HashSet(0);
	private Set consumocapacidadmanuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipocapacidadoperativa() {
	}

	/** minimal constructor */
	public Tipocapacidadoperativa(String nombreTipocapacidad) {
		this.nombreTipocapacidad = nombreTipocapacidad;
	}

	/** full constructor */
	public Tipocapacidadoperativa(Unidadmedida unidadmedida, String nombreTipocapacidad, String descripcionTipocapacidad,
			Set capacidadoperativas, Set consumocapacidadmanuals) {
		this.unidadmedida = unidadmedida;
		this.nombreTipocapacidad = nombreTipocapacidad;
		this.descripcionTipocapacidad = descripcionTipocapacidad;
		this.capacidadoperativas = capacidadoperativas;
		this.consumocapacidadmanuals = consumocapacidadmanuals;
	}

	// Property accessors

	public Long getPkCodigoTipocapacidadoperativ() {
		return this.pkCodigoTipocapacidadoperativ;
	}

	public void setPkCodigoTipocapacidadoperativ(Long pkCodigoTipocapacidadoperativ) {
		this.pkCodigoTipocapacidadoperativ = pkCodigoTipocapacidadoperativ;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getNombreTipocapacidad() {
		return this.nombreTipocapacidad;
	}

	public void setNombreTipocapacidad(String nombreTipocapacidad) {
		this.nombreTipocapacidad = nombreTipocapacidad;
	}

	public String getDescripcionTipocapacidad() {
		return this.descripcionTipocapacidad;
	}

	public void setDescripcionTipocapacidad(String descripcionTipocapacidad) {
		this.descripcionTipocapacidad = descripcionTipocapacidad;
	}

	public Set getCapacidadoperativas() {
		return this.capacidadoperativas;
	}

	public void setCapacidadoperativas(Set capacidadoperativas) {
		this.capacidadoperativas = capacidadoperativas;
	}

	public Set getConsumocapacidadmanuals() {
		return this.consumocapacidadmanuals;
	}

	public void setConsumocapacidadmanuals(Set consumocapacidadmanuals) {
		this.consumocapacidadmanuals = consumocapacidadmanuals;
	}

}