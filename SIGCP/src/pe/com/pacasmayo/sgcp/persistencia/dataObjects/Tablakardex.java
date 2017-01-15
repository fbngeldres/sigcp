package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tablakardex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tablakardex implements java.io.Serializable {

	// Fields

	private Long pkCodigoTablakardex;
	private Producciondiaria producciondiaria;
	private Almacen almacen;
	private Ubicacion ubicacionByFkCodigoUbicacionDestino;
	private Ubicacion ubicacionByFkCodigoUbicacionOrigen;
	private Date fechaTablakardex;
	private String observacionTablakardex;
	private Double ingresoTablakardex;
	private Double consumoTablakardex;
	private Double saldoInicialTablakardex;
	private Double stockFisicoTablakardex;
	private Double stockFinalTablakardex;
	private Double variacionTablakardex;
	private Double ingresoHumedadTablakardex;
	private Double consumoHumedadTablakardex;
	private Double ajustelogisticoTablakardex;
	private Set valorpromvariablecalidads = new HashSet(0);
	private Set factorkardexes = new HashSet(0);
	private Set consumocomponentes = new HashSet(0);
	private Set kardexmedioalmacenamientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tablakardex() {
	}

	/** minimal constructor */
	public Tablakardex(Producciondiaria producciondiaria, Almacen almacen, Ubicacion ubicacionByFkCodigoUbicacionOrigen,
			Date fechaTablakardex, Double ingresoTablakardex, Double consumoTablakardex, Double saldoInicialTablakardex,
			Double stockFisicoTablakardex, Double stockFinalTablakardex, Double variacionTablakardex) {
		this.producciondiaria = producciondiaria;
		this.almacen = almacen;
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
		this.fechaTablakardex = fechaTablakardex;
		this.ingresoTablakardex = ingresoTablakardex;
		this.consumoTablakardex = consumoTablakardex;
		this.saldoInicialTablakardex = saldoInicialTablakardex;
		this.stockFisicoTablakardex = stockFisicoTablakardex;
		this.stockFinalTablakardex = stockFinalTablakardex;
		this.variacionTablakardex = variacionTablakardex;
	}

	public Tablakardex(Long pkCodigoTablakardex, Producciondiaria producciondiaria, Almacen almacen,
			Ubicacion ubicacionByFkCodigoUbicacionDestino, Ubicacion ubicacionByFkCodigoUbicacionOrigen, Date fechaTablakardex,
			String observacionTablakardex, Double ingresoTablakardex, Double consumoTablakardex, Double saldoInicialTablakardex,
			Double stockFisicoTablakardex, Double stockFinalTablakardex, Double variacionTablakardex,
			Double ingresoHumedadTablakardex, Double consumoHumedadTablakardex, Double ajustelogisticoTablakardex,
			Set valorpromvariablecalidads, Set factorkardexes, Set consumocomponentes, Set kardexmedioalmacenamientos) {
		super();
		this.pkCodigoTablakardex = pkCodigoTablakardex;
		this.producciondiaria = producciondiaria;
		this.almacen = almacen;
		this.ubicacionByFkCodigoUbicacionDestino = ubicacionByFkCodigoUbicacionDestino;
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
		this.fechaTablakardex = fechaTablakardex;
		this.observacionTablakardex = observacionTablakardex;
		this.ingresoTablakardex = ingresoTablakardex;
		this.consumoTablakardex = consumoTablakardex;
		this.saldoInicialTablakardex = saldoInicialTablakardex;
		this.stockFisicoTablakardex = stockFisicoTablakardex;
		this.stockFinalTablakardex = stockFinalTablakardex;
		this.variacionTablakardex = variacionTablakardex;
		this.ingresoHumedadTablakardex = ingresoHumedadTablakardex;
		this.consumoHumedadTablakardex = consumoHumedadTablakardex;
		this.ajustelogisticoTablakardex = ajustelogisticoTablakardex;
		this.valorpromvariablecalidads = valorpromvariablecalidads;
		this.factorkardexes = factorkardexes;
		this.consumocomponentes = consumocomponentes;
		this.kardexmedioalmacenamientos = kardexmedioalmacenamientos;
	}

	// Property accessors

	public Long getPkCodigoTablakardex() {
		return this.pkCodigoTablakardex;
	}

	public void setPkCodigoTablakardex(Long pkCodigoTablakardex) {
		this.pkCodigoTablakardex = pkCodigoTablakardex;
	}

	public Producciondiaria getProducciondiaria() {
		return this.producciondiaria;
	}

	public void setProducciondiaria(Producciondiaria producciondiaria) {
		this.producciondiaria = producciondiaria;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Ubicacion getUbicacionByFkCodigoUbicacionDestino() {
		return this.ubicacionByFkCodigoUbicacionDestino;
	}

	public void setUbicacionByFkCodigoUbicacionDestino(Ubicacion ubicacionByFkCodigoUbicacionDestino) {
		this.ubicacionByFkCodigoUbicacionDestino = ubicacionByFkCodigoUbicacionDestino;
	}

	public Ubicacion getUbicacionByFkCodigoUbicacionOrigen() {
		return this.ubicacionByFkCodigoUbicacionOrigen;
	}

	public void setUbicacionByFkCodigoUbicacionOrigen(Ubicacion ubicacionByFkCodigoUbicacionOrigen) {
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
	}

	public Date getFechaTablakardex() {
		return this.fechaTablakardex;
	}

	public void setFechaTablakardex(Date fechaTablakardex) {
		this.fechaTablakardex = fechaTablakardex;
	}

	public String getObservacionTablakardex() {
		return this.observacionTablakardex;
	}

	public void setObservacionTablakardex(String observacionTablakardex) {
		this.observacionTablakardex = observacionTablakardex;
	}

	public Double getIngresoTablakardex() {
		return this.ingresoTablakardex;
	}

	public void setIngresoTablakardex(Double ingresoTablakardex) {
		this.ingresoTablakardex = ingresoTablakardex;
	}

	public Double getConsumoTablakardex() {
		return this.consumoTablakardex;
	}

	public void setConsumoTablakardex(Double consumoTablakardex) {
		this.consumoTablakardex = consumoTablakardex;
	}

	public Double getSaldoInicialTablakardex() {
		return this.saldoInicialTablakardex;
	}

	public void setSaldoInicialTablakardex(Double saldoInicialTablakardex) {
		this.saldoInicialTablakardex = saldoInicialTablakardex;
	}

	public Double getStockFisicoTablakardex() {
		return this.stockFisicoTablakardex;
	}

	public void setStockFisicoTablakardex(Double stockFisicoTablakardex) {
		this.stockFisicoTablakardex = stockFisicoTablakardex;
	}

	public Double getStockFinalTablakardex() {
		return this.stockFinalTablakardex;
	}

	public void setStockFinalTablakardex(Double stockFinalTablakardex) {
		this.stockFinalTablakardex = stockFinalTablakardex;
	}

	public Double getVariacionTablakardex() {
		return this.variacionTablakardex;
	}

	public void setVariacionTablakardex(Double variacionTablakardex) {
		this.variacionTablakardex = variacionTablakardex;
	}

	public Double getIngresoHumedadTablakardex() {
		return this.ingresoHumedadTablakardex;
	}

	public void setIngresoHumedadTablakardex(Double ingresoHumedadTablakardex) {
		this.ingresoHumedadTablakardex = ingresoHumedadTablakardex;
	}

	public Double getConsumoHumedadTablakardex() {
		return this.consumoHumedadTablakardex;
	}

	public void setConsumoHumedadTablakardex(Double consumoHumedadTablakardex) {
		this.consumoHumedadTablakardex = consumoHumedadTablakardex;
	}

	public Set getValorpromvariablecalidads() {
		return this.valorpromvariablecalidads;
	}

	public void setValorpromvariablecalidads(Set valorpromvariablecalidads) {
		this.valorpromvariablecalidads = valorpromvariablecalidads;
	}

	public Set getFactorkardexes() {
		return this.factorkardexes;
	}

	public void setFactorkardexes(Set factorkardexes) {
		this.factorkardexes = factorkardexes;
	}

	public Set getConsumocomponentes() {
		return this.consumocomponentes;
	}

	public void setConsumocomponentes(Set consumocomponentes) {
		this.consumocomponentes = consumocomponentes;
	}

	public Set getKardexmedioalmacenamientos() {
		return kardexmedioalmacenamientos;
	}

	public void setKardexmedioalmacenamientos(Set kardexmedioalmacenamientos) {
		this.kardexmedioalmacenamientos = kardexmedioalmacenamientos;
	}

	public Double getAjustelogisticoTablakardex() {
		return ajustelogisticoTablakardex;
	}

	public void setAjustelogisticoTablakardex(Double ajustelogisticoTablakardex) {
		this.ajustelogisticoTablakardex = ajustelogisticoTablakardex;
	}

}