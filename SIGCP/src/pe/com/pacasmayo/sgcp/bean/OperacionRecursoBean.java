package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionRecursoBea.java
 * Modificado: May 10, 2010 12:51:52 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OperacionRecursoBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract RecursoBean getRecurso();

	public abstract void setRecurso(RecursoBean recurso);

	public abstract OperacionBean getOperacion();

	public abstract void setOperacion(OperacionBean operacion);

}
