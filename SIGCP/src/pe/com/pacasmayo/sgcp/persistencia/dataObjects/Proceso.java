package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Proceso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Proceso implements java.io.Serializable {

	// Fields

	private Long pkCodigoProceso;
	private Tipoproducto tipoproducto;
	private Lineanegocio lineanegocio;
	private String nombreProceso;
	private String descripcionProceso;
	private Short ordenEjecucionProceso;
	private Long codigoSccProceso;
	private String codigoSapProceso;
	private String siglasProceso;
	private Set produccions = new HashSet(0);
	private Set plantillareportes = new HashSet(0);
	private Set actividads = new HashSet(0);
	private Set produccionsemanals = new HashSet(0);
	private Set receptors = new HashSet(0);
	private Set registromedicions = new HashSet(0);
	private Set grupoprocesos = new HashSet(0);
	private Set configuracionreceptors = new HashSet(0);

	// Constructors

	/** default constructor */
	public Proceso() {
	}

	/** minimal constructor */
	public Proceso(Lineanegocio lineanegocio, String nombreProceso) {
		this.lineanegocio = lineanegocio;
		this.nombreProceso = nombreProceso;
	}

	/** full constructor */
	public Proceso(Tipoproducto tipoproducto, Lineanegocio lineanegocio, String nombreProceso, String descripcionProceso,
			Short ordenEjecucionProceso, Long codigoSccProceso, String codigoSapProceso, String siglasProceso, Set produccions,
			Set plantillareportes, Set actividads, Set produccionsemanals, Set receptors, Set registromedicions,
			Set grupoprocesos, Set configuracionreceptors) {
		this.tipoproducto = tipoproducto;
		this.lineanegocio = lineanegocio;
		this.nombreProceso = nombreProceso;
		this.descripcionProceso = descripcionProceso;
		this.ordenEjecucionProceso = ordenEjecucionProceso;
		this.codigoSccProceso = codigoSccProceso;
		this.codigoSapProceso = codigoSapProceso;
		this.siglasProceso = siglasProceso;
		this.produccions = produccions;
		this.plantillareportes = plantillareportes;
		this.actividads = actividads;
		this.produccionsemanals = produccionsemanals;
		this.receptors = receptors;
		this.registromedicions = registromedicions;
		this.grupoprocesos = grupoprocesos;
		this.configuracionreceptors = configuracionreceptors;
	}

	// Property accessors

	public Long getPkCodigoProceso() {
		return this.pkCodigoProceso;
	}

	public void setPkCodigoProceso(Long pkCodigoProceso) {
		this.pkCodigoProceso = pkCodigoProceso;
	}

	public Tipoproducto getTipoproducto() {
		return this.tipoproducto;
	}

	public void setTipoproducto(Tipoproducto tipoproducto) {
		this.tipoproducto = tipoproducto;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public String getNombreProceso() {
		return this.nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getDescripcionProceso() {
		return this.descripcionProceso;
	}

	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}

	public Short getOrdenEjecucionProceso() {
		return this.ordenEjecucionProceso;
	}

	public void setOrdenEjecucionProceso(Short ordenEjecucionProceso) {
		this.ordenEjecucionProceso = ordenEjecucionProceso;
	}

	public Long getCodigoSccProceso() {
		return this.codigoSccProceso;
	}

	public void setCodigoSccProceso(Long codigoSccProceso) {
		this.codigoSccProceso = codigoSccProceso;
	}

	public String getCodigoSapProceso() {
		return this.codigoSapProceso;
	}

	public void setCodigoSapProceso(String codigoSapProceso) {
		this.codigoSapProceso = codigoSapProceso;
	}

	public String getSiglasProceso() {
		return this.siglasProceso;
	}

	public void setSiglasProceso(String siglasProceso) {
		this.siglasProceso = siglasProceso;
	}

	public Set getProduccions() {
		return this.produccions;
	}

	public void setProduccions(Set produccions) {
		this.produccions = produccions;
	}

	public Set getPlantillareportes() {
		return this.plantillareportes;
	}

	public void setPlantillareportes(Set plantillareportes) {
		this.plantillareportes = plantillareportes;
	}

	public Set getActividads() {
		return this.actividads;
	}

	public void setActividads(Set actividads) {
		this.actividads = actividads;
	}

	public Set getProduccionsemanals() {
		return this.produccionsemanals;
	}

	public void setProduccionsemanals(Set produccionsemanals) {
		this.produccionsemanals = produccionsemanals;
	}

	public Set getReceptors() {
		return this.receptors;
	}

	public void setReceptors(Set receptors) {
		this.receptors = receptors;
	}

	public Set getRegistromedicions() {
		return this.registromedicions;
	}

	public void setRegistromedicions(Set registromedicions) {
		this.registromedicions = registromedicions;
	}

	public Set getGrupoprocesos() {
		return this.grupoprocesos;
	}

	public void setGrupoprocesos(Set grupoprocesos) {
		this.grupoprocesos = grupoprocesos;
	}

	public Set getConfiguracionreceptors() {
		return this.configuracionreceptors;
	}

	public void setConfiguracionreceptors(Set configuracionreceptors) {
		this.configuracionreceptors = configuracionreceptors;
	}

}