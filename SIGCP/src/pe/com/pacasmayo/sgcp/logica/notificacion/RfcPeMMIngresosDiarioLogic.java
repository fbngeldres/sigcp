package pe.com.pacasmayo.sgcp.logica.notificacion;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.RfcPeMMIngresosDiarioLogicFacade;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import util.ConexionSAP;

public class RfcPeMMIngresosDiarioLogic implements ConstantesMensajeAplicacion, RfcPeMMIngresosDiarioLogicFacade, ConstantesSap {

	private static String mensajeError = "";
	public static final String NOMBRE_RFC_INGRESOS = "Ingresos";

	/** Logger Instance */
	protected static Logger logger = Logger.getLogger(RfcPeMMIngresosDiarioLogic.class);

	@SuppressWarnings("unchecked")
	public List<String> obtenerDatosTabla(String fechaContabilizacion) throws LogicaException {
		ConexionSAP conexionSAP = null;
		List<String> datos = null;
		String errorBase = "";

		try {
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);
			conexionSAP.RegistrarRFC(RFC_INGRESO);
			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, SOCIEDAD_CAMPO);
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, CENTRO_CAMPO);
			conexionSAP.IngresarDatosInput(CODIGO_SAP_ALMACEN, ALMACEN_CAMPO);
			conexionSAP.IngresarDatosInput(fechaContabilizacion, FECHA_CONTABILIZACION_CAMPO);
			conexionSAP.EjecutarRFC();
			conexionSAP.CreaTabla(TABLA_SALIDA_TI_MATPRIMA);
			datos = conexionSAP.ObtenerDatosTabla();
		} catch (Exception e) {
			errorBase = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			mensajeError = MessageFormat.format(errorBase, new Object[] { NOMBRE_RFC_INGRESOS });
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} catch (NoClassDefFoundError e) {
			errorBase = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			mensajeError = MessageFormat.format(errorBase, new Object[] { NOMBRE_RFC_INGRESOS });
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
