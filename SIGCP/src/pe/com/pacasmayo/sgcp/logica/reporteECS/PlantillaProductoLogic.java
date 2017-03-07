package pe.com.pacasmayo.sgcp.logica.reporteECS;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.bean.PlantillaProductoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.PlantillaProductoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.JDBCConnectionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SessionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.TransactionException;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaProductoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PlantillaProductoDTO;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PlantillaProductoLogic implements PlantillaProductoLogicFacade, ConstantesMensajeAplicacion {
	private Log logger = LogFactory.getLog(this.getClass());
	private static BeanFactory beanFactory;

	public PlantillaProductoLogic() {
		beanFactory = BeanFactoryImpl.getInstance();

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.PlantillaProductoLogicFacade#
	 * obtenerPorDatosDePlantillaProducto(java.lang.Long, java.lang.Long)
	 */
	public List<PlantillaProductoBean> obtenerPorPlantillaReporteYProducto(Long codigoPlantillaReporte, Long codigoProducto)
			throws LogicaException {

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
		
		List<PlantillaProductoBean> listaPlantillaProducto = null;
		try {
			List<Plantillaproducto> plantillaproductos = PlantillaProductoQuerier.obtenerPorPlantillaReporteYProducto(codigoPlantillaReporte, codigoProducto); 
			listaPlantillaProducto = beanFactory.transformarListaPlantillaProducto(plantillaproductos);
			return listaPlantillaProducto;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public PlantillaProductoDTO obtenerActualPorPlantillaReporteYProducto(Long codigoPlantillaReporte, Long codigoProducto)
			throws LogicaException {

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
			Plantillaproducto plantillasProducto = PlantillaProductoQuerier.obtenerActualPorPlantillaReporteYProducto(
					codigoPlantillaReporte, codigoProducto);
			if (plantillasProducto == null) {
				return null;
			}
			Date fechaPlantillaproducto = plantillasProducto.getFechaPlantillaproducto();
			String fechaStr = FechaUtil.convertirDateToString(fechaPlantillaproducto);

			PlantillaProductoDTO plantillaProductoDTO = new PlantillaProductoDTO();
			plantillaProductoDTO.setPkCodigoPlantillaProducto(plantillasProducto.getPkCodigoPlantillaproducto());
			plantillaProductoDTO.setNombrePlantillaProducto(plantillasProducto.getProducto().getNombreProducto() + " " + fechaStr
					+ " v" + plantillasProducto.getVersionPlantillaproducto());
			return plantillaProductoDTO;
		} catch (SesionVencidaException e) {
			throw new LogicaException(e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}
}
