package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionRecursoBeanImpl.java
 * Modificado: May 10, 2010 12:48:52 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OperacionRecursoBean;
import pe.com.pacasmayo.sgcp.bean.RecursoBean;

public class OperacionRecursoBeanImpl implements OperacionRecursoBean {

	private Long codigo;
	private RecursoBean recurso;
	private OperacionBean operacion;

	public OperacionRecursoBeanImpl() {
		recurso = new RecursoBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionRecursoBea#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionRecursoBea#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionRecursoBea#getRecurso()
	 */
	public RecursoBean getRecurso() {
		return recurso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionRecursoBea#setRecurso(pe.com
	 * .pacasmayo.sgcp.bean.RecursoBean)
	 */
	public void setRecurso(RecursoBean recurso) {
		this.recurso = recurso;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionRecursoBea#getOperacion()
	 */
	public OperacionBean getOperacion() {
		return operacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionRecursoBea#setOperacion(pe.com
	 * .pacasmayo.sgcp.bean.OperacionBean)
	 */
	public void setOperacion(OperacionBean operacion) {
		this.operacion = operacion;
	}
}
