package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Concepto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Concepto implements java.io.Serializable {

	// Fields

	private Long pkCodigoConcepto;
	private Unidadmedida unidadmedida;
	private String nombreConcepto;
	private String descripcionConcepto;
	private Set conceptomensuals = new HashSet(0);
	private Set conceptodiarios = new HashSet(0);
	private Set conceptovalors = new HashSet(0);
	private Set balanceproductos = new HashSet(0);
	private Set configuraciondistribuibles = new HashSet(0);

	// Constructors

	public Set getConfiguraciondistribuibles() {
		return configuraciondistribuibles;
	}

	public void setConfiguraciondistribuibles(Set configuraciondistribuibles) {
		this.configuraciondistribuibles = configuraciondistribuibles;
	}

	/** default constructor */
	public Concepto() {
	}

	/** minimal constructor */
	public Concepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}

	/** full constructor */
	public Concepto(Unidadmedida unidadmedida, String nombreConcepto, String descripcionConcepto, Set conceptomensuals,
			Set conceptodiarios, Set conceptovalors, Set balanceproductos) {
		this.unidadmedida = unidadmedida;
		this.nombreConcepto = nombreConcepto;
		this.descripcionConcepto = descripcionConcepto;
		this.conceptomensuals = conceptomensuals;
		this.conceptodiarios = conceptodiarios;
		this.conceptovalors = conceptovalors;
		this.balanceproductos = balanceproductos;
	}

	// Property accessors

	public Long getPkCodigoConcepto() {
		return this.pkCodigoConcepto;
	}

	public void setPkCodigoConcepto(Long pkCodigoConcepto) {
		this.pkCodigoConcepto = pkCodigoConcepto;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getNombreConcepto() {
		return this.nombreConcepto;
	}

	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}

	public String getDescripcionConcepto() {
		return this.descripcionConcepto;
	}

	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}

	public Set getConceptomensuals() {
		return this.conceptomensuals;
	}

	public void setConceptomensuals(Set conceptomensuals) {
		this.conceptomensuals = conceptomensuals;
	}

	public Set getConceptodiarios() {
		return this.conceptodiarios;
	}

	public void setConceptodiarios(Set conceptodiarios) {
		this.conceptodiarios = conceptodiarios;
	}

	public Set getConceptovalors() {
		return this.conceptovalors;
	}

	public void setConceptovalors(Set conceptovalors) {
		this.conceptovalors = conceptovalors;
	}

	public Set getBalanceproductos() {
		return this.balanceproductos;
	}

	public void setBalanceproductos(Set balanceproductos) {
		this.balanceproductos = balanceproductos;
	}

}