package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.TableroControlBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TableroControlBeanImpl.java
 * Modificado: Jun 23, 2010 2:38:02 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class TableroControlBeanImpl implements TableroControlBean {
	private Long codigo;
	private String nombre;
	private String descripcion;
	private UnidadBean unidad;

	public TableroControlBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#setNombre(java.lang
	 * .String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#getDescripcion()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#setDescripcion(java
	 * .lang.String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#getUnidad()
	 */
	public UnidadBean getUnidad() {
		return unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TableroControlBean#setUnidad(pe.com.pacasmayo
	 * .sgcp.bean.UnidadBean)
	 */
	public void setUnidad(UnidadBean unidad) {
		this.unidad = unidad;
	}

}
