package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: AjusteProduccionBean.java
 * Modificado: May 26, 2010 4:08:12 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface AjusteProduccionBean extends EntidadBean {

	public abstract Long getPkCodigoAjusteproduccion();

	public abstract void setPkCodigoAjusteproduccion(Long pkCodigoAjusteproduccion);

	public abstract EstadoAjusteProduccionBean getEstadoajusteproduccion();

	public abstract void setEstadoajusteproduccion(EstadoAjusteProduccionBean estadoajusteproduccion);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioRegistra();

	public abstract void setUsuarioByFkCodigoUsuarioRegistra(UsuarioBean usuarioByFkCodigoUsuarioRegistra);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuario();

	public abstract void setUsuarioByFkCodigoUsuario(UsuarioBean usuarioByFkCodigoUsuario);

	public abstract PeriodoContableBean getPeriodocontable();

	public abstract void setPeriodocontable(PeriodoContableBean periodocontable);

	public abstract LineaNegocioBean getLineanegocio();

	/**
	 * @return the consumoEnviadoSap
	 */
	public abstract Boolean getConsumoEnviadoSap();

	/**
	 * @param consumoEnviadoSap the consumoEnviadoSap to set
	 */
	public abstract void setConsumoEnviadoSap(Boolean consumoEnviadoSap);

	/**
	 * @return the combutibleEnviadoSap
	 */
	public abstract Boolean getCombutibleEnviadoSap();

	/**
	 * @param combutibleEnviadoSap the combutibleEnviadoSap to set
	 */
	public abstract void setCombutibleEnviadoSap(Boolean combutibleEnviadoSap);

	public abstract void setLineanegocio(LineaNegocioBean lineanegocio);

	public abstract void setFechaAprobacionAjusteproduccion(String fechaAprobacionAjusteproduccion);

	public abstract String getFechaAprobacionAjusteproduccion();

}