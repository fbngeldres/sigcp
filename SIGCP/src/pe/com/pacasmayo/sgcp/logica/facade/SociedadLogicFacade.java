package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SociedadLogicFacade.java
 * Modificado: Jan 25, 2010 8:34:06 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface SociedadLogicFacade {

	/**
	 * @param codigoSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract SociedadBean obtenerSociedad(Long codigoSociedad) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<SociedadBean> obtenerSociedades() throws LogicaException;

	/**
	 * @param codigoDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Sociedad> obtenerSociedadesDataObjects(Long codigoDivision) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<SociedadBean> obtenerSociedadesPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param codigoDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<SociedadBean> obtenerSociedadesPorCodigoDivision(Long codigoDivision) throws LogicaException;

	/**
	 * @param nombreSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<SociedadBean> obtenerSociedadesPorNombre(String nombreSociedad) throws LogicaException;

	/**
	 * @param nombre
	 * @param codigoDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract SociedadBean consultarPorNombreYDivision(String nombre, Long codigoDivision) throws LogicaException;

	/**
	 * @param siglas
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<SociedadBean> obtenerSociedadesPorSiglas(String siglas) throws LogicaException;

	/**
	 * @param codigoSAP
	 * @return
	 * @throws LogicaException
	 */
	public abstract SociedadBean obtenerSociedadPorCodigoSAP(String codigoSAP) throws LogicaException;

	/**
	 * @param sociedadBean
	 * @throws LogicaException
	 */
	public abstract void insertarSociedad(SociedadBean sociedadBean) throws LogicaException;

	/**
	 * @param sociedadBean
	 * @throws LogicaException
	 */
	public abstract void actualizarSociedad(SociedadBean sociedadBean) throws LogicaException;

	/**
	 * @param sociedadBean
	 * @throws LogicaException
	 */
	public abstract void eliminarSociedad(SociedadBean sociedadBean) throws LogicaException;

}
