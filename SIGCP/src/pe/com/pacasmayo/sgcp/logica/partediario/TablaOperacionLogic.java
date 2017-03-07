package pe.com.pacasmayo.sgcp.logica.partediario;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.PuestoTrabajoProduccionDetalleBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.PuestoTrabajoProduccionGraficoBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.ReportePuestoTrabajoProduccionBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.SubReporteDetallePuestoTrabajoProduccionBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.SubReporteGraficoPuestoTrabajoProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaOperacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.mail.bean.MailBean;
import pe.com.pacasmayo.sgcp.mail.bean.MailConfig;
import pe.com.pacasmayo.sgcp.mail.util.MailUtil;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaOperacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class TablaOperacionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, ConstantesLogicaNegocio,
		TablaOperacionLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());
	private String mensajeError;
	private ParametroSistemaLogicFacade parametro;

	public TablaOperacionLogic() {
		parametro = new ParametroSistemaLogic();
	}

	
	
	
	public ReportePuestoTrabajoProduccionBean obtenerProduccionPuestotrabajoReporte(Short mes, Integer anio, Long unidad)
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

		List<PuestoTrabajoProduccionGraficoBean> lista;
		ReportePuestoTrabajoProduccionBean reporte = new ReportePuestoTrabajoProduccionBean();

		try {
			Long[] puestoTrabajo;
			Long[] procesos;
			String nombreEjeY2;
			Boolean muestraTMPH;
			String tituloReporte;
			String factor;
			String nombreFactor;
			String proceso;
			String tituloInforme;
			List<ParametroSistemaBean> parametrosGraficos = parametro
					.obtenerParametrosSistema(ConstantesParametro.REPORTE_PARTEDIARIO_PRODUCCION_GRAFICO);
			SubReporteGraficoPuestoTrabajoProduccionBean subreporte;
			Boolean muestraSuma = Boolean.FALSE;
			Unidad unidadQ = UnidadQuerier.getById(unidad);

			for (ParametroSistemaBean paramGraficos : parametrosGraficos) {
				String[] paramSistema = paramGraficos.getValor().split(",");

				String[] puestos = paramSistema[0].split(";");
				puestoTrabajo = new Long[puestos.length];
				for (int i = 0; i < puestos.length; i++) {
					puestoTrabajo[i] = Long.valueOf(puestos[i].trim());
				}

				String[] procesosstr = paramSistema[4].split(";");
				procesos = new Long[procesosstr.length];
				for (int i = 0; i < procesosstr.length; i++) {
					procesos[i] = Long.valueOf(procesosstr[i].trim());
				}

				// nombreEjeY2 = paramSistema[1];
				// muestraTMPH = Boolean.valueOf(paramSistema[2]);
				// tituloReporte = paramSistema[3];
				//
				// lista =
				// TablaOperacionQuerier.obtenerProduccionPuestoTrabajo(anio,
				// mes, unidad, puestoTrabajo, nombreEjeY2,
				// muestraTMPH);
				// reporte.setMes(FechaUtil.numeroMesANombreMes(mes));
				// reporte.setAnio(anio.toString());
				// reporte.setUnidad(unidadQ.getNombreUnidad());
				// subreporte = new
				// SubReporteGraficoPuestoTrabajoProduccionBean();
				// subreporte.setTituloSubReporte(tituloReporte);
				// subreporte.setSubReporteGraficos(lista);
				// if (subreporte.getSubReporteGraficos().size() > 0) {
				// reporte.getSubReporteGrafico().add(subreporte);
				// }
				for (int i = 0; i < puestos.length; i++) {

					Long[] puestoTrabajo2 = new Long[1];
					puestoTrabajo2[0] = Long.valueOf(puestos[i].trim());
					nombreEjeY2 = paramSistema[1];
					muestraTMPH = Boolean.valueOf(paramSistema[2]);
					tituloReporte = paramSistema[3];

					lista = TablaOperacionQuerier.obtenerProduccionPuestoTrabajo(anio, mes, unidad, puestoTrabajo2, nombreEjeY2,
							muestraTMPH, procesos);
					reporte.setMes(FechaUtil.numeroMesANombreMes(mes));
					reporte.setAnio(anio.toString());
					reporte.setUnidad(unidadQ.getNombreUnidad());
					subreporte = new SubReporteGraficoPuestoTrabajoProduccionBean();
					subreporte.setTituloSubReporte(tituloReporte);
					subreporte.setSubReporteGraficos(lista);

					lista = TablaOperacionQuerier.obtenerProduccionPuestoTrabajoHoras(anio, mes, unidad, puestoTrabajo2,
							nombreEjeY2, muestraTMPH, procesos);
					subreporte.getSubReporteGraficos().addAll(lista);
					subreporte.setSubReporteGraficosEnergia(null);

					if (subreporte.getSubReporteGraficos().size() > 0) {
						reporte.getSubReporteGrafico().add(subreporte);
					}
				}

			}

			// grafica con energia
			for (ParametroSistemaBean paramGraficos : parametrosGraficos) {
				String[] paramSistema = paramGraficos.getValor().split(",");

				String[] puestos = paramSistema[0].split(";");
				puestoTrabajo = new Long[puestos.length];
				for (int i = 0; i < puestos.length; i++) {
					puestoTrabajo[i] = Long.valueOf(puestos[i].trim());
				}
				nombreFactor = paramSistema[5];

				String[] procesosstr = paramSistema[4].split(";");
				procesos = new Long[procesosstr.length];
				for (int i = 0; i < procesosstr.length; i++) {
					procesos[i] = Long.valueOf(procesosstr[i].trim());
				}
				for (int i = 0; i < puestos.length; i++) {

					Long[] puestoTrabajo2 = new Long[1];
					puestoTrabajo2[0] = Long.valueOf(puestos[i].trim());
					nombreEjeY2 = paramSistema[1];
					muestraTMPH = Boolean.valueOf(paramSistema[2]);
					tituloReporte = paramSistema[3];

					lista = TablaOperacionQuerier.obtenerProduccionPuestoTrabajoKW(anio, mes, unidad, puestoTrabajo2[0], "TMPKW",
							muestraTMPH, nombreFactor, procesos);
					subreporte = new SubReporteGraficoPuestoTrabajoProduccionBean();
					subreporte.setTituloSubReporteEnergia(tituloReporte + " - KW");
					subreporte.setSubReporteGraficos(null);
					subreporte.setSubReporteGraficosEnergia(lista);
					// subreporte.getSubReporteGraficos().addAll(lista);

					if (subreporte.getSubReporteGraficosEnergia().size() > 0) {
						reporte.getSubReporteGrafico().add(subreporte);
					}
				}

			}

			List<ParametroSistemaBean> parametrosDetalles = parametro
					.obtenerParametrosSistema(ConstantesParametro.REPORTE_PARTEDIARIO_PRODUCCION_DETALLE);
			SubReporteDetallePuestoTrabajoProduccionBean subreportedetalle;
			for (ParametroSistemaBean paramDetalles : parametrosDetalles) {
				String[] paramSistema = paramDetalles.getValor().split(",");

				String[] puestos = paramSistema[0].split(";");
				puestoTrabajo = new Long[puestos.length];

				for (int i = 0; i < puestos.length; i++) {
					puestoTrabajo[i] = Long.valueOf(puestos[i].trim());
				}

				String[] procesosstr = paramSistema[8].split(";");
				procesos = new Long[procesosstr.length];
				for (int i = 0; i < procesosstr.length; i++) {
					procesos[i] = Long.valueOf(procesosstr[i].trim());
				}

				nombreEjeY2 = paramSistema[1];
				muestraTMPH = Boolean.valueOf(paramSistema[2]);
				muestraSuma = Boolean.valueOf(paramSistema[3]);
				tituloReporte = paramSistema[4];
				factor = paramSistema[5];
				proceso = paramSistema[6];
				tituloInforme = paramSistema[7];
				lista = TablaOperacionQuerier.obtenerProduccionPuestoTrabajo(anio, mes, unidad, puestoTrabajo, nombreEjeY2,
						muestraTMPH, procesos);
				subreportedetalle = new SubReporteDetallePuestoTrabajoProduccionBean();
				subreportedetalle.setSubReporteDetalle(obtenerReporteDetallePuestotrabajoProduccion(lista, muestraSuma));
				subreportedetalle.setTituloSubReporte(tituloReporte);
				if (subreportedetalle.getSubReporteDetalle().size() > 0) {
					reporte.getSubReporteDetalle().add(subreportedetalle);
				}

				if (!factor.trim().equals("") && !factor.trim().equals("_")) {
					if (!proceso.trim().equals("") && !proceso.trim().equals("_")) {
						List<Tablakardex> listaKardex = TablaKardexQuerier.obtenerInformeFactores(unidad, anio, mes,
								Long.valueOf(proceso));

						// subreportedetalle = new
						// SubReporteDetallePuestoTrabajoProduccionBean();
						// subreportedetalle.setSubReporteDetalle(obtenerReporteDetallePuestotrabajoProduccion(listaKardex,
						// factor));
						// subreportedetalle.setTituloSubReporte(tituloInforme);
						//
						// if (subreportedetalle.getSubReporteDetalle().size() >
						// 0) {
						// reporte.getSubReporteDetalle().add(subreportedetalle);
						// }

						subreportedetalle = new SubReporteDetallePuestoTrabajoProduccionBean();
						subreportedetalle.setSubReporteDetalleFactores(obtenerReporteDetallePuestotrabajoProduccion(listaKardex,
								factor));
						subreportedetalle.setTituloSubReporteFactores(tituloInforme);

						if (subreportedetalle.getSubReporteDetalleFactores().size() > 0) {
							reporte.getSubReporteDetalle().add(subreportedetalle);
						}
					}
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

		return reporte;
	}

	private List<PuestoTrabajoProduccionDetalleBean> obtenerReporteDetallePuestotrabajoProduccion(List<Tablakardex> listaKardex,
			String nombreCategoria) {
		try {

			List<PuestoTrabajoProduccionDetalleBean> listaDetalle = new ArrayList<PuestoTrabajoProduccionDetalleBean>();
			PuestoTrabajoProduccionDetalleBean reporteDetalle;
			Map<Integer, List<Tablakardex>> mapaDias = new HashMap<Integer, List<Tablakardex>>();

			for (Tablakardex r : listaKardex) {
				Integer dia = FechaUtil.diaFecha(r.getFechaTablakardex());

				List<Tablakardex> lista = mapaDias.get(dia);
				if (lista != null) {
					lista.add(r);
					mapaDias.put(dia, lista);
				} else {
					lista = new ArrayList<Tablakardex>();
					lista.add(r);
					mapaDias.put(dia, lista);
				}

			}
			List<String> nombreColumnasProductos = new ArrayList<String>();
			for (Tablakardex r : listaKardex) {
				String producto = r.getProducciondiaria().getOrdenproduccion().getProduccion().getProducto().getNombreProducto();
				if (!validarCadena(nombreColumnasProductos, producto)) {
					nombreColumnasProductos.add(producto);
				}

			}

			Collections.sort(nombreColumnasProductos, new Comparator<String>() {
				public int compare(String o1, String o2) {
					int compareFechas = o1.compareTo(o2);
					return compareFechas;
				}
			});

			List<Integer> dias = new ArrayList<Integer>(mapaDias.keySet());
			Collections.sort(dias, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					int compareFechas = o1.compareTo(o2);
					return compareFechas;
				}
			});
			Map<String, Tablakardex> mapaPuestosTrabajo;
			Tablakardex pu;
			List<Tablakardex> lista = null;
			int orden = 1;
			for (int i = 0; i < dias.size(); i++) {
				reporteDetalle = new PuestoTrabajoProduccionDetalleBean();
				lista = mapaDias.get(dias.get(i));
				mapaPuestosTrabajo = obtenerProductos(lista);
				orden = 1;
				for (int j = 0; j < nombreColumnasProductos.size(); j++) {

					pu = mapaPuestosTrabajo.get(nombreColumnasProductos.get(j));

					if (pu != null) {
						// if ((j + 1) % 3 == 0) {
						// asignarAtributoHorasProducidas(reporteDetalle, pu,
						// orden, (i + 1), nombreCategoria);
						//
						// }else
						if ((j + 1) % 2 == 0) {
							asignarAtributoTMPH(reporteDetalle, pu, orden, (i + 1), nombreCategoria);
							orden++;
						} else {
							asignarAtributoSigla(reporteDetalle, pu, orden, (i + 1), nombreCategoria);
						}
					} else {
						PuestoTrabajoProduccionGraficoBean pd = new PuestoTrabajoProduccionGraficoBean();
						pd.setSiglaProducto(nombreColumnasProductos.get(j));
						pd.setDia(dias.get(i));
						pd.setNombrePuestoTrabajo(nombreColumnasProductos.get(j));
						asignarAtributoTMPH(reporteDetalle, pu, orden - 1, (i + 1), nombreCategoria);
						pd.setSiglaProducto(nombreColumnasProductos.get(j));
						pd.setDia(dias.get(i));
						pd.setNombrePuestoTrabajo(nombreColumnasProductos.get(j));
						asignarAtributoSigla(reporteDetalle, pu, orden, (i + 1), nombreCategoria);

					}
				}
				listaDetalle.add(reporteDetalle);

			}
			return listaDetalle;
		} catch (Exception e) {
			// TODO Manejo de Log
			e.printStackTrace();
		}
		return new ArrayList<PuestoTrabajoProduccionDetalleBean>();
	}

	private void asignarAtributoSigla(PuestoTrabajoProduccionDetalleBean reporteDetalle, Tablakardex tablakardex, int orden,
			int dia, String nombreCategoria) {
		String nombreProducto = tablakardex.getProducciondiaria().getOrdenproduccion().getProduccion().getProducto()
				.getNombreProducto();
		try {
			BeanUtils.setProperty(reporteDetalle, "toneladasproducidas_" + orden, hallarFactor(tablakardex, nombreCategoria));
			BeanUtils.setProperty(reporteDetalle, "siglapt_" + orden, obtenerTituloFactor(nombreProducto));
			BeanUtils.setProperty(reporteDetalle, "dia_" + orden, dia);

		} catch (IllegalAccessException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		} catch (InvocationTargetException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		}

	}

	private void asignarAtributoTMPH(PuestoTrabajoProduccionDetalleBean reporteDetalle, Tablakardex tablakardex, int orden,
			int dia, String nombreCategoria) {
		String nombreProducto = tablakardex.getProducciondiaria().getOrdenproduccion().getProduccion().getProducto()
				.getNombreProducto();
		try {
			BeanUtils.setProperty(reporteDetalle, "toneladasproducidasporhora_" + orden,
					hallarFactor(tablakardex, nombreCategoria));
			BeanUtils.setProperty(reporteDetalle, "tmph_" + orden, obtenerTituloFactor(nombreProducto));
			BeanUtils.setProperty(reporteDetalle, "dia_" + orden, dia);

		} catch (IllegalAccessException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		} catch (InvocationTargetException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		}

	}

	private void asignarAtributoHorasProducidas(PuestoTrabajoProduccionDetalleBean reporteDetalle, Tablakardex tablakardex,
			int orden, int dia, String nombreCategoria) {
		String nombreProducto = tablakardex.getProducciondiaria().getOrdenproduccion().getProduccion().getProducto()
				.getNombreProducto();
		try {
			BeanUtils.setProperty(reporteDetalle, "horas_" + orden, hallarFactor(tablakardex, nombreCategoria));
			BeanUtils.setProperty(reporteDetalle, "horast_" + orden, obtenerTituloFactor(nombreProducto));
			BeanUtils.setProperty(reporteDetalle, "dia_" + orden, dia);

		} catch (IllegalAccessException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		} catch (InvocationTargetException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		}

	}

	private String obtenerTituloFactor(String nombreProducto) {
		String titulo = MessageFormat.format(ConstantesAplicacionAction.CABECERA_NOMBRE_FACTOR, new Object[] { nombreProducto });
		return titulo;
	}

	private double hallarFactor(Tablakardex tk, String strtipocategoria) {
		Double consumo = 0D;
		Double ingreso;
		Double factor = 0D;
		// produccion de cemento X
		ingreso = tk.getIngresoTablakardex();
		Iterator<Consumocomponente> itComponente = tk.getConsumocomponentes().iterator();
		Consumocomponente consumocomponente;
		while (itComponente.hasNext()) {
			consumocomponente = itComponente.next();
			Tipocategoriaproducto tipocategoria = consumocomponente.getComponente().getProductoByFkCodigoProductoComponente()
					.getTipocategoriaproducto();
			if (tipocategoria != null && tipocategoria.getNombreTipocategoriaproducto().equals(strtipocategoria)) {
				consumo += consumocomponente.getConsumoConsumocomponente();
			}
		}
		if (consumo > 0) {
			// factor = ingreso / consumo;
			factor = consumo / ingreso;
		}

		return factor;
	}

	private Map<String, Tablakardex> obtenerProductos(List<Tablakardex> lista) {
		Map<String, Tablakardex> mapaPuestosTrabajo = new HashMap<String, Tablakardex>();
		Tablakardex repor;
		for (Tablakardex pt : lista) {
			String producto = pt.getProducciondiaria().getOrdenproduccion().getProduccion().getProducto().getNombreProducto();
			repor = mapaPuestosTrabajo.get(producto);
			if (repor == null) {
				mapaPuestosTrabajo.put(producto, pt);
			}
		}

		return mapaPuestosTrabajo;
	}

	private boolean validarCadena(List<String> nombreColumnasPuestosTrabajos, String cadena) {

		for (String string : nombreColumnasPuestosTrabajos) {
			if (string.equals(cadena)) {
				return true;
			}
		}
		return false;
	}

	private List<PuestoTrabajoProduccionDetalleBean> obtenerReporteDetallePuestotrabajoProduccion(
			List<PuestoTrabajoProduccionGraficoBean> reporteDetalleProduccion, Boolean muestraSuma) {
		try {

			List<PuestoTrabajoProduccionDetalleBean> listaDetalle = new ArrayList<PuestoTrabajoProduccionDetalleBean>();
			PuestoTrabajoProduccionDetalleBean reporteDetalle;
			Map<Integer, List<PuestoTrabajoProduccionGraficoBean>> mapaDias = new HashMap<Integer, List<PuestoTrabajoProduccionGraficoBean>>();

			for (PuestoTrabajoProduccionGraficoBean r : reporteDetalleProduccion) {

				List<PuestoTrabajoProduccionGraficoBean> lista = mapaDias.get(r.getDia());
				if (lista != null) {
					lista.add(r);
					mapaDias.put(r.getDia(), lista);
				} else {
					lista = new ArrayList<PuestoTrabajoProduccionGraficoBean>();
					lista.add(r);
					mapaDias.put(r.getDia(), lista);
				}

			}
			List<String> nombreColumnasPuestosTrabajos = new ArrayList<String>();
			for (PuestoTrabajoProduccionGraficoBean r : reporteDetalleProduccion) {
				if (!validarCadena(nombreColumnasPuestosTrabajos, r.getSiglaProducto())) {
					nombreColumnasPuestosTrabajos.add(r.getSiglaProducto());
				}

			}

			Collections.sort(nombreColumnasPuestosTrabajos, new Comparator<String>() {
				public int compare(String o1, String o2) {
					int compareFechas = o1.compareTo(o2);
					return compareFechas;
				}
			});

			List<Integer> dias = new ArrayList<Integer>(mapaDias.keySet());
			Collections.sort(dias, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					int compareFechas = o1.compareTo(o2);
					return compareFechas;
				}
			});
			Map<String, PuestoTrabajoProduccionGraficoBean> mapaPuestosTrabajo;
			PuestoTrabajoProduccionGraficoBean pu;
			List<PuestoTrabajoProduccionGraficoBean> lista = null;
			Double sumaSaldoFinal = 0d;
			for (int i = 0; i < dias.size(); i++) {
				reporteDetalle = new PuestoTrabajoProduccionDetalleBean();
				lista = mapaDias.get(dias.get(i));
				mapaPuestosTrabajo = obtenerPuestosTrabajos(lista);
				sumaSaldoFinal = 0d;
				for (int j = 0; j < nombreColumnasPuestosTrabajos.size(); j++) {

					pu = mapaPuestosTrabajo.get(nombreColumnasPuestosTrabajos.get(j));

					if (pu != null) {
						sumaSaldoFinal += pu.getToneladasproducidas();
						asignarAtributo(reporteDetalle, pu, (j + 1));
					} else {

						PuestoTrabajoProduccionGraficoBean pd = new PuestoTrabajoProduccionGraficoBean();
						pd.setSiglaProducto(nombreColumnasPuestosTrabajos.get(j));
						pd.setDia(dias.get(i));
						pd.setNombrePuestoTrabajo(nombreColumnasPuestosTrabajos.get(j));
						asignarAtributo(reporteDetalle, pd, (j + 1));
					}
				}
				if (muestraSuma) {
					PuestoTrabajoProduccionGraficoBean reporte = new PuestoTrabajoProduccionGraficoBean();
					reporte.setToneladasproducidas(sumaSaldoFinal);
					asignarAtributoSuma(reporteDetalle, reporte, nombreColumnasPuestosTrabajos.size() + 1);
				}
				listaDetalle.add(reporteDetalle);

			}
			return listaDetalle;
		} catch (Exception e) {
			// TODO Manejo de Log
			e.printStackTrace();
		}
		return new ArrayList<PuestoTrabajoProduccionDetalleBean>();
	}

	private Map<String, PuestoTrabajoProduccionGraficoBean> obtenerPuestosTrabajos(List<PuestoTrabajoProduccionGraficoBean> lista) {
		Map<String, PuestoTrabajoProduccionGraficoBean> mapaPuestosTrabajo = new HashMap<String, PuestoTrabajoProduccionGraficoBean>();
		PuestoTrabajoProduccionGraficoBean repor;
		for (PuestoTrabajoProduccionGraficoBean pt : lista) {
			repor = mapaPuestosTrabajo.get(pt.getSiglaProducto());
			if (repor == null) {
				mapaPuestosTrabajo.put(pt.getSiglaProducto(), pt);
			}
		}

		return mapaPuestosTrabajo;
	}

	private void asignarAtributoSuma(PuestoTrabajoProduccionDetalleBean reporteDetalle,
			PuestoTrabajoProduccionGraficoBean reporte, int orden) {

		try {

			BeanUtils.setProperty(reporteDetalle, "siglapt_" + orden, "Total");
			BeanUtils.setProperty(reporteDetalle, "toneladasproducidas_" + orden, reporte.getToneladasproducidas());

		} catch (IllegalAccessException e) {
			// TODO Manejo Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Manejo Log
			e.printStackTrace();
		}

	}

	private void asignarAtributo(PuestoTrabajoProduccionDetalleBean reporteDetalle, PuestoTrabajoProduccionGraficoBean reporte,
			int orden) {

		try {
			BeanUtils.setProperty(reporteDetalle, "toneladasproducidas_" + orden, reporte.getToneladasproducidas());
			BeanUtils.setProperty(reporteDetalle, "toneladasproducidasporhora_" + orden, reporte.getToneladasproducidasporhora());
			BeanUtils.setProperty(reporteDetalle, "siglapt_" + orden, reporte.getSiglaProducto());
			BeanUtils.setProperty(reporteDetalle, "nombrePuestoTrabajo_" + orden, reporte.getNombrePuestoTrabajo());
			BeanUtils.setProperty(reporteDetalle, "dia_" + orden, reporte.getDia());
			BeanUtils.setProperty(reporteDetalle, "horas_" + orden, reporte.getHoraProduccion());

			// Descripcion Cabecera
			BeanUtils.setProperty(reporteDetalle, "tmph_" + orden, ConstantesAplicacionAction.CABECERA_TMPH);
			BeanUtils.setProperty(reporteDetalle, "horast_" + orden, ConstantesAplicacionAction.CABECERA_HR);

		} catch (IllegalAccessException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		} catch (InvocationTargetException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_VARIABLE_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
		}

	}

	private String obtenerParametro(String parametroStr) throws LogicaException {
		ParametroSistemaBean parametroSistemaBean;
		try {
			parametroSistemaBean = parametro.obtenerParametroSistema(parametroStr);
			if (parametroSistemaBean != null && parametroSistemaBean.getValor() != null
					&& !parametroSistemaBean.getValor().toString().equals("")) {
				return parametroSistemaBean.getValor();
			}
		} catch (LogicaException e) {
			mensajeError = "No se pudo obtener el parámetro con el nombre " + parametroStr;
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		}

		return null;

	}

	public void enviarCorreo(Short mes, Integer anio, Long unidad, String direccionReporte) throws LogicaException {

		String correoParametroSistema = obtenerParametro(ConstantesParametro.CORREO_ENVIO_EMAIL);
		if (correoParametroSistema == null) {
			mensajeError = "No se pudo obtener el valor parámetro con el nombre " + ConstantesParametro.CORREO_ENVIO_EMAIL;
			throw new LogicaException(mensajeError);
		}
		String passwordParametroSistema = obtenerParametro(ConstantesParametro.PASSWORD_ENVIO_EMAIL);
		if (passwordParametroSistema == null) {
			mensajeError = "No se pudo obtener el valor parámetro con el nombre " + ConstantesParametro.PASSWORD_ENVIO_EMAIL;
			throw new LogicaException(mensajeError);
		}
		String timeOutParametroSistema = obtenerParametro(ConstantesParametro.TIMEOUT_ENVIO_EMAIL);

		if (timeOutParametroSistema == null) {
			mensajeError = "No se pudo obtener el valor parámetro con el nombre " + ConstantesParametro.TIMEOUT_ENVIO_EMAIL;
			throw new LogicaException(mensajeError);
		}

		String transportHostParametroSistema = obtenerParametro(ConstantesParametro.TRANSPORT_HOST_ENVIO_EMAIL);

		if (transportHostParametroSistema == null) {
			mensajeError = "No se pudo obtener el valor parámetro con el nombre "
					+ ConstantesParametro.TRANSPORT_HOST_ENVIO_EMAIL;
			throw new LogicaException(mensajeError);
		}

		String mailToParametroSistema = obtenerParametro(ConstantesParametro.MAIL_TO_ENVIO_EMAIL);

		if (mailToParametroSistema == null) {
			mensajeError = "No se pudo obtener el valor parámetro con el nombre " + ConstantesParametro.MAIL_TO_ENVIO_EMAIL;
			throw new LogicaException(mensajeError);
		}
		try {

			MailBean mailBean = null;

			mailBean = new MailBean();
			MailConfig mailCfg = new MailConfig();

			mailCfg.setUser(correoParametroSistema);
			mailCfg.setPswd(passwordParametroSistema);
			mailCfg.setTimeout(timeOutParametroSistema);
			mailCfg.setConnectionTimeout(timeOutParametroSistema);
			mailCfg.setTransportHost(transportHostParametroSistema);

			mailCfg.setMailTransportProtocol("mail.transport.protocol");
			mailCfg.setTransportProtocol("SMTP");
			mailCfg.setMailTransportHost("mail.smtp.host");
			mailCfg.setMailTransportAuth("mail.smtp.auth");
			mailCfg.setAuthorization("true");
			mailCfg.setContentType("text/html");
			mailCfg.setMailConnectionTimeout("mail.smtp.connectiontimeout");
			mailCfg.setMailTimeout("mail.smtp.timeout");

			mailBean.setFrom(correoParametroSistema);

			mailBean.setTo(mailToParametroSistema);
			mailBean.setToCC(null);
			mailBean.setToBCC(null);

			mailBean.setSubject("Envío reporte producción diaria " + FechaUtil.convertirDateToString(new Date()));
			mailBean.setText("Se ajunta el reporte producción diaria del mes " + FechaUtil.numeroMesANombreMes(mes) + " del año "
					+ anio);

			ReportePuestoTrabajoProduccionBean reporte = this.obtenerProduccionPuestotrabajoReporte(mes, anio, unidad);

			ByteArrayOutputStream bytes = this.generarReporteJasperPDF(reporte, direccionReporte);

			DataSource ds;
			ds = new ByteArrayDataSource(bytes.toByteArray(), "application/pdf");
			MailUtil.send(mailBean, mailCfg, ds, "reporteProduccionDiaria_envioEmail.pdf");
			logger.info("fin Test Mailing");

		} catch (Exception e) {
			mensajeError = "Se produjo un error al enviar el correo.";
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		}
	}

	public ByteArrayOutputStream generarReporteJasperPDF(ReportePuestoTrabajoProduccionBean reporte, String direccionReporte) {
		ByteArrayOutputStream baos = null;
		try {

			List<ReportePuestoTrabajoProduccionBean> reporteb = new ArrayList<ReportePuestoTrabajoProduccionBean>();
			reporteb.add(reporte);
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(reporteb);

			String jrxmlPath = direccionReporte;

			String path = jrxmlPath + "\\reporteParteDiarioPuestoTrabajoProduccion.jasper";

			JasperPrint jasperPrint = JasperFillManager.fillReport(path, null, datasource);

			baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return baos;

	}

}
