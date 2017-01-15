package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoCombustibleBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteProductoBean;
import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoConsumoComponentesBean;
import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoPuestoTrabajoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.DetallePuestoTrabajoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.DetallePuestoTrabajoConsumoCombustibleBean;
import pe.com.pacasmayo.sgcp.bean.OrdenReporteBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteConsumosVentasCal_Bean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ConsumoComponenteProductoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoPuestoTrabajoComponenteBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoConsumoCombustibleBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ReporteConsumoVentasCal_List;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.GenerarParteTecnicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.OrdenReporteLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Balanceproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.querier.BalanceProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoCombustiblesQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoComponenteAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaAjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoProduccionQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

public class GenerarParteTecnicoLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		GenerarParteTecnicoLogicFacade, ConstantesLogicaNegocio {

	private Log logger = LogFactory.getLog(this.getClass());
	private AjusteProduccionMesLogicFacade ajusteProduccionFacade;
	public static final String CODIGO_PRODCUTO_CLK_I = "registro.cubicacion.codigo.producto.clk1";
	String mensajeError = "";
	private ParametroSistemaLogicFacade parametro;
	private final String CONCEPTO_SALDO_INICIAL = "Saldo Inicial";

	private final String CONCEPTO_PRODUCCION = "Producci\u00F3n";
	private final String CONCEPTO_CONSUMO = "Consumo";
	private final String CATEGORIA_COMBUSTIBLE = "combustible";
	private BeanFactory beanFactory;
	private ProductoLogicFacade productoFacade;
	NumberFormat decimalFormat = NumberFormat.getInstance(Locale.UK);
	private MovimientoLogicFacade movimientoLogicFacade;

	public GenerarParteTecnicoLogic() {
		ajusteProduccionFacade = new AjusteProduccionMesLogic();
		parametro = new ParametroSistemaLogic();
		beanFactory = BeanFactoryImpl.getInstance();
		productoFacade = new ProductoLogic();
		movimientoLogicFacade = new MovimientoLogic();
	}

	// public static void main(String[] args) throws
	// ElementoNoEncontradoException {
	// GenerarParteTecnicoLogic gptl = new GenerarParteTecnicoLogic();
	// Produccion producto = ProduccionQuerier.getById(Long.valueOf(122));
	// List<Object[]> list = new ArrayList<Object[]>();
	// list.add(new Object[] { producto.getProducto(), producto.getProceso() });
	// gptl.obtenerDetalleParteTecnicoConsumo(list, Long.valueOf(1),
	// Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), Short
	// .valueOf("10"), 2012);
	// }

	/**
	 * Método para obtener los balances de consumo de los componentes por
	 * producto
	 * 
	 * @param productos
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @param estadoAjuste
	 * @return
	 */
	private List<DetalleParteTecnicoConsumoComponentesBean> obtenerDetalleParteTecnicoConsumo(List<Object[]> productosProcesos,
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio) {
		List<DetalleParteTecnicoConsumoComponentesBean> detalles = new ArrayList<DetalleParteTecnicoConsumoComponentesBean>();
		DetalleParteTecnicoConsumoComponentesBean detalle = new DetalleParteTecnicoConsumoComponentesBeanImpl();

		double saldoInicial = 0;

		double ingreso = 0;
		double consumo = 0;
		double ingresoAcumulado = 0;
		double consumoAcumulado = 0;

		for (int j = 0; j < productosProcesos.size(); j++) {
			detalle = new DetalleParteTecnicoConsumoComponentesBeanImpl();

			Producto producto = (Producto) productosProcesos.get(j)[0];
			Proceso proceso = (Proceso) productosProcesos.get(j)[1];
			saldoInicial = 0;

			ingreso = 0;
			consumo = 0;
			ingresoAcumulado = 0;
			consumoAcumulado = 0;

			// 1.2 Se obtienen todos los balances de los productos
			List<Balanceproducto> balances = BalanceProductoQuerier.obtenerBalanceProducto_(producto.getPkCodigoProducto(),
					codigoLineaNegocio, mes, anio, null);
			// SI PRODUCTO NO SE ENCUENTRA ALGUN BALANCE BUSCAR EN
			// CONSUMOCOMPONENTEAJUSTE

			// OBTENGO el ajuste
			// FIXME: arrglar consulta de ajuste
			double ajuste = PuestoTrabajoProduccionQuerier.obtenerAjustePTrabajoProducnPorIdAjusteProducto(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);

			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteValidLineNego(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoValidandoLineaNegocio(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);

			if (balances != null && balances.size() > 0) {

				ProductoBean productoBean = new ProductoBeanImpl();
				productoBean.setCodigo(producto.getPkCodigoProducto());
				Double movimientoLogiscto = null;
				try {
					movimientoLogiscto = movimientoLogicFacade.obtenerMovimentoLogisticoProductoDAO(codigoLineaNegocio,
							productoBean, anio, mes);
				} catch (LogicaException e) {
					logger.error(e.getMensaje());
				}

				if (movimientoLogiscto == null) {
					movimientoLogiscto = 0d;
				}

				// 1.3 Se obtienen calcula el total del balance de cada producto
				for (int i = 0; i < balances.size(); i++) {

					String nombreConcepto = balances.get(i).getConcepto().getNombreConcepto();

					if (nombreConcepto.equals(CONCEPTO_SALDO_INICIAL)) {
						saldoInicial += balances.get(i).getMontoBalanceproducto();
					}

					if (nombreConcepto.equals(CONCEPTO_PRODUCCION)) {
						ingreso += balances.get(i).getMontoBalanceproducto();
					}
					if (nombreConcepto.equals(CONCEPTO_CONSUMO)) {
						consumo += balances.get(i).getMontoBalanceproducto();
					}
				}

				// 1.4 Se alimenta linea detalle con los valores de los
				// conceptos por cada producto
				detalle.setComponente(producto.getNombreProducto());
				detalle.setCodigoComponente(producto.getPkCodigoProducto());
				detalle.setSaldoInicial(saldoInicial);

				// detalle.setSaldoFinal((saldoFinal + ajuste) -
				// (consumoPorAjuste + consumoPorAjusteProducto));
				detalle.setIngreso(ingreso + ajuste);
				detalle.setConsumo(consumo + consumoPorAjuste + consumoPorAjusteProducto);
				detalle.setSaldoFinal(((detalle.getIngreso() + detalle.getSaldoInicial()) - detalle.getConsumo())
						+ movimientoLogiscto);
				detalle.setMovimientoLogistico(movimientoLogiscto);
				detalle.setUnidadMedida(producto.getUnidadmedida().getNombreUnidadmedida());
				detalle.setProceso(proceso.getNombreProceso());

				detalle.setTipoProducto(proceso.getTipoproducto().getSiglasTipoproducto());

				detalle.setOrdenProceso(proceso.getOrdenEjecucionProceso());
				detalle.setLinea(proceso.getLineanegocio().getNombreLineanegocio());

				// 1.5 Se obtienen todos los balances del año, para calcular los
				// conceptos acumulados
				List<Balanceproducto> balancesAcumulados = BalanceProductoQuerier.obtenerConceptosAcumulados(
						producto.getPkCodigoProducto(), codigoLineaNegocio, anio, null, mes);

				for (int i = 0; i < balancesAcumulados.size(); i++) {
					String nombreConcepto = balancesAcumulados.get(i).getConcepto().getNombreConcepto();

					if (nombreConcepto.equals(CONCEPTO_PRODUCCION)) {
						ingresoAcumulado += balancesAcumulados.get(i).getMontoBalanceproducto();
					}
					if (nombreConcepto.equals(CONCEPTO_CONSUMO)) {
						consumoAcumulado += balancesAcumulados.get(i).getMontoBalanceproducto();
					}
				}

				ajuste = PuestoTrabajoProduccionQuerier.obtenerAjusteAcumuladoPTrabajo(producto.getPkCodigoProducto(),
						codigoLineaNegocio, mes, anio);
				consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteAcumulado(
						producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
				consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoAcumulado(
						producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
				// 1.6 Se alimenta linea detalle con los balances acumulados de
				// los productos
				detalle.setConsumoAcumulado(consumoAcumulado + consumoPorAjuste + consumoPorAjusteProducto);
				detalle.setProduccionAcumulada(ingresoAcumulado + ajuste);

				// 1.7 Se establece la lista de componentes
				detalles.add(detalle);
			} else {

				detalles.add(crearConsumoComponenteDto(producto, proceso, codigoLineaNegocio, mes, anio));

			}

		}

		return detalles;
	}

	/**
	 * Método para cargar u obtener los balances de los productos de la linea de
	 * negocio
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 * @throws LogicaException
	 */
	public List<DetalleParteTecnicoConsumoComponentesBean> obtenerTotalBalanceProductosLineaNegocio(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio, Long estadoA)
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
		List<DetalleParteTecnicoConsumoComponentesBean> resultadosConsulta = new ArrayList<DetalleParteTecnicoConsumoComponentesBean>();

		try {
			resultadosConsulta = traerListaConsulta(codigoDivision, codigoSociedad, codigoUnidad, codigoLineaNegocio, mes, anio);
			resultadosConsulta = eliminarProductosRepetidos(resultadosConsulta);

		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return resultadosConsulta;
	}

	private List<DetalleParteTecnicoConsumoComponentesBean> traerListaConsulta(Long codigoDivision, Long codigoSociedad,
			Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio) {

		OrdenReporteLogicFacade ordenLogicFacade = new OrdenReporteLogic();

		// trae Procesos
		// Productos
		List<OrdenReporteBean> ordenReporteProducto;
		List<Object[]> listObjProd = null;
		List<Object[]> listObjProductoscomponente = null;
		List<Object[]> lista = new ArrayList<Object[]>();
		List<DetalleParteTecnicoConsumoComponentesBean> listaDetalle = null;
		List<Object[]> productosRestantes = null;

		try {
			List<DetalleParteTecnicoConsumoComponentesBean> listaComponentes = null;

			ordenReporteProducto = ordenLogicFacade
					.obtenerOrdenResumenProductoOrdenadoPorTipoDAO(ConstantesParametro.LISTAR_RESUMEN);

			for (OrdenReporteBean orden : ordenReporteProducto) {
				listObjProd = ComponenteQuerier.obtenerProductosPorProceso(codigoDivision, codigoSociedad, codigoUnidad,
						codigoLineaNegocio, orden.getProceso().getCodigo(), orden.getOrdenProcesoProducto());
				lista.addAll(listObjProd);
			}

			ordenReporteProducto = ordenLogicFacade
					.obtenerOrdenResumenProductoOrdenadoPorTipoDAO(ConstantesParametro.LISTAR_PRODUCTOS_COMPONENTES_RESUMEN);

			listaDetalle = obtenerDetalleParteTecnicoConsumo(lista, codigoDivision, codigoSociedad, codigoUnidad,
					codigoLineaNegocio, mes, anio);

			// trae Componentes
			for (OrdenReporteBean orden2 : ordenReporteProducto) {
				listObjProductoscomponente = ComponenteQuerier.obtenerComponentesPorProductos(codigoDivision, codigoSociedad,
						codigoUnidad, codigoLineaNegocio, orden2.getProducto().getCodigo());

				listaComponentes = obtenerDetalleParteTecnicoConsumo(listObjProductoscomponente, codigoDivision, codigoSociedad,
						codigoUnidad, codigoLineaNegocio, mes, anio);

				Collections.sort(listaComponentes, new Comparator<DetalleParteTecnicoConsumoComponentesBean>() {
					public int compare(DetalleParteTecnicoConsumoComponentesBean o1, DetalleParteTecnicoConsumoComponentesBean o2) {
						int compareCons = Double.valueOf(o2.getConsumo()).compareTo(Double.valueOf(o1.getConsumo()));
						return compareCons;
					}
				});

				listaDetalle.addAll(listaComponentes);

			}

			// Combustibles Solidos
			for (OrdenReporteBean orden2 : ordenReporteProducto) {
				listObjProductoscomponente = ComponenteQuerier.obtenerComponentesPorProductosCombustibles(codigoDivision,
						codigoSociedad, codigoUnidad, codigoLineaNegocio, orden2.getProducto().getCodigo(), true);
				listaDetalle.addAll(obtenerDetalleParteTecnicoConsumo(listObjProductoscomponente, codigoDivision, codigoSociedad,
						codigoUnidad, codigoLineaNegocio, mes, anio));

			}

			// Combustibles Liquidos
			for (OrdenReporteBean orden2 : ordenReporteProducto) {
				listObjProductoscomponente = ComponenteQuerier.obtenerComponentesPorProductosCombustibles(codigoDivision,
						codigoSociedad, codigoUnidad, codigoLineaNegocio, orden2.getProducto().getCodigo(), false);
				listaDetalle.addAll(obtenerDetalleParteTecnicoConsumo(listObjProductoscomponente, codigoDivision, codigoSociedad,
						codigoUnidad, codigoLineaNegocio, mes, anio));

			}

			// productos restantes
			productosRestantes = ComponenteQuerier.obtenerComponentes(codigoDivision, codigoSociedad, codigoUnidad,
					codigoLineaNegocio);
			listaDetalle.addAll(obtenerDetalleParteTecnicoConsumo(productosRestantes, codigoDivision, codigoSociedad,
					codigoUnidad, codigoLineaNegocio, mes, anio));

		} catch (AplicacionException e) {
			e.printStackTrace();
		}

		return listaDetalle;
	}

	private List<DetalleParteTecnicoConsumoComponentesBean> eliminarProductosRepetidos(
			List<DetalleParteTecnicoConsumoComponentesBean> resultadosConsulta) {

		for (int i = 0; i < resultadosConsulta.size(); i++) {
			int indice = buscarProducto(i, resultadosConsulta.get(i).getCodigoComponente(), resultadosConsulta);
			if (indice != -1) {
				resultadosConsulta.remove(indice);
				i = 0;
			}
		}

		return resultadosConsulta;
	}

	private int buscarProducto(int inicio, Long pkCodigoProducto,
			List<DetalleParteTecnicoConsumoComponentesBean> resultadosConsulta) {

		for (int i = inicio + 1; i < resultadosConsulta.size(); i++) {
			if (resultadosConsulta.get(i).getCodigoComponente().compareTo(pkCodigoProducto) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Crea DTO de consumo componente
	 * 
	 * @param componente Producto
	 * @param proceso Long
	 * @param codigoLinea Long
	 * @param mes Short
	 * @param anio Integer
	 * @return RegistroTablaConsumoComponentesDTO
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	private DetalleParteTecnicoConsumoComponentesBean crearConsumoComponenteDto(Producto componente, Proceso proceso,
			Long codigoLinea, Short mes, Integer anio) {
		String codigoClinkerI = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODCUTO_CLK_I);
		boolean esClinkerI = codigoClinkerI.equals(componente.getPkCodigoProducto().toString());
		DetalleParteTecnicoConsumoComponentesBean det = null;
		Double[] arrayStocks;

		Double[] arrayStocksAnual;
		try {

			arrayStocks = ajusteProduccionFacade
					.obtenerStocksMensualComponenteDAO(componente, codigoLinea, mes, anio, esClinkerI);

			det = new DetalleParteTecnicoConsumoComponentesBeanImpl();

			det.setCodigoComponente(componente.getPkCodigoProducto());
			det.setComponente(componente.getNombreProducto());
			det.setUnidadMedida(componente.getUnidadmedida().getNombreUnidadmedida());
			det.setProceso(proceso.getNombreProceso());
			det.setUnidadMedida(componente.getUnidadmedida().getNombreUnidadmedida());
			det.setTipoProducto(proceso.getTipoproducto().getSiglasTipoproducto());
			det.setOrdenProceso(proceso.getOrdenEjecucionProceso());
			det.setLinea(proceso.getLineanegocio().getNombreLineanegocio());

			Double consumoParteDiario = arrayStocks[2];
			Double saldoInicial = arrayStocks[0];
			Double ajuste = arrayStocks[3];
			det.setSaldoInicial(saldoInicial);
			det.setIngreso(arrayStocks[1]);

			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponente_(
					componente.getPkCodigoProducto(), codigoLinea, mes, anio);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProducto_(
					componente.getPkCodigoProducto(), codigoLinea, mes, anio);
			det.setConsumo(consumoParteDiario + consumoPorAjuste + consumoPorAjusteProducto);
			det.setSaldoFinal(((saldoInicial + det.getIngreso()) - det.getConsumo()) + ajuste);
			det.setMovimientoLogistico(ajuste);

			arrayStocksAnual = ajusteProduccionFacade.obtenerStocksAnualComponenteDAO(componente, codigoLinea, mes, anio,
					esClinkerI);

			consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteAnual(
					componente.getPkCodigoProducto(), codigoLinea, mes, anio);
			consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoAnual(
					componente.getPkCodigoProducto(), codigoLinea, mes, anio);

			det.setProduccionAcumulada(arrayStocksAnual[1]);
			det.setConsumoAcumulado(consumoPorAjuste + consumoPorAjusteProducto + arrayStocksAnual[2]);

		} catch (LogicaException e) {
			// TODO manejo de log
			e.printStackTrace();
		}
		return det;
	}

	/**
	 * Método para cargar u obtener los balances de los componentes
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 * @throws LogicaException
	 */
	public List<DetalleParteTecnicoConsumoComponentesBean> obtenerTotalBalanceComponentes(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoProducto, Long codigoLineaNegocio, Short mes, Integer anio,
			Long estadoAjus) throws LogicaException {
		ProductoBean productoBean = productoFacade.obtenerProducto(codigoProducto);

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

		List<DetalleParteTecnicoConsumoComponentesBean> detalles = new ArrayList<DetalleParteTecnicoConsumoComponentesBean>();
		DetalleParteTecnicoConsumoComponentesBean detalle = new DetalleParteTecnicoConsumoComponentesBeanImpl();
		try {

			double saldoInicial = 0;

			double ingreso = 0;
			double consumo = 0;
			double ingresoAcumulado = 0;
			double consumoAcumulado = 0;
			// 1. Se obtiene el Producto Terminado de cada producto
			// Agregado el estado del Ajuste por Fabian Geldres
			List<Balanceproducto> balancesProducto = BalanceProductoQuerier.obtenerBalanceProducto_(codigoProducto,
					codigoLineaNegocio, mes, anio, estadoAjus);

			// OBTENGO el ajuste
			double ajuste = PuestoTrabajoProduccionQuerier.obtenerAjustePTrabajoProducnPorIdAjusteProducto(codigoProducto,
					codigoLineaNegocio, mes, anio);

			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteValidLineNego(codigoProducto,
					codigoLineaNegocio, mes, anio);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoValidandoLineaNegocio(
					codigoProducto, codigoLineaNegocio, mes, anio);

			if (balancesProducto != null && balancesProducto.size() > 0) {

				// 1.1 Se obtienen los valores de los conceptos despues del
				// ajuste
				for (int i = 0; i < balancesProducto.size(); i++) {

					String nombreConcepto = balancesProducto.get(i).getConcepto().getNombreConcepto();

					if (nombreConcepto.equals(CONCEPTO_SALDO_INICIAL))
						saldoInicial += balancesProducto.get(i).getMontoBalanceproducto();

					if (nombreConcepto.equals(CONCEPTO_PRODUCCION))
						ingreso += balancesProducto.get(i).getMontoBalanceproducto();

					if (nombreConcepto.equals(CONCEPTO_CONSUMO))
						consumo += balancesProducto.get(i).getMontoBalanceproducto();

				}

			}
			// 1.2 Se alimenta linea de detalle a mostrar en presentación

			detalle.setComponente(productoBean.getNombre());
			detalle.setCodigoComponente(codigoProducto);
			detalle.setSaldoInicial(saldoInicial);
			detalle.setIngreso(ingreso + ajuste);
			detalle.setConsumo(consumo + consumoPorAjuste + consumoPorAjusteProducto);
			detalle.setSaldoFinal((detalle.getIngreso() + detalle.getSaldoInicial()) - detalle.getConsumo());
			detalle.setUnidadMedida(productoBean.getUnidadMedida().getNombre());
			// 1.3 Se obtienen todos los balances del año, para calcular los
			// conceptos acumulados del producto
			List<Balanceproducto> balancesAcumuladosProducto = BalanceProductoQuerier.obtenerConceptosAcumulados(codigoProducto,
					codigoLineaNegocio, anio, estadoAjus, mes);

			for (int i = 0; i < balancesAcumuladosProducto.size(); i++) {
				String nombreConcepto = balancesAcumuladosProducto.get(i).getConcepto().getNombreConcepto();

				if (nombreConcepto.equals(CONCEPTO_PRODUCCION))
					ingresoAcumulado += balancesAcumuladosProducto.get(i).getMontoBalanceproducto();

				if (nombreConcepto.equals(CONCEPTO_CONSUMO))
					consumoAcumulado += balancesAcumuladosProducto.get(i).getMontoBalanceproducto();
			}

			ajuste = PuestoTrabajoProduccionQuerier.obtenerAjusteAcumuladoPTrabajo(codigoProducto, codigoLineaNegocio, mes, anio);
			consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteAcumulado(codigoProducto,
					codigoLineaNegocio, mes, anio);
			consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoAcumulado(codigoProducto,
					codigoLineaNegocio, mes, anio);
			// 1.6 Se alimenta linea detalle con los balances acumulados de
			// los productos
			detalle.setConsumoAcumulado(consumoAcumulado + consumoPorAjuste + consumoPorAjusteProducto);
			detalle.setProduccionAcumulada(ingresoAcumulado + ajuste);

			detalles.add(detalle);

			// 2. Se obtienen los componentes (PT) del producto
			List<Object[]> componentesPT = ComponenteQuerier.obtenerComponentesPorHojaRutaProduccionYTipoProducto(codigoDivision,
					codigoSociedad, codigoUnidad, codigoProducto, true, "PT", codigoLineaNegocio);

			List<Long> codigosComponentes = obtenerCodigosComponentes(componentesPT);
			if (codigosComponentes != null && codigosComponentes.size() > 0) {

				Map<Long, Double> comsumosComponentes = ConsumoComponenteAjusteQuerier.obtenerConsumoComponenteProducto(
						codigosComponentes, codigoProducto, anio, mes);
				// 4. Se obtienen los avlores de los concpetos de cada
				// componente
				for (int j = 0; j < componentesPT.size(); j++) {

					detalle = null;
					consumo = 0;
					Componente componente = (Componente) componentesPT.get(j)[0];
					Proceso proceso = (Proceso) componentesPT.get(j)[1];
					// 4.1. Se obtienen todos los balances del componente del
					// mes

					detalle = new DetalleParteTecnicoConsumoComponentesBeanImpl();

					// 4.4 Se alimenta linea detalle con los valores de los
					// conceptos por componentes
					Producto productoComponente = componente.getProductoByFkCodigoProductoComponente();
					detalle.setComponente(productoComponente.getNombreProducto());
					detalle.setCodigoComponente(componente.getPkCodigoComponente());
					detalle.setUnidadMedida(productoComponente.getUnidadmedida().getNombreUnidadmedida());
					detalle.setProceso(proceso.getNombreProceso());
					detalle.setTipoProducto(productoComponente.getTipoproducto().getSiglasTipoproducto());
					detalle.setOrdenProceso(proceso.getOrdenEjecucionProceso());
					detalle.setLinea(proceso.getLineanegocio().getNombreLineanegocio());
					consumo = comsumosComponentes.get(componente.getProductoByFkCodigoProductoComponente().getPkCodigoProducto()) != null ? comsumosComponentes
							.get(componente.getProductoByFkCodigoProductoComponente().getPkCodigoProducto()) : 0.0D;
					detalle.setConsumo(consumo);

					// 4.6 Se alimenat linea detalle con los balances acumulados
					// de los componentes
					detalles.add(detalle);
				}
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

		}
		return detalles;
	}

	private List<Long> obtenerCodigosComponentes(List<Object[]> componentesPT) {
		List<Long> codigos = new ArrayList<Long>();
		if (componentesPT != null) {
			for (Object[] objects : componentesPT) {
				codigos.add(((Componente) objects[0]).getProductoByFkCodigoProductoComponente().getPkCodigoProducto());
			}

		}
		return codigos;
	}

	/**
	 * Método para obtener los consumos por puestos de trabajo de un producto
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @throws LogicaException
	 */

	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> obtenerPuestosTrabajoPorLineaNegocio(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio, Long estado)
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

		List<String> combustibles = obtenerCombustibles(codigoDivision, codigoSociedad, codigoUnidad, codigoLineaNegocio);

		Map<String, DetalleParteTecnicoPuestoTrabajoComponenteBean> detallesMap = new HashMap<String, DetalleParteTecnicoPuestoTrabajoComponenteBean>();
		List<DetalleParteTecnicoPuestoTrabajoComponenteBean> detalles = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
		DetalleParteTecnicoPuestoTrabajoComponenteBean detalle = null;

		List<Object[]> productosProcesos = new ArrayList<Object[]>();
		try {

			// 1. Se obtienen el producto de la linea de negocio (PP y PT)
			// TENGO QUE ORDENAR
			productosProcesos = ComponenteQuerier.obtenerComponentesPorHojaRutaTipoProducto(codigoDivision, codigoSociedad,
					codigoUnidad, codigoLineaNegocio);

			// 2. Se obtienen todos los puestos de trabajo de cada producto en
			// el
			// mes
			for (int i = 0; i < productosProcesos.size(); i++) {
				Producto producto = (Producto) productosProcesos.get(i)[0];
				Proceso proceso = (Proceso) productosProcesos.get(i)[1];

				List<Puestotrabajoproduccion> puestosTrabajoProducto = PuestoTrabajoProduccionQuerier
						.obtenerPuestosTrabajoProducto(codigoUnidad, producto.getPkCodigoProducto(), codigoLineaNegocio, mes,
								anio, estado);

				if (puestosTrabajoProducto != null && puestosTrabajoProducto.size() > 0) {
					// 3. Se extraen los puestos de trabajo utilizados y su
					// debida
					// información
					for (Puestotrabajoproduccion puestotrabajoproduccion : puestosTrabajoProducto) {

						Puestotrabajo puestoTrabajo = puestotrabajoproduccion.getPuestotrabajo();

						DetalleParteTecnicoPuestoTrabajoComponenteBean detallePT = detallesMap.get(producto.getNombreProducto());

						if (detallePT == null) {
							// Si aun no existe el registro del puesto
							detalle = new DetalleParteTecnicoPuestoTrabajoComponenteBeanImpl();

							// 1. Defino valores del producto

							detalle.setCodigoComponente(producto.getPkCodigoProducto());
							detalle.setComponente(producto.getNombreProducto());
							detalle.setProceso(proceso.getNombreProceso());
							detalle.setOrdenProceso(proceso.getOrdenEjecucionProceso());
							detalle.setLinea(proceso.getLineanegocio().getNombreLineanegocio());
							detalle.setTipoProducto(proceso.getTipoproducto().getSiglasTipoproducto());

							// 2. Defino valores por cada puesto de trabajo
							List<DetallePuestoTrabajoComponenteBean> detallesPT = new ArrayList<DetallePuestoTrabajoComponenteBean>();

							DetallePuestoTrabajoComponenteBean detallePTComponente = new DetallePuestoTrabajoComponenteBeanImpl();
							detallePTComponente.setCodigoPuestoTrabajo(puestoTrabajo.getPkCodigoPuestotrabajo());

							detallePTComponente.setPuestoTrabajo(puestoTrabajo.getNombrePuestotrabajo());

							double tmReal = puestotrabajoproduccion.getTmRealPuestotrabajoproduccion() != null ? puestotrabajoproduccion
									.getTmRealPuestotrabajoproduccion() : 0.0d;
							double hrReal = puestotrabajoproduccion.getHrRealPuestotrabajoproduccion() != null ? puestotrabajoproduccion
									.getHrRealPuestotrabajoproduccion() : 0.0d;

							detallePTComponente.setProduccion(NumberUtil.Redondear2Decimales(tmReal));
							detallePTComponente.setTiempoProduccion(NumberUtil.Redondear2Decimales(hrReal));

							Map<String, DetallePuestoTrabajoConsumoCombustibleBean> detallesPTConsumoCombustibleAux = new HashMap<String, DetallePuestoTrabajoConsumoCombustibleBean>();
							List<DetallePuestoTrabajoConsumoCombustibleBean> detallesPTConsumoCombustible = new ArrayList<DetallePuestoTrabajoConsumoCombustibleBean>();
							DetallePuestoTrabajoConsumoCombustibleBean detalleConsumoCombustible = null;

							// 3. Determino consumos de un puesto de trabajo
							Set<Consumocomponenteajuste> consumoComponentes = puestotrabajoproduccion
									.getConsumocomponenteajustes();

							if (consumoComponentes != null && consumoComponentes.size() > 0) {

								// 4. determino el consumo de combustible por
								// cada
								// puesto de trabajo
								for (Consumocomponenteajuste consumocomponenteajuste : consumoComponentes) {

									Producto productoCombustible = consumocomponenteajuste.getComponente()
											.getProductoByFkCodigoProductoComponente();

									if (productoCombustible.getTipocategoriaproducto() != null
											&& productoCombustible.getTipocategoriaproducto().getNombreTipocategoriaproducto()
													.toLowerCase().equals("combustible")) {

										detalleConsumoCombustible = detallesPTConsumoCombustibleAux.get(productoCombustible
												.getSiglasProducto());

										if (detalleConsumoCombustible == null) {
											detalleConsumoCombustible = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();

											detalleConsumoCombustible.setCodigoConsumo(consumocomponenteajuste
													.getPkCodigoConsumocomponenteajus());
											detalleConsumoCombustible.setConsumoComponenteCombustible(consumocomponenteajuste
													.getTmProdConsumocomponenteajus());
											detalleConsumoCombustible.setNombreComponenteCombustible(productoCombustible
													.getSiglasProducto());
											detalleConsumoCombustible.setUnidadMedida(productoCombustible.getUnidadmedida()
													.getNombreUnidadmedida());
										} else {
											double consumo = detalleConsumoCombustible.getConsumoComponenteCombustible();

											detalleConsumoCombustible.setConsumoComponenteCombustible(consumo
													+ consumocomponenteajuste.getTmProdConsumocomponenteajus());
										}

										detallesPTConsumoCombustibleAux.put(
												detalleConsumoCombustible.getNombreComponenteCombustible(),
												detalleConsumoCombustible);

									}
								}

							} // Finalizo la obtención de los consumos por
								// puesto
								// de trabajo

							// Comlpetar la matriz de consumos antes de
							// establecerla.

							try {

								for (String clave : combustibles) {
									int posicion = combustibles.indexOf(clave);

									DetallePuestoTrabajoConsumoCombustibleBean detallePTConsumo = detallesPTConsumoCombustibleAux
											.get(clave);

									if (detallePTConsumo == null) {
										detallePTConsumo = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();
										detallePTConsumo.setCodigoConsumo(new Long(0));
										detallePTConsumo.setNombreComponenteCombustible(clave);
										detallePTConsumo.setConsumoComponenteCombustible(0);
										detallePTConsumo.setUnidadMedida(ConstantesParametro.TIPOMEDIDA_TM);
									}

									detallesPTConsumoCombustible.add(posicion, detallePTConsumo);
								}

							} catch (Exception e) {
								e.printStackTrace();
								throw new LogicaException(e);
							}

							detallePTComponente.setDetalleConsumoCombustible(detallesPTConsumoCombustible);

							detallesPT.add(detallePTComponente);
							detalle.setDetallePuetoTrabajo(detallesPT);
							detallesMap.put(detalle.getComponente(), detalle);

						}
						// Finaliza Producto nuevo
						else {
							// Ya existe el registro del producto
							List<DetallePuestoTrabajoComponenteBean> detallesPT = new ArrayList<DetallePuestoTrabajoComponenteBean>(
									detallePT.getDetallePuestoTrabajo());

							double totalHorasPT = 0;
							double totalProduccionPT = 0;
							int indiceEncontrado = -1;

							for (int j = 0; j < detallesPT.size(); j++) {
								if (detallesPT.get(j).getCodigoPuestoTrabajo().equals(puestoTrabajo.getPkCodigoPuestotrabajo())) {
									indiceEncontrado = j;
								}
							}

							DetallePuestoTrabajoComponenteBean detallePTComponente = new DetallePuestoTrabajoComponenteBeanImpl();

							if (indiceEncontrado != -1) {
								// Se actualiza
								detallePTComponente = detallesPT.get(indiceEncontrado);
								totalHorasPT = detallePTComponente.getTiempoProduccion();
								totalProduccionPT = detallePTComponente.getProduccion();
								detallePTComponente.setProduccion(totalProduccionPT);
								detallePTComponente.setTiempoProduccion(totalHorasPT);
								detallesPT.set(indiceEncontrado, detallePTComponente);
							} else {
								detallePTComponente.setCodigoPuestoTrabajo(puestoTrabajo.getPkCodigoPuestotrabajo());
								detallePTComponente.setPuestoTrabajo(puestoTrabajo.getNombrePuestotrabajo());

								totalProduccionPT = puestotrabajoproduccion.getTmRealPuestotrabajoproduccion() != null ? puestotrabajoproduccion
										.getTmRealPuestotrabajoproduccion() : 0.0d;
								totalHorasPT = puestotrabajoproduccion.getHrRealPuestotrabajoproduccion() != null ? puestotrabajoproduccion
										.getHrRealPuestotrabajoproduccion() : 0.0d;

								detallePTComponente.setProduccion(totalProduccionPT);
								detallePTComponente.setTiempoProduccion(totalHorasPT);
								detallesPT.add(detallePTComponente);
							}

							detalle.setDetallePuetoTrabajo(detallesPT);

							detallesMap.put(detalle.getComponente(), detalle);
						} // Finalizo opción de producto existente
					} // Finalizo ciclo de puestos de trabajo
				}
			} // Finalizo ciclo de productos

			// Se define lista con detalles del puesto por producto y se
			// totalizan
			// horas y producción
			detalles = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
			Set<String> nombreProductos = detallesMap.keySet();

			for (String nombreProducto : nombreProductos) {
				DetalleParteTecnicoPuestoTrabajoComponenteBean detalleProducto = detallesMap.get(nombreProducto);

				List<DetallePuestoTrabajoComponenteBean> puestosTrabajo = detalleProducto.getDetallePuestoTrabajo();

				double produccionTotal = 0;
				double horasTotal = 0;

				for (DetallePuestoTrabajoComponenteBean detallePuestoTrabajoComponenteBean : puestosTrabajo) {
					produccionTotal += detallePuestoTrabajoComponenteBean.getProduccion();
					horasTotal += detallePuestoTrabajoComponenteBean.getTiempoProduccion();
				}

				// Se totatlizan los valores
				DetallePuestoTrabajoComponenteBean detallePTComponenteTotal = new DetallePuestoTrabajoComponenteBeanImpl();

				detallePTComponenteTotal.setCodigoPuestoTrabajo(new Long(0));
				detallePTComponenteTotal.setProduccion(produccionTotal);
				detallePTComponenteTotal.setTiempoProduccion(horasTotal);
				detallePTComponenteTotal.setPuestoTrabajo("Total");
				detalleProducto.getDetallePuestoTrabajo().add(detallePTComponenteTotal);

				detalles.add(detalleProducto);

			}
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return detalles;
	}

	/**
	 * Obtiene los productos combustibles de la división
	 * 
	 * @param codigoDivision
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public List<String> obtenerCombustibles(Long codigoDivision, Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio)
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

		List<String> combustibles = new ArrayList<String>();

		try {

			List<Producto> productosCombustibles = ProductoQuerier.findCombustibles(codigoDivision, codigoSociedad, codigoUnidad,
					codigoLineaNegocio);

			for (int i = 0; i < productosCombustibles.size(); i++) {
				combustibles.add(productosCombustibles.get(i).getSiglasProducto());
			}
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return combustibles;

	}

	/**
	 * Método para obtener los consumos por puestos de trabajo de un producto
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @throws LogicaException
	 */

	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> obtenerPuestosTrabajoPorProducto(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoProducto, Long codigoLineaNegocio, Short mes, Integer anio,
			Long estadoAjus) throws LogicaException {
		ProductoBean producto = productoFacade.obtenerProducto(codigoProducto);
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

		// Declaracion
		List<DetalleParteTecnicoPuestoTrabajoComponenteBean> detalles = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
		try {

			List<ComponenteBean> combustibles = obtenerCombustiblesSegunProducto(codigoUnidad, codigoLineaNegocio, codigoProducto);
			Map<String, DetallePuestoTrabajoComponenteBean> mapDetallePuestoTrabajo = new java.util.HashMap<String, DetallePuestoTrabajoComponenteBean>();

			// 1. Se obtienen el producto y proceso

			DetalleParteTecnicoPuestoTrabajoComponenteBean detalleProducto = new DetalleParteTecnicoPuestoTrabajoComponenteBeanImpl();
			detalleProducto.setComponente(producto.getNombre());
			detalleProducto.setCodigoComponente(producto.getCodigo());

			// 2. Se obtienen todos los puestos de trabajo del producto del mes
			List<Puestotrabajoproduccion> produccionPuestosTrabajo = PuestoTrabajoProduccionQuerier
					.obtenerPuestosTrabajoProducto(codigoUnidad, producto.getCodigo(), codigoLineaNegocio, mes, anio, estadoAjus);

			// 3. Se extraen los puestos de trabajo utilizados y su debida
			// información
			DetallePuestoTrabajoComponenteBean detallePuestoTrabajo;
			for (Puestotrabajoproduccion puestotrabajoproduccion : produccionPuestosTrabajo) {

				Puestotrabajo puestoTrabajo = puestotrabajoproduccion.getPuestotrabajo();
				detallePuestoTrabajo = mapDetallePuestoTrabajo.get(puestoTrabajo.getNombrePuestotrabajo());
				if (detallePuestoTrabajo != null) {

				} else {
					mapDetallePuestoTrabajo.put(puestoTrabajo.getNombrePuestotrabajo(),
							crearDetalleOperacionPuestoTrabajo(puestotrabajoproduccion, combustibles));
				}

			}
			// Aqui empieza a totalizar la suma de los puestos de trabajo
			List<DetallePuestoTrabajoComponenteBean> listaProducccionPuestoTrabajo = new ArrayList<DetallePuestoTrabajoComponenteBean>(
					mapDetallePuestoTrabajo.values());
			Double sumaProduccion = 0d;
			Double sumaHoras = 0d;
			for (DetallePuestoTrabajoComponenteBean detallePuestoTrabajoComponenteBean : listaProducccionPuestoTrabajo) {
				sumaProduccion += detallePuestoTrabajoComponenteBean.getProduccion();
				sumaHoras += detallePuestoTrabajoComponenteBean.getTiempoProduccion();
			}

			// Se totatlizan los valores
			DetallePuestoTrabajoComponenteBean detallePTComponenteTotal = new DetallePuestoTrabajoComponenteBeanImpl();

			detallePTComponenteTotal.setCodigoPuestoTrabajo(new Long(0));
			detallePTComponenteTotal.setProduccion(sumaProduccion);
			detallePTComponenteTotal.setTiempoProduccion(sumaHoras);
			detallePTComponenteTotal.setPuestoTrabajo("Total");
			listaProducccionPuestoTrabajo.add(detallePTComponenteTotal);

			detalleProducto.setDetallePuetoTrabajo(listaProducccionPuestoTrabajo);

			detalles.add(detalleProducto);
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

		}
		return detalles;
	}

	private DetallePuestoTrabajoComponenteBean crearDetalleOperacionPuestoTrabajo(
			Puestotrabajoproduccion puestotrabajoproduccion, List<ComponenteBean> combustibles) {

		DetallePuestoTrabajoComponenteBean operacionPuestoTrabajo = new DetallePuestoTrabajoComponenteBeanImpl();
		operacionPuestoTrabajo.setCodigoPuestoTrabajo(puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo());
		operacionPuestoTrabajo.setPuestoTrabajo(puestotrabajoproduccion.getPuestotrabajo().getNombrePuestotrabajo());
		operacionPuestoTrabajo.setProduccion(obtenerProduccionRealPuestoTrabajo(puestotrabajoproduccion));
		operacionPuestoTrabajo.setTiempoProduccion(obtenerTiempoRealPuestoTrabajo(puestotrabajoproduccion));

		operacionPuestoTrabajo
				.setDetalleConsumoCombustible(consumoCombustiblePuestoTrabajo(combustibles, puestotrabajoproduccion));

		return operacionPuestoTrabajo;
	}

	private List<DetallePuestoTrabajoConsumoCombustibleBean> consumoCombustiblePuestoTrabajo(List<ComponenteBean> combustibles,
			Puestotrabajoproduccion puestotrabajoproduccion) {
		List<DetallePuestoTrabajoConsumoCombustibleBean> consumoCombustiblePuestoTrabajo = new ArrayList<DetallePuestoTrabajoConsumoCombustibleBean>();
		Map<String, Consumocomponenteajuste> consumoPuestoTrabajo = obtenerListacombustible(puestotrabajoproduccion
				.getConsumocomponenteajustes());
		for (ComponenteBean combustible : combustibles) {
			Consumocomponenteajuste consumocompAjuste = consumoPuestoTrabajo.get(combustible.getProductoComponente().getNombre());
			if (consumocompAjuste != null) {
				Producto productoCombustible = consumocompAjuste.getComponente().getProductoByFkCodigoProductoComponente();
				DetallePuestoTrabajoConsumoCombustibleBean consumoCombstible = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();
				consumoCombstible.setCodigoConsumo(consumocompAjuste.getPkCodigoConsumocomponenteajus());
				consumoCombstible.setConsumoComponenteCombustible(consumocompAjuste.getTmProdConsumocomponenteajus());
				consumoCombstible.setNombreComponenteCombustible(productoCombustible.getSiglasProducto());
				consumoCombstible.setUnidadMedida(productoCombustible.getUnidadmedida().getNombreUnidadmedida());
				consumoCombustiblePuestoTrabajo.add(consumoCombstible);
			} else {
				consumoCombustiblePuestoTrabajo.add(buscarConsumoComponente(puestotrabajoproduccion, combustible));
			}

		}
		return consumoCombustiblePuestoTrabajo;
	}

	private DetallePuestoTrabajoConsumoCombustibleBean buscarConsumoComponente(Puestotrabajoproduccion puestotrabajoproduccion,
			ComponenteBean combustible) {

		DetallePuestoTrabajoConsumoCombustibleBean detallePTConsumo = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();

		detallePTConsumo.setNombreComponenteCombustible(combustible.getProductoComponente().getSiglas());

		detallePTConsumo.setUnidadMedida(combustible.getProductoComponente().getUnidadMedida().getNombre());

		try {
			List<Consumocomponenteajuste> listaConsumoComponente = ConsumoComponenteAjusteQuerier
					.obtenerByProductoPuestoTrabajoComponente(combustible.getProductoComponente().getCodigo(),
							puestotrabajoproduccion.getAjusteproducto().getAjusteproduccion().getLineanegocio()
									.getPkCodigoLineanegocio(), puestotrabajoproduccion.getAjusteproducto().getAjusteproduccion()
									.getPeriodocontable().getAnoPeriodocontable(), puestotrabajoproduccion.getAjusteproducto()
									.getAjusteproduccion().getPeriodocontable().getMesPeriodocontable(), combustible
									.getProducto().getCodigo(), puestotrabajoproduccion.getPuestotrabajo()
									.getPkCodigoPuestotrabajo());
			double consumo = 0d;
			detallePTConsumo.setCodigoConsumo(Long.valueOf(0));

			for (Consumocomponenteajuste consumocomponenteajuste : listaConsumoComponente) {
				consumo += NumberUtil.sumar(consumocomponenteajuste.getTmProdConsumocomponenteajus(),
						consumocomponenteajuste.getDiferenciaConsumocomponenteaju());
			}
			detallePTConsumo.setConsumoComponenteCombustible(consumo);
		} catch (EntornoEjecucionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AplicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detallePTConsumo;
	}

	private Map<String, Consumocomponenteajuste> obtenerListacombustible(Set<Consumocomponenteajuste> consumoComponentes) {
		Map<String, Consumocomponenteajuste> mapaCombustible = new HashMap<String, Consumocomponenteajuste>();
		for (Consumocomponenteajuste consumocomponenteajuste : consumoComponentes) {
			Producto productoCombustible = consumocomponenteajuste.getComponente().getProductoByFkCodigoProductoComponente();
			Tipocategoriaproducto categoria = productoCombustible.getTipocategoriaproducto();
			if (categoria != null
					&& StringUtils.equalsIgnoreCase(categoria.getNombreTipocategoriaproducto(), CATEGORIA_COMBUSTIBLE)) {
				mapaCombustible.put(productoCombustible.getNombreProducto(), consumocomponenteajuste);
			}
		}
		return mapaCombustible;
	}

	private double obtenerTiempoRealPuestoTrabajo(Puestotrabajoproduccion puestotrabajoproduccion) {

		return NumberUtil.sumar(puestotrabajoproduccion.getHrRealPuestotrabajoproduccion());
	}

	private double obtenerProduccionRealPuestoTrabajo(Puestotrabajoproduccion puestotrabajoproduccion) {

		return NumberUtil.sumar(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
	}

	public Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupoCemento(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, Long estadoPt)
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

			String nombreGrupo = obtenerNombreGrupo(ConstantesLogicaNegocio.NOMBRE_GRUPO_CEMENTO);
			String nombreProceso = obtenerNombreProceso(ConstantesLogicaNegocio.NOMBRE_PROCESO_MOLIENDA_CEMENTO);

			return obtenerConsumoConponenteGrupo(codigoLineaNegocio, codigoUnidad, codigoSociedad, codigoDivision, anio, mes,
					nombreGrupo, nombreProceso);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Valida los valores obligatorios para la busqueda de consumo componente
	 * por grupo
	 * 
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param anio
	 * @param mes
	 * @throws LogicaException
	 */
	private void validarParametros(Long codigoSociedad, Long codigoDivision, Integer anio, Short mes) throws LogicaException {
		if (codigoSociedad == null || codigoSociedad.longValue() <= 0) {
			String mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_SOCIEDAD_REQUERIDO);
			logger.error(mensaje);
			throw new LogicaException(mensaje);
		}
		if (codigoDivision == null || codigoDivision.longValue() <= 0) {
			String mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DIVISION_REQUERIDA);
			logger.error(mensaje);
			throw new LogicaException(mensaje);
		}
		if (anio == null || anio.intValue() <= 0) {
			String mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(PARTETECNICO_ANIO_REQUERIDO);
			logger.error(mensaje);
			throw new LogicaException(mensaje);
		}
		if (mes == null || mes.shortValue() <= 0) {
			String mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(PARTETECNICO_MES_REQUERIDO);
			logger.error(mensaje);
			throw new LogicaException(mensaje);
		}
	}

	private Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupo(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, String nombreGrupo,
			String nombreProceso) throws LogicaException {

		validarParametros(codigoSociedad, codigoDivision, anio, mes);

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();

			Map<String, List<ConsumoComponenteProductoBean>> componentesConsumidos = new LinkedHashMap<String, List<ConsumoComponenteProductoBean>>();
			List<Producto> productos = PlantillaAjusteProductoQuerier.obtenerProductosPorGrupo(nombreGrupo, nombreProceso);

			List<Long> codigosProductos = new ArrayList<Long>();
			for (Producto producto : productos) {
				codigosProductos.add(producto.getPkCodigoProducto());
			}

			List<Consumocomponenteajuste> consumos = ConsumoComponenteAjusteQuerier.obtenerConsumosPorNombreGrupo(
					codigoLineaNegocio, codigoUnidad, codigoSociedad, codigoDivision, codigosProductos, anio, mes, nombreGrupo);

			for (Consumocomponenteajuste consumo : consumos) {

				ConsumoComponenteProductoBean cosnumoComp = new ConsumoComponenteProductoBeanImpl();
				cosnumoComp.setCodigoProductoConsumidor(consumo.getComponente().getProductoByFkCodigoProducto()
						.getPkCodigoProducto());
				cosnumoComp.setNombreProductoConsumidor(consumo.getComponente().getProductoByFkCodigoProducto()
						.getNombreProducto());
				cosnumoComp.setConsumo(consumo.getTmRealConsumocomponenteajuste());

				ProductoBean producto = new ProductoBeanImpl();
				producto.setCodigo((consumo.getComponente().getProductoByFkCodigoProducto().getPkCodigoProducto()));
				producto.setNombre(consumo.getComponente().getProductoByFkCodigoProducto().getNombreProducto());

				cosnumoComp.setProducto(producto);

				ProductoBean productoConsumido = new ProductoBeanImpl();
				productoConsumido.setCodigo((consumo.getComponente().getProductoByFkCodigoProductoComponente()
						.getPkCodigoProducto()));
				productoConsumido
						.setNombre(consumo.getComponente().getProductoByFkCodigoProductoComponente().getNombreProducto());

				cosnumoComp.setUnidadMedida(consumo.getComponente().getProductoByFkCodigoProductoComponente().getUnidadmedida()
						.getNombreUnidadmedida());
				cosnumoComp.setProductoConsumido(productoConsumido);

				if (componentesConsumidos.containsKey(consumo.getComponente().getProductoByFkCodigoProductoComponente()
						.getNombreProducto())) {
					List<ConsumoComponenteProductoBean> lista = componentesConsumidos.get(consumo.getComponente()
							.getProductoByFkCodigoProductoComponente().getNombreProducto());
					// lista.add(cosnumoComp);
					int cod = -1;
					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i).getCodigoProductoConsumidor().compareTo(cosnumoComp.getCodigoProductoConsumidor()) == 0) {
							cod = i;
							break;
						}
					}
					if (cod != -1) {
						double suma = lista.get(cod).getConsumo() + cosnumoComp.getConsumo();
						lista.get(cod).setConsumo(NumberUtil.Redondear2Decimales(suma));
					} else {
						lista.add(cosnumoComp);
					}
				} else {
					List<ConsumoComponenteProductoBean> lista = new ArrayList<ConsumoComponenteProductoBean>();
					lista.add(cosnumoComp);
					componentesConsumidos.put(consumo.getComponente().getProductoByFkCodigoProductoComponente()
							.getNombreProducto(), lista);
				}
			}

			// AGREGO A LA LISTA MoviemientoAjusteProducto
			List<Movimientoajusteproducto> ajuste = MovimientoAjusteProductoQuerier
					.obtenerMovimientoAjusteProductoByCodigoAjusteProducto(codigoLineaNegocio, codigoUnidad, codigosProductos,
							anio, mes, nombreGrupo);

			Iterator<Movimientoajusteproducto> it = ajuste.iterator();
			ArrayList<String> nombreProductosAjustados = new ArrayList<String>(componentesConsumidos.keySet());
			while (it.hasNext()) {
				Movimientoajusteproducto producto = it.next();
				for (String nombre : nombreProductosAjustados) {
					if (nombre.trim().equals(producto.getProducto().getNombreProducto().trim())) {
						it.remove();
					}
				}
			}

			for (Movimientoajusteproducto map : ajuste) {
				List<ConsumoComponenteProductoBean> consumidores = componentesConsumidos.get(map.getProducto()
						.getNombreProducto());

				if (consumidores != null) {

					for (ConsumoComponenteProductoBean ccp : consumidores) {

						if (ccp.getProductoConsumido().getCodigo().compareTo(map.getProducto().getPkCodigoProducto()) == 0) {

							Double valor = ccp.getConsumo();
							valor += map.getCantidadMovimientoajusteproducto();
							ccp.setConsumo(valor);
						}
					}
				} else {
					List<ConsumoComponenteProductoBean> lista = new ArrayList<ConsumoComponenteProductoBean>();
					ConsumoComponenteProductoBean cc = new ConsumoComponenteProductoBeanImpl();
					cc.setCodigoProductoConsumidor(map.getPuestotrabajoproduccion().getAjusteproducto().getProducto()
							.getPkCodigoProducto());
					cc.setConsumo(map.getCantidadMovimientoajusteproducto());
					cc.setNombreProductoConsumidor(map.getPuestotrabajoproduccion().getAjusteproducto().getProducto()
							.getNombreProducto());
					cc.setProducto(beanFactory.transformarProductoBasico(map.getPuestotrabajoproduccion().getAjusteproducto()
							.getProducto()));
					cc.setProductoConsumido(beanFactory.transformarProductoBasico(map.getProducto()));
					cc.setUnidadMedida(map.getProducto().getUnidadmedida().getNombreUnidadmedida());

					lista.add(cc);
					componentesConsumidos.put(map.getProducto().getNombreProducto(), lista);
				}

			}

			if (!componentesConsumidos.isEmpty()) {
				return componentesConsumidos;
			}

			return null;
		} catch (AplicacionException e) {
			e.printStackTrace();
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} catch (MissingResourceException e) {
			e.printStackTrace();
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA) + " - "
					+ ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBTENER_VALOR_ARCHIVO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ClassCastException e) {
			e.printStackTrace();
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA) + " - "
					+ ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBTENER_VALOR_ARCHIVO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupoCrudo(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, Long estadoParteTec)
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

			String nombreGrupo = obtenerNombreGrupo(ConstantesLogicaNegocio.NOMBRE_GRUPO_CRUDO);
			String nombreProceso = obtenerNombreProceso(ConstantesLogicaNegocio.NOMBRE_PROCESO_MOLIENDA_CRUDO);

			return obtenerConsumoConponenteGrupo(codigoLineaNegocio, codigoUnidad, codigoSociedad, codigoDivision, anio, mes,
					nombreGrupo, nombreProceso);
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupoClinker(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, Long estadoPt)
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

			String nombreGrupo = obtenerNombreGrupo(ConstantesLogicaNegocio.NOMBRE_GRUPO_CLINKER);
			String nombreProceso = obtenerNombreProceso(ConstantesLogicaNegocio.NOMBRE_PROCESO_MOLIENDA_CLINKER);

			return obtenerConsumoConponenteGrupo(codigoLineaNegocio, codigoUnidad, codigoSociedad, codigoDivision, anio, mes,
					nombreGrupo, nombreProceso);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	private String obtenerNombreGrupo(String nombreConstante) throws LogicaException {
		try {
			return ManejadorPropiedades.obtenerPropiedadPorClave(nombreConstante);
		} catch (NullPointerException ne) {
			logger.error(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ERROR_OBTENER_VALOR_ARCHIVO),
					ne);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} catch (MissingResourceException mre) {
			logger.error(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ERROR_OBTENER_VALOR_ARCHIVO),
					mre);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} catch (ClassCastException cce) {
			logger.error(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ERROR_OBTENER_VALOR_ARCHIVO),
					cce);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		}
	}

	private String obtenerNombreProceso(String nombreProceso) throws LogicaException {
		try {
			return ManejadorPropiedades.obtenerPropiedadPorClave(nombreProceso);
		} catch (NullPointerException ne) {
			logger.error(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ERROR_OBTENER_VALOR_ARCHIVO),
					ne);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} catch (MissingResourceException mre) {
			logger.error(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ERROR_OBTENER_VALOR_ARCHIVO),
					mre);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} catch (ClassCastException cce) {
			logger.error(ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ERROR_OBTENER_VALOR_ARCHIVO),
					cce);
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		}
	}

	public Map<String, List<ConsumoCombustibleBean>> obtenerProductosCombustibles(Long codigoDivision, Long codigoSociedad,
			Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio, boolean estadoFisicoSolido)
			throws LogicaException {

		Map<String, List<ConsumoCombustibleBean>> componentesConsumidos = new LinkedHashMap<String, List<ConsumoCombustibleBean>>();

		// se validan los parametros
		validarParametros(codigoSociedad, codigoDivision, anio, mes);

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
		} catch (EntornoEjecucionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}

		try {

			List<Producto> productosCombustiblesSolidos = ConsumoCombustiblesQuerier.findCombustiblesPorEstadoFisico(
					codigoDivision, codigoSociedad, codigoUnidad, codigoLineaNegocio, estadoFisicoSolido);

			List<ConsumoCombustibleBean> consumoCombustibleMensual = ConsumoCombustiblesQuerier.obtenerConsumosCombustibles(
					codigoLineaNegocio, codigoUnidad, codigoSociedad, mes, anio, estadoFisicoSolido);

			List<ConsumoCombustibleBean> consumoCombustibleAnual = ConsumoCombustiblesQuerier.obtenerConsumosCombustibles(
					codigoLineaNegocio, codigoUnidad, codigoSociedad, Short.valueOf("-1"), anio, estadoFisicoSolido);

			for (Producto producto : productosCombustiblesSolidos) {

				List<ConsumoCombustibleBean> lista = new ArrayList<ConsumoCombustibleBean>();
				EqualPredicate nameEqlPredicate = new EqualPredicate(producto.getPkCodigoProducto());
				BeanPredicate beanPredicate = new BeanPredicate("codigoProductoCombustible", nameEqlPredicate);
				Collection<ConsumoCombustibleBean> collectionMensual = CollectionUtils.select(consumoCombustibleMensual,
						beanPredicate);
				Collection<ConsumoCombustibleBean> collectionAnual = CollectionUtils.select(consumoCombustibleAnual,
						beanPredicate);

				for (Iterator<ConsumoCombustibleBean> iterator = collectionMensual.iterator(); iterator.hasNext();) {
					ConsumoCombustibleBean object = iterator.next();
					ConsumoCombustibleBean objectMensual = obtenerPorPuestoTrabajo(collectionAnual,
							object.getCodigoPuestoTrabajo());
					if (objectMensual != null) {
						object.setConsumoCalentamientoAcumulado(NumberUtil.validateDouble(objectMensual
								.getConsumoCalentamientoMes()));
						object.setConsumoProduccionAcumulado(NumberUtil.validateDouble(objectMensual.getConsumoProduccionMes()));
					} else {
						object.setConsumoCalentamientoAcumulado(0d);
						object.setConsumoProduccionAcumulado(0d);
					}
					object.setConsumoTotalMes(NumberUtil.sumar(object.getConsumoCalentamientoMes(),
							object.getConsumoProduccionMes()));
					object.setConsumoTotalAcumulado(NumberUtil.sumar(object.getConsumoCalentamientoAcumulado(),
							object.getConsumoProduccionAcumulado()));
					lista.add(object);
				}
				if (lista.size() > 0) {
					componentesConsumidos.put(producto.getNombreProducto(), lista);
				}

			}

		} catch (AplicacionException e) {
			e.printStackTrace();
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA));
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		if (!componentesConsumidos.isEmpty()) {
			return componentesConsumidos;
		}

		return null;

	}

	private ConsumoCombustibleBean obtenerPorPuestoTrabajo(Collection<ConsumoCombustibleBean> collectionAnual,
			Long codigoPuestoTrabajo) {
		for (Iterator<ConsumoCombustibleBean> iterator = collectionAnual.iterator(); iterator.hasNext();) {
			ConsumoCombustibleBean object = iterator.next();
			if (object.getCodigoPuestoTrabajo().compareTo(codigoPuestoTrabajo) == 0) {
				return object;
			}
		}
		return null;
	}

	/**
	 * Obtiene los productos combustibles de la división segun el producto
	 * seleccionado
	 * 
	 * @param codigoDivision
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	private List<ComponenteBean> obtenerCombustiblesSegunProducto(Long codigoUnidad, Long codigoLineaNegocio, Long codigoProducto)
			throws LogicaException {

		List<ComponenteBean> combustibles = new ArrayList<ComponenteBean>();

		List<Componente> productosCombustibles = HojaRutaComponenteQuerier.getCombustiblesPorProductoHojaRutaActiva(
				codigoLineaNegocio, codigoProducto);

		for (int i = 0; i < productosCombustibles.size(); i++) {

			combustibles.add(beanFactory.transformarComponenteSinFactorDosificacion(productosCombustibles.get(i)));
		}
		return combustibles;

	}

	public List<ReporteConsumoVentasCal_List> obtenerConsumosVentasProductos(Long codigoDivision, Long codigoSociedad,
			Long codigoLineaNegocio, Long codigoUnidad, Integer anio, Short mes) throws LogicaException {

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

		List<ReporteConsumosVentasCal_Bean> listaConsumosVentas;
		List<ReporteConsumoVentasCal_List> subReporteVentas = new ArrayList<ReporteConsumoVentasCal_List>();
		ReporteConsumoVentasCal_List reporteConsumoVentasCal_List;
		ReporteConsumosVentasCal_Bean reporteConsumosVentas;
		Double totalVentas = 0d;
		Double totalConsumos = 0d;
		Double totalIngresos = 0d;
		Double totalSalidas = 0d;
		Double totalCal = 0d;
		int decimales = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
		ParametroSistemaBean valoresProductos = parametro
				.obtenerParametroSistemaDAO(ConstantesParametro.REPORTE_CONSUMOS_VENTAS_PRODUCTOS);
		try {
			if (valoresProductos == null) {
				return null;
			}

			String[] valores = valoresProductos.getValor().split(",");
			Producto productoHO;
			for (String producto : valores) {
				listaConsumosVentas = new ArrayList<ReporteConsumosVentasCal_Bean>();
				reporteConsumoVentasCal_List = new ReporteConsumoVentasCal_List();
				reporteConsumosVentas = new ReporteConsumosVentasCal_Bean();

				productoHO = ProductoQuerier.getById(Long.parseLong(producto));
				if (productoHO != null) {

					totalIngresos = MovimientoQuerier.obtenerMovimientoPorProductoPorRangoFechaXClasificacion(
							ConstantesParametro.CODIGO_CLASIFICACION_TIPOMOVIMIENTO_INGRESO, productoHO.getPkCodigoProducto(),
							anio, mes);
					totalSalidas = MovimientoQuerier.obtenerMovimientoPorProductoPorRangoFechaXClasificacion(
							ConstantesParametro.CODIGO_CLASIFICACION_TIPOMOVIMIENTO_SALIDA, productoHO.getPkCodigoProducto(),
							anio, mes);

					totalVentas = totalSalidas - totalIngresos;
					totalConsumos = obtenerConsumoProducto(productoHO, codigoDivision, codigoSociedad, codigoLineaNegocio,
							codigoUnidad, mes, anio);
					totalCal = totalConsumos;
					totalConsumos = totalConsumos - totalVentas;

					valoresProductos = parametro.obtenerParametroSistemaDAO(productoHO.getUnidadmedida().getNombreUnidadmedida());
					if (valoresProductos != null) {
						decimales = Integer.parseInt(valoresProductos.getValor());
					}

					reporteConsumosVentas.setValor_1(productoHO.getNombreProducto());
					reporteConsumosVentas.setValor_2(NumberUtil.redondeoReportePT(totalVentas, decimales, productoHO
							.getUnidadmedida().getNombreUnidadmedida()));
					reporteConsumosVentas.setValor_3(NumberUtil.redondeoReportePT(totalConsumos, decimales, productoHO
							.getUnidadmedida().getNombreUnidadmedida()));
					reporteConsumosVentas.setValor_4(NumberUtil.redondeoReportePT(totalCal, decimales, productoHO
							.getUnidadmedida().getNombreUnidadmedida()));

					listaConsumosVentas.add(reporteConsumosVentas);
					reporteConsumoVentasCal_List.setSubReporteVentasCal(listaConsumosVentas);
					subReporteVentas.add(reporteConsumoVentasCal_List);
				}

			}

			for (ReporteConsumoVentasCal_List subReporte : subReporteVentas) {
				totalIngresos = 0d;
				totalSalidas = 0d;
				totalCal = 0d;
				for (ReporteConsumosVentasCal_Bean datoReporte : subReporte.getSubReporteVentasCal()) {
					totalIngresos += Double.valueOf(datoReporte.getValor_2().toString());
					totalSalidas += Double.valueOf(datoReporte.getValor_3().toString());
					totalCal += Double.valueOf(datoReporte.getValor_4().toString());
				}
				reporteConsumosVentas = new ReporteConsumosVentasCal_Bean();
				reporteConsumosVentas.setValor_1("Total");
				reporteConsumosVentas.setValor_2(NumberUtil.redondear(totalIngresos, decimales) + "");
				reporteConsumosVentas.setValor_3(NumberUtil.redondear(totalSalidas, decimales) + "");
				reporteConsumosVentas.setValor_4(NumberUtil.redondear(totalCal, decimales) + "");
				subReporte.getSubReporteVentasCal().add(reporteConsumosVentas);
			}

		} catch (NumberFormatException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return subReporteVentas;
	}

	private Double obtenerConsumoProducto(Producto producto, Long codigoDivision, Long codigoSociedad, Long codigoLineaNegocio,
			Long codigoUnidad, Short mes, Integer anio) throws LogicaException {
		double consumo = 0;

		// 1.2 Se obtienen todos los balances de los productos
		List<Balanceproducto> balances = BalanceProductoQuerier.obtenerBalanceProducto_(producto.getPkCodigoProducto(),
				codigoLineaNegocio, mes, anio, null);

		if (balances != null && balances.size() > 0) {
			// 1.3 Se obtienen calcula el total del balance de cada producto
			for (int i = 0; i < balances.size(); i++) {

				String nombreConcepto = balances.get(i).getConcepto().getNombreConcepto();

				if (nombreConcepto.equals(CONCEPTO_CONSUMO)) {
					consumo += balances.get(i).getMontoBalanceproducto();
				}
			}
			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteValidLineNego(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoValidandoLineaNegocio(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);

			consumo = consumo + consumoPorAjuste + consumoPorAjusteProducto;
		} else {

			consumo = crearConsumoProducto(producto, codigoLineaNegocio, mes, anio);

		}

		return consumo;
	}

	private double crearConsumoProducto(Producto producto, Long codigoLineaNegocio, Short mes, Integer anio) {
		String codigoClinkerI = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODCUTO_CLK_I);
		boolean esClinkerI = codigoClinkerI.equals(producto.getPkCodigoProducto().toString());

		Double[] arrayStocks;
		Double consumo = 0d;
		try {
			arrayStocks = ajusteProduccionFacade.obtenerStocksMensualComponenteDAO(producto, codigoLineaNegocio, mes, anio,
					esClinkerI);

			Double consumoParteDiario = arrayStocks[2];

			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponente_(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProducto_(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
			consumo = consumoParteDiario + consumoPorAjuste + consumoPorAjusteProducto;

		} catch (LogicaException e) {
			// TODO manejo de log
			e.printStackTrace();
		}
		return consumo;
	}

	private Double validarDouble(Double numero) {
		if (numero != null) {
			return numero;
		}
		return 0D;
	}

	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> obtenerPuestoTrabajoConsumo(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio) throws LogicaException {

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

		Map<String, DetalleParteTecnicoPuestoTrabajoComponenteBean> detallesMap = new HashMap<String, DetalleParteTecnicoPuestoTrabajoComponenteBean>();

		List<DetalleParteTecnicoPuestoTrabajoComponenteBean> detalles = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
		DetalleParteTecnicoPuestoTrabajoComponenteBean detalle = null;

		try {

			List<Object[]> productosProcesos = new ArrayList<Object[]>();

			// 1. Se obtienen el producto de la linea de negocio (PP y PT)
			productosProcesos = ComponenteQuerier.obtenerComponentesPorHojaRutaTipoProducto(codigoDivision, codigoSociedad,
					codigoUnidad, codigoLineaNegocio);

			// 2. Se obtienen todos los puestos de trabajo de cada producto en
			// el
			// mes
			for (int i = 0; i < productosProcesos.size(); i++) {
				Producto producto = (Producto) productosProcesos.get(i)[0];
				Proceso proceso = (Proceso) productosProcesos.get(i)[1];

				List<Puestotrabajoproduccion> puestosTrabajoProducto = PuestoTrabajoProduccionQuerier
						.obtenerPuestosTrabajoProducto(codigoUnidad, producto.getPkCodigoProducto(), codigoLineaNegocio, mes,
								anio, null);

				if (puestosTrabajoProducto != null && puestosTrabajoProducto.size() > 0) {

					// 3. Se extraen los puestos de trabajo utilizados y su
					// debida
					// información
					for (Puestotrabajoproduccion puestotrabajoproduccion : puestosTrabajoProducto) {

						Puestotrabajo puestoTrabajo = puestotrabajoproduccion.getPuestotrabajo();

						// Obtengo por producto el consumo de los componentes
						// asi
						// como la produccion de
						// los puestos de trabajo
						DetalleParteTecnicoPuestoTrabajoComponenteBean detallePT = detallesMap.get(producto.getNombreProducto());

						if (detallePT == null) {

							// Si aun no existe el registro del puesto
							detalle = new DetalleParteTecnicoPuestoTrabajoComponenteBeanImpl();

							// 1. Defino valores del producto

							detalle.setCodigoComponente(producto.getPkCodigoProducto());
							detalle.setComponente(producto.getNombreProducto());
							detalle.setProceso(proceso.getNombreProceso());

							// 2. Agregar los datos a la lista PRODUCCION(TM) Y
							// HORAS(HR) DEl PUESTO TRABAJO
							List<DetallePuestoTrabajoComponenteBean> detallesPT = new ArrayList<DetallePuestoTrabajoComponenteBean>();

							DetallePuestoTrabajoComponenteBean detallePTComponente = new DetallePuestoTrabajoComponenteBeanImpl();

							detallePTComponente.setCodigoPuestoTrabajo(puestoTrabajo.getPkCodigoPuestotrabajo());
							detallePTComponente.setPuestoTrabajo(puestoTrabajo.getNombrePuestotrabajo());

							// Calculo las Toneladas producidad y las Horas
							// trabajadas
							double tmReal = validarDouble(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
							double hrReal = validarDouble(puestotrabajoproduccion.getHrRealPuestotrabajoproduccion());
							// Calculo los ajustes en la produccion y en las
							// horas
							// trajadas
							// tmReal +=
							// validarDouble(puestotrabajoproduccion.getTmAjustePuestotrabajoproducci());
							// hrReal +=
							// validarDouble(puestotrabajoproduccion.getHrAjustePuestotrabajoproducci());

							detallePTComponente.setProduccion(NumberUtil.Redondear2Decimales(tmReal));
							detallePTComponente.setTiempoProduccion(NumberUtil.Redondear2Decimales(hrReal));
							// --------------------------------------------------------------------------------------------------------------------------

							Map<String, DetallePuestoTrabajoConsumoCombustibleBean> detallesPTConsumoCombustibleAux = new HashMap<String, DetallePuestoTrabajoConsumoCombustibleBean>();

							List<DetallePuestoTrabajoConsumoCombustibleBean> detallesPTConsumoCombustible = new ArrayList<DetallePuestoTrabajoConsumoCombustibleBean>();
							DetallePuestoTrabajoConsumoCombustibleBean detalleConsumoCombustible = null;

							// 3. Determino consumos de un puesto de trabajo
							Set<Consumocomponenteajuste> consumoComponentes = puestotrabajoproduccion
									.getConsumocomponenteajustes();

							if (consumoComponentes != null && consumoComponentes.size() > 0) {

								// 4. determino el consumo de los componentes
								// por
								// cada puesto de trabajo
								for (Consumocomponenteajuste consumocomponenteajuste : consumoComponentes) {

									Producto productoCombustible = consumocomponenteajuste.getComponente()
											.getProductoByFkCodigoProductoComponente();

									detalleConsumoCombustible = detallesPTConsumoCombustibleAux.get(productoCombustible
											.getNombreProducto());

									if (detalleConsumoCombustible == null) {
										if (consumocomponenteajuste.getTmRealConsumocomponenteajuste() > 0) {
											detalleConsumoCombustible = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();

											detalleConsumoCombustible.setCodigoConsumo(consumocomponenteajuste
													.getPkCodigoConsumocomponenteajus());
											detalleConsumoCombustible.setConsumoComponenteCombustible(consumocomponenteajuste
													.getTmRealConsumocomponenteajuste());
											detalleConsumoCombustible.setNombreComponenteCombustible(productoCombustible
													.getNombreProducto());
											detalleConsumoCombustible.setUnidadMedida(productoCombustible.getUnidadmedida()
													.getNombreUnidadmedida());
											detallesPTConsumoCombustibleAux.put(
													detalleConsumoCombustible.getNombreComponenteCombustible(),
													detalleConsumoCombustible);
										}

									} else {
										double consumo = detalleConsumoCombustible.getConsumoComponenteCombustible();
										if (consumo > 0) {
											detalleConsumoCombustible.setConsumoComponenteCombustible(consumo
													+ consumocomponenteajuste.getTmRealConsumocomponenteajuste());
											detallesPTConsumoCombustibleAux.put(
													detalleConsumoCombustible.getNombreComponenteCombustible(),
													detalleConsumoCombustible);
										}

									}

								}

							}

							try {
								ArrayList<String> combustibles = new ArrayList<String>(detallesPTConsumoCombustibleAux.keySet());
								for (String clave : combustibles) {

									int posicion = combustibles.indexOf(clave);

									DetallePuestoTrabajoConsumoCombustibleBean detallePTConsumo = detallesPTConsumoCombustibleAux
											.get(clave);

									if (detallePTConsumo == null) {
										detallePTConsumo = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();
										detallePTConsumo.setCodigoConsumo(new Long(0));
										detallePTConsumo.setNombreComponenteCombustible(clave);
										detallePTConsumo.setConsumoComponenteCombustible(0);
										detallePTConsumo.setUnidadMedida(ConstantesParametro.TIPOMEDIDA_TM);
									}

									detallesPTConsumoCombustible.add(posicion, detallePTConsumo);
								}

							} catch (Exception e) {
								throw new LogicaException(e);
							}

							detallePTComponente.setDetalleConsumoCombustible(detallesPTConsumoCombustible);

							detallesPT.add(detallePTComponente);
							detalle.setDetallePuetoTrabajo(detallesPT);
							detallesMap.put(detalle.getComponente(), detalle);

						}
						// Finaliza Producto nuevo
						else {

							// Ya existe el registro del producto
							List<DetallePuestoTrabajoComponenteBean> detallesPT = new ArrayList<DetallePuestoTrabajoComponenteBean>(
									detallePT.getDetallePuestoTrabajo());

							double totalHorasPT = 0;
							double totalProduccionPT = 0;
							int indiceEncontrado = -1;

							for (int j = 0; j < detallesPT.size(); j++) {
								if (detallesPT.get(j).getCodigoPuestoTrabajo().equals(puestoTrabajo.getPkCodigoPuestotrabajo())) {
									indiceEncontrado = j;
								}
							}

							DetallePuestoTrabajoComponenteBean detallePTComponente = new DetallePuestoTrabajoComponenteBeanImpl();

							// ********************************************************************

							Map<String, DetallePuestoTrabajoConsumoCombustibleBean> detallesPTConsumoCombustibleAux = new HashMap<String, DetallePuestoTrabajoConsumoCombustibleBean>();

							List<DetallePuestoTrabajoConsumoCombustibleBean> detallesPTConsumoCombustible = new ArrayList<DetallePuestoTrabajoConsumoCombustibleBean>();
							DetallePuestoTrabajoConsumoCombustibleBean detalleConsumoCombustible = null;

							// 3. Determino consumos de un puesto de trabajo
							Set<Consumocomponenteajuste> consumoComponentes = puestotrabajoproduccion
									.getConsumocomponenteajustes();

							if (consumoComponentes != null && consumoComponentes.size() > 0) {

								// 4. determino el consumo de los componentes
								// por
								// cada puesto de trabajo
								for (Consumocomponenteajuste consumocomponenteajuste : consumoComponentes) {

									Producto productoCombustible = consumocomponenteajuste.getComponente()
											.getProductoByFkCodigoProductoComponente();

									detalleConsumoCombustible = detallesPTConsumoCombustibleAux.get(productoCombustible
											.getNombreProducto());

									if (detalleConsumoCombustible == null) {
										if (consumocomponenteajuste.getTmRealConsumocomponenteajuste() > 0) {
											detalleConsumoCombustible = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();

											detalleConsumoCombustible.setCodigoConsumo(consumocomponenteajuste
													.getPkCodigoConsumocomponenteajus());
											detalleConsumoCombustible.setConsumoComponenteCombustible(consumocomponenteajuste
													.getTmRealConsumocomponenteajuste());
											detalleConsumoCombustible.setNombreComponenteCombustible(productoCombustible
													.getNombreProducto());
											detalleConsumoCombustible.setUnidadMedida(productoCombustible.getUnidadmedida()
													.getNombreUnidadmedida());
											detallesPTConsumoCombustibleAux.put(
													detalleConsumoCombustible.getNombreComponenteCombustible(),
													detalleConsumoCombustible);
										}
									} else {
										double consumo = detalleConsumoCombustible.getConsumoComponenteCombustible();
										if (consumo > 0) {

											detalleConsumoCombustible.setConsumoComponenteCombustible(consumo
													+ consumocomponenteajuste.getTmRealConsumocomponenteajuste());
											detallesPTConsumoCombustibleAux.put(
													detalleConsumoCombustible.getNombreComponenteCombustible(),
													detalleConsumoCombustible);
										}
									}

								}

							}

							try {
								ArrayList<String> combustibles = new ArrayList<String>(detallesPTConsumoCombustibleAux.keySet());
								for (String clave : combustibles) {

									int posicion = combustibles.indexOf(clave);

									DetallePuestoTrabajoConsumoCombustibleBean detallePTConsumo = detallesPTConsumoCombustibleAux
											.get(clave);

									if (detallePTConsumo == null) {
										detallePTConsumo = new DetallePuestoTrabajoConsumoCombustibleBeanImpl();
										detallePTConsumo.setCodigoConsumo(new Long(0));
										detallePTConsumo.setNombreComponenteCombustible(clave);
										detallePTConsumo.setConsumoComponenteCombustible(0);
										detallePTConsumo.setUnidadMedida(ConstantesParametro.TIPOMEDIDA_TM);
									}

									detallesPTConsumoCombustible.add(posicion, detallePTConsumo);
								}

							} catch (Exception e) {
								throw new LogicaException(e);
							}
							detallePTComponente.setDetalleConsumoCombustible(detallesPTConsumoCombustible);
							// ********************************************************************

							if (indiceEncontrado != -1) {
								// Se actualiza
								detallePTComponente = detallesPT.get(indiceEncontrado);
								totalHorasPT = detallePTComponente.getTiempoProduccion();
								totalProduccionPT = detallePTComponente.getProduccion();
								detallePTComponente.setProduccion(totalProduccionPT);
								detallePTComponente.setTiempoProduccion(totalHorasPT);
								detallesPT.set(indiceEncontrado, detallePTComponente);
							} else {
								detallePTComponente.setCodigoPuestoTrabajo(puestoTrabajo.getPkCodigoPuestotrabajo());
								detallePTComponente.setPuestoTrabajo(puestoTrabajo.getNombrePuestotrabajo());

								totalProduccionPT = validarDouble(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
								totalHorasPT = validarDouble(puestotrabajoproduccion.getHrRealPuestotrabajoproduccion());

								// totalProduccionPT +=
								// validarDouble(puestotrabajoproduccion.getTmAjustePuestotrabajoproducci());
								// totalHorasPT +=
								// validarDouble(puestotrabajoproduccion.getHrAjustePuestotrabajoproducci());

								detallePTComponente.setProduccion(totalProduccionPT);
								detallePTComponente.setTiempoProduccion(totalHorasPT);
								detallesPT.add(detallePTComponente);
							}

							detalle.setDetallePuetoTrabajo(detallesPT);

							detallesMap.put(detalle.getComponente(), detalle);
						} // Finalizo opción de producto existente
					} // Finalizo ciclo de puestos de trabajo
				}
			} // Finalizo ciclo de productos

			// Eliminar los componentes que son 0

			// // Se define lista con detalles del puesto por producto y se
			// totalizan
			// // horas y producción
			detalles = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
			Set<String> nombreProductos = detallesMap.keySet();

			for (String nombreProducto : nombreProductos) {

				DetalleParteTecnicoPuestoTrabajoComponenteBean detalleProducto = detallesMap.get(nombreProducto);

				List<DetallePuestoTrabajoComponenteBean> puestosTrabajo = detalleProducto.getDetallePuestoTrabajo();

				double produccionTotal = 0;
				double horasTotal = 0;

				for (DetallePuestoTrabajoComponenteBean detallePuestoTrabajoComponenteBean : puestosTrabajo) {

					produccionTotal += detallePuestoTrabajoComponenteBean.getProduccion();
					horasTotal += detallePuestoTrabajoComponenteBean.getTiempoProduccion();

				}

				// Se totatlizan los valores
				DetallePuestoTrabajoComponenteBean detallePTComponenteTotal = new DetallePuestoTrabajoComponenteBeanImpl();

				detallePTComponenteTotal.setCodigoPuestoTrabajo(new Long(0));
				detallePTComponenteTotal.setProduccion(produccionTotal);
				detallePTComponenteTotal.setTiempoProduccion(horasTotal);
				detallePTComponenteTotal.setPuestoTrabajo("Total");
				detalleProducto.getDetallePuestoTrabajo().add(detallePTComponenteTotal);

				detalles.add(detalleProducto);

			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return detalles;
	}

	/**
	 * Método para obtener los balances de consumo de los componentes por
	 * producto
	 * 
	 * @param productos
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @param estadoAjuste
	 * @return
	 */
	public DetalleParteTecnicoConsumoComponentesBean obtenerDetalleParteTecnicoConsumoparaPD(Producto producto,
			Long codigoLineaNegocio, Short mes, Integer anio) throws LogicaException {

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

		DetalleParteTecnicoConsumoComponentesBean detalle = null;

		try {

			detalle = obtenerDetalleParteTecnicoConsumoparaPDDAO(producto, codigoLineaNegocio, mes, anio);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return detalle;
	}

	/**
	 * Método para obtener los balances de consumo de los componentes por
	 * producto este metodo no cierra la sesion.
	 * 
	 * @param producto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 * @throws LogicaException
	 */

	public DetalleParteTecnicoConsumoComponentesBean obtenerDetalleParteTecnicoConsumoparaPDDAO(Producto producto,
			Long codigoLineaNegocio, Short mes, Integer anio) throws LogicaException {

		String mensajeError = "";

		DetalleParteTecnicoConsumoComponentesBean detalle = new DetalleParteTecnicoConsumoComponentesBeanImpl();

		try {

			double ingreso = 0;
			double consumo = 0;
			ingreso = 0;
			consumo = 0;

			// 1.2 Se obtienen todos los balances de los productos
			List<Balanceproducto> balances = BalanceProductoQuerier.obtenerBalanceProducto_(producto.getPkCodigoProducto(),
					codigoLineaNegocio, mes, anio, null);
			// SI PRODUCTO NO SE ENCUENTRA ALGUN BALANCE BUSCAR EN
			// CONSUMOCOMPONENTEAJUSTE
			// OBTENGO el ajuste
			double ajuste = PuestoTrabajoProduccionQuerier.obtenerAjustePTrabajoProducnPorIdAjusteProducto(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);

			double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteValidLineNego(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoValidandoLineaNegocio(
					producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);

			if (balances != null && balances.size() > 0) {

				detalle.setCodigoComponente(producto.getPkCodigoProducto());
				detalle.setIngreso(ajuste);
				detalle.setConsumo(consumoPorAjuste + consumoPorAjusteProducto);

			} else {

				List<Movimientoajuste> consumoPorAjustePD = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponentePD(
						producto.getPkCodigoProducto(), codigoLineaNegocio, mes, anio);

				for (Movimientoajuste movimientoajuste : consumoPorAjustePD) {

					consumo += movimientoajuste.getCantidadMovimientoajuste();
				}

				detalle.setIngreso(Math.abs(ingreso));
				detalle.setConsumo(consumo + consumoPorAjusteProducto);
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
		return detalle;
	}





	
}
