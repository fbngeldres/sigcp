package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlantillaProductoDTO.java
 * Modificado: May 25, 2011 11:59:54 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class PlantillaProductoDTO implements java.io.Serializable {

	private static final long serialVersionUID = -5766740805840623875L;

	private Long pkCodigoPlantillaProducto;
	private String nombrePlantillaProducto;

	public Long getPkCodigoPlantillaProducto() {
		return pkCodigoPlantillaProducto;
	}

	public void setPkCodigoPlantillaProducto(Long pkCodigoPlantillaProducto) {
		this.pkCodigoPlantillaProducto = pkCodigoPlantillaProducto;
	}

	public String getNombrePlantillaProducto() {
		return nombrePlantillaProducto;
	}

	public void setNombrePlantillaProducto(String nombrePlantillaProducto) {
		this.nombrePlantillaProducto = nombrePlantillaProducto;
	}
}
