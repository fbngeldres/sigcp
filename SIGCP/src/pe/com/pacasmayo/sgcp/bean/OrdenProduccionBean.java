package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionBean.java
 * Modificado: Oct 27, 2010 5:02:12 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OrdenProduccionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract EstadoOrdenProduccionBean getEstadoOrdenProduccion();

	public abstract void setEstadoOrdenProduccion(EstadoOrdenProduccionBean estadoOrdenProduccion);

	public abstract UsuarioBean getUsuarioRegistro();

	public abstract void setUsuarioRegistro(UsuarioBean usuarioRegistro);

	public abstract UsuarioBean getUsuarioAprueba();

	public abstract void setUsuarioAprueba(UsuarioBean usuarioAprueba);

	public abstract ProduccionBean getProduccion();

	public abstract void setProduccion(ProduccionBean produccion);

	public abstract HojaRutaBean getHojaRuta();

	public abstract void setHojaRuta(HojaRutaBean hojaRuta);

	public abstract boolean isEsManual();

	public abstract void setEsManual(boolean esManual);

	public abstract int getMes();

	public abstract void setMes(int mes);

	public abstract String getNumeroOrden();

	public abstract void setNumeroOrden(String numeroOrden);

	public abstract String getNumeroDocumento();

	public abstract void setNumeroDocumento(String numeroDocumento);

	public abstract Double getProduccionEstimada();

	public abstract void setProduccionEstimada(Double produccionEstimada);

	public abstract Double getProduccionEjecutada();

	public abstract void setProduccionEjecutada(Double produccionEjecutada);

	public abstract Date getFechaAprobacion();

	public abstract void setFechaAprobacion(Date fechaAprobacion);

	public abstract Date getFechaRegistro();

	public abstract void setFechaRegistro(Date fechaRegistro);

	public abstract List<OperacionBean> getListaOperacionOrdenProduccionBean();

	public abstract void setListaOperacionOrdenProduccionBean(List<OperacionBean> listaOperacionOrdenProduccionBean);

	public abstract List<ComponenteRegistroOrdenBean> getListaComponenteOrdenProduccion();

	public abstract void setListaComponenteOrdenProduccion(List<ComponenteRegistroOrdenBean> listaComponenteOrdenProduccion);

	public abstract List<RecursoBean> getListaRecursoOrdenProduccion();

	public abstract void setListaRecursoOrdenProduccion(List<RecursoBean> listaRecursoOrdenProduccion);

}
