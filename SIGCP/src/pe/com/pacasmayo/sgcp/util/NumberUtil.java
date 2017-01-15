package pe.com.pacasmayo.sgcp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

	public static Double convertirStringToDouble(String strobj) {
		if (strobj == null || strobj.trim().equals("")) {
			return 0.00;
		}

		return Double.parseDouble(strobj);
	}

	public static BigDecimal convertirStringToBigDecimal(String strobj) {
		if (strobj == null || strobj.trim().equals("")) {
			return new BigDecimal(0.00);
		}

		return new BigDecimal(strobj);
	}

	public static Long convertirStringToLong(String strobj) {
		if (strobj == null || strobj.trim().equals("")) {
			return new Long(0);
		}

		return new Long(strobj);
	}

	public static int convertirStirngToInteger(String strobj) {
		if ((strobj == null) || strobj.trim().equals("")) {
			return -1;
		}
		return Integer.parseInt(strobj);
	}

	public static Double validateDouble(Double dblobj) {
		if (dblobj == null) {
			return 0.00;
		}

		return dblobj;
	}

	public static BigDecimal validateBigDecimal(BigDecimal gblobj) {
		if (gblobj == null) {
			return new BigDecimal(0.00);
		}

		return gblobj;
	}

	public static Double Redondear2Decimales(Double dblnum) {

		double numero = dblnum * 100;
		numero = Math.round(numero);
		dblnum = numero / 100;

		return dblnum;

	}

	public static Double Redondear1Decimal(Double dblnum) {

		double numero = dblnum * 10;
		numero = Math.round(numero);
		dblnum = numero / 10;

		return dblnum;

	}

	public static Double redondear(Double dblnum) {

		double numero = dblnum * 1;
		numero = Math.round(numero);
		dblnum = numero / 1;

		return dblnum;

	}

	public static Integer convertirLongToInteger(Long lngValor) {
		if (lngValor == null) {
			return -1;
		}
		return lngValor.intValue();
	}

	public static BigDecimal convertirDoubleToBigDecimal(Double dblValor) {
		if (dblValor == null) {
			return new BigDecimal(0.00);
		}

		return new BigDecimal(dblValor);
	}

	public static Double convertirBigDecimalToDouble(BigDecimal bgValor) {
		if (bgValor == null) {
			return 0.00;
		}

		return bgValor.doubleValue();
	}

	public static String convertirBigDecimaToString(BigDecimal bgNumero) {
		if (bgNumero == null) {
			return "0.00";
		}
		return bgNumero.toString();
	}

	public static String convertirLongToString(Long lngNumero) {
		if (lngNumero == null) {
			return "0.00";
		}
		return lngNumero.toString();
	}

	public static String formatearNumero(Double numero, String Formato) {
		NumberFormat format = new DecimalFormat(Formato);
		return format.format(numero);
	}

	public static String formatearNumero(int numero, String Formato) {
		NumberFormat format = new DecimalFormat(Formato);
		return format.format(numero);
	}

	public static String formatearNumero(Long numero, String Formato) {
		NumberFormat format = new DecimalFormat(Formato);
		return format.format(numero);
	}

	public static BigDecimal redondearBigDecimal(BigDecimal numero, int decimales) {

		return numero.setScale(decimales, BigDecimal.ROUND_HALF_UP);
	}

	public static Integer convetirStringToInteger(String numero) {
		if (("").equals(numero) || numero == null) {
			return -1;
		}
		return Integer.parseInt(numero);
	}

	public static Long convertirIntegerToLong(Integer numero) {
		if (numero == null) {
			return new Long(0);
		}
		return new Long(numero);
	}

	public static Double convertirObjectToDouble(Object numero) {
		if (numero == null) {
			return 0.00;
		}
		return Double.parseDouble(numero + "");
	}

	public static double reducirAdosDecimales(double dblnum) {

		double numero = dblnum * 100;
		numero = Math.round(numero);
		dblnum = numero / 100;

		return dblnum;

	}

	public static boolean decimalMayor(Double valor1, Double comparar) {
		if (valor1 != null && comparar != null) {
			if (valor1 > comparar) {
				return true;
			}
		}

		return false;
	}

	public static boolean decimalMayorMasMenos(Double valor1, Double comparar) {
		if (valor1 != null && comparar != null) {
			if (valor1 > comparar || multiplicar(valor1, -1.0) > comparar) {
				return true;
			}
		}

		return false;
	}

	// Metodo agregado por Fabian Geldres
	// Sirve para Formatear un Double a 1,222.00
	public static String formatearNumeroLocaleUS(Double numero) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		return format.format(numero);
	}

	public static Double multiplicar(Double multiplicando, Double multiplicador) {
		Double producto = 0.0;
		if (multiplicando != null && multiplicador != null) {
			producto = multiplicando * multiplicador;
			producto = Redondear2Decimales(producto);
		}
		return producto;
	}

	public static String numeroMayorCeroRedondeadoFUS(Double numero) {
		if (numero != null) {
			if (decimalMayorMasMenos(numero, 0.0)) {
				return formatearNumeroLocaleUS(Redondear2Decimales(numero));
			}
		}
		return " ";
	}

	/**
	 * Agregado por Fabian Verifica si dos numeros son iguales
	 */

	public static boolean verificarIgualdad(Long numero1, Long numero2) {
		if (numero1.compareTo(numero2) == 0) {
			return true;
		}
		return false;
	}

	public static String mostrarNumero(double numero) {
		if (numero > 0.0 || (numero * -1) > 0.0) {
			return numero + "";
		}
		return "";
	}

	public static double porcentaje(Double porcentajeRealConsumocomponen, Double tmProdConsumocomponenteajus) {
		Double porcentaje = 0.0;
		porcentaje = ((porcentajeRealConsumocomponen * 100) / tmProdConsumocomponenteajus);
		porcentaje = reducirAdosDecimales(porcentaje);

		return porcentaje;
	}

	public static double redondear(double numero, int numeroDecimales) {
		long mult = (long) Math.pow(10, numeroDecimales);
		double resultado = (Math.round(numero * mult)) / (double) mult;
		return resultado;
	}

	/**
	 * este redondeo es utilizado solo para el reporte parte tecnico
	 * 
	 * @param numeroAredondear Numero que se va a redondear
	 * @param cantidadDecimales Cantidad de Decimales del Numero
	 * @param unidadMedida Unidad de medida (En Caso sea M3) lo convierte en
	 *            Galones
	 * @return
	 */
	public static String redondeoReportePT(double numeroAredondear, int cantidadDecimales, String unidadMedida) {

//		try {
//			if (unidadMedida != null && unidadMedida.equals(ConstantesParametro.TIPOMEDIDA_M3)) {
//				numeroAredondear = convertirGalonesaM3(numeroAredondear);
//			}
//		} catch (NumberFormatException e1) {
//			e1.printStackTrace();
//		} catch (LogicaException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Double numeroRedondeado = redondear(numeroAredondear, cantidadDecimales);
		String numeroACadena = "";

		try {

			if (cantidadDecimales == 0) {
				numeroACadena = numeroRedondeado.intValue() + "";
				return numeroACadena;
			}
			numeroACadena = numeroRedondeado.toString();
			int decimales = numeroACadena.substring(numeroACadena.indexOf(".") + 1, numeroACadena.length()).length();
			decimales = cantidadDecimales - decimales;
			for (int i = 0; i < decimales; i++) {
				numeroACadena += "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numeroACadena;
	}

//	private static double convertirGalonesaM3(double numero) throws NumberFormatException, LogicaException {
//
//		ParametroSistemaBean parametrosistema = parametro.obtenerParametroSistema(ConstantesParametro.TIPOMEDIDA_GALONES);
//		Double convertor = 1d;
//
//		if (parametrosistema != null) {
//			convertor = Double.valueOf(parametrosistema.getValor());
//		}
//		numero = numero / convertor;
//		return numero;
//	}

	public static Double sumar(Double... tmRealPuestotrabajoproduccion) {
		Double suma = 0d;
		if (tmRealPuestotrabajoproduccion != null) {
			for (int i = 0; i < tmRealPuestotrabajoproduccion.length; i++) {
				if (tmRealPuestotrabajoproduccion[i] != null) {
					suma += tmRealPuestotrabajoproduccion[i];
				}

			}
		}
		suma = Redondear2Decimales(suma);
		return suma;
	}

	public static Double dividir(Double dividendo, Double divisor) {
		Double producto = 0d;
		if (divisor != null && divisor > 0 && dividendo != null) {
			producto = dividendo / divisor;
			producto = Redondear2Decimales(producto);
		}
		return producto;
	}

	public static Double dividirSinRedondear(Double dividendo, Double divisor) {
		Double producto = 0d;
		if (divisor != null && divisor > 0 && dividendo != null) {
			producto = dividendo / divisor;

		}
		return producto;
	}

	/**
	 * Formula para calcular la proporcion (cantidadAdistribuir*cantidad/total)
	 * 
	 * @param cantidadAdistribuir la cantidad la que voy a distribuir
	 * @param cantidad
	 * @param total
	 * @return
	 */
	public static Double formulaProporcion(Double cantidadAdistribuir, Double cantidad, Double total) {

		Double proporcion = (cantidadAdistribuir * cantidad / total);

		return proporcion;
	}

	public static BigDecimal convertirNegativoBigDecimal(BigDecimal gblobj) {
		if (gblobj == null) {
			return new BigDecimal(0d);
		}
		gblobj = gblobj.multiply(new BigDecimal(-1d));
		return gblobj;
	}

	public static Long convertirObjectToLong(Object numero) {
		if (numero == null) {
			return 0L;
		}
		return Long.parseLong(numero.toString());
	}
}
