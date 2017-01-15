package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipovariableoperacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipovariableoperacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipovariableoperacion;
	private String nombreTipovariableoperacion;
	private Set variableoperacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipovariableoperacion() {
	}

	/** minimal constructor */
	public Tipovariableoperacion(String nombreTipovariableoperacion) {
		this.nombreTipovariableoperacion = nombreTipovariableoperacion;
	}

	/** full constructor */
	public Tipovariableoperacion(String nombreTipovariableoperacion, Set variableoperacions) {
		this.nombreTipovariableoperacion = nombreTipovariableoperacion;
		this.variableoperacions = variableoperacions;
	}

	// Property accessors

	public Long getPkCodigoTipovariableoperacion() {
		return this.pkCodigoTipovariableoperacion;
	}

	public void setPkCodigoTipovariableoperacion(Long pkCodigoTipovariableoperacion) {
		this.pkCodigoTipovariableoperacion = pkCodigoTipovariableoperacion;
	}

	public String getNombreTipovariableoperacion() {
		return this.nombreTipovariableoperacion;
	}

	public void setNombreTipovariableoperacion(String nombreTipovariableoperacion) {
		this.nombreTipovariableoperacion = nombreTipovariableoperacion;
	}

	public Set getVariableoperacions() {
		return this.variableoperacions;
	}

	public void setVariableoperacions(Set variableoperacions) {
		this.variableoperacions = variableoperacions;
	}

}