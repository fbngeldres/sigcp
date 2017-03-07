package pe.com.pacasmayo.sgcp.logica.stock;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoRegistroMedicionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoregistromedicion;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoRegistroMedicionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoRegistroMedicionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		EstadoRegistroMedicionLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	public List<Estadoregistromedicion> obtenerEstadosRegistroMedicionDataObjects() throws LogicaException {

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
			return EstadoRegistroMedicionQuerier.getAll();
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadoregistromedicion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public Estadoregistromedicion obtenerEstadoRegistroMedicionDataObject(Long codigoEstado) throws LogicaException {

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

		Estadoregistromedicion estado = null;

		try {
			estado = EstadoRegistroMedicionQuerier.getById(codigoEstado);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return estado;
	}
}