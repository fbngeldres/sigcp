package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Dosificacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Dosificacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoDosificacion;
	private Factordosificacion factordosificacion;
	private Unidadmedida unidadmedida;
	private Conceptomensual conceptomensual;
	private Set dosificacionregistromensuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dosificacion() {
	}

	/** minimal constructor */
	public Dosificacion(Factordosificacion factordosificacion, Unidadmedida unidadmedida, Conceptomensual conceptomensual) {
		this.factordosificacion = factordosificacion;
		this.unidadmedida = unidadmedida;
		this.conceptomensual = conceptomensual;
	}

	/** full constructor */
	public Dosificacion(Factordosificacion factordosificacion, Unidadmedida unidadmedida, Conceptomensual conceptomensual,
			Set dosificacionregistromensuals) {
		this.factordosificacion = factordosificacion;
		this.unidadmedida = unidadmedida;
		this.conceptomensual = conceptomensual;
		this.dosificacionregistromensuals = dosificacionregistromensuals;
	}

	// Property accessors

	public Long getPkCodigoDosificacion() {
		return this.pkCodigoDosificacion;
	}

	public void setPkCodigoDosificacion(Long pkCodigoDosificacion) {
		this.pkCodigoDosificacion = pkCodigoDosificacion;
	}

	public Factordosificacion getFactordosificacion() {
		return this.factordosificacion;
	}

	public void setFactordosificacion(Factordosificacion factordosificacion) {
		this.factordosificacion = factordosificacion;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Conceptomensual getConceptomensual() {
		return this.conceptomensual;
	}

	public void setConceptomensual(Conceptomensual conceptomensual) {
		this.conceptomensual = conceptomensual;
	}

	public Set getDosificacionregistromensuals() {
		return this.dosificacionregistromensuals;
	}

	public void setDosificacionregistromensuals(Set dosificacionregistromensuals) {
		this.dosificacionregistromensuals = dosificacionregistromensuals;
	}

}