package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Plancomercializacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plancomercializacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlancomercializacion;
	private Plananual plananual;
	private Produccion produccion;
	private Set estimacionregistromensuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Plancomercializacion() {
	}

	/** minimal constructor */
	public Plancomercializacion(Plananual plananual, Produccion produccion) {
		this.plananual = plananual;
		this.produccion = produccion;
	}

	/** full constructor */
	public Plancomercializacion(Plananual plananual, Produccion produccion, Set estimacionregistromensuals) {
		this.plananual = plananual;
		this.produccion = produccion;
		this.estimacionregistromensuals = estimacionregistromensuals;
	}

	// Property accessors

	public Long getPkCodigoPlancomercializacion() {
		return this.pkCodigoPlancomercializacion;
	}

	public void setPkCodigoPlancomercializacion(Long pkCodigoPlancomercializacion) {
		this.pkCodigoPlancomercializacion = pkCodigoPlancomercializacion;
	}

	public Plananual getPlananual() {
		return this.plananual;
	}

	public void setPlananual(Plananual plananual) {
		this.plananual = plananual;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Set getEstimacionregistromensuals() {
		return this.estimacionregistromensuals;
	}

	public void setEstimacionregistromensuals(Set estimacionregistromensuals) {
		this.estimacionregistromensuals = estimacionregistromensuals;
	}

}