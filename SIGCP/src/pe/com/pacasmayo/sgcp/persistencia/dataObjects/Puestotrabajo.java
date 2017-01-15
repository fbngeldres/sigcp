package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Puestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Puestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoPuestotrabajo;
	private Unidadmedida unidadmedida;
	private Estadopuestotrabajo estadopuestotrabajo;
	private Tipopuestotrabajo tipopuestotrabajo;
	private String nombrePuestotrabajo;
	private String descripcionPuestotrabajo;
	private String siglasPuestotrabajo;
	private String codigoSapPuestotrabajo;
	private Long codSccPuestotrabajo;
	private Set capacidadvolquetes = new HashSet(0);
	private Set medioalmacenamientos = new HashSet(0);
	private Set notificacionoperacions = new HashSet(0);
	private Set puestotrabajoproduccions = new HashSet(0);
	private Set ubicacionespecificas = new HashSet(0);
	private Set tableropuestotrabajos = new HashSet(0);
	private Set produccionpuestotrabajos = new HashSet(0);
	private Set consumocapacidadmanuals = new HashSet(0);
	private Set plancapacidads = new HashSet(0);
	private Set notificacioncanterasForFkCodigoCarguio = new HashSet(0);
	private Set emisors = new HashSet(0);
	private Set configuracionemisors = new HashSet(0);
	private Set notificacionproduccions = new HashSet(0);
	private Set operacions = new HashSet(0);
	private Set notificacioncanterasForFkCodigoPuestotrabajo = new HashSet(0);
	private Set plantillareportes = new HashSet(0);
	private Set equipovariableoperacions = new HashSet(0);
	private Set tasarealproduccions = new HashSet(0);
	private Set horapuntas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Puestotrabajo() {
	}

	/** minimal constructor */
	public Puestotrabajo(Unidadmedida unidadmedida, Estadopuestotrabajo estadopuestotrabajo, Tipopuestotrabajo tipopuestotrabajo,
			String nombrePuestotrabajo) {
		this.unidadmedida = unidadmedida;
		this.estadopuestotrabajo = estadopuestotrabajo;
		this.tipopuestotrabajo = tipopuestotrabajo;
		this.nombrePuestotrabajo = nombrePuestotrabajo;
	}

	/** full constructor */
	public Puestotrabajo(Unidadmedida unidadmedida, Estadopuestotrabajo estadopuestotrabajo, Tipopuestotrabajo tipopuestotrabajo,
			String nombrePuestotrabajo, String descripcionPuestotrabajo, String siglasPuestotrabajo,
			String codigoSapPuestotrabajo, Long codSccPuestotrabajo, Set capacidadvolquetes, Set medioalmacenamientos,
			Set notificacionoperacions, Set puestotrabajoproduccions, Set ubicacionespecificas, Set tableropuestotrabajos,
			Set produccionpuestotrabajos, Set consumocapacidadmanuals, Set plancapacidads,
			Set notificacioncanterasForFkCodigoCarguio, Set emisors, Set configuracionemisors, Set notificacionproduccions,
			Set operacions, Set notificacioncanterasForFkCodigoPuestotrabajo, Set plantillareportes,
			Set equipovariableoperacions, Set tasarealproduccions, Set horapuntas) {
		this.unidadmedida = unidadmedida;
		this.estadopuestotrabajo = estadopuestotrabajo;
		this.tipopuestotrabajo = tipopuestotrabajo;
		this.nombrePuestotrabajo = nombrePuestotrabajo;
		this.descripcionPuestotrabajo = descripcionPuestotrabajo;
		this.siglasPuestotrabajo = siglasPuestotrabajo;
		this.codigoSapPuestotrabajo = codigoSapPuestotrabajo;
		this.codSccPuestotrabajo = codSccPuestotrabajo;
		this.capacidadvolquetes = capacidadvolquetes;
		this.medioalmacenamientos = medioalmacenamientos;
		this.notificacionoperacions = notificacionoperacions;
		this.puestotrabajoproduccions = puestotrabajoproduccions;
		this.ubicacionespecificas = ubicacionespecificas;
		this.tableropuestotrabajos = tableropuestotrabajos;
		this.produccionpuestotrabajos = produccionpuestotrabajos;
		this.consumocapacidadmanuals = consumocapacidadmanuals;
		this.plancapacidads = plancapacidads;
		this.notificacioncanterasForFkCodigoCarguio = notificacioncanterasForFkCodigoCarguio;
		this.emisors = emisors;
		this.configuracionemisors = configuracionemisors;
		this.notificacionproduccions = notificacionproduccions;
		this.operacions = operacions;
		this.notificacioncanterasForFkCodigoPuestotrabajo = notificacioncanterasForFkCodigoPuestotrabajo;
		this.plantillareportes = plantillareportes;
		this.equipovariableoperacions = equipovariableoperacions;
		this.tasarealproduccions = tasarealproduccions;
		this.horapuntas = horapuntas;
	}

	// Property accessors

	public Long getPkCodigoPuestotrabajo() {
		return this.pkCodigoPuestotrabajo;
	}

	public void setPkCodigoPuestotrabajo(Long pkCodigoPuestotrabajo) {
		this.pkCodigoPuestotrabajo = pkCodigoPuestotrabajo;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Estadopuestotrabajo getEstadopuestotrabajo() {
		return this.estadopuestotrabajo;
	}

	public void setEstadopuestotrabajo(Estadopuestotrabajo estadopuestotrabajo) {
		this.estadopuestotrabajo = estadopuestotrabajo;
	}

	public Tipopuestotrabajo getTipopuestotrabajo() {
		return this.tipopuestotrabajo;
	}

	public void setTipopuestotrabajo(Tipopuestotrabajo tipopuestotrabajo) {
		this.tipopuestotrabajo = tipopuestotrabajo;
	}

	public String getNombrePuestotrabajo() {
		return this.nombrePuestotrabajo;
	}

	public void setNombrePuestotrabajo(String nombrePuestotrabajo) {
		this.nombrePuestotrabajo = nombrePuestotrabajo;
	}

	public String getDescripcionPuestotrabajo() {
		return this.descripcionPuestotrabajo;
	}

	public void setDescripcionPuestotrabajo(String descripcionPuestotrabajo) {
		this.descripcionPuestotrabajo = descripcionPuestotrabajo;
	}

	public String getSiglasPuestotrabajo() {
		return this.siglasPuestotrabajo;
	}

	public void setSiglasPuestotrabajo(String siglasPuestotrabajo) {
		this.siglasPuestotrabajo = siglasPuestotrabajo;
	}

	public String getCodigoSapPuestotrabajo() {
		return this.codigoSapPuestotrabajo;
	}

	public void setCodigoSapPuestotrabajo(String codigoSapPuestotrabajo) {
		this.codigoSapPuestotrabajo = codigoSapPuestotrabajo;
	}

	public Long getCodSccPuestotrabajo() {
		return this.codSccPuestotrabajo;
	}

	public void setCodSccPuestotrabajo(Long codSccPuestotrabajo) {
		this.codSccPuestotrabajo = codSccPuestotrabajo;
	}

	public Set getCapacidadvolquetes() {
		return this.capacidadvolquetes;
	}

	public void setCapacidadvolquetes(Set capacidadvolquetes) {
		this.capacidadvolquetes = capacidadvolquetes;
	}

	public Set getMedioalmacenamientos() {
		return this.medioalmacenamientos;
	}

	public void setMedioalmacenamientos(Set medioalmacenamientos) {
		this.medioalmacenamientos = medioalmacenamientos;
	}

	public Set getNotificacionoperacions() {
		return this.notificacionoperacions;
	}

	public void setNotificacionoperacions(Set notificacionoperacions) {
		this.notificacionoperacions = notificacionoperacions;
	}

	public Set getPuestotrabajoproduccions() {
		return this.puestotrabajoproduccions;
	}

	public void setPuestotrabajoproduccions(Set puestotrabajoproduccions) {
		this.puestotrabajoproduccions = puestotrabajoproduccions;
	}

	public Set getUbicacionespecificas() {
		return this.ubicacionespecificas;
	}

	public void setUbicacionespecificas(Set ubicacionespecificas) {
		this.ubicacionespecificas = ubicacionespecificas;
	}

	public Set getTableropuestotrabajos() {
		return this.tableropuestotrabajos;
	}

	public void setTableropuestotrabajos(Set tableropuestotrabajos) {
		this.tableropuestotrabajos = tableropuestotrabajos;
	}

	public Set getProduccionpuestotrabajos() {
		return this.produccionpuestotrabajos;
	}

	public void setProduccionpuestotrabajos(Set produccionpuestotrabajos) {
		this.produccionpuestotrabajos = produccionpuestotrabajos;
	}

	public Set getConsumocapacidadmanuals() {
		return this.consumocapacidadmanuals;
	}

	public void setConsumocapacidadmanuals(Set consumocapacidadmanuals) {
		this.consumocapacidadmanuals = consumocapacidadmanuals;
	}

	public Set getPlancapacidads() {
		return this.plancapacidads;
	}

	public void setPlancapacidads(Set plancapacidads) {
		this.plancapacidads = plancapacidads;
	}

	public Set getNotificacioncanterasForFkCodigoCarguio() {
		return this.notificacioncanterasForFkCodigoCarguio;
	}

	public void setNotificacioncanterasForFkCodigoCarguio(Set notificacioncanterasForFkCodigoCarguio) {
		this.notificacioncanterasForFkCodigoCarguio = notificacioncanterasForFkCodigoCarguio;
	}

	public Set getEmisors() {
		return this.emisors;
	}

	public void setEmisors(Set emisors) {
		this.emisors = emisors;
	}

	public Set getConfiguracionemisors() {
		return this.configuracionemisors;
	}

	public void setConfiguracionemisors(Set configuracionemisors) {
		this.configuracionemisors = configuracionemisors;
	}

	public Set getNotificacionproduccions() {
		return this.notificacionproduccions;
	}

	public void setNotificacionproduccions(Set notificacionproduccions) {
		this.notificacionproduccions = notificacionproduccions;
	}

	public Set getOperacions() {
		return this.operacions;
	}

	public void setOperacions(Set operacions) {
		this.operacions = operacions;
	}

	public Set getNotificacioncanterasForFkCodigoPuestotrabajo() {
		return this.notificacioncanterasForFkCodigoPuestotrabajo;
	}

	public void setNotificacioncanterasForFkCodigoPuestotrabajo(Set notificacioncanterasForFkCodigoPuestotrabajo) {
		this.notificacioncanterasForFkCodigoPuestotrabajo = notificacioncanterasForFkCodigoPuestotrabajo;
	}

	public Set getPlantillareportes() {
		return this.plantillareportes;
	}

	public void setPlantillareportes(Set plantillareportes) {
		this.plantillareportes = plantillareportes;
	}

	public Set getEquipovariableoperacions() {
		return this.equipovariableoperacions;
	}

	public void setEquipovariableoperacions(Set equipovariableoperacions) {
		this.equipovariableoperacions = equipovariableoperacions;
	}

	public Set getTasarealproduccions() {
		return this.tasarealproduccions;
	}

	public void setTasarealproduccions(Set tasarealproduccions) {
		this.tasarealproduccions = tasarealproduccions;
	}

	public Set getHorapuntas() {
		return this.horapuntas;
	}

	public void setHorapuntas(Set horapuntas) {
		this.horapuntas = horapuntas;
	}

}