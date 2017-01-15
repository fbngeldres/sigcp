package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PuestoTrabajoQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.UnresolvableObjectException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PuestoTrabajoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/** Logger Instance */
	protected static Logger loggerQ = Logger.getLogger(PuestoTrabajoQuerier.class);

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_PUESTO_TRABAJO = "nombrePuestotrabajo";
	private static final String CODIGO_SCC = "codSccPuestotrabajo";
	private static final String CODIGO_SAP = "codigoSapPuestotrabajo";
	private static final String SIGLAS = "siglasPuestotrabajo";
	private static final String TIPO_PUESTO_TRABAJO = "tipopuestotrabajo.pkCodigoTipopuestotrabajo";
	private static final String ESTADO_PUESTO_TRABAJO = "estadopuestotrabajo.pkCodigoEstadopuestotrabajo";
	private static final String CODIGO_CENTRO_COSTOS = "centroscostos.pkCodigoCentrocosto";
	private static final String CODIGO_UNIDAD_MEDIDA = "unidadmedida.pkCodigoUnidadMedida";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Metodo para obtener la lista de objectos Producto
	 * 
	 * @return
	 */
	public static List<Puestotrabajo> getAll() throws AplicacionException {

		return Querier.getAll(Puestotrabajo.class);
	}

	/**
	 * Metodo para obtener la lista de objectos de puestos de trabajo ordenados
	 * por nombre
	 * 
	 * @return
	 */
	public static List<Puestotrabajo> getAllOrderByNombre() throws AplicacionException {
		return getAllOrderBy(NOMBRE_PUESTO_TRABAJO);
	}

	/**
	 * Metodo para obtener la lista de objectos Producto, ordenados por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Puestotrabajo> getAllOrderBy(String order) throws AplicacionException {

		return Querier.getAll(Puestotrabajo.class, order);
	}

	/**
	 * Metodo para modificar un Puestotrabajo de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Puestotrabajo getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return (Puestotrabajo) Querier.getById(Puestotrabajo.class, codigo);
	}

	/**
	 * Mï¿½todo para obtener los Puestos de TRabajo de la BD por CodigoSCC.
	 * 
	 * @param CodigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByCodigoSCC(Long CodigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, CODIGO_SCC, CodigoSCC);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Mï¿½todo para obtener los Puestos de Trabajo de la BD por CodigoSAP.
	 * 
	 * @param CodigoSAP
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByCodigoSAP(String CodigoSAP) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, CODIGO_SAP, CodigoSAP);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener los Centros de Costos de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Puestotrabajo.class, NOMBRE_PUESTO_TRABAJO, nombre);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener los Puestos de Trabajo de la BD por siglas.
	 * 
	 * @param siglas
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findBySiglas(String siglas) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, SIGLAS, siglas);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener los Puestos de Trabajo de la BD por codigo del tipo
	 * de puesto de trabajo.
	 * 
	 * @param codTipoPuestoTrab
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByCodigoTipoPuestoTrabajo(Long codTipoPuestoTrab) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, TIPO_PUESTO_TRABAJO, codTipoPuestoTrab);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener los Puestos de Trabajo de la BD por nombre.
	 * 
	 * @param codEstadoPuestoTrab
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByCodigoEstadoPuestoTrabajo(Long codEstadoPuestoTrab) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, ESTADO_PUESTO_TRABAJO, codEstadoPuestoTrab);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener los Puestos de Trabajo de la BD por codigo del Centro
	 * de Costos.
	 * 
	 * @param codCentroCostos
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByCodigoCentroCostos(Long codCentroCostos) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, CODIGO_CENTRO_COSTOS, codCentroCostos);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener los Puestos de Trabajo de la BD por codigo de la
	 * unidad de medida.
	 * 
	 * @param codUnidadMedida
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByCodigoUnidadMedida(Long codUnidadMedida) throws AplicacionException {

		try {
			return Querier.findByProperty(Puestotrabajo.class, CODIGO_CENTRO_COSTOS, codUnidadMedida);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un Puesto de Trabajo en la BD.
	 * 
	 * @param puestoTrabajo
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Puestotrabajo puestoTrabajo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(puestoTrabajo);
	}

	/**
	 * Método para modificar un Puesto de Trabajo de la BD.
	 * 
	 * @param puestoTrabajo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Puestotrabajo puestoTrabajo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(puestoTrabajo);
	}

	/**
	 * Método para eliminar un Puesto de Trabajo de la BD.
	 * 
	 * @param puestoTrabajo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Puestotrabajo puestoTrabajo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(puestoTrabajo);
	}

	/**
	 * Método para consultar en base de datos los Puestos de trabajo asociados a
	 * un Proceso especifico
	 * 
	 * @param procesoCodigo
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByProcess(Long procesoCodigo) throws AplicacionException {

		String consulta = "select distinct op.puestotrabajo from Operacion op, Actividad a "
				+ "where op.actividad.pkCodigoActividad = a.pkCodigoActividad and " + "a.proceso.pkCodigoProceso = ?";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, procesoCodigo);
			return query.list();
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para consultar en base de datos los Puestos de trabajo asociados a
	 * una Lï¿½nea de Negocio especifica
	 * 
	 * @param procesoCodigo
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByBussinesLine(Long lineaCodigo) throws AplicacionException {

		List<Puestotrabajo> puestos = new ArrayList<Puestotrabajo>();

		String consulta = "select distinct op.puestotrabajo from Operacion op, Actividad a "
				+ "where op.actividad.pkCodigoActividad = a.pkCodigoActividad and "
				+ "a.proceso.lineanegocio.pkCodigoLineanegocio = ?";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, lineaCodigo);

			puestos = query.list();

			return puestos;

		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para consultar en base de datos los Puestos de trabajo asociados a
	 * una Unidad especifica
	 * 
	 * @param procesoCodigo
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByBussinesUnidad(Long codigoUnidad) throws AplicacionException {

		List<Puestotrabajo> puestos = new ArrayList<Puestotrabajo>();

		String consulta = "select distinct op.puestotrabajo from Operacion op, Actividad a "
				+ "where op.actividad.pkCodigoActividad = a.pkCodigoActividad and "
				+ "a.proceso.lineanegocio.unidad.pkCodigoUnidad = ? " + " ORDER BY op.puestotrabajo.nombrePuestotrabajo";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoUnidad);

			puestos = query.list();

			return puestos;

		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para consultar en base de datos los Puestos de trabajo asociados a
	 * un tablero de control especifico
	 * 
	 * @param codigoTableroControl
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> findByTableroControl(Long codigoTableroControl) throws AplicacionException {

		List<Puestotrabajo> puestos = new ArrayList<Puestotrabajo>();

		String consulta = "select distinct tp.puestotrabajo from Tableropuestotrabajo tp "
				+ "where tp.tablerocontrol.pkCodigoTablerocontrol = ?";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoTableroControl);

			puestos = query.list();

			return puestos;
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * @param codigoProceso
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Puestotrabajo> obtenerPuestosTrabajoPorCodigoProceso(Long codigoProceso) throws AplicacionException {

		List<Puestotrabajo> puestosTrabajo = new ArrayList<Puestotrabajo>();
		String consulta = "select distinct puestotrabajo "
				+ "from Puestotrabajo puestotrabajo,Tasarealproduccion tasarealproduccion,Produccion produccion,Proceso  proceso "
				+ "where proceso.pkCodigoProceso = produccion.proceso.pkCodigoProceso "
				+ "and produccion.pkProduccion = tasarealproduccion.produccion.pkProduccion "
				+ "and tasarealproduccion.puestotrabajo.pkCodigoPuestotrabajo = puestotrabajo.pkCodigoPuestotrabajo "
				+ "and proceso.pkCodigoProceso  = ?";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoProceso);

			puestosTrabajo = query.list();

			return puestosTrabajo;
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}
}