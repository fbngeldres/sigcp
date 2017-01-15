package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadMedidaQuerier.java
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
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class UnidadMedidaQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	public static final String NOMBRE_UNIDAD_MEDIDA = "nombreUnidadmedida";
	public static final Object TM = "TM";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Unidadmedida
	 * 
	 * @return
	 */
	public static List<Unidadmedida> getAll() {

		return Querier.getAll(Unidadmedida.class);
	}

	/**
	 * Método para obtener todas las unidades de medida, ordenados por el nombre
	 * 
	 * @param order
	 * @return Lista de Objetos de Producto
	 */
	public static List<Unidadmedida> getAllOrderByNombre() throws HibernateException {

		return getAllOrderBy(NOMBRE_UNIDAD_MEDIDA);
	}

	/**
	 * Método para obtener todos Productos, ordenados por un atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de Producto
	 */
	public static List<Unidadmedida> getAllOrderBy(String order) throws HibernateException {

		return getAll(Unidadmedida.class, order);
	}

	/**
	 * Método para obtener una Unidad de Medida de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Unidadmedida getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Unidadmedida.class, codigo);
	}

	/**
	 * Método para obtener una Unidad de Medida de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Unidadmedida getByNombre(String nombre) throws ElementoNoEncontradoException, EntornoEjecucionException,
			SesionVencidaException {
		List<Unidadmedida> listaUnidad = Querier.findByProperty(Unidadmedida.class, "nombreUnidadmedida", nombre);

		if (listaUnidad == null || listaUnidad.isEmpty()) {
			throw new ElementoNoEncontradoException("La Unidad de medida de nombre: " + nombre
					+ " no se encuentra en la base de datos");
		}

		return listaUnidad.get(0);
	}

	public static Unidadmedida getByPuestoTrabajo(Long codigoPuestoTrabajo) throws AplicacionException {

		try {
			String queryString = "SELECT um FROM Unidadmedida um, Puestotrabajo pt "
					+ "where um.pkCodigoUnidadMedida = pt.unidadmedida.pkCodigoUnidadMedida"
					+ " and pt.pkCodigoPuestotrabajo = ?";

			Query queryObject = Querier.query(queryString);// getSession().createQuery(queryString);
			queryObject.setParameter(0, codigoPuestoTrabajo);

			return (Unidadmedida) queryObject.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Unidadmedida getByProducto(Long codigoProducto) throws AplicacionException {

		try {
			String queryString = "SELECT um FROM Unidadmedida um, Producto p "
					+ "where um.pkCodigoUnidadMedida = p.unidadmedida.pkCodigoUnidadMedida" + " and p.pkCodigoProducto = ?";

			Query queryObject = Querier.query(queryString);
			queryObject.setParameter(0, codigoProducto);

			return (Unidadmedida) queryObject.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

}
