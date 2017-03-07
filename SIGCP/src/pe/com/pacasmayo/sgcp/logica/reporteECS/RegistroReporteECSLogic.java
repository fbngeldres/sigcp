package pe.com.pacasmayo.sgcp.logica.reporteECS;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.logica.facade.DatoReporteLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RegistroReporteECSLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.RegistroReporteECSQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class RegistroReporteECSLogic implements ConstantesMensajeAplicacion, ConstantesMensajePresentacion,
		RegistroReporteECSLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public RegistroReporteECSLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.reporteECS.RegistroReporteECSLogicFacade
	 * #insertarRegistroReporteECS
	 * (pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean)
	 */
	public void insertarRegistroReporteECS(RegistroReporteEcsBean registroReporteECSBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		DatoReporteLogicFacade datoReporteFacade = new DatoReporteLogic();

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			List<Registroreporteecs> registros = RegistroReporteECSQuerier.findByNombre(registroReporteECSBean
					.getNombreRegistroReportEecs());

			if (registros != null && registros.size() > 0) {
				Registroreporteecs registroReporte = registros.get(0);
				registroReporte.setNombreRegistroreporteecs(registroReporteECSBean.getNombreRegistroReportEecs());
				registroReporte.setFechaRegistroreporteecs(registroReporteECSBean.getFechaRegistroReporteEcs());

				RegistroReporteECSQuerier.update(registroReporte);
				datoReporteFacade.actualizarDatosReporte(registroReporteECSBean.getDatosReporte(), registroReporte, tx);
			} else {
				Registroreporteecs registroReporte = new Registroreporteecs();
				registroReporte.setNombreRegistroreporteecs(registroReporteECSBean.getNombreRegistroReportEecs());
				registroReporte.setFechaRegistroreporteecs(registroReporteECSBean.getFechaRegistroReporteEcs());

				RegistroReporteECSQuerier.save(registroReporte);
				datoReporteFacade.insertarDatosReporte(registroReporteECSBean.getDatosReporte(), registroReporte, tx);
			}
		} catch (ParametroInvalidoException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			logger.error(mensajeError, e);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

}
