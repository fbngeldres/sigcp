package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadomovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadomovimiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadomovimiento;
	private String nombreEstadomovimiento;
	private Set movimientoajustes = new HashSet(0);
	private Set movimientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadomovimiento() {
	}

	/** minimal constructor */
	public Estadomovimiento(String nombreEstadomovimiento) {
		this.nombreEstadomovimiento = nombreEstadomovimiento;
	}

	/** full constructor */
	public Estadomovimiento(String nombreEstadomovimiento, Set movimientoajustes, Set movimientos) {
		this.nombreEstadomovimiento = nombreEstadomovimiento;
		this.movimientoajustes = movimientoajustes;
		this.movimientos = movimientos;
	}

	// Property accessors

	public Long getPkCodigoEstadomovimiento() {
		return this.pkCodigoEstadomovimiento;
	}

	public void setPkCodigoEstadomovimiento(Long pkCodigoEstadomovimiento) {
		this.pkCodigoEstadomovimiento = pkCodigoEstadomovimiento;
	}

	public String getNombreEstadomovimiento() {
		return this.nombreEstadomovimiento;
	}

	public void setNombreEstadomovimiento(String nombreEstadomovimiento) {
		this.nombreEstadomovimiento = nombreEstadomovimiento;
	}

	public Set getMovimientoajustes() {
		return this.movimientoajustes;
	}

	public void setMovimientoajustes(Set movimientoajustes) {
		this.movimientoajustes = movimientoajustes;
	}

	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}

}