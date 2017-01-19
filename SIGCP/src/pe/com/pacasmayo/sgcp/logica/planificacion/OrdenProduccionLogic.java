package pe.com.pacasmayo.sgcp.logica.planificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionLogic.java
 * Modificado: Feb 2, 2010 2:40:05 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaBean;
import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteRegistroOrdenBean;
import pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProdConsultaBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.RecursoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ErrorConexionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.OrdenProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadoperativa;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadoperativaregistromensu;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Conceptomensual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Conceptoregistromensual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocapacidadmanual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocapacidadplan;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponentemanual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteplan;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumorecursomanual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumorecursoplan;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Dosificacionregistromensual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoplananual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojaruta;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccionmanual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccionplan;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plananual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Recurso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Recursoregistromensual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocapacidadoperativa;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.CapacidadOperativaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.CapacidadOperativaRegistroMensuQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConceptoMensualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoCapacidadManualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoComponenteManualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoRecursoManualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoRecursoPlanQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.DivisionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoOrdenProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoPlanAnualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.LineaNegocioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OperacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenProduccionManualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenProduccionPlanQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlanAnualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProcesoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.RecursoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.SociedadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoCapacidadOperativaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProduccionDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
//import pe.com.pacasmayo.sgcp.util.ConvertidorHibernateDTO;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class OrdenProduccionLogic implements ConstantesMensajeAplicacion,
		ConstantesLogicaNegocio, ConstantesAplicacionAction,
		ConstantesMensajePresentacion, OrdenProduccionLogicFacade {

	public static final Long CODIGO_TIPO_CAPACIDAD_DIAS = new Long(2);
	public static final Long CODIGO_TIPO_CAPACIDAD_TONS = new Long(1);

	private Log logger = LogFactory.getLog(this.getClass());
	private BeanFactory beanFactory;
	private BeandozerFactory beanDozerFactory;

	public OrdenProduccionLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
		this.beanDozerFactory = BeandozerFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #generarOrdenProduccionAutomatica
	 * (pe.com.pacasmayo.sgcp.bean.PlanAnualBean,
	 * pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void generarOrdenProduccionAutomatica(PlanAnualBean planAnualBeans,
			UsuarioBean usuario) throws LogicaException {

		String mensajeError = "";
		Session session = null;
		Transaction tx = null;
		Plananual planAnual = null;

		int cantidadMes = CANTIDAD_DE_MESES;

		java.util.Calendar fecha = java.util.Calendar.getInstance();
		Date fechaRegistroOrdenproduccion = new Date(fecha.getTimeInMillis());
		Date fechaAprobacionOrdenproduccio = new Date(fecha.getTimeInMillis());

		// Se obtiene el Plan Anual en la BD
		try {
			planAnual = PlanAnualQuerier.getById(planAnualBeans.getCodigo());
			Usuario usuarioReg = planAnual
					.getUsuarioByFkCodigoUsuarioRegistra();
			Usuario usuarioApr = UsuarioQuerier.getById(usuario.getCodigo());
			Estadoordenproduccion estadoOrdenProd = EstadoOrdenProduccionQuerier
					.getById(CODIGO_POR_DEFECTO_ESTADO_ORDEN_PRODUCCION);

			planAnual.setUsuarioByFkCodigoUsuarioAprueba(usuarioApr);
			planAnual
					.setFechaAprobacionPlananual(fechaAprobacionOrdenproduccio);

			DateFormat df = new SimpleDateFormat(
					FechaUtil.PATRON_FECHA_APLICACION);
			String fechaStr = df.format(planAnual.getFechaRegistroPlananual());
			planAnual.setFechaRegistroPlananual(df.parse(fechaStr));

			// usuarioReg = planAnual.getUsuarioByFkCodigoUsuarioRegistra();
			// se valida el mes de corte del plan
			if (!StringUtils.equals(planAnual.getVersionPlananual(),
					ManejadorPropiedades
							.obtenerPropiedadPorClave(VERSION_INICIAL))) {
				if (planAnual.getMesCorteVersionPlananual() <= 0) {
					mensajeError = ManejadorPropiedades
							.obtenerPropiedadPorClave(ERROR_MES_CORTE_INVALIDO);
					logger.error(mensajeError);
					throw new LogicaException(mensajeError);
				}
			}

			if (!(planAnual.getVersionPlananual().compareTo(
					VERSION_INICIAL_PLAN) == 0)) {
				cantidadMes = CANTIDAD_DE_MESES
						- planAnual.getMesCorteVersionPlananual();
			}

			// Se setea el mes de corte del plan anual
			Short MES_CORTE = planAnual.getMesCorteVersionPlananual();

			// Se validan los datos del Plan Anual y las Ordenes Autom�ticas
			validarDatosPlanAnualOrdenesAutomaticas(planAnual);

			// gestión de sesión
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			// se obtienen las hojas de rutas asociadas al plan anual
			List<Hojaruta> hojaRutaPlanAnual = HojaRutaQuerier
					.obtenerPlanAnualHojasRuta(planAnual.getLineanegocio()
							.getPkCodigoLineanegocio());

			for (Iterator<Hojaruta> iterator = hojaRutaPlanAnual.iterator(); iterator
					.hasNext();) {
				Hojaruta hojaRuta = (Hojaruta) iterator.next();

				for (int i = 1; i <= cantidadMes; i++) {

					String anhoMesCorrelativo = Integer.toString(planAnual
							.getAnnoPlananual())
							+ Integer.toString(planAnual
									.getMesCorteVersionPlananual() + i - 1)
							+ Integer.toString(i)
							+ hojaRuta.getProduccion().getPkProduccion();
					Integer MES_ORDEN = 0;

					if (planAnual.getVersionPlananual().compareTo(
							VERSION_INICIAL_PLAN) == 0) {
						MES_ORDEN = MES_CORTE + i - 1;
					} else {
						MES_ORDEN = MES_CORTE + i;
					}
					Short mesOrden = MES_ORDEN.shortValue();

					Ordenproduccion ordenProduccion = new Ordenproduccion();

					ordenProduccion
							.setNumeroOrdenOrdenproduccion(anhoMesCorrelativo);

					ordenProduccion.setEstadoordenproduccion(estadoOrdenProd);
					ordenProduccion.setHojaruta(hojaRuta);
					ordenProduccion.setMesOrdenproduccion(mesOrden);
					ordenProduccion.setProduccion(hojaRuta.getProduccion());
					ordenProduccion.setProduccionEstimadaOrdenproduc(0.00);
					ordenProduccion
							.setUsuarioByFkCodigoUsuarioAprueba(usuarioApr);
					ordenProduccion
							.setUsuarioByFkCodigoUsuarioRegistro(usuarioReg);
					ordenProduccion
							.setFechaAprobacionOrdenproduccio(fechaAprobacionOrdenproduccio);
					ordenProduccion
							.setFechaRegistroOrdenproduccion(fechaRegistroOrdenproduccion);

					if (hojaRuta.getOperacions() != null
							&& hojaRuta.getOperacions().size() > 0) {
						Conceptomensual conceptoProd = ConceptoMensualQuerier
								.getByConceptoAndProduccionAndMesAndAnnio(
										CODIGO_POR_DEFECTO_CONCEPTO_PRODUCCION,
										hojaRuta.getProduccion()
												.getPkProduccion(), mesOrden,
										planAnual.getAnnoPlananual());

						Set<Ordenproduccionplan> listaOrdenProduccionPlan = new HashSet<Ordenproduccionplan>();

						Ordenproduccionplan ordenProduccionPlan = new Ordenproduccionplan(
								planAnual, ordenProduccion);

						// Concepto correspondiente a producción.
						if (conceptoProd != null) {

							Double ejecutada = 0.0;

							OrdenProduccionPlanQuerier
									.deleteByAnnioMesProduccion(planAnual,
											hojaRuta, mesOrden);

							for (Iterator<Conceptoregistromensual> iterCon = conceptoProd
									.getConceptoregistromensuals().iterator(); iterCon
									.hasNext();) {
								Conceptoregistromensual conceptoRegistoMensual = (Conceptoregistromensual) iterCon
										.next();
								if (conceptoRegistoMensual
										.getMesConceptoregistromensual()
										.compareTo(mesOrden) == 0) {
									ordenProduccion
											.setProduccionEstimadaOrdenproduc(conceptoRegistoMensual
													.getCantidadConceptoregistromensua());
									break;
								}
							}

							ordenProduccion
									.setProduccionEjecutadaOrdenprodu(ejecutada);

							Set<Consumocomponenteplan> listaConsumoComponentePlan = obtenerConsumoComponentePlan(
									planAnual, hojaRuta, i, ordenProduccionPlan);
							if (listaConsumoComponentePlan.size() > 0)
								ordenProduccionPlan
										.setConsumocomponenteplans(listaConsumoComponentePlan);

							Set<Consumocapacidadplan> listaConsumoCapacidadPlan = obtenerConsumoCapacidadPlan(
									planAnual, hojaRuta, i, ordenProduccionPlan);

							if (listaConsumoCapacidadPlan.size() > 0)
								ordenProduccionPlan
										.setConsumocapacidadplans(listaConsumoCapacidadPlan);

							Set<Recursoregistromensual> listaRecursoRegistroMensual = new HashSet<Recursoregistromensual>();

							obtenerConsumoRecursoPlan(planAnual, i,
									ordenProduccionPlan);

							if (listaRecursoRegistroMensual.size() > 0)
								ordenProduccionPlan
										.setConsumorecursoplans(listaRecursoRegistroMensual);
						}

						listaOrdenProduccionPlan.add(ordenProduccionPlan);

						ordenProduccion
								.setOrdenproduccionplans(listaOrdenProduccionPlan);
					}

					// Se Almacena
					OrdenProduccionQuerier.save(ordenProduccion);
				}
			}
			// cambia el edo al plan de Generado a Aprobado
			Estadoplananual estado = new Estadoplananual();
			estado = EstadoPlanAnualQuerier
					.getById(CODIGO_ESTADO_PLAN_ANUAL_APROBADO);
			planAnual.setEstadoplananual(estado);
			PlanAnualQuerier.update(planAnual);

			// cambia el estado del plan anterior a Historico
			Estadoplananual estadoPlanHistorico = EstadoPlanAnualQuerier
					.getById(CODIGO_ESTADO_PLAN_ANUAL_HISTORICO);
			Plananual plananualHistorico = PlanAnualQuerier
					.findByLineaNegocioAnioYEstadoYVersion(
							planAnual.getLineanegocio()
									.getPkCodigoLineanegocio(),
							planAnual.getAnnoPlananual(),
							estado.getPkCodigoEstadoplananual(),
							(Double.valueOf(planAnual.getVersionPlananual()) - 1d)
									+ "");

			if (plananualHistorico != null) {
				plananualHistorico.setEstadoplananual(estadoPlanHistorico);
				PlanAnualQuerier.update(plananualHistorico);

				List<Ordenproduccionplan> listaOrdenesPlan = OrdenProduccionPlanQuerier
						.findByPlanProduccion(plananualHistorico
								.getPkCodigoPlananual());

				for (Ordenproduccionplan ordenproduccionplan2 : listaOrdenesPlan) {
					if (ordenproduccionplan2.getOrdenproduccion()
							.getMesOrdenproduccion()
							.compareTo(planAnual.getMesCorteVersionPlananual()) == 0) {
						ordenproduccionplan2.setPlananual(planAnual);
						OrdenProduccionPlanQuerier.update(ordenproduccionplan2);
					}

				}
			}

			tx.commit();
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_REGISTRAR);
			logger.error(mensajeError, e);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(ERROR_ORDEN_PRODUCCION_REGISTRAR, e);
		} catch (ParseException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_REGISTRAR);
			logger.error(mensajeError, e);

			throw new LogicaException(ERROR_ORDEN_PRODUCCION_REGISTRAR, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	private Set<Consumorecursoplan> obtenerConsumoRecursoPlan(
			Plananual planAnual, int mes,
			Ordenproduccionplan ordenProduccionPlan) {

		Set<Consumorecursoplan> listaConsumoRecursoPlan = new HashSet<Consumorecursoplan>();

		if (planAnual.getRecursoregistromensuals() != null
				&& planAnual.getRecursoregistromensuals().size() > 0) {
			Consumorecursoplan consumoRecursoPlan = new Consumorecursoplan();
			consumoRecursoPlan = guardarConsumoRecursoPlan(consumoRecursoPlan,
					planAnual, ordenProduccionPlan, mes);

			consumoRecursoPlan.setOrdenproduccionplan(ordenProduccionPlan);

			for (Iterator<Recursoregistromensual> iterator5 = planAnual
					.getRecursoregistromensuals().iterator(); iterator5
					.hasNext();) {
				Recursoregistromensual recursoRegMen = (Recursoregistromensual) iterator5
						.next();
				int recursoMes = recursoRegMen.getMesRecursoregistromensual();

				int planMes = 0;
				if (planAnual.getVersionPlananual().compareTo(
						VERSION_INICIAL_PLAN) == 0) {
					planMes = planAnual.getMesCorteVersionPlananual() + mes - 1;
				} else {
					planMes = planAnual.getMesCorteVersionPlananual() + mes;
				}

				Double consumo = 0.0;
				if (recursoMes == planMes) {
					consumoRecursoPlan.setCantidadConsumorecursoplan(consumo);
					consumoRecursoPlan.setRecursoregistromensual(recursoRegMen);
					consumoRecursoPlan
							.setOrdenproduccionplan(ordenProduccionPlan);
					listaConsumoRecursoPlan.add(consumoRecursoPlan);
					return listaConsumoRecursoPlan;
				}
			}
		}

		return listaConsumoRecursoPlan;
	}

	private Set<Consumocapacidadplan> obtenerConsumoCapacidadPlan(
			Plananual planAnual, Hojaruta hojaRuta, int mes,
			Ordenproduccionplan ordenProduccionPlan) {

		Set<Consumocapacidadplan> listaConsumoCapacidadPlan = new HashSet<Consumocapacidadplan>();

		Consumocapacidadplan consumoCapacidadplan = new Consumocapacidadplan();

		for (Iterator<Operacion> iterator4 = hojaRuta.getOperacions()
				.iterator(); iterator4.hasNext();) {
			Operacion operacion = (Operacion) iterator4.next();

			if (operacion.getCapacidadoperativas() != null) {
				for (Iterator<Capacidadoperativa> iterator2 = operacion
						.getCapacidadoperativas().iterator(); iterator2
						.hasNext();) {
					Capacidadoperativa capacidadOp = (Capacidadoperativa) iterator2
							.next();

					if (capacidadOp.getCapacidadoperativaregistromensus() != null) {
						for (Iterator<Capacidadoperativaregistromensu> iterator3 = capacidadOp
								.getCapacidadoperativaregistromensus()
								.iterator(); iterator3.hasNext();) {
							Capacidadoperativaregistromensu capacidadOpRegMen = (Capacidadoperativaregistromensu) iterator3
									.next();
							int planMes = 0;
							if (planAnual.getVersionPlananual().compareTo(
									VERSION_INICIAL_PLAN) == 0) {
								planMes = planAnual
										.getMesCorteVersionPlananual()
										+ mes
										- 1;
							} else {
								planMes = planAnual
										.getMesCorteVersionPlananual() + mes;
							}

							int capacidadMes = capacidadOpRegMen
									.getMesCapacidadoperativaregistrom();
							if (capacidadMes == planMes) {
								consumoCapacidadplan
										.setCapacidadoperativaregistromensu(capacidadOpRegMen);

								Double consumo = 0.00;
								consumoCapacidadplan
										.setCantidadConsumocapacidadplan(consumo);
								consumoCapacidadplan
										.setOrdenproduccionplan(ordenProduccionPlan);

								listaConsumoCapacidadPlan
										.add(consumoCapacidadplan);
								return listaConsumoCapacidadPlan;
							}
						}
					}
				}
			}
		}

		return listaConsumoCapacidadPlan;
	}

	/**
	 * M�todo que devuelve la lista de consumocomponenteplan
	 * 
	 * @param planAnual
	 * @param hojaRuta
	 * @param mes
	 * @param ordenProduccionPlan
	 * @return
	 */
	private Set<Consumocomponenteplan> obtenerConsumoComponentePlan(
			Plananual planAnual, Hojaruta hojaRuta, int mes,
			Ordenproduccionplan ordenProduccionPlan) {

		Set<Consumocomponenteplan> listaConsumoComponentePlan = new HashSet<Consumocomponenteplan>();

		Consumocomponenteplan consumoComponenteplan = new Consumocomponenteplan();
		consumoComponenteplan.setCantidadDosificacionConsumoco(0.00);
		consumoComponenteplan.setCantidadFactordosificacionCon(0.00);
		consumoComponenteplan.setOrdenproduccionplan(ordenProduccionPlan);

		for (Iterator<Dosificacionregistromensual> iterator3 = planAnual
				.getDosificacionregistromensuals().iterator(); iterator3
				.hasNext();) {

			Dosificacionregistromensual dosificacionRegMen = (Dosificacionregistromensual) iterator3
					.next();
			int dosificacionMes = dosificacionRegMen
					.getMesDosificacionregistromensual();
			int planMes = 0;
			if (planAnual.getVersionPlananual().compareTo(VERSION_INICIAL_PLAN) == 0) {
				planMes = planAnual.getMesCorteVersionPlananual() + mes - 1;
			} else {
				planMes = planAnual.getMesCorteVersionPlananual() + mes;
			}
			if (dosificacionMes == planMes) {
				consumoComponenteplan
						.setDosificacionregistromensual(dosificacionRegMen);
				listaConsumoComponentePlan.add(consumoComponenteplan);
				return listaConsumoComponentePlan;
			}
		}

		return listaConsumoComponentePlan;
	}

	/**
	 * Este m�todo almacena los consumo recursos plan
	 * 
	 * @param consumoRecursoPlan
	 * @param planAnualPers
	 * @param ordenProduccionPlan
	 * @param i
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	private Consumorecursoplan guardarConsumoRecursoPlan(
			Consumorecursoplan consumoRecursoPlan, Plananual planAnualPers,
			Ordenproduccionplan ordenProduccionPlan, int i) {

		String mensajeError = "";
		consumoRecursoPlan.setOrdenproduccionplan(ordenProduccionPlan);

		for (Iterator<Recursoregistromensual> iterator = planAnualPers
				.getRecursoregistromensuals().iterator(); iterator.hasNext();) {
			Recursoregistromensual recursoRegMen = (Recursoregistromensual) iterator
					.next();
			int recursoMes = recursoRegMen.getMesRecursoregistromensual();

			int planMes = 0;
			if (planAnualPers.getVersionPlananual().compareTo(
					VERSION_INICIAL_PLAN) == 0) {
				planMes = planAnualPers.getMesCorteVersionPlananual() + i - 1;
			} else {
				planMes = planAnualPers.getMesCorteVersionPlananual() + i;
			}

			Double consumo = 0.0;
			if (recursoMes == planMes) {
				consumoRecursoPlan.setCantidadConsumorecursoplan(consumo);
				consumoRecursoPlan.setRecursoregistromensual(recursoRegMen);
				consumoRecursoPlan.setOrdenproduccionplan(ordenProduccionPlan);

				try {
					ConsumoRecursoPlanQuerier.save(consumoRecursoPlan);
				} catch (AplicacionException e) {
					mensajeError = ManejadorPropiedades
							.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_REGISTRAR);
					logger.error(mensajeError, e);
				}
				break;
			}
		}
		return consumoRecursoPlan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #aprobarOrdenProduccion(java.lang.Long,
	 * pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void aprobarOrdenProduccion(Long codigo, UsuarioBean usuario)
			throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session sesion = null;
		Ordenproduccion orden = null;

		try {

			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			orden = OrdenProduccionQuerier.getById(codigo);
			if (orden
					.getEstadoordenproduccion()
					.getPkCodigoEstadoorden()
					.compareTo(
							Long.parseLong(ManejadorPropiedades
									.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR))) == 0) {
				Estadoordenproduccion edo = new Estadoordenproduccion();
				edo = EstadoOrdenProduccionQuerier
						.getById(Long.parseLong(ManejadorPropiedades
								.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_LIBERADA)));
				orden.setEstadoordenproduccion(edo);
				Usuario usuarioAprob = UsuarioQuerier.getById(usuario
						.getCodigo());
				orden.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprob);
				Calendar c1 = Calendar.getInstance();
				orden.setFechaAprobacionOrdenproduccio(c1.getTime());
				OrdenProduccionQuerier.saveOrUpdate(orden);
				tx.commit();
			} else {
				mensajeError = ManejadorPropiedades
						.obtenerPropiedadPorClave(ORDEN_LIBERADA);
				throw new LogicaException(mensajeError);
			}
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			if (tx != null)
				tx.rollback();
			throw new LogicaException(e.getMessage(), e);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (sesion != null)
				PersistenciaFactory.closeSession(sesion);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #eliminarOrdenProduccion(java.lang.Long)
	 */
	public void eliminarOrdenProduccion(Long codigo) throws LogicaException {
		String mensajeError = null;
		Transaction tx = null;
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			// eliminar privado, sirve para eliminar ordenes manuales y para
			// eliminar las ordenes cuando ocurre el versionamiento del plan
			eliminarOrdenProd(codigo);
			tx.commit();
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

	}

	/**
	 * Este m�todo elimina ordenes manuales (eliminaci�n normal) y
	 * autom�ticas(solo para versionamiento del plan)
	 * 
	 * @param codigo
	 *            (de la orden)
	 * @return
	 * @throws LogicaException
	 */
	private void eliminarOrdenProd(Long codigo) throws LogicaException {

		String mensajeError = null;
		Ordenproduccion orden = null;

		try {
			orden = OrdenProduccionQuerier.getById(codigo);
			Ordenproduccionplan ordenPlan = OrdenProduccionPlanQuerier
					.findByOrdenProduccion(orden.getPkCodigoOrdenproduccion());
			if (ordenPlan != null) {
				OrdenProduccionPlanQuerier.delete(ordenPlan);
			} else {
				if (orden
						.getEstadoordenproduccion()
						.getPkCodigoEstadoorden()
						.compareTo(
								Long.parseLong(ManejadorPropiedades
										.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR))) == 0) {
					Ordenproduccionmanual ordenManual = OrdenProduccionManualQuerier
							.findByOrdenProduccion(orden
									.getPkCodigoOrdenproduccion());
					OrdenProduccionManualQuerier.delete(ordenManual);
				} else {
					mensajeError = ManejadorPropiedades
							.obtenerPropiedadPorClave(ORDEN_NO_MANUAL_O_PRELIMINAR);
					throw new LogicaException(mensajeError);
				}
			}
			OrdenProduccionQuerier.delete(orden);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			throw new LogicaException(e.getMessage(), e);
		} catch (Exception e) {
			throw new LogicaException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #obtenerHojaActivaProducto(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public HojaRutaBean obtenerHojaActivaProducto(String valorProceso,
			String valorProducto, String valorAnio, String valorMes,
			String mensajeError) throws LogicaException {

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
			List<Hojaruta> hojas = HojaRutaQuerier
					.obtenerHojasRutaActivaPorProductoYProceso(
							Long.parseLong(valorProducto),
							Long.parseLong(valorProceso));
			if (hojas != null) {
				switch (hojas.size()) {
				case 0:
					mensajeError = ManejadorPropiedades
							.obtenerPropiedadPorClave(ERROR_PRODUCTO_SIN_HOJA_DE_RUTA_ACTIVA);
					throw new LogicaException(mensajeError);
				case 1:
					Hojaruta hoja = hojas.get(0);
					if (hoja.getFactordosificacions().size() <= 0
							|| hoja.getProduccion().getTasarealproduccions()
									.size() <= 0) {
						mensajeError = MessageFormat
								.format(ManejadorPropiedades
										.obtenerPropiedadPorClave(ORDEN_PRODUCCION_FALTA_FACTORES_DOSIFICACION_TASA_REAL),
										new Object[] { hoja.getProduccion()
												.getProducto()
												.getNombreProducto() });
						throw new LogicaException(mensajeError);
					}
					return beanFactory.transformarHojaRuta(hojas.get(0));
				default:
					// este caso no debería de ocurrir nunca
					if (hojas.size() > 1) {
						mensajeError = ManejadorPropiedades
								.obtenerPropiedadPorClave(MAS_UNA_HOJA_ACTIVA);
						throw new LogicaException(mensajeError);
					}
					break;
				}
			} else {
				mensajeError = ManejadorPropiedades
						.obtenerPropiedadPorClave(NO_HAY_HOJA_ACTIVA);
				throw new LogicaException(mensajeError);
			}
			return null;
		} catch (LogicaException e) {
			throw e;
		} catch (AplicacionException e) {
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Valida los datos necesarios para generar las ordenes de produccion
	 * automaticas
	 * 
	 * @param planAnual
	 *            plan anual a validar para generar las ordenes de produccion
	 *            automaticas
	 * @throws LogicaException
	 */
	private void validarDatosPlanAnualOrdenesAutomaticas(Plananual planAnual)
			throws LogicaException {
		String error = null;
		if (planAnual == null)
			error = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_PLAN_ANUAL_NULO);
		else if (planAnual.getLineanegocio() == null)
			error = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_LINEA_NEGOCIO_NULO);
		else if (planAnual.getLineanegocio().getPkCodigoLineanegocio() == null)
			error = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_LINEA_NEGOCIO_NULO);
		else if (StringUtils.isBlank(planAnual.getVersionPlananual()))
			error = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_VERSION_NULA);
		else if (planAnual.getUsuarioByFkCodigoUsuarioAprueba() == null
				|| planAnual.getUsuarioByFkCodigoUsuarioAprueba()
						.getPkCodigoUsuario() <= 0)
			error = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_USUARIO_APR);
		else if (planAnual.getUsuarioByFkCodigoUsuarioRegistra() == null
				|| planAnual.getUsuarioByFkCodigoUsuarioRegistra()
						.getPkCodigoUsuario() <= 0)
			error = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_USUARIO_REG);
		if (!StringUtils.isBlank(error)) {
			logger.error(error);
			throw new LogicaException(error);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #obtenerOrdenProduccion(java.lang.Long)
	 */
	public OrdenProduccionBean obtenerOrdenProduccion(Long codigoOrdenProd)
			throws LogicaException {

		String mensajeError = "";
		Session session = null;
		Transaction trans = null;
		OrdenProduccionBean ordenBean = null;

		try {
			session = PersistenciaFactory.currentSession();
			trans = session.beginTransaction();
			Ordenproduccion ordenprod = OrdenProduccionQuerier
					.getById(codigoOrdenProd);
			ordenBean = beanFactory.transformarOrdenProduccion(ordenprod);
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
		} catch (HibernateException e) {
			if (trans != null)
				trans.rollback();
			throw new ErrorConexionException(ERROR_HIBERNATE, e);
		} catch (RuntimeException e) {
			if (trans != null)
				trans.rollback();
			throw new LogicaException(e.getMessage(), e);
		} catch (AplicacionException e) {
			if (trans != null)
				trans.rollback();
			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return ordenBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #filtrarOrdenProduccion(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<OrdenProdConsultaBean> filtrarOrdenProduccion(
			String valorUnidad, String valorLineaNegocio, String valorProceso,
			String valorProducto, String annio, String mes, String valorEstado)
			throws LogicaException, AplicacionException,
			AplicacionGlobalException {

		String mensajeError = "";
		Session session = null;

		List<OrdenProdConsultaBean> retornolista = new ArrayList<OrdenProdConsultaBean>();

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
			List<OrdenProduccionBean> listaBean = new ArrayList<OrdenProduccionBean>();

			List<Proceso> procesos = new ArrayList<Proceso>();

			Long codigoUnidad = Long.parseLong(valorUnidad);
			Unidad unidad = UnidadQuerier.getById(codigoUnidad);

			// CASO IV: Selecciona Línea de Negocio, Proceso, Producto y A�o
			if (valorLineaNegocio != null
					&& valorLineaNegocio.trim().length() > 0
					&& !valorLineaNegocio.equals(CANTIDAD_CERO)
					&& valorProceso != null && valorProceso.trim().length() > 0
					&& !valorProceso.equals(CANTIDAD_CERO)
					&& valorProducto != null
					&& valorProducto.trim().length() > 0
					&& !valorProducto.equals(CANTIDAD_CERO)) {
				Long codigoProceso = Long.parseLong(valorProceso);
				Proceso proceso = ProcesoQuerier.getById(codigoProceso);

				Long codigoProducto = Long.parseLong(valorProducto);

				List<Produccion> producciones = new ArrayList<Produccion>();
				Produccion produccion = ProduccionQuerier.getByProductoProceso(
						codigoProducto, codigoProceso);
				producciones.add(produccion);

				produccion.getProceso().getLineanegocio();

				Lineanegocio lineanegocio = produccion.getProceso()
						.getLineanegocio();

				List<Ordenproduccion> ordenProduccions = ordenesProducto(
						producciones, annio, mes);
				listaBean = beanFactory
						.transformarListaOrdenProduccion(ordenProduccions);

				obtenerOrdenConsulta(valorEstado, lineanegocio, proceso, annio,
						unidad.getNombreUnidad(), listaBean, retornolista);

			}
			// CASO III: Selecciona L�nea de Negocio, Proceso y A�o
			else if (valorLineaNegocio != null
					&& valorLineaNegocio.trim().length() > 0
					&& !valorLineaNegocio.equals(CANTIDAD_CERO)
					&& valorProceso != null && valorProceso.trim().length() > 0
					&& !valorProceso.equals(CANTIDAD_CERO)) {
				Long codigoProceso = Long.parseLong(valorProceso);
				Proceso proceso = ProcesoQuerier.getById(codigoProceso);

				List<Produccion> producciones = ProduccionQuerier
						.getByProceso(codigoProceso);

				Lineanegocio lineanegocio = proceso.getLineanegocio();

				List<Ordenproduccion> ordenProduccions = ordenesProducto(
						producciones, annio, mes);
				listaBean = beanFactory
						.transformarListaOrdenProduccion(ordenProduccions);

				obtenerOrdenConsulta(valorEstado, lineanegocio, proceso, annio,
						unidad.getNombreUnidad(), listaBean, retornolista);

			}
			// Caso II: Selecciona Línea de Negocio y A�o
			else if (valorLineaNegocio != null
					&& valorLineaNegocio.trim().length() > 0
					&& !valorLineaNegocio.equals(CANTIDAD_CERO)) {
				Long codigoLineaNegocio = Long.parseLong(valorLineaNegocio);
				Lineanegocio lineaNegocio = LineaNegocioQuerier
						.getById(codigoLineaNegocio);

				procesos = ProcesoQuerier
						.findByCodigoLineaNegocio(codigoLineaNegocio);
				for (Iterator<Proceso> iterProceso = procesos.iterator(); iterProceso
						.hasNext();) {
					Proceso proceso = (Proceso) iterProceso.next();

					List<Produccion> producciones = ProduccionQuerier
							.getByProceso(proceso.getPkCodigoProceso());
					List<Ordenproduccion> listaOrdenProduccion = new ArrayList<Ordenproduccion>();
					listaOrdenProduccion = ordenesProducto(producciones, annio,
							mes);
					if (listaOrdenProduccion.size() > 0) {
						listaBean = beanFactory
								.transformarListaOrdenProduccion(listaOrdenProduccion);
						obtenerOrdenConsulta(valorEstado, lineaNegocio,
								proceso, annio, unidad.getNombreUnidad(),
								listaBean, retornolista);
					}
				}
			}
			// CASO I: Selecciona Unidad y A�o
			else if (valorUnidad != null && valorUnidad.trim().length() > 0
					&& !valorUnidad.equals(CANTIDAD_CERO)) {
				List<Lineanegocio> listaLineaNegocios = LineaNegocioQuerier
						.findByCodigoUnidad(codigoUnidad);

				for (Iterator<Lineanegocio> iterlineanegocio = listaLineaNegocios
						.iterator(); iterlineanegocio.hasNext();) {
					Lineanegocio lineaNegocio = (Lineanegocio) iterlineanegocio
							.next();

					procesos = ProcesoQuerier
							.findByCodigoLineaNegocio(lineaNegocio
									.getPkCodigoLineanegocio());
					for (Iterator<Proceso> iterProceso = procesos.iterator(); iterProceso
							.hasNext();) {
						Proceso proceso = (Proceso) iterProceso.next();
						List<Produccion> producciones = ProduccionQuerier
								.getByProceso(proceso.getPkCodigoProceso());
						List<Ordenproduccion> listaOrdenProduccion = new ArrayList<Ordenproduccion>();
						listaOrdenProduccion = ordenesProducto(producciones,
								annio, mes);
						if (listaOrdenProduccion.size() > 0) {
							listaBean = beanFactory
									.transformarListaOrdenProduccion(listaOrdenProduccion);
							obtenerOrdenConsulta(valorEstado, lineaNegocio,
									proceso, annio, unidad.getNombreUnidad(),
									listaBean, retornolista);
						}
					}
				}
			}
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return retornolista;
	}

	/**
	 * Obtiene las ordenes de producción para la consulta, automáticas y
	 * manuales
	 * 
	 * @param producciones
	 * @param valorAnio
	 * @param valorMes
	 * @return
	 * @throws AplicacionException
	 */
	private List<Ordenproduccion> ordenesProducto(
			List<Produccion> producciones, String valorAnio, String valorMes)
			throws AplicacionException {

		List<Ordenproduccion> listaOrdenProduccionRetornar = new ArrayList<Ordenproduccion>();
		Short mes = mesToShort(valorMes);
		List<Ordenproduccion> listaDto = new ArrayList<Ordenproduccion>();

		for (Iterator<Produccion> iterProd = producciones.iterator(); iterProd
				.hasNext();) {
			Produccion produccion = (Produccion) iterProd.next();

			if (mes != null)
				listaDto = OrdenProduccionQuerier.getByProduccionYMes(
						produccion.getPkProduccion(), mes);
			else
				listaDto = OrdenProduccionQuerier.getByProduccion(produccion
						.getPkProduccion());

			for (Iterator<Ordenproduccion> iterorden = listaDto.iterator(); iterorden
					.hasNext();) {
				Ordenproduccion orden = (Ordenproduccion) iterorden.next();
				// Set Orden Produccion Manual
				Set ordenProduccionManualSet = orden
						.getOrdenproduccionmanuals();
				// Set Orden Produccion Automatica
				Set ordenProduccionAutomaticaSet = orden
						.getOrdenproduccionplans();

				if (ordenProduccionManualSet != null
						&& ordenProduccionManualSet.size() > 0) {
					// La orden es manual
					for (Iterator<Ordenproduccionmanual> iterator = ordenProduccionManualSet
							.iterator(); iterator.hasNext();) {
						Ordenproduccionmanual Ordenproduccionmanual = (Ordenproduccionmanual) iterator
								.next();
						if (Ordenproduccionmanual
								.getAnnoOrdenproduccionmanual().equals(
										Integer.parseInt(valorAnio))) {
							listaOrdenProduccionRetornar.add(orden);
							break;
						}
					}
				} else if (ordenProduccionAutomaticaSet != null
						&& ordenProduccionAutomaticaSet.size() > 0) {
					// La orden es automática
					for (Iterator<Ordenproduccionplan> iterator = ordenProduccionAutomaticaSet
							.iterator(); iterator.hasNext();) {
						Ordenproduccionplan ordenproduccionplan = (Ordenproduccionplan) iterator
								.next();
						if (ordenproduccionplan.getPlananual()
								.getAnnoPlananual()
								.equals(Integer.parseInt(valorAnio))) {
							listaOrdenProduccionRetornar.add(orden);
							break;
						}
					}
				}
			}
		}
		return listaOrdenProduccionRetornar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarDivision(java.lang.String)
	 */
	public String cargarDivision(String valorDivision) throws LogicaException {

		Division divDto = null;

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long div = Long.parseLong(valorDivision);
			divDto = DivisionQuerier.getById(div);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_DIVISION);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return divDto.getNombreDivision();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarSociedad(java.lang.String)
	 */
	public String cargarSociedad(String valorSociedad) throws LogicaException {

		Sociedad socDto = null;

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long soc = Long.parseLong(valorSociedad);
			socDto = SociedadQuerier.getById(soc);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_SOCIEDAD);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return socDto.getNombreSociedad();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarUnidad(java.lang.String)
	 */
	public String cargarUnidad(String valorUnidad) throws LogicaException {

		Unidad uniDto = null;

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long uni = Long.parseLong(valorUnidad);
			uniDto = UnidadQuerier.getById(uni);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_UNIDAD);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return uniDto.getNombreUnidad();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarLinea(java.lang.String)
	 */
	public String cargarLinea(String valorLineaNegocio) throws LogicaException {

		Lineanegocio lineaDto = null;
		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long lineaNeg = Long.parseLong(valorLineaNegocio);
			lineaDto = LineaNegocioQuerier.getById(lineaNeg);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_LINEA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lineaDto.getNombreLineanegocio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarProceso(java.lang.String)
	 */
	public String cargarProceso(String valorProceso) throws LogicaException {
		Proceso procDto = null;

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long proc = Long.parseLong(valorProceso);
			procDto = ProcesoQuerier.getById(proc);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_PROCESO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return procDto.getNombreProceso();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarProducto(java.lang.String)
	 */
	public String cargarProducto(String valorProducto) throws LogicaException {
		Producto prodDto = null;

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long prod = Long.parseLong(valorProducto);
			prodDto = ProductoQuerier.getById(prod);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_PRODUCTO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return prodDto.getNombreProducto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #ordenValida(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String,
	 * pe.com.pacasmayo.sgcp.bean.UsuarioBean,
	 * pe.com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public OrdenProduccionBean ordenValida(String valorProceso,
			String valorProducto, String valorAnio, String valorMes,
			String mensajeError, UsuarioBean usuario, HojaRutaBean hojaBean)
			throws LogicaException {

		Session session = null;

		OrdenProduccionBean orden = new OrdenProduccionBeanImpl();
		Produccion produccion = new Produccion();
		Long prod = Long.parseLong(valorProducto);
		Long proceso = Long.parseLong(valorProceso);
		Integer anio = Integer.parseInt(valorAnio);
		Short mes = mesToShort(valorMes);
		boolean existe = false;
		try {
			session = PersistenciaFactory.currentSession();
			produccion = ProduccionQuerier.getByProductoProceso(prod, proceso);

			List<Ordenproduccion> ordenes = OrdenProduccionQuerier
					.getByProduccion(produccion.getPkProduccion());
			if (ordenes != null && ordenes.size() > 0) {
				for (Iterator<Ordenproduccion> iter = ordenes.iterator(); iter
						.hasNext();) {
					Ordenproduccion ordenDto = (Ordenproduccion) iter.next();
					Ordenproduccionplan ordenPlan = OrdenProduccionPlanQuerier
							.findByOrdenProduccion(ordenDto
									.getPkCodigoOrdenproduccion());
					// validando unicidad de la orden automática
					if (ordenPlan != null) {
						if (ordenPlan.getPlananual().getAnnoPlananual()
								.equals(anio)) {
							if (ordenDto.getMesOrdenproduccion().equals(mes)) {
								existe = true;
								mensajeError = ManejadorPropiedades
										.obtenerPropiedadPorClave(ORDEN_PRODUCCION_ES_PARA_UN_PRODUCTO);
								throw new LogicaException(mensajeError);
							}
						}
					} else {
						// validando unicidad de la orden manual
						Ordenproduccionmanual ordenMan = OrdenProduccionManualQuerier
								.findByOrdenProduccion(ordenDto
										.getPkCodigoOrdenproduccion());
						if (ordenMan != null
								&& ordenMan.getAnnoOrdenproduccionmanual()
										.equals(anio)) {
							if (ordenDto.getMesOrdenproduccion().equals(mes)) {
								existe = true;
								mensajeError = ManejadorPropiedades
										.obtenerPropiedadPorClave(ORDEN_PRODUCCION_ES_PARA_UN_PRODUCTO);
								throw new LogicaException(mensajeError);
							}
						}
					}
				}
			}
			if (!existe) {
				ProduccionBean produccionBean = beanFactory
						.transformarProduccion(produccion);

				orden = cargarDatosOrden(usuario, hojaBean, produccionBean,
						mes.intValue(), valorAnio, proceso);
			}
		} catch (AplicacionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_VALIDACION);
			throw new LogicaException(mensajeError);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_VALIDACION);
			e.printStackTrace();
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return orden;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #cargarOperaciones(pe.com.pacasmayo.sgcp.bean.HojaRutaBean,
	 * pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	public List<OperacionBean> cargarOperaciones(HojaRutaBean hojaBean,
			OrdenProduccionBean ordenBean, Integer valorAnnio)
			throws LogicaException {

		String mensajeError = "";
		Session session = null;

		List<OperacionBean> operaciones = new ArrayList<OperacionBean>();
		List<OperacionBean> opereturn = new ArrayList<OperacionBean>();

		Integer indiceMes = ordenBean.getMes();
		try {
			session = PersistenciaFactory.currentSession();

			List<Plananual> listaPlanAnual = PlanAnualQuerier
					.getByLineaNegocioAnnoAndEstatus(hojaBean.getProduccion()
							.getProceso().getLineaNegocio().getCodigo(),
							valorAnnio, CODIGO_ESTADO_PLAN_ANUAL_APROBADO);

			Plananual planAnual = new Plananual();

			if (listaPlanAnual != null && listaPlanAnual.size() > 0
					&& listaPlanAnual.get(0) != null) {
				planAnual = listaPlanAnual.get(0);
			} else {
				throw new LogicaException(
						ManejadorPropiedades
								.obtenerPropiedadPorClave(NO_PLAN_ANUAL_LINNEG_ANIO));
			}

			List<Operacion> opes = OperacionQuerier
					.findByCodigoHojaRuta(hojaBean.getCodigo());
			operaciones = beanFactory
					.transformarListaOperacionParaPlanAnual(opes);
			int i = 0;
			for (Iterator<OperacionBean> iteroperaciones = operaciones
					.iterator(); iteroperaciones.hasNext();) {
				OperacionBean opebean = (OperacionBean) iteroperaciones.next();
				i++;
				Double planificado = new Double(0);
				Long ejecutado = new Long(0);

				Operacion oper = opes.get(i - 1);

				// si la orden es automática
				if (!ordenBean.isEsManual()) {

					// Asignando los valores de capacidad operativa registro
					// mensual
					List<Capacidadoperativa> listaCapacidadOperativa = CapacidadOperativaQuerier
							.getByOperacionYAnnio(oper.getPkCodigoOperacion(),
									valorAnnio);

					for (Iterator<Capacidadoperativa> iterator3 = listaCapacidadOperativa
							.iterator(); iterator3.hasNext();) {
						Capacidadoperativa capacidadoperativa = iterator3
								.next();
						if (capacidadoperativa.getTipocapacidadoperativa()
								.getPkCodigoTipocapacidadoperativ()
								.equals(CODIGO_TIPO_CAPACIDAD_DIAS)) {
							Set<Capacidadoperativaregistromensu> listaCapacidadRM = CapacidadOperativaRegistroMensuQuerier
									.getByCapacidadTipoAndPlanAnual(
											capacidadoperativa,
											CODIGO_TIPO_CAPACIDAD_DIAS,
											planAnual.getPkCodigoPlananual(),
											planAnual.getAnnoPlananual());
							opebean.setListaCapacidadOperativaRMDias(beanFactory
									.transformarListaCapacidadOperativaRM(listaCapacidadRM));
						} else {
							Set<Capacidadoperativaregistromensu> listaCapacidadRM = CapacidadOperativaRegistroMensuQuerier
									.getByCapacidadTipoAndPlanAnual(
											capacidadoperativa,
											CODIGO_TIPO_CAPACIDAD_TONS,
											planAnual.getPkCodigoPlananual(),
											planAnual.getAnnoPlananual());
							opebean.setListaCapacidadOperativaRMTon(beanFactory
									.transformarListaCapacidadOperativaRM(listaCapacidadRM));
						}

					}
				} else {
					// si la orden es manual
					if (ordenBean.getCodigo() != null) {
						// si es modificaci�n
						Ordenproduccionmanual ordenMan = OrdenProduccionManualQuerier
								.findByOrdenProduccion(ordenBean.getCodigo());
						List<Consumocapacidadmanual> consumoCapacidadManual = ConsumoCapacidadManualQuerier
								.findByProduccionManualYPuestoTrabajo(ordenMan
										.getPkCodigoOrdenproduccionmanual(),
										oper.getPuestotrabajo()
												.getPkCodigoPuestotrabajo());
						if (consumoCapacidadManual != null) {
							for (Iterator<Consumocapacidadmanual> iterCap = consumoCapacidadManual
									.iterator(); iterCap.hasNext();) {
								Consumocapacidadmanual consumoManCap = (Consumocapacidadmanual) iterCap
										.next();
								planificado = consumoManCap
										.getCantidadConsumocapmanual();

								CapacidadOperativaRegistroMensualBean capacidadOperativaRegistroMensualBean = new CapacidadOperativaRegistroMensualBeanImpl();
								CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRegistroMensualBean = new CapacidadOperativaRegistroMensualBean[12];

								capacidadOperativaRegistroMensualBean
										.setAnnoCapacidadOperativaRegistroMensual(ordenMan
												.getAnnoOrdenproduccionmanual());
								capacidadOperativaRegistroMensualBean
										.setCantidadCapacidadOperativaRegistroMensual(planificado);
								capacidadOperativaRegistroMensualBean
										.setMesCapacidadOperativaregistromensual(Short
												.parseShort(indiceMes
														.toString()));

								listaCapacidadOperativaRegistroMensualBean[indiceMes - 1] = capacidadOperativaRegistroMensualBean;

								if (consumoManCap
										.getTipocapacidadoperativa()
										.getPkCodigoTipocapacidadoperativ()
										.equals(CODIGO_TIPO_CAPACIDAD_OPERATIVA_DIAS)) {
									opebean.setListaCapacidadOperativaRMDias(listaCapacidadOperativaRegistroMensualBean);
								} else {
									opebean.setListaCapacidadOperativaRMTon(listaCapacidadOperativaRegistroMensualBean);
								}
							}
						}
					} else {
						// si es nuevo registro
						if (oper.getCapacidadoperativas() != null) {
							for (Iterator<Capacidadoperativa> iterator = oper
									.getCapacidadoperativas().iterator(); iterator
									.hasNext();) {
								Capacidadoperativa capacidadoperativa = (Capacidadoperativa) iterator
										.next();

								CapacidadOperativaBean capacidadOperativaBean = beanFactory
										.transformarCapacidadOperativa(capacidadoperativa);

								if (capacidadOperativaBean
										.getListaCapacidadOperativaRegistroMensual()[indiceMes - 1] != null) {
									if (capacidadOperativaBean
											.getTipoCapacidadOperativa()
											.getCodigo()
											.equals(CODIGO_TIPO_CAPACIDAD_OPERATIVA_DIAS)) {
										opebean.setListaCapacidadOperativaRMDias(capacidadOperativaBean
												.getListaCapacidadOperativaRegistroMensual());
									} else {
										planificado = capacidadOperativaBean
												.getListaCapacidadOperativaRegistroMensual()[indiceMes - 1]
												.getCantidadCapacidadOperativaRegistroMensual();
										opebean.setListaCapacidadOperativaRMTon(capacidadOperativaBean
												.getListaCapacidadOperativaRegistroMensual());
									}
								}
							}
						}
					}

				}

				opebean.setConsumoPlanificado(planificado.doubleValue());
				opebean.setConsumoEjecutado(ejecutado.doubleValue());

				Double porcentaje = 0.00;
				if (planificado != 0)
					porcentaje = ((opebean.getConsumoEjecutado() * 100) / planificado);

				opebean.setPorcentaje(porcentaje.longValue());

				opereturn.add(opebean);
			}
		} catch (LogicaException e) {
			logger.error(mensajeError, e);
			throw new LogicaException(e.getMensaje());
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_OPERACIONES);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return opereturn;
	}

	/*
	 * Encargado de llenar la lista de Componentes para registro o generaci�n
	 * de ordenes (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.facade.OrdenProduccionLogicFacade#
	 * cargarComponentes(pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean,
	 * java.lang.String, pe.com.pacasmayo.sgcp.bean.HojaRutaBean,
	 * java.lang.String, java.lang.String)
	 */
	public List<ComponenteRegistroOrdenBean> cargarComponentes(
			OrdenProduccionBean ordenBean, String valorProducto,
			HojaRutaBean hojaBean, String valorAnio, String valorMes)
			throws LogicaException {

		String mensajeError = "";
		Session session = null;

		List<ComponenteRegistroOrdenBean> componentes = new ArrayList<ComponenteRegistroOrdenBean>();

		Integer indiceMes = ordenBean.getMes();

		try {
			session = PersistenciaFactory.currentSession();
			Hojaruta hoja = HojaRutaQuerier.getById(hojaBean.getCodigo());
			HojaRutaBean hojaRutaBean = beanFactory.transformarHojaRuta(hoja);
			if (hojaRutaBean.getHojaRutaComponentes() != null
					&& hojaRutaBean.getHojaRutaComponentes().size() > 0) {
				Double dosificacionPlanificada = 0.0;
				Double dosificacionEjecutada = 0.0;
				Double produccionPlanificada = 0.0;
				Double produccionEjecutada = 0.0;

				for (Iterator<HojaRutaComponenteBean> iterator = hojaRutaBean
						.getHojaRutaComponentes().iterator(); iterator
						.hasNext();) {
					HojaRutaComponenteBean hojaRutaComponenteBean = (HojaRutaComponenteBean) iterator
							.next();

					if (!ordenBean.isEsManual()) {
						// si la orden es automática
						for (int i = 0; i < hojaRutaComponenteBean
								.getComponente().getFactorDosificacion().size(); i++) {
							if (hojaRutaComponenteBean.getComponente()
									.getFactorDosificacion().get(i)
									.getFactorDosificacionRegistroMensual() != null
									&& hojaRutaComponenteBean
											.getComponente()
											.getFactorDosificacion()
											.get(i)
											.getFactorDosificacionRegistroMensual()[indiceMes - 1] != null
									&& hojaRutaComponenteBean
											.getComponente()
											.getFactorDosificacion()
											.get(i)
											.getFactorDosificacionRegistroMensual()[indiceMes - 1]
											.getAnno() != null
									&& hojaRutaComponenteBean
											.getComponente()
											.getFactorDosificacion()
											.get(i)
											.getFactorDosificacionRegistroMensual()[indiceMes - 1]
											.getAnno()
											.equals(Integer.parseInt(valorAnio))) {
								dosificacionPlanificada = hojaRutaComponenteBean
										.getComponente()
										.getFactorDosificacion().get(i)
										.getFactorDosificacionRegistroMensual()[indiceMes - 1]
										.getCantidadRegistro();
								break;
							}
						}
					} else {
						// si la orden es manual

						if (ordenBean.getCodigo() != null) {
							// si es modificación
							Ordenproduccionmanual ordenProduccionManual = OrdenProduccionManualQuerier
									.findByOrdenProduccion(ordenBean
											.getCodigo());
							if (ordenProduccionManual != null
									&& ordenProduccionManual
											.getPkCodigoOrdenproduccionmanual() != null
									&& ordenProduccionManual
											.getConsumocomponentemanuals() != null
									&& ordenProduccionManual
											.getConsumocomponentemanuals()
											.size() > 0) {
								for (Iterator<Consumocomponentemanual> iterator2 = ordenProduccionManual
										.getConsumocomponentemanuals()
										.iterator(); iterator2.hasNext();) {
									Consumocomponentemanual consumoComponenteManual = (Consumocomponentemanual) iterator2
											.next();

									if (hojaRutaComponenteBean.getComponente()
											.getProductoComponente()
											.getCodigo() == consumoComponenteManual
											.getComponente()
											.getProductoByFkCodigoProductoComponente()
											.getPkCodigoProducto()) {

										dosificacionPlanificada = consumoComponenteManual
												.getCantidadConsumocomponentemanua();
										dosificacionEjecutada = consumoComponenteManual
												.getCantidadejecConsumocomponentem();
										break;
									}

								}
							}
						} else {
							// si es un registro nuevo

							for (int i = 0; i < hojaRutaComponenteBean
									.getComponente().getFactorDosificacion()
									.size(); i++) {
								if (hojaRutaComponenteBean.getComponente()
										.getFactorDosificacion().get(i)
										.getFactorDosificacionRegistroMensual() != null
										&& hojaRutaComponenteBean
												.getComponente()
												.getFactorDosificacion()
												.get(i)
												.getFactorDosificacionRegistroMensual()[indiceMes - 1] != null
										&& hojaRutaComponenteBean
												.getComponente()
												.getFactorDosificacion()
												.get(i)
												.getFactorDosificacionRegistroMensual()[indiceMes - 1]
												.getAnno() != null
										&& hojaRutaComponenteBean
												.getComponente()
												.getFactorDosificacion()
												.get(i)
												.getFactorDosificacionRegistroMensual()[indiceMes - 1]
												.getAnno()
												.equals(Integer
														.parseInt(valorAnio))) {
									dosificacionPlanificada = hojaRutaComponenteBean
											.getComponente()
											.getFactorDosificacion()
											.get(i)
											.getFactorDosificacionRegistroMensual()[indiceMes - 1]
											.getCantidadRegistro();
									break;
								}
							}

						}
					}

					produccionPlanificada = dosificacionPlanificada
							* ordenBean.getProduccionEstimada();

					DecimalFormat df = new DecimalFormat("###,###0.00");
					String cantidadProduccionPlanificada = df
							.format(produccionPlanificada);

					// se registran los componentes a la orden
					ComponenteRegistroOrdenBean compregistro = new ComponenteRegistroOrdenBeanImpl();
					compregistro.setComponente(hojaRutaComponenteBean
							.getComponente());
					compregistro.setUnidadmedida(hojaRutaComponenteBean
							.getComponente().getProductoComponente()
							.getUnidadMedida());

					compregistro
							.setDosificacionPlanificada(dosificacionPlanificada);
					compregistro
							.setDosificacionEjecutada(dosificacionEjecutada);
					compregistro.setProduccionEjecutada(produccionEjecutada);
					compregistro.setProduccionPlanificada(df.parse(
							cantidadProduccionPlanificada).doubleValue());

					Double porcentaje = 0.00;
					if (dosificacionPlanificada != 0
							&& compregistro.getDosificacionEjecutada() != 0)
						porcentaje = ((compregistro.getDosificacionEjecutada() * 100) / dosificacionPlanificada);

					compregistro.setPorcentaje(porcentaje);

					componentes.add(compregistro);

				}
			}
		} catch (ParseException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_COMPONENTES);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_COMPONENTES);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return componentes;
	}

	/**
	 * Carga los valores en la orden para visualizaci�n o registro.
	 * 
	 * @param usuario
	 * @param hojaBean
	 * @param produccionBean
	 * @param mes
	 * @param valorAnio
	 * @param proceso
	 * @return String
	 * @throws LogicaException
	 */
	private OrdenProduccionBean cargarDatosOrden(UsuarioBean usuario,
			HojaRutaBean hojaBean, ProduccionBean produccionBean, int mes,
			String valorAnio, Long proceso) throws LogicaException {
		OrdenProduccionBean ordenBean = new OrdenProduccionBeanImpl();
		try {
			EstadoOrdenProduccionBean edoOrdenBean = beanFactory
					.transformarEstadoOrdenProduccionBean(EstadoOrdenProduccionQuerier.getById(Long.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR))));
			ordenBean.setEstadoOrdenProduccion(edoOrdenBean);
			ordenBean.setUsuarioRegistro(usuario);
			ordenBean.setProduccion(produccionBean);
			ordenBean.setHojaRuta(hojaBean);
			ordenBean.setEsManual(true);
			Calendar c1 = Calendar.getInstance();
			ordenBean.setFechaRegistro(c1.getTime());
			ordenBean.setMes(mes);
			ordenBean.setNumeroOrden(valorAnio + " - " + mes + " - "
					+ hojaBean.getProducto().getCodigo());
			Double estimado = 0.0;
			ordenBean.setProduccionEstimada(estimado);
		} catch (AplicacionException e) {
			e.printStackTrace();
			String mensaje = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_CARGA_DATOS_ORDEN_PRODUCCION);
			throw new LogicaException(mensaje, e);
		}

		return ordenBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #grabarOrden(pe.com.pacasmayo.sgcp.bean.HojaRutaBean,
	 * pe.com.pacasmayo.sgcp.bean.UsuarioBean, java.lang.String,
	 * java.lang.String, pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	public void grabarOrden(HojaRutaBean hojaBean, UsuarioBean usuario,
			String valorAnio, String valorMes, OrdenProduccionBean ordenbean)
			throws LogicaException {

		Integer anio = Integer.parseInt(valorAnio);
		Short mes = mesToShort(valorMes);
		String mensajeError = "";
		Session session = null;
		Transaction tx = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			Hojaruta hoja = HojaRutaQuerier.getById(hojaBean.getCodigo());

			Estadoordenproduccion estadoOrdenProd = EstadoOrdenProduccionQuerier
					.getById(Long.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR)));

			Tipocapacidadoperativa tipoCapacidadOperativaTonelada = TipoCapacidadOperativaQuerier
					.getById(CODIGO_TIPO_CAPACIDAD_OPERATIVA_TONELADAS);

			Tipocapacidadoperativa tipoCapacidadOperativaDia = TipoCapacidadOperativaQuerier
					.getById(CODIGO_TIPO_CAPACIDAD_OPERATIVA_DIAS);

			Usuario usuarioReg = UsuarioQuerier.getById(usuario.getCodigo());

			Ordenproduccion ordenProduccion = new Ordenproduccion();

			if (ordenbean.getCodigo() != null)
				ordenProduccion = OrdenProduccionQuerier.getById(ordenbean
						.getCodigo());

			if (ordenProduccion != null
					&& ordenProduccion.getPkCodigoOrdenproduccion() == null) {
				ordenProduccion.setUsuarioByFkCodigoUsuarioRegistro(usuarioReg);

				if (!ordenbean
						.getEstadoOrdenProduccion()
						.getCodigo()
						.equals(Long.parseLong(ManejadorPropiedades
								.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR)))) {
					ordenProduccion.setFechaAprobacionOrdenproduccio(ordenbean
							.getFechaAprobacion());
				}
			} else {
				if (!ordenbean
						.getEstadoOrdenProduccion()
						.getCodigo()
						.equals(Long.parseLong(ManejadorPropiedades
								.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR)))) {
					Usuario usuarioAprueba = UsuarioQuerier.getById(ordenbean
							.getUsuarioAprueba().getCodigo());
					ordenProduccion
							.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprueba);
					ordenProduccion.setFechaAprobacionOrdenproduccio(ordenbean
							.getFechaAprobacion());
				}
			}

			ordenProduccion.setUsuarioByFkCodigoUsuarioAprueba(usuarioReg);

			String mesOrden = String.valueOf(ordenbean.getMes());

			ordenProduccion.setFechaRegistroOrdenproduccion(ordenbean
					.getFechaRegistro());
			ordenProduccion.setHojaruta(hoja);
			ordenProduccion.setEstadoordenproduccion(estadoOrdenProd);
			ordenProduccion.setMesOrdenproduccion(Short.parseShort(mesOrden));
			ordenProduccion.setNumeroDocumentoOrdenproduccio(ordenbean
					.getNumeroDocumento());
			ordenProduccion.setNumeroOrdenOrdenproduccion(ordenbean
					.getNumeroOrden());
			ordenProduccion.setProduccion(hoja.getProduccion());
			ordenProduccion.setProduccionEstimadaOrdenproduc(ordenbean
					.getProduccionEstimada());

			ordenProduccion.setProduccionEjecutadaOrdenprodu(ordenbean
					.getProduccionEjecutada());

			List<Ordenproduccion> listaOP = OrdenProduccionQuerier
					.getByMesAnioYProduccion(ordenProduccion
							.getMesOrdenproduccion(), hoja.getProduccion()
							.getPkProduccion(), anio);
			if (listaOP != null)
				if (listaOP.size() <= 0) {
					OrdenProduccionQuerier.saveOrUpdate(ordenProduccion);
				} else {
					mensajeError = ManejadorPropiedades
							.obtenerPropiedadPorClave(ORDEN_PRODUCCION_ES_PARA_UN_PRODUCTO);
					logger.error(mensajeError);
					throw new LogicaException(mensajeError);
				}

			Ordenproduccionmanual ordenProduccionManual = new Ordenproduccionmanual();
			if (ordenbean.getCodigo() != null) {
				ordenProduccionManual = OrdenProduccionManualQuerier
						.findByOrdenProduccionYAnnio(
								ordenProduccion.getPkCodigoOrdenproduccion(),
								Integer.parseInt(valorAnio));

				// se eliminan todos los consumos capacidad manual
				ConsumoCapacidadManualQuerier
						.deleteByCodigoOrdenProduccionManual(ordenProduccionManual
								.getPkCodigoOrdenproduccionmanual());

				// se eliminan todos los consumos componente manual
				ConsumoComponenteManualQuerier
						.deleteByCodigoOrdenProduccionManual(ordenProduccionManual
								.getPkCodigoOrdenproduccionmanual());

				// se eliminan todos los consumos recurso manual
				ConsumoRecursoManualQuerier
						.deleteByCodigoOrdenProduccionManual(ordenProduccionManual
								.getPkCodigoOrdenproduccionmanual());
			}
			ordenProduccionManual.setAnnoOrdenproduccionmanual(anio);
			ordenProduccionManual.setOrdenproduccion(ordenProduccion);

			// se almacena la orden de producci�n manual
			OrdenProduccionManualQuerier.saveOrUpdate(ordenProduccionManual);

			// almacena Consumo Capacidad Manual
			guardaConsumoCapacidadManual(ordenbean, mes,
					tipoCapacidadOperativaTonelada, tipoCapacidadOperativaDia,
					ordenProduccionManual);

			if (ordenbean.getListaComponenteOrdenProduccion() != null) {
				// almacena Consumo Componente Plan
				guardaConsumoComponenteManual(ordenbean, ordenProduccionManual);
			}

			if (ordenbean.getListaRecursoOrdenProduccion() != null) {
				// almacena Consumo Recurso Plan
				guardaConsumoRecursoManual(ordenbean, ordenProduccionManual);
			}

			tx.commit();
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_REGISTRAR);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_ORDEN_PRODUCCION_REGISTRAR);
			if (tx != null)
				tx.rollback();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * @param ordenbean
	 * @param mes
	 * @param tipoCapacidadOperativaTonelada
	 * @param tipoCapacidadOperativaDia
	 * @param ordenProduccionManual
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 */
	private void guardaConsumoCapacidadManual(OrdenProduccionBean ordenbean,
			Short mes, Tipocapacidadoperativa tipoCapacidadOperativaTonelada,
			Tipocapacidadoperativa tipoCapacidadOperativaDia,
			Ordenproduccionmanual ordenProduccionManual)
			throws ElementoNoEncontradoException, ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException {
		for (int i = 0; i < ordenbean.getListaOperacionOrdenProduccionBean()
				.size(); i++) {
			OperacionBean operacionBean = ordenbean
					.getListaOperacionOrdenProduccionBean().get(i);

			Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier
					.getById(operacionBean.getPuestoTrabajo().getCodigo());

			// se registran los consumo capacidad manual de tipo Tonelada
			Consumocapacidadmanual consumoCapTon = new Consumocapacidadmanual();

			consumoCapTon.setOrdenproduccionmanual(ordenProduccionManual);
			consumoCapTon.setPuestotrabajo(puestoTrabajo);
			consumoCapTon.setCantidadConsumocapmanual(0.00);

			if (operacionBean.getListaCapacidadOperativaRMTon()[mes.intValue() - 1] != null) {
				consumoCapTon.setCantidadConsumocapmanual(operacionBean
						.getListaCapacidadOperativaRMTon()[mes.intValue() - 1]
						.getCantidadCapacidadOperativaRegistroMensual()
						.doubleValue());
				consumoCapTon
						.setTipocapacidadoperativa(tipoCapacidadOperativaTonelada);
			}

			consumoCapTon.setCantidadejecConsumocapmanual(0.00);

			ConsumoCapacidadManualQuerier.save(consumoCapTon);

			// se registran los consumo capacidad manual de tipo Dias
			Consumocapacidadmanual consumoCapDia = new Consumocapacidadmanual();

			consumoCapDia.setOrdenproduccionmanual(ordenProduccionManual);
			consumoCapDia.setPuestotrabajo(puestoTrabajo);

			if (operacionBean.getListaCapacidadOperativaRMDias()[mes.intValue() - 1] != null) {
				consumoCapDia.setCantidadConsumocapmanual(operacionBean
						.getListaCapacidadOperativaRMDias()[mes.intValue() - 1]
						.getCantidadCapacidadOperativaRegistroMensual()
						.doubleValue());
				consumoCapDia
						.setTipocapacidadoperativa(tipoCapacidadOperativaDia);
			}

			consumoCapDia.setCantidadejecConsumocapmanual(0.00);

			ConsumoCapacidadManualQuerier.save(consumoCapDia);

		}
	}

	/**
	 * @param ordenbean
	 * @param ordenProduccionManual
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 */
	private void guardaConsumoRecursoManual(OrdenProduccionBean ordenbean,
			Ordenproduccionmanual ordenProduccionManual)
			throws ElementoNoEncontradoException, ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException {
		for (int i = 0; i < ordenbean.getListaRecursoOrdenProduccion().size(); i++) {
			RecursoBean recursoBean = ordenbean
					.getListaRecursoOrdenProduccion().get(i);

			Recurso recurso = RecursoQuerier.getById(recursoBean.getCodigo());

			Consumorecursomanual consumoRecursoManual = new Consumorecursomanual();
			consumoRecursoManual.setCantidadConsumo(recursoBean
					.getConsumoPlanificado());
			consumoRecursoManual.setCantidadejecConsumorecursomanu(0.00);
			consumoRecursoManual
					.setOrdenproduccionmanual(ordenProduccionManual);
			consumoRecursoManual.setRecurso(recurso);

			ConsumoRecursoManualQuerier.save(consumoRecursoManual);
		}
	}

	/**
	 * @param ordenbean
	 * @param ordenProduccionManual
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 */
	private void guardaConsumoComponenteManual(OrdenProduccionBean ordenbean,
			Ordenproduccionmanual ordenProduccionManual)
			throws ElementoNoEncontradoException, ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException {
		for (int i = 0; i < ordenbean.getListaComponenteOrdenProduccion()
				.size(); i++) {
			ComponenteRegistroOrdenBean componenteRegistroOrdenBean = ordenbean
					.getListaComponenteOrdenProduccion().get(i);

			Consumocomponentemanual consumoComponenteManual = new Consumocomponentemanual();
			consumoComponenteManual
					.setCantidadConsumocomponentemanua(componenteRegistroOrdenBean
							.getDosificacionPlanificada());
			consumoComponenteManual.setCantidadejecConsumocomponentem(0.00);

			Componente componente = ComponenteQuerier
					.getById(componenteRegistroOrdenBean.getComponente()
							.getCodigo());

			consumoComponenteManual.setComponente(componente);
			consumoComponenteManual
					.setOrdenproduccionmanual(ordenProduccionManual);

			ConsumoComponenteManualQuerier.save(consumoComponenteManual);

		}
	}

	/**
	 * Método para obtener la lista de ordenes de la consulta
	 * 
	 * @param línea
	 * @param proceso
	 * @param valorAnio
	 * @param valorUnidad
	 * @param lista
	 * @param retornolista
	 * @return
	 * @throws AplicacionException
	 */
	private void obtenerOrdenConsulta(String valorEstado, Lineanegocio linea,
			Proceso proceso, String valorAnio, String valorUnidad,
			List<OrdenProduccionBean> lista,
			List<OrdenProdConsultaBean> retornolista) {

		for (Iterator<OrdenProduccionBean> iterlista = lista.iterator(); iterlista
				.hasNext();) {
			OrdenProduccionBean ordenBean = (OrdenProduccionBean) iterlista
					.next();

			OrdenProdConsultaBean ordenConsulta = new OrdenProdConsultaBeanImpl();
			if (ordenBean.getMes() >= 10) {
				ordenConsulta.setAnio_mes(valorAnio + " - "
						+ ordenBean.getMes());
			} else {
				ordenConsulta.setAnio_mes(valorAnio + " - 0"
						+ ordenBean.getMes());
			}
			ordenConsulta.setCodigo(ordenBean.getCodigo());
			ordenConsulta.setEsManual(ordenBean.isEsManual());
			if (ordenConsulta.isEsManual()) {
				ordenConsulta.setTipo('M');
			} else {
				ordenConsulta.setTipo('A');
			}

			if (valorEstado.trim().length() > 0
					&& !ordenBean.getEstadoOrdenProduccion().getCodigo()
							.toString().equals(valorEstado))
				continue;

			ordenConsulta.setEstadoOrdenProduccion(ordenBean
					.getEstadoOrdenProduccion());
			ordenConsulta.setFechaRegistro(ordenBean.getFechaRegistro());
			ordenConsulta.setHojaRuta(ordenBean.getHojaRuta());
			ordenConsulta.setLineaneg(linea.getNombreLineanegocio());
			ordenConsulta.setMes(ordenBean.getMes());
			ordenConsulta.setNumeroDocumento(ordenBean.getNumeroDocumento());
			ordenConsulta.setNumeroOrden(ordenBean.getNumeroOrden());
			ordenConsulta.setProceso(proceso.getNombreProceso());
			ordenConsulta.setProduccion(ordenBean.getProduccion());
			ordenConsulta.setProduccionEjecutada(ordenBean
					.getProduccionEjecutada());
			ordenConsulta.setProduccionEstimada(ordenBean
					.getProduccionEstimada());
			Double p100 = 0.0;

			if (ordenBean.getProduccionEjecutada() != null
					&& ordenBean.getProduccionEjecutada() > 0
					&& ordenBean.getProduccionEstimada() != null
					&& ordenBean.getProduccionEstimada() > 0) {
				p100 = (ordenBean.getProduccionEjecutada() * 100)
						/ ordenBean.getProduccionEstimada();
			}

			ordenConsulta.setPorcentaje(p100);
			ordenConsulta.setProducto(ordenBean.getProduccion().getProducto()
					.getNombre());
			ordenConsulta.setUnidad(valorUnidad);
			ordenConsulta.setUsuarioRegistro(ordenBean.getUsuarioRegistro());
			ordenConsulta.setUsuarioR(ordenBean.getUsuarioRegistro()
					.getNombreCompleto());
			if (ordenBean
					.getEstadoOrdenProduccion()
					.getCodigo()
					.compareTo(
							Long.parseLong(ManejadorPropiedades
									.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR))) != 0) {
				ordenConsulta
						.setFechaAprobacion(ordenBean.getFechaAprobacion());
				ordenConsulta.setUsuarioAprueba(ordenBean.getUsuarioAprueba());
				String usuarioA = ordenBean.getUsuarioAprueba()
						.getNombreCompleto();
				ordenConsulta.setUsuarioA(usuarioA);
			}
			retornolista.add(ordenConsulta);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #modificarOrden(java.lang.Long)
	 */
	public OrdenProdConsultaBean modificarOrden(Long codigoordenProdConsulta)
			throws LogicaException {

		Session session = null;
		String mensajeError = "";

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

		OrdenProdConsultaBean ordenConsulta = new OrdenProdConsultaBeanImpl();
		try {
			Ordenproduccion orden = OrdenProduccionQuerier
					.getById(codigoordenProdConsulta);
			Ordenproduccionplan ordenPlan = OrdenProduccionPlanQuerier
					.findByOrdenProduccion(codigoordenProdConsulta);
			Lineanegocio lineaneg = new Lineanegocio();
			if (ordenPlan != null) {
				Plananual plan = ordenPlan.getPlananual();
				lineaneg = plan.getLineanegocio();
				ordenConsulta.setAnio(plan.getAnnoPlananual().toString());
				ordenConsulta.setEsManual(false);
			} else {
				Ordenproduccionmanual ordenMan = OrdenProduccionManualQuerier
						.findByOrdenProduccion(codigoordenProdConsulta);
				if (ordenMan != null) {
					ordenConsulta.setEsManual(true);

					ordenConsulta.setAnio(ordenMan
							.getAnnoOrdenproduccionmanual().toString());
				}
			}

			Hojaruta hoja = orden.getHojaruta();
			Produccion produccion = hoja.getProduccion();
			Proceso proceso = produccion.getProceso();
			lineaneg = proceso.getLineanegocio();
			Unidad unidad = lineaneg.getUnidad();
			Sociedad sociedad = unidad.getSociedad();
			Division div = sociedad.getDivision();
			Producto producto = produccion.getProducto();

			ordenConsulta.setCodigo(orden.getPkCodigoOrdenproduccion());
			ordenConsulta.setDivision(div.getNombreDivision());
			ordenConsulta.setSociedad(sociedad.getNombreSociedad());
			ordenConsulta.setUnidad(unidad.getNombreUnidad());

			ordenConsulta.setLineaneg(lineaneg.getNombreLineanegocio());
			ordenConsulta.setEstadoOrdenProduccion(beanFactory
					.transformarEstadoOrdenProduccionBean(orden
							.getEstadoordenproduccion()));
			ordenConsulta.setProceso(proceso.getNombreProceso());
			ordenConsulta.setProducto(producto.getNombreProducto());
			ordenConsulta.setCodigoProducto(producto.getPkCodigoProducto());
			ordenConsulta.setMes(orden.getMesOrdenproduccion().intValue());
			ordenConsulta.setHojaRuta(beanFactory
					.transformarHojaRutaParaConsultaOrdenProduccion(hoja));
			ordenConsulta.setNumeroDocumento(orden
					.getNumeroDocumentoOrdenproduccio());
			ordenConsulta.setNumeroOrden(orden.getNumeroOrdenOrdenproduccion());
			ordenConsulta.setProduccionEstimada(orden
					.getProduccionEstimadaOrdenproduc());
			ordenConsulta.setProduccionEjecutada(orden
					.getProduccionEjecutadaOrdenprodu());
			UsuarioBean usuarioRegistro = beanFactory.transformarUsuario(orden
					.getUsuarioByFkCodigoUsuarioRegistro());
			ordenConsulta.setUsuarioRegistro(usuarioRegistro);
			ordenConsulta.setUsuarioR(usuarioRegistro.getNombreCompleto());
			ordenConsulta.setFechaRegistro(orden
					.getFechaRegistroOrdenproduccion());
			if (orden
					.getEstadoordenproduccion()
					.getPkCodigoEstadoorden()
					.compareTo(
							Long.parseLong(ManejadorPropiedades
									.obtenerPropiedadPorClave(CODIGO_ESTADO_ORDEN_PRODUCCION_PRELIMINAR))) != 0) {
				UsuarioBean usuarioAprueba = beanFactory
						.transformarUsuario(orden
								.getUsuarioByFkCodigoUsuarioAprueba());
				ordenConsulta.setUsuarioAprueba(usuarioAprueba);
				ordenConsulta.setUsuarioA(usuarioAprueba.getNombreCompleto());
				ordenConsulta.setFechaAprobacion(orden
						.getFechaAprobacionOrdenproduccio());
			}

		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje(), e);
			throw new LogicaException(ERROR_ORDEN_PRODUCCION_CONSULTA, e);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje(), e);
			throw new LogicaException(ERROR_ORDEN_PRODUCCION_CONSULTA, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return ordenConsulta;
	}

	/**
	 * M�todo para obtener el n�mero del mes partiendo del nombre
	 * 
	 * @param valorMes
	 * @return mes
	 */
	private Short mesToShort(String valorMes) {
		Short mes = null;
		if (valorMes.compareTo("Enero") == 0) {
			mes = 1;
		}
		if (valorMes.compareTo("Febrero") == 0) {
			mes = 2;
		}
		if (valorMes.compareTo("Marzo") == 0) {
			mes = 3;
		}
		if (valorMes.compareTo("Abril") == 0) {
			mes = 4;
		}
		if (valorMes.compareTo("Mayo") == 0) {
			mes = 5;
		}
		if (valorMes.compareTo("Junio") == 0) {
			mes = 6;
		}
		if (valorMes.compareTo("Julio") == 0) {
			mes = 7;
		}
		if (valorMes.compareTo("Agosto") == 0) {
			mes = 8;
		}
		if (valorMes.compareTo("Septiembre") == 0) {
			mes = 9;
		}
		if (valorMes.compareTo("Octubre") == 0) {
			mes = 10;
		}
		if (valorMes.compareTo("Noviembre") == 0) {
			mes = 11;
		}
		if (valorMes.compareTo("Diciembre") == 0) {
			mes = 12;
		}
		return mes;
	}

	/*
	 * (non-Javadoc) Obtiene �rdenes de producción por propiedades
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #obtenerOrdenesProduccionPorPropiedades(java.util.Map)
	 */
	public List<OrdenProduccionBean> obtenerOrdenesProduccionPorPropiedades(
			Map<?, ?> propiedades) throws LogicaException {

		List<OrdenProduccionBean> lista = null;

		Session session = null;
		String mensajeError = "";

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
			session = PersistenciaFactory.currentSession();

			lista = beanFactory
					.transformarListaOrdenProduccion(OrdenProduccionQuerier
							.findByProperties(propiedades));
		} catch (HibernateException e) {
			throw new ErrorConexionException(ERROR_HIBERNATE, e);
		} catch (RuntimeException e) {
			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lista;
	}

	/*
	 * (non-Javadoc) Obtiene atributos de las �rdenes de producci�n
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #obtenerAtributos()
	 */
	public List<UtilBean> obtenerAtributos() {
		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();
		filtro.setCodigo(1);
		filtro.setValor(FILTRO_UTILBEAN_CODIGO);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc) Obtiene el DTO de la orden de producci�n
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #obtenerOrdenProduccionDataObject()
	 */
	public Ordenproduccion obtenerOrdenProduccionDataObject(
			Integer codigoOrdenProd) throws LogicaException {

		String mensajeError = "";
		Session session = null;
		Ordenproduccion orden = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long codigo = new Long(codigoOrdenProd);
			orden = OrdenProduccionQuerier.getById(codigo);
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
		} catch (HibernateException e) {
			throw new ErrorConexionException(ERROR_HIBERNATE, e);
		} catch (RuntimeException e) {
			throw new LogicaException(e.getMessage(), e);
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMessage(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return orden;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #obtenerOrdenesProduccionPorMesDatosProcesoProducto(java.lang.Short)
	 */
	public List<OrdenProduccionBean> obtenerOrdenesProduccionPorMesDatosProcesoProducto(
			Short mesOrdenProduccion) throws LogicaException {
		String mensajeError = "";
		List<OrdenProduccionBean> listaOrdenes = null;
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			listaOrdenes = beanFactory
					.transformarListaOrdenProduccionBasico(OrdenProduccionQuerier
							.findByMes(mesOrdenProduccion));
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
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_OBTENER_ORDEN_PRODUCCION);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return listaOrdenes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogicFacade
	 * #getOrdenesDTOByMesYProceso(int, java.lang.Long, java.lang.Integer)
	 */
	public List<OrdenProduccionDTO> getOrdenesByMesPlantillaLiberadas(
			Integer mes, Integer annio, Long codigoPlantillaReporte)
			throws LogicaException {

		List<Ordenproduccion> ordenes = null;
		List<OrdenProduccionDTO> ordenesDTO = null;
		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			// no se pueden ordenar estas ordenes por el nombre, se va a tener
			// que hacer luego de hacer la consulta.
			Long estadoPlanAnual = Long
					.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(PLANIFICACION_CODIGO_ESTADO_PLAN_APROBADO));
			ordenes = OrdenProduccionQuerier.getByMesLiberadaPlantilla(mes,
					annio, codigoPlantillaReporte, estadoPlanAnual);

			Collections.sort(ordenes, new Comparator<Ordenproduccion>() {
				public int compare(Ordenproduccion o1, Ordenproduccion o2) {
					return o1
							.getProduccion()
							.getProducto()
							.getNombreProducto()
							.compareToIgnoreCase(
									o2.getProduccion().getProducto()
											.getNombreProducto());
				}

			});

			ordenesDTO = new ArrayList<OrdenProduccionDTO>();
			for (Iterator<Ordenproduccion> iterator = ordenes.iterator(); iterator
					.hasNext();) {
				Ordenproduccion orden = iterator.next();
				OrdenProduccionDTO ordenDTO = new OrdenProduccionDTO();

				ordenDTO.setPkCodigoOrdenproduccion(orden
						.getPkCodigoOrdenproduccion());

				ordenDTO.setFechaAprobacionOrdenproduccio(orden
						.getFechaAprobacionOrdenproduccio());

				ordenDTO.setFechaRegistroOrdenproduccion(orden
						.getFechaRegistroOrdenproduccion());

				ordenDTO.setMesOrdenproduccion(orden.getMesOrdenproduccion());

				ordenDTO.setNumeroDocumentoOrdenproduccio(orden
						.getNumeroDocumentoOrdenproduccio());

				ordenDTO.setNumeroOrdenOrdenproduccion(orden
						.getNumeroOrdenOrdenproduccion());

				ordenDTO.setProduccionEjecutadaOrdenprodu(orden
						.getProduccionEjecutadaOrdenprodu());

				ordenDTO.setProduccionEstimadaOrdenproduc(orden
						.getProduccionEstimadaOrdenproduc());

				ordenDTO.setProduccion((ProduccionDTO)beanDozerFactory.transformarBean(orden
						.getProduccion(), ProduccionDTO.class));
//						.convertirProduccionAProduccionDTO(orden
//								.getProduccion()));

				ordenesDTO.add(ordenDTO);

				ordenDTO.setNombreProducto(orden.getProduccion().getProducto()
						.getNombreProducto());

			}
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
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_OBTENER_ORDEN_PRODUCCION);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return ordenesDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.facade.OrdenProduccionLogicFacade#
	 * obtenerOrdenProduccionDTO(java.lang.Integer)
	 */
	public OrdenProduccionDTO obtenerOrdenProduccionDTO(Integer codigoOrdenProd)
			throws LogicaException {
		String mensajeError = "";
		OrdenProduccionDTO ordenProduccionDTO = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Long codigo = new Long(codigoOrdenProd);
			Ordenproduccion orden = OrdenProduccionQuerier.getById(codigo);
			
			ordenProduccionDTO = (OrdenProduccionDTO) beanDozerFactory.transformarBean(orden, OrdenProduccionDTO.class);
			
		} catch (ElementoNoEncontradoException e) {
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (HibernateException e) {
			logger.error(mensajeError, e);
			throw new ErrorConexionException(ERROR_HIBERNATE, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return ordenProduccionDTO;
	}

	public void cambiarEstadoOrdenesProduccion(Integer valorAno, Short valorMes) {

		Transaction tx = null;
		Session sesion = null;

		try {

			sesion = PersistenciaFactory.currentSession();
			tx = sesion.beginTransaction();

			List<Ordenproduccion> ordenesProduccion = OrdenProduccionPlanQuerier
					.findOrdenProduccionByAnoMes(valorMes, valorAno);
			Estadoordenproduccion estadoordenproduccion = new Estadoordenproduccion();
			Long pkCodigoEstadoorden = Long
					.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(ConstantesLogicaNegocio.CODIGO_ESTADO_ORDEN_PRODUCCION_CERRADA));
			estadoordenproduccion.setPkCodigoEstadoorden(pkCodigoEstadoorden);
			for (Ordenproduccion ordenproduccion : ordenesProduccion) {
				ordenproduccion.setEstadoordenproduccion(estadoordenproduccion);
				OrdenProduccionQuerier.update(ordenproduccion);
			}

			tx.commit();

		} catch (ElementoNoEncontradoException e) {

			if (tx != null)
				tx.rollback();

		} catch (ParametroInvalidoException e) {

			if (tx != null)
				tx.rollback();

		} catch (ElementoExistenteException e) {

			if (tx != null)
				tx.rollback();

		} catch (ElementoEliminadoException e) {
			if (tx != null)
				tx.rollback();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

		} finally {
			if (sesion != null)
				PersistenciaFactory.closeSession(sesion);
		}

	}

	public List<OrdenProduccionDTO> getOrdenesByMesPlantillaHistoricas(
			Integer mes, Integer annio, Long codigoPlantillaReporte)
			throws LogicaException {

		List<Ordenproduccion> ordenes = null;
		List<OrdenProduccionDTO> ordenesDTO = null;
		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			// no se pueden ordenar estas ordenes por el nombre, se va a tener
			// que hacer luego de hacer la consulta.
			Long estadoPlanAnual = Long
					.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(PLANIFICACION_CODIGO_ESTADO_PLAN_HISTORICO));
			ordenes = OrdenProduccionQuerier.getByMesLiberadaPlantilla(mes,
					annio, codigoPlantillaReporte, estadoPlanAnual);

			Collections.sort(ordenes, new Comparator<Ordenproduccion>() {
				public int compare(Ordenproduccion o1, Ordenproduccion o2) {
					return o1
							.getProduccion()
							.getProducto()
							.getNombreProducto()
							.compareToIgnoreCase(
									o2.getProduccion().getProducto()
											.getNombreProducto());
				}

			});

			ordenesDTO = new ArrayList<OrdenProduccionDTO>();
			for (Iterator<Ordenproduccion> iterator = ordenes.iterator(); iterator
					.hasNext();) {
				Ordenproduccion orden = iterator.next();
				OrdenProduccionDTO ordenDTO = new OrdenProduccionDTO();

				ordenDTO.setPkCodigoOrdenproduccion(orden
						.getPkCodigoOrdenproduccion());

				ordenDTO.setFechaAprobacionOrdenproduccio(orden
						.getFechaAprobacionOrdenproduccio());

				ordenDTO.setFechaRegistroOrdenproduccion(orden
						.getFechaRegistroOrdenproduccion());

				ordenDTO.setMesOrdenproduccion(orden.getMesOrdenproduccion());

				ordenDTO.setNumeroDocumentoOrdenproduccio(orden
						.getNumeroDocumentoOrdenproduccio());

				ordenDTO.setNumeroOrdenOrdenproduccion(orden
						.getNumeroOrdenOrdenproduccion());

				ordenDTO.setProduccionEjecutadaOrdenprodu(orden
						.getProduccionEjecutadaOrdenprodu());

				ordenDTO.setProduccionEstimadaOrdenproduc(orden
						.getProduccionEstimadaOrdenproduc());

				// ordenDTO.setProduccion(ConvertidorHibernateDTO.convertirProduccionAProduccionDTO(orden.getProduccion()));
				ordenesDTO.add(ordenDTO);

				ordenDTO.setNombreProducto(orden.getProduccion().getProducto()
						.getNombreProducto());

			}
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
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_OBTENER_ORDEN_PRODUCCION);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return ordenesDTO;
	}

}