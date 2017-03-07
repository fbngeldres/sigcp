package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.TipoMedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMedioAlmacenamientoLogicFacade.java
 * Modificado: Mar 22, 2010 3:16:37 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TipoMedioAlmacenamientoLogicFacade {

	/**
	 * @param codigotipoMedAlm
	 * @return
	 * @throws LogicaException
	 */
	public abstract TipoMedioAlmacenamientoBean obtenerTipoMedioAlmacenamiento(Long codigotipoMedAlm) throws LogicaException;

	/**
	 * @param codigoTipoMedio
	 * @return
	 * @throws LogicaException
	 */
	public abstract Tipomedioalmacenamiento obtenerTipoMedioAlmacenamientoDataObject(Long codigoTipoMedio) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TipoMedioAlmacenamientoBean> obtenerTipoMedioAlmacenamientos() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Tipomedioalmacenamiento> obtenerTiposMedioAlmacenamientoDataObject() throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TipoMedioAlmacenamientoBean> obtenerTipoMedioAlmacenamientoPorNombre(String nombre)
			throws LogicaException;

}
