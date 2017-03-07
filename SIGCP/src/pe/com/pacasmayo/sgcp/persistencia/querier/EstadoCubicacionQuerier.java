package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoCubicacionQuerier.java
 * Modificado: Jun 8, 2010 9:40:09 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadoCubicacionQuerier extends Querier implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio {

	private static final String NOMBRE_CLASE_ESTADO_CUBICACION = Estadocubicacion.class.getSimpleName();
	private static final String ATRB_NOMBE_ESTADO_CUBICACION = "nombreEstadocubicacion";

	private static final String ESTADO_CUBICACION_APROBADO = ManejadorPropiedades
			.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);

	private static final String ESTADO_CUBICACION_ANULADO = ManejadorPropiedades
			.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_ANULADO);

	private static final String ESTADO_CUBICACION_GENERADO = ManejadorPropiedades
			.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_GENERADO);

	/**
	 * Metodo para obtener la lista de objectos Lineanegocio
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Estadocubicacion> getAll() throws AplicacionException {

		return Querier.getAll(Estadocubicacion.class);
	}

	/**
	 * Metodo para obtener la lista de objectos Lineanegocio
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Estadocubicacion> getAllByNombre() {
		return Querier.getAllOrderByAscendent(Estadocubicacion.class, ATRB_NOMBE_ESTADO_CUBICACION);
	}

	/**
	 * Mï¿½todo para obtener una Usuario de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadocubicacion getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Estadocubicacion.class, codigo);
	}

	private static Estadocubicacion obtenerPorNombre(String nombre) throws AplicacionException {
		final String parametroNombreStr = "nombre";
		String consulta = "FROM {0} AS edoCub WHERE edoCub.{1} = :{2}";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_ESTADO_CUBICACION, ATRB_NOMBE_ESTADO_CUBICACION,
				parametroNombreStr });

		try {
			Query query = Querier.query(consulta);
			query.setString(parametroNombreStr, nombre);

			return (Estadocubicacion) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Estadocubicacion obtenerEstadoAprobado() throws AplicacionException {
		return obtenerPorNombre(ESTADO_CUBICACION_APROBADO);
	}

	public static Estadocubicacion obtenerEstadoAnulado() throws AplicacionException {
		return obtenerPorNombre(ESTADO_CUBICACION_ANULADO);
	}

	public static Estadocubicacion obtenerEstadoGenerado() throws AplicacionException {
		return obtenerPorNombre(ESTADO_CUBICACION_GENERADO);
	}

}
