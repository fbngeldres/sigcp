package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: UnidadMedidaLogic.java
 * Modificado: Jan 9, 2010 11:04:06 AM
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadMedidaLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadMedidaQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class UnidadMedidaLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, UnidadMedidaLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public UnidadMedidaLogic() {

		UnidadMedidaLogic.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadMedidaLogicFacade#
	 * obtenerUnidadMedidas()
	 */
	public List<UnidadMedidaBean> obtenerUnidadMedidas() throws LogicaException {

		String mensajeError = "";

		try {
			return beanFactory.transformarListaUnidadMedida(UnidadMedidaQuerier.getAll());
		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

	}

	public List<UnidadMedidaBean> obtenerUnidadMedidasOrdenAlfabetico() throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {
			return beanFactory.transformarListaUnidadMedida(UnidadMedidaQuerier.getAllOrderByNombre());
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadMedidaLogicFacade#
	 * obtenerUnidadMedidaPorNombre(java.lang.String)
	 */
	public UnidadMedidaBean obtenerUnidadMedidaPorNombre(String nombre) throws LogicaException {

		UnidadMedidaBean unidadMedidaBean = null;
		String mensajeError = "";

		try {
			unidadMedidaBean = beanFactory.transformarUnidadMedida(UnidadMedidaQuerier.getByNombre(nombre));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e);
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidadmedida.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

		return unidadMedidaBean;
	}

	public UnidadMedidaBean obtenerUnidadMedidaPorCodigo(Long codigoUnidadMedida) throws LogicaException {
		UnidadMedidaBean unidadMedidaBean = null;
		String mensajeError = "";

		try {
			unidadMedidaBean = beanFactory.transformarUnidadMedida(UnidadMedidaQuerier.getById(codigoUnidadMedida));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidadmedida.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

		return unidadMedidaBean;
	}

	public UnidadMedidaBean obtenerUnidadMedidaPorPuestoTrabajo(Long codigoPuestoTrabajo) throws LogicaException {
		UnidadMedidaBean unidadMedidaBean = null;
		String mensajeError = "";

		try {
			unidadMedidaBean = beanFactory.transformarUnidadMedida(UnidadMedidaQuerier.getByPuestoTrabajo(codigoPuestoTrabajo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidadmedida.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

		return unidadMedidaBean;
	}

}
