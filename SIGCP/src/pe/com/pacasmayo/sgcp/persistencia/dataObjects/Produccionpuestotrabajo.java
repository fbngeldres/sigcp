package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Produccionpuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Produccionpuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoProduccionpuestotraba;
	private Puestotrabajo puestotrabajo;
	private Partediario partediario;
	private Set tablaoperacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Produccionpuestotrabajo() {
	}

	/** minimal constructor */
	public Produccionpuestotrabajo(Puestotrabajo puestotrabajo, Partediario partediario) {
		this.puestotrabajo = puestotrabajo;
		this.partediario = partediario;
	}

	/** full constructor */
	public Produccionpuestotrabajo(Puestotrabajo puestotrabajo, Partediario partediario, Set tablaoperacions) {
		this.puestotrabajo = puestotrabajo;
		this.partediario = partediario;
		this.tablaoperacions = tablaoperacions;
	}

	// Property accessors

	public Long getPkCodigoProduccionpuestotraba() {
		return this.pkCodigoProduccionpuestotraba;
	}

	public void setPkCodigoProduccionpuestotraba(Long pkCodigoProduccionpuestotraba) {
		this.pkCodigoProduccionpuestotraba = pkCodigoProduccionpuestotraba;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Partediario getPartediario() {
		return this.partediario;
	}

	public void setPartediario(Partediario partediario) {
		this.partediario = partediario;
	}

	public Set getTablaoperacions() {
		return this.tablaoperacions;
	}

	public void setTablaoperacions(Set tablaoperacions) {
		this.tablaoperacions = tablaoperacions;
	}

}