package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.TransactionException;

import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoAjusteProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.JDBCConnectionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SessionException;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoAjusteProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoAjusteProduccionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		EstadoAjusteProduccionLogicFacade {

	private static BeanFactory beanFactory = BeanFactoryImpl.getInstance();

	private Log logger = LogFactory.getLog(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.parteTecnico.EstadoAjusteProduccionLogicFacade
	 * #obtenerEstadosColumnaReporte()
	 */
	public List<EstadoAjusteProduccionBean> obtenerEstadosAjusteProduccion() throws LogicaException {

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
			return beanFactory.transformarListaEstadoAjusteProduccion(EstadoAjusteProduccionQuerier.getAll());

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

}
