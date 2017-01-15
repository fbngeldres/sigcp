package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoProductoQuerier.java
 * Modificado: Jan 9, 2010 10:38:51 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipoproducto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TipoProductoQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static String NOMBRE_TIPO_PRODUCTO = "nombreTipodocumentomaterial";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Tipoproducto
	 * 
	 * @return
	 */
	public static List<Tipoproducto> getAll() throws AplicacionException {
		List<Tipoproducto> listaTipoProducto = null;

		try {
			listaTipoProducto = getAll(Tipoproducto.class);
		} catch (HibernateException e) {
			throw new AplicacionException(e);
		}

		return listaTipoProducto;
	}

	/**
	 * Método para obtener un Tipoproducto de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipoproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipoproducto.class, codigo);
	}

	/**
	 * Método para obtener un tipo de documento material de la BD por el nombre.
	 * 
	 * @param nombre
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static Tipoproducto findByNombreUniqueResult(String nombre) throws AplicacionException {

		try {
			return Querier.findByPropertyUniqueResult(Tipoproducto.class, "nombreTipoproducto", nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}
}
