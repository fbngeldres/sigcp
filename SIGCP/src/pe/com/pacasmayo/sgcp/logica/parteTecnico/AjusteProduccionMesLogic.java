package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.AprobarAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.DetalleAjusteProduccionMesBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaGrupoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.SubReporteAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ConsumoPuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.GenerarParteTecnicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.OrdenProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RendimientoTermicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.RendimientoTermicoLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionDiariaLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.PropiedadesSap;
import pe.com.pacasmayo.sgcp.logica.partediario.ConsumoPuestoTrabajoLogic;
import pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogic;
import pe.com.pacasmayo.sgcp.logica.stock.PeriodoContableLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Balanceproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Concepto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocombustibles;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadomovimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacionregistromensu;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillagrupoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.RendimientoTermico;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tasarealprodregistromensual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipobalance;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocomponenteajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomovimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Valorpromvariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProduccionMesQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.BalanceProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConceptoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoCombustiblesQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoComponenteAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.CubicacionProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoAjusteProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoAjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoMovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorDosificacionRegistroMensualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.LineaNegocioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedicionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PeriodoContableQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaAjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaGrupoAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionDiariaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.RendimientoTermicoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TasaRealProduccionRegistroMensualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoBalanceQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoComponenteAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoMovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ValorPromVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.AjusteProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.ConsumosFactoresPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.OrdenMolCementoDto;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroPuestoTrabajoProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumoComponentesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumosPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.util.DownloadServlet;

import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

public class AjusteProduccionMesLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		AjusteProduccionMesLogicFacade, ConstantesLogicaNegocio {

	private Log logger = LogFactory.getLog(this.getClass());
	private static BeanFactory beanFactory;

	public static long codigoClinker = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLINKER1));
	public static long codigoClinker5 = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLINKER5));
	private static RendimientoTermicoLogicFacade rendimientoTermicoLogic = new RendimientoTermicoLogic();

	public static long codigoCaliza1Fbr = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CALIZA1_FBR));
	public static long codigoCalizaCalFbr = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CALIZA_CAL_FBR));

	public static long codigoClzCanteras = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLZ_CAL_CANTERAS));
	public static long codigoClzTolvas = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLZ_P_CAL_TOLVAS));
	public static long codigoClzTriturac = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLZ_TRITURAC_I));
	public static long codigoClzAdicMol = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLZ_ADIC_MOL_HH_TRIT_SECUND_FABR));
	public static long codigoClzIExp = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLZ_I_EXP));
	public static long codigoClzIPrep = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLZ_I_PREP));

	public static long codigoArcChancSecund = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_ARC_CHANC_SECUND));
	public static long codigoArcChungal = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_ARC_CHUNGAL_FABR));
	public static long codigoArcPituras = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_ARC_PITURAS_FABR));
	public static long codigoArcRioja = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_ARCILLA_RIOJA));

	public static long codigoFeAltaLey = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_FE_BAJA_LEY_80));
	public static long codigoFeBajaLey = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_FE_ALTA_LEY_85));

	public static long codigoArena = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_ARENA));

	public static long codigoFluorita = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_FLUORITA));

	public static long codigoCarbAntrc41 = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_C_ANTR_905_00041));
	public static long codigoCarbAntrc01 = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_C_ANTR_905_00101));
	public static long codigoCarbAntrc02 = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_C_ANTR_905_00102));

	public static long codigoPecoke = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_PECOKE));

	public static long codigoYeso = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_YESO));

	public static String GRUPO_PRODUCTO = "CARBON.MIX";
	String mensajeError = "";
	private static GenerarParteTecnicoLogicFacade parteTecnicoLogic = new GenerarParteTecnicoLogic();
	private static ParametroSistemaLogicFacade parametrosSistema = new ParametroSistemaLogic();
	// private static final ConstantesModuloParteTecnico CONSTANTES =
	// GWT.create(ConstantesModuloParteTecnico.class);

	public static final String ESTADO_APROBADO = ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_APROBADO_AJUSTEPRODUCCION);
	public static final String ESTADO_INICIAL = ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_ESTADO_INICIAL);
	public static final String MENSAJE_ERROR_AJUSTE = ManejadorPropiedades
			.obtenerPropiedadPorClave(ERROR_APROBAR_AJUSTEPRODUCCION);
	private MovimientoLogicFacade movimientoLogicFacade;

	public AjusteProduccionMesLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
		movimientoLogicFacade = new MovimientoLogic();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogicFacade
	 * #obtenerAjusteProduccionMesPorLineaNegocioYEstado(java.lang.Long,
	 * java.lang.Integer, java.lang.Short, java.lang.Long)
	 */
	public List<DetalleAjusteProduccionMesBean> obtenerAjusteProduccionMesPorLineaNegocioYEstado(Long lineaCodigo,
			Integer anioContable, Short mesContable, Long estadoAjusteProduccionCodigo) throws LogicaException {
		List<DetalleAjusteProduccionMesBean> detalles = new ArrayList<DetalleAjusteProduccionMesBean>();

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
			List<Ajusteproduccion> ajustes = AjusteProduccionMesQuerier.findByBussinesLine(lineaCodigo, anioContable,
					mesContable, estadoAjusteProduccionCodigo);
			Lineanegocio linea = LineaNegocioQuerier.getById(lineaCodigo);

			for (int indice = 0; indice < ajustes.size(); indice++) {
				Ajusteproduccion ajuste = ajustes.get(indice);

				DetalleAjusteProduccionMesBean detalle = new DetalleAjusteProduccionMesBeanImpl();
				detalle.setCodigoAjuste(ajuste.getPkCodigoAjusteproduccion());
				detalle.setEstado(ajuste.getEstadoajusteproduccion().getNombreEstadoajusteproduccion());
				detalle.setLineaNegocio(linea.getNombreLineanegocio());
				detalle.setMesAnio(ajuste.getPeriodocontable().getAnoPeriodocontable().toString() + "-"
						+ ajuste.getPeriodocontable().getMesPeriodocontable().toString());
				detalle.setSociedad(linea.getUnidad().getSociedad().getNombreSociedad());
				detalle.setUnidad(linea.getUnidad().getNombreUnidad());
				detalle.setDivision(linea.getUnidad().getSociedad().getDivision().getNombreDivision());
				if (ajuste.getUsuarioByFkCodigoUsuario() != null) {
					detalle.setUsuarioAprueba(ajuste.getUsuarioByFkCodigoUsuario().getLoginUsuario());
				}
				detalle.setUsuarioAjusta(ajuste.getUsuarioByFkCodigoUsuarioRegistra().getLoginUsuario());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				if (ajuste.getFechaAprobacionAjusteproducci() != null) {
					detalle.setFechaAprueba(dateFormat.format(ajuste.getFechaAprobacionAjusteproducci()));
				}

				detalle.setEnvioconsumo(ajuste.getConsumoEnviadoSap());
				detalle.setEnviocombustible(ajuste.getCombutibleEnviadoSap());
				detalles.add(detalle);
			}

		} catch (ElementoNoEncontradoException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_LINEAPROCESO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return detalles;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerGruposProducto(java.lang.Long)
	 */
	public List<PlantillaGrupoAjusteBean> obtenerGruposProducto(Long codigoLineaNegocio) throws LogicaException {
		List<PlantillaGrupoAjusteBean> gruposAjuste = new ArrayList<PlantillaGrupoAjusteBean>();
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
			gruposAjuste = beanFactory.transformarListaPlantillaGrupoAjuste(PlantillaGrupoAjusteQuerier
					.obtenerGruposAjustePorLineaNegocio(codigoLineaNegocio));
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return gruposAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerGruposProductoDataObjects(java.lang.Long)
	 */
	public List<Plantillagrupoajuste> obtenerGruposProductoDataObjects(Long codigoLineaNegocio) throws LogicaException {

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

		List<Plantillagrupoajuste> gruposAjuste = new ArrayList<Plantillagrupoajuste>();
		try {
			gruposAjuste = PlantillaGrupoAjusteQuerier.obtenerGruposAjustePorLineaNegocio(codigoLineaNegocio);
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return gruposAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerProductoGrupos(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductoGrupos(Long codigoGrupoAjuste) throws LogicaException {

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

		List<ProductoBean> productosGrupoAjuste = new ArrayList<ProductoBean>();
		try {
			productosGrupoAjuste = beanFactory.transformarListaProductos(PlantillaAjusteProductoQuerier
					.obtenerProductosPorGruposAjuste(codigoGrupoAjuste));
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productosGrupoAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerProductosLineaNegocio(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductosLineaNegocio(Long codigoLineaNegocio) throws LogicaException {

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

		List<ProductoBean> productosGrupoAjuste = new ArrayList<ProductoBean>();
		try {
			productosGrupoAjuste = beanFactory.transformarListaProductos(PlantillaAjusteProductoQuerier
					.obtenerProductosPorLineaNegocio(codigoLineaNegocio));
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productosGrupoAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerProductosLineaNegocioYClaseProducto(java.lang.Long,
	 * java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductosLineaNegocioYClaseProducto(Long codigoLineaNegocio, Long claseProducto)
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

		List<ProductoBean> productosGrupoAjuste = new ArrayList<ProductoBean>();
		try {
			productosGrupoAjuste = beanFactory.transformarListaProductos(PlantillaAjusteProductoQuerier
					.obtenerProductosPorLineaNegocioYClaseProducto(codigoLineaNegocio, claseProducto));
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productosGrupoAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerAjuste(java.lang.Long, java.lang.Long, java.lang.Short,
	 * java.lang.Integer, java.lang.String, java.util.Date, java.util.Date,
	 * java.lang.Long)
	 */
	public double obtenerAjuste(Long codigoLinea, Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto, Date fechaMesFinal) throws LogicaException {

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

		Double ajuste = 0d;
		try {
			ajuste = obtenerAjustePorMedicionDiaria(codigoProducto, codigoLinea, mesContable, anioContable, fechaMesFinal);

			if (ajuste == null) {

				ajuste = obtenerAjusteMedidaCubicacion(codigoLinea, codigoProducto, mesContable, anioContable,
						estadoCubicacionProducto);
			}

			return ajuste;
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerConceptosLibro(java.lang.Long, java.lang.Long, java.lang.Short,
	 * java.lang.Integer, java.util.Date, java.util.Date, java.lang.Long)
	 */
	public double[] obtenerConceptosLibro(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable,
			Date fechaMesInicial, Date fechaMesFinal, Long tipoMedioAlmacenamiento) throws LogicaException {

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

			return obtenerConceptosLibroDAO(codigoProducto, codigoLinea, mesContable, anioContable, fechaMesInicial,
					fechaMesFinal, tipoMedioAlmacenamiento);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public double[] obtenerConceptosLibroDAO(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable,
			Date fechaMesInicial, Date fechaMesFinal, Long tipoMedioAlmacenamiento) throws LogicaException {

		try {
			// 0 saldo inicial
			// 1 ingreso
			// 2 consumo
			// 3 saldo final
			// 4 Ajuste Logistico
			double[] conceptos = new double[5];

			// El producto se almacena en un silo
			// fecha de fin de mes
			List<Tablakardex> medicionDiaria = AjusteProduccionMesQuerier.obtenerKardexMes(codigoProducto, codigoLinea,
					mesContable, anioContable);
			double saldoInicial = 0d, ingreso = 0d, consumo = 0d;

			if (medicionDiaria != null && medicionDiaria.size() > 0) {
				Tablakardex kardex = medicionDiaria.get(medicionDiaria.size() - 1);
				saldoInicial = kardex.getSaldoInicialTablakardex();

				for (Tablakardex tablakardex : medicionDiaria) {
					ingreso += tablakardex.getIngresoTablakardex();
					consumo += tablakardex.getConsumoTablakardex();
				}
			}

			// Cuando el producto el Clinker
			// Hay dos procesos que estan generando una misma cantidad de CLK -
			// I
			if (codigoClinker == codigoProducto) {
				consumo = consumo / 2;
			}

			// Obtener Movimiento ajuste
			ProductoBean productoBean = new ProductoBeanImpl();
			productoBean.setCodigo(codigoProducto);
			Double movimientoLogiscto = null;
			try {
				movimientoLogiscto = movimientoLogicFacade.obtenerMovimentoLogisticoProductoDAO(codigoLinea, productoBean,
						anioContable, mesContable);
			} catch (LogicaException e) {
				logger.error(e.getMensaje());
			}

			if (movimientoLogiscto == null) {
				movimientoLogiscto = 0d;
			}

			conceptos[0] = saldoInicial;
			conceptos[1] = ingreso;
			conceptos[2] = consumo;
			conceptos[3] = (saldoInicial + ingreso - consumo) + movimientoLogiscto;
			conceptos[4] = movimientoLogiscto;
			return conceptos;
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerProduccionPuestoTrabajoMes(java.lang.Long, java.lang.Long,
	 * java.lang.Short, java.lang.Integer)
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMes(Long codigoProducto, Long codigoLinea,
			Short mesContable, Integer anioContable) throws LogicaException {

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
		List<RegistroPuestoTrabajoProduccionDTO> lista = new ArrayList<RegistroPuestoTrabajoProduccionDTO>();

		try {
			List<Object[]> produccionesPuestoTrabajo = AjusteProduccionMesQuerier.obtenerProduccionPuestoTrabajoMes(
					codigoProducto, codigoLinea, mesContable, anioContable);

			Set<Long> codigosPuestosTrab = new HashSet<Long>();
			for (int i = 0; i < produccionesPuestoTrabajo.size(); i++) {
				Object[] objetos = produccionesPuestoTrabajo.get(i);
				Long codigoPuestoTrabajo = (Long) objetos[0];
				codigosPuestosTrab.add(codigoPuestoTrabajo);
			}

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, mesContable - 1);
			calendar.set(Calendar.YEAR, anioContable);
			calendar.add(Calendar.MONTH, -1);

			Integer mesAnterior = calendar.get(Calendar.MONTH) + 1;
			int anioAnterior = calendar.get(Calendar.YEAR);

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);

			boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;
			boolean excepcionProducto = validarProductoRendimentoTermico(codigoProducto);
			Map<Long, ConsumosFactoresPuestoTrabajoDTO> factoresCrudoYCarbon = new HashMap<Long, ConsumosFactoresPuestoTrabajoDTO>();

			if (esClinker) {
				factoresCrudoYCarbon = obtenerFactoresCrudoCarbonPorPuestoDAO(codigoProducto, codigoLinea, anioContable,
						mesContable, codigosPuestosTrab);
			}

			ConsumoPuestoTrabajoLogicFacade consumoPuestoTrabajoLogic = new ConsumoPuestoTrabajoLogic();
			Map<Long, Double[]> mapaConsumosCarbon = consumoPuestoTrabajoLogic.hallarConsumoPuestoTrabajo(codigoProducto,
					codigoLinea, mesContable, anioContable, Boolean.TRUE);
			Map<Long, Double[]> mapaConsumosBunker = consumoPuestoTrabajoLogic.hallarConsumoPuestoTrabajo(codigoProducto,
					codigoLinea, mesContable, anioContable, Boolean.FALSE);

			String nombreProducto = ProductoQuerier.getById(codigoProducto).getNombreProducto();

			for (int i = 0; i < produccionesPuestoTrabajo.size(); i++) {
				Object[] objetos = produccionesPuestoTrabajo.get(i);
				Long codigoPuestoTrabajo = (Long) objetos[0];
				String nombrePuesto = (String) objetos[1];
				Double produccionTonaledas = (Double) objetos[2];
				Double produccionHoras = (Double) objetos[3];
				Double minimoRendimiento = (Double) objetos[4];
				Double maximoRendimiento = (Double) objetos[5];
				Double tasaNominal = (Double) objetos[6];

				// -------------------calculando Bunker Produccion y
				// Calentamiento, Carbon Produccion y Calentamiento
				Double CarProd = 0d;
				Double CarCalent = 0d;
				Double BunProd = 0d;
				Double BunCalent = 0d;
				Double[] consumoCarbon = mapaConsumosCarbon.get(codigoPuestoTrabajo);
				Double[] consumoBunker = mapaConsumosBunker.get(codigoPuestoTrabajo);

				if (consumoCarbon != null) {
					CarProd = consumoCarbon[0];
					CarCalent = consumoCarbon[1];
				}

				if (consumoBunker != null) {
					BunProd = consumoBunker[0];
					BunCalent = consumoBunker[1];
				}

				Double poderCalorificoPonderado = consumoPuestoTrabajoLogic.hallarPoderCalorficoCarbonPonderadoDAO(
						codigoPuestoTrabajo, codigoProducto, codigoLinea, anioContable, mesContable);

				Double[] produccionHorasMesAntArray = AjusteProduccionMesQuerier.obtenerProduccionyHoras(codigoProducto,
						codigoLinea, mesAnterior.shortValue(), anioAnterior, codigoPuestoTrabajo);

				Double produccionTonaledasAnterior = produccionHorasMesAntArray[0];
				Double produccionHorasAnterior = produccionHorasMesAntArray[1];

				Double produccionKcal = 0d;
				if (esClinker) {
					produccionKcal = AjusteProduccionMesQuerier.obtenerPromedioKcalPuestoTrabMes(codigoProducto,
							codigoPuestoTrabajo, codigoLinea, mesContable, anioContable);
				} else if (excepcionProducto) {
					produccionKcal = AjusteProduccionMesQuerier.obtenerPromedioKcalPuestoTrabMes(codigoProducto,
							codigoPuestoTrabajo, codigoLinea, mesContable, anioContable);
				}

				RendimientoTermico rendimientoTermico = RendimientoTermicoQuerier.obtenerRendimientoTermico(codigoPuestoTrabajo,
						codigoProducto);

				Long valorMax = rendimientoTermico == null ? -1 : rendimientoTermico.getValorKiloCalorias2();

				RegistroPuestoTrabajoProduccionDTO registro = crearRegistroPuestoTrabajoProdDto(codigoPuestoTrabajo,
						nombrePuesto, produccionTonaledasAnterior, produccionHorasAnterior, produccionTonaledas, produccionHoras,
						produccionKcal, minimoRendimiento, maximoRendimiento, tasaNominal, nombreProducto,
						poderCalorificoPonderado, valorMax, CarProd, CarCalent, BunProd, BunCalent);

				ConsumosFactoresPuestoTrabajoDTO consumosFactores = factoresCrudoYCarbon.get(codigoPuestoTrabajo);

				double movimientoAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponentePorPuestoTrabajo(
						codigoPuestoTrabajo, codigoProducto, codigoLinea, mesContable, anioContable);
				double movimientoAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoPorPuestoTrabajo(
						codigoPuestoTrabajo, codigoProducto, codigoLinea, mesContable, anioContable);
				produccionTonaledas = produccionTonaledas + movimientoAjuste + movimientoAjusteProducto;

				if (consumosFactores != null && produccionTonaledas != 0d) {
					if (consumosFactores.getFactAntrac() != null) {
						registro.setFactAntrac(consumosFactores.getFactAntrac());
					}
				}

				lista.add(registro);
			}
		} catch (LogicaException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_EXCEPTION_ELEMENTO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerFactoresCrudoCarbonPorPuesto(java.lang.Long, java.lang.Long,
	 * java.lang.Integer, java.lang.Short, java.util.Set)
	 */
	public Map<Long, ConsumosFactoresPuestoTrabajoDTO> obtenerFactoresCrudoCarbonPorPuesto(Long codigoProducto, Long codigoLinea,
			Integer anioContable, Short numeroMes, Set<Long> codigosPuestosTrab) throws LogicaException {

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

		Map<Long, ConsumosFactoresPuestoTrabajoDTO> factoresClinker;
		try {

			factoresClinker = obtenerFactoresCrudoCarbonPorPuestoDAO(codigoProducto, codigoLinea, anioContable, numeroMes,
					codigosPuestosTrab);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return factoresClinker;
	}

	public Map<Long, ConsumosFactoresPuestoTrabajoDTO> obtenerFactoresCrudoCarbonPorPuestoDAO(Long codigoProducto,
			Long codigoLinea, Integer anioContable, Short numeroMes, Set<Long> codigosPuestosTrab) throws LogicaException {
		// Metodos DAO solo son para utilizar en otros Logic que Tambien utilzan
		// la sesion

		long codHornoVertical1 = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL1));
		long codHornoVertical2 = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL2));
		long codHornoVertical3 = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL3));
		long codHornoVertical4 = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL4));
		long codHornoVertical5 = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL5));
		long codHornoVertical6 = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PARTEDIARIO_PRODUCCION_CLINKER_HORNO_VERTICAL6));

		String mensajeError = "";

		Map<Long, ConsumosFactoresPuestoTrabajoDTO> factoresClinker = new HashMap<Long, ConsumosFactoresPuestoTrabajoDTO>();
		try {

			for (Long codigoPuesto : codigosPuestosTrab) {

				List<Consumopuestotrabajo> consumoPuestoTrab = ConsumoPuestoTrabajoQuerier
						.obtenerRegistrosPorLineaNegPuestoProductoAnnioYMes(codigoPuesto, codigoProducto, codigoLinea,
								anioContable, numeroMes);
				Double[] crudoYCarbon = calcularConsumoCarbonyCrudo(codigoPuesto, codigoLinea, anioContable, numeroMes,
						consumoPuestoTrab);
				double crudo = crudoYCarbon[0];
				double carbon = crudoYCarbon[1];
				double factorAntracita = 0d;

				if (codigoPuesto.longValue() == codHornoVertical1 || codigoPuesto.longValue() == codHornoVertical2
						|| codigoPuesto.longValue() == codHornoVertical3 || codigoPuesto.longValue() == codHornoVertical4
						|| codigoPuesto.longValue() == codHornoVertical5 || codigoPuesto.longValue() == codHornoVertical6) {
					// Solo crudo negro
					long codProductoCrudoNegro = Long.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CRUDO_NEGRO));

					List<Consumopuestotrabajo> consumoCrudoNegro = ConsumoPuestoTrabajoQuerier
							.obtenerRegistrosPorLineaNegPuestoProductoAnnioYMes(null, codProductoCrudoNegro, codigoLinea,
									anioContable, numeroMes);

					double carbones = 0d, totalConsumo = 0d;
					String tipoCombustible = ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_CATEG_PRODUCTO_COMBUSTIBLE)
							.toLowerCase();

					// PRODUCTO_CRUDO_NEGRO
					if (factorAntracita == 0d) {
						for (Consumopuestotrabajo consumo : consumoCrudoNegro) {
							Producto componenet = consumo.getComponente().getProductoByFkCodigoProductoComponente();
							Tipocategoriaproducto tipo = componenet.getTipocategoriaproducto();
							if (tipo != null && tipoCombustible.equals(tipo.getNombreTipocategoriaproducto().toLowerCase())) {
								carbones += consumo.getCantidadConsumopuestotrabajo();
							}
							totalConsumo += consumo.getCantidadConsumopuestotrabajo();
						}
						factorAntracita = totalConsumo != 0d ? (carbones / totalConsumo) * 100 : 0d;
					}
					carbon = factorAntracita * crudo / 100;
				}

				factoresClinker.put(codigoPuesto, new ConsumosFactoresPuestoTrabajoDTO(crudo, carbon, factorAntracita));
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}

		return factoresClinker;
	}

	/**
	 * Calcula el consumo de crudo y de carbon de la lista de los componentes de
	 * clnker en un puesro de trabajo especifico
	 * 
	 * @param codigoLinea Long codigo de la linea de negocio
	 * @param anioContable Integer año
	 * @param numeroMes Short mes
	 * @param consumoPuestoTrab
	 * @param crudo
	 * @param carbon
	 */
	private Double[] calcularConsumoCarbonyCrudo(Long codigoPuesto, Long codigoLinea, Integer anioContable, Short numeroMes,
			List<Consumopuestotrabajo> consumoPuestoTrab) {
		Double crudo = 0d, carbon = 0d;
		String SIGLAS_CRUDO = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_CRUDO_SIGLAS).toLowerCase();
		for (Consumopuestotrabajo consumopuestotrabajo : consumoPuestoTrab) {
			Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();

			String siglas = componente.getSiglasProducto().toLowerCase();
			double montoAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponentePorPuestoTrabajo(codigoPuesto,
					componente.getPkCodigoProducto(), codigoLinea, numeroMes, anioContable);
			double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoPorPuestoTrabajo(
					codigoPuesto, componente.getPkCodigoProducto(), codigoLinea, numeroMes, anioContable);
			if (SIGLAS_CRUDO.indexOf(siglas) >= 0) {
				crudo += consumopuestotrabajo.getCantidadConsumopuestotrabajo() + montoAjuste + consumoPorAjusteProducto;
			} else {
				// TODO if (carbon) {}
				carbon += consumopuestotrabajo.getCantidadConsumopuestotrabajo() + montoAjuste + consumoPorAjusteProducto;
			}
		}
		return new Double[] { crudo, carbon };
	}

	private RegistroPuestoTrabajoProduccionDTO crearRegistroPuestoTrabajoProdDto(Long codigoPuestoTrabajo,
			String nombrePuestoTrab, Double produccionTonaledasAnterior, Double produccionHorasAnterior,
			Double produccionTonaledas, Double produccionHoras, Double produccionKcal, Double minimoRendimiento,
			Double maximoRendimiento, Double tasaProduccNominal, String nombreProducto, Double poderCalorificoPonderado,
			Long valorMax, Double CarProd, Double CarCalent, Double BunProd, Double BunCalent) {

		RegistroPuestoTrabajoProduccionDTO registro = new RegistroPuestoTrabajoProduccionDTO();

		registro.setCodigoPuestoTrabajo(codigoPuestoTrabajo);
		registro.setNombrePuestoTrabajo(nombrePuestoTrab);
		registro.setProduccionTM(produccionTonaledas);
		registro.setProduccionHR(produccionHoras);
		registro.setProduccionKCAL(produccionKcal);
		if (produccionTonaledas != null && produccionHoras != null) {
			registro.setProduccionTMPH(produccionHoras == 0D ? 0D : produccionTonaledas / produccionHoras);
		} else {
			registro.setProduccionTMPH(0d);
		}
		registro.setMesAnteriorTM(produccionTonaledasAnterior);
		registro.setMesAnteriorHR(produccionHorasAnterior);
		registro.setMinimoRendimiento(minimoRendimiento);
		registro.setMaximoRendimiento(maximoRendimiento);
		registro.setTasaProduccionNominal(tasaProduccNominal);
		registro.setPoderCalorificoPonderado(poderCalorificoPonderado);
		registro.setValorMax(valorMax);
		registro.setNombreProducto(nombreProducto);
		registro.setProduccionCarProd(CarProd);
		registro.setProduccionCarCalent(CarCalent);
		registro.setBunkerProduccion(BunProd);
		registro.setBunkerCalent(BunCalent);

		return registro;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerConceptosComponentesProducto(java.lang.Long, java.lang.Long,
	 * java.lang.Short, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<RegistroTablaConsumoComponentesDTO> obtenerConceptosComponentesProducto(Long codigoProducto, Long codigoLinea,
			Short mesContable, Integer anioContable, Date fechaMesInicial, Date fechaMesFinal, Set<String> otrosProductosAjuste)
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

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);
			long codigoProcesoCemento = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO));
			long codigoProcesoCrudo = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CRUDO));

			boolean prodEsClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

			boolean esMoliendaCemento = false, esMoliendaCrudo = false;

			if (!prodEsClinker) {
				Proceso proceso = HojaRutaQuerier.getProcesoSegunHojaRuta(codigoProducto, codigoLinea);
				if (proceso != null) {
					esMoliendaCemento = proceso.getPkCodigoProceso().longValue() == codigoProcesoCemento;
					esMoliendaCrudo = proceso.getPkCodigoProceso().longValue() == codigoProcesoCrudo;
				}
			}
			List<Object[]> consumoComponentes = AjusteProduccionMesQuerier.obtenerConceptosComponentesProducto(codigoProducto,
					codigoLinea, mesContable, anioContable);

			List<Object[]> otrosProductos = MovimientoAjusteQuerier
					.obtenerProductosAjustadosViaMovimientoAjusteProducto(otrosProductosAjuste);
			consumoComponentes.addAll(otrosProductos);

			List<RegistroTablaConsumoComponentesDTO> lista = new ArrayList<RegistroTablaConsumoComponentesDTO>();

			final int COL_CODIGO = 0, COL_NOMBRE = 1, COL_GRUPO = 3, COL_CODIGOPRODUCTOCOMP = 4;

			for (int i = 0; i < consumoComponentes.size(); i++) {
				Long codigoComponente = (Long) consumoComponentes.get(i)[COL_CODIGO];
				Long codigoProductoComponente = (Long) consumoComponentes.get(i)[COL_CODIGOPRODUCTOCOMP];
				String nombreProducto = (String) consumoComponentes.get(i)[COL_NOMBRE];
				RegistroTablaConsumoComponentesDTO registroDTO = new RegistroTablaConsumoComponentesDTO(nombreProducto);
				registroDTO.setCodigoComponente(codigoComponente);
				registroDTO.setCodigoProductoComponente(codigoProductoComponente);
				registroDTO.setNombreComponente(nombreProducto);
				registroDTO.setGrupoComponente((String) consumoComponentes.get(i)[COL_GRUPO]);

				Producto productoComponente = ProductoQuerier.getById(codigoProductoComponente);
				asignarOrden(esMoliendaCemento, registroDTO, productoComponente, esMoliendaCrudo);
				String codigoClinkerI = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODCUTO_CLK_I);
				boolean esClinkerI = codigoClinkerI.equals(productoComponente.getPkCodigoProducto().toString());
				Double[] stocksMensuales = obtenerStocksMensualesComponente(productoComponente, codigoLinea, mesContable,
						anioContable, esClinkerI);
				double stockFisico = obtenerStockFisico(productoComponente, codigoLinea, mesContable, anioContable,
						fechaMesInicial, fechaMesFinal, false);

				registroDTO.setFisico(stockFisico);
				registroDTO.setCodigoComponente(codigoComponente);
				registroDTO.setCodigoProductoComponente(codigoProductoComponente);

				registroDTO.setIngreso(stocksMensuales[1]);
				registroDTO.setAjusteLogistico(stocksMensuales[3]);
				
				Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorProductoLineaNegPeriodoContYGrupo(
						productoComponente.getPkCodigoProducto(), codigoLinea, mesContable, anioContable);
				if (ajusteProducto != null) {
					registroDTO.setAjusteProducto(ajusteProducto.getAjusteAjusteproducto());
				} else {
					registroDTO.setAjusteProducto(0d);
				}

				Double consumoParteDiario = stocksMensuales[2];

				double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteExceptuando(
						productoComponente.getPkCodigoProducto(), codigoLinea, mesContable, anioContable, codigoProducto);
				double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProductoExceptuando(
						productoComponente.getPkCodigoProducto(), codigoLinea, mesContable, anioContable, codigoProducto);
				Double saldoInicial = stocksMensuales[0];
				registroDTO.setSaldoInicial(saldoInicial);
				registroDTO.setConsumo(consumoParteDiario);
				registroDTO.setConsumoAjuste(consumoParteDiario + consumoPorAjuste + consumoPorAjusteProducto);

				lista.add(registroDTO);
			}
			ordenarListaCompCementos(esMoliendaCemento, lista, esMoliendaCrudo);

			return lista;
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

	/**
	 * Método para obtener las horas total ajustadas por puesto de trabajo
	 * producción
	 * 
	 * @param detallesProduccionPuestoTrabajo
	 * @param codigoLineaNegocio
	 * @param mesContable
	 * @param anioContable
	 * @return
	 * @throws LogicaException
	 */
	public Map<String, Double> obtenerDesviacionHoras(Long codigoProducto,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo, Long codigoLineaNegocio, Short mesContable,
			Integer anioContable) throws LogicaException {

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
		double desviacionHoras = 0;
		Map<String, Double> desviacionesPuestoTrabajo = new HashMap<String, Double>();
		try {
			for (RegistroPuestoTrabajoProduccionDTO detalle : detallesProduccionPuestoTrabajo) {

				desviacionHoras = 0;
				List<Puestotrabajoproduccion> producciones = AjusteProductoQuerier.obtenerHorasAjustePorLineaNegPeriodoCont(
						detalle.getCodigoPuestoTrabajo(), codigoLineaNegocio, mesContable, anioContable);
				double horasajustadas = 0;

				// Se suman todas las horas ajustadas del puesto hasta el
				// momento
				for (Puestotrabajoproduccion puestotrabajoproduccion : producciones) {
					if (puestotrabajoproduccion.getAjusteproducto().getProducto().getPkCodigoProducto().longValue() != codigoProducto
							.longValue()) {
						horasajustadas += puestotrabajoproduccion.getHrAjustePuestotrabajoproducci();
					}
				}
				desviacionHoras = detalle.getAjusteHR().doubleValue() + horasajustadas;
				if (desviacionHoras != 0)
					desviacionesPuestoTrabajo.put(detalle.getNombrePuestoTrabajo(), desviacionHoras);
			}

		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (Exception e) {
			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return desviacionesPuestoTrabajo;
	}

	public void asignarOrden(boolean esMoliendaCemento, OrdenMolCementoDto registroDTO, Producto productoComponente,
			boolean esMoliendaCrudo) {

		long codProdcuto = productoComponente.getPkCodigoProducto().longValue();
		if (esMoliendaCemento) {
			if (codProdcuto == codigoClinker) {
				registroDTO.setOrden(0);
			}

			if (codProdcuto == codigoClinker5) {
				registroDTO.setOrden(1);
			}

			if (codProdcuto == codigoCaliza1Fbr) {
				registroDTO.setOrden(2);
			}

			if (codProdcuto == codigoCalizaCalFbr) {
				registroDTO.setOrden(3);
			}

			if (codProdcuto == codigoYeso) {
				registroDTO.setOrden(4);
			}
		} else if (esMoliendaCrudo) {

			if (codProdcuto == codigoCaliza1Fbr || codProdcuto == codigoClzCanteras || codProdcuto == codigoClzTolvas
					|| codProdcuto == codigoClzTriturac || codProdcuto == codigoClzAdicMol || codProdcuto == codigoClzIExp
					|| codProdcuto == codigoClzIPrep) {
				registroDTO.setOrden(0);
			}

			if (codProdcuto == codigoArcChancSecund || codProdcuto == codigoArcChungal || codProdcuto == codigoArcPituras
					|| codProdcuto == codigoArcRioja) {
				registroDTO.setOrden(1);
			}

			if (codProdcuto == codigoFeAltaLey || codProdcuto == codigoFeBajaLey) {
				registroDTO.setOrden(2);
			}

			if (codProdcuto == codigoArena) {
				registroDTO.setOrden(3);
			}

			if (codProdcuto == codigoFluorita) {
				registroDTO.setOrden(4);
			}

			if (codProdcuto == codigoCarbAntrc41 || codProdcuto == codigoCarbAntrc01 || codProdcuto == codigoCarbAntrc02) {
				registroDTO.setOrden(5);
			}

			if (codProdcuto == codigoPecoke) {
				registroDTO.setOrden(6);
			}
		}
	}

	public void ordenarListaCompCementos(final boolean esMoliendaCemento, List<? extends OrdenMolCementoDto> lista,
			final boolean esMoliendaCrudo) {

		Collections.sort(lista, new Comparator<OrdenMolCementoDto>() {
			public int compare(OrdenMolCementoDto o1, OrdenMolCementoDto o2) {
				if (esMoliendaCemento || esMoliendaCrudo) {
					return o1.getOrden().compareTo(o2.getOrden());
				}
				return o1.getNombreComponenteOrden().compareTo(o2.getNombreComponenteOrden());
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerStocksMensualesComponente
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto, java.lang.Long,
	 * java.lang.Short, java.lang.Integer)
	 */
	public Double[] obtenerStocksMensualesComponente(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException {

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

			return obtenerStocksMensualesComponenteDAO(componente, codigoLinea, mes, anio, esClinkerI);
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerStocksMensualesComponente
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto, java.lang.Long,
	 * java.lang.Short, java.lang.Integer)
	 */
	public Double[] obtenerStocksMensualesComponenteDAO(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException {

		String mensajeError = "";

		Double[] arrayStocksDoubles = null;
		try {

			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(componente);

			Object[] arrayStocks = null;
			if (esMateriaPrima) {
				arrayStocks = ProduccionDiariaQuerier.obtenerStocksMensualesPorProducto(componente.getPkCodigoProducto(),
						codigoLinea, mes, anio);
			} else {
				arrayStocks = ProduccionDiariaQuerier.obtenerStocksMensualesPorOrdenProd(componente.getPkCodigoProducto(),
						codigoLinea, mes, anio);
			}

			Double saldoInicial = arrayStocks[0] == null ? 0d : (Double) arrayStocks[0];
			Double ingreso = arrayStocks[1] == null ? 0d : (Double) arrayStocks[1];
			Double consumo = arrayStocks[2] == null ? 0d : (Double) arrayStocks[2];
			Double ajusteLogistico = arrayStocks[3] == null ? 0d : (Double) arrayStocks[3];

			if (esClinkerI) {
				// Esto porq en el query se suma el consumo de clinker en
				// proceso HH y HV y son el mismo ya que el consumo es por
				// producto es independiente del proceso
				consumo /= 2;
				saldoInicial = ProduccionDiariaQuerier.obtenerSaldoInicialClinker(componente.getPkCodigoProducto(), codigoLinea,
						mes, anio);
				ajusteLogistico /=2;
			}

			arrayStocksDoubles = new Double[] { saldoInicial, ingreso, consumo , ajusteLogistico};

		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
		return arrayStocksDoubles;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerStockFisico
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto, java.lang.Long,
	 * java.lang.Short, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public double obtenerStockFisico(Producto componente, Long codigoLinea, Short numeroMes, Integer anio, Date fechaMesInicial,
			Date fechaMesFinal, Boolean estadoInicial) throws LogicaException {

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

			return obtenerStockFisicoDAO(componente, codigoLinea, numeroMes, anio, fechaMesInicial, fechaMesFinal, estadoInicial);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public double obtenerStockFisicoDAO(Producto componente, Long codigoLinea, Short numeroMes, Integer anio,
			Date fechaMesInicial, Date fechaMesFinal, Boolean estadoInicial) throws LogicaException {

		String mensajeError = "";

		double obtenerStockFisicoCubicado = 0d;
		try {

			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaMesFinal);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			Integer mesAsInt = cal.get(Calendar.MONTH) + 1;
			Date fechaDiaSig = cal.getTime();
			int anioCalendar = cal.get(Calendar.YEAR);

			double stockFisicoMedicion = MedicionQuerier.obtenerFisico(codigoLinea, componente.getPkCodigoProducto(),
					anioCalendar, mesAsInt.shortValue(), fechaDiaSig);
			if (stockFisicoMedicion != 0d) {
				return stockFisicoMedicion;
			}

			String estadoCubicacionProducto = null;
			if (!estadoInicial) {
				estadoCubicacionProducto = ManejadorPropiedades
						.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);
			}
			obtenerStockFisicoCubicado = obtenerStockFisicoProductoCubicadoFinMes(componente.getPkCodigoProducto(), numeroMes,
					anio, estadoCubicacionProducto, codigoLinea);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
		return obtenerStockFisicoCubicado;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerConsumoComponentesPuestoTrabajo(java.lang.Long, java.lang.Long,
	 * java.lang.Short, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> obtenerConsumoComponentesPuestoTrabajo(Long codigoProducto,
			Long codigoLinea, Short mesContable, Integer anioContable, Date fechaMesInicial, Date fechaMesFinal)
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

			Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> conceptosComponentes = new HashMap<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>();
			List<Object[]> datos = AjusteProduccionMesQuerier.obtenerConsumoComponentePuestoTrabajoMes(codigoProducto,
					codigoLinea, mesContable, anioContable, fechaMesInicial, fechaMesFinal);

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);

			boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

			boolean esMoliendaCemento = false, esMoliendaCrudo = false;

			if (!esClinker) {
				Proceso proceso = null;

				proceso = HojaRutaQuerier.getProcesoSegunHojaRuta(codigoProducto, codigoLinea);
				if (proceso != null) {
					long codigoProcesoCemento = new Long(
							ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO));
					esMoliendaCemento = proceso.getPkCodigoProceso().longValue() == codigoProcesoCemento;

					long codigoProcesoCrudo = new Long(
							ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CRUDO));
					esMoliendaCrudo = proceso.getPkCodigoProceso().longValue() == codigoProcesoCrudo;
				}
			}

			Map<Long, Factordosificacionregistromensu> mapaIndicesProduccion = new HashMap<Long, Factordosificacionregistromensu>();

			for (int i = 0; i < datos.size(); i++) {
				Long codigoComponente = (Long) datos.get(i)[2];
				Factordosificacionregistromensu factordosificacion = mapaIndicesProduccion.get(codigoComponente);
				if (factordosificacion == null) {
					factordosificacion = obtenerIndiceProduccion(codigoProducto, anioContable, mesContable, codigoComponente);
					if (factordosificacion != null) {
						mapaIndicesProduccion.put(codigoComponente, factordosificacion);
					}
				}

				double indice = factordosificacion == null ? 0d : factordosificacion.getCantidadFactordosificacionregi();
				Long codigoPuestoTrab = (Long) datos.get(i)[0];

				final int COL_NOMBREPUESTO = 1, COL_NOMBRECOMPONENTE = 3, COL_MONTOCONSUMIDO = 4, COL_GRUPOCOMPONENTE = 5, COL_CODPRODUCTOCOMPONENTE = 6;

				RegistroTablaConsumosPuestoTrabajoDTO registroDTO = new RegistroTablaConsumosPuestoTrabajoDTO(
						(String) datos.get(i)[3]);
				registroDTO.setCodigoPuestoTrabajo(codigoPuestoTrab);
				String nombrePuestoTrabajo = (String) datos.get(i)[COL_NOMBREPUESTO];
				registroDTO.setNombrePuestoTrabajo(nombrePuestoTrabajo);
				registroDTO.setCodigoComponente(codigoComponente);
				registroDTO.setCodigoProductoComponente((Long) datos.get(i)[COL_CODPRODUCTOCOMPONENTE]);
				registroDTO.setNombreComponente((String) datos.get(i)[COL_NOMBRECOMPONENTE]);
				registroDTO.setGrupoComponente((String) datos.get(i)[COL_GRUPOCOMPONENTE]);
				registroDTO.setMontoConsumido((Double) datos.get(i)[COL_MONTOCONSUMIDO]);
				registroDTO.setProduccionRealTM((Double) datos.get(i)[COL_MONTOCONSUMIDO]);
				registroDTO.setDosificacion(indice * 100d);

				Producto productoComponente = ComponenteQuerier.getById(codigoComponente)
						.getProductoByFkCodigoProductoComponente();

				asignarOrden(esMoliendaCemento, registroDTO, productoComponente, esMoliendaCrudo);

				List<RegistroTablaConsumosPuestoTrabajoDTO> consumos = conceptosComponentes.get(nombrePuestoTrabajo);

				if (consumos == null) {
					consumos = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
					consumos.add(registroDTO);
					conceptosComponentes.put(nombrePuestoTrabajo, consumos);
				} else {
					consumos.add(registroDTO);
				}

			}

			Set<String> claves = conceptosComponentes.keySet();
			for (String clave : claves) {
				List<RegistroTablaConsumosPuestoTrabajoDTO> lista = conceptosComponentes.get(clave);
				ordenarListaCompCementos(esMoliendaCemento, lista, esMoliendaCrudo);
			}

			return conceptosComponentes;

		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	private Factordosificacionregistromensu obtenerIndiceProduccion(Long codigoProducto, Integer anioContable, Short numeroMes,
			Long codigoComponente) throws LogicaException {
		Factordosificacionregistromensu indiceProduccion;
		try {
			indiceProduccion = FactorDosificacionRegistroMensualQuerier.obtenerFactorSegunProductoCompoeneteYMes(codigoProducto,
					codigoComponente, numeroMes, anioContable);
			return indiceProduccion;
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerStockFisicoProductoCubicado(java.lang.Long, java.lang.Short,
	 * java.lang.Integer, java.lang.String)
	 */
	private double obtenerStockFisicoProductoCubicadoFinMes(Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto, Long codigoLinea) throws LogicaException {
		// 297_47637

		double stockFisico = CubicacionProductoQuerier.obtenerStockFisicoProductoCubicado(codigoProducto, mesContable,
				anioContable, estadoCubicacionProducto);
		if (stockFisico == 0d) {
			logger.error("No  existe cubicaciones para el mes: " + mesContable + " del producto: " + codigoProducto);
			return 0d;
		}

		Date fechaCubicacion = AjusteProduccionMesQuerier.obtenerFechaCubicacionProducto(codigoProducto, codigoLinea,
				mesContable, anioContable, estadoCubicacionProducto);
		if (fechaCubicacion == null) {
			logger.error("No  existe cubicaciones para el mes: " + mesContable + " del producto: " + codigoProducto);
			return 0D;
		}

		Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
		Long codigoPT = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_TERMINADO));
		Producto producto;
		try {
			producto = ProductoQuerier.getById(codigoProducto);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
		double stockCubicadoSeco = verificarHumedaadCubicacion(producto, fechaCubicacion, stockFisico);
		Long codigoTipoproducto = producto.getTipoproducto().getPkCodigoTipoproducto();

		boolean esProductoProceso = codigoTipoproducto.longValue() == codigoPP.longValue();
		boolean esProductoTerminado = codigoTipoproducto.longValue() == codigoPT.longValue();
		boolean excepcionproducto = validarExcepcionProducto(producto.getPkCodigoProducto());
		boolean poseeCompEnHojaRuta = HojaRutaComponenteQuerier.poseeCompEnHojaRuta(producto.getPkCodigoProducto());

		boolean esMP = !(esProductoProceso && poseeCompEnHojaRuta);

		if (excepcionproducto && esProductoTerminado) {
			esMP = !(esProductoTerminado && poseeCompEnHojaRuta);
			;
		}
		Double[] ingresosConsumosPostCubicacion = AjusteProduccionMesQuerier.obtenerIngresosConsumosPostCubicacion(
				codigoProducto, codigoLinea, mesContable, anioContable, fechaCubicacion, esMP);
		Double ingresosPostCubicacion = ingresosConsumosPostCubicacion[0];

		Double consumosPostCubicacion = ingresosConsumosPostCubicacion[1];

		String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER_CUBICACION);
		boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

		if (esClinker) {
			// Ya que aqui esatria sumando los consumos de kardex de hh y hv que
			// son iguales
			consumosPostCubicacion = consumosPostCubicacion / 2;
		}

		double cubicacionFinMes = stockCubicadoSeco + ingresosPostCubicacion - consumosPostCubicacion;

		return cubicacionFinMes;
	}

	private Boolean validarExcepcionProducto(Long codigoProducto) {

		ArrayList<Long> productoExcepciones = new ArrayList<Long>();
		ParametroSistema parametro = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.PRODUCTOS_EXCEPCIONES_CUBICAJE);

		if (parametro != null && parametro.getValorParametro() != null) {
			String[] productos = parametro.getValorParametro().split(",");
			for (String cadenaProductos : productos) {
				productoExcepciones.add(Long.valueOf(cadenaProductos));
			}

			for (Long codigoProductoEx : productoExcepciones) {
				if (codigoProducto.compareTo(codigoProductoEx) == 0) {
					return true;
				}
			}
		}

		return false;

	}

	private double verificarHumedaadCubicacion(Producto producto, Date fechaCubicacion, Double stockTmHum) throws LogicaException {
		double factorHum = 0d;
		int anno = FechaUtil.obtenerAnnioFecha(fechaCubicacion);
		int mes = FechaUtil.obtenerMesFecha(fechaCubicacion);
		Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes((short) (mes - 1), anno);
		fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
		factorHum = DownloadServlet.obtenerHumedad(producto, fechaMesCalendar.getTime());

		double stockSeco = stockTmHum * (1 - (factorHum / 100d));

		return stockSeco;
	}

	public double obtenerFactorHumPonderado(Producto producto, Date fechaCubicacion) throws LogicaException {

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

			return obtenerFactorHumPonderadoDAO(producto, fechaCubicacion);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public double obtenerFactorHumPonderadoDAO(Producto producto, Date fechaCubicacion) throws LogicaException {

		String mensajeError = "";

		Long codigoProductoScc = producto.getCodigoSccProducto();

		if (codigoProductoScc == null) {
			// no tien codigo producto scc == no tiene humedad
			return 0;
		}
		double factor = 0;
		try {

			Calendar calendarFechafinal = Calendar.getInstance();
			calendarFechafinal.setTime(fechaCubicacion);
			calendarFechafinal.add(Calendar.DAY_OF_MONTH, 1);

			Calendar calendarFechaInicial = Calendar.getInstance();
			calendarFechaInicial.setTime(fechaCubicacion);
			calendarFechaInicial.set(Calendar.DAY_OF_MONTH, 1);

			List<Object[]> suma = TablaKardexQuerier.obtenerIngresoProductoToFecha(producto, calendarFechaInicial.getTime(),
					calendarFechafinal.getTime());
			factor = obtenerFactor(suma);

		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}

		return factor;
	}

	public double obtenerCantidadMensualKardex(Producto producto, Date fecha) throws LogicaException {

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

			return obtenerCantidadMensualKardexDAO(producto, fecha);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public double obtenerCantidadMensualKardexDAO(Producto producto, Date fecha) throws LogicaException {

		String mensajeError = "";

		Double suma = 0d;
		try {

			Date calendarFechafinal = FechaUtil.obtenerFechaDiaSiguiente(FechaUtil.getUltimoDiaMes(
					(short) (FechaUtil.obtenerMesFecha(fecha) - 1), FechaUtil.obtenerAnnioFecha(fecha)).getTime());
			List<Object[]> kardexs = TablaKardexQuerier.obtenerIngresoProductoToFecha(producto, fecha, calendarFechafinal);

			for (Object[] objects : kardexs) {
				suma += (objects[1] != null) ? (Double) objects[1] : 0d;
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}

		return suma;
	}

	public double obtenerFactorHumPonderadoConsumo(Producto producto, Date fechaCubicacion) throws LogicaException {

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

			return obtenerFactorHumPonderadoConsumoDAO(producto, fechaCubicacion);
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public double obtenerFactorHumPonderadoConsumoDAO(Producto producto, Date fechaCubicacion) throws LogicaException {

		String mensajeError = "";

		Long codigoProductoScc = producto.getCodigoSccProducto();

		if (codigoProductoScc == null) {
			// no tien codigo producto scc == no tiene humedad
			return 0;
		}
		double factor = 0;
		try {

			Calendar calendarFechafinal = Calendar.getInstance();
			calendarFechafinal.setTime(fechaCubicacion);
			calendarFechafinal.add(Calendar.DAY_OF_MONTH, 1);

			Calendar calendarFechaInicial = Calendar.getInstance();
			calendarFechaInicial.setTime(fechaCubicacion);
			calendarFechaInicial.set(Calendar.DAY_OF_MONTH, 1);

			List<Object[]> suma = TablaKardexQuerier.obtenerConsumoProductoToFecha(producto, calendarFechaInicial.getTime(),
					calendarFechafinal.getTime());

			factor = obtenerFactor(suma);

		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
		return factor;
	}

	private double obtenerFactor(List<Object[]> suma) {
		Double ponderado = 0d;
		try {

			Double sumaHumeda = 0d;
			Double sumaHumedaPorFactor = 0d;

			for (Object[] objects : suma) {
				Double ingresoHumedadTablakardex = (objects[1] != null) ? (Double) objects[1] : 0d;
				Double ingresoTablakardex = (objects[0] != null) ? (Double) objects[0] : 0d;
				if (ingresoTablakardex != 0d && ingresoHumedadTablakardex != 0d) {

					double factorHumIngreso = (1 - (ingresoTablakardex / ingresoHumedadTablakardex)) * 100;
					sumaHumeda += ingresoHumedadTablakardex;
					sumaHumedaPorFactor += (ingresoHumedadTablakardex * factorHumIngreso);
				}
			}
			ponderado = sumaHumedaPorFactor / sumaHumeda;
			ponderado = NumberUtil.Redondear2Decimales(ponderado);
		} catch (Exception e) {
			logger.error("error " + e.getMessage());
		}
		return ponderado;
	}

	/**
	 * Método para calcular el valor ajustar cuando aplique de acuerdo a la
	 * regla del 5%
	 * 
	 * @param codigoLinea
	 * @param codigoProduccion
	 * @param mesContable
	 * @param anioContable
	 * @param estadoCubicacionProducto
	 * @param tipoAlmacenamiento
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws ElementoNoEncontradoException
	 * @throws SesionVencidaException
	 * @throws LogicaException
	 * @throws ElementoNoEncontradoException
	 */
	private double obtenerAjusteMedidaCubicacion(Long codigoLinea, Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto) throws SesionVencidaException, EntornoEjecucionException, LogicaException,
			ElementoNoEncontradoException {

		double stockFisico = obtenerStockFisicoProductoCubicadoFinMes(codigoProducto, mesContable, anioContable,
				estadoCubicacionProducto, codigoLinea);

		double stockLibro = obtenerStockLibros(codigoProducto, codigoLinea, mesContable, anioContable);

		// 3. Se calcula la diferencia entre stock en libro y fisico
		double valorAjuste = stockFisico - stockLibro;

		return valorAjuste;
	}

	/**
	 * Método para calcular el valor ajustar cuando aplique por medicion diaria
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @param tipoMedioAlmacenamiento
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	private Double obtenerAjustePorMedicionDiaria(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable,
			Date fechaMesFinal) throws SesionVencidaException, EntornoEjecucionException, ElementoNoEncontradoException {
		double valorAjuste = 0;
		double stockFisico = 0;
		double stockLibro = 0;

		List<Tablakardex> medicionDiaria = AjusteProduccionMesQuerier.obtenerKardexMes(codigoProducto, codigoLinea, mesContable,
				anioContable);

		if (medicionDiaria != null && medicionDiaria.size() > 0) {
			Tablakardex kardex = medicionDiaria.get(0);
			stockLibro = kardex.getStockFinalTablakardex() != null ? kardex.getStockFinalTablakardex() : 0d;
			if (kardex.getStockFisicoTablakardex() != null && kardex.getStockFisicoTablakardex().doubleValue() != 0d) {
				stockFisico = kardex.getStockFisicoTablakardex();
				valorAjuste = (stockFisico - stockLibro);
				return valorAjuste;
			}
		}
		return null;
	}

	private double obtenerStockLibros(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable)
			throws SesionVencidaException, EntornoEjecucionException, ElementoNoEncontradoException {
		List<Tablakardex> kardexs = AjusteProduccionMesQuerier.obtenerKardexMes(codigoProducto, codigoLinea, mesContable,
				anioContable);

		double stockLibro = 0d;
		if (kardexs != null && kardexs.size() > 0) {

			String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER_CUBICACION);
			boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

			if (esClinker) {
				// esto debido a que clinker tiene dos procesos y por lo tanto
				// tiene dos kardex y hay que considerar ambos ingresos
				Tablakardex kardex1 = kardexs.get(0);
				Tablakardex kardex2 = kardexs.get(1);

				double ingreso = kardex1.getProducciondiaria().getIngresoProduccionProducciondi()
						+ kardex2.getProducciondiaria().getIngresoProduccionProducciondi();
				double consumo = 0d;

				double saldoInicial = 0d;

				long codigoProcesoClkHH = new Long(
						ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HH));

				if (kardex1.getProducciondiaria().getOrdenproduccion().getProduccion().getProceso().getPkCodigoProceso()
						.longValue() == codigoProcesoClkHH) {
					saldoInicial = kardex1.getProducciondiaria().getSaldoInicialProducciondiaria();
					consumo = kardex1.getProducciondiaria().getConsumoProducciondiaria();
				} else {
					saldoInicial = kardex2.getProducciondiaria().getSaldoInicialProducciondiaria();
					consumo = kardex2.getProducciondiaria().getConsumoProducciondiaria();
				}

				stockLibro = saldoInicial + ingreso - consumo;
			} else {
				Tablakardex kardex = kardexs.get(0);
				stockLibro = kardex.getStockFinalTablakardex();
			}

		}
		return stockLibro;
	}

	/**
	 * Método para insertar todos los ajustes de un producto
	 * 
	 * @param codigoLineaNegocio
	 * @param anio
	 * @param mes
	 * @param codigoUsuarioAprueba
	 * @param codigoUsuarioAjusta
	 * @param codigoPlantillaGrupoAjuste
	 * @param codigoOrdenProduccion
	 * @param saldoInicialLibroBalance
	 * @param produccionLibroBalance
	 * @param saldoFinalLibroBalance
	 * @param consumoLibroBalance
	 * @param consumoAjusteBalance
	 * @param produccionAjusteBalance
	 * @param detallesProduccionPuestoTrabajo
	 * @param detallesConsumoComponenteAjuste
	 * @param detallesMovimientoAjuste
	 * @throws LogicaException
	 * @throws LogicaException
	 */
	public void insertarAjusteProducto(Double ajuste, Long codigoLineaNegocio, Integer anio, Short mes, Long codigoUsuarioAjusta,
			Long codigoUsuarioAprueba, Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance,
			double produccionLibroBalance, double saldoFinalLibroBalance, double consumoLibroBalance,
			double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String observaciones) throws LogicaException {

		Boolean parteCerrado = this.verificarParteDiarioCerrado(codigoLineaNegocio, FechaUtil.numeroMesANombreMes(mes), anio);

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		Usuario usuarioAjusta = null;
		String login = "";
		String nombre = "";
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Usuario usuarioAprueba = null;

			if (codigoUsuarioAprueba != null) {
				usuarioAprueba = UsuarioQuerier.getById(codigoUsuarioAprueba);
			}

			usuarioAjusta = UsuarioQuerier.getById(codigoUsuarioAjusta);
			login = usuarioAjusta.getLoginUsuario();
			nombre = usuarioAjusta.getPersona().getApellidoPersona() + " , " + usuarioAjusta.getPersona().getNombrePersona();
			Ajusteproduccion ajusteProduccion = AjusteProduccionQuerier.getByLineaNegocioMesYAno(mes, anio, codigoLineaNegocio);

			Lineanegocio lineaNegocio = LineaNegocioQuerier.getById(codigoLineaNegocio);
			Periodocontable periodoContable = PeriodoContableQuerier.getByMesYAno(mes, anio);
			if (ajusteProduccion == null) {
				Estadoajusteproduccion estadoAjusteProduccion = null;

				if (!parteCerrado) {
					estadoAjusteProduccion = EstadoAjusteProduccionQuerier.findByName(ManejadorPropiedades
							.obtenerPropiedadPorClave(ETIQUETA_ESTADO_INICIAL));

				} else {

					estadoAjusteProduccion = EstadoAjusteProduccionQuerier.findByName(ManejadorPropiedades
							.obtenerPropiedadPorClave(ETIQUETA_ESTADO_GENERADO));
				}

				ajusteProduccion = new Ajusteproduccion();
				ajusteProduccion.setLineanegocio(lineaNegocio);
				ajusteProduccion.setPeriodocontable(periodoContable);
				ajusteProduccion.setUsuarioByFkCodigoUsuarioRegistra(usuarioAjusta);
				ajusteProduccion.setEstadoajusteproduccion(estadoAjusteProduccion);
				ajusteProduccion.setCombutibleEnviadoSap(false);
				ajusteProduccion.setConsumoEnviadoSap(false);

				AjusteProduccionQuerier.save(ajusteProduccion);
			}

			// 2. Insertar producto del grupo
			Plantillagrupoajuste plantillagrupoajuste = new Plantillagrupoajuste();
			plantillagrupoajuste.setPkCodigoPlantillagrupoajuste(codigoPlantillaGrupoAjuste);

			Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorProductoLineaNegPeriodoContYGrupo(codigoProducto,
					codigoLineaNegocio, mes, anio);

			if (ajusteProducto != null) {
				AjusteProductoQuerier.delete(ajusteProducto);
			}

			ajusteProducto = new Ajusteproducto();
			Estadoajusteproducto estadoAjusteProducto = null;
			if (!parteCerrado) {
				estadoAjusteProducto = EstadoAjusteProductoQuerier.findByName(ManejadorPropiedades
						.obtenerPropiedadPorClave(NOMBRE_ESTADO_AJUSTE_PRODUCTO_INICIAL));
			} else {
				estadoAjusteProducto = EstadoAjusteProductoQuerier.findByName(ManejadorPropiedades
						.obtenerPropiedadPorClave(NOMBRE_ESTADO_AJUSTE_PRODUCTO_GENERADO));
			}
			Producto producto = new Producto();
			producto.setPkCodigoProducto(codigoProducto);

			ajusteProducto.setAjusteproduccion(ajusteProduccion);
			ajusteProducto.setPlantillagrupoajuste(plantillagrupoajuste);
			ajusteProducto.setEstadoajusteproducto(estadoAjusteProducto);
			ajusteProducto.setProducto(producto);
			ajusteProducto.setUsuarioByFkCodigoUsuarioAjusta(usuarioAjusta);
			ajusteProducto.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprueba);
			ajusteProducto.setAjusteAjusteproducto(ajuste);
			ajusteProducto.setObservacionAjusteproducto(observaciones);

			AjusteProductoQuerier.save(ajusteProducto);

			Map<Long, Long> codigosBunker = obteniendoCodigosBunker(codigoProducto, codigoLineaNegocio, mes, anio);

			insertarTablaBalance(ajusteProducto, saldoInicialLibroBalance, produccionLibroBalance, saldoFinalLibroBalance,
					consumoLibroBalance, consumoAjusteBalance, produccionAjusteBalance);
			Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion = insertarPuestoTrabajoProduccion(ajusteProducto,
					detallesProduccionPuestoTrabajo);

			insertarConsumoComponentesAjuste(detallesConsumoComponenteAjuste, detallesMovimientoAjuste,
					mapaPuestoTrabajoProduccion, ajusteProducto);
			insertarConsumoCombustibles(mapaPuestoTrabajoProduccion, detallesConsumoComponenteAjuste, codigoProducto,
					codigosBunker);

			tx.commit();
		} catch (ParametroInvalidoException e) {
			e.printStackTrace();
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			e.printStackTrace();
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = e.getMensaje();
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}


	}

	private Map<Long, Long> obteniendoCodigosBunker(Long codigoProducto, Long codigoLineaNegocio, Short mes, Integer anio) {
		Map<Long, Long> mapaCodigos = new HashMap<Long, Long>();
		List<Object[]> pkComponentesPorPT = ComponenteQuerier.obtenerCodigoComponentesParaBunker(codigoProducto,
				codigoLineaNegocio, mes, anio);

		for (int i = 0; i < pkComponentesPorPT.size(); i++) {
			Object[] codigosBunker = pkComponentesPorPT.get(i);
			mapaCodigos.put(Long.valueOf(codigosBunker[0].toString()), Long.valueOf(codigosBunker[1].toString()));
		}

		return mapaCodigos;
	}

	private void insertarConsumoCombustibles(Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste, Long codigoProducto,
			Map<Long, Long> codigosBunker) {

		try {
			Double constante = 100d;
			Double porcentajeCarbon;
			if (validarProductoRendimentoTermico(codigoProducto)) {
				for (int i = 0; i < detallesConsumoComponenteAjuste.size(); i++) {
					RegistroTablaConsumosPuestoTrabajoDTO consumoCompAjusteDto = detallesConsumoComponenteAjuste.get(i);
					Long codigoComponente = consumoCompAjusteDto.getCodigoComponente();
					Componente componente = ComponenteQuerier.getById(codigoComponente);

					Long codigoPuestoTrabajo = consumoCompAjusteDto.getCodigoPuestoTrabajo();
					Puestotrabajoproduccion puestoTrabajoProduccion = mapaPuestoTrabajoProduccion.get(codigoPuestoTrabajo);

					if (validarPuestoTrabajoRendimientoTermico(codigoPuestoTrabajo)) {
						if (componente.getProductoByFkCodigoProductoComponente().getGrupoProducto() != null
								&& componente.getProductoByFkCodigoProductoComponente().getGrupoProducto()
										.compareTo(GRUPO_PRODUCTO) == 0) {

							porcentajeCarbon = consumoCompAjusteDto.getPorcetanjeCarbones();

							Consumocombustibles consumoCombustibles = new Consumocombustibles();
							consumoCombustibles.setPuestotrabajoproduccion(puestoTrabajoProduccion);
							consumoCombustibles.setComponente(componente);

							if (porcentajeCarbon != null && porcentajeCarbon == 0d
									&& consumoCompAjusteDto.getMontoConsumido() > 0d) {
								porcentajeCarbon = 100d;
							}

							Double porcCarProd = NumberUtil.dividir(
									(puestoTrabajoProduccion.getCarprodPuestotrabajoproduccion() * porcentajeCarbon), constante);
							Double porcCarCalent = NumberUtil
									.dividir((puestoTrabajoProduccion.getCarcalentPuestotrabajoproduccion() * porcentajeCarbon),
											constante);
							Double porcCarProdReal = NumberUtil.dividir(
									(puestoTrabajoProduccion.getCarprodRealPuestotrabajoproduccion() * porcentajeCarbon),
									constante);
							Double porcCarCalentReal = NumberUtil.dividir(
									(puestoTrabajoProduccion.getCarcalentRealPuestotrabajoproduccion() * porcentajeCarbon),
									constante);

							logger.info("puestoTrabajoProduccion.getCarprodPuestotrabajoproduccion()_"
									+ puestoTrabajoProduccion.getCarprodPuestotrabajoproduccion());
							logger.info("puestoTrabajoProduccion.getCarcalentPuestotrabajoproduccion()_"
									+ puestoTrabajoProduccion.getCarcalentPuestotrabajoproduccion());
							logger.info("puestoTrabajoProduccion.getCarprodRealPuestotrabajoproduccion()_"
									+ puestoTrabajoProduccion.getCarprodRealPuestotrabajoproduccion());
							logger.info("puestoTrabajoProduccion.getCarcalentRealPuestotrabajoproduccion()_"
									+ puestoTrabajoProduccion.getCarcalentRealPuestotrabajoproduccion());

							logger.info("constante " + constante);
							logger.info("porcentajeCarbon " + porcentajeCarbon);
							logger.info("porcCarProd_" + porcCarProd);
							logger.info("porcCarCalent_" + porcCarCalent);
							logger.info("porcCarProdReal_" + porcCarProdReal);
							logger.info("porcCarCalentReal_" + porcCarCalentReal);

							consumoCombustibles.setCombustibleprodConsumocombustibles(porcCarProd);
							consumoCombustibles.setCombustiblecalentConsumocombustibles(porcCarCalent);
							consumoCombustibles.setCombustibleprodRealConsumocombustibles(porcCarProdReal);
							consumoCombustibles.setCombustiblecalentRealConsumocombustibles(porcCarCalentReal);

							ConsumoCombustiblesQuerier.save(consumoCombustibles);
						}
					}
				}
				insertarConsumoCombustiblesBunker(mapaPuestoTrabajoProduccion, codigosBunker, codigoProducto);
			}
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			e.printStackTrace();
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			e.printStackTrace();
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			e.printStackTrace();
		}
	}

	private boolean validarPuestoTrabajoRendimientoTermico(Long codigoPuestoTrabajo) {
		List<Long> puestoTrabajoRendimientoTer;
		Boolean isRendimiento = Boolean.FALSE;
		try {
			puestoTrabajoRendimientoTer = rendimientoTermicoLogic.obtenerCodigosPuestoTrabajoRendimientosTermicos();
			for (Long codigoPTRendimiento : puestoTrabajoRendimientoTer) {
				if (codigoPuestoTrabajo.compareTo(codigoPTRendimiento) == 0) {
					isRendimiento = Boolean.TRUE;
					break;
				}
			}
		} catch (LogicaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
		}

		return isRendimiento;
	}

	private void insertarConsumoCombustiblesBunker(Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion,
			Map<Long, Long> codigosBunker, Long codigoProducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Set<Long> puestoT = codigosBunker.keySet();

		for (Long pt : puestoT) {
			Puestotrabajoproduccion puestoTrabajoProduccion = mapaPuestoTrabajoProduccion.get(pt);

			if (puestoTrabajoProduccion != null) {
				Long codigoBunker = codigosBunker.get(pt);

				if (codigosBunker != null) {
					Componente componenteBunker = ComponenteQuerier.getById(codigoBunker);

					if (componenteBunker != null) {
						if (validarProductoRendimentoTermico(codigoProducto)
								&& validarPuestoTrabajoRendimientoTermico(puestoTrabajoProduccion.getPuestotrabajo()
										.getPkCodigoPuestotrabajo())) {

							Consumocombustibles consumoCombustibles = new Consumocombustibles();

							consumoCombustibles.setPuestotrabajoproduccion(puestoTrabajoProduccion);
							consumoCombustibles.setComponente(componenteBunker);

							consumoCombustibles.setCombustibleprodConsumocombustibles(puestoTrabajoProduccion
									.getBunkprodPuestotrabajoproduccion());
							consumoCombustibles.setCombustiblecalentConsumocombustibles(puestoTrabajoProduccion
									.getBunkcalentPuestotrabajoproduccion());
							consumoCombustibles.setCombustibleprodRealConsumocombustibles(puestoTrabajoProduccion
									.getBunkprodRealPuestotrabajoproduccion());
							consumoCombustibles.setCombustiblecalentRealConsumocombustibles(puestoTrabajoProduccion
									.getBunkcalentRealPuestotrabajoproduccion());

							ConsumoCombustiblesQuerier.save(consumoCombustibles);
						}
					}
				}
			}
		}// for
	}

	private boolean validarProductoRendimentoTermico(Long codigoProducto) {
		List<Long> productoRendimientoTer;
		Boolean isRendimiento = Boolean.FALSE;
		try {
			productoRendimientoTer = rendimientoTermicoLogic.obtenerCodigosProductosRendimientosTermicos();
			for (Long codigoProductoRendimiento : productoRendimientoTer) {
				if (codigoProducto.compareTo(codigoProductoRendimiento) == 0) {
					isRendimiento = Boolean.TRUE;
					break;
				}
			}
		} catch (LogicaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
		}

		return isRendimiento;
	}

	/**
	 * Método para aprobar el ajuste de una producción
	 * 
	 * @param codigoAjusteProduccion
	 * @param codigoUsuario
	 * @throws LogicaException
	 */

	public Object[] aprobarAjusteProduccion(Long codigoAjusteProduccion, UsuarioBean usuarioBean) throws LogicaException {
		Transaction tx = null;
		Session session = null;
		List<ResultadoBeanImpl> resultados = new ArrayList<ResultadoBeanImpl>();
		Boolean exitoOperacion = Boolean.FALSE;
		Boolean envioConsumo = Boolean.FALSE;
		Boolean envioCombustible = Boolean.FALSE;
		Object[] valoresRetorno = new Object[2];
		Short valorMes = 0;
		Integer valorAno = 0;
		Long lineaNegocio = 1L;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Ajusteproduccion ajusteProduccion = AjusteProduccionQuerier.getById(codigoAjusteProduccion);
			if (ajusteProduccion.getEstadoajusteproduccion().getNombreEstadoajusteproduccion().equals(ESTADO_APROBADO)) {
				mensajeError = MENSAJE_ERROR_AJUSTE;
				throw new LogicaException(mensajeError);
			}

			if (ajusteProduccion.getEstadoajusteproduccion().getNombreEstadoajusteproduccion().equals(ESTADO_INICIAL)) {
				String mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_APROBAR_AJUSTEPRODUCCION_INCIAL);
				mensajeError = mensaje;
				throw new LogicaException(mensajeError);
			}

			lineaNegocio = ajusteProduccion.getLineanegocio().getPkCodigoLineanegocio();

			valorMes = ajusteProduccion.getPeriodocontable().getMesPeriodocontable();
			valorAno = ajusteProduccion.getPeriodocontable().getAnoPeriodocontable();
			RegistroMovimientoLogic registrarMovimiento = new RegistroMovimientoLogic();
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean = new ArrayList<AprobarAjusteProduccionBean>();
			String[] arrCodProSap = null;
			Date fecha = FechaUtil.getUltimoDiaMes((short) (ajusteProduccion.getPeriodocontable().getMesPeriodocontable() - 1),
					ajusteProduccion.getPeriodocontable().getAnoPeriodocontable()).getTime();
			String fechaP_BUDAT = PropiedadesSap.formatoFechaPbudat(fecha);
			String fechaBLDATT = PropiedadesSap.formatoFechaBLDATT(fecha);

			try {
				arrCodProSap = ParametroSistemaQuerier.obtenerParametroSistema(ConstantesParametro.PRODUCTOS_ENVIO_SAP)
						.getValorParametro().split(",");

				listAprobarAjusteProduccionBean = ConsumoComponenteAjusteQuerier
						.obtenerPorAjusteProduccion(codigoAjusteProduccion);

				/**
				 * Ingresar en el envio a SAP los Ajustes realizados de manera
				 * Manual.
				 */
				List<AprobarAjusteProduccionBean> mapaAjusteProducto = MovimientoAjusteProductoQuerier
						.obtenerAjusteComponenteConsolidado(ajusteProduccion.getPkCodigoAjusteproduccion());
				for (AprobarAjusteProduccionBean ajusteProductoManual : mapaAjusteProducto) {
					AprobarAjusteProduccionBean beanAjusteModificado = null;
					for (AprobarAjusteProduccionBean ajusteProductoAutomatico : listAprobarAjusteProduccionBean) {
						if (ajusteProductoManual.getCodigoProductoComponente().compareTo(
								ajusteProductoAutomatico.getCodigoProductoComponente()) == 0) {
							beanAjusteModificado = ajusteProductoAutomatico;
							break;
						}
					}
					if (beanAjusteModificado != null) {
						beanAjusteModificado.setSumaTmRealConsumoComponenteAjuste(NumberUtil.sumar(
								beanAjusteModificado.getSumaTmRealConsumoComponenteAjuste(),
								ajusteProductoManual.getSumaTmRealConsumoComponenteAjuste()));
					} else {
						listAprobarAjusteProduccionBean.add(ajusteProductoManual);
					}

				}
				ResultadoBeanImpl resultadoMateriales = null;
				ResultadoBeanImpl resultadoConsumoCombustible = null;
				ResultadoBeanImpl resultadoMermaCombustible = null;

				if (!ajusteProduccion.getConsumoEnviadoSap()) {
					resultadoMateriales = registrarMovimiento.ingresarMovimientosPorLote(fechaP_BUDAT,
							listAprobarAjusteProduccionBean, fechaBLDATT, fecha);
				} else {

					resultadoMateriales = new ResultadoBeanImpl();
					resultadoMateriales.setExitoOperacion(ajusteProduccion.getConsumoEnviadoSap());
				}

				if (!ajusteProduccion.getCombutibleEnviadoSap() && resultadoMateriales.getExitoOperacion()) {
					listAprobarAjusteProduccionBean = ConsumoComponenteAjusteQuerier.obtenerConsumoAjuste(codigoAjusteProduccion,
							arrCodProSap[1]);

					resultadoConsumoCombustible = registrarMovimiento.ingresarMovimientosCombustibleConsumo(fechaP_BUDAT,
							listAprobarAjusteProduccionBean, fechaBLDATT);
					resultadoMermaCombustible = registrarMovimiento.ingresarMovimientosCombustibleMermas(fechaP_BUDAT,
							listAprobarAjusteProduccionBean, fechaBLDATT);
				} else {

					resultadoConsumoCombustible = new ResultadoBeanImpl();
					resultadoMermaCombustible = new ResultadoBeanImpl();
					resultadoConsumoCombustible.setExitoOperacion(ajusteProduccion.getCombutibleEnviadoSap());
					resultadoMermaCombustible.setExitoOperacion(ajusteProduccion.getCombutibleEnviadoSap());

				}

				exitoOperacion = resultadoMateriales.getExitoOperacion() && resultadoConsumoCombustible.getExitoOperacion()
						&& resultadoMermaCombustible.getExitoOperacion();
				envioConsumo = resultadoMateriales.getExitoOperacion();
				envioCombustible = resultadoConsumoCombustible.getExitoOperacion()
						&& resultadoMermaCombustible.getExitoOperacion();
				resultados.add(resultadoMateriales);
				resultados.add(resultadoConsumoCombustible);
				resultados.add(resultadoMermaCombustible);

			} catch (AplicacionException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			valoresRetorno[0] = exitoOperacion;
			valoresRetorno[1] = resultados;

			if (exitoOperacion) {

				// Aprobar Parte Tecnico
				Usuario usuario = new Usuario();
				usuario.setPkCodigoUsuario(usuarioBean.getCodigo());
				ajusteProduccion.setFechaAprobacionAjusteproducci(new Date());
				ajusteProduccion.setUsuarioByFkCodigoUsuario(usuario);
				Estadoajusteproduccion estadoAjusteProduccion = EstadoAjusteProduccionQuerier.findByName(ManejadorPropiedades
						.obtenerPropiedadPorClave(ETIQUETA_ESTADO_APROBADO));
				ajusteProduccion.setEstadoajusteproduccion(estadoAjusteProduccion);
				ajusteProduccion.setCombutibleEnviadoSap(true);
				ajusteProduccion.setConsumoEnviadoSap(true);
				session.update(ajusteProduccion);
				AjusteProductoQuerier.cambiarAEstadoAprobadoAjustesProducto(ajusteProduccion.getPkCodigoAjusteproduccion(),
						session);
				actualizarTasaRealesProduccionMes(ajusteProduccion, session);

				// Abrir Periodo Contable
				PeriodoContableLogicFacade periodoContableLogic = new PeriodoContableLogic();
				periodoContableLogic.registrarNuevoPeriodoContable(ajusteProduccion.getPeriodocontable().getAnoPeriodocontable(),
						ajusteProduccion.getPeriodocontable().getMesPeriodocontable());

				actualizarHumedadPonderadaCubicaciones(ajusteProduccion.getPeriodocontable());

			} else {

				if (envioCombustible) {
					ajusteProduccion.setCombutibleEnviadoSap(true);
				}
				if (envioConsumo) {
					ajusteProduccion.setConsumoEnviadoSap(true);
				}
			}
			tx.commit();

		} catch (ElementoNoEncontradoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (SesionVencidaException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		if (exitoOperacion) {
			

			// Cambiar Estado Ordenes produccion

			OrdenProduccionLogicFacade ordenProduccionLogic = new OrdenProduccionLogic();
			ordenProduccionLogic.cambiarEstadoOrdenesProduccion(valorAno, valorMes);
		}

	
		return valoresRetorno;
	}

	private void actualizarHumedadPonderadaCubicaciones(Periodocontable periodocontable) {

		try {

			String estadoCubicacionAprobada = ManejadorPropiedades
					.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);

			Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes((short) (periodocontable.getMesPeriodocontable() - 1),
					periodocontable.getAnoPeriodocontable());
			fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
			Date fechaMesInicial = fechaMesCalendar.getTime();

			Double humedad = 0d;
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put(CubicacionProductoQuerier.NOMBRE_ESTADO_CUBICACION, estadoCubicacionAprobada);
			parametros.put(CubicacionProductoQuerier.ANIO, periodocontable.getAnoPeriodocontable());
			parametros.put(CubicacionProductoQuerier.MES, periodocontable.getMesPeriodocontable());

			List<Cubicacionproducto> cubis = CubicacionProductoQuerier.buscarPorPropiedades(parametros);

			for (Cubicacionproducto cubicacion : cubis) {

				humedad = DownloadServlet.obtenerHumedad(cubicacion.getProduccion().getProducto(), fechaMesInicial);

				cubicacion.setHumedadPonderadaCubicacionproducto(humedad);
				CubicacionProductoQuerier.update(cubicacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void actualizarTasaRealesProduccionMes(Ajusteproduccion ajusteProduccion, Session session)
			throws ParametroInvalidoException, ElementoNoEncontradoException, EntornoEjecucionException {
		List<Puestotrabajoproduccion> ajustesProduccion = PuestoTrabajoProduccionQuerier
				.obtenerPorCodigoAjusteProduccion(ajusteProduccion.getPkCodigoAjusteproduccion());
		List<Tasarealprodregistromensual> tasaRealesRegistroMensuales = TasaRealProduccionRegistroMensualQuerier
				.obtenerTasaRealesPorAnnoMesProducto(ajusteProduccion.getPeriodocontable(), ajusteProduccion.getLineanegocio()
						.getPkCodigoLineanegocio());

		Periodocontable periodocontable = ajusteProduccion.getPeriodocontable();
		Puestotrabajoproduccion ultimoPuestoTrabajoProduccion;
		Ajusteproducto ajusteproducto;
		for (Tasarealprodregistromensual tasarealprodregistromensual : tasaRealesRegistroMensuales) {

			Puestotrabajoproduccion puestotrabajoproduccion = encontrarTasaRealRegistroMensual(ajustesProduccion,
					tasarealprodregistromensual);

			if (puestotrabajoproduccion != null) {

				if (puestotrabajoproduccion.getTmphRealPuestotrabajoproducci() > 0) {
					tasarealprodregistromensual.setCantidadTasarealprodregmensual(puestotrabajoproduccion
							.getTmphRealPuestotrabajoproducci());

				} else {

					ajusteproducto = puestotrabajoproduccion.getAjusteproducto();
					ultimoPuestoTrabajoProduccion = PuestoTrabajoProduccionQuerier
							.obtenerPorPeriodoContableProductoPuestoTrabajo(ajusteProduccion.getLineanegocio()
									.getPkCodigoLineanegocio(), ajusteproducto.getProducto().getPkCodigoProducto(),
									puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo(), periodocontable
											.getMesPeriodocontable(), periodocontable.getAnoPeriodocontable());

					if (ultimoPuestoTrabajoProduccion != null) {

						tasarealprodregistromensual.setCantidadTasarealprodregmensual(ultimoPuestoTrabajoProduccion
								.getTmphRealPuestotrabajoproducci());

					}

				}
			} else {

				ultimoPuestoTrabajoProduccion = PuestoTrabajoProduccionQuerier.obtenerPorPeriodoContableProductoPuestoTrabajo(
						ajusteProduccion.getLineanegocio().getPkCodigoLineanegocio(), tasarealprodregistromensual
								.getTasarealproduccion().getProduccion().getProducto().getPkCodigoProducto(),
						tasarealprodregistromensual.getTasarealproduccion().getPuestotrabajo().getPkCodigoPuestotrabajo(),
						periodocontable.getMesPeriodocontable(), periodocontable.getAnoPeriodocontable());

				if (ultimoPuestoTrabajoProduccion != null) {

					tasarealprodregistromensual.setCantidadTasarealprodregmensual(ultimoPuestoTrabajoProduccion
							.getTmphRealPuestotrabajoproducci());

				}

			}

			try {
				TasaRealProduccionRegistroMensualQuerier.update(tasarealprodregistromensual);
			} catch (ElementoExistenteException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (ElementoEliminadoException e) {
				logger.error(e);
				e.printStackTrace();
			}

		}

	}

	private Puestotrabajoproduccion encontrarTasaRealRegistroMensual(List<Puestotrabajoproduccion> ajustesProduccion,
			Tasarealprodregistromensual tasarealprodregistromensual) {

		for (Puestotrabajoproduccion puestotrabajoproduccion : ajustesProduccion) {

			if (tasarealprodregistromensual.getTasarealproduccion().getPuestotrabajo().getPkCodigoPuestotrabajo()
					.compareTo(puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo()) == 0
					&& tasarealprodregistromensual.getTasarealproduccion().getProduccion().getProducto().getPkCodigoProducto()
							.compareTo(puestotrabajoproduccion.getAjusteproducto().getProducto().getPkCodigoProducto()) == 0) {

				return puestotrabajoproduccion;
			}
		}
		return null;

	}

	/**
	 * Método para almacenar los ajuste de componentes y movimientos asociados
	 * 
	 * @param detallesConsumoComponenteAjuste
	 * @param detallesMovimientoAjuste
	 * @param mapaPuestoTrabajoProduccion
	 * @param ajusteProducto
	 * @param ajusteProducto
	 * @param registroBalanceInicial
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 */
	private void insertarConsumoComponentesAjuste(List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste,
			Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion, Ajusteproducto ajusteProducto)
			throws ElementoNoEncontradoException, ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException {

		Estadomovimiento estadoMovimiento = EstadoMovimientoQuerier.findByNombreUniqueResult(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_ESTADO_ACTIVA));
		String codigoTipoMovimientoSAPIngreso = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_TIPO_MOVIMIENTO_SAP_INGRESO)
				.toLowerCase();
		String codigoTipoMovimientoSAPSalida = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_TIPO_MOVIMIENTO_SAP_SALIDA)
				.toLowerCase();
		Tipomovimiento tipoMovimientoIngreso = TipoMovimientoQuerier.findByCodigoSAP(codigoTipoMovimientoSAPIngreso);
		Tipomovimiento tipoMovimientoSalida = TipoMovimientoQuerier.findByCodigoSAP(codigoTipoMovimientoSAPSalida);

		// 2. Preparo y salvo la información de los ajuste por componente
		for (int i = 0; i < detallesConsumoComponenteAjuste.size(); i++) {
			RegistroTablaConsumosPuestoTrabajoDTO consumoCompAjusteDto = detallesConsumoComponenteAjuste.get(i);
			Long codigoComponente = consumoCompAjusteDto.getCodigoComponente();
			Componente componente = ComponenteQuerier.getById(codigoComponente);

			Consumocomponenteajuste consumoComponenteAjuste = new Consumocomponenteajuste();
			Long codigoPuestoTrabajo = consumoCompAjusteDto.getCodigoPuestoTrabajo();

			consumoComponenteAjuste.setPuestotrabajoproduccion(mapaPuestoTrabajoProduccion.get(codigoPuestoTrabajo));
			consumoComponenteAjuste.setComponente(componente);
			consumoComponenteAjuste.setDiferenciaConsumocomponenteaju(consumoCompAjusteDto.getAjusteTM());
			consumoComponenteAjuste.setPorcentajeRealConsumocomponen(consumoCompAjusteDto.getProduccionRealPorcentaje());
			consumoComponenteAjuste.setPorcentProduccConsucompajuste(consumoCompAjusteDto.getProduccionPorcentaje());
			consumoComponenteAjuste.setPorcentCarbonConsucompajuste(consumoCompAjusteDto.getPorcetanjeCarbones());
			consumoComponenteAjuste.setTmProdConsumocomponenteajus(consumoCompAjusteDto.getMontoConsumido());
			consumoComponenteAjuste.setTmRealConsumocomponenteajuste(consumoCompAjusteDto.getProduccionRealTM());
			consumoComponenteAjuste.setEditadoManualConsumocompajus(consumoCompAjusteDto.isEditadoManual());

			// 3. Se salva el ajuste por compoenente
			ConsumoComponenteAjusteQuerier.save(consumoComponenteAjuste);

			guardarMovimientoAjuste(ajusteProducto, consumoComponenteAjuste, estadoMovimiento, tipoMovimientoIngreso,
					tipoMovimientoSalida, codigoComponente, codigoPuestoTrabajo, detallesMovimientoAjuste);

		}
		guardarMovimientoAjusteProducto(detallesMovimientoAjuste, mapaPuestoTrabajoProduccion);
	}

	private void guardarMovimientoAjuste(Ajusteproducto ajusteProducto, Consumocomponenteajuste consumoComponenteAjuste,
			Estadomovimiento estadoMovimiento, Tipomovimiento tipoMovimientoIngreso, Tipomovimiento tipoMovimientoSalida,
			Long codigoComponente, Long codigoPuestoTrabajo, List<RegistroTablaAjusteDTO> detallesMovimientoAjuste)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {
		// 4. Localizo el movimiento asociado al compoenente
		for (RegistroTablaAjusteDTO movAjusteDto : detallesMovimientoAjuste) {

			if (movAjusteDto.getTipoComponente().equals(ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_COMPONENTE_HOJA_RUTA))) {

				if (movAjusteDto.getCodigoComponente().longValue() == codigoComponente.longValue()
						&& movAjusteDto.getCodigoPuesto().longValue() == codigoPuestoTrabajo.longValue()) {

					Movimientoajuste movimientoAjuste = crearObjMovimientoAjuste(ajusteProducto, consumoComponenteAjuste,
							estadoMovimiento, tipoMovimientoIngreso, tipoMovimientoSalida, movAjusteDto);
					MovimientoAjusteQuerier.save(movimientoAjuste);
				}
			}
		}

	}

	private void guardarMovimientoAjusteMerma(Ajusteproducto ajusteProducto, Consumocomponenteajuste consumoComponenteAjuste,
			Estadomovimiento estadoMovimiento, Tipomovimiento tipoMovimientoIngreso, Tipomovimiento tipoMovimientoSalida,
			Long codigoComponente, Long codigoPuestoTrabajo, List<RegistroTablaAjusteDTO> detallesMovimientoAjuste)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {
		Tipocomponenteajuste tipomovimientoajuste = TipoComponenteAjusteQuerier.getbyNombre(ManejadorPropiedades
				.obtenerPropiedadPorClave(TIPO_COMPONENTE_MERMA));

		// 4. Localizo el movimiento asociado al compoenente
		for (RegistroTablaAjusteDTO movAjusteDto : detallesMovimientoAjuste) {

			if (movAjusteDto.getTipoComponente().equals(ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_COMPONENTE_MERMA))) {

				if (movAjusteDto.getCodigoComponente().longValue() == codigoComponente.longValue()
						&& movAjusteDto.getCodigoPuesto().longValue() == codigoPuestoTrabajo.longValue()) {

					Movimientoajuste movimientoAjuste = crearObjMovimientoAjuste(ajusteProducto, consumoComponenteAjuste,
							estadoMovimiento, tipoMovimientoIngreso, tipoMovimientoSalida, movAjusteDto);
					movimientoAjuste.setTipocomponenteajuste(tipomovimientoajuste);
					MovimientoAjusteQuerier.save(movimientoAjuste);
				}
			}
		}

	}

	private void guardarMovimientoAjusteProducto(List<RegistroTablaAjusteDTO> detallesMovimientoAjuste,
			Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		// 4. Localizo el movimiento asociado al compoenente
		for (RegistroTablaAjusteDTO movAjusteDto : detallesMovimientoAjuste) {

			if (movAjusteDto.getTipoComponente().equals(ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_COMPONENTE_PRODUCTOS))
					&& mapaPuestoTrabajoProduccion.containsKey(movAjusteDto.getCodigoPuesto())) {
				Movimientoajusteproducto movimientoAjusteProducto = crearObjMovimientoAjusteProducto(movAjusteDto,
						mapaPuestoTrabajoProduccion);
				MovimientoAjusteProductoQuerier.save(movimientoAjusteProducto);
			}
		}

	}

	private Movimientoajuste crearObjMovimientoAjuste(Ajusteproducto ajusteProducto,
			Consumocomponenteajuste consumoComponenteAjuste, Estadomovimiento estadoMovimiento,
			Tipomovimiento tipoMovimientoIngreso, Tipomovimiento tipoMovimientoSalida, RegistroTablaAjusteDTO movAjusteDto) {
		Movimientoajuste movimientoAjuste = new Movimientoajuste();

		movimientoAjuste.setCantidadMovimientoajuste(movAjusteDto.getConsumo());
		movimientoAjuste.setTipomovimiento(tipoMovimientoSalida);

		movimientoAjuste.setMovManualMovimientoajuste(movAjusteDto.isMovimientoManual());
		movimientoAjuste.setConsumocomponenteajuste(consumoComponenteAjuste);
		movimientoAjuste.setProducto(ajusteProducto.getProducto());
		movimientoAjuste.setDescripcionMovimientoajuste(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_MOVIMIENTO_DESCRIPCION_AJUSTE)
				+ movimientoAjuste.getProducto().getNombreProducto());
		movimientoAjuste.setEstadomovimiento(estadoMovimiento);
		return movimientoAjuste;
	}

	private Movimientoajusteproducto crearObjMovimientoAjusteProducto(RegistroTablaAjusteDTO movAjusteDto,
			Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion) {
		Movimientoajusteproducto movimientoAjusteProducto = new Movimientoajusteproducto();
		Producto producto = new Producto();
		producto.setPkCodigoProducto(movAjusteDto.getCodigoProductoComponente());
		movimientoAjusteProducto.setCantidadMovimientoajusteproducto(movAjusteDto.getConsumo());
		movimientoAjusteProducto.setProducto(producto);
		movimientoAjusteProducto.setPuestotrabajoproduccion(mapaPuestoTrabajoProduccion.get(movAjusteDto.getCodigoPuesto()));
		return movimientoAjusteProducto;
	}

	/**
	 * Método para insertar en bd la produccion por puesto de trabajo
	 * 
	 * @param ajusteProducto
	 * @param detallesProduccionPuestoTrabajo
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	private Map<Long, Puestotrabajoproduccion> insertarPuestoTrabajoProduccion(Ajusteproducto ajusteProducto,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo) throws ElementoNoEncontradoException,
			ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException {
		Puestotrabajoproduccion puestoTrabajoProduccion = null;

		Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion = new HashMap<Long, Puestotrabajoproduccion>();

		for (int i = 0; i < detallesProduccionPuestoTrabajo.size(); i++) {
			puestoTrabajoProduccion = new Puestotrabajoproduccion();
			RegistroPuestoTrabajoProduccionDTO detalle = detallesProduccionPuestoTrabajo.get(i);
			Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier.getById(detalle.getCodigoPuestoTrabajo());

			puestoTrabajoProduccion.setAjusteproducto(ajusteProducto);
			puestoTrabajoProduccion.setPuestotrabajo(puestoTrabajo);

			// Produccion
			puestoTrabajoProduccion.setHrPuestotrabajoproduccion(detalle.getProduccionHR());
			puestoTrabajoProduccion.setTmPuestotrabajoproduccion(detalle.getProduccionTM());
			puestoTrabajoProduccion.setTmphPuestotrabajoproduccion(detalle.getProduccionTMPH());

			// Ajuste
			puestoTrabajoProduccion.setHrAjustePuestotrabajoproducci(detalle.getAjusteHR());
			puestoTrabajoProduccion.setTmAjustePuestotrabajoproducci(detalle.getAjusteTM());

			// Real
			puestoTrabajoProduccion.setHrRealPuestotrabajoproduccion(detalle.getProduccionRealHR());
			puestoTrabajoProduccion.setTmRealPuestotrabajoproduccion(detalle.getProduccionRealTM());
			puestoTrabajoProduccion.setTmphRealPuestotrabajoproducci(detalle.getProduccionRealTMPH());

			puestoTrabajoProduccion.setBunkprodPuestotrabajoproduccion(detalle.getBunkerProduccion());
			puestoTrabajoProduccion.setBunkcalentPuestotrabajoproduccion(detalle.getBunkerCalent());
			puestoTrabajoProduccion.setBunkprodRealPuestotrabajoproduccion(detalle.getBunkerProduccionReal());
			puestoTrabajoProduccion.setBunkcalentRealPuestotrabajoproduccion(detalle.getBunkerCalentReal());

			puestoTrabajoProduccion.setCarprodPuestotrabajoproduccion(detalle.getProduccionCarProd());
			puestoTrabajoProduccion.setCarcalentPuestotrabajoproduccion(detalle.getProduccionCarCalent());
			puestoTrabajoProduccion.setCarprodRealPuestotrabajoproduccion(detalle.getProduccionRealCarProd());
			puestoTrabajoProduccion.setCarcalentRealPuestotrabajoproduccion(detalle.getProduccionRealCarCalent());

			// Kilo Calorias
			puestoTrabajoProduccion.setKcalPuestotrabajoproduccion(detalle.getProduccionKCAL());
			puestoTrabajoProduccion.setKcalRealPuestotrabajoproducci(detalle.getProduccionRealKCAL());

			PuestoTrabajoProduccionQuerier.save(puestoTrabajoProduccion);

			mapaPuestoTrabajoProduccion.put(puestoTrabajo.getPkCodigoPuestotrabajo(), puestoTrabajoProduccion);
		}

		return mapaPuestoTrabajoProduccion;
	}

	/**
	 * Método para insertar la tabla balance
	 * 
	 * @param ajusteProducto
	 * @param saldoInicialLibroBalance
	 * @param produccionLibroBalance
	 * @throws AplicacionException
	 */
	private void insertarTablaBalance(Ajusteproducto ajusteProducto, double saldoInicialLibroBalance,
			double produccionLibroBalance, double saldoFinalLibroBalance, double consumoLibroBalance,
			double consumoAjusteBalance, double produccionAjusteBalance) throws AplicacionException {

		List<Tipobalance> tiposBalance = TipoBalanceQuerier.getAll();
		Map<String, Tipobalance> tiposBalanceMap = new HashMap<String, Tipobalance>();
		for (int i = 0; i < tiposBalance.size(); i++) {
			tiposBalanceMap.put(tiposBalance.get(i).getNombreTipobalance(), tiposBalance.get(i));
		}

		List<Concepto> conceptos = ConceptoQuerier.getAll();
		Map<String, Concepto> conceptosMap = new HashMap<String, Concepto>();
		for (int i = 0; i < conceptos.size(); i++) {
			conceptosMap.put(conceptos.get(i).getNombreConcepto(), conceptos.get(i));
		}
		// 3.1.1 Saldo Inicial del Libro
		Balanceproducto balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_SALDO_INICIAL)));
		balanceProducto.setMontoBalanceproducto(saldoInicialLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.2 Produccion del Libro
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto
				.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_PRODUCCION)));
		balanceProducto.setMontoBalanceproducto(produccionLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.3 Consumo del Libro
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO)));
		balanceProducto.setMontoBalanceproducto(consumoLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.4 Saldo Final del Libro
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto
				.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_SALDO_FINAL)));
		balanceProducto.setMontoBalanceproducto(saldoFinalLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.5 Produccion del Ajuste
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto
				.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_PRODUCCION)));
		balanceProducto.setMontoBalanceproducto(produccionAjusteBalance);
		balanceProducto
				.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_BALANCE_AJUSTE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.6 Consumo del Ajuste
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO)));
		balanceProducto.setMontoBalanceproducto(consumoAjusteBalance);
		balanceProducto
				.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_BALANCE_AJUSTE)));
		BalanceProductoQuerier.save(balanceProducto);
	}

	public AjusteProductoDTO verificarSiExisteAjusteBd(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) throws LogicaException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();

			Short mes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			mes++;

			Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorProductoLineaNegPeriodoContYGrupo(codigoProducto,
					codigoLinea, mes, anioContable);

			if (ajusteProducto != null) {
				AjusteProductoDTO ajusteProductoDTO = new AjusteProductoDTO(ajusteProducto.getPkCodigoAjusteproducto(),
						ajusteProducto.getEstadoajusteproducto().getNombreEstadoajusteproducto(),
						ajusteProducto.getObservacionAjusteproducto(), ajusteProducto.getAjusteAjusteproducto());
				return ajusteProductoDTO;
			}

			return null;

		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public List<PlantillaGrupoAjusteBean> obtenerPlantillaGrupoAjuste(Long codigoLin) throws LogicaException {

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

		List<PlantillaGrupoAjusteBean> gruposAjuste = new ArrayList<PlantillaGrupoAjusteBean>();
		try {
			gruposAjuste = beanFactory.transformarListaPlantillaGrupoAjuste(PlantillaGrupoAjusteQuerier
					.obtenerGruposAjuste(codigoLin));
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return gruposAjuste;
	}

	public List<AjusteProduccionBean> obtenerAjustePorduccionPorPerdiodoLineaNegocio(Long lineaNegocio, Short mes, Integer anno)
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

		List<AjusteProduccionBean> ajustes = null;
		try {

			List<Ajusteproduccion> ajustesHO = AjusteProduccionQuerier.getListaByLineaNegocioMesYAno(mes, anno, lineaNegocio);

			ajustes = beanFactory.transformarListaAjusteProduccion(ajustesHO);

		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return ajustes;
	}

	public Double[] obtenerStocksMensualComponente(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException {

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

		Double[] arrayStocksDoubles = null;
		try {
			arrayStocksDoubles = obtenerStocksMensualComponenteDAO(componente, codigoLinea, mes, anio, esClinkerI);

		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return arrayStocksDoubles;
	}

	/**
	 * Este metodo es la logica de obtenerStocksMensualComponente encapsulado en
	 * un metodo que no cierra sesion
	 * 
	 * @param componente
	 * @param codigoLinea
	 * @param mes
	 * @param anio
	 * @param esClinkerI
	 * @return
	 * @throws LogicaException
	 */
	public Double[] obtenerStocksMensualComponenteDAO(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException {

		Double[] arrayStocksDoubles = null;
		try {
			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(componente);

			Object[] arrayStocks = null;
			if (esMateriaPrima) {

				arrayStocks = ProduccionDiariaQuerier.obtenerStocksMensualProducto(componente.getPkCodigoProducto(), codigoLinea,
						mes, anio, false);

			} else {
				arrayStocks = ProduccionDiariaQuerier.obtenerStocksMensualProducto(componente.getPkCodigoProducto(), codigoLinea,
						mes, anio, true);
			}

			Double saldoInicial = arrayStocks[0] == null ? 0d : (Double) arrayStocks[0];
			Double ingreso = arrayStocks[1] == null ? 0d : (Double) arrayStocks[1];
			Double consumo = arrayStocks[2] == null ? 0d : (Double) arrayStocks[2];
			Double ajusteLogistico = arrayStocks[3] == null ? 0d : (Double) arrayStocks[3];

			if (esClinkerI) {
				// Esto porq en el query se suma el consumo de clinker en
				// proceso HH y HV y son el mismo ya que el consumo es por
				// producto es independiente del proceso
				consumo /= 2;
				if (mes != null) {
					saldoInicial = ProduccionDiariaQuerier.obtenerSaldoInicialClinker(componente.getPkCodigoProducto(),
							codigoLinea, mes, anio);
				}

			}

			arrayStocksDoubles = new Double[] { saldoInicial, ingreso, consumo, ajusteLogistico };

		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		}
		return arrayStocksDoubles;
	}

	public Double[] obtenerStocksAnualComponente(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException {

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

		Double[] arrayStocksDoubles = null;
		try {
			arrayStocksDoubles = obtenerStocksAnualComponenteDAO(componente, codigoLinea, mes, anio, esClinkerI);

		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return arrayStocksDoubles;
	}

	public Double[] obtenerStocksAnualComponenteDAO(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException {

		Double[] arrayStocksDoubles = null;
		try {
			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(componente);

			Object[] arrayStocks = null;
			if (esMateriaPrima) {

				arrayStocks = ProduccionDiariaQuerier.obtenerStocksAnualProducto(componente.getPkCodigoProducto(), codigoLinea,
						mes, anio, false);

			} else {
				arrayStocks = ProduccionDiariaQuerier.obtenerStocksAnualProducto(componente.getPkCodigoProducto(), codigoLinea,
						mes, anio, true);
			}

			Double saldoInicial = arrayStocks[0] == null ? 0d : (Double) arrayStocks[0];
			Double ingreso = arrayStocks[1] == null ? 0d : (Double) arrayStocks[1];
			Double consumo = arrayStocks[2] == null ? 0d : (Double) arrayStocks[2];

			if (esClinkerI) {
				// Esto porq en el query se suma el consumo de clinker en
				// proceso HH y HV y son el mismo ya que el consumo es por
				// producto es independiente del proceso
				consumo /= 2;
				// if(mes!=null){
				// saldoInicial =
				// ProduccionDiariaQuerier.obtenerSaldoInicialClinker(componente.getPkCodigoProducto(),
				// codigoLinea, mes, anio);
				// }

			}

			arrayStocksDoubles = new Double[] { saldoInicial, ingreso, consumo };

		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			e.printStackTrace();
			logger.debug(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		}
		return arrayStocksDoubles;
	}

	public ReporteAjusteProduccionBean obtenerReporteAjusteProduccion(Integer valorAnio, Short valorMes, Long valorLineaNegocio)
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

		ArrayList<Long> productosExcluidos = new ArrayList<Long>();
		String tituloSubReporteDiferencias = "reporte.ajuste.diferecias.subReporte";
		String tituloSubReporteDiferenciaCombustible = "reporte.ajuste.diferecias.subReporteCombustible";
		try {
			ParametroSistemaBean productoExcluidoReporteMovimineto = parametrosSistema
					.obtenerParametroSistema(ConstantesParametro.PRODUCTOS_EXCEPCION_REPORTE_DIFERENCIA);
			if (productoExcluidoReporteMovimineto != null) {
				productosExcluidos.add(Long.valueOf(productoExcluidoReporteMovimineto.getValor()));
			}

		} catch (LogicaException e) {
			logger.debug(e.getMensaje());
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		ReporteAjusteProduccionBean reporte = new ReporteAjusteProduccionBean();
		try {

			List<Movimientoajuste> movimientosProductos = MovimientoAjusteQuerier.obtenerMovimientosAjustes(valorAnio, valorMes,
					valorLineaNegocio, productosExcluidos);

			reporte.setTituloSubReporte(MessageFormat.format(
					ManejadorPropiedades.obtenerPropiedadPorClave(tituloSubReporteDiferencias),
					FechaUtil.numeroMesANombreMes(valorMes), valorAnio.toString()));
			reporte.setTituloReporteCombustible(MessageFormat.format(
					ManejadorPropiedades.obtenerPropiedadPorClave(tituloSubReporteDiferenciaCombustible),
					FechaUtil.numeroMesANombreMes(valorMes), valorAnio.toString()));

			List<SubReporteAjusteProduccionBean> subReporteMovimentos = new ArrayList<SubReporteAjusteProduccionBean>();
			for (Movimientoajuste movimientoajuste : movimientosProductos) {
				SubReporteAjusteProduccionBean subreporteAjusteProduccionBean = new SubReporteAjusteProduccionBean();

				subreporteAjusteProduccionBean.setProducto(movimientoajuste.getProducto().getNombreProducto());
				subreporteAjusteProduccionBean.setPuestoTrabajo(movimientoajuste.getConsumocomponenteajuste()
						.getPuestotrabajoproduccion().getPuestotrabajo().getNombrePuestotrabajo());
				subreporteAjusteProduccionBean.setComponente(movimientoajuste.getConsumocomponenteajuste().getComponente()
						.getProductoByFkCodigoProductoComponente().getNombreProducto());
				subreporteAjusteProduccionBean.setAjuste(movimientoajuste.getCantidadMovimientoajuste());
				subreporteAjusteProduccionBean.setToneladareal(movimientoajuste.getConsumocomponenteajuste()
						.getTmRealConsumocomponenteajuste());
				subreporteAjusteProduccionBean.setPorcentajereal(movimientoajuste.getConsumocomponenteajuste()
						.getPorcentajeRealConsumocomponen());
				subreporteAjusteProduccionBean.setCosto(NumberUtil.multiplicar(movimientoajuste.getCantidadMovimientoajuste(),
						movimientoajuste.getProducto().getCostoProducto()));
				subreporteAjusteProduccionBean.setToneladaNotificado(movimientoajuste.getConsumocomponenteajuste()
						.getTmProdConsumocomponenteajus());
				subreporteAjusteProduccionBean.setPorcentajeRealDNotificado(NumberUtil.porcentaje(movimientoajuste
						.getCantidadMovimientoajuste(), movimientoajuste.getConsumocomponenteajuste()
						.getTmProdConsumocomponenteajus()));

				subReporteMovimentos.add(subreporteAjusteProduccionBean);
			}
			reporte.setListaSubReporte(subReporteMovimentos);

			if (productosExcluidos != null && productosExcluidos.size() > 0) {

				List<Object[]> movimientosBunker = MovimientoAjusteQuerier.obtenerMovimientosAjustesCombustible(valorAnio,
						valorMes, valorLineaNegocio, productosExcluidos);

				List<SubReporteAjusteProduccionBean> subReporteBunker = new ArrayList<SubReporteAjusteProduccionBean>();
				for (Object[] movimientoajuste : movimientosBunker) {
					SubReporteAjusteProduccionBean subreporteAjusteProduccionBean = new SubReporteAjusteProduccionBean();
					subreporteAjusteProduccionBean.setComponente(movimientoajuste[0] + "");
					subreporteAjusteProduccionBean.setPuestoTrabajo(movimientoajuste[1] + "");

					subreporteAjusteProduccionBean.setToneladaNotificado(NumberUtil.convertirObjectToDouble(movimientoajuste[2]));
					subreporteAjusteProduccionBean.setToneladareal(NumberUtil.convertirObjectToDouble(movimientoajuste[3]));
					subreporteAjusteProduccionBean.setAjuste(NumberUtil.convertirObjectToDouble(movimientoajuste[4]));
					subreporteAjusteProduccionBean.setPorcentajereal(NumberUtil.convertirObjectToDouble(movimientoajuste[5]));

					subReporteBunker.add(subreporteAjusteProduccionBean);
				}
				reporte.setListaSubReporteCombustibles(subReporteBunker);
			}
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return reporte;
	}

	public List<RegistroTablaConsumosPuestoTrabajoDTO> obtenerConsumoComponenteProducto(Long codigoProducto, Long codigoLinea,
			Short numeroMes, Integer anioContable) throws LogicaException {

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

		List<RegistroTablaConsumosPuestoTrabajoDTO> registroConsumoPuestoTrabajo = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
		try {
			Short mesAnterior = numeroMes;
			Double factordbl;
			List<Consumocomponenteajuste> consumoComponente = ConsumoComponenteAjusteQuerier.obtenerPorAjusteProducto(
					codigoProducto, codigoLinea, anioContable, numeroMes);

			List<Consumocomponenteajuste> listaFactores = ConsumoComponenteAjusteQuerier.obtenerPorAjusteProducto(codigoProducto,
					codigoLinea, anioContable, --mesAnterior);

			Map<String, Double> factor = obtenerFactorMesAterior(listaFactores);
			RegistroTablaConsumosPuestoTrabajoDTO registroTabla;

			if (consumoComponente != null && consumoComponente.size() > 0) {

				for (Consumocomponenteajuste consumoptBean : consumoComponente) {

					registroTabla = new RegistroTablaConsumosPuestoTrabajoDTO();

					registroTabla.setCodigoPuestoTrabajo(consumoptBean.getPuestotrabajoproduccion().getPuestotrabajo()
							.getPkCodigoPuestotrabajo());
					registroTabla.setNombrePuestoTrabajo(consumoptBean.getPuestotrabajoproduccion().getPuestotrabajo()
							.getNombrePuestotrabajo());
					registroTabla.setCodigoProductoComponente(consumoptBean.getComponente().getProductoByFkCodigoProducto()
							.getPkCodigoProducto());
					registroTabla.setNombreComponente(consumoptBean.getComponente().getProductoByFkCodigoProducto()
							.getNombreProducto());
					registroTabla.setCodigoComponente(consumoptBean.getComponente().getPkCodigoComponente());

					registroTabla.setProduccionRealTM(consumoptBean.getPuestotrabajoproduccion().getTmPuestotrabajoproduccion());
					registroTabla.setMontoConsumido(consumoptBean.getTmProdConsumocomponenteajus());
					registroTabla.setPorcetanjeCarbones(consumoptBean.getPorcentCarbonConsucompajuste());

					registroTabla.setAjusteTM(consumoptBean.getDiferenciaConsumocomponenteaju());
					factordbl = factor.get(registroTabla.getCodigoPuestoTrabajo() + "-" + registroTabla.getNombreComponente());
					if (factordbl == null) {
						factordbl = 0D;
					}

					registroTabla.setDosificacion(factordbl);
					registroConsumoPuestoTrabajo.add(registroTabla);
				}
			} else {

				List<Object[]> consumoPuestoTrabajo = ConsumoPuestoTrabajoQuerier.obtenerByCodigoProductoComponente(
						codigoProducto, numeroMes, anioContable, codigoLinea);

				for (Object[] consumoptBean : consumoPuestoTrabajo) {

					registroTabla = new RegistroTablaConsumosPuestoTrabajoDTO();

					registroTabla.setCodigoPuestoTrabajo(Long.valueOf(consumoptBean[0].toString()));
					registroTabla.setNombrePuestoTrabajo(consumoptBean[1].toString());
					registroTabla.setCodigoProductoComponente(Long.valueOf(consumoptBean[2].toString()));
					registroTabla.setNombreComponente(consumoptBean[3].toString());
					registroTabla.setCodigoComponente(Long.valueOf(consumoptBean[4].toString()));
					registroTabla.setProduccionRealTM((Double) consumoptBean[5]);
					registroTabla.setMontoConsumido((Double) consumoptBean[6]);

					factordbl = factor.get(registroTabla.getCodigoPuestoTrabajo() + "-" + registroTabla.getNombreComponente());
					if (factordbl == null) {
						factordbl = 0D;
					}

					registroTabla.setDosificacion(factordbl);
					registroConsumoPuestoTrabajo.add(registroTabla);
				}
			}
		} catch (EntornoEjecucionException e) {
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

		return registroConsumoPuestoTrabajo;
	}

	private Map<String, Double> obtenerFactorMesAterior(List<Consumocomponenteajuste> listaFactores) {

		Map<String, Double> mapaFactores = new HashMap<String, Double>();
		String codigo = "";
		Double dividendo = 0d;
		Double divisor = 0d;
		Double factor = 0d;
		for (Consumocomponenteajuste consumoptBean : listaFactores) {
			codigo = consumoptBean.getPuestotrabajoproduccion().getPuestotrabajo().getPkCodigoPuestotrabajo() + "-"
					+ consumoptBean.getComponente().getProductoByFkCodigoProducto().getNombreProducto();
			dividendo = consumoptBean.getTmProdConsumocomponenteajus() + consumoptBean.getDiferenciaConsumocomponenteaju();
			divisor = consumoptBean.getPuestotrabajoproduccion().getTmPuestotrabajoproduccion();
			factor = 0d;
			if (divisor > 0.000) {
				factor = dividendo / divisor;

			}

			mapaFactores.put(codigo, NumberUtil.redondear(factor, 3));
		}
		return mapaFactores;
	}

	public void insertarAjusteProductoCombustible(Double mermaIngresada, Double ajuste, Long codigoLineaNegocio, Integer anio,
			Short mes, Long codigoUsuarioAjusta, Long codigoUsuarioAprueba, Long codigoPlantillaGrupoAjuste, Long codigoProducto,
			double saldoInicialLibroBalance, double produccionLibroBalance, double saldoFinalLibroBalance,
			double consumoLibroBalance, double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String fechaFactura, String cantidadFactura,
			String cantidadRestante, String observaciones) throws LogicaException {
		Boolean parteCerrado = this.verificarParteDiarioCerrado(codigoLineaNegocio, FechaUtil.numeroMesANombreMes(mes), anio);

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		Usuario usuarioAjusta;
		String login = "";
		String nombre = "";
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Usuario usuarioAprueba = null;

			if (codigoUsuarioAprueba != null) {
				usuarioAprueba = UsuarioQuerier.getById(codigoUsuarioAprueba);
			}

			usuarioAjusta = UsuarioQuerier.getById(codigoUsuarioAjusta);
			login = usuarioAjusta.getLoginUsuario();
			nombre = usuarioAjusta.getPersona().getApellidoPersona() + " , " + usuarioAjusta.getPersona().getNombrePersona();
			Ajusteproduccion ajusteProduccion = AjusteProduccionQuerier.getByLineaNegocioMesYAno(mes, anio, codigoLineaNegocio);

			Lineanegocio lineaNegocio = LineaNegocioQuerier.getById(codigoLineaNegocio);
			Periodocontable periodoContable = PeriodoContableQuerier.getByMesYAno(mes, anio);
			if (ajusteProduccion == null) {
				Estadoajusteproduccion estadoAjusteProduccion = null;
				if (!parteCerrado) {
					estadoAjusteProduccion = EstadoAjusteProduccionQuerier.findByName(ManejadorPropiedades
							.obtenerPropiedadPorClave(ETIQUETA_ESTADO_INICIAL));

				} else {

					estadoAjusteProduccion = EstadoAjusteProduccionQuerier.findByName(ManejadorPropiedades
							.obtenerPropiedadPorClave(ETIQUETA_ESTADO_GENERADO));
				}

				ajusteProduccion = new Ajusteproduccion();
				ajusteProduccion.setLineanegocio(lineaNegocio);
				ajusteProduccion.setPeriodocontable(periodoContable);
				ajusteProduccion.setUsuarioByFkCodigoUsuarioRegistra(usuarioAjusta);
				ajusteProduccion.setEstadoajusteproduccion(estadoAjusteProduccion);
				ajusteProduccion.setCombutibleEnviadoSap(false);
				ajusteProduccion.setConsumoEnviadoSap(false);

				AjusteProduccionQuerier.save(ajusteProduccion);
			}

			// 2. Insertar producto del grupo
			Plantillagrupoajuste plantillagrupoajuste = new Plantillagrupoajuste();
			plantillagrupoajuste.setPkCodigoPlantillagrupoajuste(codigoPlantillaGrupoAjuste);

			Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorProductoLineaNegPeriodoContYGrupo(codigoProducto,
					codigoLineaNegocio, mes, anio);

			if (ajusteProducto != null) {
				AjusteProductoQuerier.delete(ajusteProducto);
			}

			ajusteProducto = new Ajusteproducto();
			Estadoajusteproducto estadoAjusteProducto = EstadoAjusteProductoQuerier.findByName(ManejadorPropiedades
					.obtenerPropiedadPorClave(NOMBRE_ESTADO_AJUSTE_PRODUCTO_GENERADO));

			Producto producto = new Producto();
			producto.setPkCodigoProducto(codigoProducto);

			ajusteProducto.setAjusteproduccion(ajusteProduccion);
			ajusteProducto.setPlantillagrupoajuste(plantillagrupoajuste);
			ajusteProducto.setEstadoajusteproducto(estadoAjusteProducto);
			ajusteProducto.setProducto(producto);
			ajusteProducto.setUsuarioByFkCodigoUsuarioAjusta(usuarioAjusta);
			ajusteProducto.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprueba);
			ajusteProducto.setAjusteAjusteproducto(ajuste);
			ajusteProducto.setObservacionAjusteproducto(observaciones);

			AjusteProductoQuerier.save(ajusteProducto);

			insertarTablaBalanceMerma(mermaIngresada, ajusteProducto, saldoInicialLibroBalance, produccionLibroBalance,
					saldoFinalLibroBalance, consumoLibroBalance, consumoAjusteBalance, produccionAjusteBalance);

			insertarTablaBalanceCombustible(ajusteProducto, NumberUtil.convertirStringToDouble(cantidadFactura),
					NumberUtil.convertirStringToDouble(cantidadRestante), fechaFactura);

			Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion = insertarPuestoTrabajoProduccionCombustible(
					ajusteProducto, detallesConsumoComponenteAjuste);

			insertarConsumoComponentesAjusteCombustible(detallesConsumoComponenteAjuste, detallesMovimientoAjuste,
					mapaPuestoTrabajoProduccion, ajusteProducto);

			tx.commit();
		} catch (ParametroInvalidoException e) {
			e.printStackTrace();
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			e.printStackTrace();
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = e.getMensaje();
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}


	}

	private void insertarTablaBalanceMerma(Double mermaIngresada, Ajusteproducto ajusteProducto, double saldoInicialLibroBalance,
			double produccionLibroBalance, double saldoFinalLibroBalance, double consumoLibroBalance,
			double consumoAjusteBalance, double produccionAjusteBalance) throws AplicacionException {

		List<Tipobalance> tiposBalance = TipoBalanceQuerier.getAll();
		Map<String, Tipobalance> tiposBalanceMap = new HashMap<String, Tipobalance>();
		for (int i = 0; i < tiposBalance.size(); i++) {
			tiposBalanceMap.put(tiposBalance.get(i).getNombreTipobalance(), tiposBalance.get(i));
		}

		List<Concepto> conceptos = ConceptoQuerier.getAll();
		Map<String, Concepto> conceptosMap = new HashMap<String, Concepto>();
		for (int i = 0; i < conceptos.size(); i++) {
			conceptosMap.put(conceptos.get(i).getNombreConcepto(), conceptos.get(i));
		}
		// 3.1.1 Saldo Inicial del Libro
		Balanceproducto balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_SALDO_INICIAL)));
		balanceProducto.setMontoBalanceproducto(saldoInicialLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.2 Produccion del Libro
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto
				.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_PRODUCCION)));
		balanceProducto.setMontoBalanceproducto(produccionLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.3 Consumo del Libro
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO)));
		balanceProducto.setMontoBalanceproducto(consumoLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.4 Saldo Final del Libro
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto
				.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_SALDO_FINAL)));
		balanceProducto.setMontoBalanceproducto(saldoFinalLibroBalance);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.5 Produccion del Ajuste
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto
				.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_PRODUCCION)));
		balanceProducto.setMontoBalanceproducto(produccionAjusteBalance);
		balanceProducto
				.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_BALANCE_AJUSTE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.6 Consumo del Ajuste
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO)));
		balanceProducto.setMontoBalanceproducto(consumoAjusteBalance);
		balanceProducto
				.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_BALANCE_AJUSTE)));
		BalanceProductoQuerier.save(balanceProducto);

		// merma ingresadad por la caja de texto
		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_MERMA)));
		balanceProducto.setMontoBalanceproducto(mermaIngresada);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);
	}

	/**
	 * inserta la los registros en puestotrabajoproduccion para el ajuste del
	 * 
	 * @param ajusteProducto
	 * @param detallesConsumoComponenteAjuste
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 */
	private Map<Long, Puestotrabajoproduccion> insertarPuestoTrabajoProduccionCombustible(Ajusteproducto ajusteProducto,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste) throws ElementoNoEncontradoException,
			ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException {
		Puestotrabajoproduccion puestoTrabajoProduccion = null;

		Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion = new HashMap<Long, Puestotrabajoproduccion>();
		Long codigo = 0L;
		for (int i = 0; i < detallesConsumoComponenteAjuste.size(); i++) {
			puestoTrabajoProduccion = new Puestotrabajoproduccion();
			RegistroTablaConsumosPuestoTrabajoDTO detalle = detallesConsumoComponenteAjuste.get(i);
			Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier.getById(detalle.getCodigoPuestoTrabajo());

			puestoTrabajoProduccion.setAjusteproducto(ajusteProducto);
			puestoTrabajoProduccion.setPuestotrabajo(puestoTrabajo);

			puestoTrabajoProduccion.setTmPuestotrabajoproduccion(detalle.getProduccionRealTM());

			puestoTrabajoProduccion.setHrPuestotrabajoproduccion(0D);
			puestoTrabajoProduccion.setTmphPuestotrabajoproduccion(0D);

			// Ajuste
			puestoTrabajoProduccion.setHrAjustePuestotrabajoproducci(0D);
			puestoTrabajoProduccion.setTmAjustePuestotrabajoproducci(0D);

			// Real
			puestoTrabajoProduccion.setHrRealPuestotrabajoproduccion(0D);
			puestoTrabajoProduccion.setTmRealPuestotrabajoproduccion(0D);
			puestoTrabajoProduccion.setTmphRealPuestotrabajoproducci(0D);

			PuestoTrabajoProduccionQuerier.save(puestoTrabajoProduccion);
			codigo++;
			detalle.setCodigoPuestoTrabajo(detalle.getCodigoPuestoTrabajo());
			detalle.setCodigo(codigo);
			mapaPuestoTrabajoProduccion.put(codigo, puestoTrabajoProduccion);
		}

		return mapaPuestoTrabajoProduccion;
	}

	public String[] obtenerConsumoComponenteProductoFecha(Long codigoProducto, Long codigoLineaNegocio, String fecha)
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

			Date fechaInicio = FechaUtil.FormaterFecha(fecha);
			Date fechaFin = FechaUtil.getUltimoDiaMes((short) (FechaUtil.obtenerMesFecha(fechaInicio) - 1),
					FechaUtil.obtenerAnnioFecha(fechaInicio)).getTime();

			Double consumo = ConsumoPuestoTrabajoQuerier.obtenerConsumoComponentesPorFechaInicioFin(fechaInicio, fechaFin,
					codigoLineaNegocio, codigoProducto);
			if (consumo == null) {
				consumo = 0D;
			}
			String[] valores = new String[3];
			valores[0] = FechaUtil.convertirDateToString(FechaUtil.obtenerFechaDiaSiguiente(fechaInicio));
			valores[1] = FechaUtil.convertirDateToString(fechaFin);
			valores[2] = NumberUtil.Redondear2Decimales(consumo) + "";
			return valores;
		} catch (Exception e) {
			e.printStackTrace();

			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	private void insertarTablaBalanceCombustible(Ajusteproducto ajusteProducto, double consumoFactura, double consumoRestante,
			String fechaFactura) throws AplicacionException {

		List<Tipobalance> tiposBalance = TipoBalanceQuerier.getAll();
		Map<String, Tipobalance> tiposBalanceMap = new HashMap<String, Tipobalance>();
		for (int i = 0; i < tiposBalance.size(); i++) {
			tiposBalanceMap.put(tiposBalance.get(i).getNombreTipobalance(), tiposBalance.get(i));
		}

		List<Concepto> conceptos = ConceptoQuerier.getAll();
		Map<String, Concepto> conceptosMap = new HashMap<String, Concepto>();
		for (int i = 0; i < conceptos.size(); i++) {
			conceptosMap.put(conceptos.get(i).getNombreConcepto(), conceptos.get(i));
		}
		// 3.1.1 Consumo Factura del combustible
		Balanceproducto balanceProducto = new Balanceproducto();

		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO_COMPROBANTE)));
		balanceProducto.setMontoBalanceproducto(consumoFactura);
		balanceProducto.setFecha_balanceproducto(fechaFactura);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

		// 3.1.2 consumo Restante de Fecha Inicio -> fecha Fin

		balanceProducto = new Balanceproducto();
		balanceProducto.setAjusteproducto(ajusteProducto);
		balanceProducto.setConcepto(conceptosMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO_RESTANTE)));
		balanceProducto.setMontoBalanceproducto(consumoRestante);
		balanceProducto.setTipobalance(tiposBalanceMap.get(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_BALANCE_BALANCE)));
		BalanceProductoQuerier.save(balanceProducto);

	}

	public String[] obtenerDatosComprobante(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable)
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

			List<Balanceproducto> balancesProducto = BalanceProductoQuerier.obtenerBalanceProducto(codigoProducto, codigoLinea,
					mesContable, anioContable);

			String[] datosServidor = new String[4];
			for (Balanceproducto balanceproducto : balancesProducto) {

				if (balanceproducto.getConcepto().getNombreConcepto()
						.equals(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO_COMPROBANTE))) {
					datosServidor[0] = balanceproducto.getFecha_balanceproducto();
					datosServidor[1] = balanceproducto.getMontoBalanceproducto() + "";

				}
				if (balanceproducto.getConcepto().getNombreConcepto()
						.equals(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_CONSUMO_RESTANTE))) {
					datosServidor[2] = balanceproducto.getMontoBalanceproducto() + "";
				}
				if (balanceproducto.getConcepto().getNombreConcepto()
						.equals(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_CONCEPTO_MERMA))) {
					if (balanceproducto.getMontoBalanceproducto() == null) {
						datosServidor[3] = 0 + "";
					} else {
						datosServidor[3] = balanceproducto.getMontoBalanceproducto() + "";
					}
				}

			}

			return datosServidor;
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
	 * Método para almacenar los ajuste de componentes y movimientos asociados
	 * 
	 * @param detallesConsumoComponenteAjuste
	 * @param detallesMovimientoAjuste
	 * @param mapaPuestoTrabajoProduccion
	 * @param ajusteProducto
	 * @param ajusteProducto
	 * @param registroBalanceInicial
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 */
	private void insertarConsumoComponentesAjusteCombustible(
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste,
			Map<Long, Puestotrabajoproduccion> mapaPuestoTrabajoProduccion, Ajusteproducto ajusteProducto)
			throws ElementoNoEncontradoException, ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException {

		Estadomovimiento estadoMovimiento = EstadoMovimientoQuerier.findByNombreUniqueResult(ManejadorPropiedades
				.obtenerPropiedadPorClave(ETIQUETA_ESTADO_ACTIVA));
		String codigoTipoMovimientoSAPIngreso = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_TIPO_MOVIMIENTO_SAP_INGRESO)
				.toLowerCase();
		String codigoTipoMovimientoSAPSalida = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_TIPO_MOVIMIENTO_SAP_SALIDA)
				.toLowerCase();
		Tipomovimiento tipoMovimientoIngreso = TipoMovimientoQuerier.findByCodigoSAP(codigoTipoMovimientoSAPIngreso);
		Tipomovimiento tipoMovimientoSalida = TipoMovimientoQuerier.findByCodigoSAP(codigoTipoMovimientoSAPSalida);

		// 2. Preparo y salvo la información de los ajuste por componente
		for (int i = 0; i < detallesConsumoComponenteAjuste.size(); i++) {
			RegistroTablaConsumosPuestoTrabajoDTO consumoCompAjusteDto = detallesConsumoComponenteAjuste.get(i);
			Long codigoComponente = consumoCompAjusteDto.getCodigoComponente();
			Componente componente = ComponenteQuerier.getById(codigoComponente);

			Consumocomponenteajuste consumoComponenteAjuste = new Consumocomponenteajuste();
			Long codigoPuestoTrabajo = consumoCompAjusteDto.getCodigoPuestoTrabajo();
			Long codigo = consumoCompAjusteDto.getCodigo();
			consumoComponenteAjuste.setPuestotrabajoproduccion(mapaPuestoTrabajoProduccion.get(codigo));
			consumoComponenteAjuste.setComponente(componente);
			consumoComponenteAjuste.setDiferenciaConsumocomponenteaju(consumoCompAjusteDto.getAjusteTM());
			consumoComponenteAjuste.setPorcentajeRealConsumocomponen(consumoCompAjusteDto.getProduccionRealPorcentaje());
			consumoComponenteAjuste.setPorcentProduccConsucompajuste(consumoCompAjusteDto.getProduccionPorcentaje());
			consumoComponenteAjuste.setPorcentCarbonConsucompajuste(consumoCompAjusteDto.getPorcetanjeCarbones());
			consumoComponenteAjuste.setTmProdConsumocomponenteajus(consumoCompAjusteDto.getMontoConsumido());
			consumoComponenteAjuste.setTmRealConsumocomponenteajuste(consumoCompAjusteDto.getProduccionRealTM());
			consumoComponenteAjuste.setEditadoManualConsumocompajus(consumoCompAjusteDto.isEditadoManual());

			// 3. Se salva el ajuste por compoenente
			ConsumoComponenteAjusteQuerier.save(consumoComponenteAjuste);

			guardarMovimientoAjusteCombustible(ajusteProducto, consumoComponenteAjuste, estadoMovimiento, tipoMovimientoIngreso,
					tipoMovimientoSalida, codigoComponente, codigoPuestoTrabajo, detallesMovimientoAjuste);
			guardarMovimientoAjusteMerma(ajusteProducto, consumoComponenteAjuste, estadoMovimiento, tipoMovimientoIngreso,
					tipoMovimientoSalida, codigoComponente, codigoPuestoTrabajo, detallesMovimientoAjuste);
		}
		guardarMovimientoAjusteProducto(detallesMovimientoAjuste, mapaPuestoTrabajoProduccion);
	}

	private void guardarMovimientoAjusteCombustible(Ajusteproducto ajusteProducto,
			Consumocomponenteajuste consumoComponenteAjuste, Estadomovimiento estadoMovimiento,
			Tipomovimiento tipoMovimientoIngreso, Tipomovimiento tipoMovimientoSalida, Long codigoComponente,
			Long codigoPuestoTrabajo, List<RegistroTablaAjusteDTO> detallesMovimientoAjuste) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Tipocomponenteajuste tipomovimientoajuste = TipoComponenteAjusteQuerier.getbyNombre(ManejadorPropiedades
				.obtenerPropiedadPorClave(TIPO_COMPONENTE_HOJA_RUTA));

		// 4. Localizo el movimiento asociado al compoenente
		for (RegistroTablaAjusteDTO movAjusteDto : detallesMovimientoAjuste) {

			if (movAjusteDto.getTipoComponente().equals(ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_COMPONENTE_HOJA_RUTA))) {

				if (movAjusteDto.getCodigoComponente().longValue() == codigoComponente.longValue()
						&& movAjusteDto.getCodigoPuesto().longValue() == codigoPuestoTrabajo.longValue()) {
					Movimientoajuste movimientoAjuste = crearObjMovimientoAjuste(ajusteProducto, consumoComponenteAjuste,
							estadoMovimiento, tipoMovimientoIngreso, tipoMovimientoSalida, movAjusteDto);
					movimientoAjuste.setTipocomponenteajuste(tipomovimientoajuste);
					MovimientoAjusteQuerier.save(movimientoAjuste);
				}
			}
		}

	}

	public Double obtenerFactorHumedadVariableCalidad(Producto producto) throws LogicaException {

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

			return obtenerFactorHumedadVariableCalidadDAO(producto);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public Double obtenerFactorHumedadVariableCalidadDAO(Producto producto) throws LogicaException {

		String mensajeError = "";

		Long codigoProductoScc = producto.getCodigoSccProducto();

		if (codigoProductoScc == null) {
			// no tien codigo producto scc == no tiene humedad
			return 0d;
		}
		double factorHumedad = 0;
		Double sumaIngresoH = 0d;
		Double ingresoHxFactor = 0d;
		Double sumaIngHxFactor = 0d;
		Double consumoTMHumedas = 0d;
		Double consumoTMSecas = 0d;
		try {

			Tablakardex tablaKardex = ValorPromVariableCalidadQuerier.obtenerUltimoKardexHumedadIngreso(producto);

			if (tablaKardex == null) {
				return 0d;
			}
			List<Tablakardex> tablasKardex = TablaKardexQuerier.obtenerPorProduccionDiaria(tablaKardex.getProducciondiaria()
					.getPkCodigoProducciondiaria());

			for (Tablakardex v_tablaKardex : tablasKardex) {
				factorHumedad = obtenerFactorHumedadIngreso(v_tablaKardex);

				sumaIngresoH += v_tablaKardex.getIngresoHumedadTablakardex();
				ingresoHxFactor = v_tablaKardex.getIngresoHumedadTablakardex() * factorHumedad;
				sumaIngHxFactor += ingresoHxFactor;
			}

			factorHumedad = NumberUtil.dividir(sumaIngHxFactor, sumaIngresoH);

			if (factorHumedad == 0) {
				tablaKardex = TablaKardexQuerier.obtenerKardexUltimoConsumo(producto);
				if (tablaKardex == null) {
					return 0d;
				}
				tablasKardex = TablaKardexQuerier.obtenerPorProduccionDiaria(tablaKardex.getProducciondiaria()
						.getPkCodigoProducciondiaria());
				sumaIngresoH = 0d;
				ingresoHxFactor = 0d;
				sumaIngHxFactor = 0d;

				for (Tablakardex var_tablakardex : tablasKardex) {
					consumoTMHumedas = var_tablakardex.getConsumoHumedadTablakardex();
					consumoTMSecas = var_tablakardex.getConsumoTablakardex();
					factorHumedad = NumberUtil
							.reducirAdosDecimales(((consumoTMHumedas - consumoTMSecas) / (consumoTMHumedas)) * 100d);

					sumaIngresoH += consumoTMHumedas;
					ingresoHxFactor = consumoTMSecas * factorHumedad;
					sumaIngHxFactor += ingresoHxFactor;

				}

				factorHumedad = NumberUtil.dividir(sumaIngHxFactor, sumaIngresoH);
			}

		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}
		return factorHumedad;
	}

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

	public Boolean verificarParteDiarioCerrado(Long codigoLinea, String mesContable, Integer anioContable) throws LogicaException {

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

			Short mes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesContable).ordinal()).shortValue();
			String estadoCerrado = ManejadorPropiedades.obtenerPropiedadPorClave(ESTADONOTIFICACION_CERRADO);
			NotificacionDiariaLogicFacade notificacionDiariaLogic = new NotificacionDiariaLogic();
			Date fechaUltima = FechaUtil.getUltimoDiaMes(mes, anioContable).getTime();
			NotificacionDiariaBean notificacion = notificacionDiariaLogic.obtenerNotificacion(codigoLinea, fechaUltima);
			if (notificacion != null && notificacion.getEstadoNotificacion().getNombreEstadoNotificacion().equals(estadoCerrado)) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * @seepe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade#
	 * obtenerAjuste(java.lang.Long, java.lang.Long, java.lang.Short,
	 * java.lang.Integer, java.lang.String, java.util.Date, java.util.Date,
	 * java.lang.Long)
	 */
	public AjusteProduccionBean obtenerAjustePorCodigo(Long dodigoAjuste) throws LogicaException {

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
			Ajusteproduccion ajusteproduccion = AjusteProduccionMesQuerier.obtenerAjusteProduccionPorCodigo(dodigoAjuste);
			AjusteProduccionBean ajusteProduccionBean = beanFactory.transformarAjusteProduccion(ajusteproduccion);
			return ajusteProduccionBean;
		} catch (SesionVencidaException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {

			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public Boolean eliminarAjustePorCodigo(Long codigoAjuste, UsuarioBean usuario) throws LogicaException {

		Transaction tx = null;
		Session session = null;
		Boolean operacion = Boolean.FALSE;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Ajusteproduccion ajusteproduccion = AjusteProduccionMesQuerier.obtenerAjusteProduccionPorCodigo(codigoAjuste);
			AjusteProduccionMesQuerier.delete(ajusteproduccion);

			operacion = Boolean.TRUE;
			tx.commit();

		} catch (SesionVencidaException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} finally {

			PersistenciaFactory.closeSession(session);

		}


		return operacion;
	}

	public void validarExistenciaAjusteInicial(Short mes, Integer anno, Long codigoLineaNegocio, UsuarioBean usuario)
			throws LogicaException {

		try {
			Ajusteproduccion ajusteproduccion = AjusteProduccionQuerier.getByLineaNegocioMesYAno(mes, anno, codigoLineaNegocio);

			if (ajusteproduccion != null
					&& ajusteproduccion.getEstadoajusteproduccion().getNombreEstadoajusteproduccion().equals(ESTADO_INICIAL)) {
				this.eliminarAjustePorCodigo(ajusteproduccion.getPkCodigoAjusteproduccion(), usuario);
			}

		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMessage(), e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			throw new LogicaException(mensajeError, e);
		}

	}
}
