package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMedioAlmacenamientoBean.java
 * Modificado: Feb 22, 2010 10:51:19 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

public interface TipoMedioAlmacenamientoBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract UnidadMedidaBean getUnidada();

	public abstract void setUnidada(UnidadMedidaBean unidada);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	public abstract String getDescripcion();

	public abstract void setDescripcion(String descripcion);

	public abstract Set<?> getMediosAlmacenamiento();

	public abstract void setMediosAlmacenamiento(Set<?> mediosAlmacenamiento);

	public abstract Set<?> getRegistroMedicioness();

	public abstract void setRegistroMedicioness(Set<?> registroMedicioness);

}