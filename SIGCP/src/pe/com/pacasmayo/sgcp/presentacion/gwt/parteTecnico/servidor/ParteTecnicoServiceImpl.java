package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.servidor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ConsumoPuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoAjusteProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.EstadoAjusteProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.ConsumoPuestoTrabajoLogic;
import pe.com.pacasmayo.sgcp.logica.stock.PeriodoContableLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacionregistromensu;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillagrupoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.RendimientoTermico;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProduccionMesQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.CubicacionProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorDosificacionRegistroMensualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.RendimientoTermicoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoAjusteProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.GrupoAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.AjusteProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.ConsumosFactoresPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroPuestoTrabajoProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumoComponentesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumosPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.servidor.ServicioImpl;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ParteTecnicoServiceImpl.javaf
 * Modificado: Sep 20, 2010 2:28:22 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

@SuppressWarnings("serial")
public class ParteTecnicoServiceImpl extends RemoteServiceServlet implements ParteTecnicoService, ConstantesAplicacionAction,
		ConstantesLogicaNegocio {

	private Log logger = LogFactory.getLog(this.getClass());
	private EstadoAjusteProduccionLogicFacade estadoAjusteProduccionFacade = new EstadoAjusteProduccionLogic();
	private PeriodoContableLogicFacade periodoContableFacade = new PeriodoContableLogic();
	private AjusteProduccionMesLogicFacade ajusteProduccionFacade = new AjusteProduccionMesLogic();
	private ProductoLogicFacade productoFacade = new ProductoLogic();
	private static AjusteProduccionMesLogicFacade ajusteProduccionMesLogicFacade = new AjusteProduccionMesLogic();

	public static Long codigoTipoMedioSilo = Long
			.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_TIPO_MEDIO_SILO));

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#cargarEstadosAjusteProduccion()
	 */
	public List<EstadoAjusteProduccionDTO> cargarEstadosAjusteProduccion() throws ServicioGWTGlobalException {
		List<EstadoAjusteProduccionBean> estados = null;
		List<EstadoAjusteProduccionDTO> estadosDTO = new ArrayList<EstadoAjusteProduccionDTO>();

		try {
			estados = estadoAjusteProduccionFacade.obtenerEstadosAjusteProduccion();
			for (EstadoAjusteProduccionBean estadoBean : estados) {
				EstadoAjusteProduccionDTO estadoDTO = new EstadoAjusteProduccionDTO();
				estadoDTO.setPkCodigoEstadoajusteproduccio(estadoBean.getCodigo());
				estadoDTO.setNombreEstadoajusteproduccion(estadoBean.getNombre());
				estadosDTO.add(estadoDTO);
			}

			return estadosDTO;
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#cargarMeses()
	 */
	public List<String> cargarMeses() throws ServicioGWTGlobalException {
		List<String> meses = new ArrayList<String>();

		try {
			meses = periodoContableFacade.obtenerMesesPorPeriodoAbierto();
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}

		return meses;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#cargarAnios()
	 */
	public List<Integer> cargarAnios() throws ServicioGWTGlobalException {
		List<Integer> anios = new ArrayList<Integer>();

		try {
			anios = periodoContableFacade.obtenerAnosPorPeriodoAbierto();
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}

		return anios;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#cargarGrupoAjuste(java.lang.Long)
	 */
	public List<GrupoAjusteDTO> cargarGrupoAjuste(Long codigoLineaNegocio) {

		List<Plantillagrupoajuste> gruposAjuste = new ArrayList<Plantillagrupoajuste>();
		List<GrupoAjusteDTO> gruposAjusteDTO = new ArrayList<GrupoAjusteDTO>();

		if (codigoLineaNegocio != null) {
			try {
				gruposAjuste = ajusteProduccionFacade.obtenerGruposProductoDataObjects(codigoLineaNegocio);
			} catch (LogicaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (Plantillagrupoajuste grupoAjuste : gruposAjuste) {
				GrupoAjusteDTO grupoAjusteDTO = new GrupoAjusteDTO();
				grupoAjusteDTO.setCodigo(grupoAjuste.getPkCodigoPlantillagrupoajuste());
				grupoAjusteDTO.setNombre(grupoAjuste.getNombrePlantillagrupoajuste());
				grupoAjusteDTO.setOrdenPlantilla(grupoAjuste.getOrdenPlantillagrupoajuste());
				gruposAjusteDTO.add(grupoAjusteDTO);
			}
		}
		return gruposAjusteDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#cargarProductosporCodigoGrupoAjuste(java.lang.Long)
	 */
	public List<ProductoDTO> cargarProductosporCodigoGrupoAjuste(Long codigoGrupoAjuste) {

		List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
		List<ProductoBean> productos;
		try {
			productos = ajusteProduccionFacade.obtenerProductoGrupos(codigoGrupoAjuste);
			for (Iterator<ProductoBean> iterator = productos.iterator(); iterator.hasNext();) {
				ProductoBean producto = iterator.next();
				ProductoDTO productoDTO = new ProductoDTO();
				productoDTO.setPkCodigoProducto(producto.getCodigo());
				productoDTO.setNombreProducto(producto.getNombre());
				productoDTO.setGrupoProducto(producto.getGrupoProducto());
				productosDTO.add(productoDTO);
			}
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productosDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerAjuste(java.lang.Long, java.lang.Long,
	 * java.lang.String, java.lang.Integer)
	 */
	public Double obtenerAjuste(Long codigoLinea, Long codigoProducto, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException {
		try {
			Double ajuste = null;
			// 297_47637
			Boolean estadoCerrado = this.validarParteDiarioCerrado(codigoLinea, mesContable, anioContable);
			String estadoCubicacionProducto = null;
			if (estadoCerrado) {
				estadoCubicacionProducto = ManejadorPropiedades
						.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);
			}

			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes(numeroMes, anioContable);
			Date fechaMesFinal = fechaMesCalendar.getTime();
			numeroMes++;
			ajuste = ajusteProduccionFacade.obtenerAjuste(codigoLinea, codigoProducto, numeroMes, anioContable,
					estadoCubicacionProducto, fechaMesFinal);

			double ajustePorConsumo = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteExceptuando(codigoProducto,
					codigoLinea, numeroMes, anioContable, codigoProducto);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProducto(codigoProducto,
					codigoLinea, numeroMes, anioContable);

			ajuste += ajustePorConsumo + consumoPorAjusteProducto;
			return ajuste;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerConceptosLibro(java.lang.Long, java.lang.Long,
	 * java.lang.String, java.lang.Integer)
	 */
	public RegistroTablaBalanceDTO obtenerConceptosLibro(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) throws ServicioGWTGlobalException {
		try {
			RegistroTablaBalanceDTO registroTablaBalanceDTO = new RegistroTablaBalanceDTO();
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes(numeroMes, anioContable);

			Date fechaMesFinal = fechaMesCalendar.getTime();
			fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
			Date fechaMesInicial = fechaMesCalendar.getTime();

			numeroMes++;
			double[] valores = ajusteProduccionFacade.obtenerConceptosLibro(codigoProducto, codigoLinea, numeroMes, anioContable,
					fechaMesInicial, fechaMesFinal, codigoTipoMedioSilo);

			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponente(codigoProducto, codigoLinea,
					numeroMes, anioContable);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProducto(codigoProducto,
					codigoLinea, numeroMes, anioContable);
			registroTablaBalanceDTO.setConsumoPorAjuste(consumoPorAjuste + consumoPorAjusteProducto);

			registroTablaBalanceDTO.setSaldoInicial(valores[0]);
			registroTablaBalanceDTO.setIngreso(valores[1]);
			registroTablaBalanceDTO.setConsumo(valores[2]);
			registroTablaBalanceDTO.setSaldoFinal(valores[3]);

			return registroTablaBalanceDTO;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerStockFisico(java.lang.Long, java.lang.Long,
	 * java.lang.String, java.lang.Integer)
	 */
	public Double obtenerStockFisico(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException {
		try {
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes(numeroMes, anioContable);

			Date fechaMesFinal = fechaMesCalendar.getTime();
			fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
			Date fechaMesInicial = fechaMesCalendar.getTime();

			numeroMes++;
			Producto producto = new Producto();
			producto.setPkCodigoProducto(codigoProducto);
			double stockFisico = ajusteProduccionFacade.obtenerStockFisico(producto, codigoLinea, numeroMes, anioContable,
					fechaMesInicial, fechaMesFinal, false);

			return stockFisico;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerProduccionPuestoTrabajoMes(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMes(Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable) throws ServicioGWTGlobalException {
		Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
		numeroMes++;

		List<RegistroPuestoTrabajoProduccionDTO> lista = null;
		try {
			lista = ajusteProduccionFacade
					.obtenerProduccionPuestoTrabajoMes(codigoProducto, codigoLinea, numeroMes, anioContable);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerConceptosComponentesProducto(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public List<RegistroTablaConsumoComponentesDTO> obtenerConceptosComponentesProducto(Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable, Set<String> otrosProductosAjuste) throws ServicioGWTGlobalException {
		Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
		Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes(numeroMes, anioContable);
		numeroMes++;

		Date fechaMesFinal = fechaMesCalendar.getTime();
		fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
		Date fechaMesInicial = fechaMesCalendar.getTime();

		try {

			List<RegistroTablaConsumoComponentesDTO> lista = ajusteProduccionFacade.obtenerConceptosComponentesProducto(
					codigoProducto, codigoLinea, numeroMes, anioContable, fechaMesInicial, fechaMesFinal, otrosProductosAjuste);
			return lista;
		} catch (SesionVencidaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("Error en parte tecnico", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService
	 * #obtenerConsumoComponentesPuestoTrabajo(java.lang.Long, java.lang.Long,
	 * java.lang.String, java.lang.Integer)
	 */
	public Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> obtenerConsumoComponentesPuestoTrabajo(Long codigoProducto,
			Long codigoLinea, String mesContable, Integer anioContable) throws ServicioGWTGlobalException {
		Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
		Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes(numeroMes, anioContable);
		numeroMes++;
		Date fechaMesFinal = fechaMesCalendar.getTime();
		fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
		Date fechaMesInicial = fechaMesCalendar.getTime();

		try {
			Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> consumosPorPuesto = ajusteProduccionFacade
					.obtenerConsumoComponentesPuestoTrabajo(codigoProducto, codigoLinea, numeroMes, anioContable,
							fechaMesInicial, fechaMesFinal);
			return consumosPorPuesto;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/**
	 * Obtiene el factor registro mesnual de factores de dosificacion
	 * 
	 * @param codigoProducto
	 * @param anioContable
	 * @param numeroMes
	 * @param codigoComponente
	 * @return Factordosificacionregistromensu
	 * @throws ServicioGWTGlobalException
	 */
	private Factordosificacionregistromensu obtenerIndiceProduccion(Long codigoProducto, Integer anioContable, Short numeroMes,
			Long codigoComponente) throws ServicioGWTGlobalException {
		Factordosificacionregistromensu indiceProduccion;
		try {
			indiceProduccion = FactorDosificacionRegistroMensualQuerier.obtenerFactorSegunProductoCompoeneteYMes(codigoProducto,
					codigoComponente, numeroMes, anioContable);
			return indiceProduccion;
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#registrarParteTecnico(java.lang.Long,
	 * java.lang.Integer, java.lang.String, java.lang.Long, java.lang.Long,
	 * double, double, double, double, double, double, java.util.List,
	 * java.util.List, java.util.List)
	 */
	public Boolean registrarParteTecnico(Double ajuste, Long codigoLineaNegocio, Integer anio, String mesContable,
			Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance, double produccionLibroBalance,
			double saldoFinalLibroBalance, double consumoLibroBalance, double consumoAjusteBalance,
			double produccionAjusteBalance, List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String observaciones) throws ServicioGWTGlobalException {

		try {
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			numeroMes++;
			Long codigoUsuarioAprueba = null;
			Usuario usuarioRegistra = ServicioImpl.obtenerUsuarioDTO(getUsuarioSession());
			Long codigoUsuarioAjusta = usuarioRegistra.getPkCodigoUsuario();

			ajusteProduccionFacade.validarExistenciaAjusteInicial(numeroMes, anio, codigoLineaNegocio, getUsuarioSession());

			ajusteProduccionFacade.insertarAjusteProducto(ajuste, codigoLineaNegocio, anio, numeroMes, codigoUsuarioAjusta,
					codigoUsuarioAprueba, codigoPlantillaGrupoAjuste, codigoProducto, saldoInicialLibroBalance,
					produccionLibroBalance, saldoFinalLibroBalance, consumoLibroBalance, consumoAjusteBalance,
					produccionAjusteBalance, detallesProduccionPuestoTrabajo, detallesConsumoComponenteAjuste,
					detallesMovimientoAjuste, observaciones);
			return true;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e);
		}
	}

	/**
	 * Metodo que retorna la session
	 * 
	 * @return
	 */
	private HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	/**
	 * Metodo que retorna el usuario actual
	 * 
	 * @return
	 */
	protected UsuarioBean getUsuarioSession() {
		HttpSession sesion = getSession();
		return (UsuarioBean) sesion.getAttribute(USUARIO_SESION);
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#verificarSiExisteAjusteBd(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public AjusteProductoDTO verificarSiExisteAjusteBd(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) throws ServicioGWTGlobalException {
		try {
			return ajusteProduccionFacade.verificarSiExisteAjusteBd(codigoProducto, codigoLinea, mesContable, anioContable);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerProduccionPuestoTrabajoMesBD(java.lang.Long)
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMesBD(Long codigoAjusteProducto)
			throws ServicioGWTGlobalException {
		Session session = null;
		List<RegistroPuestoTrabajoProduccionDTO> registrosDto = new ArrayList<RegistroPuestoTrabajoProduccionDTO>();
		ConsumoPuestoTrabajoLogicFacade consumoPuestoTrabajoLogic = new ConsumoPuestoTrabajoLogic();
		try {
			session = PersistenciaFactory.currentSession();
			List<Puestotrabajoproduccion> puestoTrabProdList = PuestoTrabajoProduccionQuerier
					.obtenerPorCodigoAjusteProducto(codigoAjusteProducto);

			Ajusteproducto ajusteproducto = puestoTrabProdList.get(0).getAjusteproducto();

			Long codigoProducto = ajusteproducto.getProducto().getPkCodigoProducto();

			Ajusteproduccion ajusteproduccion = ajusteproducto.getAjusteproduccion();
			Periodocontable periodocontable = ajusteproduccion.getPeriodocontable();

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, periodocontable.getMesPeriodocontable() - 1);
			calendar.set(Calendar.YEAR, periodocontable.getAnoPeriodocontable());
			calendar.add(Calendar.MONTH, -1);

			Integer mesAnterior = calendar.get(Calendar.MONTH) + 1;
			int anioAnterior = calendar.get(Calendar.YEAR);

			Set<Long> puestos = new HashSet<Long>();
			for (Puestotrabajoproduccion puestotrabajoproduccion : puestoTrabProdList) {
				puestos.add(puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo());
			}

			Long codigoLineanegocio = ajusteproduccion.getLineanegocio().getPkCodigoLineanegocio();

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);

			boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

			Map<Long, ConsumosFactoresPuestoTrabajoDTO> factoresCrudoYCarbon = new HashMap<Long, ConsumosFactoresPuestoTrabajoDTO>();

			if (esClinker) {
				factoresCrudoYCarbon = ajusteProduccionFacade.obtenerFactoresCrudoCarbonPorPuestoDAO(codigoProducto,
						codigoLineanegocio, periodocontable.getAnoPeriodocontable(), periodocontable.getMesPeriodocontable(),
						puestos);
			}

			for (Puestotrabajoproduccion puestotrabajoproduccion : puestoTrabProdList) {
				Puestotrabajo puestotrabajo = puestotrabajoproduccion.getPuestotrabajo();

				RendimientoTermico rendimientoTermico = RendimientoTermicoQuerier.obtenerRendimientoTermico(
						puestotrabajo.getPkCodigoPuestotrabajo(), codigoProducto);
				Long valorMax = rendimientoTermico == null ? -1 : rendimientoTermico.getValorKiloCalorias2();

				String nombreProducto = ProductoQuerier.getById(codigoProducto).getNombreProducto();

				
				Double poderCalorificoPonderado = consumoPuestoTrabajoLogic.hallarPoderCalorficoCarbonPonderadoDAO(
						puestotrabajo.getPkCodigoPuestotrabajo(), codigoProducto, codigoLineanegocio,
						periodocontable.getAnoPeriodocontable(), periodocontable.getMesPeriodocontable());
				RegistroPuestoTrabajoProduccionDTO registro = new RegistroPuestoTrabajoProduccionDTO();
				registro.setCodigoPuestoTrabajo(puestotrabajo.getPkCodigoPuestotrabajo());
				registro.setNombrePuestoTrabajo(puestotrabajo.getNombrePuestotrabajo());
				registro.setNombreProducto(nombreProducto);

				registro.setProduccionHR(puestotrabajoproduccion.getHrPuestotrabajoproduccion());
				Double produccionTM = puestotrabajoproduccion.getTmPuestotrabajoproduccion();
				registro.setProduccionTM(produccionTM);
				registro.setProduccionCarProd(puestotrabajoproduccion.getCarprodPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getCarprodPuestotrabajoproduccion());
				registro.setProduccionCarCalent(puestotrabajoproduccion.getCarcalentPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getCarcalentPuestotrabajoproduccion());
				registro.setBunkerProduccion(puestotrabajoproduccion.getBunkprodPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getBunkprodPuestotrabajoproduccion());
				registro.setBunkerCalent(puestotrabajoproduccion.getBunkcalentPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getBunkcalentPuestotrabajoproduccion());
				registro.setProduccionTMPH(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
				Double produccionKcal = puestotrabajoproduccion.getKcalPuestotrabajoproduccion();
				registro.setProduccionKCAL(produccionKcal == null ? 0d : produccionKcal);

				registro.setAjusteTM(puestotrabajoproduccion.getTmAjustePuestotrabajoproducci());
				registro.setAjusteHR(puestotrabajoproduccion.getHrAjustePuestotrabajoproducci());

				registro.setProduccionRealHR(puestotrabajoproduccion.getHrRealPuestotrabajoproduccion());
				registro.setProduccionRealTM(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
				registro.setProduccionRealCarProd(puestotrabajoproduccion.getCarprodRealPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getCarprodRealPuestotrabajoproduccion());
				registro.setProduccionRealCarCalent(puestotrabajoproduccion.getCarcalentRealPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getCarcalentRealPuestotrabajoproduccion());
				registro.setBunkerProduccionReal(puestotrabajoproduccion.getBunkprodRealPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getBunkprodRealPuestotrabajoproduccion());
				registro.setBunkerCalentReal(puestotrabajoproduccion.getBunkcalentRealPuestotrabajoproduccion() == null ? 0d
						: puestotrabajoproduccion.getBunkcalentRealPuestotrabajoproduccion());
				registro.setProduccionRealTMPH(puestotrabajoproduccion.getTmphRealPuestotrabajoproducci());
				registro.setProduccionRealKCAL(puestotrabajoproduccion.getKcalRealPuestotrabajoproducci() == null ? 0d
						: puestotrabajoproduccion.getKcalRealPuestotrabajoproducci());
				registro.setValorMax(valorMax);
				registro.setPoderCalorificoPonderado(poderCalorificoPonderado == null ? 0d : poderCalorificoPonderado);

				Double[] produccionHorasArray = AjusteProduccionMesQuerier.obtenerProduccionyHoras(codigoProducto,
						codigoLineanegocio, mesAnterior.shortValue(), anioAnterior, puestotrabajo.getPkCodigoPuestotrabajo());

				Double produccionTonaledasAnterior = produccionHorasArray[0];
				Double produccionHorasAnterior = produccionHorasArray[1];

				Producto producto = puestotrabajoproduccion.getAjusteproducto().getProducto();

				Object[] tasas = AjusteProduccionMesQuerier.obtenerTasasMaxMinYNominalporPuestoyProduccion(
						producto.getPkCodigoProducto(), puestotrabajo.getPkCodigoPuestotrabajo(),
						periodocontable.getMesPeriodocontable(), periodocontable.getAnoPeriodocontable());

				registro.setMinimoRendimiento((Double) tasas[0]);
				registro.setMaximoRendimiento((Double) tasas[1]);
				registro.setTasaProduccionNominal((Double) tasas[2]);

				registro.setMesAnteriorHR(produccionHorasAnterior);
				registro.setMesAnteriorTM(produccionTonaledasAnterior);

				ConsumosFactoresPuestoTrabajoDTO consumosFactores = factoresCrudoYCarbon.get(puestotrabajo
						.getPkCodigoPuestotrabajo());
				if (consumosFactores != null && produccionTM != 0) {
					// TODO: eliminar este "if". fue traducción de un if (len >
					// 2) que no tiene
					// sentido
					if (consumosFactores.getFactAntrac() != null) {
						registro.setFactAntrac(consumosFactores.getFactAntrac());
					}
				}

				registrosDto.add(registro);
			}

			return registrosDto;
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService
	 * #obtenerConsumoComponentesPuestoTrabajoBD(java.lang.Long)
	 */
	public Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> obtenerConsumoComponentesPuestoTrabajoBD(
			Long codigoAjusteProducto) throws ServicioGWTGlobalException {
		Session session = null;
		Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> registrosDto = new HashMap<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>();
		try {
			session = PersistenciaFactory.currentSession();
			List<Consumocomponenteajuste> consumos = PuestoTrabajoProduccionQuerier
					.obtenerConsumoComponentesPorCodigoAjusteProducto(codigoAjusteProducto);

			Ajusteproducto ajusteproducto = AjusteProductoQuerier.getById(codigoAjusteProducto);

			Map<Long, Factordosificacionregistromensu> mapaIndicesProduccion = new HashMap<Long, Factordosificacionregistromensu>();

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);
			Long codigoProducto = ajusteproducto.getProducto().getPkCodigoProducto();

			boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

			boolean esMoliendaCemento = false, esMoliendaCrudo = false;

			if (!esClinker) {
				Long codigoLinea = ajusteproducto.getAjusteproduccion().getLineanegocio().getPkCodigoLineanegocio();
				Proceso proceso = HojaRutaQuerier.getProcesoSegunHojaRuta(codigoProducto, codigoLinea);
				if (proceso != null) {
					long codigoProcesoCemento = new Long(
							ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO));
					esMoliendaCemento = proceso.getPkCodigoProceso().longValue() == codigoProcesoCemento;

					long codigoProcesoCrudo = new Long(
							ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CRUDO));
					esMoliendaCrudo = proceso.getPkCodigoProceso().longValue() == codigoProcesoCrudo;
				}
			}

			for (Consumocomponenteajuste consumocomponenteajuste : consumos) {

				Puestotrabajo puestotrabajo = consumocomponenteajuste.getPuestotrabajoproduccion().getPuestotrabajo();

				List<RegistroTablaConsumosPuestoTrabajoDTO> componentes = registrosDto
						.get(puestotrabajo.getNombrePuestotrabajo());

				Periodocontable periodocontable = ajusteproducto.getAjusteproduccion().getPeriodocontable();

				if (componentes == null) {
					componentes = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
					registrosDto.put(puestotrabajo.getNombrePuestotrabajo(), componentes);
				}

				Componente componente = consumocomponenteajuste.getComponente();

				String nombreProducto = componente.getProductoByFkCodigoProductoComponente().getNombreProducto();

				RegistroTablaConsumosPuestoTrabajoDTO registroDTO = new RegistroTablaConsumosPuestoTrabajoDTO(nombreProducto);
				registroDTO.setCodigoPuestoTrabajo(puestotrabajo.getPkCodigoPuestotrabajo());
				registroDTO.setNombrePuestoTrabajo(puestotrabajo.getNombrePuestotrabajo());
				registroDTO.setEditadoManual(consumocomponenteajuste.getEditadoManualConsumocompajus());

				Factordosificacionregistromensu factordosificacion = mapaIndicesProduccion
						.get(componente.getPkCodigoComponente());
				if (factordosificacion == null) {
					Long codigoProductoComponente = componente.getProductoByFkCodigoProductoComponente().getPkCodigoProducto();
					factordosificacion = obtenerIndiceProduccion(codigoProductoComponente,
							periodocontable.getAnoPeriodocontable(), periodocontable.getMesPeriodocontable(),
							componente.getPkCodigoComponente());
					if (factordosificacion != null) {
						mapaIndicesProduccion.put(componente.getPkCodigoComponente(), factordosificacion);
					}
				}

				double indice = factordosificacion == null ? 0d : factordosificacion.getCantidadFactordosificacionregi();

				registroDTO.setCodigoComponente(componente.getPkCodigoComponente());
				registroDTO.setCodigoProductoComponente(componente.getProductoByFkCodigoProductoComponente()
						.getPkCodigoProducto());
				registroDTO.setNombreComponente(nombreProducto);
				registroDTO.setGrupoComponente(componente.getProductoByFkCodigoProductoComponente().getGrupoProducto());

				registroDTO.setDosificacion(indice);

				registroDTO.setMontoConsumido(consumocomponenteajuste.getTmProdConsumocomponenteajus());
				registroDTO.setProduccionPorcentaje(consumocomponenteajuste.getPorcentProduccConsucompajuste());
				registroDTO.setPorcetanjeCarbones(consumocomponenteajuste.getPorcentCarbonConsucompajuste());

				registroDTO.setProduccionRealTM(consumocomponenteajuste.getTmRealConsumocomponenteajuste());
				registroDTO.setProduccionRealPorcentaje(consumocomponenteajuste.getPorcentajeRealConsumocomponen());

				ajusteProduccionFacade.asignarOrden(esMoliendaCemento, registroDTO,
						componente.getProductoByFkCodigoProductoComponente(), esMoliendaCrudo);

				componentes.add(registroDTO);
			}

			Set<String> claves = registrosDto.keySet();
			for (String clave : claves) {
				List<RegistroTablaConsumosPuestoTrabajoDTO> lista = registrosDto.get(clave);
				ajusteProduccionFacade.ordenarListaCompCementos(esMoliendaCemento, lista, esMoliendaCrudo);
			}
			return registrosDto;
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerDatosGridMovimientoAjustesBD(java.lang.Long)
	 */
	public List<RegistroTablaAjusteDTO> obtenerDatosGridMovimientoAjustesBD(Long codigoAjusteProducto)
			throws ServicioGWTGlobalException {
		Session session = null;
		List<RegistroTablaAjusteDTO> registrosTablaAjusteDTO = new ArrayList<RegistroTablaAjusteDTO>();
		try {
			session = PersistenciaFactory.currentSession();
			List<Movimientoajuste> consumos = PuestoTrabajoProduccionQuerier
					.obtenerMovimientoAjustesPorCodigoAjusteProducto(codigoAjusteProducto);

			for (Movimientoajuste movimientoajuste : consumos) {
				RegistroTablaAjusteDTO ajusteDTO = new RegistroTablaAjusteDTO();
				Componente componente = movimientoajuste.getConsumocomponenteajuste().getComponente();
				ajusteDTO.setCodigoComponente(componente.getPkCodigoComponente());
				Puestotrabajo puestotrabajo = movimientoajuste.getConsumocomponenteajuste().getPuestotrabajoproduccion()
						.getPuestotrabajo();
				ajusteDTO.setCodigoPuesto(puestotrabajo.getPkCodigoPuestotrabajo());
				ajusteDTO.setConsumo(movimientoajuste.getCantidadMovimientoajuste());
				ajusteDTO.setNombreComponente(componente.getProductoByFkCodigoProductoComponente().getNombreProducto());
				ajusteDTO.setNombrePuesto(puestotrabajo.getNombrePuestotrabajo());
				ajusteDTO.setMovimientoManual(movimientoajuste.getMovManualMovimientoajuste());
				ajusteDTO.setCodigoProductoComponente(componente.getProductoByFkCodigoProductoComponente().getPkCodigoProducto());
				ajusteDTO.setTipoComponente("Hoja de Ruta");
				registrosTablaAjusteDTO.add(ajusteDTO);
			}

			List<Movimientoajusteproducto> consumosPorProducto = PuestoTrabajoProduccionQuerier
					.obtenerMovimientoAjustesProductoPorCodigoAjusteProducto(codigoAjusteProducto);

			for (Movimientoajusteproducto movimientoajusteproducto : consumosPorProducto) {
				RegistroTablaAjusteDTO ajusteDTO = new RegistroTablaAjusteDTO();
				ajusteDTO.setCodigoComponente(movimientoajusteproducto.getProducto().getPkCodigoProducto());
				Puestotrabajo puestotrabajo = movimientoajusteproducto.getPuestotrabajoproduccion().getPuestotrabajo();
				ajusteDTO.setCodigoPuesto(puestotrabajo.getPkCodigoPuestotrabajo());
				ajusteDTO.setConsumo(movimientoajusteproducto.getCantidadMovimientoajusteproducto());
				ajusteDTO.setNombreComponente(movimientoajusteproducto.getProducto().getNombreProducto());
				ajusteDTO.setNombrePuesto(puestotrabajo.getNombrePuestotrabajo());
				ajusteDTO.setCodigoProductoComponente(movimientoajusteproducto.getProducto().getPkCodigoProducto());
				ajusteDTO.setTipoComponente("Productos");
				// Un Ajuste de Producto siempre será manual
				ajusteDTO.setMovimientoManual(true);

				registrosTablaAjusteDTO.add(ajusteDTO);
			}

			return registrosTablaAjusteDTO;
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerDatosGridConsumoComponentesBD(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public List<RegistroTablaConsumoComponentesDTO> obtenerDatosGridConsumoComponentesBD(Long codigoAjusteProducto,
			Long codigoProducto, Long codigoLinea, String mes, Integer anio, Set<String> otrosProductosAjuste)
			throws ServicioGWTGlobalException {

		Session session = null;
		List<RegistroTablaConsumoComponentesDTO> registrosTablaConsumoComponentesDTO = new ArrayList<RegistroTablaConsumoComponentesDTO>();
		try {
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mes).ordinal()).shortValue();
			Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes(numeroMes, anio);
			Date fechaMesFinal = fechaMesCalendar.getTime();
			fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
			Date fechaMesInicial = fechaMesCalendar.getTime();

			numeroMes++;

			session = PersistenciaFactory.currentSession();

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);
			boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

			boolean esMoliendaCemento = false, esMoliendaCrudo = false;

			if (!esClinker) {
				Proceso proceso = HojaRutaQuerier.getProcesoSegunHojaRuta(codigoProducto, codigoLinea);
				if (proceso != null) {
					long codigoProcesoCemento = new Long(
							ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO));
					esMoliendaCemento = proceso.getPkCodigoProceso().longValue() == codigoProcesoCemento;

					long codigoProcesoCrudo = new Long(
							ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CRUDO));
					esMoliendaCrudo = proceso.getPkCodigoProceso().longValue() == codigoProcesoCrudo;
				}
			}

			List<Object[]> elementos = PuestoTrabajoProduccionQuerier.obtenerComponentesConConsumo(codigoAjusteProducto);

			List<Object[]> otrosElementos = new LinkedList<Object[]>();
			for (String codProductoComp : otrosProductosAjuste) {
				otrosElementos.add(new Object[] { null, ProductoQuerier.getById(Long.valueOf(codProductoComp)) });
			}

			elementos.addAll(otrosElementos);

			Set<Long> codigosComponente = new HashSet<Long>();
			for (Object[] elemento : elementos) {
				final int COL_CODCOMPONENTE = 0, COL_CODPRODUCTOCOMPONENTE = 1;
				Long codComponente = (Long) elemento[COL_CODCOMPONENTE];
				Producto productoComponente = (Producto) elemento[COL_CODPRODUCTOCOMPONENTE];

				// Producto componente2 =
				// consumocomponenteajuste.getComponente().getProductoByFkCodigoProductoComponente();

				if (codigosComponente.contains(productoComponente.getPkCodigoProducto())) {
					continue;
				}
				codigosComponente.add(productoComponente.getPkCodigoProducto());

				RegistroTablaConsumoComponentesDTO dto = crearConsumoComponenteDto(productoComponente, codigoAjusteProducto,
						codigoProducto, codigoLinea, numeroMes, anio);
				dto.setCodigoComponente(codComponente);
				dto.setCodigoProductoComponente(productoComponente.getPkCodigoProducto());

				ajusteProduccionFacade.asignarOrden(esMoliendaCemento, dto, productoComponente, esMoliendaCrudo);

				double stockFisico = ajusteProduccionFacade.obtenerStockFisico(productoComponente, codigoLinea, numeroMes, anio,
						fechaMesInicial, fechaMesFinal, false);
				dto.setFisico(stockFisico);

				registrosTablaConsumoComponentesDTO.add(dto);
			}

			ajusteProduccionFacade.ordenarListaCompCementos(esMoliendaCemento, registrosTablaConsumoComponentesDTO,
					esMoliendaCrudo);

			return registrosTablaConsumoComponentesDTO;
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (RuntimeException e) {
			logger.error("Error en partetecnico", e);
			throw new ServicioGWTGlobalException("Error en servidor. Contacte al administrador", e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Crea DTO de consumo componente
	 * 
	 * @param componente Producto
	 * @param codigoAjusteProducto Long
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mes Short
	 * @param anio Integer
	 * @return RegistroTablaConsumoComponentesDTO
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	private RegistroTablaConsumoComponentesDTO crearConsumoComponenteDto(Producto componente, Long codigoAjusteProducto,
			Long codigoProducto, Long codigoLinea, Short mes, Integer anio) throws LogicaException, EntornoEjecucionException,
			ElementoNoEncontradoException {
		String codigoClinkerI = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODCUTO_CLK_I);
		boolean esClinkerI = codigoClinkerI.equals(componente.getPkCodigoProducto().toString());

		Double[] arrayStocks = ajusteProduccionFacade.obtenerStocksMensualesComponente(componente, codigoLinea, mes, anio,
				esClinkerI);

		RegistroTablaConsumoComponentesDTO dto = new RegistroTablaConsumoComponentesDTO(componente.getNombreProducto());
		dto.setNombreComponente(componente.getNombreProducto());
		dto.setGrupoComponente(componente.getGrupoProducto());
		dto.setIngreso(arrayStocks[1]);
		dto.setAjusteLogistico(arrayStocks[3]);

		Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorProductoLineaNegPeriodoContYGrupo(
				componente.getPkCodigoProducto(), codigoLinea, mes, anio);
		if (ajusteProducto != null) {
			dto.setAjusteProducto(ajusteProducto.getAjusteAjusteproducto());
		} else {
			dto.setAjusteProducto(0d);
		}

		Double consumoParteDiario = arrayStocks[2];
		Double saldoInicial = arrayStocks[0];

		dto.setSaldoInicial(saldoInicial);
		double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteExceptuando(
				componente.getPkCodigoProducto(), codigoLinea, mes, anio, codigoProducto);
		double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoExceptuando(
				componente.getPkCodigoProducto(), codigoLinea, mes, anio, codigoProducto);
		dto.setConsumo(consumoParteDiario);
		dto.setConsumoAjuste(consumoParteDiario + consumoPorAjuste + consumoPorAjusteProducto);

		return dto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#validarSiexistencubicaciones(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Boolean validarSiexistencubicaciones(String codigoLineaNegocio, String anioStr, String mesStr)
			throws ServicioGWTGlobalException {
		Integer anio = new Integer(anioStr);
		Long lineaNegocio = Long.valueOf(codigoLineaNegocio);
		Short mes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesStr).ordinal()).shortValue();
		mes++;
		Boolean parteCerrado = false;
		try {
			parteCerrado = ajusteProduccionFacade.verificarParteDiarioCerrado(lineaNegocio, FechaUtil.numeroMesANombreMes(mes),
					anio);
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String estadoCubicacionAprobada = null;
		if (parteCerrado) {
			estadoCubicacionAprobada = ManejadorPropiedades.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);
		}

		List<Cubicacion> cubis = CubicacionProductoQuerier.obtenerCubicacionProducto(null, mes.shortValue(), anio,
				estadoCubicacionAprobada);

		return cubis != null && cubis.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#getProductos()
	 */
	public List<ProductoDTO> getProductos() throws ServicioGWTGlobalException {
		List<ProductoDTO> listaProductos = null;
		try {
			listaProductos = productoFacade.obtenerProductosDTO();
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}
		return listaProductos;
	}

	/**
	 * Méotod para obtener la desviacion de horas de ajuste
	 * 
	 * @param detallesProduccionPuestoTrabajo
	 * @param codigoLineaNegocio
	 * @param mesContable
	 * @param anioContable
	 * @return
	 * @throws ServicioGWTGlobalException
	 */
	public Map<String, Double> obtenerDesviacionHoras(Long codigoProducto,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo, Long codigoLineaNegocio,
			String mesContable, Integer anioContable) throws ServicioGWTGlobalException {
		Map<String, Double> desviacionesPuestoTrabajo = new HashMap<String, Double>();
		Short mes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
		mes++;
		try {
			desviacionesPuestoTrabajo = ajusteProduccionMesLogicFacade.obtenerDesviacionHoras(codigoProducto,
					detallesProduccionPuestoTrabajo, codigoLineaNegocio, mes, anioContable);
			return desviacionesPuestoTrabajo;

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServicioGWTGlobalException(ex.getMessage(), ex);
		}
	}

	public List<RegistroTablaConsumosPuestoTrabajoDTO> obtenerDatosCombustible(Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable) throws ServicioGWTGlobalException {
		try {
			List<RegistroTablaConsumosPuestoTrabajoDTO> registrosDto;
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			numeroMes++;

			registrosDto = ajusteProduccionFacade.obtenerConsumoComponenteProducto(codigoProducto, codigoLinea, numeroMes,
					anioContable);

			return registrosDto;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new ServicioGWTGlobalException(ex.getMessage(), ex);
		}
	}

	public Boolean registrarParteTecnicoCombustible(Double mermaIngresada, Double ajuste, Long codigoLineaNegocio, Integer anio,
			String mesContable, Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance,
			double produccionLibroBalance, double saldoFinalLibroBalance, double consumoLibroBalance,
			double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String fechaFactura, String cantidadFactura,
			String cantidadRestante, String observaciones) throws ServicioGWTGlobalException {

		try {
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			numeroMes++;
			Long codigoUsuarioAprueba = null;
			Usuario usuarioRegistra = ServicioImpl.obtenerUsuarioDTO(getUsuarioSession());
			Long codigoUsuarioAjusta = usuarioRegistra.getPkCodigoUsuario();

			ajusteProduccionFacade.validarExistenciaAjusteInicial(numeroMes, anio, codigoLineaNegocio, getUsuarioSession());

			ajusteProduccionFacade.insertarAjusteProductoCombustible(mermaIngresada, ajuste, codigoLineaNegocio, anio, numeroMes,
					codigoUsuarioAjusta, codigoUsuarioAprueba, codigoPlantillaGrupoAjuste, codigoProducto,
					saldoInicialLibroBalance, produccionLibroBalance, saldoFinalLibroBalance, consumoLibroBalance,
					consumoAjusteBalance, produccionAjusteBalance, detallesConsumoComponenteAjuste, detallesMovimientoAjuste,
					fechaFactura, cantidadFactura, cantidadRestante, observaciones);
			return true;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e);
		}
	}

	public String[] obtenerConsumoDesdeFecha(Long codigoLineaNegocio, Long codigoProducto, String fecha)
			throws ServicioGWTGlobalException {
		try {
			return ajusteProduccionFacade.obtenerConsumoComponenteProductoFecha(codigoProducto, codigoLineaNegocio, fecha);
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e);
		}

	}

	public String[] obtenerDatosComprobante(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException {
		try {
			Short numeroMes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			numeroMes++;
			return ajusteProduccionFacade.obtenerDatosComprobante(codigoProducto, codigoLinea, numeroMes, anioContable);
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e);
		}
	}

	/**
	 * Para el Ajuste Bunker
	 */

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.
	 * ParteTecnicoService#obtenerDatosGridMovimientoAjustesBD(java.lang.Long)
	 */
	public List<RegistroTablaAjusteDTO> obtenerDatosGridMovimientoAjustesCombustibleBD(Long codigoAjusteProducto)
			throws ServicioGWTGlobalException {
		Session session = null;
		List<RegistroTablaAjusteDTO> registrosTablaAjusteDTO = new ArrayList<RegistroTablaAjusteDTO>();
		try {
			session = PersistenciaFactory.currentSession();
			List<Movimientoajuste> consumos = PuestoTrabajoProduccionQuerier
					.obtenerMovimientoAjustesPorCodigoAjusteProducto(codigoAjusteProducto);

			for (Movimientoajuste movimientoajuste : consumos) {
				RegistroTablaAjusteDTO ajusteDTO = new RegistroTablaAjusteDTO();
				Componente componente = movimientoajuste.getConsumocomponenteajuste().getComponente();
				ajusteDTO.setCodigoComponente(componente.getPkCodigoComponente());
				Puestotrabajo puestotrabajo = movimientoajuste.getConsumocomponenteajuste().getPuestotrabajoproduccion()
						.getPuestotrabajo();
				ajusteDTO.setCodigoPuesto(puestotrabajo.getPkCodigoPuestotrabajo());
				ajusteDTO.setConsumo(movimientoajuste.getCantidadMovimientoajuste());
				ajusteDTO.setNombreComponente(componente.getProductoByFkCodigoProductoComponente().getNombreProducto());
				ajusteDTO.setNombrePuesto(puestotrabajo.getNombrePuestotrabajo());
				ajusteDTO.setMovimientoManual(movimientoajuste.getMovManualMovimientoajuste());
				ajusteDTO.setCodigoProductoComponente(componente.getProductoByFkCodigoProductoComponente().getPkCodigoProducto());
				if (movimientoajuste.getTipocomponenteajuste() != null) {
					// cargamos por la BD el tipo de componente ajuste (Hoja
					// Ruta / Merma)
					ajusteDTO.setTipoComponente(movimientoajuste.getTipocomponenteajuste().getNombreTipoComponenteAjuste());
				} else {
					// valor por defecto
					ajusteDTO.setTipoComponente("Hoja de Ruta");
				}
				registrosTablaAjusteDTO.add(ajusteDTO);
			}

			List<Movimientoajusteproducto> consumosPorProducto = PuestoTrabajoProduccionQuerier
					.obtenerMovimientoAjustesProductoPorCodigoAjusteProducto(codigoAjusteProducto);

			for (Movimientoajusteproducto movimientoajusteproducto : consumosPorProducto) {
				RegistroTablaAjusteDTO ajusteDTO = new RegistroTablaAjusteDTO();
				ajusteDTO.setCodigoComponente(movimientoajusteproducto.getProducto().getPkCodigoProducto());
				Puestotrabajo puestotrabajo = movimientoajusteproducto.getPuestotrabajoproduccion().getPuestotrabajo();
				ajusteDTO.setCodigoPuesto(puestotrabajo.getPkCodigoPuestotrabajo());
				ajusteDTO.setConsumo(movimientoajusteproducto.getCantidadMovimientoajusteproducto());
				ajusteDTO.setNombreComponente(movimientoajusteproducto.getProducto().getNombreProducto());
				ajusteDTO.setNombrePuesto(puestotrabajo.getNombrePuestotrabajo());
				ajusteDTO.setCodigoProductoComponente(movimientoajusteproducto.getProducto().getPkCodigoProducto());
				ajusteDTO.setTipoComponente("Productos");
				// Un Ajuste de Producto siempre será manual
				ajusteDTO.setMovimientoManual(true);

				registrosTablaAjusteDTO.add(ajusteDTO);
			}

			return registrosTablaAjusteDTO;
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public Boolean validarParteDiarioCerrado(Long codigoLinea, String mesContable, Integer anioContable)
			throws ServicioGWTGlobalException {
		try {
			return ajusteProduccionFacade.verificarParteDiarioCerrado(codigoLinea, mesContable, anioContable);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	public Boolean validarSiExisteAjustes(String codigoLineaNegocio, String anioStr, String mesStr) {

		Boolean existeAjuste = false;
		Integer anio = new Integer(anioStr);
		Long lineaNegocio = Long.valueOf(codigoLineaNegocio);
		Short mes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesStr).ordinal()).shortValue();
		mes++;

		try {
			List<AjusteProduccionBean> ajustesProduccion = ajusteProduccionFacade.obtenerAjustePorduccionPorPerdiodoLineaNegocio(
					lineaNegocio, mes, anio);

			if (ajustesProduccion != null && ajustesProduccion.size() > 0) {
				existeAjuste = true;
			}
		} catch (LogicaException e1) {
			logger.error(e1);
		}

		return existeAjuste;

	}
}