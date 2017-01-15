package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Consumocomponentepuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocomponentepuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocomponentepues;
	private Consumocomponenteajuste consumocomponenteajuste;
	private Puestotrabajoproduccion puestotrabajoproduccion;
	private Double tmConsumocomponentepuestotraba;
	private Double porcentajeConsumocomponentepue;
	private Double tmRealConsumocomponentepuesto;
	private Double porcentajeRealConsumocomponen;
	private Double diferenciaConsumocomponentepue;
	private Double poderCalorificoConsumocompone;
	private String observacionConsumocomponentepu;
	private Set movimientoajustes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Consumocomponentepuestotrabajo() {
	}

	/** minimal constructor */
	public Consumocomponentepuestotrabajo(Consumocomponenteajuste consumocomponenteajuste, Double tmConsumocomponentepuestotraba,
			Double porcentajeConsumocomponentepue, Double tmRealConsumocomponentepuesto, Double porcentajeRealConsumocomponen,
			Double diferenciaConsumocomponentepue) {
		this.consumocomponenteajuste = consumocomponenteajuste;
		this.tmConsumocomponentepuestotraba = tmConsumocomponentepuestotraba;
		this.porcentajeConsumocomponentepue = porcentajeConsumocomponentepue;
		this.tmRealConsumocomponentepuesto = tmRealConsumocomponentepuesto;
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
		this.diferenciaConsumocomponentepue = diferenciaConsumocomponentepue;
	}

	/** full constructor */
	public Consumocomponentepuestotrabajo(Consumocomponenteajuste consumocomponenteajuste,
			Puestotrabajoproduccion puestotrabajoproduccion, Double tmConsumocomponentepuestotraba,
			Double porcentajeConsumocomponentepue, Double tmRealConsumocomponentepuesto, Double porcentajeRealConsumocomponen,
			Double diferenciaConsumocomponentepue, Double poderCalorificoConsumocompone, String observacionConsumocomponentepu,
			Set movimientoajustes) {
		this.consumocomponenteajuste = consumocomponenteajuste;
		this.puestotrabajoproduccion = puestotrabajoproduccion;
		this.tmConsumocomponentepuestotraba = tmConsumocomponentepuestotraba;
		this.porcentajeConsumocomponentepue = porcentajeConsumocomponentepue;
		this.tmRealConsumocomponentepuesto = tmRealConsumocomponentepuesto;
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
		this.diferenciaConsumocomponentepue = diferenciaConsumocomponentepue;
		this.poderCalorificoConsumocompone = poderCalorificoConsumocompone;
		this.observacionConsumocomponentepu = observacionConsumocomponentepu;
		this.movimientoajustes = movimientoajustes;
	}

	// Property accessors

	public Long getPkCodigoConsumocomponentepues() {
		return this.pkCodigoConsumocomponentepues;
	}

	public void setPkCodigoConsumocomponentepues(Long pkCodigoConsumocomponentepues) {
		this.pkCodigoConsumocomponentepues = pkCodigoConsumocomponentepues;
	}

	public Consumocomponenteajuste getConsumocomponenteajuste() {
		return this.consumocomponenteajuste;
	}

	public void setConsumocomponenteajuste(Consumocomponenteajuste consumocomponenteajuste) {
		this.consumocomponenteajuste = consumocomponenteajuste;
	}

	public Puestotrabajoproduccion getPuestotrabajoproduccion() {
		return this.puestotrabajoproduccion;
	}

	public void setPuestotrabajoproduccion(Puestotrabajoproduccion puestotrabajoproduccion) {
		this.puestotrabajoproduccion = puestotrabajoproduccion;
	}

	public Double getTmConsumocomponentepuestotraba() {
		return this.tmConsumocomponentepuestotraba;
	}

	public void setTmConsumocomponentepuestotraba(Double tmConsumocomponentepuestotraba) {
		this.tmConsumocomponentepuestotraba = tmConsumocomponentepuestotraba;
	}

	public Double getPorcentajeConsumocomponentepue() {
		return this.porcentajeConsumocomponentepue;
	}

	public void setPorcentajeConsumocomponentepue(Double porcentajeConsumocomponentepue) {
		this.porcentajeConsumocomponentepue = porcentajeConsumocomponentepue;
	}

	public Double getTmRealConsumocomponentepuesto() {
		return this.tmRealConsumocomponentepuesto;
	}

	public void setTmRealConsumocomponentepuesto(Double tmRealConsumocomponentepuesto) {
		this.tmRealConsumocomponentepuesto = tmRealConsumocomponentepuesto;
	}

	public Double getPorcentajeRealConsumocomponen() {
		return this.porcentajeRealConsumocomponen;
	}

	public void setPorcentajeRealConsumocomponen(Double porcentajeRealConsumocomponen) {
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
	}

	public Double getDiferenciaConsumocomponentepue() {
		return this.diferenciaConsumocomponentepue;
	}

	public void setDiferenciaConsumocomponentepue(Double diferenciaConsumocomponentepue) {
		this.diferenciaConsumocomponentepue = diferenciaConsumocomponentepue;
	}

	public Double getPoderCalorificoConsumocompone() {
		return this.poderCalorificoConsumocompone;
	}

	public void setPoderCalorificoConsumocompone(Double poderCalorificoConsumocompone) {
		this.poderCalorificoConsumocompone = poderCalorificoConsumocompone;
	}

	public String getObservacionConsumocomponentepu() {
		return this.observacionConsumocomponentepu;
	}

	public void setObservacionConsumocomponentepu(String observacionConsumocomponentepu) {
		this.observacionConsumocomponentepu = observacionConsumocomponentepu;
	}

	public Set getMovimientoajustes() {
		return this.movimientoajustes;
	}

	public void setMovimientoajustes(Set movimientoajustes) {
		this.movimientoajustes = movimientoajustes;
	}

}