package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: PlanAnualBean.java
 * Modificado: Oct 13, 2011 9:38:12 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlanAnualBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getAnno()
	 */
	public abstract int getAnno();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setAnno(int)
	 */
	public abstract void setAnno(int anno);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setCodigo(java.lang.Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getComentario()
	 */
	public abstract String getComentario();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setComentario(java.lang
	 * .String)
	 */
	public abstract void setComentario(String comentario);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getMesCorteVersion()
	 */
	public abstract Short getMesCorteVersion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setMesCorteVersion(java
	 * .lang.Short)
	 */
	public abstract void setMesCorteVersion(Short mesCorteVersion);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getNecesidadComercial()
	 */
	public abstract Double getNecesidadComercial();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setNecesidadComercial(java
	 * .lang.Double)
	 */
	public abstract void setNecesidadComercial(Double necesidadComercial);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getProduccionAnual()
	 */
	public abstract Double getProduccionAnual();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setProduccionAnual(java
	 * .lang.Double)
	 */
	public abstract void setProduccionAnual(Double produccionAnual);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getVersion()
	 */
	public abstract String getVersion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setVersion(java.lang.String
	 * )
	 */
	public abstract void setVersion(String version);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getLineaNegocio()
	 */
	public abstract LineaNegocioBean getLineaNegocio();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setLineaNegocio(pe.com.
	 * pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public abstract void setLineaNegocio(LineaNegocioBean lineaNegocio);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getOrdenProduccion()
	 */
	public abstract OrdenProduccionBean getOrdenProduccion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setOrdenProduccion(pe.com
	 * .pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	public abstract void setOrdenProduccion(OrdenProduccionBean ordenProduccion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanParada()
	 */
	public abstract PlanParadaBean getPlanParada();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanParada(pe.com.pacasmayo
	 * .sgcp.bean.PlanParadaBean)
	 */
	public abstract void setPlanParada(PlanParadaBean planParada);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanNecesidad()
	 */
	public abstract PlanNecesidadBean getPlanNecesidad();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanNecesidad(pe.com
	 * .pacasmayo.sgcp.bean.PlanNecesidadBean)
	 */
	public abstract void setPlanNecesidad(PlanNecesidadBean planNecesidad);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getEstadoPlan()
	 */
	public abstract EstadoPlanBean getEstadoPlan();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setEstadoPlan(pe.com.pacasmayo
	 * .sgcp.bean.EstadoPlanBean)
	 */
	public abstract void setEstadoPlan(EstadoPlanBean estadoPlan);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistra()
	 */
	public abstract UsuarioBean getUsuarioRegistra();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioRegistra(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public abstract void setUsuarioRegistra(UsuarioBean usuarioRegistra);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioAprueba()
	 */
	public abstract UsuarioBean getUsuarioAprueba();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioAprueba(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public abstract void setUsuarioAprueba(UsuarioBean usuarioAprueba);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaPlanComercializacion
	 * ()
	 */
	public abstract List<PlanComercializacionBean> getListaPlanComercializacion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaPlanComercializacion
	 * (java.util.List)
	 */
	public abstract void setListaPlanComercializacion(List<PlanComercializacionBean> listaPlanComercializacion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaAprueba()
	 */
	public abstract String getFechaAprueba();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaAprueba(java.lang
	 * .String)
	 */
	public abstract void setFechaAprueba(String fechaAprueba);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaRegistra()
	 */
	public abstract String getFechaRegistra();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaRegistra(java.lang
	 * .String)
	 */
	public abstract void setFechaRegistra(String fechaRegistra);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaCapacidadOperRegMen
	 * ()
	 */
	public abstract List<CapacidadOperativaRegistroMensualBean> getListaCapacidadOperRegMen();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaCapacidadOperRegMen
	 * (java.util.List)
	 */
	public abstract void setListaCapacidadOperRegMen(List<CapacidadOperativaRegistroMensualBean> listaCapacidadOperRegMen);

	public abstract void setUsuarioApruebaConFecha(String usuarioApruebaConFecha);

	public abstract void setUsuarioRegistraConFecha(String usuarioRegistraConFecha);

	public abstract String getUsuarioApruebaConFecha();

	public abstract String getUsuarioRegistraConFecha();

}
