package pe.com.pacasmayo.sgcp.logica.notificacion;

import java.util.Date;
import java.util.ResourceBundle;

import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;

public class PropiedadesSap implements ConstantesLogicaNegocio {

	/** ruta del archivo de propiedades de configuracion */
	private static final String DIRECCION_ARCHIVO_PROPIEDADES = "conexionSap";

	/** Instancia del archivo de configuracion */
	private static ResourceBundle recurso;

	static {
		recurso = ResourceBundle.getBundle(DIRECCION_ARCHIVO_PROPIEDADES);
	}

	/**
	 * Crea una instancia de la clase y establece las propiedades del objeto con
	 * los dados como parametros.
	 */
	private PropiedadesSap() {
	}

	/**
	 * Obtiene el mensaje que se corresponde con la clave asignada en el archivo
	 * de propiedades
	 * 
	 * @param clave Clave asociada aun mensaje definida en el archivo de
	 *            propiedades
	 * @return resultado
	 */
	public static String getPropiedad(String clave) {
		String resultado = "";

		if (clave != null) {
			resultado = recurso.getString(clave);
		}

		return resultado;
	}

	public static String formatoFechaBLDATT(Date fecha) {
		String fechaString = FechaUtil.convertirDateToString(fecha);
		String[] fechaStringArray = fechaString.split(FechaUtil.SEPARADOR_CAMPOS_FECHA_APP);

		return fechaStringArray[DIA] + "-" + fechaStringArray[MES] + "-" + fechaStringArray[ANIO];
	}

	public static String formatoFechaPbudat(Date fecha) {
		String fechaString = FechaUtil.convertirDateToString(fecha);
		String[] fechaStringArray = fechaString.split(FechaUtil.SEPARADOR_CAMPOS_FECHA_APP);

		return fechaStringArray[ANIO] + fechaStringArray[MES] + fechaStringArray[DIA];
	}
}
