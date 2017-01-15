package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Consumopuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumopuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumopuestotrabajo;
	private Productogenerado productogenerado;
	private Componente componente;
	private Double cantidadConsumopuestotrabajo;
	private Double cantidadHumedaConsumopuestotrabajo;
	private Double cantidadCalentamientoConsumopuestotrabajo;
	private Set factorconsumopuestotrabajos = new HashSet(0);
	private Set factorvariacionpuestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Consumopuestotrabajo() {
	}

	/** full constructor */
	public Consumopuestotrabajo(Productogenerado productogenerado, Componente componente, Double cantidadConsumopuestotrabajo) {
		this.productogenerado = productogenerado;
		this.componente = componente;
		this.cantidadConsumopuestotrabajo = cantidadConsumopuestotrabajo;
	}

	// Property accessors

	public Long getPkCodigoConsumopuestotrabajo() {
		return this.pkCodigoConsumopuestotrabajo;
	}

	public void setPkCodigoConsumopuestotrabajo(Long pkCodigoConsumopuestotrabajo) {
		this.pkCodigoConsumopuestotrabajo = pkCodigoConsumopuestotrabajo;
	}

	public Productogenerado getProductogenerado() {
		return this.productogenerado;
	}

	public void setProductogenerado(Productogenerado productogenerado) {
		this.productogenerado = productogenerado;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Double getCantidadConsumopuestotrabajo() {
		return this.cantidadConsumopuestotrabajo;
	}

	public void setCantidadConsumopuestotrabajo(Double cantidadConsumopuestotrabajo) {
		this.cantidadConsumopuestotrabajo = cantidadConsumopuestotrabajo;
	}

	public Double getCantidadHumedaConsumopuestotrabajo() {
		return cantidadHumedaConsumopuestotrabajo;
	}

	public void setCantidadHumedaConsumopuestotrabajo(Double cantidadHumedaConsumopuestotrabajo) {
		this.cantidadHumedaConsumopuestotrabajo = cantidadHumedaConsumopuestotrabajo;
	}

	public Set getFactorconsumopuestotrabajos() {
		return factorconsumopuestotrabajos;
	}

	public void setFactorconsumopuestotrabajos(Set factorconsumopuestotrabajos) {
		this.factorconsumopuestotrabajos = factorconsumopuestotrabajos;
	}

	public Set getFactorvariacionpuestotrabajos() {
		return factorvariacionpuestotrabajos;
	}

	public void setFactorvariacionpuestotrabajos(Set factorvariacionpuestotrabajos) {
		this.factorvariacionpuestotrabajos = factorvariacionpuestotrabajos;
	}

	public Double getCantidadCalentamientoConsumopuestotrabajo() {
		return cantidadCalentamientoConsumopuestotrabajo;
	}

	public void setCantidadCalentamientoConsumopuestotrabajo(Double cantidadCalentamientoConsumopuestotrabajo) {
		this.cantidadCalentamientoConsumopuestotrabajo = cantidadCalentamientoConsumopuestotrabajo;
	}

}