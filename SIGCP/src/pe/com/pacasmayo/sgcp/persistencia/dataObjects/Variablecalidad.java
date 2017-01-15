package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Variablecalidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Variablecalidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoVariablecalidad;
	private Unidadmedida unidadmedida;
	private String nombreVariablecalidad;
	private Set productovariablecalidads = new HashSet(0);

	// Constructors

	/** default constructor */
	public Variablecalidad() {
	}

	/** minimal constructor */
	public Variablecalidad(Unidadmedida unidadmedida, String nombreVariablecalidad) {
		this.unidadmedida = unidadmedida;
		this.nombreVariablecalidad = nombreVariablecalidad;
	}

	/** full constructor */
	public Variablecalidad(Unidadmedida unidadmedida, String nombreVariablecalidad, Set productovariablecalidads) {
		this.unidadmedida = unidadmedida;
		this.nombreVariablecalidad = nombreVariablecalidad;
		this.productovariablecalidads = productovariablecalidads;
	}

	// Property accessors

	public Long getPkCodigoVariablecalidad() {
		return this.pkCodigoVariablecalidad;
	}

	public void setPkCodigoVariablecalidad(Long pkCodigoVariablecalidad) {
		this.pkCodigoVariablecalidad = pkCodigoVariablecalidad;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getNombreVariablecalidad() {
		return this.nombreVariablecalidad;
	}

	public void setNombreVariablecalidad(String nombreVariablecalidad) {
		this.nombreVariablecalidad = nombreVariablecalidad;
	}

	public Set getProductovariablecalidads() {
		return this.productovariablecalidads;
	}

	public void setProductovariablecalidads(Set productovariablecalidads) {
		this.productovariablecalidads = productovariablecalidads;
	}

}