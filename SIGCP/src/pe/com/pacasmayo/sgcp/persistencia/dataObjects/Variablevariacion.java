package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Variablevariacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Variablevariacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoVariablevariacion;
	private String nombreVariablevariacion;
	private Set productovariablevariacions = new HashSet(0);
	private Boolean produccion;

	// Constructors

	/** default constructor */
	public Variablevariacion() {
	}

	/** minimal constructor */
	public Variablevariacion(String nombreVariablevariacion) {
		this.nombreVariablevariacion = nombreVariablevariacion;
	}

	/** full constructor */
	public Variablevariacion(String nombreVariablevariacion, Set productovariablevariacions) {
		this.nombreVariablevariacion = nombreVariablevariacion;
	}

	// Property accessors

	public Long getPkCodigoVariablevariacion() {
		return this.pkCodigoVariablevariacion;
	}

	public void setPkCodigoVariablevariacion(Long pkCodigoVariablevariacion) {
		this.pkCodigoVariablevariacion = pkCodigoVariablevariacion;
	}

	public String getNombreVariablevariacion() {
		return this.nombreVariablevariacion;
	}

	public void setNombreVariablevariacion(String nombreVariablevariacion) {
		this.nombreVariablevariacion = nombreVariablevariacion;
	}

	public Set getProductovariablevariacions() {
		return this.productovariablevariacions;
	}

	public void setProductovariablevariacions(Set productovariablevariacions) {
		this.productovariablevariacions = productovariablevariacions;
	}

	public Boolean getProduccion() {
		return produccion;
	}

	public void setProduccion(Boolean produccion) {
		this.produccion = produccion;
	}

}