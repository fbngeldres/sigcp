package pe.com.pacasmayo.sgcp.logica.notificacion;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.DetalleReporteDosificacion;
import pe.com.pacasmayo.sgcp.bean.NotDiariaProduccionRepBean;
import pe.com.pacasmayo.sgcp.bean.NotVarProduccionPuestoTrabajoRepBean;
import pe.com.pacasmayo.sgcp.bean.NotVariablesProduccionHoraRepBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.ReporteDosificacion;
import pe.com.pacasmayo.sgcp.bean.ResumenNotificacionItemRepBean;
import pe.com.pacasmayo.sgcp.bean.ResumenNotificacionRepBean;
import pe.com.pacasmayo.sgcp.bean.SubReporteDosificacion;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnaplantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hora;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productogenerado;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteNotificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.DivisionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionDiariaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoGeneradoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.SociedadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;

import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de GestiÃ³n y Control de la ProducciÃ³n) 
 * Archivo: NotificacionProduccionLogic.java
 * Modificado: Jun 25, 2010 3:28:45 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class NotificacionProduccionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		ConstantesLogicaNegocio, NotificacionProduccionLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());
	private static BeanFactory beanFactory;

	public NotificacionProduccionLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
		parametroSistemaLogicFacade = new ParametroSistemaLogic();
	}

	private static final String ESTADO_CERRADO = ManejadorPropiedades.obtenerPropiedadPorClave(ESTADONOTIFICACION_CERRADO);
	private static final String ESTADO_APROBADO = ManejadorPropiedades.obtenerPropiedadPorClave(ESTADONOTIFICACION_APROBADO);
	private NotificacionDiariaLogicFacade notificacionDiariaLogic = new NotificacionDiariaLogic();
	ParametroSistemaLogicFacade parametroSistemaLogicFacade;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.facade.NotificacionProduccionLogicFacade
	 * #insertarNotificacionProduccion(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date, java.lang.Integer,
	 * java.lang.Integer, java.lang.String)
	 */
	public Notificacionproduccion insertarNotificacionProduccion(Long codigoRegistroReporteECS, Long codigoNotificacionDiaria,
			Long codigoHora, Long codigoPuestoTrabajo, Date fechaRegistro, Integer codigoOrden, Integer codigoMedio, String obs,
			Boolean cambioProduccionNormal, Boolean cambioProduccionLavado, String cambioProduccionHora, Double energia,
			Double agua, Double horaEcs, Plantillaproducto plantillaproducto) throws LogicaException {
		//Obtengo Valores de otro Logic
		Notificaciondiaria notificaciondiaria = notificacionDiariaLogic
				.obtenerNotificacionDiariaDataObject(codigoNotificacionDiaria);

		Transaction tx = null;
		Session session = null;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Notificacionproduccion notificacionProduccion = new Notificacionproduccion();
			// Hora
			Hora hora = new Hora();
			hora.setPkCodigoHora(codigoHora);

			// RegistroReporte ECS
			if (codigoRegistroReporteECS != null) {
				Registroreporteecs registroreporteecs = new Registroreporteecs();
				registroreporteecs.setPkCodigoRegistroreporteecs(codigoRegistroReporteECS);
				notificacionProduccion.setRegistroreporteecs(registroreporteecs);
			}

			// Puesto de Trabajo
			Puestotrabajo puestotrabajo = new Puestotrabajo();
			puestotrabajo.setPkCodigoPuestotrabajo(codigoPuestoTrabajo);
			notificacionProduccion.setPuestotrabajo(puestotrabajo);

			// Orden
			Ordenproduccion orden = new Ordenproduccion();
			orden.setPkCodigoOrdenproduccion(new Long(codigoOrden));
			notificacionProduccion.setOrdenproduccion(orden);

			// Notificacion Diaria

			notificacionProduccion.setNotificaciondiaria(notificaciondiaria);
			notificacionProduccion.setHora(hora);

			// Medio
			if (codigoMedio != null) {
				Medioalmacenamiento medio = new Medioalmacenamiento();
				medio.setPkCodigoMedioalmacenamiento(new Long(codigoMedio));
				notificacionProduccion.setMedioalmacenamiento(medio);
			}

			// Observacion
			notificacionProduccion.setObservacionNotificacionproducc(obs);

			notificacionProduccion.setCambioproduccionNotificacionpr(cambioProduccionNormal);
			notificacionProduccion.setProduccionlavadoNotificacionpr(cambioProduccionLavado);
			notificacionProduccion.setHoraCambioproduccionNotificac(cambioProduccionHora);

			// Fecha
			notificacionProduccion.setFechaNotificacionproduccion(fechaRegistro);

			notificacionProduccion.setEnergiaEcsNotificacionproduccion(energia);
			notificacionProduccion.setAguaEcsNotificacionproduccion(agua);
			notificacionProduccion.setHoraEcsNotificacionproduccion(horaEcs);

			notificacionProduccion.setPlantillaproducto(plantillaproducto);

			NotificacionProduccionQuerier.save(notificacionProduccion);
			tx.commit();

			return notificacionProduccion;
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoEliminadoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ConstraintViolationException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VIOLACION_INTEGRIDAD_BD), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public void crearComponentesNotificacion(Notificacionproduccion notificacionProduccion, DatoReporteDTO datoreporteDTO,
			List<Columnaplantillaproducto> listaColumnaPlantillaProducto) throws LogicaException {

		Session session = null;
		Transaction tx = null;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Map<Long, Componente> mapaComponentesHRActiva = new HashMap<Long, Componente>();

			if (listaColumnaPlantillaProducto != null && listaColumnaPlantillaProducto.size() > 0) {
				List<Componente> componentesHojaActiva = HojaRutaComponenteQuerier
						.obtenerComponentesPorProductoSegunHojaRuta(listaColumnaPlantillaProducto.get(0).getPlantillaproducto()
								.getProducto().getPkCodigoProducto());

				for (Componente componente : componentesHojaActiva) {
					mapaComponentesHRActiva.put(componente.getPkCodigoComponente(), componente);
				}
			} else {
				return;
			}

			Long puestoTrabajo = notificacionProduccion.getPuestotrabajo().getPkCodigoPuestotrabajo();
			Boolean vCalentamiento = false;

			ParametroSistema parametroSistema = ParametroSistemaQuerier
					.obtenerParametroSistema(ConstantesParametro.PUESTO_TRABAJO_KCAL);
			if (parametroSistema != null) {
				String[] parametroPuestoTrabajo = parametroSistema.getValorParametro().split(",");
				for (String codPuestoTrabajo : parametroPuestoTrabajo) {
					if (puestoTrabajo == Long.parseLong(codPuestoTrabajo)) {
						vCalentamiento = true;
					}
				}
			} else {
				logger.error("No se encontrÃ³ registros en el parametroSistema PUESTO_TRABAJO_KCAL");
			}

			Double componentes = 0d;
			Double combustibles = 0d;
			for (Iterator<Columnaplantillaproducto> iterator = listaColumnaPlantillaProducto.iterator(); iterator.hasNext();) {
				Columnaplantillaproducto columnapp = iterator.next();
				Componente componente = columnapp.getComponente();

				if (mapaComponentesHRActiva.get(componente.getPkCodigoComponente()) == null) {
					// El componente pertenece a una HR inactiva
					continue;
				}

				Double proporcion = columnapp.getProporcionColumnareporte();

				Short posicionColumnareporte = columnapp.getColumnareporte().getPosicionColumnareporte();

				Double valorVariable = obtenerValorDeVariableSegunValorColumna(datoreporteDTO, posicionColumnareporte);

				if (valorVariable != null) {
					Componentenotificacion componentenotificacion = new Componentenotificacion();
					Double valorComponentenotificacion = 0d;
					if (proporcion == 100) {
						valorComponentenotificacion = valorVariable;
					} else {
						valorComponentenotificacion = (proporcion * valorVariable) / 100;
					}

					if (vCalentamiento) {
						Tipocategoriaproducto tipocategoria = componente.getProductoByFkCodigoProductoComponente()
								.getTipocategoriaproducto();

						if (tipocategoria == null) {
							componentes += valorComponentenotificacion;
						} else if (tipocategoria != null
								&& tipocategoria.getPkCodigoTipocategoriaproducto().compareTo(Long.valueOf(1)) != 0) {
							componentes += valorComponentenotificacion;
						} else {
							combustibles += valorComponentenotificacion;
						}
					}

					componentenotificacion.setValorComponentenotificacion(valorComponentenotificacion);
					componentenotificacion.setComponente(componente);
					componentenotificacion.setNotificacionproduccion(notificacionProduccion);

					ComponenteNotificacionQuerier.save(componentenotificacion);
				}
			}

			if (vCalentamiento) {
				if (componentes > 0d) {
					// No es en calentamiento False
					notificacionProduccion.setEscalentamientoNotificacionpr(Boolean.FALSE);
				} else if (componentes == 0d && combustibles > 0d) {
					// Calentamiento TRUE
					notificacionProduccion.setEscalentamientoNotificacionpr(Boolean.TRUE);
				}
				NotificacionProduccionQuerier.saveOrUpdate(notificacionProduccion);
			}
			tx.commit();
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e);
		} catch (ElementoEliminadoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e);
		} catch (ElementoNoEncontradoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e);
		} catch (ConstraintViolationException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Obtiene el valor de la variable de dator reporte que coresponde con la
	 * columna asociada del reporte ecs
	 * 
	 * @param datoreporteDTO
	 * @param posicion
	 * @param valorVariable
	 * @return Double
	 */
	public Double obtenerValorDeVariableSegunValorColumna(DatoReporteDTO datoreporteDTO, Short posicion) {
		Double valorVariable = null;
		switch (posicion) {
		case 1:
			valorVariable = datoreporteDTO.getVariable1Datoreporte();
			break;
		case 2:
			valorVariable = datoreporteDTO.getVariable2Datoreporte();
			break;
		case 3:
			valorVariable = datoreporteDTO.getVariable3Datoreporte();
			break;
		case 4:
			valorVariable = datoreporteDTO.getVariable4Datoreporte();
			break;
		case 5:
			valorVariable = datoreporteDTO.getVariable5Datoreporte();
			break;
		case 6:
			valorVariable = datoreporteDTO.getVariable6Datoreporte();
			break;
		case 7:
			valorVariable = datoreporteDTO.getVariable7Datoreporte();
			break;
		case 8:
			valorVariable = datoreporteDTO.getVariable8Datoreporte();
			break;
		case 9:
			valorVariable = datoreporteDTO.getVariable9Datoreporte();
			break;
		case 10:
			valorVariable = datoreporteDTO.getVariable10Datoreporte();
			break;
		case 11:
			valorVariable = datoreporteDTO.getVariable11Datoreporte();
			break;
		case 12:
			valorVariable = datoreporteDTO.getVariable12Datoreporte();
			break;
		case 13:
			valorVariable = datoreporteDTO.getVariable13Datoreporte();
			break;
		case 14:
			valorVariable = datoreporteDTO.getVariable14Datoreporte();
			break;
		case 15:
			valorVariable = datoreporteDTO.getVariable15Datoreporte();
			break;
		default:
			return null;
		}
		return valorVariable;
	}

	public boolean validaValoresIngresados(DatoReporteDTO datoreporteDTO) {
		int maximoDatoReporte = 15;
		Boolean contieneRegistros = Boolean.FALSE;
		for (short posicion = 0; posicion <= maximoDatoReporte; posicion++) {
			if (contieneRegistros) {
				break;
			}
			switch (posicion) {
			case 1:

				if (datoreporteDTO.getVariable1Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 2:

				if (datoreporteDTO.getVariable2Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 3:

				if (datoreporteDTO.getVariable3Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 4:

				if (datoreporteDTO.getVariable4Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 5:

				if (datoreporteDTO.getVariable5Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 6:

				if (datoreporteDTO.getVariable6Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 7:

				if (datoreporteDTO.getVariable7Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 8:

				if (datoreporteDTO.getVariable8Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 9:

				if (datoreporteDTO.getVariable9Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 10:

				if (datoreporteDTO.getVariable10Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 11:

				if (datoreporteDTO.getVariable11Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 12:

				if (datoreporteDTO.getVariable12Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 13:

				if (datoreporteDTO.getVariable13Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;
			case 14:

				if (datoreporteDTO.getVariable14Datoreporte() > 0.00) {
					contieneRegistros = Boolean.TRUE;
				}
				break;

			default:
				contieneRegistros = Boolean.FALSE;
			}
		}
		return contieneRegistros;

	}

	public List<Notificacionproduccion> obtenerNotificacionesProduccionByCodigoNotificacionDiaria(Long codigoNotificacionDiaria)
			throws LogicaException {

		String mensajeError = "";

		List<Notificacionproduccion> notificacionesProduccion = null;

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
			notificacionesProduccion = NotificacionProduccionQuerier.findByCodigoNotificacion(codigoNotificacionDiaria);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return notificacionesProduccion;
	}

	private Session crearSesion() throws SesionVencidaException, EntornoEjecucionException {
		try {
			return PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	private int buscarColumna(Long clave, ArrayList lista) {
		for (int x = 0; x < lista.size(); x++) {
			if (lista.get(x) == clave) {
				return x + 1;
			}
		}

		return 0;
	}

	@SuppressWarnings("unchecked")
	public NotDiariaProduccionRepBean obtenerDatosReporteNotificacionesProduccion(Date fechaInicio, Date fechaFin,
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad, Long codigoPuestoTrabajo, Long codigoProducto)
			throws LogicaException {

		ParametroSistemaBean parametroAgua = parametroSistemaLogicFacade
				.obtenerParametroSistema(ConstantesParametro.COLUMNAS_REPORTE_NOTIFICACION_AGUA);
		ParametroSistemaBean parametroECS = parametroSistemaLogicFacade
				.obtenerParametroSistema(ConstantesParametro.COLUMNAS_REPORTE_NOTIFICACION_HORAS_ECS);
		// se obtiene la sesion
		Session session = crearSesion();

		// lista de notificaciones para el reporte
		NotDiariaProduccionRepBean reporteNotificacionPlanta = new NotDiariaProduccionRepBean();
		DateFormat df = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);
		NotVarProduccionPuestoTrabajoRepBean notificacionPuestoTrabajo = null;
		List<NotVariablesProduccionHoraRepBean> listaNotificacionPuestoHora = null;

		String[] columnasAgua = null;
		String[] columnasECS = null;
		if (parametroAgua != null) {
			columnasAgua = parametroAgua.getValor().split("-");
		}

		if (parametroECS != null) {
			columnasECS = parametroECS.getValor().split("-");
		}

		try {
			// se buscan las notificaciones diarias de acuerdo a los parametros
			List<Notificaciondiaria> notificacionesDiarias = NotificacionDiariaQuerier.obtenerPorFechaYVariosFiltros(
					codigoUnidad, codigoSociedad, codigoDivision, fechaInicio, fechaFin, codigoPuestoTrabajo, codigoProducto);

			List<Puestotrabajo> puestostrabajos = NotificacionDiariaQuerier.obtenerPuestostrabajosPorFechaYVariosFiltros(
					codigoUnidad, codigoSociedad, codigoDivision, fechaInicio, fechaFin, codigoPuestoTrabajo, codigoProducto);

			// si no se encuentran notificaciones diarias para los parametros se
			// retorna la lista null
			if (notificacionesDiarias.isEmpty()) {
				return null;

			}

			/**
			 * <cabeceras>
			 */

			reporteNotificacionPlanta.setDivision(DivisionQuerier.getById(codigoDivision).getNombreDivision());
			reporteNotificacionPlanta.setSociedad(SociedadQuerier.getById(codigoSociedad).getNombreSociedad());
			reporteNotificacionPlanta.setUnidad(UnidadQuerier.getById(codigoSociedad).getNombreUnidad());
			if (codigoPuestoTrabajo == null)
				reporteNotificacionPlanta.setPuestoTrabajo("Todos");
			else
				reporteNotificacionPlanta.setPuestoTrabajo(PuestoTrabajoQuerier.getById(codigoPuestoTrabajo)
						.getNombrePuestotrabajo());
			if (codigoProducto == null)
				reporteNotificacionPlanta.setProductoA("Todos");
			else
				reporteNotificacionPlanta.setProductoA(ProductoQuerier.getById(codigoProducto).getNombreProducto());

			reporteNotificacionPlanta.setRango("Desde:" + df.format(fechaInicio) + "  Hasta:" + df.format(fechaFin));

			/**
			 * </cabeceras>
			 */

			// se usa un hashMap del codigo de puesto de trabajo y las
			// notificaciones de PuestoTrabajo para separar por puesto de
			// trabajo
			List<NotVarProduccionPuestoTrabajoRepBean> listNotPuesto = new ArrayList<NotVarProduccionPuestoTrabajoRepBean>();
			HashMap<Short, Integer> columnas = new HashMap<Short, Integer>();

			for (Iterator<Notificaciondiaria> notDiariaIterator = notificacionesDiarias.iterator(); notDiariaIterator.hasNext();) {

				// debe existir una notificacion diaria por dia
				Notificaciondiaria notificacionDiaria = notDiariaIterator.next();

				// se buscan los valores de los componentes/variables de
				// produccion por hora de los puestos de trabajo, son las
				// notificacion de produccion (1 por cada hora) ordenada por
				// puesto de trabajo, hora y turno
				List<Notificacionproduccion> notificacionesProduccion = null;
				notificacionesProduccion = NotificacionProduccionQuerier.obtenerPorNotificacionDiariaOrdenadoPuestoTurnoHora(
						notificacionDiaria.getPkCodigoNotificaciondiaria(), codigoPuestoTrabajo, codigoProducto);

				for (Puestotrabajo puestotrabajo2 : puestostrabajos) {
					codigoPuestoTrabajo = puestotrabajo2.getPkCodigoPuestotrabajo();

					EqualPredicate nameEqlPredicate = new EqualPredicate(codigoPuestoTrabajo);
					BeanPredicate beanPredicate = new BeanPredicate("puestotrabajo.pkCodigoPuestotrabajo", nameEqlPredicate);

					Collection<Notificacionproduccion> filteredCollection = CollectionUtils.select(notificacionesProduccion,
							beanPredicate);

					if (filteredCollection != null && !filteredCollection.isEmpty()) {

						// se construyen las filas del reporte recorriendo
						// notificacionProduccion
						listaNotificacionPuestoHora = new ArrayList<NotVariablesProduccionHoraRepBean>();
						NotVariablesProduccionHoraRepBean notificacionSubtotal = new NotVariablesProduccionHoraRepBean();
						notificacionSubtotal.setSilo("Sub Total");
						columnas.clear();
						for (Notificacionproduccion notificacionproduccion : filteredCollection) {

							notificacionPuestoTrabajo = new NotVarProduccionPuestoTrabajoRepBean();
							Puestotrabajo puestotrabajo = notificacionproduccion.getPuestotrabajo();
							Notificaciondiaria notificaciondiaria2 = notificacionproduccion.getNotificaciondiaria();
							notificacionPuestoTrabajo.setPuestoTrabajo(puestotrabajo.getNombrePuestotrabajo());
							notificacionPuestoTrabajo
									.setFecha(df.format(notificacionproduccion.getFechaNotificacionproduccion()));
							notificacionPuestoTrabajo.setFechaNotDiaria(df.format(notificaciondiaria2
									.getFechaNotificaciondiaria()));
							notificacionPuestoTrabajo.setEstado(notificaciondiaria2.getEstadonotificacion()
									.getNombreEstadonotificacion());
							notificacionPuestoTrabajo.setProducto(notificacionproduccion.getOrdenproduccion().getProduccion()
									.getProducto().getNombreProducto());

							List<Columnareporte> plantillasProducto = IteratorUtils.toList(notificacionproduccion
									.getPlantillaproducto().getPlantillareporte().getColumnareportes().iterator());

							int i = 1;

							Collections.sort(plantillasProducto, new Comparator<Columnareporte>() {

								public int compare(Columnareporte o1, Columnareporte o2) {
									int compareFechas = o1.getPosicionColumnareporte().compareTo(o2.getPosicionColumnareporte());
									return compareFechas;
								}
							});

							Short columnaAgua = (short) 12;
							Short columnaECS = (short) 15;

							for (Columnareporte columnareporte : plantillasProducto) {

								if (columnareporte.getEstadocolumnareporte().getPkCodigoEstadocolumnareporte().compareTo(1L) == 0
										&& !columnareporte.getNombreColumnareporte().equals("Hora")) {

									// .equals("agua")
									if (validarColumnaEspecificaAgua(columnareporte.getNombreColumnareporte(), columnasAgua)) {
										columnaAgua = columnareporte.getPosicionColumnareporte();
									}
									// .indexOf("horas de o") != 0
									// ||
									// columnareporte.getNombreColumnareporte().toLowerCase().indexOf("hrs
									// op") != 0

									if (validarColumnaEspecificaHoraECS(columnareporte.getNombreColumnareporte(), columnasECS)) {
										columnaECS = columnareporte.getPosicionColumnareporte();
									}
									asignarComponentes(i, columnareporte, notificacionPuestoTrabajo);
									columnas.put(columnareporte.getPosicionColumnareporte(), i);
									i++;

								}
							}

							Iterator<Columnaplantillaproducto> columnasPlantillas = notificacionproduccion.getPlantillaproducto()
									.getColumnaplantillaproductos().iterator();
							List<Columnaplantillaproducto> columnasPlantilla = IteratorUtils.toList(columnasPlantillas);

							NotVariablesProduccionHoraRepBean notificacionPuestoHora = new NotVariablesProduccionHoraRepBean();

							llenarNotificacionHora(notificacionPuestoHora, notificacionproduccion);
							Iterator<Componentenotificacion> itComponentes = notificacionproduccion.getComponentenotificacions()
									.iterator();

							for (Iterator<Componentenotificacion> componenteIterator = itComponentes; componenteIterator
									.hasNext();) {
								Componentenotificacion componentenotificacion = componenteIterator.next();

								for (Columnaplantillaproducto columnaplantillaproducto : columnasPlantilla) {

									if (componentenotificacion
											.getComponente()
											.getProductoByFkCodigoProductoComponente()
											.getPkCodigoProducto()
											.compareTo(
													columnaplantillaproducto.getComponente()
															.getProductoByFkCodigoProductoComponente().getPkCodigoProducto()) == 0) {
										Integer columna = columnas.get(columnaplantillaproducto.getColumnareporte()
												.getPosicionColumnareporte());
										asignarVariables(columna, componentenotificacion.getValorComponentenotificacion(),
												notificacionPuestoHora);
										asignarVariablesStatic(columna, componentenotificacion.getValorComponentenotificacion(),
												notificacionSubtotal);
									}
								}

							}
							// Columna Agua o Horas OP

							if (columnaAgua != null) {
								Integer columna = columnas.get(columnaAgua);
								if (columna != null) {

									asignarVariables(columna, notificacionproduccion.getAguaEcsNotificacionproduccion(),
											notificacionPuestoHora);
									asignarVariablesStatic(columna, notificacionproduccion.getAguaEcsNotificacionproduccion(),
											notificacionSubtotal);
								}
							}

							if (columnaECS != null) {

								Integer columna = columnas.get(columnaECS);
								if (columna != null) {

									asignarVariables(columna, notificacionproduccion.getHoraEcsNotificacionproduccion(),
											notificacionPuestoHora);

									asignarVariablesStatic(columna, notificacionproduccion.getHoraEcsNotificacionproduccion(),
											notificacionSubtotal);
								}
							}
							listaNotificacionPuestoHora.add(notificacionPuestoHora);

						}
						listaNotificacionPuestoHora.add(notificacionSubtotal);
						notificacionPuestoTrabajo.setVariablesProdHoras(listaNotificacionPuestoHora);
						listNotPuesto.add(notificacionPuestoTrabajo);

					}
				}

			}

			reporteNotificacionPlanta.setNotPuestoTrabajos(listNotPuesto);
		} catch (AplicacionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		if (reporteNotificacionPlanta.getNotPuestoTrabajos().isEmpty())
			return null;

		return reporteNotificacionPlanta;
	}

	private boolean validarColumnaEspecificaHoraECS(String nombreColumnareporte, String[] columnasECS) {
		if (columnasECS != null) {
			for (String nombreColumna : columnasECS) {
				if (nombreColumna.trim().toLowerCase().equals(nombreColumnareporte.trim().toLowerCase())) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean validarColumnaEspecificaAgua(String nombreColumnareporte, String[] columnasAgua) {
		if (columnasAgua != null) {
			for (String nombreColumna : columnasAgua) {
				if (nombreColumna.trim().toLowerCase().equals(nombreColumnareporte.trim().toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public void llenarNotificacionHora(NotVariablesProduccionHoraRepBean notificacionPuestoHora,
			Notificacionproduccion notificacionproduccion) {
		notificacionPuestoHora.setHora(notificacionproduccion.getHora().getHoraHora().toString());

		notificacionPuestoHora.setOrdenProduccion(notificacionproduccion.getOrdenproduccion().getNumeroOrdenOrdenproduccion()
				+ "-" + notificacionproduccion.getOrdenproduccion().getProduccion().getProducto().getPkCodigoProducto());

		notificacionPuestoHora.setProducto(notificacionproduccion.getOrdenproduccion().getProduccion().getProducto()
				.getNombreProducto());
		if (notificacionproduccion.getMedioalmacenamiento() != null) {
			notificacionPuestoHora.setSilo(notificacionproduccion.getMedioalmacenamiento().getNombreMedioalmacenamiento());
		}
	}

	public void asignarComponentes(int posicion, Columnareporte columnareporte,
			NotVarProduccionPuestoTrabajoRepBean notPuestoTrabajo) {
		switch (posicion) {
		case 1:

			if (notPuestoTrabajo.getComponente1() == null)
				notPuestoTrabajo.setComponente1(columnareporte.getNombreColumnareporte());

			break;
		case 2:

			if (notPuestoTrabajo.getComponente2() == null)
				notPuestoTrabajo.setComponente2(columnareporte.getNombreColumnareporte());
			break;
		case 3:

			if (notPuestoTrabajo.getComponente3() == null)
				notPuestoTrabajo.setComponente3(columnareporte.getNombreColumnareporte());
			break;
		case 4:

			if (notPuestoTrabajo.getComponente4() == null)
				notPuestoTrabajo.setComponente4(columnareporte.getNombreColumnareporte());
			break;
		case 5:

			if (notPuestoTrabajo.getComponente5() == null)
				notPuestoTrabajo.setComponente5(columnareporte.getNombreColumnareporte());
			break;
		case 6:

			if (notPuestoTrabajo.getComponente6() == null)
				notPuestoTrabajo.setComponente6(columnareporte.getNombreColumnareporte());
			break;
		case 7:

			if (notPuestoTrabajo.getComponente7() == null)
				notPuestoTrabajo.setComponente7(columnareporte.getNombreColumnareporte());
			break;
		case 8:

			if (notPuestoTrabajo.getComponente8() == null)
				notPuestoTrabajo.setComponente8(columnareporte.getNombreColumnareporte());
			break;
		case 9:

			if (notPuestoTrabajo.getComponente9() == null)
				notPuestoTrabajo.setComponente9(columnareporte.getNombreColumnareporte());
			break;
		case 10:

			if (notPuestoTrabajo.getComponente10() == null)
				notPuestoTrabajo.setComponente10(columnareporte.getNombreColumnareporte());
			break;
		case 11:

			if (notPuestoTrabajo.getComponente11() == null)
				notPuestoTrabajo.setComponente11(columnareporte.getNombreColumnareporte());
			break;
		case 12:

			if (notPuestoTrabajo.getComponente12() == null)
				notPuestoTrabajo.setComponente12(columnareporte.getNombreColumnareporte());
			break;
		case 13:

			if (notPuestoTrabajo.getComponente13() == null)
				notPuestoTrabajo.setComponente13(columnareporte.getNombreColumnareporte());
			break;
		case 14:

			if (notPuestoTrabajo.getComponente14() == null)
				notPuestoTrabajo.setComponente14(columnareporte.getNombreColumnareporte());
			break;
		case 15:

			if (notPuestoTrabajo.getComponente15() == null)
				notPuestoTrabajo.setComponente15(columnareporte.getNombreColumnareporte());
			break;
		default:
			break;
		}
	}

	public void asignarVariables(int posicion, Double valorComponenteNotificacion,
			NotVariablesProduccionHoraRepBean notificacionPuestoHora) {
		switch (posicion) {
		case 1:
			notificacionPuestoHora.setVariable1Datoreporte(valorComponenteNotificacion);

			break;
		case 2:
			notificacionPuestoHora.setVariable2Datoreporte(valorComponenteNotificacion);

			break;
		case 3:
			notificacionPuestoHora.setVariable3Datoreporte(valorComponenteNotificacion);

			break;
		case 4:
			notificacionPuestoHora.setVariable4Datoreporte(valorComponenteNotificacion);

			break;
		case 5:
			notificacionPuestoHora.setVariable5Datoreporte(valorComponenteNotificacion);

			break;
		case 6:
			notificacionPuestoHora.setVariable6Datoreporte(valorComponenteNotificacion);

			break;
		case 7:
			notificacionPuestoHora.setVariable7Datoreporte(valorComponenteNotificacion);

			break;
		case 8:
			notificacionPuestoHora.setVariable8Datoreporte(valorComponenteNotificacion);

			break;
		case 9:
			notificacionPuestoHora.setVariable9Datoreporte(valorComponenteNotificacion);

			break;
		case 10:
			notificacionPuestoHora.setVariable10Datoreporte(valorComponenteNotificacion);

			break;
		case 11:
			notificacionPuestoHora.setVariable11Datoreporte(valorComponenteNotificacion);

			break;
		case 12:
			notificacionPuestoHora.setVariable12Datoreporte(valorComponenteNotificacion);

			break;
		case 13:
			notificacionPuestoHora.setVariable13Datoreporte(valorComponenteNotificacion);

			break;
		case 14:
			notificacionPuestoHora.setVariable14Datoreporte(valorComponenteNotificacion);

			break;
		case 15:
			notificacionPuestoHora.setVariable15Datoreporte(valorComponenteNotificacion);

			break;
		default:
			break;
		}
	}

	private void asignarVariablesStatic(int posicion, Double valor, NotVariablesProduccionHoraRepBean notificacionPuestoHora) {
		Double sumacolumna = 0.0;
		switch (posicion) {
		case 1:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable1Datoreporte(), valor);
			notificacionPuestoHora.setVariable1Datoreporte(sumacolumna);

			break;
		case 2:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable2Datoreporte(), valor);
			notificacionPuestoHora.setVariable2Datoreporte(sumacolumna);
			break;
		case 3:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable3Datoreporte(), valor);
			notificacionPuestoHora.setVariable3Datoreporte(sumacolumna);
			break;
		case 4:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable4Datoreporte(), valor);
			notificacionPuestoHora.setVariable4Datoreporte(sumacolumna);
			break;
		case 5:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable5Datoreporte(), valor);
			notificacionPuestoHora.setVariable5Datoreporte(sumacolumna);
			break;
		case 6:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable6Datoreporte(), valor);
			notificacionPuestoHora.setVariable6Datoreporte(sumacolumna);
			break;
		case 7:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable7Datoreporte(), valor);
			notificacionPuestoHora.setVariable7Datoreporte(sumacolumna);
			break;
		case 8:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable8Datoreporte(), valor);
			notificacionPuestoHora.setVariable8Datoreporte(sumacolumna);
			break;
		case 9:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable9Datoreporte(), valor);
			notificacionPuestoHora.setVariable9Datoreporte(sumacolumna);
			break;
		case 10:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable10Datoreporte(), valor);
			notificacionPuestoHora.setVariable10Datoreporte(sumacolumna);
			break;
		case 11:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable11Datoreporte(), valor);
			notificacionPuestoHora.setVariable11Datoreporte(sumacolumna);
			break;
		case 12:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable12Datoreporte(), valor);
			notificacionPuestoHora.setVariable12Datoreporte(sumacolumna);
			break;
		case 13:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable13Datoreporte(), valor);
			notificacionPuestoHora.setVariable13Datoreporte(sumacolumna);
			break;
		case 14:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable14Datoreporte(), valor);
			notificacionPuestoHora.setVariable14Datoreporte(sumacolumna);
			break;
		case 15:
			sumacolumna = NumberUtil.sumar(notificacionPuestoHora.getVariable15Datoreporte(), valor);
			notificacionPuestoHora.setVariable15Datoreporte(sumacolumna);
			break;
		default:
			break;
		}
	}

	public ResumenNotificacionRepBean obtenerDatosReporteResumenNotificacionorFecha(Long codigoDivision, Long codigoSociedad,
			Long codigoUnidad, Long codigoPuestoTrabajo, Long codigoProducto, String fechaInicio, String fechaFin)
			throws LogicaException {

		// se obtiene la sesion
		Session session = crearSesion();

		try {
			if (fechaFin == null || fechaFin.trim().length() == 0) {
				fechaFin = fechaInicio;
			}

			Calendar fechaInicioCal = Calendar.getInstance();
			Calendar fechaFinCal = Calendar.getInstance();
			fechaInicioCal.set(Integer.parseInt(fechaInicio.split("/")[2]), Integer.parseInt(fechaInicio.split("/")[1]) - 1,
					Integer.parseInt(fechaInicio.split("/")[0]));
			fechaFinCal.set(Integer.parseInt(fechaFin.split("/")[2]), Integer.parseInt(fechaFin.split("/")[1]) - 1,
					Integer.parseInt(fechaFin.split("/")[0]));

			String fechaI = FechaUtil.convertirDateToString(fechaInicioCal.getTime(), FechaUtil.PATRON_FECHA_BD);
			String fechaF = FechaUtil.convertirDateToString(fechaFinCal.getTime(), FechaUtil.PATRON_FECHA_BD);

			// Se inicializan los beans para el Reporte
			ResumenNotificacionRepBean resumenBean = new ResumenNotificacionRepBean();

			// Se busca la informacion a colocar en el encabezado del reporte
			resumenBean.setDivision(DivisionQuerier.getById(codigoDivision).getNombreDivision());
			resumenBean.setSociedad(SociedadQuerier.getById(codigoSociedad).getNombreSociedad());
			resumenBean.setUnidad(UnidadQuerier.getById(codigoSociedad).getNombreUnidad());
			resumenBean.setPuestoTrabajo(PuestoTrabajoQuerier.getById(codigoPuestoTrabajo).getNombrePuestotrabajo());
			if (codigoProducto != null) {
				resumenBean.setProducto(ProductoQuerier.getById(codigoProducto).getNombreProducto());
			}

			resumenBean.setRango("Desde: " + fechaInicio + "  Hasta: " + fechaFin);

			// Obtenemos la lista de Items a mostrar en el Reporte
			List<ResumenNotificacionItemRepBean> listaResumenItems = NotificacionProduccionQuerier
					.obtenerResumenItemsNotificacion(codigoPuestoTrabajo, codigoProducto, fechaI, fechaF);

			// Si no se encuentran items para los parametros, retorna la lista
			// null
			if (listaResumenItems.isEmpty()) {
				return null;
			}

			resumenBean.setReporteItems(listaResumenItems);

			return resumenBean;

		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public List<NotificacionProduccionBean> obtenerNotificacionesProduccion(Long valorLineaNegocio, Long valorEstadoNotificacion,
			Long valorPuestoTrabajo, Date fechaInicio, Date fechaFin) throws LogicaException {
		List<NotificacionProduccionBean> notificacionesProduccionBean = null;
		Session session = crearSesion();
		try {
			List<Object[]> notificacionesProduccion = NotificacionProduccionQuerier.obtenerPorNotificacionProduccionPorFiltros(
					valorLineaNegocio, valorEstadoNotificacion, valorPuestoTrabajo, fechaInicio, fechaFin);
			NotificacionProduccionBean notificacionProduccionBean;
			notificacionesProduccionBean = new ArrayList<NotificacionProduccionBean>();
			for (Object[] objects : notificacionesProduccion) {
				notificacionProduccionBean = new NotificacionProduccionBeanImpl();

				notificacionProduccionBean.setPuestoTrabajo(beanFactory.transformarPuestoTrabajo((Puestotrabajo) objects[0]));
				notificacionProduccionBean.setNotificacionDiaria(beanFactory
						.transformarNotificacionDiariaSimple((Notificaciondiaria) objects[1]));

				notificacionesProduccionBean.add(notificacionProduccionBean);
			}
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return notificacionesProduccionBean;
	}

	public boolean eliminarPorCodigoNotificacionPuestoTrabajo(Long codigoNotificacion, Long valorPuestoTrabajo,
			UsuarioBean usuario) throws LogicaException {
		Session session = null;
		Transaction tx = null;
		Boolean operacion = Boolean.FALSE;
		NotificacionDiariaBean notificacion;
		String mensajeError;
		try {
			notificacion = notificacionDiariaLogic.obtenerNotificacionDiaria(codigoNotificacion);
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			// Si el estado es cerrado
			if (notificacion != null && notificacion.getEstadoNotificacion().getNombreEstadoNotificacion().equals(ESTADO_CERRADO)) {
				mensajeError = MessageFormat.format(ERROR_NOTIFICACION_CERRADO,
						new Object[] { notificacion.getFechaNotificacionDiaria() });
				throw new LogicaException(mensajeError);
			}

			if (notificacion != null
					&& notificacion.getEstadoNotificacion().getNombreEstadoNotificacion().equals(ESTADO_APROBADO)) {
				throw new LogicaException(ERROR_NOTIFICACION_APROBADA);
			}

			List<Notificacionproduccion> notificacionesProduccion = NotificacionProduccionQuerier.obtenerPorNotifPuestoTrab(
					codigoNotificacion, valorPuestoTrabajo);

			for (Notificacionproduccion notificacionproduccion : notificacionesProduccion) {
				NotificacionProduccionQuerier.delete(notificacionproduccion);
			}
			tx.commit();
			operacion = Boolean.TRUE;
		} catch (ParametroInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoEliminadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			logger.error(e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	
		return operacion;
	}

	public ReporteDosificacion generarReporteDosificacion(Long valorUnidad, Long valorProceso, Long valorProducto,
			Long valorPuestoTrabajo, String fechaInicio, String fechaFin) throws LogicaException {

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
		
		Date fechaInicial;
		Date fechaFinal;
		ReporteDosificacion reporteDosificacion = null;
		try {
			if (fechaInicio == null) {
				return reporteDosificacion;
			}
			if (fechaFin == null) {
				fechaFin = fechaInicio;
			}
			fechaInicial = FechaUtil.FormaterFecha(fechaInicio);
			fechaFinal = FechaUtil.FormaterFecha(fechaFin);
			reporteDosificacion = new ReporteDosificacion();
			int anno = FechaUtil.obtenerAnnioFecha(fechaInicial);
			int mes = (FechaUtil.obtenerMesFecha(fechaInicial) - 1);

			List<Productogenerado> productosGenerados = ProductoGeneradoQuerier.obtenerProductoGeneradoPorVarioFiltros(
					valorPuestoTrabajo, valorProceso, valorProducto, anno, mes, fechaFinal, fechaInicial);

			reporteDosificacion.setDetallereporte(generarReporteDosificacion(productosGenerados, fechaInicial, fechaFinal));
			reporteDosificacion.setUnidad(UnidadQuerier.getById(valorUnidad).getNombreUnidad());
			String TODAS = "Todas";
			if (valorProducto != null) {
				reporteDosificacion.setProducto(ProductoQuerier.getById(valorProducto).getNombreProducto());
			} else {
				reporteDosificacion.setProducto(TODAS);
			}
			if (valorPuestoTrabajo != null) {
				reporteDosificacion.setPuestoTrabajo(PuestoTrabajoQuerier.getById(valorPuestoTrabajo).getNombrePuestotrabajo());
			} else {
				reporteDosificacion.setPuestoTrabajo(TODAS);
			}

			reporteDosificacion.setPeriodo(FechaUtil.numeroMesANombreMes((short) (mes + 1)) + "-" + anno);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return reporteDosificacion;
	}

	private List<Long> obtenerCodigosParametros(String parametro) {

		ArrayList<Long> paramList = new ArrayList<Long>();
		try {
			ParametroSistemaBean parametrosBean = parametroSistemaLogicFacade.obtenerParametroSistemaDAO(parametro);

			if (parametrosBean != null) {
				String[] parametros = parametrosBean.getValor().split("-");
				for (String param : parametros) {
					paramList.add(Long.valueOf(param));
				}
			}
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return paramList;
	}

	private List<SubReporteDosificacion> generarReporteDosificacion(List<Productogenerado> productosGenerados, Date fechainicio,
			Date fechafin) {

		List<Long> clinkers;
		List<Long> bunkers;
		List<Long> procesos;

		clinkers = obtenerCodigosParametros(ConstantesParametro.PRODUCTOS_CLINKER);
		bunkers = obtenerCodigosParametros(ConstantesParametro.PRODUCTOS_BUNKER);
		procesos = obtenerCodigosParametros(ConstantesParametro.PROCESOS_VALIDACION_REPORTE_DOSIFICACION);

		Map<String, List<Productogenerado>> mapaProductoGenerados = new HashMap<String, List<Productogenerado>>();
		String key = "";
		List<Productogenerado> productosGeneradoslista;

		for (Productogenerado productogenerado : productosGenerados) {
			Produccion produccion = productogenerado.getOrdenproduccion().getProduccion();
			Produccionpuestotrabajo produccionpuestotrabajo = productogenerado.getTablaoperacion().getProduccionpuestotrabajo();
			key = produccion.getProceso().getPkCodigoProceso() + "@";
			key += produccion.getProceso().getNombreProceso() + "@";
			key += produccion.getProceso().getOrdenEjecucionProceso() + "@";
			key += produccion.getProducto().getPkCodigoProducto() + "@";
			key += produccion.getProducto().getNombreProducto() + "@";
			key += produccionpuestotrabajo.getPuestotrabajo().getPkCodigoPuestotrabajo() + "@";
			key += produccionpuestotrabajo.getPuestotrabajo().getNombrePuestotrabajo();
			if (mapaProductoGenerados.containsKey(key)) {
				productosGeneradoslista = mapaProductoGenerados.get(key);
				productosGeneradoslista.add(productogenerado);

			} else {
				productosGeneradoslista = new ArrayList<Productogenerado>();
				productosGeneradoslista.add(productogenerado);
				mapaProductoGenerados.put(key, productosGeneradoslista);
			}
		}

		ArrayList<String> keys = new ArrayList<String>(mapaProductoGenerados.keySet());
		Long proceso;
		Long producto;
		Long puestotrabajo;
		List<Object[]> columnasComponentes;
		List<SubReporteDosificacion> subReporteDosificacion = new ArrayList<SubReporteDosificacion>();
		Boolean esprocesoSeleccionado = false;
		Integer validacionClinker = 0;
		String[] codigos;
		int columna;
		Map<String, Integer> columnaComponente;
		SubReporteDosificacion sunReporteDosificacion;
		for (String string : keys) {
			codigos = string.split("@");
			proceso = Long.valueOf(codigos[0]);
			producto = Long.valueOf(codigos[3]);
			puestotrabajo = Long.valueOf(codigos[5]);

			if (procesos.contains(proceso)) {
				esprocesoSeleccionado = true;
			}

			columnasComponentes = ComponenteNotificacionQuerier.obtenerNombresComponentesSegunProcesoProductoPuesto(proceso,
					producto, puestotrabajo, fechainicio, fechafin);

			productosGeneradoslista = mapaProductoGenerados.get(string);
			sunReporteDosificacion = new SubReporteDosificacion();
			sunReporteDosificacion.setColumna1("DÃ�A");
			sunReporteDosificacion.setColumna2("TM");
			sunReporteDosificacion.setColumna3("Horas");
			sunReporteDosificacion.setColumna4("TMPH");
			columna = 5;
			columnaComponente = new HashMap<String, Integer>();

			// Ordenar

			int index = 0;
			for (Object[] columnas : columnasComponentes) {
				if (bunkers.contains(Long.valueOf(columnas[0] + ""))) {

					if (index != 0) {
						Collections.swap(columnasComponentes, 0, index);
					}

				}
				index++;
			}
			// -----
			for (Object[] columnas : columnasComponentes) {
				setPropiedad(sunReporteDosificacion, "columna" + columna, columnas[1]);
				columnaComponente.put(columnas[1] + "", columna);
				if (esprocesoSeleccionado) {
					if (clinkers.contains(Long.valueOf(columnas[0] + ""))) {
						validacionClinker++;
					}
				}
				columna++;
			}

			if (validacionClinker > 1) {
				setPropiedad(sunReporteDosificacion, "columna" + columna, "CLINKER");
				columnaComponente.put("CLINKER", columna);
			}

			Collections.sort(productosGeneradoslista, new Comparator<Productogenerado>() {
				public int compare(Productogenerado o1, Productogenerado o2) {
					return o1.getTablaoperacion().getFechaTablaoperacion()
							.compareTo(o2.getTablaoperacion().getFechaTablaoperacion());
				}

			});

			

			DetalleReporteDosificacion reportedetalle;
			List<DetalleReporteDosificacion> detalle = new ArrayList<DetalleReporteDosificacion>();

			Double acumCLinker = 0d;
			for (Productogenerado productogenerado : productosGeneradoslista) {
				reportedetalle = new DetalleReporteDosificacion();
				acumCLinker = 0d;

				reportedetalle.setColumna1(FechaUtil.obtenerDiaFecha(productogenerado.getTablaoperacion()
						.getFechaTablaoperacion()));
				reportedetalle.setColumna2(NumberUtil.Redondear2Decimales(productogenerado.getProduccionTmProductogenerado()));
				reportedetalle.setColumna3(NumberUtil.Redondear2Decimales(productogenerado.getHorasProductogenerado()));

				reportedetalle.setValor2(NumberUtil.Redondear2Decimales(productogenerado.getProduccionTmProductogenerado()));
				reportedetalle.setValor3(NumberUtil.Redondear2Decimales(productogenerado.getHorasProductogenerado()));

				reportedetalle.setColumna4(NumberUtil.dividir(productogenerado.getProduccionTmProductogenerado(),
						productogenerado.getHorasProductogenerado()));
				List<Object[]> componentes = ConsumoPuestoTrabajoQuerier.obtenerComponentesSegunProcesoProductoPuesto(proceso,
						producto, puestotrabajo, productogenerado.getTablaoperacion().getFechaTablaoperacion());
				for (Object[] componentenotificacion : componentes) {
					String nombreComponente = componentenotificacion[1] + "";
					Integer columnaC = columnaComponente.get(nombreComponente);

					if (columnaC == null) {
						continue;
					}

					setPropiedad(reportedetalle, "columna" + columnaC, NumberUtil.dividirSinRedondear(
							NumberUtil.convertirObjectToDouble(componentenotificacion[2]),
							productogenerado.getProduccionTmProductogenerado()));
					setPropiedad(reportedetalle, "valor" + columnaC,
							NumberUtil.Redondear2Decimales(NumberUtil.convertirObjectToDouble(componentenotificacion[2])));

					if (esprocesoSeleccionado) {
						if (clinkers.contains(Long.valueOf(componentenotificacion[0] + ""))) {
							acumCLinker += NumberUtil.convertirObjectToDouble(componentenotificacion[2]);
						}
					}

				}

				if (esprocesoSeleccionado) {
					Integer columnaC = columnaComponente.get("CLINKER");
					if (columnaC != null) {
						setPropiedad(reportedetalle, "columna" + columnaC,
								NumberUtil.dividirSinRedondear(acumCLinker, productogenerado.getProduccionTmProductogenerado()));
						setPropiedad(reportedetalle, "valor" + columnaC, NumberUtil.Redondear2Decimales(acumCLinker));
					}
				}

				detalle.add(reportedetalle);
			}

			sunReporteDosificacion.setDetalleReporteDosificacion(detalle);
			subReporteDosificacion.add(sunReporteDosificacion);

		}
		return subReporteDosificacion;

	}

	private void setPropiedad(Object obj, String atributo, Object valor) {
		try {
			BeanUtils.setProperty(obj, atributo, valor);
		} catch (IllegalAccessException e) {
			// TODO Manejo de Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}

	}

}
