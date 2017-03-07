package pe.com.pacasmayo.sgcp.logica.stock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.CubicacionProductoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.CubicacionProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.DivisionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoCubicacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.LineaNegocioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedioAlmacenamientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.SociedadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.CubicacionProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConvertidorHibernateDTO;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.XLSFactory;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionProductoLogic.java
 * Modificado: Jun 8, 2010 11:19:00 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class CubicacionProductoLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		ConstantesLogicaNegocio, CubicacionProductoLogicFacade {

	private static BeanFactory beanFactory;

	private static Log logger = LogFactory.getLog(CubicacionProductoLogic.class.getCanonicalName());
	private AjusteProduccionMesLogicFacade ajusteProduccionLogic;

	@SuppressWarnings("static-access")
	public CubicacionProductoLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
		ajusteProduccionLogic = new AjusteProduccionMesLogic();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade#
	 * obtenerCubicionesProductoPorPropiedades(java.util.Map)
	 */
	public List<CubicacionProductoBean> obtenerCubicionesProductoPorPropiedades(Map<String, Object> propiedades)
			throws LogicaException {
		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		

		try {
			return beanFactory.transformarListaCubicacionProducto(CubicacionProductoQuerier.buscarPorPropiedades(propiedades));
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_LISTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} catch (SesionVencidaException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_LISTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_LISTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_LISTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		}finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade#
	 * aprobarCubicacion(java.lang.Long)
	 */
	public void aprobarCubicacion(Long codigoCubicacionProducto) throws LogicaException {

		Transaction tx = null;
		Session sesion = null;
		Cubicacionproducto cubicacionProducto = null;

		try {

			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			cubicacionProducto = CubicacionProductoQuerier.getById(codigoCubicacionProducto);

			if (cubicacionProducto == null) {
				throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_APROBAR_CUBICACION));
			}

			String estadoActualStr = cubicacionProducto.getEstadocubicacion().getNombreEstadocubicacion().toLowerCase();

			Estadocubicacion estadoCubicacion = EstadoCubicacionQuerier.obtenerEstadoAprobado();
			Estadocubicacion estadoGenerado = EstadoCubicacionQuerier.obtenerEstadoGenerado();

			if (!estadoActualStr.equals(estadoGenerado.getNombreEstadocubicacion().toLowerCase())) {
				throw new LogicaException("Solo  se pueden aprobar cubicaciones en estado: "
						+ estadoGenerado.getNombreEstadocubicacion());
			}

			/*
			 * tomo las propiedades para buscar si existe uno igual con estado
			 * aprobado
			 */
			Map<String, Object> propiedadesFiltrado = agregarPropiedadesFiltrado(cubicacionProducto);
			propiedadesFiltrado.put(CubicacionProductoQuerier.CODIGO_ESTADO_CUBICACION,
					estadoCubicacion.getPkCodigoEstadocubicacion());
			List<Cubicacionproducto> cubicaciones = CubicacionProductoQuerier.buscarPorPropiedades(propiedadesFiltrado);
			if (cubicaciones.size() > 0) {
				String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_CUBICACION_REPETIDA);

				throw new LogicaException(mensajeError);
			}
			cubicacionProducto.setEstadocubicacion(estadoCubicacion);
			CubicacionProductoQuerier.update(cubicacionProducto);

			tx.commit();
		} catch (LogicaException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} catch (ParametroInvalidoException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} catch (ElementoExistenteException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} catch (ElementoEliminadoException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} catch (ElementoNoEncontradoException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} catch (SesionVencidaException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} catch (EntornoEjecucionException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} catch (AplicacionException e) {
			rollBack(tx, e, ERROR_STOCK_APROBAR_CUBICACION);
		} finally {
			PersistenciaFactory.closeSession();
		}
	}

	private void rollBack(Transaction tx, Exception e, String clavePropiedad) throws LogicaException {
		if (tx != null) {
			tx.rollback();
		}
		logger.error(e);
		throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(clavePropiedad), e);
	}

	public void registrarCubicacion(List<TablaCubicacionDTO> listaCubicaciones, Long codigoUsuario) throws LogicaException {
		Cubicacionproducto cubicacionproducto = new Cubicacionproducto();
		Transaction tx = null;
		Session sesion = null;
		try {
			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			// Se toma el primero porq todos traen el mismo valor de los combos
			// de cabecera
			long codigoLineaNegocio = listaCubicaciones.get(0).getCodigoLineaNegocio().longValue();
			long codigoProducto = listaCubicaciones.get(0).getCodigoProducto().longValue();
			long codigoProceso = listaCubicaciones.get(0).getCodigoProceso().longValue();
			long codigoEstado = listaCubicaciones.get(0).getCodigoEstado().longValue();
			Date fechaCubicacion = listaCubicaciones.get(0).getFecha();
			Double humedad = listaCubicaciones.get(0).getHumedadPonderada();

			Calendar calendar = FechaUtil.getCalendarByDate(fechaCubicacion);

			short mes = (short) (calendar.get(Calendar.MONTH) + 1);
			int anio = calendar.get(Calendar.YEAR);

			Lineanegocio lineaNegocio = LineaNegocioQuerier.getById(codigoLineaNegocio);

			Usuario usuario = UsuarioQuerier.getById(codigoUsuario);

			Produccion produccion = ProduccionQuerier.getByProductoProceso(codigoProducto, codigoProceso);

			Estadocubicacion estadocubicacion = EstadoCubicacionQuerier.getById(codigoEstado);

			cubicacionproducto.setAnoCubicacionproducto(anio);
			cubicacionproducto.setEstadocubicacion(estadocubicacion);
			cubicacionproducto.setFechaCubicacionproducto(fechaCubicacion);
			cubicacionproducto.setHumedadPonderadaCubicacionproducto(humedad);
			cubicacionproducto.setLineanegocio(lineaNegocio);
			cubicacionproducto.setMesCubicacionproducto(mes);
			cubicacionproducto.setProduccion(produccion);
			cubicacionproducto.setUsuarioByFkCodigoUsuario(usuario);
			cubicacionproducto.setUsuarioByFkCodigoUsuarioRegistra(usuario);
			cubicacionproducto.setToneladasCubicacionproducto(0D);
			cubicacionproducto.setStockFisicoCubicacionproducto(0D);

			Set<Cubicacion> cubicaciones = new HashSet<Cubicacion>();
			for (Iterator<TablaCubicacionDTO> iterator = listaCubicaciones.iterator(); iterator.hasNext();) {
				TablaCubicacionDTO tablaCubicacionDTO = iterator.next();
				Cubicacion cubicacion = new Cubicacion();

				Double alturaCentral = tablaCubicacionDTO.getAlturaCentral();
				if (alturaCentral != null) {
					cubicacion.setAlturaCentralMtsCubicacion(alturaCentral);
				}

				Double alturaLadoCarboni = tablaCubicacionDTO.getAlturaLadoCarboni();
				if (alturaLadoCarboni != null) {
					cubicacion.setAlturaLadoCarbonMtsCubicaci(alturaLadoCarboni);
				}

				Double alturaLadoClinker = tablaCubicacionDTO.getAlturaLadoClinker();
				if (alturaLadoClinker != null) {
					cubicacion.setAlturaLadoClinkerMtsCubicac(alturaLadoClinker);
				}

				Double alturaLibrePromedio = tablaCubicacionDTO.getAlturaLibrePromedio();
				if (alturaLibrePromedio != null) {
					cubicacion.setAlturaLibrePromedioMtsCubic(alturaLibrePromedio);
				}

				cubicacion.setAreaCrestaM2Cubicacion(tablaCubicacionDTO.getAreaCresta());

				cubicacion.setAreaOcupadaM2Cubicacion(tablaCubicacionDTO.getAreaOcupada());

				cubicacion.setAreaPieM2Cubicacion(tablaCubicacionDTO.getAreaPie());
				cubicacion.setCubicacionproducto(cubicacionproducto);
				cubicacion.setDiferenciaNivelMtsCubicacion(tablaCubicacionDTO.getDiferenciaNivel());
				cubicacion.setMedioalmacenamiento(MedioAlmacenamientoQuerier.getById(tablaCubicacionDTO
						.getCodigoMedioalmacenamiento().longValue()));

				String observacionCubicacion = tablaCubicacionDTO.getObservacionCubicacion();
				if (observacionCubicacion != null) {
					cubicacion.setObservacionCubicacion(observacionCubicacion);
				}

				Double relacionCubicacion = tablaCubicacionDTO.getRelacionCubicacion();
				if (relacionCubicacion != null) {
					cubicacion.setRelacionCubicacion(relacionCubicacion);
				}

				Double unidad = tablaCubicacionDTO.getUnidad();
				if (unidad != null) {
					cubicacion.setUnidadKgCubicacion(unidad);
				}

				cubicacion.setVolumenM3Cubicacion(tablaCubicacionDTO.getVolumen());

				if (tablaCubicacionDTO.getDensidadMedioAlmc() != null) {
					cubicacion.setDensidadMedioalmacenamiento(tablaCubicacionDTO.getDensidadMedioAlmc());
				}

				cubicaciones.add(cubicacion);
			}
			cubicacionproducto.setCubicacions(cubicaciones);

			CubicacionProductoQuerier.save(cubicacionproducto);
			tx.commit();
		} catch (ParametroInvalidoException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (ElementoExistenteException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (ElementoEliminadoException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (ElementoNoEncontradoException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (SesionVencidaException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (HibernateException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (EntornoEjecucionException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} catch (AplicacionException e) {
			rollBack(tx, e, ERROR_STOCK_REGISTRAR_CUBICACION);
		} finally {
			PersistenciaFactory.closeSession();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade#
	 * anularCubicacion(java.lang.Long)
	 */
	public void anularCubicacion(Long codigoCubicacionProducto) throws LogicaException {
		Transaction tx = null;
		Session sesion = null;
		Cubicacionproducto cubicacionProducto = null;

		try {

			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			cubicacionProducto = CubicacionProductoQuerier.getById(codigoCubicacionProducto);

			String estadoActualStr = cubicacionProducto.getEstadocubicacion().getNombreEstadocubicacion().toLowerCase();

			Estadocubicacion estadoCubicacion = EstadoCubicacionQuerier.obtenerEstadoAnulado();
			Estadocubicacion estadoGenerado = EstadoCubicacionQuerier.obtenerEstadoGenerado();

			if (!estadoActualStr.equals(estadoGenerado.getNombreEstadocubicacion().toLowerCase())) {
				throw new LogicaException("Solo se puede anular cubicaciones en estado: "
						+ estadoGenerado.getNombreEstadocubicacion());
			}

			cubicacionProducto.setEstadocubicacion(estadoCubicacion);

			CubicacionProductoQuerier.update(cubicacionProducto);

			tx.commit();
		} catch (LogicaException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ParametroInvalidoException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ElementoExistenteException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ElementoEliminadoException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ElementoNoEncontradoException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (SesionVencidaException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (EntornoEjecucionException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (AplicacionException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} finally {
			PersistenciaFactory.closeSession();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade#
	 * modificarCubicacion(java.lang.Long, java.util.List, java.lang.Long)
	 */
	public void modificarCubicacion(Long codigoCubicacionProducto, List<TablaCubicacionDTO> listaCubicaciones, Long codigoUsuario)
			throws LogicaException {

		Cubicacionproducto cubicacionproducto;
		Transaction tx = null;
		Session sesion = null;
		validarDatosNecesariosModificar(codigoCubicacionProducto, listaCubicaciones);
		try {
			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			cubicacionproducto = CubicacionProductoQuerier.getById(codigoCubicacionProducto);

			if (listaCubicaciones.size() > 0) {
				Double humedad = listaCubicaciones.get(0).getHumedadPonderada();

				cubicacionproducto.setHumedadPonderadaCubicacionproducto(humedad);
			}

			Set<Cubicacion> cubicaciones = new HashSet<Cubicacion>();
			for (Iterator<TablaCubicacionDTO> iterator = listaCubicaciones.iterator(); iterator.hasNext();) {
				TablaCubicacionDTO tablaCubicacionDTO = iterator.next();
				Cubicacion cubicacion = new Cubicacion();

				Double alturaCentral = tablaCubicacionDTO.getAlturaCentral();
				if (alturaCentral != null) {
					cubicacion.setAlturaCentralMtsCubicacion(alturaCentral);
				}

				Double alturaLadoCarboni = tablaCubicacionDTO.getAlturaLadoCarboni();
				if (alturaLadoCarboni != null) {
					cubicacion.setAlturaLadoCarbonMtsCubicaci(alturaLadoCarboni);
				}

				Double alturaLadoClinker = tablaCubicacionDTO.getAlturaLadoClinker();
				if (alturaLadoClinker != null) {
					cubicacion.setAlturaLadoClinkerMtsCubicac(alturaLadoClinker);
				}

				Double alturaLibrePromedio = tablaCubicacionDTO.getAlturaLibrePromedio();
				if (alturaLibrePromedio != null) {
					cubicacion.setAlturaLibrePromedioMtsCubic(alturaLibrePromedio);
				}

				cubicacion.setAreaCrestaM2Cubicacion(tablaCubicacionDTO.getAreaCresta());

				cubicacion.setAreaOcupadaM2Cubicacion(tablaCubicacionDTO.getAreaOcupada());

				cubicacion.setAreaPieM2Cubicacion(tablaCubicacionDTO.getAreaPie());
				cubicacion.setCubicacionproducto(cubicacionproducto);
				cubicacion.setDiferenciaNivelMtsCubicacion(tablaCubicacionDTO.getDiferenciaNivel());
				cubicacion.setMedioalmacenamiento(MedioAlmacenamientoQuerier.getById(tablaCubicacionDTO
						.getCodigoMedioalmacenamiento().longValue()));

				String observacionCubicacion = tablaCubicacionDTO.getObservacionCubicacion();
				if (observacionCubicacion != null) {
					cubicacion.setObservacionCubicacion(observacionCubicacion);
				}

				Double relacionCubicacion = tablaCubicacionDTO.getRelacionCubicacion();
				if (relacionCubicacion != null) {
					cubicacion.setRelacionCubicacion(relacionCubicacion);
				}

				Double unidad = tablaCubicacionDTO.getUnidad();
				if (unidad != null) {
					cubicacion.setUnidadKgCubicacion(unidad);
				}

				cubicacion.setVolumenM3Cubicacion(tablaCubicacionDTO.getVolumen());
				if (tablaCubicacionDTO.getDensidadMedioAlmc() != null) {
					cubicacion.setDensidadMedioalmacenamiento(tablaCubicacionDTO.getDensidadMedioAlmc());
				}

				cubicaciones.add(cubicacion);
			}
			for (Object cubicacionObj : cubicacionproducto.getCubicacions()) {
				CubicacionProductoQuerier.delete(cubicacionObj);
			}
			cubicacionproducto.setCubicacions(cubicaciones);
			CubicacionProductoQuerier.update(cubicacionproducto);
			tx.commit();
		} catch (ParametroInvalidoException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (ElementoExistenteException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (ElementoEliminadoException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (ElementoNoEncontradoException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (SesionVencidaException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (EntornoEjecucionException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (HibernateException e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} catch (Exception e) {
			rollBack(tx, e, ERROR_STOCK_MODIFICAR_CUBICACION);
		} finally {
			PersistenciaFactory.closeSession();
		}
	}

	/**
	 * Agrega los datos de la cubicacion producto a un Map<Strig, Object>
	 * 
	 * @param cubicacionproducto
	 * @return
	 */
	private Map<String, Object> agregarPropiedadesFiltrado(Cubicacionproducto cubicacionproducto) {
		Map<String, Object> propiedades = new HashMap<String, Object>();

		Long valorLineaNegocio = cubicacionproducto.getLineanegocio().getPkCodigoLineanegocio();
		Long valorProceso = cubicacionproducto.getProduccion().getProceso().getPkCodigoProceso();
		Long valorProducto = cubicacionproducto.getProduccion().getProducto().getPkCodigoProducto();
		Integer anioCubicacion = cubicacionproducto.getAnoCubicacionproducto();
		Short mesCubicacion = cubicacionproducto.getMesCubicacionproducto();

		if (valorLineaNegocio != null) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_LINEA_NEGOCIO, valorLineaNegocio);
		}

		if (valorProceso != null) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_PROCESO, valorProceso);
		}

		if (valorProducto != null) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_PRODUCTO, valorProducto);
		}

		if (anioCubicacion != null) {
			propiedades.put(CubicacionProductoQuerier.ANIO, anioCubicacion);
		}

		if (mesCubicacion != null) {
			propiedades.put(CubicacionProductoQuerier.MES, mesCubicacion);
		}

		return propiedades;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade#
	 * obtenerCubicacionProducto(java.lang.Long)
	 */
	public CubicacionProductoDTO obtenerCubicacionProducto(Long codigoCubicacionProducto) throws LogicaException {

		Cubicacionproducto cubicacionProducto = null;
		CubicacionProductoDTO cubicacionProductoDTO = null;
		String mensajeError = "";

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			cubicacionProducto = CubicacionProductoQuerier.getById(codigoCubicacionProducto);
			cubicacionProductoDTO =  ConvertidorHibernateDTO.convertirCubicacionProductoACubicacionProductoDTO(cubicacionProducto);

			String estadoAprobado = EstadoCubicacionQuerier.obtenerEstadoAprobado().getNombreEstadocubicacion();
			String estadoAnulado = EstadoCubicacionQuerier.obtenerEstadoAnulado().getNombreEstadocubicacion();
			String estadoActual = cubicacionProducto.getEstadocubicacion().getNombreEstadocubicacion();
			cubicacionProductoDTO.setAprobadoAnulado(estadoActual.compareTo(estadoAprobado) == 0
					|| estadoActual.compareTo(estadoAnulado) == 0);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_CONSULTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} catch (SesionVencidaException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_CONSULTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_CONSULTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_STOCK_CONSULTAR_CUBICACION);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return cubicacionProductoDTO;
	}
	
	
	/**
	 * Verifica si el existen los datos necesarios para realizar la modificación
	 * de la Cubicación del Producto
	 * 
	 * @param codigoCubicacionProducto
	 * @param cubicaciones
	 * @throws LogicaException
	 */
	private void validarDatosNecesariosModificar(Long codigoCubicacionProducto, List<TablaCubicacionDTO> cubicaciones)
			throws LogicaException {
		if (codigoCubicacionProducto == null || codigoCubicacionProducto <= 0) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PARAMETRO_INVALIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}
		if (cubicaciones == null || cubicaciones.isEmpty()) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PARAMETRO_INVALIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}
	}

	public Boolean validarSiExisteCubicacionProducto(long codigoLineaNegocio, long codigoProducto, long codigoProceso,
			Date fechaCubicacion) throws LogicaException {
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Cubicacionproducto cubicacionProducto = CubicacionProductoQuerier.obtenerPorPropiedadesYEdoDistintoDeAnulado(
					codigoLineaNegocio, codigoProducto, codigoProceso, fechaCubicacion);

			return cubicacionProducto != null;

		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Este metodo fue agregado por Fabian Geldres Genera el reporte Cubicacion
	 */
	public ByteArrayOutputStream generarReporteCubicacion(Integer anio, Short mes, Long division, Long sociedad, Long unidad,
			String ruta) throws LogicaException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {

			HSSFWorkbook libroXLS = null;
			XLSFactory xlsFactory = new XLSFactory();
			String nombreDivision = DivisionQuerier.getById(division).getNombreDivision();
			String nombreSociedad = SociedadQuerier.getById(sociedad).getNombreSociedad();
			String nombreUnidad = UnidadQuerier.getById(unidad).getNombreUnidad();

			List<Cubicacion> cubicaciones = CubicacionProductoQuerier.getCubicaciones(anio, mes, null);

			libroXLS = xlsFactory.crearReporteCubicaciones(cubicaciones, nombreDivision, nombreSociedad, nombreUnidad, mes, ruta);
			libroXLS.write(baos);

		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje());
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMessage());
		}finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return baos;

	}

	public void revertirCubicacion(long codigoCubicacionProducto) throws LogicaException {
		Transaction tx = null;
		Session sesion = null;
		Cubicacionproducto cubicacionProducto = null;

		try {

			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			cubicacionProducto = CubicacionProductoQuerier.getById(codigoCubicacionProducto);
			Estadocubicacion estadoGenerado = EstadoCubicacionQuerier.obtenerEstadoGenerado();
			cubicacionProducto.setEstadocubicacion(estadoGenerado);

			CubicacionProductoQuerier.update(cubicacionProducto);

			tx.commit();
		} catch (LogicaException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ParametroInvalidoException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ElementoExistenteException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ElementoEliminadoException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (ElementoNoEncontradoException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (SesionVencidaException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (EntornoEjecucionException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} catch (AplicacionException e) {
			rollBack(tx, e, ERROR_STOCK_ANULAR_CUBICACION);
		} finally {
			PersistenciaFactory.closeSession();
		}

	}

	public Boolean validarCubicaciones(String[] codigosCubicaciones) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		Cubicacionproducto cubicacionProducto = null;
		Boolean operacion = true;
		Boolean operacionPT = true;
		Short mesFiltrado = -1;
		Integer anioFiltrado = -1;
		try {
			Estadocubicacion estadoAprobado = EstadoCubicacionQuerier.obtenerEstadoAprobado();

			for (int i = 0; i < codigosCubicaciones.length; i++) {

				if (!(operacion && operacionPT)) {
					break;
				}

				cubicacionProducto = CubicacionProductoQuerier.getById(Long.valueOf(codigosCubicaciones[i]));

				if (!estadoAprobado.getNombreEstadocubicacion().equals(
						cubicacionProducto.getEstadocubicacion().getNombreEstadocubicacion())) {
					operacion = false;
				}

				if (mesFiltrado != cubicacionProducto.getMesCubicacionproducto()
						&& anioFiltrado != cubicacionProducto.getAnoCubicacionproducto()) {
					mesFiltrado = cubicacionProducto.getMesCubicacionproducto();
					anioFiltrado = cubicacionProducto.getAnoCubicacionproducto();

					List<AjusteProduccionBean> ajusteproduccion = ajusteProduccionLogic
							.obtenerAjustePorduccionPorPerdiodoLineaNegocio(cubicacionProducto.getLineanegocio()
									.getPkCodigoLineanegocio(), mesFiltrado, anioFiltrado);

					if ((ajusteproduccion.size() == 1)
							&& (ajusteproduccion.get(0).getEstadoajusteproduccion().getNombre().equals("Aprobado") || ajusteproduccion
									.get(0).getEstadoajusteproduccion().getNombre().equals("Cerrado"))) {
						operacionPT = false;
					}

				}

			}
		} catch (SesionVencidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntornoEjecucionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AplicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}


		return (operacion && operacionPT);
	}
}
