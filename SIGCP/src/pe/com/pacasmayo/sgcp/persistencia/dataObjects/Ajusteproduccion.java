package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ajusteproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ajusteproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoAjusteproduccion;
	private Estadoajusteproduccion estadoajusteproduccion;
	private Usuario usuarioByFkCodigoUsuarioRegistra;
	private Usuario usuarioByFkCodigoUsuario;
	private Periodocontable periodocontable;
	private Lineanegocio lineanegocio;
	private Date fechaAprobacionAjusteproducci;
	private Set ajusteproductos = new HashSet(0);
	private Boolean consumoEnviadoSap;
	private Boolean combutibleEnviadoSap;

	// Constructors

	/** default constructor */
	public Ajusteproduccion() {
	}

	/** minimal constructor */
	public Ajusteproduccion(Estadoajusteproduccion estadoajusteproduccion, Usuario usuarioByFkCodigoUsuarioRegistra,
			Periodocontable periodocontable, Lineanegocio lineanegocio) {
		this.estadoajusteproduccion = estadoajusteproduccion;
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.periodocontable = periodocontable;
		this.lineanegocio = lineanegocio;
	}

	// Full constructor
	public Ajusteproduccion(Long pkCodigoAjusteproduccion, Estadoajusteproduccion estadoajusteproduccion,
			Usuario usuarioByFkCodigoUsuarioRegistra, Usuario usuarioByFkCodigoUsuario, Periodocontable periodocontable,
			Lineanegocio lineanegocio, Date fechaAprobacionAjusteproducci, Set ajusteproductos, Boolean consumoEnviadoSap,
			Boolean combutibleEnviadoSap) {
		super();
		this.pkCodigoAjusteproduccion = pkCodigoAjusteproduccion;
		this.estadoajusteproduccion = estadoajusteproduccion;
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
		this.usuarioByFkCodigoUsuario = usuarioByFkCodigoUsuario;
		this.periodocontable = periodocontable;
		this.lineanegocio = lineanegocio;
		this.fechaAprobacionAjusteproducci = fechaAprobacionAjusteproducci;
		this.ajusteproductos = ajusteproductos;
		this.consumoEnviadoSap = consumoEnviadoSap;
		this.combutibleEnviadoSap = combutibleEnviadoSap;
	}

	// Property accessors
	public Long getPkCodigoAjusteproduccion() {
		return this.pkCodigoAjusteproduccion;
	}

	public void setPkCodigoAjusteproduccion(Long pkCodigoAjusteproduccion) {
		this.pkCodigoAjusteproduccion = pkCodigoAjusteproduccion;
	}

	public Estadoajusteproduccion getEstadoajusteproduccion() {
		return this.estadoajusteproduccion;
	}

	public void setEstadoajusteproduccion(Estadoajusteproduccion estadoajusteproduccion) {
		this.estadoajusteproduccion = estadoajusteproduccion;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistra() {
		return this.usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(Usuario usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public Usuario getUsuarioByFkCodigoUsuario() {
		return this.usuarioByFkCodigoUsuario;
	}

	public void setUsuarioByFkCodigoUsuario(Usuario usuarioByFkCodigoUsuario) {
		this.usuarioByFkCodigoUsuario = usuarioByFkCodigoUsuario;
	}

	public Periodocontable getPeriodocontable() {
		return this.periodocontable;
	}

	public void setPeriodocontable(Periodocontable periodocontable) {
		this.periodocontable = periodocontable;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Date getFechaAprobacionAjusteproducci() {
		return this.fechaAprobacionAjusteproducci;
	}

	public void setFechaAprobacionAjusteproducci(Date fechaAprobacionAjusteproducci) {
		this.fechaAprobacionAjusteproducci = fechaAprobacionAjusteproducci;
	}

	/**
	 * @return the ajusteproductos
	 */
	public Set getAjusteproductos() {
		return ajusteproductos;
	}

	/**
	 * @param ajusteproductos the ajusteproductos to set
	 */
	public void setAjusteproductos(Set ajusteproductos) {
		this.ajusteproductos = ajusteproductos;
	}

	/**
	 * @return the consumoEnviadoSap
	 */
	public Boolean getConsumoEnviadoSap() {
		return consumoEnviadoSap;
	}

	/**
	 * @param consumoEnviadoSap the consumoEnviadoSap to set
	 */
	public void setConsumoEnviadoSap(Boolean consumoEnviadoSap) {
		this.consumoEnviadoSap = consumoEnviadoSap;
	}

	/**
	 * @return the combutibleEnviadoSap
	 */
	public Boolean getCombutibleEnviadoSap() {
		return combutibleEnviadoSap;
	}

	/**
	 * @param combutibleEnviadoSap the combutibleEnviadoSap to set
	 */
	public void setCombutibleEnviadoSap(Boolean combutibleEnviadoSap) {
		this.combutibleEnviadoSap = combutibleEnviadoSap;
	}

}