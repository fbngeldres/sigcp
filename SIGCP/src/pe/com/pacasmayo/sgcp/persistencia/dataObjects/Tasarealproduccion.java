package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tasarealproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tasarealproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoTasarealproduccion;
	private Unidadmedida unidadmedida;
	private Puestotrabajo puestotrabajo;
	private Produccion produccion;
	private Double maximoTasarealproduccion;
	private Double minimoTasarealproduccion;
	private Double nominalTasarealproduccion;
	private Set capacidadoperativas = new HashSet(0);
	private Set tasarealprodregistromensuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tasarealproduccion() {
	}

	/** minimal constructor */
	public Tasarealproduccion(Puestotrabajo puestotrabajo, Produccion produccion) {
		this.puestotrabajo = puestotrabajo;
		this.produccion = produccion;
	}

	/** full constructor */
	public Tasarealproduccion(Unidadmedida unidadmedida, Puestotrabajo puestotrabajo, Produccion produccion,
			Double maximoTasarealproduccion, Double minimoTasarealproduccion, Set capacidadoperativas,
			Set tasarealprodregistromensuals) {
		this.unidadmedida = unidadmedida;
		this.puestotrabajo = puestotrabajo;
		this.produccion = produccion;
		this.maximoTasarealproduccion = maximoTasarealproduccion;
		this.minimoTasarealproduccion = minimoTasarealproduccion;
		this.capacidadoperativas = capacidadoperativas;
		this.tasarealprodregistromensuals = tasarealprodregistromensuals;
	}

	// Property accessors

	public Long getPkCodigoTasarealproduccion() {
		return this.pkCodigoTasarealproduccion;
	}

	public void setPkCodigoTasarealproduccion(Long pkCodigoTasarealproduccion) {
		this.pkCodigoTasarealproduccion = pkCodigoTasarealproduccion;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
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

	public Double getMaximoTasarealproduccion() {
		return this.maximoTasarealproduccion;
	}

	public void setMaximoTasarealproduccion(Double maximoTasarealproduccion) {
		this.maximoTasarealproduccion = maximoTasarealproduccion;
	}

	public Double getMinimoTasarealproduccion() {
		return this.minimoTasarealproduccion;
	}

	public void setMinimoTasarealproduccion(Double minimoTasarealproduccion) {
		this.minimoTasarealproduccion = minimoTasarealproduccion;
	}

	public Set getCapacidadoperativas() {
		return this.capacidadoperativas;
	}

	public void setCapacidadoperativas(Set capacidadoperativas) {
		this.capacidadoperativas = capacidadoperativas;
	}

	public Set getTasarealprodregistromensuals() {
		return this.tasarealprodregistromensuals;
	}

	public void setTasarealprodregistromensuals(Set tasarealprodregistromensuals) {
		this.tasarealprodregistromensuals = tasarealprodregistromensuals;
	}

	/**
	 * @return the nominalTasarealproduccion
	 */
	public Double getNominalTasarealproduccion() {
		return nominalTasarealproduccion;
	}

	/**
	 * @param nominalTasarealproduccion the nominalTasarealproduccion to set
	 */
	public void setNominalTasarealproduccion(Double nominalTasarealproduccion) {
		this.nominalTasarealproduccion = nominalTasarealproduccion;
	}

}