package pe.com.pacasmayo.sgcp.logica.notificacion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.TableroControlBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.TableroControlLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablerocontrol;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.TableroControlQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TableroControlDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class TableroControlLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		TableroControlLogicFacade {
	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	private static String mensajeError = "";

	public TableroControlLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogicFacade#
	 * obtenerTablerosControl()
	 */
	public List<TableroControlBean> obtenerTablerosControl() throws LogicaException {

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
			return beanFactory.transformarListaTableroControl(TableroControlQuerier.getAll());
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tablerocontrol.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogicFacade#
	 * obtenerTablerosControlPorUnidad(java.lang.Long)
	 */
	public List<TableroControlBean> obtenerTablerosControlPorUnidad(Long codigoUnidad) throws LogicaException {

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
			return beanFactory.transformarListaTableroControl(TableroControlQuerier.findByCodigoUnidad(codigoUnidad));
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tablerocontrol.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogicFacade#
	 * obtenerTablerosControlDTOPorUnidad(java.lang.Long)
	 */
	public List<Tablerocontrol> obtenerTablerosControlDTOPorUnidad(Long codigoUnidad) throws LogicaException {

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
			return TableroControlQuerier.findByCodigoUnidad(codigoUnidad);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tablerocontrol.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogicFacade#
	 * obtenerTablerosControlDO(java.lang.Long, java.lang.Long)
	 */
	private List<Tablerocontrol> obtenerTablerosControlDO(Long codigoPuestoTrabajo, Long codigoUnidad) throws LogicaException {

		try {
			return TableroControlQuerier.findByPuestoTrabajoAndUnidad(codigoPuestoTrabajo, codigoUnidad);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Columnareporte.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogicFacade#
	 * obtenerTablerosControlDTO(java.lang.Long, java.lang.Long)
	 */
	public List<TableroControlDTO> obtenerTablerosControlDTO(Long codigoPuestoTrabajo, Long codigoUnidad) throws LogicaException {

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
		List<TableroControlDTO> tablerosDTO = new ArrayList<TableroControlDTO>();
		try {

			List<Tablerocontrol> tableros = obtenerTablerosControlDO(codigoPuestoTrabajo, codigoUnidad);

			for (Tablerocontrol tablerocontrol : tableros) {
				TableroControlDTO tableroControlDTO = new TableroControlDTO();

				tableroControlDTO.setPkCodigoTableroControl(tablerocontrol.getPkCodigoTablerocontrol());
				tableroControlDTO.setNombreTableroControl(tablerocontrol.getNombreTablerocontrol());
				tableroControlDTO.setDescripcionTableroControl(tablerocontrol.getDescripcionTablerocontrol());

				tablerosDTO.add(tableroControlDTO);
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Columnareporte.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return tablerosDTO;
	}

}
