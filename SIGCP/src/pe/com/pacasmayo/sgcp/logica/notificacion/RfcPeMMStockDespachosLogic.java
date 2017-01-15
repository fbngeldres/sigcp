package pe.com.pacasmayo.sgcp.logica.notificacion;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.RfcPeMMStockDespachosLogicFacade;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import util.ConexionSAP;

public class RfcPeMMStockDespachosLogic implements ConstantesMensajeAplicacion, RfcPeMMStockDespachosLogicFacade, ConstantesSap {

	public static final String NOMBRE_RFC_DESPACHOS = "Despachos";
	private static String mensajeError = "";

	/** Logger Instance */
	protected static Logger logger = Logger.getLogger(RfcPeMMStockDespachosLogic.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMStockDespachosLogicFacade
	 * #obtenerDatosTabla(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<String> obtenerDatosTabla(String fecha) throws LogicaException {

		ArrayList<String> datos = null;
		ConexionSAP conexionSAP = null;
		try {
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);
			conexionSAP.RegistrarRFC(RFC_DESPACHOS);

			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, SOCIEDAD_CAMPO);
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, CENTRO_CAMPO);
			conexionSAP.IngresarDatosInput(fecha, FECHA_CAMPO);

			conexionSAP.EjecutarRFC();
			conexionSAP.CreaTabla(TABLA_SALIDA_IT_RESULT);

			datos = conexionSAP.ObtenerDatosTabla();
		} catch (Exception e) {
			String errorBase = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			mensajeError = MessageFormat.format(errorBase, new Object[] { NOMBRE_RFC_DESPACHOS });
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (conexionSAP != null) {
				conexionSAP.DesconectarSAP();
			}
		}

		return datos;
	}

	public List<String> obtenerDatosTablaPorRangoFechas(String fechaInicial, String fechaFinal) throws LogicaException {

		ArrayList<String> datos = null;
		ConexionSAP conexionSAP = null;
		try {
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);
			conexionSAP.RegistrarRFC(RFC_DESPACHOS2);

			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, SOCIEDAD_CAMPO);
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, CENTRO_CAMPO);
			conexionSAP.IngresarDatosInput(fechaInicial, FECHA_CAMPO);
			conexionSAP.IngresarDatosInput(fechaFinal, FECHA_FIN_CAMPO);

			conexionSAP.EjecutarRFC();
			conexionSAP.CreaTabla(TABLA_SALIDA_IT_RESULT);

			datos = conexionSAP.ObtenerDatosTabla();
		} catch (Exception e) {
			String errorBase = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			mensajeError = MessageFormat.format(errorBase, new Object[] { NOMBRE_RFC_DESPACHOS });
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (conexionSAP != null) {
				conexionSAP.DesconectarSAP();
			}
		}

		return datos;
	}
}
