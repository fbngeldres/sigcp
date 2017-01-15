package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Productogenerado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Productogenerado implements java.io.Serializable {

	// Fields

	private Long pkCodigoProductogenerado;
	private Tablaoperacion tablaoperacion;
	private Ordenproduccion ordenproduccion;
	private Double produccionTmProductogenerado;
	private Double horasProductogenerado;
	private Double kcalProductogenerado;
	private Double descuentoAdicionalLiquido;
	private Double descuentoAdicionalSolido;
	private Set consumopuestotrabajos = new HashSet(0);
	private Set factorvariacionproduccionpuestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Productogenerado() {
	}

	/** minimal constructor */
	public Productogenerado(Tablaoperacion tablaoperacion, Ordenproduccion ordenproduccion, Double produccionTmProductogenerado,
			Double horasProductogenerado) {
		this.tablaoperacion = tablaoperacion;
		this.ordenproduccion = ordenproduccion;
		this.produccionTmProductogenerado = produccionTmProductogenerado;
		this.horasProductogenerado = horasProductogenerado;
	}

	/** full constructor */
	public Productogenerado(Tablaoperacion tablaoperacion, Ordenproduccion ordenproduccion, Double produccionTmProductogenerado,
			Double horasProductogenerado, Set consumopuestotrabajos) {
		this.tablaoperacion = tablaoperacion;
		this.ordenproduccion = ordenproduccion;
		this.produccionTmProductogenerado = produccionTmProductogenerado;
		this.horasProductogenerado = horasProductogenerado;
		this.consumopuestotrabajos = consumopuestotrabajos;
	}

	// Property accessors

	public Productogenerado(Long pkCodigoProductogenerado, Tablaoperacion tablaoperacion, Ordenproduccion ordenproduccion,
			Double produccionTmProductogenerado, Double horasProductogenerado, Double kcalProductogenerado,
			Double descuentoAdicionalLiquido, Double descuentoAdicionalSolido, Set factorvariacionproduccionpuestotrabajos) {
		super();
		this.pkCodigoProductogenerado = pkCodigoProductogenerado;
		this.tablaoperacion = tablaoperacion;
		this.ordenproduccion = ordenproduccion;
		this.produccionTmProductogenerado = produccionTmProductogenerado;
		this.horasProductogenerado = horasProductogenerado;
		this.kcalProductogenerado = kcalProductogenerado;
		this.descuentoAdicionalLiquido = descuentoAdicionalLiquido;
		this.descuentoAdicionalSolido = descuentoAdicionalSolido;
		this.factorvariacionproduccionpuestotrabajos = factorvariacionproduccionpuestotrabajos;
	}

	public Long getPkCodigoProductogenerado() {
		return this.pkCodigoProductogenerado;
	}

	public void setPkCodigoProductogenerado(Long pkCodigoProductogenerado) {
		this.pkCodigoProductogenerado = pkCodigoProductogenerado;
	}

	public Tablaoperacion getTablaoperacion() {
		return this.tablaoperacion;
	}

	public void setTablaoperacion(Tablaoperacion tablaoperacion) {
		this.tablaoperacion = tablaoperacion;
	}

	public Ordenproduccion getOrdenproduccion() {
		return this.ordenproduccion;
	}

	public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Double getProduccionTmProductogenerado() {
		return this.produccionTmProductogenerado;
	}

	public void setProduccionTmProductogenerado(Double produccionTmProductogenerado) {
		this.produccionTmProductogenerado = produccionTmProductogenerado;
	}

	public Double getHorasProductogenerado() {
		return this.horasProductogenerado;
	}

	public void setHorasProductogenerado(Double horasProductogenerado) {
		this.horasProductogenerado = horasProductogenerado;
	}

	public Set getConsumopuestotrabajos() {
		return this.consumopuestotrabajos;
	}

	public void setConsumopuestotrabajos(Set consumopuestotrabajos) {
		this.consumopuestotrabajos = consumopuestotrabajos;
	}

	/**
	 * @return the factorvariacionproduccionpuestotrabajos
	 */
	public Set getFactorvariacionproduccionpuestotrabajos() {
		return factorvariacionproduccionpuestotrabajos;
	}

	/**
	 * @param factorvariacionproduccionpuestotrabajos the
	 *            factorvariacionproduccionpuestotrabajos to set
	 */
	public void setFactorvariacionproduccionpuestotrabajos(Set factorvariacionproduccionpuestotrabajos) {
		this.factorvariacionproduccionpuestotrabajos = factorvariacionproduccionpuestotrabajos;
	}

	public Double getKcalProductogenerado() {
		return kcalProductogenerado;
	}

	public void setKcalProductogenerado(Double kcalProductogenerado) {
		this.kcalProductogenerado = kcalProductogenerado;
	}

	public Double getDescuentoAdicionalLiquido() {
		return descuentoAdicionalLiquido;
	}

	public void setDescuentoAdicionalLiquido(Double descuentoAdicionalLiquido) {
		this.descuentoAdicionalLiquido = descuentoAdicionalLiquido;
	}

	public Double getDescuentoAdicionalSolido() {
		return descuentoAdicionalSolido;
	}

	public void setDescuentoAdicionalSolido(Double descuentoAdicionalSolido) {
		this.descuentoAdicionalSolido = descuentoAdicionalSolido;
	}

}