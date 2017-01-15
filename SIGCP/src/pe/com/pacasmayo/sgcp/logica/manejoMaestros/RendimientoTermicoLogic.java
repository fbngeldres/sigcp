package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.RendimientoTermicoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.RendimientoTermicoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.RendimientoTermico;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.RendimientoTermicoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class RendimientoTermicoLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, ConstantesFiltros,
		RendimientoTermicoLogicFacade, ConstantesLogicaNegocio {

	private Log logger = LogFactory.getLog(this.getClass());
	private static BeanFactory beanFactory;

	@SuppressWarnings("static-access")
	public RendimientoTermicoLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	public List<RendimientoTermicoBean> obtenerRendimientosTermicos() throws LogicaException {

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
			return beanFactory.transformarListaRendimientoTermico(RendimientoTermicoQuerier.getAll());
		} catch (SesionVencidaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ RendimientoTermico.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ RendimientoTermico.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LogicaException(mensajeError, e);
		}

		finally {
			if (session != null) {
				PersistenciaFactory.closeSession(session);
			}
		}

	}

	public RendimientoTermicoBean obtenerRendimientoTermico(Long pkCodigoRendimientoTermico) throws LogicaException {
		RendimientoTermicoBean rendimientoTermicoBean = null;
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
			rendimientoTermicoBean = beanFactory.transformarRendimientoTermico(RendimientoTermicoQuerier
					.getById(pkCodigoRendimientoTermico));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null) {
				PersistenciaFactory.closeSession(session);
			}
		}
		return rendimientoTermicoBean;
	}

	public void insertarRendimientoTermico(RendimientoTermicoBean rendimientoTermicoBean) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Puestotrabajo puestoTrabajo = new Puestotrabajo();
			Producto producto = new Producto();

			puestoTrabajo.setPkCodigoPuestotrabajo(rendimientoTermicoBean.getCodigoPuestoTrabajo());
			producto.setPkCodigoProducto(rendimientoTermicoBean.getCodigoProducto());
			RendimientoTermico rendimientoTermico = new RendimientoTermico();
			rendimientoTermico.setPuestoTrabajo(puestoTrabajo);
			rendimientoTermico.setProducto(producto);
			rendimientoTermico.setValorKiloCalorias1(rendimientoTermicoBean.getValorkCal1());
			rendimientoTermico.setValorKiloCalorias2(rendimientoTermicoBean.getValorkCal2());

			RendimientoTermicoQuerier.save(rendimientoTermico);

			tx.commit();
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null) {
				tx.rollback();
			}

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null) {
				tx.rollback();
			}

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null) {
				PersistenciaFactory.closeSession(session);
			}
		}

	}

	public void modificarRendimientoTermico(RendimientoTermicoBean rendimientoTermicoBean) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		Producto producto = new Producto();
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			producto.setPkCodigoProducto(rendimientoTermicoBean.getCodigoProducto());

			RendimientoTermico rendimientoTermico = RendimientoTermicoQuerier.getById(rendimientoTermicoBean
					.getPkCodigoRendimientoTermico());
			rendimientoTermico.setProducto(producto);
			rendimientoTermico.setValorKiloCalorias1(rendimientoTermicoBean.getValorkCal1());
			rendimientoTermico.setValorKiloCalorias2(rendimientoTermicoBean.getValorkCal2());

			RendimientoTermicoQuerier.update(rendimientoTermico);
			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null) {
				tx.rollback();
			}

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null) {
				tx.rollback();
			}

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null) {
				PersistenciaFactory.closeSession(session);
			}
		}

	}

	public void eliminarRendimientoTermico(RendimientoTermicoBean rendimientoTermicoBean) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			RendimientoTermico rendimientoTermico = RendimientoTermicoQuerier.getById(rendimientoTermicoBean
					.getPkCodigoRendimientoTermico());

			RendimientoTermicoQuerier.delete(rendimientoTermico);
			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null) {
				tx.rollback();
			}

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null) {
				tx.rollback();
			}

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null) {
				tx.rollback();
			}

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null) {
				PersistenciaFactory.closeSession(session);
			}
		}

	}

	public List<Long> obtenerCodigosProductosRendimientosTermicos() throws LogicaException {
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
			return RendimientoTermicoQuerier.getCodigoProductoRendimientoTermico();
		} catch (SesionVencidaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ RendimientoTermico.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ RendimientoTermico.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LogicaException(mensajeError, e);
		}

		// finally {
		// if (session != null) {
		// PersistenciaFactory.closeSession(session);
		// }
		// }
	}

	public List<Long> obtenerCodigosPuestoTrabajoRendimientosTermicos() throws LogicaException {
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
			return RendimientoTermicoQuerier.getCodigoPuestoTrabajoRendimientoTermico();
		} catch (SesionVencidaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ RendimientoTermico.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ RendimientoTermico.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LogicaException(mensajeError, e);
		}
	}

}
