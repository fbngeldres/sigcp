package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Producciondiaria entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Producciondiaria implements java.io.Serializable {

	// Fields

	private Long pkCodigoProducciondiaria;
	private Partediario partediario;
	private Ordenproduccion ordenproduccion;
	private Producto producto;
	private Double saldoInicialProducciondiaria;
	private Double ingresoProduccionProducciondi;
	private Double consumoProducciondiaria;
	private Double saldoFinalProducciondiaria;
	private Double ajustelogisticoProducciondiaria;
	private Set tablakardexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Producciondiaria() {
	}

	/** minimal constructor */
	public Producciondiaria(Partediario partediario, Ordenproduccion ordenproduccion, Double saldoInicialProducciondiaria,
			Double ingresoProduccionProducciondi, Double consumoProducciondiaria, Double saldoFinalProducciondiaria,
			Double ajustelogisticoProducciondiaria) {
		this.partediario = partediario;
		this.ordenproduccion = ordenproduccion;
		this.saldoInicialProducciondiaria = saldoInicialProducciondiaria;
		this.ingresoProduccionProducciondi = ingresoProduccionProducciondi;
		this.consumoProducciondiaria = consumoProducciondiaria;
		this.saldoFinalProducciondiaria = saldoFinalProducciondiaria;
		this.ajustelogisticoProducciondiaria = ajustelogisticoProducciondiaria; 
	}

	public Producciondiaria(Long pkCodigoProducciondiaria, Partediario partediario, Ordenproduccion ordenproduccion,
			Producto producto, Double saldoInicialProducciondiaria, Double ingresoProduccionProducciondi,
			Double consumoProducciondiaria, Double saldoFinalProducciondiaria, Double ajustelogisticoProducciondiaria,
			Set tablakardexes) {
		super();
		this.pkCodigoProducciondiaria = pkCodigoProducciondiaria;
		this.partediario = partediario;
		this.ordenproduccion = ordenproduccion;
		this.producto = producto;
		this.saldoInicialProducciondiaria = saldoInicialProducciondiaria;
		this.ingresoProduccionProducciondi = ingresoProduccionProducciondi;
		this.consumoProducciondiaria = consumoProducciondiaria;
		this.saldoFinalProducciondiaria = saldoFinalProducciondiaria;
		this.ajustelogisticoProducciondiaria = ajustelogisticoProducciondiaria;
		this.tablakardexes = tablakardexes;
	}

	public Producciondiaria(Partediario partediario, Ordenproduccion ordenproduccion) {
		this.partediario = partediario;
		this.ordenproduccion = ordenproduccion;
	}

	// Property accessors
	public Long getPkCodigoProducciondiaria() {
		return this.pkCodigoProducciondiaria;
	}

	public void setPkCodigoProducciondiaria(Long pkCodigoProducciondiaria) {
		this.pkCodigoProducciondiaria = pkCodigoProducciondiaria;
	}

	public Partediario getPartediario() {
		return this.partediario;
	}

	public void setPartediario(Partediario partediario) {
		this.partediario = partediario;
	}

	public Ordenproduccion getOrdenproduccion() {
		return this.ordenproduccion;
	}

	public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Double getSaldoInicialProducciondiaria() {
		return this.saldoInicialProducciondiaria;
	}

	public void setSaldoInicialProducciondiaria(Double saldoInicialProducciondiaria) {
		this.saldoInicialProducciondiaria = saldoInicialProducciondiaria;
	}

	public Double getIngresoProduccionProducciondi() {
		return this.ingresoProduccionProducciondi;
	}

	public void setIngresoProduccionProducciondi(Double ingresoProduccionProducciondi) {
		this.ingresoProduccionProducciondi = ingresoProduccionProducciondi;
	}

	public Double getConsumoProducciondiaria() {
		return this.consumoProducciondiaria;
	}

	public void setConsumoProducciondiaria(Double consumoProducciondiaria) {
		this.consumoProducciondiaria = consumoProducciondiaria;
	}

	public Double getSaldoFinalProducciondiaria() {
		return this.saldoFinalProducciondiaria;
	}

	public void setSaldoFinalProducciondiaria(Double saldoFinalProducciondiaria) {
		this.saldoFinalProducciondiaria = saldoFinalProducciondiaria;
	}

	public Set getTablakardexes() {
		return this.tablakardexes;
	}

	public void setTablakardexes(Set tablakardexes) {
		this.tablakardexes = tablakardexes;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getAjustelogisticoProducciondiaria() {
		return ajustelogisticoProducciondiaria;
	}

	public void setAjustelogisticoProducciondiaria(Double ajustelogisticoProducciondiaria) {
		this.ajustelogisticoProducciondiaria = ajustelogisticoProducciondiaria;
	}

}