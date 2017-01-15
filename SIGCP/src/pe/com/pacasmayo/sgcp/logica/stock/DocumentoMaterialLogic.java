package pe.com.pacasmayo.sgcp.logica.stock;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DocumentoMaterialLogic.java
 * Modificado: Jun 9, 2010 11:26:45 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.MovimientoBean;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.logica.facade.DocumentoMaterialLogicFacade;
import pe.com.pacasmayo.sgcp.logica.partediario.ParteDiarioLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Documentomaterial;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Valorpromvariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.DocumentoMaterialQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ValorPromVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class DocumentoMaterialLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		DocumentoMaterialLogicFacade, ConstantesLogicaNegocio {

	private String mensajeError = "";

	private Log logger = LogFactory.getLog(this.getClass());

	public DocumentoMaterialLogic() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.stock.DocumentoMaterialLogicFacade#
	 * insertarDocumentoMaterial
	 * (pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean)
	 */
	public void insertarDocumentoMaterial(DocumentoMaterialBean documentoMaterialBean) throws LogicaException {

		Transaction tx = null;
		Session session = null;
		Documentomaterial documentoMaterial = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			// Se crea un documentoMaterial dado un DocumentoMaterialBean
			documentoMaterial = new Documentomaterial(documentoMaterialBean);
			Set<Movimiento> movimientos = new HashSet<Movimiento>();

			Long codigoVariableHumedad = Long.parseLong(ManejadorPropiedades
					.obtenerPropiedadPorClave(CODIGO_VARIABLE_CALIDAD_HUMEDAD));

			for (Iterator<MovimientoBean> iterator = documentoMaterialBean.getMovimientos().iterator(); iterator.hasNext();) {
				MovimientoBean movimientoBean = iterator.next();
				// Se crea un movimiento dado un MovimientoBean
				Movimiento movimiento = new Movimiento(movimientoBean);
				movimiento.setDocumentomaterial(documentoMaterial);
				logger.debug(movimiento.getFechaMovimiento() + "_" + movimiento.getCantidadMovimiento());
				movimientos.add(movimiento);

				if (!movimientoBean.getIsMovimientoLogistico()) {
					verificarVarCalidadMovimiento(movimiento, codigoVariableHumedad);
				}

			}
			documentoMaterial.setMovimientos(movimientos);
			DocumentoMaterialQuerier.save(documentoMaterial);
			tx.commit();
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Verifica si el producto del movimiento possee la variable de calidad si
	 * la posee relaiza el decuento y almacena el valor seco y humedo del
	 * movimiento
	 * 
	 * @param movimiento Movimiento
	 * @param codigoVariableHumedad
	 * @param producto
	 * @param productoVariableCalidad
	 * @param cantidadMovimiento
	 * @throws LogicaException
	 */
	private void verificarVarCalidadMovimiento(Movimiento movimiento, Long codigoVariableHumedad) throws LogicaException {

		Producto producto;
		try {
			producto = ProductoQuerier.getById(movimiento.getProducto().getPkCodigoProducto());
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
		Productovariablecalidad productoVariableCalidad = ProductoVariableCalidadQuerier.obtenerProductoVariableCalidad(
				producto.getPkCodigoProducto(), codigoVariableHumedad);

		Double cantidadMovimiento = movimiento.getCantidadMovimiento();
		movimiento.setCantidadMovimientoHumedad(cantidadMovimiento);

		double factorHumedad = 0d;
		if (productoVariableCalidad != null) {
			factorHumedad = ParteDiarioLogic.obtenerFactorHum(producto, productoVariableCalidad.getCodigoProcesoScc(), movimiento
					.getDocumentomaterial().getFechaDocumentomaterial(), productoVariableCalidad.getVariablecalidad()
					.getNombreVariablecalidad());

			if (factorHumedad == 0) {
				// Obtener Humedad almacenada dias anteriores

				Valorpromvariablecalidad promvarcalidad = ValorPromVariableCalidadQuerier.obtenerUltimoValorPromCalidadIngreso(
						productoVariableCalidad.getPkCodigoProductovariablecalid(), movimiento.getDocumentomaterial()
								.getFechaDocumentomaterial());
				if (promvarcalidad != null) {

					factorHumedad = promvarcalidad.getValorValorpromvariblecalidad();
				}
			}
			// La cantidad que se registra en la GUI es humeda, ya que
			// el producto tiene humedad
			movimiento.setCantidadMovimiento(cantidadMovimiento * (1 - (factorHumedad / 100d)));
		}

		movimiento.setFactorHumedad(factorHumedad);
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.stock.DocumentoMaterialLogicFacade#
	 * eliminarDocumentoMaterial(java.lang.Long)
	 */
	public void eliminarDocumentoMaterial(Long codigo) throws LogicaException {

		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			DocumentoMaterialQuerier.delete(DocumentoMaterialQuerier.getById(codigo));

			tx.commit();

		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.DocumentoMaterialLogicFacade#
	 * eliminarDocumentosGeneradosSap(java.util.Date)
	 */
	public void eliminarDocumentosGeneradosSap(Date fechaNotif) throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			session.beginTransaction();
			DocumentoMaterialQuerier.eliminarDocumentosGeneradosSap(fechaNotif);
			session.getTransaction().commit();
		} catch (ParametroInvalidoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}
}
