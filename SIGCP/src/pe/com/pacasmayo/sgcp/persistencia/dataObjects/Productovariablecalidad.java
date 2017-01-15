package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Productovariablecalidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Productovariablecalidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoProductovariablecalid;
	private Hojaruta hojaruta;
	private Variablecalidad variablecalidad;
	private Long codigoMatrixScc;
	private Long codigoProcesoScc;
	private Set valorpromvariablecalidads = new HashSet(0);

	// Constructors

	/** default constructor */
	public Productovariablecalidad() {
	}

	/** minimal constructor */
	public Productovariablecalidad(Hojaruta hojarutacomponente, Variablecalidad variablecalidad, Long codigoMatrixScc) {
		this.hojaruta = hojarutacomponente;
		this.variablecalidad = variablecalidad;
		this.codigoMatrixScc = codigoMatrixScc;
	}

	/** full constructor */
	public Productovariablecalidad(Hojaruta hojarutacomponente, Variablecalidad variablecalidad, Long codigoMatrixScc,
			Set valorpromvariablecalidads) {
		this.hojaruta = hojarutacomponente;
		this.variablecalidad = variablecalidad;
		this.codigoMatrixScc = codigoMatrixScc;
		this.valorpromvariablecalidads = valorpromvariablecalidads;
	}

	// Property accessors

	public Long getPkCodigoProductovariablecalid() {
		return this.pkCodigoProductovariablecalid;
	}

	public void setPkCodigoProductovariablecalid(Long pkCodigoProductovariablecalid) {
		this.pkCodigoProductovariablecalid = pkCodigoProductovariablecalid;
	}

	public Variablecalidad getVariablecalidad() {
		return this.variablecalidad;
	}

	public void setVariablecalidad(Variablecalidad variablecalidad) {
		this.variablecalidad = variablecalidad;
	}

	public Long getCodigoMatrixScc() {
		return this.codigoMatrixScc;
	}

	public void setCodigoMatrixScc(Long codigoMatrixScc) {
		this.codigoMatrixScc = codigoMatrixScc;
	}

	public Set getValorpromvariablecalidads() {
		return this.valorpromvariablecalidads;
	}

	public void setValorpromvariablecalidads(Set valorpromvariablecalidads) {
		this.valorpromvariablecalidads = valorpromvariablecalidads;
	}

	public Hojaruta getHojaruta() {
		return hojaruta;
	}

	public void setHojaruta(Hojaruta hojaruta) {
		this.hojaruta = hojaruta;
	}

	/**
	 * @return the codigoProcesoScc
	 */
	public Long getCodigoProcesoScc() {
		return codigoProcesoScc;
	}

	/**
	 * @param codigoProcesoScc the codigoProcesoScc to set
	 */
	public void setCodigoProcesoScc(Long codigoProcesoScc) {
		this.codigoProcesoScc = codigoProcesoScc;
	}

}