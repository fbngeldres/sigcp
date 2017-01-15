package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipomovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipomovimiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipomovimiento;
	private Clasificaciontipomovimiento clasificaciontipomovimiento;
	private String nombreTipomovimiento;
	private String descripcionTipomovimiento;
	private String codigoSapTipomovimiento;
	private Set movimientos = new HashSet(0);
	private Set movimientoajustes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipomovimiento() {
	}

	/** minimal constructor */
	public Tipomovimiento(Clasificaciontipomovimiento clasificaciontipomovimiento, String nombreTipomovimiento,
			String codigoSapTipomovimiento) {
		this.clasificaciontipomovimiento = clasificaciontipomovimiento;
		this.nombreTipomovimiento = nombreTipomovimiento;
		this.codigoSapTipomovimiento = codigoSapTipomovimiento;
	}

	/** full constructor */
	public Tipomovimiento(Clasificaciontipomovimiento clasificaciontipomovimiento, String nombreTipomovimiento,
			String descripcionTipomovimiento, String codigoSapTipomovimiento, Set movimientos, Set movimientoajustes) {
		this.clasificaciontipomovimiento = clasificaciontipomovimiento;
		this.nombreTipomovimiento = nombreTipomovimiento;
		this.descripcionTipomovimiento = descripcionTipomovimiento;
		this.codigoSapTipomovimiento = codigoSapTipomovimiento;
		this.movimientos = movimientos;
		this.movimientoajustes = movimientoajustes;
	}

	// Property accessors

	public Long getPkCodigoTipomovimiento() {
		return this.pkCodigoTipomovimiento;
	}

	public void setPkCodigoTipomovimiento(Long pkCodigoTipomovimiento) {
		this.pkCodigoTipomovimiento = pkCodigoTipomovimiento;
	}

	public Clasificaciontipomovimiento getClasificaciontipomovimiento() {
		return this.clasificaciontipomovimiento;
	}

	public void setClasificaciontipomovimiento(Clasificaciontipomovimiento clasificaciontipomovimiento) {
		this.clasificaciontipomovimiento = clasificaciontipomovimiento;
	}

	public String getNombreTipomovimiento() {
		return this.nombreTipomovimiento;
	}

	public void setNombreTipomovimiento(String nombreTipomovimiento) {
		this.nombreTipomovimiento = nombreTipomovimiento;
	}

	public String getDescripcionTipomovimiento() {
		return this.descripcionTipomovimiento;
	}

	public void setDescripcionTipomovimiento(String descripcionTipomovimiento) {
		this.descripcionTipomovimiento = descripcionTipomovimiento;
	}

	public String getCodigoSapTipomovimiento() {
		return this.codigoSapTipomovimiento;
	}

	public void setCodigoSapTipomovimiento(String codigoSapTipomovimiento) {
		this.codigoSapTipomovimiento = codigoSapTipomovimiento;
	}

	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}

	public Set getMovimientoajustes() {
		return this.movimientoajustes;
	}

	public void setMovimientoajustes(Set movimientoajustes) {
		this.movimientoajustes = movimientoajustes;
	}

}