package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionRegistroMensualBean.java
 * Modificado: Jan 11, 2010 10:36:40 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface FactorDosificacionRegistroMensualBean extends EntidadBean {

	public abstract FactorDosificacionBean getFactorDosificacionBean();

	public abstract void setFactorDosificacionBean(FactorDosificacionBean factorDosificacionBean);

	public abstract Short getMes();

	public abstract void setMes(Short mes);

	public abstract Integer getAnno();

	public abstract void setAnno(Integer anno);

	public abstract double getCantidadRegistro();

	public abstract void setCantidadRegistro(double cantidadRegistro);

}