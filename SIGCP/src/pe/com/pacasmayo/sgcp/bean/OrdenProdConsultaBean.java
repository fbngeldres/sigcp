package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: OrdenProdConsultaBean.java
 * Modificado: Oct 25, 2010 4:18:52 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OrdenProdConsultaBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setCodigo(int)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#isEsManual()
	 */
	public abstract boolean isEsManual();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setEsManual(boolean)
	 */
	public abstract void setEsManual(boolean esManual);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getFechaAprobacion()
	 */
	public abstract Date getFechaAprobacion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setFechaAprobacion
	 * (java.util.Date)
	 */
	public abstract void setFechaAprobacion(Date fechaAprobacion);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getFechaRegistro()
	 */
	public abstract Date getFechaRegistro();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setFechaRegistro(
	 * java.util.Date)
	 */
	public abstract void setFechaRegistro(Date fechaRegistro);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getMes()
	 */
	public abstract int getMes();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setMes(java.lang.
	 * String)
	 */
	public abstract void setMes(int mes);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getNumeroDocumento()
	 */
	public abstract String getNumeroDocumento();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setNumeroDocumento
	 * (int)
	 */
	public abstract void setNumeroDocumento(String numeroDocumento);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getNumeroOrden()
	 */
	public abstract String getNumeroOrden();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setNumeroOrden(java
	 * .lang.String)
	 */
	public abstract void setNumeroOrden(String numeroOrden);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccionEjecutada
	 * ()
	 */
	public abstract Double getProduccionEjecutada();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccionEjecutada
	 * (int)
	 */
	public abstract void setProduccionEjecutada(Double produccionEjecutada);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccionEstimada
	 * ()
	 */
	public abstract Double getProduccionEstimada();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccionEstimada
	 * (double)
	 */
	public abstract void setProduccionEstimada(Double produccionEstimada);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProceso()
	 */
	public abstract String getProceso();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProceso(pe.com
	 * .pacasmayo.sgcp.bean.ProcesoBean)
	 */
	public abstract void setProceso(String proceso);

	public abstract EstadoOrdenProduccionBean getEstadoOrdenProduccion();

	public abstract HojaRutaBean getHojaRuta();

	public abstract ProduccionBean getProduccion();

	public abstract UsuarioBean getUsuarioAprueba();

	public abstract UsuarioBean getUsuarioRegistro();

	public abstract void setEstadoOrdenProduccion(EstadoOrdenProduccionBean estadoOrdenProduccion);

	public abstract void setHojaRuta(HojaRutaBean hojaRuta);

	public abstract void setProduccion(ProduccionBean produccion);

	public abstract void setUsuarioAprueba(UsuarioBean usuarioAprueba);

	public abstract void setUsuarioRegistro(UsuarioBean usuarioRegistro);

	/**
	 * @return the a�o_mes
	 */
	public abstract String getAnio_mes();

	/**
	 * @param a�o_mes the a�o_mes to set
	 */
	public abstract void setAnio_mes(String anio_mes);

	/**
	 * @return the unidad
	 */
	public abstract String getUnidad();

	/**
	 * @param unidad the unidad to set
	 */
	public abstract void setUnidad(String unidad);

	/**
	 * @return the lineaneg
	 */
	public abstract String getLineaneg();

	/**
	 * @param lineaneg the lineaneg to set
	 */
	public abstract void setLineaneg(String lineaneg);

	/**
	 * @return the producto
	 */
	public abstract String getProducto();

	/**
	 * @param producto the producto to set
	 */
	public abstract void setProducto(String producto);

	/**
	 * @return the porcentaje
	 */
	public abstract Double getPorcentaje();

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public abstract void setPorcentaje(Double porcentaje);

	/**
	 * @return the division
	 */
	public abstract String getDivision();

	/**
	 * @param division the division to set
	 */
	public abstract void setDivision(String division);

	/**
	 * @return the sociedad
	 */
	public abstract String getSociedad();

	/**
	 * @param sociedad the sociedad to set
	 */
	public abstract void setSociedad(String sociedad);

	/**
	 * @return the anio
	 */
	public abstract String getAnio();

	/**
	 * @param anio the anio to set
	 */
	public abstract void setAnio(String anio);

	/**
	 * @return the usuarioR
	 */
	public abstract String getUsuarioR();

	/**
	 * @param usuarioR the usuarioR to set
	 */
	public abstract void setUsuarioR(String usuarioR);

	/**
	 * @return the usuarioA
	 */
	public abstract String getUsuarioA();

	/**
	 * @param usuarioA the usuarioA to set
	 */
	public abstract void setUsuarioA(String usuarioA);

	/**
	 * @return the tipo
	 */
	public abstract char getTipo();

	/**
	 * @param tipo the tipo to set
	 */
	public abstract void setTipo(char tipo);

	public abstract Long getCodigoProducto();

	public abstract void setCodigoProducto(Long codigoProducto);

}
