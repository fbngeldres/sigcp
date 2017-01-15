package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Plananual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plananual implements java.io.Serializable {

	// Fields
	private Long pkCodigoPlananual;
	private Usuario usuarioByFkCodigoUsuarioRegistra;
	private Lineanegocio lineanegocio;
	private Usuario usuarioByFkCodigoUsuarioAprueba;
	private Estadoplananual estadoplananual;
	private Integer annoPlananual;
	private String versionPlananual;
	private Double produccionPlananual;
	private Double necesidadComercialPlananual;
	private String observacionPlananual;
	private Date fechaRegistroPlananual;
	private Date fechaAprobacionPlananual;
	private Short mesCorteVersionPlananual;
	private Set capacidadoperativaregistromensus = new HashSet(0);
	private Set recursoregistromensuals = new HashSet(0);
	private Set plancomercializacions = new HashSet(0);
	private Set plannecesidads = new HashSet(0);
	private Set dosificacionregistromensuals = new HashSet(0);
	private Set ordenproduccionplans = new HashSet(0);

	// Constructors

	/** default constructor */
	public Plananual() {
	}

	/** minimal constructor */
	public Plananual(Lineanegocio lineanegocio, Usuario usuarioByFkCodigoUsuarioAprueba, Estadoplananual estadoplananual,
			Integer annoPlananual, String versionPlananual, Double produccionPlananual, Double necesidadComercialPlananual) {
		this.lineanegocio = lineanegocio;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.estadoplananual = estadoplananual;
		this.annoPlananual = annoPlananual;
		this.versionPlananual = versionPlananual;
		this.produccionPlananual = produccionPlananual;
		this.necesidadComercialPlananual = necesidadComercialPlananual;
	}

	/** full constructor */
	public Plananual(Usuario usuarioByFkCodigoUsuarioRegistra, Lineanegocio lineanegocio,
			Usuario usuarioByFkCodigoUsuarioAprueba, Estadoplananual estadoplananual, Integer annoPlananual,
			String versionPlananual, Double produccionPlananual, Double necesidadComercialPlananual, String observacionPlananual,
			Date fechaRegistroPlananual, Date fechaAprobacionPlananual, Short mesCorteVersionPlananual,
			Set capacidadoperativaregistromensus, Set recursoregistromensuals, Set plancomercializacions, Set plannecesidads,
			Set dosificacionregistromensuals, Set ordenproduccionplans) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.lineanegocio = lineanegocio;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.estadoplananual = estadoplananual;
		this.annoPlananual = annoPlananual;
		this.versionPlananual = versionPlananual;
		this.produccionPlananual = produccionPlananual;
		this.necesidadComercialPlananual = necesidadComercialPlananual;
		this.observacionPlananual = observacionPlananual;
		this.fechaRegistroPlananual = fechaRegistroPlananual;
		this.fechaAprobacionPlananual = fechaAprobacionPlananual;
		this.mesCorteVersionPlananual = mesCorteVersionPlananual;
		this.capacidadoperativaregistromensus = capacidadoperativaregistromensus;
		this.recursoregistromensuals = recursoregistromensuals;
		this.plancomercializacions = plancomercializacions;
		this.plannecesidads = plannecesidads;
		this.dosificacionregistromensuals = dosificacionregistromensuals;
		this.ordenproduccionplans = ordenproduccionplans;
	}

	// Property accessors

	public Long getPkCodigoPlananual() {
		return this.pkCodigoPlananual;
	}

	public void setPkCodigoPlananual(Long pkCodigoPlananual) {
		this.pkCodigoPlananual = pkCodigoPlananual;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistra() {
		return this.usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(Usuario usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAprueba() {
		return this.usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(Usuario usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public Estadoplananual getEstadoplananual() {
		return this.estadoplananual;
	}

	public void setEstadoplananual(Estadoplananual estadoplananual) {
		this.estadoplananual = estadoplananual;
	}

	public Integer getAnnoPlananual() {
		return this.annoPlananual;
	}

	public void setAnnoPlananual(Integer annoPlananual) {
		this.annoPlananual = annoPlananual;
	}

	public String getVersionPlananual() {
		return this.versionPlananual;
	}

	public void setVersionPlananual(String versionPlananual) {
		this.versionPlananual = versionPlananual;
	}

	public Double getProduccionPlananual() {
		return this.produccionPlananual;
	}

	public void setProduccionPlananual(Double produccionPlananual) {
		this.produccionPlananual = produccionPlananual;
	}

	public Double getNecesidadComercialPlananual() {
		return this.necesidadComercialPlananual;
	}

	public void setNecesidadComercialPlananual(Double necesidadComercialPlananual) {
		this.necesidadComercialPlananual = necesidadComercialPlananual;
	}

	public String getObservacionPlananual() {
		return this.observacionPlananual;
	}

	public void setObservacionPlananual(String observacionPlananual) {
		this.observacionPlananual = observacionPlananual;
	}

	public Date getFechaRegistroPlananual() {
		return this.fechaRegistroPlananual;
	}

	public void setFechaRegistroPlananual(Date fechaRegistroPlananual) {
		this.fechaRegistroPlananual = fechaRegistroPlananual;
	}

	public Date getFechaAprobacionPlananual() {
		return this.fechaAprobacionPlananual;
	}

	public void setFechaAprobacionPlananual(Date fechaAprobacionPlananual) {
		this.fechaAprobacionPlananual = fechaAprobacionPlananual;
	}

	public Short getMesCorteVersionPlananual() {
		return this.mesCorteVersionPlananual;
	}

	public void setMesCorteVersionPlananual(Short mesCorteVersionPlananual) {
		this.mesCorteVersionPlananual = mesCorteVersionPlananual;
	}

	public Set getCapacidadoperativaregistromensus() {
		return this.capacidadoperativaregistromensus;
	}

	public void setCapacidadoperativaregistromensus(Set capacidadoperativaregistromensus) {
		this.capacidadoperativaregistromensus = capacidadoperativaregistromensus;
	}

	public Set getRecursoregistromensuals() {
		return this.recursoregistromensuals;
	}

	public void setRecursoregistromensuals(Set recursoregistromensuals) {
		this.recursoregistromensuals = recursoregistromensuals;
	}

	public Set getPlancomercializacions() {
		return this.plancomercializacions;
	}

	public void setPlancomercializacions(Set plancomercializacions) {
		this.plancomercializacions = plancomercializacions;
	}

	public Set getPlannecesidads() {
		return this.plannecesidads;
	}

	public void setPlannecesidads(Set plannecesidads) {
		this.plannecesidads = plannecesidads;
	}

	public Set getDosificacionregistromensuals() {
		return this.dosificacionregistromensuals;
	}

	public void setDosificacionregistromensuals(Set dosificacionregistromensuals) {
		this.dosificacionregistromensuals = dosificacionregistromensuals;
	}

	public Set getOrdenproduccionplans() {
		return this.ordenproduccionplans;
	}

	public void setOrdenproduccionplans(Set ordenproduccionplans) {
		this.ordenproduccionplans = ordenproduccionplans;
	}

}