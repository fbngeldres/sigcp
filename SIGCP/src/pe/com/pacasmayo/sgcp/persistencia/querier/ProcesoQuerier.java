package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanAnualQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
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
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ProcesoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_PROCESO = "nombreProceso";
	private static final String CODIGO_SCC_PROCESO = "codigoSccProceso";
	private static final String CODIGO_LINEA_NEGOCIO = "lineanegocio.pkCodigoLineanegocio";
	public static final String ORDEN_EJECUCION = "ordenEjecucionProceso";
	private static final String CODIGO_TIPO_PRODUCTO = "tipoproducto.pkCodigoTipoproducto";
	private static final String CODIGO_UNIDAD = "lineanegocio.unidad.pkCodigoUnidad";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Proceso
	 * 
	 * @return
	 */
	public static List getAll() throws AplicacionException {

		return Querier.getAll(Proceso.class);
	}

	/**
	 * Método para obtener la lista de objectos Proceso, ordenados por un
	 * atributo
	 * 
	 * @return
	 */
	public static List getAllOrderBy(String order) throws AplicacionException {

		return Querier.getAll(Proceso.class, order);
	}

	/**
	 * Método para obtener un Proceso de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Proceso getById(Long codigo) throws ElementoNoEncontradoException {

		return (Proceso) Querier.getById(Proceso.class, codigo);
	}

	/**
	 * Método para obtener los Procesos de la BD por el codigo SCC.
	 * 
	 * @param codigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Proceso> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Proceso.class, CODIGO_SCC_PROCESO, codigoSCC);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Procesos de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Proceso> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Proceso.class, NOMBRE_PROCESO, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Procesos de la BD por orden de ejecución.
	 * 
	 * @param orden
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Proceso> findByOrdenEjecucion(Short orden) throws AplicacionException {

		try {
			return Querier.findByProperty(Proceso.class, ORDEN_EJECUCION, orden);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener ima lista de Proceso, por medio del código la linea
	 * de negocio
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Proceso> findByCodigoLineaNegocio(Long codigoLineaNegocio) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Proceso.class, CODIGO_LINEA_NEGOCIO, codigoLineaNegocio, NOMBRE_PROCESO);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener ima lista de Proceso, por medio del código del Tipo
	 * de Producto de negocio
	 * 
	 * @param codigoTipoProducto
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Proceso> findByCodigoTipoProducto(Long codigoTipoProducto) throws AplicacionException {

		try {
			return Querier.findByProperty(Proceso.class, CODIGO_TIPO_PRODUCTO, codigoTipoProducto);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener la lista de objectos Proceso, con un Orden en una
	 * Lína de Negocio
	 * 
	 * @param orden
	 * @param codigoLineaNegocio
	 * @return Lista de Objetos de Proceso
	 */
	public static List<Proceso> getProcesosPorOrdenLineaNegocio(short orden, Long codigoLineaNegocio) throws AplicacionException {

		String consulta = "from Proceso proc Where proc.lineanegocio.pkCodigoLineanegocio = ? and proc.ordenEjecucionProceso = ?";

		Query query = Querier.query(consulta);
		query.setLong(0, codigoLineaNegocio);
		query.setShort(1, orden);
		return (List<Proceso>) query.list();
	}

	/**
	 * Metodo para consultar el proceso segun el codigo de linea de negocio y el
	 * codigo de producto
	 * 
	 * @param codigoLinea
	 * @param codigoProducto
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Proceso obtenerProcesoPorcodigoLineaYCodigoProducto(Long codigoLinea, Long codigoProducto)
			throws ElementoNoEncontradoException, SesionVencidaException, EntornoEjecucionException, AplicacionException {

		Proceso proceso = null;

		try {

			String consulta = "Select proceso from Proceso as proceso, Produccion as produccion, Producto  as producto"
					+ " where proceso.lineanegocio.pkCodigoLineanegocio   = ?"
					+ " and produccion.proceso = proceso.pkCodigoProceso "
					+ " and produccion.producto.pkCodigoProducto = producto.pkCodigoProducto"
					+ " and producto.pkCodigoProducto = ? ";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoLinea);
			query.setLong(1, codigoProducto);
			proceso = (Proceso) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(ERROR_HIBERNATE, hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

		return proceso;
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un Proceso en la BD.
	 * 
	 * @param proceso
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Proceso proceso) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(proceso);
	}

	/**
	 * Método para modificar un Proceso en la BD.
	 * 
	 * @param proceso
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Proceso proceso) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(proceso);
	}

	/**
	 * Método para eliminar un Proceso en la BD.
	 * 
	 * @param proceso
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Proceso proceso) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(proceso);
	}

	/**
	 * Método para obtener ima lista de Proceso, por medio del código la linea
	 * de negocio
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Proceso> findByCodigoUnidad(Long codigoUnidad) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Proceso.class, CODIGO_UNIDAD, codigoUnidad, NOMBRE_PROCESO);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}
}
