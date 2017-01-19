package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Hojaruta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Hojaruta implements java.io.Serializable {

	// Fields

	private Long pkCodigoHojaruta;
	private Estadohojaruta estadohojaruta;
	private Produccion produccion;
	private String nombreHojaruta;
	private Set ordenproduccions = new HashSet(0);
	private Set operacions = new HashSet(0);
	
	private Set hojarutacomponentes = new TreeSet();
	private Set productovariablecalidads = new HashSet(0);

	private Set factordosificacions = new HashSet(0);
	private Set plannecesidads = new HashSet(0);

	// Constructors

	/** default constructor */
	public Hojaruta() {
	}

	/** minimal constructor */
	public Hojaruta(Estadohojaruta estadohojaruta, Produccion produccion, String nombreHojaruta) {
		this.estadohojaruta = estadohojaruta;
		this.produccion = produccion;
		this.nombreHojaruta = nombreHojaruta;
	}

	/** full constructor */
	public Hojaruta(Estadohojaruta estadohojaruta, Produccion produccion, String nombreHojaruta, Set ordenproduccions,
			Set operacions,  Set hojarutacomponentes, Set factordosificacions, Set plannecesidads) {
		this.estadohojaruta = estadohojaruta;
		this.produccion = produccion;
		this.nombreHojaruta = nombreHojaruta;
		this.ordenproduccions = ordenproduccions;
		this.operacions = operacions;
		
		this.hojarutacomponentes = hojarutacomponentes;
		this.factordosificacions = factordosificacions;
		this.plannecesidads = plannecesidads;
	}

	// Property accessors

	public Long getPkCodigoHojaruta() {
		return this.pkCodigoHojaruta;
	}

	public void setPkCodigoHojaruta(Long pkCodigoHojaruta) {
		this.pkCodigoHojaruta = pkCodigoHojaruta;
	}

	public Estadohojaruta getEstadohojaruta() {
		return this.estadohojaruta;
	}

	public void setEstadohojaruta(Estadohojaruta estadohojaruta) {
		this.estadohojaruta = estadohojaruta;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public String getNombreHojaruta() {
		return this.nombreHojaruta;
	}

	public void setNombreHojaruta(String nombreHojaruta) {
		this.nombreHojaruta = nombreHojaruta;
	}

	public Set getOrdenproduccions() {
		return this.ordenproduccions;
	}

	public void setOrdenproduccions(Set ordenproduccions) {
		this.ordenproduccions = ordenproduccions;
	}

	public Set getOperacions() {
		return this.operacions;
	}

	public void setOperacions(Set operacions) {
		this.operacions = operacions;
	}



	public Set getHojarutacomponentes() {
		return this.hojarutacomponentes;
	}

	public void setHojarutacomponentes(Set hojarutacomponentes) {
		this.hojarutacomponentes = hojarutacomponentes;
	}

	public Set getFactordosificacions() {
		return this.factordosificacions;
	}

	public void setFactordosificacions(Set factordosificacions) {
		this.factordosificacions = factordosificacions;
	}

	public Set getPlannecesidads() {
		return this.plannecesidads;
	}

	public void setPlannecesidads(Set plannecesidads) {
		this.plannecesidads = plannecesidads;
	}

	public Set getProductovariablecalidads() {
		return productovariablecalidads;
	}

	public void setProductovariablecalidads(Set productovariablecalidads) {
		this.productovariablecalidads = productovariablecalidads;
	}

}