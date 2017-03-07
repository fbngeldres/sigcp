package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaStockDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedicionPorProductoServiceAsync.java
 * Modificado: Apr 21, 2010 3:38:57 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface StockServiceAsync {

	public void obtenerListaRegistroStock(Long codigoProceso, Long codigoEstadoRegistroMedicion, Long codigoTipoMedio,
			Integer anno, String mes, AsyncCallback<List<RegistroTablaStockDTO>> callback) throws IllegalArgumentException;

	public void obtenerListaDetallesSilos(Long codigoProceso, Long codigoTipoMedio, String fecha, Long codigoEstado,
			Long lineaNegocio, AsyncCallback<List<RegistroTablaMedicionDiaDTO>> callback);

	public void obtenerListaRegistroDetallesStock(Long codigoRegistroMedicion,
			AsyncCallback<List<RegistroTablaMedicionDiaDTO>> callback) throws IllegalArgumentException;

	public void cambiarEstadoRegistroMedicion(Long codigoRegistroMedicion, Long codigoEstado, AsyncCallback<String> callback);

	public void registrarMedicion(Long codigoProceso, Long codigoEstado, Long codigoTipoMedio,
			List<RegistroTablaMedicionDiaDTO> listaRegistros, String fecha, AsyncCallback<String> callback);

	public void obtenerCantidadAlmacenadaFormula(Long codigoSilo, List<Double> alturas, AsyncCallback<Double> callback);
}