package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.CubicacionProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: CubicacionProductoService.java
 * Modificado: Feb 21, 2011 3:38:57 PM 
 * Autor: daniel.franklin
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
@RemoteServiceRelativePath("servicioCubicacion")
public interface CubicacionProductoService extends RemoteService {

	/**
	 * Modifica la lista de cubicaciones de la Cubicaci�n Producto espec�ficada.
	 */
	public String modificarCubicacion(Long codigoCubicacionProducto, List<TablaCubicacionDTO> cubicaciones)
			throws ServicioGWTGlobalException;

	/**
	 * Consulta de cubicaci�n dado un Id
	 */
	public CubicacionProductoDTO consultarCubicacion(Long codigoCubicacion) throws ServicioGWTGlobalException;
}