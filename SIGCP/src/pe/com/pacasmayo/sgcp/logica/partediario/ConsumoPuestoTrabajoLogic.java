package pe.com.pacasmayo.sgcp.logica.partediario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.ConsumoPuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorconsumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorvariacionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoGeneradoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroVarCalidadDTO;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoPuestoTrabajoLogic.java
 * Modificado: Aug 9, 2011 9:40:38 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ConsumoPuestoTrabajoLogic implements ConsumoPuestoTrabajoLogicFacade, ConstantesMensajeAplicacion {
	private Log logger = LogFactory.getLog(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.ConsumoPuestoTrabajoLogicFacade#
	 * cargarDetalleVarablesCalidad(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Integer, java.lang.Short)
	 */

	public List<RegistroVarCalidadDTO> cargarDetalleVarablesCalidad(Long codigoPuestoTrabajo, Long codProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			List<Consumopuestotrabajo> consumos = ConsumoPuestoTrabajoQuerier.obtenerRegistrosPorLineaNegPuestoProductoAnnioYMes(
					codigoPuestoTrabajo, codProducto, codigoLineaNegocio, anno, mes);

			List<RegistroVarCalidadDTO> DTOs = transformarConsumosPuestoTrabajoDTO(consumos);

			Collections.sort(DTOs, new Comparator<RegistroVarCalidadDTO>() {

				public int compare(RegistroVarCalidadDTO o1, RegistroVarCalidadDTO o2) {
					return o1.getFecha().compareTo(o2.getFecha());
				}
			});

			return DTOs;
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public Double hallarPoderCalorficoCarbonPonderado(Long codigoPuestoTrabajo, Long codProducto, Long codigoLineaNegocio,
			Integer anno, Short mes) throws LogicaException {

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

			return hallarPoderCalorficoCarbonPonderadoDAO(codigoPuestoTrabajo, codProducto, codigoLineaNegocio, anno, mes);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public Double hallarPoderCalorficoCarbonPonderadoDAO(Long codigoPuestoTrabajo, Long codProducto, Long codigoLineaNegocio,
			Integer anno, Short mes) throws LogicaException {
		// Este metodo solo es para utilizar en otros Logic que manejan sesion
		try {
			List<Consumopuestotrabajo> consumos = ConsumoPuestoTrabajoQuerier.obtenerRegistrosPorLineaNegPuestoProductoAnnioYMes(
					codigoPuestoTrabajo, codProducto, codigoLineaNegocio, anno, mes);
			List<RegistroVarCalidadDTO> DTOs = transformarConsumosPuestoTrabajoDTO(consumos);

			List<Object[]> consumosPorPT = ConsumoPuestoTrabajoQuerier.obtenerConsumoPuestoTrabajoCarbon(codProducto,
					codigoLineaNegocio, codigoPuestoTrabajo, mes, anno, Boolean.TRUE);

			Double sumaPoderCalorifico = 0d;
			Double sumaConsumosCarbones = 0d;

			for (int i = 0; i < consumosPorPT.size(); i++) {
				String nombreProducto = consumosPorPT.get(i)[2].toString();
				Double consumo = Double.valueOf(consumosPorPT.get(i)[3].toString());

				Double poderCalorificoCarMix = obtenerPorcPoderCalorifico(DTOs, nombreProducto);

				sumaPoderCalorifico += poderCalorificoCarMix * consumo;
				sumaConsumosCarbones += consumo;
			}

			return NumberUtil.dividir(sumaPoderCalorifico, sumaConsumosCarbones);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}

	}

	private Double obtenerPorcPoderCalorifico(List<RegistroVarCalidadDTO> DTOs, String carbon) {
		Double porcCarMix = 0d;
		int contC = 0;
		String i = "\u00ED"; // UNICODE

		for (RegistroVarCalidadDTO registroVarCalidad : DTOs) {
			Set<String> clavesVariablesCalidad = registroVarCalidad.getVariablesCalidad().keySet();

			for (String clavesVariableCal : clavesVariablesCalidad) {
				Map<String, Double> mapaVariables = registroVarCalidad.getVariablesCalidad().get(clavesVariableCal);
				Set<String> variables = mapaVariables.keySet();
				for (String variable : variables) {
					if (clavesVariableCal.compareTo(carbon) == 0) {

						if (variable.indexOf("Poder Calor" + i + "f") == 0) {
							porcCarMix += mapaVariables.get(variable);
							contC++;
						}
					}
				}
			}
		}
		return NumberUtil.reducirAdosDecimales(porcCarMix / contC);
	}

	@SuppressWarnings("unchecked")
	private List<RegistroVarCalidadDTO> transformarConsumosPuestoTrabajoDTO(List<Consumopuestotrabajo> consumos) {
		Map<Date, List<Consumopuestotrabajo>> map = new HashMap<Date, List<Consumopuestotrabajo>>();

		for (Consumopuestotrabajo consumo : consumos) {
			Date fechaTablaoperacion = consumo.getProductogenerado().getTablaoperacion().getFechaTablaoperacion();
			if (map.containsKey(fechaTablaoperacion)) {
				map.get(fechaTablaoperacion).add(consumo);
			} else {
				map.put(fechaTablaoperacion, new ArrayList<Consumopuestotrabajo>());
				map.get(fechaTablaoperacion).add(consumo);
			}
		}

		List<RegistroVarCalidadDTO> listaregistros = new ArrayList<RegistroVarCalidadDTO>();

		for (Date fecha : map.keySet()) {
			RegistroVarCalidadDTO reg = new RegistroVarCalidadDTO();
			reg.setFecha(fecha);
			List<Consumopuestotrabajo> list = map.get(fecha);
			for (Consumopuestotrabajo consumopuestotrabajo : list) {

				String nombreProducto = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente()
						.getNombreProducto();
				Set<Factorconsumopuestotrabajo> factores = consumopuestotrabajo.getFactorconsumopuestotrabajos();

				Map<String, Double> variablesCalidad = new HashMap<String, Double>();
				for (Factorconsumopuestotrabajo factorconsumopuestotrabajo : factores) {
					String descripSgcpVarCalidad = factorconsumopuestotrabajo.getFkCodigoEquivalenciasccvarcalidad()
							.getDescripSgcpVarCalidad();
					if (factorconsumopuestotrabajo.getValorFactorconsumopuestotrabajo() > 0.00d) {
						variablesCalidad.put(descripSgcpVarCalidad,
								factorconsumopuestotrabajo.getValorFactorconsumopuestotrabajo());
					}
				}

				reg.getVariablesCalidad().put(nombreProducto, variablesCalidad);

				Set<Factorvariacionpuestotrabajo> factoresVariacion = consumopuestotrabajo.getFactorvariacionpuestotrabajos();

				Map<String, Double> variablesVariacion = new HashMap<String, Double>();
				for (Factorvariacionpuestotrabajo factorvariacionpuestotrabajo : factoresVariacion) {
					String nombreVariablevariacion = factorvariacionpuestotrabajo.getProductovariablevariacion()
							.getVariablevariacion().getNombreVariablevariacion();
					Double valorVariablevariacionFactorv = factorvariacionpuestotrabajo.getValorVariablevariacionFactorv();
					if (valorVariablevariacionFactorv > 0.00d) {
						variablesVariacion.put(nombreVariablevariacion, valorVariablevariacionFactorv);
					}
				}
				reg.getVariablesVariacion().put(nombreProducto, variablesVariacion);
			}
			listaregistros.add(reg);
		}

		Collections.sort(listaregistros, new Comparator<RegistroVarCalidadDTO>() {
			public int compare(RegistroVarCalidadDTO o1, RegistroVarCalidadDTO o2) {
				return (int) (o1.getFecha().getTime() - o2.getFecha().getTime());
			}
		});

		return listaregistros;
	}

	public Map<Long, Double[]> hallarConsumoPuestoTrabajo(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable, Boolean tipoCombustible) throws LogicaException {

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
		Map<Long, Double[]> mapaConsumoPuestoTrabajo = new HashMap<Long, Double[]>();

		try {

			List<Object[]> consumos = ConsumoPuestoTrabajoQuerier.obtenerConsumoPuestoTrabajoMes(codigoProducto, codigoLinea,
					mesContable, anioContable, tipoCombustible);

			List<Object[]> calentProductoGenerado = ProductoGeneradoQuerier.obtenerCalententamiento(codigoProducto, codigoLinea,
					mesContable, anioContable, tipoCombustible);

			Object[] objetoConsumo;
			Double[] lista;

			for (int i = 0; i < consumos.size(); i++) {
				objetoConsumo = consumos.get(i);
				lista = new Double[2];
				Long codigoPuestoTrabajo = Long.valueOf(objetoConsumo[0].toString());
				Double consumoPuestoTrabajo = Double.valueOf(objetoConsumo[1].toString());
				Double calentamientoConsumo = Double.valueOf(objetoConsumo[2].toString());

				Double calentPg = buscarCalentProductoGenerado(codigoPuestoTrabajo, calentProductoGenerado);

				if (calentPg == null) {
					calentPg = 0d;
				}

				lista[0] = consumoPuestoTrabajo - (calentamientoConsumo + calentPg);
				lista[1] = calentamientoConsumo + calentPg;

				mapaConsumoPuestoTrabajo.put(codigoPuestoTrabajo, lista);

			}
		} catch (Exception e) {
			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return mapaConsumoPuestoTrabajo;
	}

	private Double buscarCalentProductoGenerado(Long codigoPuestoTrabajo, List<Object[]> calentProductoGenerado) {
		Double calentamientoProductoGenerado = 0d;

		for (int i = 0; i < calentProductoGenerado.size(); i++) {
			Object[] productoGenerado = calentProductoGenerado.get(i);
			if (codigoPuestoTrabajo.compareTo(Long.valueOf(productoGenerado[0].toString())) == 0) {
				calentamientoProductoGenerado = (Double) productoGenerado[1];
			}
		}
		return calentamientoProductoGenerado;
	}
}
