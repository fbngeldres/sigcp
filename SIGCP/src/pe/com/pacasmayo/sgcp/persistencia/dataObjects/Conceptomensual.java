package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Conceptomensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Conceptomensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoConceptomensual;
	private Concepto concepto;
	private Produccion produccion;
	private Set dosificacions = new HashSet(0);
	private Set conceptoregistromensuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Conceptomensual() {
	}

	/** minimal constructor */
	public Conceptomensual(Concepto concepto, Produccion produccion) {
		this.concepto = concepto;
		this.produccion = produccion;
	}

	/** full constructor */
	public Conceptomensual(Concepto concepto, Produccion produccion, Set dosificacions, Set conceptoregistromensuals) {
		this.concepto = concepto;
		this.produccion = produccion;
		this.dosificacions = dosificacions;
		this.conceptoregistromensuals = conceptoregistromensuals;
	}

	// Property accessors

	public Long getPkCodigoConceptomensual() {
		return this.pkCodigoConceptomensual;
	}

	public void setPkCodigoConceptomensual(Long pkCodigoConceptomensual) {
		this.pkCodigoConceptomensual = pkCodigoConceptomensual;
	}

	public Concepto getConcepto() {
		return this.concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Set getDosificacions() {
		return this.dosificacions;
	}

	public void setDosificacions(Set dosificacions) {
		this.dosificacions = dosificacions;
	}

	public Set getConceptoregistromensuals() {
		return this.conceptoregistromensuals;
	}

	public void setConceptoregistromensuals(Set conceptoregistromensuals) {
		this.conceptoregistromensuals = conceptoregistromensuals;
	}

}