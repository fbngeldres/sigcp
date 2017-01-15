package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipobalance entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipobalance implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipobalance;
	private String nombreTipobalance;
	private Set balanceproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipobalance() {
	}

	/** minimal constructor */
	public Tipobalance(String nombreTipobalance) {
		this.nombreTipobalance = nombreTipobalance;
	}

	/** full constructor */
	public Tipobalance(String nombreTipobalance, Set balanceproductos) {
		this.nombreTipobalance = nombreTipobalance;
		this.balanceproductos = balanceproductos;
	}

	// Property accessors

	public Long getPkCodigoTipobalance() {
		return this.pkCodigoTipobalance;
	}

	public void setPkCodigoTipobalance(Long pkCodigoTipobalance) {
		this.pkCodigoTipobalance = pkCodigoTipobalance;
	}

	public String getNombreTipobalance() {
		return this.nombreTipobalance;
	}

	public void setNombreTipobalance(String nombreTipobalance) {
		this.nombreTipobalance = nombreTipobalance;
	}

	public Set getBalanceproductos() {
		return this.balanceproductos;
	}

	public void setBalanceproductos(Set balanceproductos) {
		this.balanceproductos = balanceproductos;
	}

}