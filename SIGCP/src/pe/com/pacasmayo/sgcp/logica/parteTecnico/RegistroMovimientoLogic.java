package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.bean.AprobarAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.notificacion.ConstantesSap;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaconsumo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaConsumoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import util.ConexionSAP;

public class RegistroMovimientoLogic implements ConstantesMensajeAplicacion, ConstantesSap {

	private static final String orden = "";

	private static String texdet = "";
	private String delimitador = "\\u00AC";
	private static String mensajeError = "";

	private Logger logger = Logger.getLogger(RegistroMovimientoLogic.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMConMatPrimaLogicFacade
	 * #ingresarDatosPorLote(java.lang.String, java.util.List)
	 */
	public ResultadoBeanImpl ingresarMovimientosPorLote(String fechaP_BUDAT,
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean, String fechaBLDATT, Date fecha)
			throws LogicaException {
		ConexionSAP conexionSAP = null;

		try {
			
			//Establecer conexion a SAP , los parametros son cargados a partir de un archivo de configuracion
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);

			conexionSAP.RegistrarRFC(RFC_CONSUMO);
			conexionSAP.CreaTabla("GT_MSEG");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, "P_BUKRS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, "P_WERKS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_ALMACEN, "P_LGORT");
			conexionSAP.IngresarDatosInput(fechaP_BUDAT, FECHA_CONTABILIZACION_CAMPO_CONSUMO);

			int fila = 1;

			NumberFormat decimalFormat = NumberFormat.getInstance(Locale.UK);

			AprobarAjusteProduccionBean aprobarAjusteProduccionBean = null;
			String tipomovimiento = "";

			for (int i = 0; i < listAprobarAjusteProduccionBean.size(); i++) {
				aprobarAjusteProduccionBean = listAprobarAjusteProduccionBean.get(i);

				boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(aprobarAjusteProduccionBean
						.getCodigoProductoComponente());
				
				//Valida si el registro es una materia prima.
				if (!esMateriaPrima) {
					continue;
				}

				if (aprobarAjusteProduccionBean.getSumaTmRealConsumoComponenteAjuste() == 0D) {
					continue;
				}

				//Determina el tipo de movimiento segun la cantidad 
				if (aprobarAjusteProduccionBean.getSumaTmRealConsumoComponenteAjuste() > 0d) {
					tipomovimiento = TIPO_MOVIMIENTO_CONSUMO; // 201 salida
				} else {
					aprobarAjusteProduccionBean.setSumaTmRealConsumoComponenteAjuste(Math.abs(aprobarAjusteProduccionBean
							.getSumaTmRealConsumoComponenteAjuste()));
					tipomovimiento = TIPO_MOVIMIENTO_INGRESO; // 202 ingrEso
				}

				String material = aprobarAjusteProduccionBean.getCodigoProductoSapComponente();
				//Descuenta la humedad
				Double cantidadA = descontarHumedadPonderada(aprobarAjusteProduccionBean, fecha);

				String cantidadAjustada = decimalFormat.format(cantidadA).replace(",", "").trim();
				
				//Obtiene el centro de costo
				String centroCosto = obtenerCodigoCentroCosto(aprobarAjusteProduccionBean.getCodigoProductoAjusteProducto(),
						aprobarAjusteProduccionBean.getCodigoProductoComponente());
				if (centroCosto == null) {
					continue;
				}

				
				//Ingresa los datos a la tabla

				conexionSAP.IngresarDatoTabla(fechaBLDATT, "BLDATT", fila);
				conexionSAP.IngresarDatoTabla(TEXCAB, "BKTXT", fila);
				logger.info(fechaBLDATT + " _envio _" + centroCosto + "_ " + material + "_" + cantidadAjustada + "_"
						+ aprobarAjusteProduccionBean.getNombreProductoComponente() + "_"
						+ aprobarAjusteProduccionBean.getNombreProductoAjusteProducto() + "  TipoMovimiento: " + tipomovimiento);
				conexionSAP.IngresarDatoTabla(tipomovimiento, "BWART", fila);
				conexionSAP.IngresarDatoTabla(centroCosto, "KOSTL", fila);
				conexionSAP.IngresarDatoTabla(orden, "AUFNR", fila);
				conexionSAP.IngresarDatoTabla(material, "MATNR", fila);
				conexionSAP.IngresarDatoTabla(cantidadAjustada, "MENGE", fila); //
				conexionSAP.IngresarDatoTabla(DESTINO_CPSAA_MATPRO, "WEMPF", fila);
				conexionSAP.IngresarDatoTabla(texdet, "SGTXT", fila);

				fila++;
			}

			//Envia los datos a SAP
			conexionSAP.EjecutarRFC();
			conexionSAP.CreaTabla("GT_LOG");
			ArrayList<String> Resultado = conexionSAP.ObtenerDatosTabla();
			System.out.println((new StringBuilder()).append("---->").append(Resultado).toString());
			//Transformo el resultado de SAP a un objeto del SGCP
			return enviarResultadoOperacionRFC(Resultado, " Envio Consumos");
		} catch (Exception e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (conexionSAP != null) {
				conexionSAP.DesconectarSAP();
			}
		}
	}

	AjusteProduccionMesLogic ajusteProduccionMesLogic = new AjusteProduccionMesLogic();

	private Double descontarHumedadPonderada(AprobarAjusteProduccionBean aprobarAjusteProduccionBean, Date fecha) {
		// descuento humedad ya sea de ingreso o de consumo
		// valido humedad de ingreso
		Produccion produccion = ProductoVariableCalidadQuerier
				.obtenerProductoVariableCalidadPorProducto(aprobarAjusteProduccionBean.getCodigoProductoComponente());
		double valorHumedo = aprobarAjusteProduccionBean.getSumaTmRealConsumoComponenteAjuste();

		Double fHum = 0d;
		// calculo humedad ingreso
		if (produccion != null) {

			try {
				fHum = ajusteProduccionMesLogic.obtenerFactorHumPonderadoDAO(produccion.getProducto(), fecha);
			} catch (LogicaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			valorHumedo = valorHumedo / (1 - fHum / 100);

			if (fHum == 0D) {
				fHum = obtenerFactorHumedadConsumo(aprobarAjusteProduccionBean.getCodigoProductoComponente(), fecha);
				valorHumedo = valorHumedo / (1 - fHum / 100);
			}
			System.out.println("FAC1 . " + fHum);
		} else {
			fHum = obtenerFactorHumedadConsumo(aprobarAjusteProduccionBean.getCodigoProductoComponente(), fecha);
			valorHumedo = valorHumedo / (1 - fHum / 100);
			System.out.println("FAC2 . " + fHum);
		}

		return valorHumedo;
	}

	public Double obtenerFactorHumedadConsumo(Long codigoComponente, Date fecha) {
		Double fHum = 0D;
		try {

			List<Produccion> producci = ProduccionQuerier.getByProducto(codigoComponente);
			fHum = ajusteProduccionMesLogic.obtenerFactorHumPonderadoConsumoDAO(producci.get(0).getProducto(), fecha);
		} catch (AplicacionException e) {
			e.printStackTrace();
		}
		return fHum;
	}

	private String obtenerCodigoCentroCosto(Long codigoProductoAjusteProducto, Long codigoProductoComponente)
			throws LogicaException {
		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = PlantillaConsumoQuerier.obtenerPlantillaConsumo(codigoProductoAjusteProducto,
					codigoProductoComponente);
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMensaje());
		}
		if (plantillaConsumo == null) {
			return null;
		}
		return plantillaConsumo.getObjetocosto().getCodigoSapObjetocosto();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMConMatPrimaLogicFacade
	 * #ingresarDatosPorLote(java.lang.String, java.util.List)
	 */
	public ResultadoBeanImpl ingresarMovimientosCombustibleConsumo(String fechaP_BUDAT,
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean, String fechaBLDATT) throws LogicaException {
		ConexionSAP conexionSAP = null;
		
		try {
			
			//Establece conexion con SAP . los parametros son cargados a partir de un archivo de configuracion
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);

			conexionSAP.RegistrarRFC(RFC_CONSUMO);
			conexionSAP.CreaTabla("GT_MSEG");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, "P_BUKRS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, "P_WERKS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_ALMACEN, "P_LGORT");
			conexionSAP.IngresarDatosInput(fechaP_BUDAT, FECHA_CONTABILIZACION_CAMPO_CONSUMO);

			int fila = 1;

			NumberFormat decimalFormat = NumberFormat.getInstance(Locale.UK);

			AprobarAjusteProduccionBean aprobarAjusteProduccionBean = null;
			String tipomovimiento = "";

			for (int i = 0; i < listAprobarAjusteProduccionBean.size(); i++) {
				aprobarAjusteProduccionBean = listAprobarAjusteProduccionBean.get(i);
				boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(aprobarAjusteProduccionBean
						.getCodigoProductoAjusteProducto());
				//Determina si es materia prima
				if (!esMateriaPrima) {
					continue;
				}
				//Obtiene el centro de costo segun el puesto de trabajo
				String centroCosto = obtenerCodigoCentroCostoProductoPuestoTrabajo(
						aprobarAjusteProduccionBean.getCodigoProductoAjusteProducto(),
						aprobarAjusteProduccionBean.getCodigoPuestoTrabajo());
				if (centroCosto == null) {
					continue;
				}
				String material = aprobarAjusteProduccionBean.getCodigoProductoSapAjusteProducto();

				if (aprobarAjusteProduccionBean.getSumaTmRealConsumoComponenteAjuste() != 0D) {
					
					//Valida el tipo de movimiento
					if (aprobarAjusteProduccionBean.getSumaTmRealConsumoComponenteAjuste() > 0d) {
						tipomovimiento = TIPO_MOVIMIENTO_CONSUMO; // 202
						// ingreso
					} else {
						aprobarAjusteProduccionBean.setSumaTmRealConsumoComponenteAjuste(Math.abs(aprobarAjusteProduccionBean
								.getSumaTmRealConsumoComponenteAjuste()));
						tipomovimiento = TIPO_MOVIMIENTO_INGRESO; // 201
						// salida
					}
					Double cantidadA = aprobarAjusteProduccionBean.getSumaTmRealConsumoComponenteAjuste();
					String cantidadAjustada = decimalFormat.format(cantidadA).replace(",", "").trim();
					logger.info(fechaBLDATT + " _envio _" + centroCosto + "_ " + material + "_" + cantidadAjustada + "_"
							+ aprobarAjusteProduccionBean.getNombreProductoComponente() + "_"
							+ aprobarAjusteProduccionBean.getNombreProductoAjusteProducto() + "  TipoMovimiento: "
							+ tipomovimiento);
					
					//Carga datos para envio a SAP
					conexionSAP.IngresarDatoTabla(fechaBLDATT, "BLDATT", fila);
					conexionSAP.IngresarDatoTabla(TEXCAB, "BKTXT", fila);
					conexionSAP.IngresarDatoTabla(tipomovimiento, "BWART", fila);
					conexionSAP.IngresarDatoTabla(centroCosto, "KOSTL", fila);
					conexionSAP.IngresarDatoTabla(orden, "AUFNR", fila);
					conexionSAP.IngresarDatoTabla(material, "MATNR", fila);
					conexionSAP.IngresarDatoTabla(cantidadAjustada, "MENGE", fila); //
					conexionSAP.IngresarDatoTabla(DESTINO_CPSAA_MATPRO, "WEMPF", fila);
					conexionSAP.IngresarDatoTabla(texdet, "SGTXT", fila);
					fila++;

				}

			}
			//Realiza los envios a SAP
			conexionSAP.EjecutarRFC();
			conexionSAP.CreaTabla("GT_LOG");
			ArrayList<String> Resultado = conexionSAP.ObtenerDatosTabla();
			System.out.println((new StringBuilder()).append("---->").append(Resultado).toString());
			//Transformo el resultado de SAP a un objeto del SGCP
			return enviarResultadoOperacionRFC(Resultado, "Consumo Combustible");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (conexionSAP != null) {
				conexionSAP.DesconectarSAP();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMConMatPrimaLogicFacade
	 * #ingresarDatosPorLote(java.lang.String, java.util.List)
	 */
	public ResultadoBeanImpl ingresarMovimientosCombustibleMermas(String fechaP_BUDAT,
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean, String fechaBLDATT) throws LogicaException {
		ConexionSAP conexionSAP = null;
		logger.info("Envio Mermas ingresarMovimientosCombustibleMermas");
		try {
			//Establece conexion con SAP . los parametros son cargados a partir de un archivo de configuracion
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);

			conexionSAP.RegistrarRFC(RFC_CONSUMO);
			conexionSAP.CreaTabla("GT_MSEG");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, "P_BUKRS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, "P_WERKS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_ALMACEN, "P_LGORT");
			conexionSAP.IngresarDatosInput(fechaP_BUDAT, FECHA_CONTABILIZACION_CAMPO_CONSUMO);

			int fila = 1;

			NumberFormat decimalFormat = NumberFormat.getInstance(Locale.UK);

			AprobarAjusteProduccionBean aprobarAjusteProduccionBean = null;
			String tipomovimiento = "";

			for (int i = 0; i < listAprobarAjusteProduccionBean.size(); i++) {
				aprobarAjusteProduccionBean = listAprobarAjusteProduccionBean.get(i);
				boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(aprobarAjusteProduccionBean
						.getCodigoProductoAjusteProducto());
				//Valida si es materia prima
				if (!esMateriaPrima) {
					continue;
				}
				//Obtiene el objeto costo segun el puesto de trabajo
				String centroCosto = obtenerCodigoCentroCostoProductoPuestoTrabajo(
						aprobarAjusteProduccionBean.getCodigoProductoAjusteProducto(),
						aprobarAjusteProduccionBean.getCodigoPuestoTrabajo());
				if (centroCosto == null) {
					continue;
				}
				String material = aprobarAjusteProduccionBean.getCodigoProductoSapAjusteProducto().trim();

				if (aprobarAjusteProduccionBean.getSumaMermas() == 0D) {
					continue;
				}
				
				//Determina el tipo de movimiento
				if (aprobarAjusteProduccionBean.getSumaMermas() > 0d) {
					tipomovimiento = TIPO_MOVIMIENTO_CONSUMO; // 202
					// ingreso
				} else {
					aprobarAjusteProduccionBean.setSumaMermas(Math.abs(aprobarAjusteProduccionBean.getSumaMermas()));
					tipomovimiento = TIPO_MOVIMIENTO_INGRESO; // 201
					// salida
				}

				Double cantidadMerma = aprobarAjusteProduccionBean.getSumaMermas();

				String cantidadMermaStr = decimalFormat.format(cantidadMerma).replace(",", "").trim();

				logger.info(tipomovimiento + "_envio _" + centroCosto + "_" + orden + "_" + material + "_" + cantidadMermaStr
						+ "_" + DESTINO_CPSAA_MATPRO + "_TipoMovimiento:_" + texdet);
				
				//Carga los registros a SAP
				conexionSAP.IngresarDatoTabla(fechaBLDATT, "BLDATT", fila);
				conexionSAP.IngresarDatoTabla(TEXCAB, "BKTXT", fila);
				conexionSAP.IngresarDatoTabla(tipomovimiento, "BWART", fila);
				conexionSAP.IngresarDatoTabla(centroCosto, "KOSTL", fila);
				conexionSAP.IngresarDatoTabla(orden, "AUFNR", fila);
				conexionSAP.IngresarDatoTabla(material, "MATNR", fila);
				conexionSAP.IngresarDatoTabla(cantidadMermaStr, "MENGE", fila); //
				conexionSAP.IngresarDatoTabla(DESTINO_CPSAA_MATPRO, "WEMPF", fila);
				conexionSAP.IngresarDatoTabla(texdet, "SGTXT", fila);

				fila++;

			}
			//Envia los registros a SAP
			conexionSAP.EjecutarRFC();
			conexionSAP.CreaTabla("GT_LOG");
			ArrayList<String> Resultado = conexionSAP.ObtenerDatosTabla();
			System.out.println((new StringBuilder()).append("---->").append(Resultado).toString());
			//Transformo el resultado de SAP a un objeto del SGCP
			return enviarResultadoOperacionRFC(Resultado, "Merma Combustibles");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (conexionSAP != null) {
				conexionSAP.DesconectarSAP();
			}
		}
	}

	private String obtenerCodigoCentroCostoProductoPuestoTrabajo(Long codigoProductoAjusteProducto, Long codigoPuestoTrabajo)
			throws LogicaException {
		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = PlantillaConsumoQuerier.obtenerPlantillaConsumoByProductoPuestoTrabajo(
					codigoProductoAjusteProducto, codigoPuestoTrabajo);
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMensaje());
		}
		if (plantillaConsumo == null) {
			return null;
		}
		return plantillaConsumo.getObjetocosto().getCodigoSapObjetocosto().trim();
	}

	private ResultadoBeanImpl enviarResultadoOperacionRFC(ArrayList<String> mensajesSAP, String nombreLog) {
		String cadenaSeparacion = "  ";
		ResultadoBeanImpl resultadoBean = new ResultadoBeanImpl();
		String mensajesContatenados = "";
		mensajesContatenados += "LOG : " + nombreLog + "\n";
		Boolean operacionExitosa = Boolean.TRUE;
		if (mensajesSAP != null && mensajesSAP.size() > 0) {

			for (String mensajeSAP : mensajesSAP) {

				String cadenaMensaje = mensajeSAP;

				String[] arrSplitCadena = cadenaMensaje.split(delimitador);

				if (!(cadenaMensaje.contains(procesoRFCCorrecto) || cadenaMensaje.contains(procesoRFCIncorrecto))) {
					mensajesContatenados += arrSplitCadena[7] + "\n";
				} else {
					mensajesContatenados += arrSplitCadena[2] + cadenaSeparacion + arrSplitCadena[3] + cadenaSeparacion
							+ arrSplitCadena[5] + cadenaSeparacion + arrSplitCadena[6] + cadenaSeparacion + arrSplitCadena[7]
							+ cadenaSeparacion + arrSplitCadena[9] + cadenaSeparacion + arrSplitCadena[10] + "\n";

					if (operacionExitosa) {
						if (arrSplitCadena[10].equals(procesoRFCCorrecto)) {
							operacionExitosa = Boolean.TRUE;
						} else {
							operacionExitosa = Boolean.FALSE;
						}

					}
				}

			}

		}
		resultadoBean.setExitoOperacion(operacionExitosa);
		resultadoBean.setMensajeOperacion(mensajesContatenados);
		return resultadoBean;
	}
}
