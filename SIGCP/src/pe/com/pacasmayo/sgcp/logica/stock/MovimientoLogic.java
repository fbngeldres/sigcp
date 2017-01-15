package pe.com.pacasmayo.sgcp.logica.stock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.MovimientoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.MovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomovimiento;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoMovimientoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MovimientoLogic.java
 * Modificado: May 26, 2010 8:13:19 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class MovimientoLogic implements ConstantesMensajePresentacion, ConstantesLogicaNegocio, ConstantesMensajeAplicacion,
		MovimientoLogicFacade {

	private String mensajeError = "";

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;
	private static String FECHAMOVIMIENTO = "fechaMovimiento";

	public MovimientoLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	public List<MovimientoBean> obtenerMovimientosPorTipoFecha(String nombreTipoMovimiento, Date fecha) throws LogicaException {
		List<Tipomovimiento> tiposMovimientos;
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			tiposMovimientos = TipoMovimientoQuerier.findByNombre(nombreTipoMovimiento);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tipomovimiento.class.getName().toString();
			logger.error(mensajeError, e);
			if (session != null)
				PersistenciaFactory.closeSession(session);
			throw new LogicaException(mensajeError, e);
		}
		if (tiposMovimientos == null || tiposMovimientos.isEmpty()) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TIPO_MOVIMIENTO_NO_ENCONTRADO) + " "
					+ nombreTipoMovimiento;
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}
		Map<String, Object> propiedades = new HashMap<String, Object>();
		propiedades.put(MovimientoQuerier.CODIGO_TIPO_MOVIMIENTO, tiposMovimientos.get(0).getPkCodigoTipomovimiento());
		propiedades.put(FECHAMOVIMIENTO, fecha);
		try {
			return beanFactory.transformarListaMovimiento(MovimientoQuerier.obtenerMovimientosPorFiltros(propiedades));
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Movimiento.class.getName().toString();
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<MovimientoBean> obtenerMovimientoPorProductoYRangoFechas(Long codigoProducto, Date fechaIni, Date fechafin,
			Long codigoMedAlman, Long codigoAlma) throws LogicaException {

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

		List<MovimientoBean> movimientoBeans = null;

		try {
			movimientoBeans = beanFactory.transformarListaMovimiento(MovimientoQuerier.obtenerMovimientoPorProductoYRangoFechas(
					codigoProducto, fechaIni, fechafin, codigoMedAlman, codigoAlma));
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return movimientoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogicFacade#
	 * obtenerMovimientosPorFiltros()
	 */
	public List<MovimientoBean> obtenerMovimientosPorFiltros(Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoTipoDocumento, Long codigoAlmacen, Long codigoUbicacion, Long codigoProducto, Date fechaInicio,
			Date fechaFin) throws LogicaException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(COMUNICACION_BD_FALLO);
			logger.error(mensajeError, e);
			if (session != null)
				PersistenciaFactory.closeSession(session);
			throw new LogicaException(mensajeError, e);
		}
		try {
			return beanFactory.transformarListaMovimiento(MovimientoQuerier.obtenerMovimientosPorVarios(codigoSociedad,
					codigoUnidad, codigoLineaNegocio, codigoTipoDocumento, codigoAlmacen, codigoUbicacion, codigoProducto,
					fechaInicio, fechaFin));
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogicFacade#
	 * obtenerMovimientosSalidaPorFiltros()
	 */
	public List<MovimientoBean> obtenerMovimientosSalidaPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoTipoDocumento, Long codigoAlmacenSalida, Long codigoUbicacionSalida,
			Long codigoProducto, Date fechaInicio, Date fechaFin) throws LogicaException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(COMUNICACION_BD_FALLO);
			logger.error(mensajeError, e);
			if (session != null)
				PersistenciaFactory.closeSession(session);
			throw new LogicaException(mensajeError, e);
		}
		try {
			return beanFactory.transformarListaMovimiento(MovimientoQuerier.obtenerMovimientosPorVariosTransferencia(
					codigoSociedad, codigoUnidad, codigoLineaNegocio, codigoTipoDocumento, codigoAlmacenSalida,
					codigoUbicacionSalida, codigoProducto, fechaInicio, fechaFin));
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	// Inicio Metodo para Tabla Valor

	public List<MovimientoBean> obtenerMovimientosSalidaPorFiltrosTV(Long codigoSociedad, Long codigoDivision,
			String codigoSapProducto, Long codigoTipoDocumento, Short mes, Integer anio, boolean productoSinDiferenciar)
			throws LogicaException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(COMUNICACION_BD_FALLO);
			logger.error(mensajeError, e);
			if (session != null)
				PersistenciaFactory.closeSession(session);
			throw new LogicaException(mensajeError, e);
		}
		try {
			return beanFactory.transformarListaMovimiento(MovimientoQuerier.obtenerMovimientosPorVariosTransferenciaTV(
					codigoSociedad, codigoDivision, codigoSapProducto, codigoTipoDocumento, mes, anio, productoSinDiferenciar));

		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	// Termina Metodo para Tabla Valor

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogicFacade#
	 * obtenerMovimientosTransferenciaPorFiltros()
	 */
	public List<MovimientoBean> obtenerMovimientosTransferenciaPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoTipoDocumento, Long codigoAlmacen, Long codigoUbicacion, Long codigoProducto,
			Date fechaInicio, Date fechaFin) throws LogicaException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			return beanFactory.transformarListaMovimiento(MovimientoQuerier.obtenerMovimientosPorVariosTransferencia(
					codigoSociedad, codigoUnidad, codigoLineaNegocio, codigoTipoDocumento, codigoAlmacen, codigoUbicacion,
					codigoProducto, fechaInicio, fechaFin));
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	public Double obtenerMovimentoLogisticoProductoDAO(Long codigoLineNegocio, ProductoBean producto, Integer annio, Short mes)
			throws LogicaException

	{

		Long codigoProducto = producto.getCodigo();

		List<Movimiento> listaMovimientos;
		Double consumosEspecificos = 0d;
		try {

			Double cantidad = 0d;
			String[] codigos = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_MOVIMIENTO_LOGISTICO).split(",");

			Long codigoSalida = 2L;

			listaMovimientos = MovimientoQuerier.obtenerMovimientoPorRangoFechasProductoTipoMovimiento(codigoLineNegocio, annio,
					mes, codigos, codigoProducto, null);
			// Consumos Internos

			for (Movimiento movimiento : listaMovimientos) {

				cantidad = 0d;

				Long clasificacionMovimiento = movimiento.getTipomovimiento().getClasificaciontipomovimiento()
						.getPkCodigoClasificaciontipomovi();

				if (clasificacionMovimiento.compareTo(codigoSalida) == 0) {
					cantidad -= movimiento.getCantidadMovimiento();
				} else {
					cantidad += movimiento.getCantidadMovimiento();
				}

				consumosEspecificos += cantidad;

			}

			System.out.println("--Cantidad " + cantidad);
		} catch (NumberFormatException e) {
			mensajeError = "Formato de Numero Incorrecto: " + e.getMessage();
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}
		return consumosEspecificos;
	}

	public static void main(String[] args) {
		MovimientoLogic logic = new MovimientoLogic();

		try {
			logic.obtenerMovimentoLogisticoProductoDiariaDAO(1L, 230L, FechaUtil.FormaterFecha("31/01/2016"));
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Double obtenerMovimentoLogisticoProductoDiariaDAO(Long codigoLineNegocio, Long codigoProducto, Date fechaDiaria)
			throws LogicaException

	{

		List<Movimiento> listaMovimientos;
		Double consumosEspecificos = 0d;
		try {

			Double cantidad = 0d;
			String[] codigos = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_MOVIMIENTO_LOGISTICO).split(",");

			Long codigoSalida = 2L;

			listaMovimientos = MovimientoQuerier.obtenerMovimientoPorDiaProductoTipoMovimiento(codigoLineNegocio, fechaDiaria,
					codigoProducto, codigos);
			// Consumos Internos

			for (Movimiento movimiento : listaMovimientos) {

				cantidad = 0d;

				Long clasificacionMovimiento = movimiento.getTipomovimiento().getClasificaciontipomovimiento()
						.getPkCodigoClasificaciontipomovi();

				if (clasificacionMovimiento.compareTo(codigoSalida) == 0) {
					cantidad -= movimiento.getCantidadMovimiento();
				} else {
					cantidad += movimiento.getCantidadMovimiento();
				}

				consumosEspecificos += cantidad;

			}

			System.out.println("--Cantidad " + consumosEspecificos);
		} catch (NumberFormatException e) {
			mensajeError = "Formato de Numero Incorrecto: " + e.getMessage();
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}
		return consumosEspecificos;
	}

	public Map<String, Double> obtenerMovimientoLogistico(Long codigoLineNegocio, ProductoBean producto, Integer annio, Short mes)
			throws LogicaException

	{

		Long codigoProducto = producto.getCodigo();

		Map<String, Double> mapaDiaConsumosEspecificos = new HashMap<>();
		List<Movimiento> listaMovimientos;

		try {

			Double cantidad = 0d;
			String[] codigos = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_MOVIMIENTO_LOGISTICO).split(",");

			Long codigoSalida = 2L;

			listaMovimientos = MovimientoQuerier.obtenerMovimientoPorRangoFechasProductoTipoMovimiento(codigoLineNegocio, annio,
					mes, codigos, codigoProducto, null);
			// Consumos Internos
			for (Movimiento movimiento : listaMovimientos) {
				System.out.println("-ss->" + movimiento.getTipomovimiento().getCodigoSapTipomovimiento());

				cantidad = 0d;
				String fecha = FechaUtil.convertirDateToString(movimiento.getFechaMovimiento());

				Double consumosEspecificos = mapaDiaConsumosEspecificos.get(fecha);

				if (consumosEspecificos == null) {
					consumosEspecificos = 0d;

				}

				Long clasificacionMovimiento = movimiento.getTipomovimiento().getClasificaciontipomovimiento()
						.getPkCodigoClasificaciontipomovi();

				if (clasificacionMovimiento.compareTo(codigoSalida) == 0) {
					cantidad -= movimiento.getCantidadMovimiento();
				} else {
					cantidad += movimiento.getCantidadMovimiento();
				}

				consumosEspecificos += cantidad;
				mapaDiaConsumosEspecificos.put(fecha, consumosEspecificos);
			}

		} catch (NumberFormatException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		}
		return mapaDiaConsumosEspecificos;
	}

}
