package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Consumocomponenteajuste entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Consumocomponenteajuste implements java.io.Serializable {

	// Fields

	private Long pkCodigoConsumocomponenteajus;
	private Puestotrabajoproduccion puestotrabajoproduccion;
	private Componente componente;
	private Double tmProdConsumocomponenteajus;
	private Double porcentProduccConsucompajuste;
	private Double tmRealConsumocomponenteajuste;
	private Double porcentajeRealConsumocomponen;
	private Double diferenciaConsumocomponenteaju;
	private Boolean editadoManualConsumocompajus;
	private Double porcentCarbonConsucompajuste;
	private Set movimientoajustes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Consumocomponenteajuste() {
	}

	/** minimal constructor */
	public Consumocomponenteajuste(Componente componente, Double tmProdConsumocomponenteajus,
			Double porcentProduccConsucompajuste, Double tmRealConsumocomponenteajuste, Double porcentajeRealConsumocomponen,
			Double diferenciaConsumocomponenteaju) {
		this.componente = componente;
		this.tmProdConsumocomponenteajus = tmProdConsumocomponenteajus;
		this.porcentProduccConsucompajuste = porcentProduccConsucompajuste;
		this.tmRealConsumocomponenteajuste = tmRealConsumocomponenteajuste;
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
		this.diferenciaConsumocomponenteaju = diferenciaConsumocomponenteaju;
	}

	/** full constructor */
	public Consumocomponenteajuste(Componente componente, Double tmProdConsumocomponenteajus,
			Double porcentProduccConsucompajuste, Double tmRealConsumocomponenteajuste, Double porcentajeRealConsumocomponen,
			Double diferenciaConsumocomponenteaju, Set movimientoajustes) {
		this.componente = componente;
		this.tmProdConsumocomponenteajus = tmProdConsumocomponenteajus;
		this.porcentProduccConsucompajuste = porcentProduccConsucompajuste;
		this.tmRealConsumocomponenteajuste = tmRealConsumocomponenteajuste;
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
		this.diferenciaConsumocomponenteaju = diferenciaConsumocomponenteaju;
		this.movimientoajustes = movimientoajustes;
	}

	// Property accessors

	public Long getPkCodigoConsumocomponenteajus() {
		return this.pkCodigoConsumocomponenteajus;
	}

	public void setPkCodigoConsumocomponenteajus(Long pkCodigoConsumocomponenteajus) {
		this.pkCodigoConsumocomponenteajus = pkCodigoConsumocomponenteajus;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Double getTmProdConsumocomponenteajus() {
		return this.tmProdConsumocomponenteajus;
	}

	public void setTmProdConsumocomponenteajus(Double tmProdConsumocomponenteajus) {
		this.tmProdConsumocomponenteajus = tmProdConsumocomponenteajus;
	}

	public Double getPorcentProduccConsucompajuste() {
		return this.porcentProduccConsucompajuste;
	}

	public void setPorcentProduccConsucompajuste(Double porcentProduccConsucompajuste) {
		this.porcentProduccConsucompajuste = porcentProduccConsucompajuste;
	}

	public Double getTmRealConsumocomponenteajuste() {
		return this.tmRealConsumocomponenteajuste;
	}

	public void setTmRealConsumocomponenteajuste(Double tmRealConsumocomponenteajuste) {
		this.tmRealConsumocomponenteajuste = tmRealConsumocomponenteajuste;
	}

	public Double getPorcentajeRealConsumocomponen() {
		return this.porcentajeRealConsumocomponen;
	}

	public void setPorcentajeRealConsumocomponen(Double porcentajeRealConsumocomponen) {
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
	}

	public Double getDiferenciaConsumocomponenteaju() {
		return this.diferenciaConsumocomponenteaju;
	}

	public void setDiferenciaConsumocomponenteaju(Double diferenciaConsumocomponenteaju) {
		this.diferenciaConsumocomponenteaju = diferenciaConsumocomponenteaju;
	}

	public Set getMovimientoajustes() {
		return this.movimientoajustes;
	}

	public void setMovimientoajustes(Set movimientoajustes) {
		this.movimientoajustes = movimientoajustes;
	}

	public Puestotrabajoproduccion getPuestotrabajoproduccion() {
		return puestotrabajoproduccion;
	}

	public void setPuestotrabajoproduccion(Puestotrabajoproduccion puestotrabajoproduccion) {
		this.puestotrabajoproduccion = puestotrabajoproduccion;
	}

	public Boolean getEditadoManualConsumocompajus() {
		return editadoManualConsumocompajus;
	}

	public void setEditadoManualConsumocompajus(Boolean editadoManualConsumocompajus) {
		this.editadoManualConsumocompajus = editadoManualConsumocompajus;
	}

	/**
	 * @return the porcentCarbonConsucompajuste
	 */
	public Double getPorcentCarbonConsucompajuste() {
		return porcentCarbonConsucompajuste;
	}

	/**
	 * @param porcentCarbonConsucompajuste the porcentCarbonConsucompajuste to
	 *            set
	 */
	public void setPorcentCarbonConsucompajuste(Double porcentCarbonConsucompajuste) {
		this.porcentCarbonConsucompajuste = porcentCarbonConsucompajuste;
	}

}