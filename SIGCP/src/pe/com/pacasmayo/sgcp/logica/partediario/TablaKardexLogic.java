package pe.com.pacasmayo.sgcp.logica.partediario;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;


import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;

import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.TablaKardexBeanImpl;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.GestionStockDetalleBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.GestionStockGraficoBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.ReporteGestionStockProduccionBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.SubReporteGestionStockGraficoBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.SubReporteGestionStockTablaBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.MedicionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaKardexLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MedicionLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorvariacionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Valorpromvariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EquivalenciasccvarcalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorVariacionPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionDiariaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoGeneradoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;

import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ConsumoCarbonesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ConsumoPorPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroDetalleCarbonesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroKardexMateriaPrimaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroKardexParteDiarioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroKardexProductoComponentesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroKardexProductoTerminadoDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

public class TablaKardexLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, ConstantesLogicaNegocio,
		TablaKardexLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());
	private MedicionLogicFacade medicionLogic = new MedicionLogic();
	private String mensajeError = "";

	private static BeanFactory beanFactory;

	private Long codProducto = Long.valueOf(0);

	private boolean validaDespacho = false;

	private enum TipoMaterial {
		MP, PP, PT;
	}

	public TablaKardexLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	public Map<String, List<RegistroKardexParteDiarioDTO>> obtenerRegistrosKardexParteDiario(Long codigoProceso,
			Long codigoProducto, Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException {

		//Cargo Mediciones
		List<MedicionDiariaDTO> mediciones = medicionLogic.obtenerDetalleRegistroMedicion(codigoProceso, codigoProducto,
				codigoLineaNegocio, anno, mes);
		
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			Producto producto = ProductoQuerier.getById(codigoProducto);

			Map<String, List<RegistroKardexParteDiarioDTO>> mapaRegistros = null;
			List<Tablakardex> listaTablaKardex = null;

			long tipoProd = producto.getTipoproducto().getPkCodigoTipoproducto().longValue();

			Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
			Long codigoMP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_MATERIA_PRIMA));

			TipoMaterial tipo = TipoMaterial.MP;

			boolean poseeCompEnHojaRuta = HojaRutaComponenteQuerier.poseeCompEnHojaRuta(producto.getPkCodigoProducto());
			boolean productoProcesoSinComp = (tipoProd == codigoPP && !poseeCompEnHojaRuta);

			List<ConsumoPorPuestoTrabajoDTO> listaConsumosPorPuestoTrab = new ArrayList<ConsumoPorPuestoTrabajoDTO>();

			if (tipoProd == codigoMP || productoProcesoSinComp) {
				listaTablaKardex = TablaKardexQuerier.obtenerTablaKardexMp(codigoProducto, codigoLineaNegocio, null, anno, mes);

				listaConsumosPorPuestoTrab = ConsumoPuestoTrabajoQuerier.obtenerConsumoComponentePorFechaEnPuestos(
						codigoProducto, codigoLineaNegocio, anno, mes);
			} else {
				if (tipoProd == codigoPP) {
					tipo = TipoMaterial.PP;
					listaConsumosPorPuestoTrab = ConsumoPuestoTrabajoQuerier.obtenerConsumoComponentePorFechaEnPuestos(
							codigoProducto, codigoLineaNegocio, anno, mes);
				} else {
					tipo = TipoMaterial.PT;
				}
				listaTablaKardex = TablaKardexQuerier.obtenerTablaKardexPpYPtDelMes(codigoProceso, codigoProducto,
						codigoLineaNegocio, null, anno, mes);
			}

			long codigoClkHH = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HH));

			List<Tablakardex> kardexsClkHV = null;
			if (codigoProceso.longValue() == codigoClkHH) {
				long codigoClkHV = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HV));
				kardexsClkHV = TablaKardexQuerier.obtenerTablaKardexPpYPtDelMes(codigoClkHV, codigoProducto, codigoLineaNegocio,
						null, anno, mes);
			}

			codProducto = codigoProducto;
			validaDespacho = false;
			ParametroSistema registroParametroSistema = new ParametroSistema();
			registroParametroSistema = ParametroSistemaQuerier.obtenerParametroSistema("PRODUCTOS_DESPACHO");

			String[] splitCodProductos = registroParametroSistema.getValorParametro().split(",");

			for (String codPro : splitCodProductos) {
				if (codPro.equals(codigoProducto.toString())) {
					validaDespacho = true;
				}
			}



			mapaRegistros = obtenerMapaRegistrosKardexParteDiarioDTO(listaTablaKardex, tipo, kardexsClkHV,
					listaConsumosPorPuestoTrab, mediciones);
			return mapaRegistros;
		} catch (SessionException e) {
			e.printStackTrace(); 
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (AplicacionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA) + Tablakardex.class.getName();
			throw new LogicaException(mensajeError, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LogicaException(e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Método para obtener el mapa de lista de RegistroKardexParteDiarioDTO
	 * 
	 * @param producto
	 * @param listaTablaKardex
	 * @param tipo
	 * @param kardexsClkHV
	 * @param listaConsumosPorPuestoTrab
	 * @return
	 * @throws AplicacionException
	 */

	private Map<String, List<RegistroKardexParteDiarioDTO>> obtenerMapaRegistrosKardexParteDiarioDTO(
			List<Tablakardex> listaTablaKardex, TipoMaterial tipo, List<Tablakardex> kardexsClkHV,
			List<ConsumoPorPuestoTrabajoDTO> listaConsumosPorPuestoTrab, List<MedicionDiariaDTO> mediciones) {

		Map<String, List<RegistroKardexParteDiarioDTO>> mapaRegistroKardexParteDiarioDTO = new HashMap<String, List<RegistroKardexParteDiarioDTO>>();
		List<RegistroKardexParteDiarioDTO> listaRegistroKardexParteDiarioDTO = new ArrayList<RegistroKardexParteDiarioDTO>();

		String claseRegistroKardexParteDiarioDTO = obtenerClaseRegistroKardexParteDiarioDTO(tipo);

		if (claseRegistroKardexParteDiarioDTO == null) {
			return mapaRegistroKardexParteDiarioDTO;
		}

		// int indexKardexHV = 0;
		for (Tablakardex tablakardex : listaTablaKardex) {

			Tablakardex tablakardexClkHV = null;

			tablakardexClkHV = obtenerKrdexHVSegunFecha(kardexsClkHV, tablakardex.getFechaTablakardex());

			RegistroKardexParteDiarioDTO registroKardexParteDiarioDTO = obtenerRegistroKardexParteDiarioDTO(
					claseRegistroKardexParteDiarioDTO, tablakardex, tablakardexClkHV);

			EqualPredicate nameEqlPredicate = new EqualPredicate(tablakardex.getFechaTablakardex());
			BeanPredicate beanPredicate = new BeanPredicate("fecha", nameEqlPredicate);

			if (validaDespacho) {
				try {
					Double totMovimientoIngreso = MovimientoQuerier.obtenerMovimientoPorProductoXFechaXClasificacion(
							Long.valueOf(1), codProducto, tablakardex.getFechaTablakardex());
					Double totMovimientoSalida = MovimientoQuerier.obtenerMovimientoPorProductoXFechaXClasificacion(
							Long.valueOf(2), codProducto, tablakardex.getFechaTablakardex());
					Double totMovimiento = totMovimientoSalida - totMovimientoIngreso;
					registroKardexParteDiarioDTO.setDespachoCAL(totMovimiento);
					registroKardexParteDiarioDTO.setDespachoCALbool(validaDespacho);
				} catch (AplicacionException e) {
					// TODO manejo de exception
					e.printStackTrace();
				}
			}

			Map<String, Double> consumosPorPuesto = registroKardexParteDiarioDTO.getConsumosPorPuesto();
			Map<String, Double> consumosPorPuestoHumedo = registroKardexParteDiarioDTO.getConsumosPorPuestoHumedo();

			Collection collection = CollectionUtils.select(listaConsumosPorPuestoTrab, beanPredicate);
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				ConsumoPorPuestoTrabajoDTO object = (ConsumoPorPuestoTrabajoDTO) iterator.next();
				Double consumo = consumosPorPuesto.get(object.getNombrePuesto());
				Double consumoHumedo = consumosPorPuestoHumedo.get(object.getNombrePuesto());
				if (consumo != null) {
					consumosPorPuesto.put(object.getNombrePuesto(), consumo + object.getConsumo());
				} else {
					consumosPorPuesto.put(object.getNombrePuesto(), object.getConsumo());
				}
				if (consumoHumedo != null) {
					consumosPorPuestoHumedo.put(object.getNombrePuesto() + "_humedo", consumoHumedo + object.getConsumoHumedo());
				} else {
					consumosPorPuestoHumedo.put(object.getNombrePuesto() + "_humedo", object.getConsumoHumedo());
				}

			}

			beanPredicate = new BeanPredicate("fechaRegistroMedicion", nameEqlPredicate);
			collection = CollectionUtils.select(mediciones, beanPredicate);
			Map<String, Double> medicionPorMedioAlmacenamiento = registroKardexParteDiarioDTO
					.getMedicionDiariaMedioAlmacenamiento();
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				MedicionDiariaDTO object = (MedicionDiariaDTO) iterator.next();
				medicionPorMedioAlmacenamiento.put(object.getNombreTipoMedioAlmacenamiento(),
						object.getCantidadMedioAlmacenamiento());

			}

			listaRegistroKardexParteDiarioDTO.add(registroKardexParteDiarioDTO);
			// indexKardexHV++;
		}

		mapaRegistroKardexParteDiarioDTO.put(claseRegistroKardexParteDiarioDTO, listaRegistroKardexParteDiarioDTO);

		return mapaRegistroKardexParteDiarioDTO;
	}

	public Tablakardex obtenerKrdexHVSegunFecha(List<Tablakardex> kardexsClkHV, Date fechaTablakardex) {

		try {
			for (Tablakardex tablakardex : kardexsClkHV) {
				if (tablakardex.getFechaTablakardex().equals(fechaTablakardex)) {
					return tablakardex;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * Método para obtener el nombre de la clase de RegistroKardexParteDiarioDTO
	 * segun el tipo de producto
	 * 
	 * @param producto
	 * @param tipo
	 * @return
	 */
	private String obtenerClaseRegistroKardexParteDiarioDTO(TipoMaterial tipo) {

		String claseRegistroKardexParteDiarioDTO = null;

		switch (tipo) {
		case MP:
			claseRegistroKardexParteDiarioDTO = RegistroKardexMateriaPrimaDTO.class.getName();
			break;
		case PP:
			claseRegistroKardexParteDiarioDTO = RegistroKardexProductoComponentesDTO.class.getName();
			break;
		case PT:
			claseRegistroKardexParteDiarioDTO = RegistroKardexProductoTerminadoDTO.class.getName();
			break;

		default:
			break;
		}

		return claseRegistroKardexParteDiarioDTO;
	}

	/**
	 * Método para obtener un RegistroKardexParteDiarioDTO a partir de su tipo
	 * 
	 * @param claseRegistroKardexParteDiarioDTO Tipo de
	 *            RegistroKardexParteDiarioDTO
	 * @param tablaKardex Registro tabla kardex
	 * @param tablakardexClkHV
	 * @return
	 * @throws AplicacionException
	 */
	private RegistroKardexParteDiarioDTO obtenerRegistroKardexParteDiarioDTO(String claseRegistroKardexParteDiarioDTO,
			Tablakardex tablaKardex, Tablakardex tablakardexClkHV) {

		if (RegistroKardexProductoTerminadoDTO.class.getName().equals(claseRegistroKardexParteDiarioDTO)) {
			return crearRegistroKardexProductoTerminado(tablaKardex);
		}

		if (RegistroKardexProductoComponentesDTO.class.getName().equals(claseRegistroKardexParteDiarioDTO)) {
			return crearRegistroProductoComponentes(tablaKardex, tablakardexClkHV);
		}

		if (RegistroKardexMateriaPrimaDTO.class.getName().equals(claseRegistroKardexParteDiarioDTO)) {
			return crearRegistroKardexMateriaPrima(tablaKardex);
		}

		return null;
	}

	/**
	 * * Método para obtener un RegistroKardexMateriaPrimaDTO a partir de un
	 * registro tablaKardex
	 * 
	 * @param tablaKardex
	 * @return
	 * @throws AplicacionException
	 */
	private RegistroKardexParteDiarioDTO crearRegistroKardexMateriaPrima(Tablakardex tablaKardex) {

		RegistroKardexMateriaPrimaDTO registroKardexMateriaPrimaDTO = new RegistroKardexMateriaPrimaDTO();

		asignarAtributosKardexParteDiario(tablaKardex, registroKardexMateriaPrimaDTO);

		registroKardexMateriaPrimaDTO.setTmHumedadIngreso(tablaKardex.getIngresoHumedadTablakardex());
		registroKardexMateriaPrimaDTO.setFactorHumedadIngreso(obtenerFactorHumedadIngreso(tablaKardex));
		registroKardexMateriaPrimaDTO.setTmSecaIngreso(tablaKardex.getIngresoTablakardex());

		Double consumoTMHumedas = NumberUtil.reducirAdosDecimales(tablaKardex.getConsumoHumedadTablakardex());
		registroKardexMateriaPrimaDTO.setTmHumedadConsumo(consumoTMHumedas);

		Double consumoTMSecas = NumberUtil.reducirAdosDecimales(tablaKardex.getConsumoTablakardex());
		registroKardexMateriaPrimaDTO.setTmSecaConsumo(consumoTMSecas);
		double factorHumconsumo = NumberUtil
				.reducirAdosDecimales(((consumoTMHumedas - consumoTMSecas) / (consumoTMHumedas)) * 100d);
		registroKardexMateriaPrimaDTO.setFactorHumedadConsumo(factorHumconsumo);

		return registroKardexMateriaPrimaDTO;
	}

	/**
	 * Método para obtener el Valor Promedio del Factor de Humedad de Ingreso
	 * para un registro tablaKardex
	 * 
	 * @param tablaKardex
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	private Double obtenerFactorHumedadIngreso(Tablakardex tablaKardex) {
		String variableCalHumedad = "humedad";

		Set<Valorpromvariablecalidad> valorpromvariablecalidads = tablaKardex.getValorpromvariablecalidads();
		for (Valorpromvariablecalidad item : valorpromvariablecalidads) {
			String nombreVariablecalidad = item.getProductovariablecalidad().getVariablecalidad().getNombreVariablecalidad();
			if (nombreVariablecalidad.toLowerCase().equals(variableCalHumedad)) {
				return item.getValorValorpromvariblecalidad();
			}
		}

		return 0d;
	}

	/**
	 * Método para obtener un RegistroKardexProductoComponentesDTO a partir de
	 * un registro tablaKardex
	 * 
	 * @param tablaKardex
	 * @param tablakardexClkHV
	 * @return
	 */
	private RegistroKardexParteDiarioDTO crearRegistroProductoComponentes(Tablakardex tablaKardex, Tablakardex tablakardexClkHV) {

		RegistroKardexProductoComponentesDTO registroKardexProductoComponentesDTO = new RegistroKardexProductoComponentesDTO();

		asignarAtributosKardexParteDiario(tablaKardex, registroKardexProductoComponentesDTO);

		if (tablakardexClkHV != null) {

			registroKardexProductoComponentesDTO.setIngresoHV(tablakardexClkHV.getIngresoTablakardex());
			tablaKardex.getConsumocomponentes().addAll(tablakardexClkHV.getConsumocomponentes());
		} else {
			registroKardexProductoComponentesDTO.setIngresoHV(0d);
		}

		String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);

		Producto producto = tablaKardex.getProducciondiaria().getOrdenproduccion().getProduccion().getProducto();
		final boolean clinker = codigosClinker.indexOf(producto.getPkCodigoProducto().toString()) >= 0;

		if (clinker) {
			registroKardexProductoComponentesDTO.setVariablesCalidad(obtenerVariablesCalidad(tablaKardex));
			registroKardexProductoComponentesDTO.setErroresBalanza(obtenerErroresBalanza(tablaKardex));
		}

		registroKardexProductoComponentesDTO.setFisico(tablaKardex.getStockFisicoTablakardex());
		registroKardexProductoComponentesDTO.setVariacion(tablaKardex.getVariacionTablakardex());
		registroKardexProductoComponentesDTO.setConsumoComponentes(obtenerConsumoComponentes(tablaKardex));
		registroKardexProductoComponentesDTO.setConsumoComponentesSemiSeco(obtenerConsumoComponentesSemiSeco(tablaKardex));
		registroKardexProductoComponentesDTO
				.setFactorConsumoComponentesSemiSeco(obtenerFactorConsumoComponentesSemiSeco(tablaKardex));
		registroKardexProductoComponentesDTO.setConsumoComponentesHumedo(obtenerConsumoComponentesHumedo(tablaKardex));
		registroKardexProductoComponentesDTO.setVariacion(tablaKardex.getVariacionTablakardex());

		return registroKardexProductoComponentesDTO;
	}

	private Map<String, Double> obtenerFactorConsumoComponentesSemiSeco(Tablakardex tablaKardex) {
		Map<String, Double> mapaConsumoComponentes = new LinkedHashMap<String, Double>();

		for (Consumocomponente consumoComponente : (Set<Consumocomponente>) tablaKardex.getConsumocomponentes()) {
			Double factorSaldoInicial = 0d;
			if (consumoComponente.getFkCodigoFactorconsumopuestotrabajoFactor2() != null
					&& consumoComponente.getFkCodigoFactorconsumopuestotrabajoFactor2().getValorFactorconsumopuestotrabajo() != null) {
				factorSaldoInicial = consumoComponente.getFkCodigoFactorconsumopuestotrabajoFactor2()
						.getValorFactorconsumopuestotrabajo();
			}
			String nombreComponente = consumoComponente.getComponente().getProductoByFkCodigoProductoComponente()
					.getNombreProducto();
			Double valor = consumoComponente.getConsumoConsumocomponenteDiferencia();
			if (valor == null) {
				valor = 0.00d;
			}
			if (!mapaConsumoComponentes.containsKey(nombreComponente)) {
				mapaConsumoComponentes.put(nombreComponente, valor);
				mapaConsumoComponentes.put(nombreComponente + "_FactorSaldoInicial", factorSaldoInicial);
			}
		}
		return mapaConsumoComponentes;
	}

	private Map<String, Double> obtenerConsumoComponentesSemiSeco(Tablakardex tablaKardex) {
		Map<String, Double> mapaConsumoComponentes = new LinkedHashMap<String, Double>();

		for (Consumocomponente consumoComponente : (Set<Consumocomponente>) tablaKardex.getConsumocomponentes()) {

			String nombreComponente = consumoComponente.getComponente().getProductoByFkCodigoProductoComponente()
					.getNombreProducto();
			Double valor = consumoComponente.getConsumoConsumocomponenteSemiseco();
			if (valor == null) {
				valor = 0.00d;
			}
			if (!mapaConsumoComponentes.containsKey(nombreComponente)) {
				mapaConsumoComponentes.put(nombreComponente, valor);
			}
		}
		return mapaConsumoComponentes;
	}

	private Map<String, Double> obtenerConsumoComponentesHumedo(Tablakardex tablaKardex) {
		Map<String, Double> mapaConsumoComponentes = new LinkedHashMap<String, Double>();

		for (Consumocomponente consumoComponente : (Set<Consumocomponente>) tablaKardex.getConsumocomponentes()) {

			String nombreComponente = consumoComponente.getComponente().getProductoByFkCodigoProductoComponente()
					.getNombreProducto();
			Double valor = consumoComponente.getConsumoConsumocomponenteHumedo();
			if (valor == null) {
				valor = 0.00d;
			}
			if (!mapaConsumoComponentes.containsKey(nombreComponente)) {
				mapaConsumoComponentes.put(nombreComponente, valor);
			}
		}
		return mapaConsumoComponentes;
	}

	/**
	 * Método para obtener el consumo de componentes de un registro tablaKardex
	 * 
	 * @param tablaKardex
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Double> obtenerConsumoComponentes(Tablakardex tablaKardex) {
		Map<String, Double> mapaConsumoComponentes = new LinkedHashMap<String, Double>();

		for (Consumocomponente consumoComponente : (Set<Consumocomponente>) tablaKardex.getConsumocomponentes()) {

			String nombreComponente = consumoComponente.getComponente().getProductoByFkCodigoProductoComponente()
					.getNombreProducto();

			if (!mapaConsumoComponentes.containsKey(nombreComponente)) {
				mapaConsumoComponentes.put(nombreComponente, consumoComponente.getConsumoConsumocomponente());
			}
		}
		return mapaConsumoComponentes;
	}

	/**
	 * Método para obtener un RegistroKardexProductoTerminadoDTO a partir de un
	 * registro tablaKardex
	 * 
	 * @param tablaKardex
	 * @return
	 */
	private RegistroKardexParteDiarioDTO crearRegistroKardexProductoTerminado(Tablakardex tablaKardex) {

		RegistroKardexProductoTerminadoDTO registroKardexProductoTerminadoDTO = new RegistroKardexProductoTerminadoDTO();

		asignarAtributosKardexParteDiario(tablaKardex, registroKardexProductoTerminadoDTO);

		registroKardexProductoTerminadoDTO.setFisico(tablaKardex.getStockFisicoTablakardex());
		registroKardexProductoTerminadoDTO.setVariacion(tablaKardex.getVariacionTablakardex());
		registroKardexProductoTerminadoDTO.setConsumoComponentes(obtenerConsumoComponentes(tablaKardex));

		return registroKardexProductoTerminadoDTO;
	}

	/**
	 * Metodo para asignar los atributos comunes a los registros
	 * KardexParteDiario
	 * 
	 * @param tablaKardex
	 * @param registroKardexParteDiarioDTO
	 */
	private void asignarAtributosKardexParteDiario(Tablakardex tablaKardex,
			RegistroKardexParteDiarioDTO registroKardexParteDiarioDTO) {

		try {
			mensajeError = "";
			mensajeError = NotificacionDiariaQuerier
					.obtenerNotificacion(
							tablaKardex.getProducciondiaria().getPartediario().getLineanegocio().getPkCodigoLineanegocio(),
							tablaKardex.getFechaTablakardex()).getEstadonotificacion().getNombreEstadonotificacion();
		} catch (ElementoNoEncontradoException e) {
			// TODO: Manejo de Log
		}
		registroKardexParteDiarioDTO.setEstado(mensajeError);
		registroKardexParteDiarioDTO.setFecha(tablaKardex.getFechaTablakardex());
		registroKardexParteDiarioDTO.setAlmacen(tablaKardex.getAlmacen().getCodigoSapAlmacen());
		registroKardexParteDiarioDTO.setUbicacionOrigen(tablaKardex.getUbicacionByFkCodigoUbicacionOrigen().getNombreUbicacion());
		registroKardexParteDiarioDTO.setSaldoInicial(tablaKardex.getSaldoInicialTablakardex());
		registroKardexParteDiarioDTO.setIngreso(tablaKardex.getIngresoTablakardex());
		registroKardexParteDiarioDTO.setConsumo(tablaKardex.getConsumoTablakardex());
	}

	/**
	 * Método para obtener las variables de variacion de un registro tablaKardex
	 * 
	 * @param tablaKardex
	 * @return
	 */

	private Map<String, Double> obtenerVariablesCalidad(Tablakardex tablaKardex) {

		List<String> variablesCalidad = EquivalenciasccvarcalidadQuerier.obtenerNombresSgcpVarCalidad();

		Map<String, Double> mapaVariablesCalidad = new LinkedHashMap<String, Double>();
		for (String variable : variablesCalidad) {
			double factor = ConsumoPuestoTrabajoQuerier.obtenerPromedioFactorConsumo(tablaKardex, variable);
			if (!(mapaVariablesCalidad.containsKey(variable))) {
				mapaVariablesCalidad.put(variable, factor);
			}
		}

		return mapaVariablesCalidad;
	}

	private Map<String, Double> obtenerErroresBalanza(Tablakardex tablaKardex) {

		List<Factorvariacionpuestotrabajo> factoresVariableVariacion = FactorVariacionPuestoTrabajoQuerier
				.obtenerFactoresVariableVariacion(tablaKardex);
		Map<String, Double> mapaVariablesVariacion = new LinkedHashMap<String, Double>();

		for (Factorvariacionpuestotrabajo factorvariacion : factoresVariableVariacion) {
			String nombreVariablevariacion = factorvariacion.getProductovariablevariacion().getVariablevariacion()
					.getNombreVariablevariacion();
			Double valorVariablevariacion = factorvariacion.getValorVariablevariacionFactorv();
			mapaVariablesVariacion.put(nombreVariablevariacion, valorVariablevariacion == null ? 0d : valorVariablevariacion);
		}

		return mapaVariablesVariacion;
	}

	public TablaKardexBean[] obtenerKardexMPporPeriodo(Date fechaInicioDate, Date fechaFinDate, Long codigoProducto,
			Long codigoProceso, Long codigoLineaNeg, Long codigoMedioAlmacen, Long codigoAlmacen) throws LogicaException {
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

			TablaKardexBean[] periodoKardex = null;

			long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
			long codigoMP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_MATERIA_PRIMA));

			Producto producto = ProductoQuerier.getById(codigoProducto);
			long tipoProducto = producto.getTipoproducto().getPkCodigoTipoproducto().longValue();

			boolean esProductoProceso = tipoProducto == codigoPP;
			boolean poseeCompEnHojaRuta = HojaRutaComponenteQuerier.poseeCompEnHojaRuta(producto.getPkCodigoProducto());

			List<Tablakardex> tablaKardexs;

			if (tipoProducto == codigoMP || (esProductoProceso && !poseeCompEnHojaRuta)) {

				tablaKardexs = TablaKardexQuerier.obtenerPorProductoFechaLineaMP(fechaInicioDate, fechaFinDate, codigoProducto,
						codigoLineaNeg, codigoMedioAlmacen, codigoAlmacen);

			} else {
				tablaKardexs = TablaKardexQuerier.obtenerPorProductoFechaLineaPpYPt(fechaInicioDate, fechaFinDate,
						codigoProducto, codigoProceso, codigoLineaNeg, codigoMedioAlmacen, codigoAlmacen);
			}
			if (tablaKardexs != null) {
				periodoKardex = new TablaKardexBeanImpl[tablaKardexs.size()];
				for (int i = 0; i < tablaKardexs.size(); i++) {

					periodoKardex[i] = beanFactory.transformarTablaKardex(tablaKardexs.get(i));

				}

			}

			return periodoKardex;
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.TablaKardexLogicFacade#
	 * obtenerDetalleConsumoCarbonEnClinker(java.util.List, java.lang.Long,
	 * java.lang.Integer, java.lang.Short)
	 */
	public List<RegistroDetalleCarbonesDTO> obtenerDetalleConsumoCarbonEnClinker(List<ConsumoCarbonesDTO> consumoCarbonesDtos,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException {

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
		List<RegistroDetalleCarbonesDTO> lista = new ArrayList<RegistroDetalleCarbonesDTO>();
		try {

			long procesoMolCarbon = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CARBON));

			long codigoMix1 = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_CARBON_MIX_S1));
			long codigoMix2 = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_CARBON_MIX_S2));

			List<Tablakardex> consumoComponentesMix1 = TablaKardexQuerier.obtenerTablaKardexPpYPtDelMes(procesoMolCarbon,
					codigoMix1, codigoLineaNegocio, null, anno, mes);

			List<Tablakardex> consumoComponentesMix2 = TablaKardexQuerier.obtenerTablaKardexPpYPtDelMes(procesoMolCarbon,
					codigoMix2, codigoLineaNegocio, null, anno, mes);

			// calculo de consumo por puesto de trabajo
			Long codigoProducto = 0L;
			Long codigoProceso = 0L;
			if (consumoCarbonesDtos != null && consumoCarbonesDtos.size() > 0) {
				codigoProducto = consumoCarbonesDtos.get(0).getCodigoProducto();
				codigoProceso = consumoCarbonesDtos.get(0).getCodigoProceso();
			}

			List<ConsumoPorPuestoTrabajoDTO> consumoComponenteCarbon1PorPuestoTrabajo = ConsumoPuestoTrabajoQuerier
					.obtenerDatosPorPuestoTrabajo(anno, mes, codigoLineaNegocio, codigoProducto, codigoProceso, codigoMix1);
			List<ConsumoPorPuestoTrabajoDTO> consumoComponenteCarbon2PorPuestoTrabajo = ConsumoPuestoTrabajoQuerier
					.obtenerDatosPorPuestoTrabajo(anno, mes, codigoLineaNegocio, codigoProducto, codigoProceso, codigoMix2);

			Map<Long, Double[]> mapaProductoGenerado = ProductoGeneradoQuerier.obtenerCalentamientoConsumoComponentes(anno, mes,
					codigoLineaNegocio, codigoProducto, codigoProceso, codigoMix1, codigoMix2);

			int index = 0;
			BeanPredicate beanPredicate;
			EqualPredicate nameEqlPredicate;
			Collection collection;
			Collection collection2;
			for (Tablakardex kardexMix1 : consumoComponentesMix1) {
				RegistroDetalleCarbonesDTO dto = new RegistroDetalleCarbonesDTO();
				dto.setFecha(kardexMix1.getFechaTablakardex());

				nameEqlPredicate = new EqualPredicate(kardexMix1.getFechaTablakardex());

				beanPredicate = new BeanPredicate("fecha", nameEqlPredicate);
				collection = CollectionUtils.select(consumoComponenteCarbon1PorPuestoTrabajo, beanPredicate);

				ConsumoCarbonesDTO consumoCarbonesDTO = consumoCarbonesDtos.get(index);

				boolean esMix1 = true;
				crearConsumoComponentes(kardexMix1, dto, consumoCarbonesDTO, esMix1, collection, mapaProductoGenerado);

				Tablakardex kardexMix2 = consumoComponentesMix2.get(index);

				nameEqlPredicate = new EqualPredicate(kardexMix1.getFechaTablakardex());

				// beanPredicate = new BeanPredicate("fecha", nameEqlPredicate);
				collection2 = CollectionUtils.select(consumoComponenteCarbon2PorPuestoTrabajo, beanPredicate);

				esMix1 = false;
				crearConsumoComponentes(kardexMix2, dto, consumoCarbonesDTO, esMix1, collection2, mapaProductoGenerado);

				index++;
				lista.add(dto);
			}

			return lista;

		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	@SuppressWarnings("unchecked")
	private void crearConsumoComponentes(Tablakardex kardex, RegistroDetalleCarbonesDTO dto,
			ConsumoCarbonesDTO consumoCarbonesDTO, boolean esMix1, Collection collection, Map<Long, Double[]> mapaProductoGenerado) {
		Map<String, String> consumos = new HashMap<String, String>();
		Set<Consumocomponente> consumocomponentes = kardex.getConsumocomponentes();

		Double ingresoTablakardex = kardex.getIngresoTablakardex();

		for (Consumocomponente consumocomponente : consumocomponentes) {
			String nombreProducto = consumocomponente.getComponente().getProductoByFkCodigoProductoComponente()
					.getNombreProducto();
			Double consumo = consumocomponente.getConsumoConsumocomponente();
			Double factor = consumo / ingresoTablakardex;
			double comsumoMix = 0;
			if (esMix1) {
				comsumoMix = consumoCarbonesDTO.getConsumoMix1();
			} else {
				comsumoMix = consumoCarbonesDTO.getConsumoMix2();
			}

			Double valor = comsumoMix * factor;

			consumos.put(nombreProducto, valor.toString() + "-" + factor.toString());
		}
		Map<String, Double[]> consumoMix;
		Double produccion = 0D;
		Double calentamiento = 0D;
		Double consumoSolido = 0d;
		Double consumoTotal = 0d;
		if (esMix1) {
			dto.setComponentesMix1(consumos);
			consumoMix = dto.getPuestoTrabajoMix1();

			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				ConsumoPorPuestoTrabajoDTO object = (ConsumoPorPuestoTrabajoDTO) iterator.next();
				produccion = object.getConsumoHumedo() - object.getConsumo();
				consumoSolido = mapaProductoGenerado.get(object.getProductoGenerado())[0];
				consumoTotal = mapaProductoGenerado.get(object.getProductoGenerado())[1];
				calentamiento = object.getConsumo()
						+ NumberUtil.formulaProporcion(consumoSolido, object.getConsumoHumedo(), consumoTotal);
				consumoMix
						.put(object.getNombrePuesto(),
								new Double[] { NumberUtil.Redondear2Decimales(produccion),
										NumberUtil.Redondear2Decimales(calentamiento) });

			}
			dto.setPuestoTrabajoMix1(consumoMix);
		} else {
			dto.setComponentesMix2(consumos);
			consumoMix = dto.getPuestoTrabajoMix2();

			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				ConsumoPorPuestoTrabajoDTO object = (ConsumoPorPuestoTrabajoDTO) iterator.next();
				produccion = object.getConsumoHumedo() - object.getConsumo();
				consumoSolido = mapaProductoGenerado.get(object.getProductoGenerado())[0];
				consumoTotal = mapaProductoGenerado.get(object.getProductoGenerado())[1];
				calentamiento = object.getConsumo()
						+ NumberUtil.formulaProporcion(consumoSolido, object.getConsumoHumedo(), consumoTotal);
				consumoMix
						.put(object.getNombrePuesto(),
								new Double[] { NumberUtil.Redondear2Decimales(produccion),
										NumberUtil.Redondear2Decimales(calentamiento) });

			}
			dto.setPuestoTrabajoMix2(consumoMix);
		}
	}

	public ReporteGestionStockProduccionBean obtenerStockReporte(Short mes, Integer anio, Long unidad) throws LogicaException {

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

		ParametroSistemaLogicFacade parametroSistema = new ParametroSistemaLogic();
		int mesActual = mes - 1;

		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.set(anio, Short.valueOf(mesActual + ""), 1);
		Calendar fechaFin = FechaUtil.getUltimoDiaMes(Short.valueOf(mesActual + ""), anio);

		ReporteGestionStockProduccionBean reportePrincipal = new ReporteGestionStockProduccionBean();

		try {
			Long[] proceso;
			String tituloReporteGrafico;
			String ejeY;
			Boolean isReporteGraficoLineal;
			String ejeY2;
			List<GestionStockGraficoBean> reporteTotlaSF;
			List<GestionStockGraficoBean> reporteStock = null;
			SubReporteGestionStockTablaBean subReporteDetalle;
			SubReporteGestionStockGraficoBean subReporteGraficos;
			List<ParametroSistemaBean> parametros = parametroSistema
					.obtenerParametrosSistema(ConstantesParametro.REPORTE_PARTEDIARIO_TABLA_DETALLE);
			Boolean muestraSuma = Boolean.FALSE;
			Boolean mostrarSumaProducccion = Boolean.FALSE;
			Boolean mostrarSumaConsumo = Boolean.FALSE;

			for (ParametroSistemaBean param : parametros) {
				List<Long> listProducto = null;
				String[] paramSistema = param.getValor().split(",");
				String[] listprocesos = paramSistema[0].split(";");
				String[] paramProductos = null;

				Boolean muestraGrafico = Boolean.valueOf(paramSistema[7].toString().trim());
				proceso = new Long[listprocesos.length];
				for (int i = 0; i < listprocesos.length; i++) {
					proceso[i] = Long.valueOf(listprocesos[i].trim());
				}

				// si es mayor que 5 tiene productos para Clinker Importado
				if (paramSistema[6] != null) {
					if (paramSistema[6].toString().trim().length() > 0) {
						paramProductos = paramSistema[6].split("/");
						if (paramProductos != null && paramSistema[6].toString().trim() != "" && paramProductos.length > 0) {
							listProducto = new ArrayList<Long>();
							for (String producto : paramProductos) {
								listProducto.add(Long.valueOf(producto));
							}
						}
					}
				}

				tituloReporteGrafico = paramSistema[1];
				ejeY = paramSistema[2];
				isReporteGraficoLineal = Boolean.valueOf(paramSistema[3]);
				ejeY2 = paramSistema[4];
				muestraSuma = Boolean.valueOf(paramSistema[5]);
				mostrarSumaProducccion = Boolean.valueOf(paramSistema[8]);
				mostrarSumaConsumo = Boolean.valueOf(paramSistema[9]);

				reporteStock = new ArrayList<GestionStockGraficoBean>();
				for (Long lngproceso : proceso) {
					reporteStock = agruparDatosPorProceso(reporteStock, TablaKardexQuerier.generarReporteStock(
							fechaInicio.getTime(), fechaFin.getTime(), lngproceso, tituloReporteGrafico, ejeY, listProducto));
				}
				calcularSaldoInicialYSaldoFinal(reporteStock);
				subReporteDetalle = new SubReporteGestionStockTablaBean();
				subReporteDetalle.setSubReporteDetalle(obtenerReporteDetalle(reporteStock, muestraSuma, mostrarSumaProducccion,
						mostrarSumaConsumo));
				if (subReporteDetalle.getSubReporteDetalle().size() > 0) {
					subReporteDetalle.setNombreDetalle(paramSistema[1]);
				} else {
					subReporteDetalle.setNombreDetalle("");
				}
				subReporteGraficos = new SubReporteGestionStockGraficoBean();
				if (muestraGrafico) {
					subReporteGraficos.setSubReporte(reporteStock);
					if (isReporteGraficoLineal) {
						reporteTotlaSF = obtenerReporteLineal(tituloReporteGrafico, ejeY, nombreEjeY2(ejeY2), reporteStock);

						subReporteGraficos.getSubReporte().addAll(reporteTotlaSF);
					}
					if (subReporteGraficos.getSubReporte() != null) {
						if (subReporteGraficos.getSubReporte().size() > 0 && paramSistema[1] != null) {
							subReporteGraficos.setNombre(paramSistema[1]);
							reportePrincipal.getReporteGraficos().add(subReporteGraficos);
						} else {
							subReporteGraficos.setNombre("");
						}
					}
				}

				Unidad unidadQ = UnidadQuerier.getById(unidad);
				if (subReporteDetalle.getSubReporteDetalle().size() > 0) {
					reportePrincipal.getReporteDetalle().add(subReporteDetalle);
				}
				reportePrincipal.setMes(FechaUtil.numeroMesANombreMes(mes));
				reportePrincipal.setUnidad(unidadQ.getNombreUnidad());
				reportePrincipal.setAnio(anio.toString());
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return reportePrincipal;
	}

	private List<GestionStockGraficoBean> obtenerReporteLineal(String tituloReporteGrafico, String ejeY, String nombreEjeY2,
			List<GestionStockGraficoBean> reporteStock) {

		Map<Integer, GestionStockGraficoBean> dias = new HashMap<Integer, GestionStockGraficoBean>();
		for (GestionStockGraficoBean gestionStockGraficoBean : reporteStock) {
			GestionStockGraficoBean grafico = dias.get(gestionStockGraficoBean.getDia());
			if (grafico == null) {
				grafico = new GestionStockGraficoBean(gestionStockGraficoBean.getDia(), gestionStockGraficoBean.getToneladas(),
						tituloReporteGrafico, nombreEjeY2, nombreEjeY2, ejeY);
				dias.put(gestionStockGraficoBean.getDia(), grafico);
			} else {
				grafico.setToneladas(grafico.getToneladas() + gestionStockGraficoBean.getToneladas());
			}
		}

		List<GestionStockGraficoBean> graficoLineal = new ArrayList<GestionStockGraficoBean>(dias.values());
		ordenarPorDiaReporteResumenStock(graficoLineal);

		return graficoLineal;
	}

	private void ordenarPorDiaReporteResumenStock(List<GestionStockGraficoBean> reporte) {
		Collections.sort(reporte, new Comparator<GestionStockGraficoBean>() {
			public int compare(GestionStockGraficoBean o1, GestionStockGraficoBean o2) {
				return o1.getDia().compareTo(o2.getDia());
			}

		});
	}

	private void calcularSaldoInicialYSaldoFinal(List<GestionStockGraficoBean> reporteStock) {
		Map<Integer, Integer> dias = new HashMap<Integer, Integer>();
		Map<String, String> productos = new HashMap<String, String>();
		for (GestionStockGraficoBean gestionStockGraficoBean : reporteStock) {
			dias.put(gestionStockGraficoBean.getDia(), gestionStockGraficoBean.getDia());
			productos.put(gestionStockGraficoBean.getDescripcion(), gestionStockGraficoBean.getDescripcion());
		}
		List<Integer> listadias = new ArrayList<Integer>(dias.keySet());
		List<String> listaproductos = new ArrayList<String>(productos.keySet());
		Collections.sort(listadias, new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				int compareFechas = o1.compareTo(o2);
				return compareFechas;
			}

		});

		Collections.sort(listaproductos, new Comparator<String>() {
			public int compare(String o1, String o2) {
				int compare = o1.compareTo(o2);
				return compare;
			}
		});
		Integer diaprimero = 1;
		Double saldoInicial = 0d;
		for (String producto : listaproductos) {
			for (Integer dia : listadias) {
				for (GestionStockGraficoBean gestionStockGraficoBean : reporteStock) {
					if (gestionStockGraficoBean.getDia().compareTo(dia) == 0
							&& gestionStockGraficoBean.getDescripcion().equals(producto)) {
						if (gestionStockGraficoBean.getDia().compareTo(diaprimero) == 0) {
							saldoInicial = gestionStockGraficoBean.getSaldoInicial();
						}
						gestionStockGraficoBean.setSaldoInicial(saldoInicial);
						gestionStockGraficoBean.setToneladas((gestionStockGraficoBean.getSaldoInicial() + gestionStockGraficoBean
								.getProduccion()) - gestionStockGraficoBean.getConsumo());
						saldoInicial = gestionStockGraficoBean.getToneladas();
					}
				}
			}
		}

	}

	private List<GestionStockGraficoBean> agruparDatosPorProceso(List<GestionStockGraficoBean> reporteStock,
			List<GestionStockGraficoBean> generarReporteStock) {
		if (reporteStock != null && reporteStock.size() > 0) {
			for (GestionStockGraficoBean gestionb : reporteStock) {
				for (GestionStockGraficoBean gestiona : generarReporteStock) {
					if (gestionb.getDia().equals(gestiona.getDia())
							&& gestionb.getDescripcion().equals(gestiona.getDescripcion())) {
						gestionb.setProduccion(gestionb.getProduccion() + gestiona.getProduccion());
					}
				}
			}

		} else {
			reporteStock = generarReporteStock;
		}
		return reporteStock;
	}

	private String nombreEjeY2(String nombre) {
		String tituloY2 = ManejadorPropiedades
				.obtenerPropiedadPorClave(ConstantesLogicaNegocio.NOMBRE_EJEY2_REPORTE_PARTEDIARIO_GESTIONSTOCK);
		return MessageFormat.format(tituloY2, new Object[] { nombre });
	}

	private List<GestionStockDetalleBean> obtenerReporteDetalle(List<GestionStockGraficoBean> generarReporteStock,
			Boolean muestraSuma, Boolean mostrarSumaProducccion, Boolean mostrarSumaConsumo) {
		List<GestionStockDetalleBean> listaDetalle = new ArrayList<GestionStockDetalleBean>();
		GestionStockDetalleBean reporteDetalle;
		Map<Integer, List<GestionStockGraficoBean>> mapaDias = new HashMap<Integer, List<GestionStockGraficoBean>>();
		for (GestionStockGraficoBean r : generarReporteStock) {
			List<GestionStockGraficoBean> lista = mapaDias.get(r.getDia());
			if (lista != null) {
				lista.add(r);
				mapaDias.put(r.getDia(), lista);
			} else {
				lista = new ArrayList<GestionStockGraficoBean>();
				lista.add(r);
				mapaDias.put(r.getDia(), lista);
			}

		}

		List<Integer> dias = new ArrayList<Integer>(mapaDias.keySet());
		Collections.sort(dias, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int compareFechas = o1.compareTo(o2);
				return compareFechas;
			}
		});
		List<GestionStockGraficoBean> lista = null;
		for (int i = 0; i < dias.size(); i++) {
			reporteDetalle = new GestionStockDetalleBean();
			lista = mapaDias.get(dias.get(i));
			Collections.sort(lista, new Comparator<GestionStockGraficoBean>() {
				public int compare(GestionStockGraficoBean o1, GestionStockGraficoBean o2) {
					int compare = o1.getDescripcion().compareTo(o2.getDescripcion());
					return compare;
				}
			});
			int orden = 1;
			Double sumaSaldoFinal = 0d;
			Double sumaTotalProduccion = 0d;
			Double sumaTotalConsumo = 0d;
			for (GestionStockGraficoBean reporte : lista) {
				sumaSaldoFinal += reporte.getToneladas();
				sumaTotalProduccion += reporte.getProduccion();
				sumaTotalConsumo += reporte.getConsumo();
				asignarAtributo(reporteDetalle, reporte, orden);
				orden++;
			}
			if (muestraSuma) {
				GestionStockGraficoBean reporte = new GestionStockGraficoBean();
				reporte.setSaldoInicial(sumaSaldoFinal);
				asignarAtributoSuma(reporteDetalle, reporte, orden);
			}

			if (mostrarSumaProducccion) {
				GestionStockGraficoBean reporte = new GestionStockGraficoBean();
				reporte.setProduccion(sumaTotalProduccion);
				asignarAtributoSumaProduccion(reporteDetalle, reporte, orden);
			}
			if (mostrarSumaConsumo) {
				GestionStockGraficoBean reporte = new GestionStockGraficoBean();
				reporte.setConsumo(sumaTotalConsumo);
				asignarAtributoSumaConsumo(reporteDetalle, reporte, orden);
			}

			listaDetalle.add(reporteDetalle);

		}
		return listaDetalle;
	}

	private void asignarAtributoSuma(GestionStockDetalleBean reporteDetalle, GestionStockGraficoBean reporte, int orden) {

		try {

			BeanUtils.setProperty(reporteDetalle, "saldoInicial_" + orden, reporte.getSaldoInicial());
			BeanUtils.setProperty(reporteDetalle, "saldoInicial_" + orden + "_1",
					ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.NOMBRE_SALDO_TOTAL));

		} catch (IllegalAccessException e) {
			// TODO Manejo Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Manejo Log
			e.printStackTrace();
		}

	}

	private void asignarAtributoSumaProduccion(GestionStockDetalleBean reporteDetalle, GestionStockGraficoBean reporte, int orden) {

		try {

			BeanUtils.setProperty(reporteDetalle, "produccion_" + orden, reporte.getProduccion());
			BeanUtils.setProperty(reporteDetalle, "produccion_" + orden + "_1",
					ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.NOMBRE_TOTAL_PRODUCCION));

		} catch (IllegalAccessException e) {
			// TODO Manejo Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Manejo Log
			e.printStackTrace();
		}

	}

	private void asignarAtributoSumaConsumo(GestionStockDetalleBean reporteDetalle, GestionStockGraficoBean reporte, int orden) {

		try {

			BeanUtils.setProperty(reporteDetalle, "consumo_" + orden, reporte.getConsumo());
			BeanUtils.setProperty(reporteDetalle, "consumo_" + orden + "_1",
					ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.NOMBRE_TOTAL_CONSUMO));

		} catch (IllegalAccessException e) {
			// TODO Manejo Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Manejo Log
			e.printStackTrace();
		}

	}

	private void asignarAtributo(GestionStockDetalleBean reporteDetalle, GestionStockGraficoBean reporte, int orden) {

		try {
			BeanUtils.setProperty(reporteDetalle, "saldoInicial_" + orden, reporte.getSaldoInicial());
			BeanUtils.setProperty(reporteDetalle, "produccion_" + orden, reporte.getProduccion());
			BeanUtils.setProperty(reporteDetalle, "consumo_" + orden, reporte.getConsumo());
			BeanUtils.setProperty(reporteDetalle, "toneladas_" + orden, reporte.getToneladas());
			BeanUtils.setProperty(reporteDetalle, "siglas_" + orden, reporte.getSiglas());
			BeanUtils.setProperty(reporteDetalle, "descripcion_" + orden, reporte.getDescripcion());
			BeanUtils.setProperty(reporteDetalle, "dia_" + orden, reporte.getDia());
			// Descripcion Cabecera
			BeanUtils.setProperty(reporteDetalle, "saldoInicial_" + orden + "_1",
					ConstantesAplicacionAction.CABECERA_SALDO_INICIAL);
			BeanUtils.setProperty(reporteDetalle, "produccion_" + orden + "_2", ConstantesAplicacionAction.CABECERA_PRODUCCION);
			BeanUtils.setProperty(reporteDetalle, "consumo_" + orden + "_3", ConstantesAplicacionAction.CABECERA_CONSUMO);
			BeanUtils.setProperty(reporteDetalle, "saldoFinal_" + orden + "_4", ConstantesAplicacionAction.CABECERA_SALDO_FINAL);

		} catch (IllegalAccessException e) {
			// TODO Manejo Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Manejo Log
			e.printStackTrace();
		}

	}

	public void generarKardexDiarioSI(Long valorLineaNegocio, Integer valorAno, Short valorMes, Integer valorDia)
			throws LogicaException {
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			Integer cantidadRegistros = 0;
			logger.info("se Genero " + cantidadRegistros + " registros");
			tx.commit();
		} catch (Exception e) {
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	

	public void eliminarReportesECSDuplicados() throws LogicaException {
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			
			logger.info("se elimino ECS repetidos ");
			tx.commit();
		} catch (Exception e) {
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

}
