package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProductoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;

import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class AjusteProductoLogic implements AjusteProductoLogicFacade, ConstantesMensajePresentacion,
		ConstantesMensajeAplicacion, ConstantesLogicaNegocio {
	private static BeanFactory beanFactory;

	public AjusteProductoLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	private Log logger = LogFactory.getLog(this.getClass());

	public List<AjusteProductoBean> obtenerAjusteProducto(Long lineaCodigo, Integer anioContable, Short mesContable,
			Long estadoAjusteProduccionCodigo) throws LogicaException {

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

		Map<String, Object> propiedades = new HashMap<String, Object>();

		if (lineaCodigo != null) {

			propiedades.put(AjusteProductoQuerier.CODIGO_LINEA_NEGOCIO_AJUSTE_PRODUCTO, lineaCodigo);
		}
		if (anioContable != null) {

			propiedades.put(AjusteProductoQuerier.ANNO_CONTABLE_AJUSTE_PRODUCTO, anioContable);
		}
		if (mesContable != null) {

			propiedades.put(AjusteProductoQuerier.MES_CONTABLE_AJUSTE_PRODUCTO, mesContable);
		}
		if (estadoAjusteProduccionCodigo != null) {

			propiedades.put(AjusteProductoQuerier.CODIGO_ESTADO_AJUSTE_PRODUCTO, estadoAjusteProduccionCodigo);
		}

		List<AjusteProductoBean> listaajusteProductoBean = null;
		try {
			List<Ajusteproducto> listaAjusteProducto = AjusteProductoQuerier.obtenerPorPropiedades(propiedades);

			listaajusteProductoBean = beanFactory.transformarListaAjusteProducto(listaAjusteProducto);

		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaajusteProductoBean;
	}

	public AjusteProductoBean obtenerPorCodigo(Long codigoAjuste) throws LogicaException {

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

		AjusteProductoBean ajusteProductoBean = null;
		try {
			Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorCodigo(codigoAjuste);
			ajusteProductoBean = beanFactory.transformarAjusteProducto(ajusteProducto);
		} catch (ElementoNoEncontradoException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return ajusteProductoBean;
	}

	public void eliminarAjusteProducto(Long codigoAjuste, UsuarioBean usuario) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorCodigo(codigoAjuste);

			if (ajusteProducto != null) {
				AjusteProductoQuerier.delete(ajusteProducto);
				tx.commit();
			}
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}


	}
}
