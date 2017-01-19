package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Produccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Produccion implements java.io.Serializable {

	// Fields

	private Long pkProduccion;
	private Producto producto;
	private Proceso proceso;
	private Set hojarutas = new HashSet(0);
	private Set tasarealproduccions = new HashSet(0);
	private Set medioalmacenamientos = new HashSet(0);
	private Set plancomercializacions = new HashSet(0);
	private Set ordenproduccions = new HashSet(0);
	private Set plantillaajusteproductos = new HashSet(0);
	private Set medicions = new HashSet(0);
	private Set conceptomensuals = new HashSet(0);
	private Set cubicacionproductos = new HashSet(0);


	// Constructors

	/** default constructor */
	public Produccion() {
	}

	/** minimal constructor */
	public Produccion(Producto producto, Proceso proceso) {
		this.producto = producto;
		this.proceso = proceso;
	}

	/** full constructor */
	public Produccion(Producto producto, Proceso proceso, Set hojarutas, Set tasarealproduccions, Set medioalmacenamientos,
			Set plancomercializacions, Set ordenproduccions, Set plantillaajusteproductos, Set medicions, Set conceptomensuals,
			Set cubicacionproductos) {
		this.producto = producto;
		this.proceso = proceso;
		this.hojarutas = hojarutas;
		this.tasarealproduccions = tasarealproduccions;
		this.medioalmacenamientos = medioalmacenamientos;
		this.plancomercializacions = plancomercializacions;
		this.ordenproduccions = ordenproduccions;
		this.plantillaajusteproductos = plantillaajusteproductos;
		this.medicions = medicions;
		this.conceptomensuals = conceptomensuals;
		this.cubicacionproductos = cubicacionproductos;
		
	}

	// Property accessors

	public Long getPkProduccion() {
		return this.pkProduccion;
	}

	public void setPkProduccion(Long pkProduccion) {
		this.pkProduccion = pkProduccion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Set getHojarutas() {
		return this.hojarutas;
	}

	public void setHojarutas(Set hojarutas) {
		this.hojarutas = hojarutas;
	}

	public Set getTasarealproduccions() {
		return this.tasarealproduccions;
	}

	public void setTasarealproduccions(Set tasarealproduccions) {
		this.tasarealproduccions = tasarealproduccions;
	}

	public Set getMedioalmacenamientos() {
		return this.medioalmacenamientos;
	}

	public void setMedioalmacenamientos(Set medioalmacenamientos) {
		this.medioalmacenamientos = medioalmacenamientos;
	}

	public Set getPlancomercializacions() {
		return this.plancomercializacions;
	}

	public void setPlancomercializacions(Set plancomercializacions) {
		this.plancomercializacions = plancomercializacions;
	}

	public Set getOrdenproduccions() {
		return this.ordenproduccions;
	}

	public void setOrdenproduccions(Set ordenproduccions) {
		this.ordenproduccions = ordenproduccions;
	}

	public Set getPlantillaajusteproductos() {
		return this.plantillaajusteproductos;
	}

	public void setPlantillaajusteproductos(Set plantillaajusteproductos) {
		this.plantillaajusteproductos = plantillaajusteproductos;
	}

	public Set getMedicions() {
		return this.medicions;
	}

	public void setMedicions(Set medicions) {
		this.medicions = medicions;
	}

	public Set getConceptomensuals() {
		return this.conceptomensuals;
	}

	public void setConceptomensuals(Set conceptomensuals) {
		this.conceptomensuals = conceptomensuals;
	}

	public Set getCubicacionproductos() {
		return this.cubicacionproductos;
	}

	public void setCubicacionproductos(Set cubicacionproductos) {
		this.cubicacionproductos = cubicacionproductos;
	}



}