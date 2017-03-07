package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.CubicacionProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: CubicacionProductoServiceAsync.java
 * Modificado: Feb 21, 2011 3:38:57 PM 
 * Autor: daniel.franklin
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface CubicacionProductoServiceAsync {

	/**
	 * Modifica la lista de cubicaciones de la Cubicaci�n Producto espec�ficada.
	 */
	public void modificarCubicacion(Long codigoCubicacionProducto, List<TablaCubicacionDTO> cubicaciones,
			AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Consulta de cubicaci�n dado un Id
	 */
	public void consultarCubicacion(Long codigoCubicacion, AsyncCallback<CubicacionProductoDTO> callback)
			throws IllegalArgumentException;
}