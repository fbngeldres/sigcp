package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Unidadmedida entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Unidadmedida implements java.io.Serializable {

	// Fields

	private Long pkCodigoUnidadMedida;
	private String nombreUnidadmedida;
	private String descripcionUnidadmedida;
	private Set puestotrabajos = new HashSet(0);
	private Set variablecalidads = new HashSet(0);
	private Set productos = new HashSet(0);
	private Set movimientos = new HashSet(0);
	private Set tasarealproduccions = new HashSet(0);
	private Set tipocapacidadoperativas = new HashSet(0);
	private Set conceptos = new HashSet(0);
	private Set plancapacidads = new HashSet(0);
	private Set recursos = new HashSet(0);
	private Set tipomedioalmacenamientos = new HashSet(0);
	private Set dosificacions = new HashSet(0);
	private Set factordosificacions = new HashSet(0);
	private Set variableoperacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Unidadmedida() {
	}

	/** minimal constructor */
	public Unidadmedida(String nombreUnidadmedida) {
		this.nombreUnidadmedida = nombreUnidadmedida;
	}

	/** full constructor */
	public Unidadmedida(String nombreUnidadmedida, String descripcionUnidadmedida, Set puestotrabajos, Set variablecalidads,
			Set productos, Set movimientos, Set tasarealproduccions, Set tipocapacidadoperativas, Set conceptos,
			Set plancapacidads, Set recursos, Set tipomedioalmacenamientos, Set dosificacions, Set factordosificacions,
			Set variableoperacions) {
		this.nombreUnidadmedida = nombreUnidadmedida;
		this.descripcionUnidadmedida = descripcionUnidadmedida;
		this.puestotrabajos = puestotrabajos;
		this.variablecalidads = variablecalidads;
		this.productos = productos;
		this.movimientos = movimientos;
		this.tasarealproduccions = tasarealproduccions;
		this.tipocapacidadoperativas = tipocapacidadoperativas;
		this.conceptos = conceptos;
		this.plancapacidads = plancapacidads;
		this.recursos = recursos;
		this.tipomedioalmacenamientos = tipomedioalmacenamientos;
		this.dosificacions = dosificacions;
		this.factordosificacions = factordosificacions;
		this.variableoperacions = variableoperacions;
	}

	// Property accessors

	public Long getPkCodigoUnidadMedida() {
		return this.pkCodigoUnidadMedida;
	}

	public void setPkCodigoUnidadMedida(Long pkCodigoUnidadMedida) {
		this.pkCodigoUnidadMedida = pkCodigoUnidadMedida;
	}

	public String getNombreUnidadmedida() {
		return this.nombreUnidadmedida;
	}

	public void setNombreUnidadmedida(String nombreUnidadmedida) {
		this.nombreUnidadmedida = nombreUnidadmedida;
	}

	public String getDescripcionUnidadmedida() {
		return this.descripcionUnidadmedida;
	}

	public void setDescripcionUnidadmedida(String descripcionUnidadmedida) {
		this.descripcionUnidadmedida = descripcionUnidadmedida;
	}

	public Set getPuestotrabajos() {
		return this.puestotrabajos;
	}

	public void setPuestotrabajos(Set puestotrabajos) {
		this.puestotrabajos = puestotrabajos;
	}

	public Set getVariablecalidads() {
		return this.variablecalidads;
	}

	public void setVariablecalidads(Set variablecalidads) {
		this.variablecalidads = variablecalidads;
	}

	public Set getProductos() {
		return this.productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}

	public Set getTasarealproduccions() {
		return this.tasarealproduccions;
	}

	public void setTasarealproduccions(Set tasarealproduccions) {
		this.tasarealproduccions = tasarealproduccions;
	}

	public Set getTipocapacidadoperativas() {
		return this.tipocapacidadoperativas;
	}

	public void setTipocapacidadoperativas(Set tipocapacidadoperativas) {
		this.tipocapacidadoperativas = tipocapacidadoperativas;
	}

	public Set getConceptos() {
		return this.conceptos;
	}

	public void setConceptos(Set conceptos) {
		this.conceptos = conceptos;
	}

	public Set getPlancapacidads() {
		return this.plancapacidads;
	}

	public void setPlancapacidads(Set plancapacidads) {
		this.plancapacidads = plancapacidads;
	}

	public Set getRecursos() {
		return this.recursos;
	}

	public void setRecursos(Set recursos) {
		this.recursos = recursos;
	}

	public Set getTipomedioalmacenamientos() {
		return this.tipomedioalmacenamientos;
	}

	public void setTipomedioalmacenamientos(Set tipomedioalmacenamientos) {
		this.tipomedioalmacenamientos = tipomedioalmacenamientos;
	}

	public Set getDosificacions() {
		return this.dosificacions;
	}

	public void setDosificacions(Set dosificacions) {
		this.dosificacions = dosificacions;
	}

	public Set getFactordosificacions() {
		return this.factordosificacions;
	}

	public void setFactordosificacions(Set factordosificacions) {
		this.factordosificacions = factordosificacions;
	}

	public Set getVariableoperacions() {
		return this.variableoperacions;
	}

	public void setVariableoperacions(Set variableoperacions) {
		this.variableoperacions = variableoperacions;
	}

}