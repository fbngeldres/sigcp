package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Productovariablevariacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Productovariablevariacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoProductovariablevaria;
	private Variablevariacion variablevariacion;
	private Producto producto;
	private Double valorProductovariablevariacion;
	private Date fechaVariablevariacion;
	private Proceso proceso;
	private Puestotrabajo puestotrabajo;
	private Set factorvariacionpuestotrabajos = new HashSet(0);

	// PRUEBA
	// Constructors

	/** default constructor */
	public Productovariablevariacion() {
	}

	/** minimal constructor */
	public Productovariablevariacion(Variablevariacion variablevariacion, Producto producto,
			Double valorProductovariablevariacion, Date fechaVariablevariacion) {
		this.variablevariacion = variablevariacion;
		this.producto = producto;
		this.valorProductovariablevariacion = valorProductovariablevariacion;
		this.fechaVariablevariacion = fechaVariablevariacion;
	}

	/** full constructor */
	public Productovariablevariacion(Variablevariacion variablevariacion, Producto producto,
			Double valorProductovariablevariacion, Date fechaVariablevariacion, Set factorkardexes) {
		this.variablevariacion = variablevariacion;
		this.producto = producto;
		this.valorProductovariablevariacion = valorProductovariablevariacion;
		this.fechaVariablevariacion = fechaVariablevariacion;
		this.factorvariacionpuestotrabajos = factorkardexes;
	}

	// Property accessors

	public Long getPkCodigoProductovariablevaria() {
		return this.pkCodigoProductovariablevaria;
	}

	public void setPkCodigoProductovariablevaria(Long pkCodigoProductovariablevaria) {
		this.pkCodigoProductovariablevaria = pkCodigoProductovariablevaria;
	}

	public Variablevariacion getVariablevariacion() {
		return this.variablevariacion;
	}

	public void setVariablevariacion(Variablevariacion variablevariacion) {
		this.variablevariacion = variablevariacion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getValorProductovariablevariacion() {
		return this.valorProductovariablevariacion;
	}

	public void setValorProductovariablevariacion(Double valorProductovariablevariacion) {
		this.valorProductovariablevariacion = valorProductovariablevariacion;
	}

	public Date getFechaVariablevariacion() {
		return this.fechaVariablevariacion;
	}

	public void setFechaVariablevariacion(Date fechaVariablevariacion) {
		this.fechaVariablevariacion = fechaVariablevariacion;
	}

	public Set getFactorvariacionpuestotrabajos() {
		return factorvariacionpuestotrabajos;
	}

	public void setFactorvariacionpuestotrabajos(Set factorvariacionpuestotrabajos) {
		this.factorvariacionpuestotrabajos = factorvariacionpuestotrabajos;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Puestotrabajo getPuestotrabajo() {
		return puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

}